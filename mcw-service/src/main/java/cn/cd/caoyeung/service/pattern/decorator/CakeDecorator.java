package cn.cd.caoyeung.service.pattern.decorator;

public class CakeDecorator implements ICakeComponent {
	protected MilkCake milkCake;
	@Override
	public void makeCake() {
		milkCake.makeCake();
		this.milkCake.remark = "油脂含量标准";
	}
	public CakeDecorator(MilkCake milkCake) {
	    super();
	    this.milkCake = milkCake;
    }

}

