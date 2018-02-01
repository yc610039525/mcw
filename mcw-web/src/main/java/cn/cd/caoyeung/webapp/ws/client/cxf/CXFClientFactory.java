package cn.cd.caoyeung.webapp.ws.client.cxf;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CXFClientFactory {
	
	
	private static Map<String ,Client> clientMap = new HashMap<String, Client>();
	
	private static JaxWsDynamicClientFactory jax  = JaxWsDynamicClientFactory.newInstance();
	
	public static Client getClient(String serviceUrl){
		Client client = clientMap.get(serviceUrl);
		if(client==null){
			client = jax.createClient(serviceUrl);
			clientMap.put(serviceUrl, client);
		}
		return client;
	}
	
	public static Object[] invoke(String wsdlUrl,String methodName,Object[] params){
		Client client = CXFClientFactory.getClient(wsdlUrl);
		Object[] result=null;
		try {
			result = client.invoke(methodName, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) throws Exception {

		String address="http://localhost/spring.framework/cxf/helloWorld?wsdl";
//		String address="http://localhost/business-web/services/HelloWorld?wsdl";
		Object[] result = CXFClientFactory.invoke(address, "sayHi", new Object[]{"wlh"});
		System.out.println(result[0]);
	}
}
