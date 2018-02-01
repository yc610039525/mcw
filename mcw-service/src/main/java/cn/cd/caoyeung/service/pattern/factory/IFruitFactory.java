package cn.cd.caoyeung.service.pattern.factory;
/**
 * 工厂方法/多态工厂方法
 * 工厂抽象 -具体工厂
 * 产品抽象-具体产品
 * @author: CaoYeung
 * @time: 下午4:31:44
 */
 public interface IFruitFactory {
	/**
	 * 工厂方法
	 * @return
	 */
	Fruit getFruit();
}
