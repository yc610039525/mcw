package cn.cd.caoyeung.service.pattern.proxy;

/**
 * 静态代理
 * @author: CaoYeung
 * @time: 下午9:24:32
 */
public class Proxy {
	private Subject subject;
	
	public Proxy(Subject subject) {
		super();
		this.subject = subject;
	}

	public void exec(String  k){
		System.out.println("前处理。。。。。");
		subject.exec(k);
		System.out.println("后处理。。。。。");
	}

}
