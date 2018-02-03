package cn.cd.caoyeung.webapp.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class WebContextUtils {
	public static HttpServletRequest getHttpServletRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		return request;
	}
	public static HttpSession getHttpSession() {
		HttpServletRequest httpServletRequest = WebContextUtils.getHttpServletRequest();
		HttpSession session = httpServletRequest.getSession();
		return session;
	}
	public static HttpSession getHttpSession(HttpServletRequest httpServletRequest){
		return httpServletRequest.getSession();
	}
	
	public static Object getSessionAttrValue(String attrName) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		Object obj = requestAttributes.getAttribute(attrName, RequestAttributes.SCOPE_SESSION);
		return obj;
	}
	public static Object getSessionAttrValue(String attrName,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = WebContextUtils.getHttpSession(httpServletRequest);
		Object obj = httpSession.getAttribute(attrName);
		return obj;
	}
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttrValue(String attrName,HttpServletRequest httpServletRequest,Class<T> cls) {
		HttpSession httpSession = WebContextUtils.getHttpSession(httpServletRequest);
		Object obj = httpSession.getAttribute(attrName);
		return (T)obj;
	}
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttrValue(String name,Class<T> cls){
		HttpSession session = WebContextUtils.getHttpSession();
		Object attribute = session.getAttribute(name);
		return (T)attribute;
	} 
	public static void setSessionAttrValue(String name,Object value){
		HttpSession session = WebContextUtils.getHttpSession();
		session.setAttribute(name, value);
	}
	public static ServletContext getServletContext(HttpServletRequest httpServletRequest){
		return httpServletRequest.getSession().getServletContext();
	}
	public static String getServletContextInitParamValue(HttpServletRequest httpServletRequest,String name){
		ServletContext servletContext = WebContextUtils.getServletContext(httpServletRequest);
		return servletContext.getInitParameter(name);
	}
	@SuppressWarnings("unchecked")
	public static <T> T getHttpServletRequestParamValue(String name,HttpServletRequest httpServletRequest,Class<T> cls){
		Object attribute = httpServletRequest.getAttribute(name);
		return (T)attribute;
	}
	public static Set<String> getAllAPIURL(HttpServletRequest request,HttpServletResponse response) {  
		Set<String> urls = new HashSet<String>();  
	    WebApplicationContext wac = (WebApplicationContext) request.getAttribute(
	    		DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	    Map<String, HandlerMapping> requestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(
	    		wac, HandlerMapping.class, true, false);  
	    for(HandlerMapping handlerMapping : requestMappings.values()) {  
	        if(handlerMapping instanceof RequestMappingHandlerMapping) {  
	            RequestMappingHandlerMapping rmhm = (RequestMappingHandlerMapping) handlerMapping;  
	            Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();  
	            for(RequestMappingInfo rmi : handlerMethods.keySet()) {  
	                PatternsRequestCondition prc = rmi.getPatternsCondition();  
	                Set<String> patterns = prc.getPatterns();  
	                for (String url : patterns)  
	                    urls.add(url);  
	            }  
	        }  
	    }  
	    return urls;
	}  
}
