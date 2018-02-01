package cn.cd.caoyeung.service.pattern.algorithm;

public class NumberUtil {
	public static String formatBinaryString(int length, String binaryString) {
		StringBuffer sb = new StringBuffer("00000000000000000000000000000000");
		if (binaryString == null) {
			return sb.toString();
		} else {
			if (binaryString.length() == Integer.SIZE) {
				return binaryString;
			} else {
				sb.delete(0, binaryString.length()).append(binaryString);
				return sb.toString();
			}
		}
	}
}
