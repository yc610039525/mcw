package cn.cd.caoyeung.service.pattern.base;
/**
 * 继承 内部类说明
 * @author Administrator
 *
 */
public class SuperClass {
	public String objectId = "ObjectId";
	public static String  label = "label";
	public static void sayHi(String s){
		System.out.println("SuperClass.sayHi:"+s);
	}
	public void sayHello(String s){
		System.out.println("SuperClass-sayHello:"+s);
	}
	class SuperInnerClass{
		
		public void sayHelloInner(String s){
			System.out.println("SuperInnerClass-sayHelloInner:"+s+objectId);
		}
		public static final String CUID = "CUID";
//		public static void sayHelloStaticInner(String s){
//			System.out.println("SuperInnerClass-sayHelloInner:"+s);
//		}
	}
	static class SuperStaticInnerClass{
		public static final String CUID = "CUID";
		public void sayHelloInner(String s){
//			System.out.println("SuperInnerClass-sayHelloInner:"+s+objectId);
			System.out.println("SuperInnerClass-sayHelloInner:"+s+label);
		}
		public static void sayHelloStaticInner(String s){
			System.out.println("SuperInnerClass-sayHelloInner:"+s);
		}
	}
	public /*final*/ String f="1212";
	public SuperInnerClass getSuperInnerClass(final String k){
		NoneNamedI si = new NoneNamedI(){
			@Override
			public void sayGoodBye(String s) {
				System.out.println(s+","+f+""+k);
			}
		};
		si.sayGoodBye(f);
		String b =SuperClass.SuperStaticInnerClass.CUID;
		String s = SuperClass.SuperInnerClass.CUID;
		return new SuperInnerClass();
	}
	public static SuperStaticInnerClass getSuperStaticInnerClass(){
		String b =SuperClass.SuperStaticInnerClass.CUID;
		String s = SuperClass.SuperInnerClass.CUID;
		return new SuperStaticInnerClass();
	}
	public static void main(String[] args) {
		new SuperClass().getSuperInnerClass("123");
		SuperClass s = new SuperClass();
		System.out.println(SuperClass.SuperStaticInnerClass.CUID);
	}
}
