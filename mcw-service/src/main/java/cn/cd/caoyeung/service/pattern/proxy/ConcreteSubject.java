package cn.cd.caoyeung.service.pattern.proxy;
/**
 * 代理类必须实现接口  JDK动态代理基于接口 cglib代理生成实现类的子类
 * @author: CaoYeung
 * @time: 下午9:26:44
 */
public class ConcreteSubject implements Subject {

	@Override
	public void exec(String s) {
      System.out.println(s);
	}

}
