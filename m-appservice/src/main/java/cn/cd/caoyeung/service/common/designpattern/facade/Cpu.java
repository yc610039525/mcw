package cn.cd.caoyeung.service.common.designpattern.facade;

public class Cpu {
	public void startup() {
		System.out.println("加载" + "CPU");
	}

	public void shutdown() {
		System.out.println("关闭" + "CPU");
	}
}
