package cn.cd.caoyeung.service.pattern.singleton;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 饿汉式
 * 常见一个对象需要消耗过多的资源 IO 数据库访问
 * @author: CaoYeung
 * @time: 下午5:11:27
 */
public class HungrySingleton {
	 private static HungrySingleton instance;
	 static Map<String,String> attr =new HashMap<String,String>();  
	 static {
	        instance = new HungrySingleton();//或使用静态常量  类加载机制
	        attr.put("1", "3");
	        attr.put("2", "2");
	        attr.put("3", "1");
	    }
	    
	    private HungrySingleton() {}
	    
	    public static HungrySingleton getInstance() {
	        return instance;
	    }
	    public static void printAttrName(){
	    	for(String k :attr.keySet()){
	    		System.out.println("AttrName:"+k);
	    	}
	    }
	    public static void main(String[] args) throws IOException {
	    	HungrySingleton instance= HungrySingleton.getInstance();
	    	attr.put("4", "");
	    	HungrySingleton.printAttrName();
	    	System.in.read();
	    	
		}
}
