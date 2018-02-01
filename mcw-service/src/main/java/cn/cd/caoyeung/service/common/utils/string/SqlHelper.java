package cn.cd.caoyeung.service.common.utils.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SqlHelper {
	public static final String UN_EQUAL = " AND 1 = 2";

	public static String optimizeSQL(String sql, Map<String, Object> param) {
		String regexp = "(?<=\\{).+?(?=\\})";
		StringBuffer sb = new StringBuffer(sql.replaceAll("\\{.+?\\}", ""));
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(sql);
		Set<String> keySet = null;
		while (m.find()) {
			if (keySet == null) {
				if (param != null && !param.isEmpty()) {
					keySet = param.keySet();
				} else {
					throw new IllegalArgumentException("SQL参数不可为空");
				}
			}
			String group = m.group();
			String conString = replaceCondition(param, keySet, group);
			sb.append(" ").append(conString);
			if (SqlHelper.UN_EQUAL.equals(conString)) {
				break;
			}
		}
		return sb.toString();
	}

	public static String replaceCondition(Map<String, Object> param, Set<String> keySet, String condition) {
		String regexp = "(?<=#).+?(?=#)";
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(condition);
		String contionValue = "";
		while (m.find()) {
			contionValue = m.group();
		}
		contionValue = contionValue.trim();
		if (keySet.contains(contionValue)) {
			Object value = param.get(contionValue);
			if (value instanceof Integer) {
				contionValue = condition.replace("#", "").replace(contionValue, Integer.toString((Integer) value));
			} else if (value instanceof String) {
				contionValue = condition.replace("#", "").replace(contionValue, "'" + (String) value + "'");
			} else {
				throw new IllegalArgumentException("未知参数类型");
			}
			return contionValue;
		} else {
			return SqlHelper.UN_EQUAL;
		}
	}

	public static void main(String[] args) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cuid", 1);
		param.put("roleId", "%accvKK**&&&vv");
		String sql = "SELECT * FROM SYS_USER S,ROLE R WHERE S.CUID = R.RELATED_USER_ID " 
		+ "{AND S.CUID = #cuid#} "
		+ "{AND R.CUID LIKE #roleId#} ";
		String optimizeSQL = SqlHelper.optimizeSQL(sql, param);
		System.out.println(optimizeSQL);
	}
}
