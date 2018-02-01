package cn.cd.caoyeung.service.pattern.facade;

public class Computer {
	Cpu cpu;
	Memory my;
	Disk disk;
	
	public Computer() {
		super();
		this.cpu =  new Cpu();
		this.my = new Memory();
		this.disk = new Disk();
	}

	public void startup() {
		cpu.startup();
		my.startup();
		disk.startup();
		System.out.println("电脑启动完成");
	}

	public void shutdown() {
		cpu.shutdown();
		my.shutdown();
		disk.shutdown();
		System.out.println("电脑关闭完成");
	}
}
