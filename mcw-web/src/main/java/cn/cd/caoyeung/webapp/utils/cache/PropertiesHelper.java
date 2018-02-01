package cn.cd.caoyeung.webapp.utils.cache;

import java.util.ResourceBundle;
/**
 * 
 * @author Admin
 *
 */
public class PropertiesHelper {
	private static  final ResourceBundle bundle = ResourceBundle.getBundle("com.imooc.tool.config");
	/**
	 * 根据Key获取Properties配置文件
	 * @param K
	 * @return
	 */
	public static String readPropertiesValue(String K) {
		String value = "";
		value = bundle.getString(K);
		return value;
	}
	public static void main(String[] args) {
		String url = PropertiesHelper.readPropertiesValue("url");
		System.out.println(url);
	}
}
