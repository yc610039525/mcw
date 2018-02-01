package cn.cd.caoyeung.service.pattern.facade;

public class Memory {
	public void startup() {
		System.out.println("加载" + "Memory");
	}

	public void shutdown() {
		System.out.println("关闭" + "Memory");
	}
}
