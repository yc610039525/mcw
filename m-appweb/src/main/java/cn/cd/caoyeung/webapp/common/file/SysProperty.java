package cn.cd.caoyeung.webapp.common.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
@SuppressWarnings("unchecked")
public class SysProperty extends PropertyPlaceholderConfigurer {
	
	private static Properties PROP;
	
	
	private static Map<String, String> HOST_CFGS = new HashMap<String, String>();
	
	public static final String CONTEXT_PATH = "CONTEXT_PATH";
	
	private static SysProperty instance = new SysProperty();
	
	private String[] custLocations;

	public void setCustLocations(String[] custLocations) {
		this.custLocations = custLocations;
	}
	
	private String srvLocation;
	
	public void setSrvLocation(String srvLocation) {
		this.srvLocation = srvLocation;
	}

	public static SysProperty getInstance(){
		return instance;
	}
	private Properties prop;
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			prop = mergeProperties();
			convertProperties(prop);
			processProperties(beanFactory, prop);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			throw new BeanInitializationException("Could not load properties", ex);
		}
	}
	
	public Properties getProperties() {
		Properties p = new Properties();
		for(Object k : prop.keySet()) {
			String pk = k.toString();
			p.setProperty(pk, prop.getProperty(pk));
		}
		if(PROP == null) {
			this.refreshProp();
		}
		if(PROP != null) {
			for(Object k : PROP.keySet()) {
				String pk = k.toString();
				p.setProperty(pk, PROP.getProperty(pk));
			}
		}
		return p;
	}
	

	public void setValue(String key,String value){
		instance.prop.put(key, value);
	}
	
	public Map<String, String> getClientSrvMap(String clientIp) {
		if(StringUtils.isBlank(clientIp)) throw new RuntimeException("缺少参数：clientIp");
		Map<String, String> map = new HashMap<String, String>();
		String hostType = null;
		loop : for(String key : HOST_CFGS.keySet()) {
			String host = HOST_CFGS.get(key);
			String[] ss = StringUtils.split(host, ",");
			for(String s : ss) {
				if(clientIp.startsWith(s)) {
					hostType = key;
					break loop;
				}
			}
		}
		return map;
	}
	
	private Map<String, Resource> files;
	
	public void setFiles(Map<String,Resource> files){
		this.files = files;
	}
	
	public String getValue(String key){
		if(PROP == null) {
			this.refreshProp();
		}
		String value = null;
		
		if(PROP != null) {
			value = PROP.getProperty(key);
		}
		if(value == null) {
			value = instance.prop.getProperty(key);
		}
		return value;
	}
	
	public void refreshSrv() {
		if(srvLocation != null) {
			try {
				HOST_CFGS.clear();
				List<Document> docList = ResourceFileUtil.getDocumentsByClassPath(srvLocation);
				for(Document doc : docList) {
					Element root = doc.getRootElement();
					List<Element> hostList = root.selectNodes("domain/host");
					for(Element el : hostList) {
	    				String text = el.getTextTrim();
	    				HOST_CFGS.put(el.attributeValue("type"), text);
					}
					
					List<Element> srvList = root.selectNodes("servers/server");
					for(Element el : srvList) {
						String name= el.attributeValue("name");
						if(StringUtils.isBlank(name)) continue;
						String refreshBO = el.attributeValue("refreshBO");
						String serverUrl = el.attributeValue("serverUrl");
						Map<String, String> urlMap = new HashMap<String, String>();
						List<Element> urlElList = el.selectNodes("url");
						for(Element urlEl : urlElList) {
							String urlType = urlEl.attributeValue("type");
							String url = urlEl.getTextTrim();
							urlMap.put(urlType, url);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void refreshProp() {
		if(custLocations != null) {
			PROP = new Properties();
			for(String loc : custLocations) {
				Resource[] fs;
				try {
					fs = ResourceFileUtil.getResourceFromClassPath(loc);
					for(Resource f : fs) {
						Properties p = new Properties();
						p.load(f.getInputStream());
						for(Object k : p.keySet()) {
							String pk = k.toString();
							PROP.setProperty(pk, p.getProperty(pk));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			PROP = null;
		}
	}
	
	public String getValue(String key,String defaultValue){
		String value = this.getValue(key);
		if(StringUtils.isEmpty(value)){
			value = defaultValue;
		}
		return value;
	}
	
	public Set getKeySet(){
		return this.getProperties().keySet();
	}
	
	public String getFilePath(String key){
		try {
			return this.files.get(key).getFile().getPath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public File getFile(String key){
		try {
			return this.files.get(key).getFile();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Resource getResource(String key){
		return this.files.get(key);
	}
	
}
