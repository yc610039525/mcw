package cn.cd.caoyeung.service.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 动态代理借口
 * @author: CaoYeung
 * @time: 下午9:24:48
 * @param <T>
 */
public class DynamicInvocetionHander<T> implements InvocationHandler {
	private T subject;
	public DynamicInvocetionHander(T concreteSubject){
		this.subject = concreteSubject;
	}

	@Override
	public Object invoke(Object proxy, Method md, Object[] args)throws Throwable {
		System.out.println("代理接口："+proxy.getClass().getName());
		System.out.println("代理接口方法："+md.getName()+",参数："+args.toString());
		md.invoke(this.subject, args);
		return null;
	}

}
