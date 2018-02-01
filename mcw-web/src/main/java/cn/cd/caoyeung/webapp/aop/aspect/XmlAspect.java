package cn.cd.caoyeung.webapp.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
/**
 * xml配置AOP
 */
public class XmlAspect {
  public void afterReturn(String retval) {
    System.out.println("Return Value: " + retval);
  }
//  public void before(JoinPoint jp,Date time,String food)
  public void before(JoinPoint jp) {
    System.out.println("参数列表:" + Arrays.toString(jp.getArgs()));
    System.out.println("方法名:"+jp.getSignature().getName());
  }
}
