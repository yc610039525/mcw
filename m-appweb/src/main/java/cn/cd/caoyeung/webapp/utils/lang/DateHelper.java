package cn.cd.caoyeung.webapp.utils.lang;

import java.util.Date;

public class DateHelper {

	/**
	 * 求时间差
	 * @param date1 减数
	 * @param date2 被减数
	 * @return（秒）
	 */
	public static long differ(Date date1, Date date2) { 
	    //return date1.getTime() / (24*60*60*1000) - date2.getTime() / (24*60*60*1000); 
	    return date2.getTime() / 86400000 - date1.getTime() / 86400000;  //用立即数，减少乘法计算的开销
	} 
}
