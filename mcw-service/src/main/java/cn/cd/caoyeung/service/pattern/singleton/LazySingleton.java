package cn.cd.caoyeung.service.pattern.singleton;

/**
 * 懒汉式
 * 
 * @author: CaoYeung
 * @time: 下午5:15:57
 */
public class LazySingleton {
	private static volatile LazySingleton singleton;// 1

	private LazySingleton() {
	}

	public static LazySingleton getInstance() {
		if (singleton == null) {
			synchronized (LazySingleton.class) {// 2 1和2 2处才能保证只存在一个实例
				if (singleton == null) {
					singleton = new LazySingleton();
				}
			}
		}
		return singleton;
	}
}
