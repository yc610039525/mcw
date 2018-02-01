package cn.cd.caoyeung.service.pattern.base;

/**
 * 内部类可以被继承  成员内部类内存泄漏
 * 匿名内部类访问外部类常量必须用final
 * 
 * @author Administrator
 *
 */
public class SubClass extends SuperClass {
	public static void main(String[] args) {
		
		SuperClass.sayHi("Lee");
		//子类继承父类静态代码块
		SubClass.sayHi("Lee");
		//调用静态内部类方法
		SuperClass.SuperStaticInnerClass.sayHelloStaticInner("LiLi");
		//子类继承父类静态内部类
		SubClass.SuperStaticInnerClass.sayHelloStaticInner("LiLi");
		//静态内部类可以被继承实例化
		new SubClass.SuperStaticInnerClass().sayHelloInner("Lee");
		//子类继承父类成员内部类
		new SubClass().new SuperInnerClass().sayHelloInner("Tom");
		new SuperClass().new SuperInnerClass().sayHelloInner("Tom");
		
		
		SuperStaticInnerClass superStaticInnerClass = SuperClass.getSuperStaticInnerClass();
		superStaticInnerClass.sayHelloStaticInner("superStaticInnerClass");
		
		String s = SuperClass.SuperInnerClass.CUID;
		String b =SuperClass.SuperStaticInnerClass.CUID;
		System.out.println(b);
		
	}
	
	/*public static void sayHi(String s){
		String b =SuperClass.SuperStaticInnerClass.CUID;
		System.out.println("SubClass.sayHi:"+s);
	}*/
	
	
}
