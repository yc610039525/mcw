package cn.cd.caoyeung.webapp.aop.annotion;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.cd.caoyeung.webapp.bean.LogMessage;

/**
 *注解方式声明拦截处理 
 */
@Service
@Aspect
public class LogMessageHandler {
	private static final Log lg = LogFactory.getLog(LogMessageHandler.class);
	public LogMessageHandler(){
		lg.info("Init LogMessageHandler");
	}
	/**
	 * 切入点
	 * 通过注解获取方法 或通过方法后去注解
	 */
//	@Pointcut("@annotation(cn.cd.caoyeung.webapp.bean.LogMessage)")    
    public void logMessagePointcut() {} 
//	@Before("logMessagePointcut()")    
    public void handleLogMessage2(JoinPoint joinPoint) {
		lg.info("ClassName:"+joinPoint.getTarget().getClass().getName());
		lg.info("MethodName:"+joinPoint.getSignature().getName());
	} 
	/**
	 * 通过注解获取方法 或通过方法后去注解
	 */
//	@Pointcut("execution(* cn.cd.caoyeung.webapp.controller.*.*(..))")    
    public void controllerPointCut() {} 
//	@Before("controllerPointCut()")    
    public  void handleLogMessage(JoinPoint joinPoint) throws Exception {    
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String requestURI = request.getRequestURI();
//		lg.info("RequestURI："+requestURI);
//		lg.info("Description："+LogMessageHandler.getAnnotionInfo(joinPoint));
	
	} 
	
	public static String getAnnotionInfo(JoinPoint joinPoint) throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class<?> targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
				Class<?>[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) { 
                	LogMessage lg = method.getAnnotation(LogMessage.class);
                	if(lg!=null){
                		description = lg.description();
                	}
                    break;  
                }  
            }  
        }  
        return description;  
    }  
}
