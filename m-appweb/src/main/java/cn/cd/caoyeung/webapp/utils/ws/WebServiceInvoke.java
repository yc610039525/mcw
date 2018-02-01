package cn.cd.caoyeung.webapp.utils.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.lang.StringUtils;

public class WebServiceInvoke {

	/**
	 * 调用ws方法
	 * @param url
	 * @param method
	 * @param params
	 * @return
	 */
	public static String invoke(String url, String method,
			OrderedMap params) {

		String result = null;
		//创建一个服务
		try {
		Service service = new Service();
		Call call = (Call)service.createCall();
		call.setOperationName(method);
		// 设置WS地址
		call.setTargetEndpointAddress(url);
		 
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
		result = (String) call.invoke(paramValues.toArray());
		}catch (Exception e){
			result = e.getMessage();
		}
		return result;
	
	}
	
	/**
	 * 调用ws方法
	 * @param url
	 * @param method
	 * @param qName
	 * @param params
	 * @return
	 */
	public static String invoke(String url, String method,String qName,
			OrderedMap params) {
		return invoke(url, method,qName, params, null);
	}
	
	/**
	 * 调用ws方法
	 * @param url
	 * @param method
	 * @param qName
	 * @param params
	 * @param timeout
	 * @return
	 */
	public static String invoke(String url, String method,String qName,
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
	
}
