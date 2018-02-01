package test.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import cn.cd.caoyeung.webapp.ws.server.IHelloWorld;

import base.SpringTestBaseCase;



public class HelloWorldWebServiceClientTest extends SpringTestBaseCase {
	  
	@Test public  void helloWorldWebServiceTest(){
		//http://localhost:8080/m-appweb/cxf/helloWorld?wsdl
	      String ADDRESS = "http://localhost:8080/m-appweb/cxf/helloWorld";
		  JaxWsProxyFactoryBean jwpFactory = new JaxWsProxyFactoryBean();  
	      jwpFactory.setAddress(ADDRESS);  
	      jwpFactory.setServiceClass(IHelloWorld.class);  
	      IHelloWorld hw = (IHelloWorld)jwpFactory.create(); 
	      String response = hw.sayHi("world");  
	      System.out.println(response); 
	     
	   }  
}
