package cn.cd.caoyeung.webapp.ws.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IHelloWorld {  
	 @WebMethod
	 String sayHi(@WebParam(name="text")String text);     
}  
