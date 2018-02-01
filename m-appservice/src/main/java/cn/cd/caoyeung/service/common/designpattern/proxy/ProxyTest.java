package cn.cd.caoyeung.service.common.designpattern.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) {
		Subject subject = new ConcreteSubject();
		InvocationHandler handler =new DynamicInvocetionHander<Subject>(subject);
		
		Subject object = (Subject)Proxy.newProxyInstance(subject.getClass().getClassLoader(), 
				new Class[]{Subject.class}, handler);
		object.exec("执行方法");
	
	}
}
