package cn.cd.caoyeung.service.common.utils.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassUtil {

	/**
	 * 获取到的参数 返回值 是Class
	 * 
	 * @param obj
	 */
	public static void printClassMethodMessage(Object obj) {
		Class<? extends Object> c = obj.getClass();
		Method[] ms = c.getMethods();
		for (int i = 0; i < ms.length; i++) {
			Class<?> returnType = ms[i].getReturnType();
			System.out.print(returnType.getName() + " ");
			System.out.print(ms[i].getName() + "(");
			Class[] paramTypes = ms[i].getParameterTypes();
			for (Class<?> class1 : paramTypes) {
				System.out.print(class1.getName() + ",");
			}
			System.out.println(")");
		}
	}

	/**
	 * 获取属性字段
	 * 
	 * @param obj
	 */
	public static void printFieldMessage(Object obj) {
		Class<? extends Object> c = obj.getClass();
		// Field[] fs = c.getFields();
		Field[] fs = c.getDeclaredFields();
		for (Field field : fs) {
			Class<?> fieldType = field.getType();
			String typeName = fieldType.getName();

			String fieldName = field.getName();
			System.out.println(typeName + " " + fieldName);
		}
	}

	//
	public static void printConMessage(Object obj) {
		Class<? extends Object> c = obj.getClass();
		// Constructor[] cs = c.getConstructors();
		Constructor[] cs = c.getDeclaredConstructors();
		for (Constructor<?> constructor : cs) {
			System.out.print(constructor.getName() + "(");
			Class[] paramTypes = constructor.getParameterTypes();
			for (Class<?> class1 : paramTypes) {
				System.out.print(class1.getName() + ",");
			}
			System.out.println(")");
		}
	}

	public static Class<?> getClass(String wholeClassName) {
		Class<?> cls = null;
		try {
			cls = Class.forName(wholeClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cls;
	}

	public static Constructor<?>[] getConstructors(Class<?> cls) {
		Constructor<?>[] constructors = cls.getConstructors();
		return constructors;
	}

	public static <T> T getObject(Class<T> cls) {
		T newInstance = null;
		try {
			newInstance = cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return newInstance;
	}
	/**
	 * 访问对象私有方法
	 * @param obj
	 * @param cls
	 * @param args
	 * @param mdName
	 * @return
	 */
	public static Object invokePrivateMd(Object obj,Class[] cls,Object[] args,String mdName){
		Class<? extends Object> c = obj.getClass();
		Object result =null;
		try {
			Method md = c.getDeclaredMethod(mdName, cls);
			md.setAccessible(true);
			result = md.invoke(obj, args);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public static Object getFieldValueByName(Object obj, String propertyName) {
		Class<? extends Object> c = obj.getClass();
		Method[] declaredMethods = c.getDeclaredMethods(); 
		Method md = null;
		if(Array.getLength(declaredMethods)>0&&propertyName!=null){
			for(int i=0;i<declaredMethods.length;i++){
				if((declaredMethods[i].getName().toUpperCase()).equals("GET"+propertyName.toUpperCase())){
					md = declaredMethods[i];
				}
			}
		}
		if(md==null){
			throw new RuntimeException("can't find propertyName with it's get method");
		}
		try {
			md.setAccessible(true);
			Object value = md.invoke(obj);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 泛型说明 <?> 该方法执行不知道传入的具体类型是什么
	 * 
	 * @param list
	 */
	public static void scanList(List<?> list) {
		List l = new ArrayList();
//		Object object = list.get(0);
		l.addAll(list);
	}

	/**
	 * 泛型说明 <? extends Number>
	 * 
	 * @param list
	 */
	public static void scanList2(List<? extends Number> list) {

	}

	/**
	 * 泛型说明 <? super Integer>
	 * 
	 * @param list
	 */
	public static void scanList3(List<? super Integer> list) {

	}
	

	public static void main(String[] args) throws Exception {
		Class<Integer> intclas = Integer.class;
		Class<Boo> cls = (Class<Boo>) ClassUtil.getClass("com.team.cd.common.utils.bean.Boo");
		Boo boo1 = ClassUtil.getObject(cls);
		boo1.sayGoodBye();
		ClassUtil.getConstructors(cls);
		Constructor<?> constructor = cls.getConstructor(String.class, String.class);
		Boo boo2 = (Boo) constructor.newInstance("李伟", "成都");
		boo2.sayGoodBye();
//		boo2.sayHi("K"); 私有方法访问报错
		Object result = ClassUtil.invokePrivateMd(boo2, new Class[]{String.class}, new Object[]{"Hello Gril"}, "sayHi");
		System.out.println("ClassUtil.invokePrivateMd:"+result);
		
		Object fieldValueByName = ClassUtil.getFieldValueByName(boo2, "address");
		System.out.println("fieldValueByName:"+fieldValueByName);
		System.out.println("getSuperclass:"+cls.getSuperclass().getName());
		System.out.println("isAnnotation:"+cls.isAnnotation());
		System.out.println("isArray:"+cls.isArray());
		Method sayGoodBye = cls.getDeclaredMethod("sayGoodBye");
		Annotation[] sayGoodByeAnnotions = sayGoodBye.getAnnotations();
		Deprecated annotation = sayGoodBye.getAnnotation(Deprecated.class);
		Class<? extends Annotation> annotationType = sayGoodByeAnnotions[0].annotationType();
		System.out.println("annotationType:"+annotationType.getName());
		
//		InputStream resourceAsStream = cls.getClassLoader().getResourceAsStream("");		
//		InputStream resourceAsStream2 = ClassLoader.getSystemClassLoader().getResourceAsStream("");
//		泛型测试
		ClassUtil.scanList(new ArrayList<Integer>());
		ClassUtil.scanList(new ArrayList<String>());
		ClassUtil.scanList2(new ArrayList<Integer>());
		ClassUtil.scanList2(new ArrayList<Long>());
		// ClassUtil.scanList2(new ArrayList<String>());
		ClassUtil.scanList3(new ArrayList<Integer>());
		ClassUtil.scanList3(new ArrayList<Number>());
		// ClassUtil.scanList3(new ArrayList<Long>());
		Runtime runtime = Runtime.getRuntime();
		long l = runtime.freeMemory();
		System.out.println("freeMemory:"+(l/1024/1024));
	}
}
