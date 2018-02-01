package cn.cd.caoyeung.service.pattern.bulider;
/**
 * 具体实现细节
 * @author: CaoYeung
 * @time: 下午8:04:08
 */
public class JeepBuilder{
	//可在抽象IProduct  IComponent
	protected Car jeep = new Car();
	
	public Car bulidHead() {
		this.jeep.setHeader("JeepHead");
		return jeep;
	}

	public Car bulidBody() {
		this.jeep.setBody("JeepBody");
		return jeep;
	}

	public Car bulidTail() {
		this.jeep.setTail("JeepTail");
		return jeep;
	}
	public Car getCar(){
		return jeep;
	};
}
