package cn.cd.caoyeung.service.common.designpattern.factory;
/**
 * 
 * @author Administrator
 *
 */
public class GrapeFactory implements IFruitFactory {

	@Override
	public Fruit getFruit() {
		System.out.println("create Grape");
		return new Apple();
	}

}
