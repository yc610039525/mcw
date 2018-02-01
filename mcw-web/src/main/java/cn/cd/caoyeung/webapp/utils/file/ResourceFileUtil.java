package cn.cd.caoyeung.webapp.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ResourceFileUtil {

	public static File[] getFileFromClassPath(String classPath) throws IOException {
		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
		Resource[] crs = resourceLoader.getResources(classPath);
		File[] files = new File[crs.length];
		for(int i = 0 ; i < crs.length; i++) {
			files[i] = crs[i].getFile();
		}
		return files;
	}
	
	public static Resource[] getResourceFromClassPath(String classPath) throws IOException {
		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
		return resourceLoader.getResources(classPath);
	}
	
	public static Document getDocument(Resource f) throws Exception {
    	SAXReader reader = new SAXReader();
    	Document doc = reader.read(f.getInputStream());
    	return doc;
    }
	
	public static Document getDocument(InputStream i) throws DocumentException {
    	SAXReader reader = new SAXReader();
    	Document doc = reader.read(i);
    	return doc;
    }
    
    public static List<Document> getDocumentsByClassPath(String classpath) throws IOException, DocumentException {
    	List<Document> list = new ArrayList<Document>();
    	Resource[] rs = ResourceFileUtil.getResourceFromClassPath(classpath);
    	for(Resource r:rs){
    		list.add(ResourceFileUtil.getDocument(r.getInputStream()));
    	}
    	return list;
    }
}
