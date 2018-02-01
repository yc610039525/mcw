package cn.cd.caoyeung.webapp.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
/**
 * 全注解配置AOP
 */
@Service
@Aspect
public class AnnotionAspect {
//	@Pointcut("execution(* com.team.cd.aop..*(..))")
	private void pointCutLog() {
	}
	@Before("execution(* cn.cd.caoyeung.webapp.aop..*(..))")  
//	@Before("pointCutLog()")  
    public void before(){  
        System.out.println("使用全注解配置AOP");  
    }  

	
}
