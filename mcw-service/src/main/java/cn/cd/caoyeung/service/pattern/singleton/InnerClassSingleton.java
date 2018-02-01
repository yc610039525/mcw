package cn.cd.caoyeung.service.pattern.singleton;

import java.io.IOException;

/**
 * <p>这种方式跟饿汉式方式采用的机制类似，但又有不同。
 * 两者都是采用了类装载的机制来保证初始化实例时只有一个线程。
 * 不同的地方在饿汉式方式是只要Singleton类被装载就会实例化，
 * 没有Lazy-Loading的作用，而静态内部类方式在Singleton类被装载时并不会立即实例化，
 * 而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，
 * 从而完成Singleton的实例化。</p>
 * @author: CaoYeung
 * @time: 下午5:24:00
 */
public class InnerClassSingleton {

	private InnerClassSingleton() {
	}

	private static class SingletonInstance {
		private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
	}

	public static InnerClassSingleton getInstance() {
		return SingletonInstance.INSTANCE;
	}
	public static void main(String[] args) throws IOException {
		HungrySingleton.printAttrName();
		
	}
}
