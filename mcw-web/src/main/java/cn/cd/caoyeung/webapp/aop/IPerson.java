package cn.cd.caoyeung.webapp.aop;

import java.util.Date;

public interface IPerson {
  String sayHello(String name);
  void eat(String food, Date time);
  void eat2(String food, Date time,String address);
  void eat3(String food, Date time,String address);
}
