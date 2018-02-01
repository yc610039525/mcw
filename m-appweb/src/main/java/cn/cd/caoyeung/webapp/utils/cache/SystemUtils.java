package cn.cd.caoyeung.webapp.utils.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class SystemUtils {
	
	public static Properties getSysProperties(){
		return System.getProperties();
	}
	
	public static Map<String, String> getSysEnvMap(){
		return System.getenv();
	}
	
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		Map<String, String> envs = System.getenv();
		
		for(Map.Entry<String, String> env : envs.entrySet()){
			System.out.println(env.getKey()+","+env.getValue());			
		}
		
		for(Entry<Object, Object> entry  : properties.entrySet()){
				System.out.println(entry.getKey()+","+entry.getValue());		
		}
	}
}
