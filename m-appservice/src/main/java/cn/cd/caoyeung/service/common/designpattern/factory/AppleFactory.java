package cn.cd.caoyeung.service.common.designpattern.factory;

public class AppleFactory implements IFruitFactory {

	@Override
	public Fruit getFruit() {
		System.out.println("create Apple");
		return new Apple();
	}

}
