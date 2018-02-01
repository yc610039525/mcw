package cn.cd.caoyeung.webapp.aop;

import java.util.Date;
public class Chinese implements IPerson
{

  @Override
  public void eat(String afood, Date atime)
  {
    System.out.println("正在吃: " + afood + ", 时间是: " + atime);
  }

  @Override
  public String sayHello(String name)
  {
    return name + " Hello, Spring AOP.";
  }
  @Override
  public void eat2(String food, Date time,String address)
  {
    System.out.println("Chinese --------- 正在吃: " + food + ", 时间是: " + time + ", 地點: " + address);
  }
  @Override
  public void eat3(String food, Date time,String address)
  {
	    System.out.println("Chinese --------- 正在吃: " + food + ", 时间是: " + time + ",地點: " + address);
	  }
}
