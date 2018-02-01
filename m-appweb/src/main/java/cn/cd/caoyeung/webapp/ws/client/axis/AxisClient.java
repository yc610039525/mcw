package cn.cd.caoyeung.webapp.ws.client.axis;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

public class AxisClient {
	/**
	 * Axis服务调用
	 * @param url
	 * @param method
	 * @param qName
	 * @param params
	 * @param timeout
	 * @return
	 */
	/**
	 * 访问可能失败 
	 * call.setOperation(new QName(qName), method);?
	 */
	public static String invokeForAxis(String url, String method,String qName,
			OrderedMap params, Integer timeout) {
		String result = null;
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setUseSOAPAction(true);
			call.setOperationStyle(org.apache.axis.constants.Style.WRAPPED);
			call.setOperationUse(org.apache.axis.constants.Use.LITERAL);
			// 设置WS地址
			call.setTargetEndpointAddress(url);
			// 设置方法
			if(StringUtils.isEmpty(qName)){
				call.setOperationName(method);
			}else{
				call.setOperation(new QName(qName), method);
			}
			// 设置入参类型
			List<Object> paramValues = new ArrayList<Object>();
			if (params != null) {
				for (Object paramName : params.keySet()) {
					call.addParameter((String)paramName,
							org.apache.axis.encoding.XMLType.XSD_STRING,
							javax.xml.rpc.ParameterMode.IN);
					paramValues.add(params.get(paramName));
				}
			}
			// 设置返回值类型
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			if(timeout != null){
				call.setTimeout(timeout);
			}
			result = (String) call.invoke(paramValues.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
		return result;
	}
	public static String invokeForCXF(String address, String method,String namespaceURI,
			OrderedMap params, Integer timeout) throws ServiceException, RemoteException {
//		String address="http://localhost/spring.framework/cxf/helloWorld?wsdl";
	    String result = null;
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setUseSOAPAction(true);
		call.setOperationStyle(org.apache.axis.constants.Style.WRAPPED);
		call.setOperationUse(org.apache.axis.constants.Use.LITERAL);
		call.setTargetEndpointAddress(address);
//		"http://ws.yc610039525.cnblog.com"
		if(StringUtils.isEmpty(namespaceURI)){
			call.setOperationName(method);
		}else{
			call.setOperationName(new QName(namespaceURI, method));
		}
//       定义所有参数均为字符串
	    List<Object> paramValues = new ArrayList<Object>();
	    for (Object paramName : params.keySet()) {
			call.addParameter((String)paramName,
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);
			paramValues.add(params.get(paramName));
		}
	    
	    call.setTimeout(timeout);
	    call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
	    /*call.addParameter("textParam",
				org.apache.axis.encoding.XMLType.XSD_STRING,
				javax.xml.rpc.ParameterMode.IN);
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
		*/
	    String returnValue = (String) call.invoke(paramValues.toArray());
		return returnValue;
	}
	public static void main(String[] args) throws ServiceException, MalformedURLException, RemoteException {
//			String address="http://localhost:8888/spring.framework/cxf/helloWorld?wsdl";
			String address="http://localhost/business-web/services/HelloWorld?wsdl";
			String method = "sayHi";
			String namespaceURI = "http://impl.api.business.org";
			OrderedMap params = new ListOrderedMap(){
					{put("name","Shirely");}
				}; 
			Integer timeout = 6000000;
		    String returnValue = AxisClient.invokeForCXF(address, method, namespaceURI, params, timeout);
//		    System.out.println(returnValue);
//		    String returnValue = AxisClient.invokeForAxis(address, method, namespaceURI, params, timeout);
		    System.out.println(returnValue);
	  }
	
	
	/*常见异常：

	1.org.xml.sax.SAXException: No deserializer for {http://www.w3.org/2001/XMLSchema}anyType

	这个异常需要检查call.setReturnClass(); 中的类型，或者是setReturnType()

	2. Unexpected wrapper element order found. 

	可以检查setOperationName中的QName参数，其中的NameSpace地址是不是有问题，或者operation名是否有误
	 */
}
