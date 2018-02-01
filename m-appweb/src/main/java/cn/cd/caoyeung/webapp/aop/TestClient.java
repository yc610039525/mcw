package cn.cd.caoyeung.webapp.aop;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * AOP客户端测试类
 *
 */
public class TestClient
{
  public static void main(String[] args)
  {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
    System.out.println(ctx);
    
    IPerson person = ctx.getBean("chinese_aop",IPerson.class);
    
    person.sayHello("jack");
    person.eat("米饭",new Date());
    person.eat3("米饭",new Date(),"上海");
  }
}
