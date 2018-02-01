package cn.cd.caoyeung.service.pattern.bulider;
/**
 * 指挥者/设计者  结构设计 分职责设计
 * @author: CaoYeung
 * @time: 下午7:26:23
 */
public class JeepDirector{
	private JeepBuilder jBuilder;

	public JeepDirector(JeepBuilder jBuilder) {
		this.jBuilder = jBuilder;
	}
	public void setjBuilder(JeepBuilder jBuilder) {
		this.jBuilder = jBuilder;
	}
	public Car makeCar(){
		this.jBuilder.bulidHead();
		this.jBuilder.bulidBody();
		this.jBuilder.bulidTail();
		return jBuilder.getCar();
	}
}
