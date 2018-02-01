package cn.cd.caoyeung.webapp.bean;


public class SysConfigHelper {
	//xml中配置必须使用set方法配合 使用注解不必使用set方法
	private static String PROP;
	public static String getPROP() {
		return PROP;
	}
	public static void setPROP(String pROP) {
		PROP = pROP;
	}
	public String getEnv(){
		return PROP;
	}
}
