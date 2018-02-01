package cn.cd.caoyeung.webapp.ws.server.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import cn.cd.caoyeung.webapp.ws.server.IHelloWorld;


@WebService(
        endpointInterface="com.team.cd.ws.server.IHelloWorld", //可以指定SEI接口
        name="HelloWorldxxInterface", //不使用SEI接口时规范portType的名称
        serviceName="HelloWorldxxService",   //服务视图的名称
        portName="HelloWorldxxPort",         //Service节点中port节点的name属性
        targetNamespace="https://helloworldxx.ws.team.com/"  //wsdl的命名空间
)
public class HelloWorldImpl implements IHelloWorld {
	
  @Override
  @WebMethod
  public String sayHi(@WebParam(name="text") String text) {
	String msg = "Hello," + text + ",GoodLucky!";  
    return msg; 
  }  
  
}  
