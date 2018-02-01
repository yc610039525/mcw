package cn.cd.caoyeung.service.pattern.factory;

public class FactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IFruitFactory factory = new AppleFactory();
		factory.getFruit();
	}

}
