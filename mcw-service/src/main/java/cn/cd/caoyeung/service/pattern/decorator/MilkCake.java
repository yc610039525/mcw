package cn.cd.caoyeung.service.pattern.decorator;

public class MilkCake implements ICakeComponent {
	protected String name = "";
	protected String remark = "油脂含量高";
	@Override
	public void makeCake() {
	}
	public MilkCake(String name) {
	    super();
	    this.name = name;
    }
	@Override
    public String toString() {
	    return "MilkCake [name=" + name + ",remark=" + remark + "]";
    }
	
}

