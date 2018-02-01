package cn.cd.caoyeung.webapp.utils.lang;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class StringHelper {
	public static String transTableNameToJavaName(String tableName){
		if(tableName==null)return null;
		tableName = tableName.toLowerCase();
		StringBuffer sb = new StringBuffer();
		int temp = -1;
		for(int i=0;i<tableName.length();i++){
			char c = tableName.charAt(i);
			if(c=='_'){
				temp = i;
				continue;
			}
			if(i==0||i==temp+1){
				sb.append(Character.toUpperCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param str
	 * @return true 是数字,false 不是
	 */
	public static boolean isNumber(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}
	/**
	 * 根据长度前面补0
	 * @param num 序号
	 * @param tagLength 目标长度
	 * @return
	 */
	public static String changeIntToFixedLthStr(Integer num,Integer tagLength){
		StringBuffer sb = new StringBuffer();
		int numLength = String.valueOf(num).length();
		for(int i=tagLength-numLength;i>0;i--){
			sb.append(0);
		}
		sb.append(num);
		return sb.toString();
	}
	/**
	 * 判断是否是16进制数据
	 * @param str
	 * @return true 是数字,false 不是
	 */
	public static boolean is16Number(String str){
		Pattern pattern = Pattern.compile("([A-Fa-f0-9]*)+(\\.)+([A-Fa-f0-9]*)");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}
	public static Map<String,String> XmlToMap(String xml){
		Map<String,String> resMap = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			throw new RuntimeException("请检查xml的数据格式！");
		}
		Element root = doc.getRootElement();
		String rootName = root.getName();
		if(!rootName.equalsIgnoreCase("e"))throw new RuntimeException("请检查根节点名称！");
		List<Element> elements= root.elements();
		for(Element e: elements){
			String eleName = e.getName();
			String eleText = e.getText();
			if(StringUtils.isBlank(eleName))throw new RuntimeException("请检查子节点名称！");
			if(e.elements()!=null&&!e.elements().isEmpty())throw new RuntimeException("子节的内容是基本数据类型！");
			resMap.put(eleName, eleText);
		}
		return resMap;
	}
	public static Map<String,String> JsonToMap(String json){
		Map<String,String> resMap = new HashMap<String, String>();
//		JSONObject jo = null;
		try{
//			jo = JSONObject.fromObject(json);
//			Iterator iter = jo.keys();
//			String key;
//			String value;
//			while (iter.hasNext()){
//				key = String.valueOf(iter.next());
//				value = jo.getString(key);
//				resMap.put(key, value);
//			}
		}catch (Exception e){
			throw new RuntimeException("请检查json格式的数据");
		}
		return resMap;
	}
	
	public static String replaceSideChar(String str) {
		return StringHelper.replaceSideChar(str, null, null);
	}
	
	public static String replaceSideChar(String str, String findStr, String replaceStr) {
		if(StringUtils.isNotBlank(str)) {
			if(StringUtils.isBlank(findStr)) {
				findStr = ",";
			}
			if(StringUtils.isBlank(replaceStr)) {
				replaceStr = "";
			}
			str = str.replaceAll("(^"+findStr+"*)|("+findStr+"*$)", replaceStr);
		}
		return str;
	}
	
	public static Boolean hasFormat(String str,String reg) {
		Matcher m = Pattern.compile(reg).matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}
	
	public static String format(String str, String[] arr) {
		Matcher m = Pattern.compile("\\{(\\d+)\\}").matcher(str);
		while (m.find()) {
			int i  = Integer.parseInt(m.group(1));
			if(i<arr.length){
				str = str.replace(m.group(), arr[i]);
			}
		}
		return str;
	}
	
	public static String format(String prefix,String str, String[] arr) {
		Matcher m = Pattern.compile("\\{("+prefix+"_\\d+)\\}").matcher(str);
		while (m.find()) {
			String to = m.group(1);
			String value = to.replace(prefix+"_", "");
			try {
				int i  = Integer.parseInt(value);
				if(i<arr.length){
					str = str.replace(m.group(), arr[i]);
				}
			} catch (Exception e) {
				throw new RuntimeException(value+"数字转换错误：",e);
			}
		}
		return str;
	}
	
	public static String formatReplace(String prefix,String str,String value) {
		Matcher m = Pattern.compile("\\{("+prefix+"_\\d+)\\}").matcher(str);
		while (m.find()) {
			str = str.replace(m.group(), value);
		}
		return str;
	}
	
	public static String formatReplace(String str,String value) {
		Matcher m = Pattern.compile("\\{(\\d+)\\}").matcher(str);
		while (m.find()) {
			str = str.replace(m.group(), value);
		}
		return str;
	}
	
	
	public static Map<String,String> turnStringToMap(String value, String resultType) {
		if(resultType.equalsIgnoreCase("xml")){
			return XmlToMap(value);
		}else if(resultType.equalsIgnoreCase("json")){
			return JsonToMap(value);
		}
		return new HashMap<String, String>();
	}
	
	public static void main(String[] args){
		String str="fsa{A_0}gfg{B_0}gfg{A_2}44{10}";
		str = StringHelper.format("A",str,new String[]{"1","2"});
	}
}
