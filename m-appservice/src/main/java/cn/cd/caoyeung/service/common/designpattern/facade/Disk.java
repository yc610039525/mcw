package cn.cd.caoyeung.service.common.designpattern.facade;

public class Disk {
	public void startup() {
		System.out.println("加载" + "Disk");
	}

	public void shutdown() {
		System.out.println("关闭" + "Disk");
	}
}
