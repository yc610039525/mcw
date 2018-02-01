package cn.cd.caoyeung.service.pattern.algorithm;


public class OperationCharTest {
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(NumberUtil.formatBinaryString(Integer.SIZE, 
				Integer.toBinaryString(Integer.MAX_VALUE)));
		System.out.println(Integer.MIN_VALUE);
		System.out.println(NumberUtil.formatBinaryString(Integer.SIZE, 
				Integer.toBinaryString(Integer.MIN_VALUE)));
		System.out.println(NumberUtil.formatBinaryString(Integer.SIZE, 
				Integer.toBinaryString(~Integer.MIN_VALUE)));
		
		System.out.println(NumberUtil.formatBinaryString(Integer.SIZE, 
				Integer.toBinaryString(-10)));
//		System.out.println(Base64.encode(""));
		System.out.println(3^6^6);
		
	}
    
}
