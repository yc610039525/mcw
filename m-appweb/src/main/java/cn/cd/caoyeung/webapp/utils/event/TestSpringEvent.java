package cn.cd.caoyeung.webapp.utils.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestSpringEvent {
  public static void main(String[] args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
    //创建一个事件对象
    EmailEvent emailEvent = new EmailEvent("Hello","cyang198906@163.com","事件发布.");
    //主动发送ApplicationContext事件
    ctx.publishEvent(emailEvent);
  }
}
