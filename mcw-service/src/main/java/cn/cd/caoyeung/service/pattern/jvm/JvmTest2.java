package cn.cd.caoyeung.service.pattern.jvm;

public class JvmTest2 {
    public static void main(String[] args) {
//        Count count = Count.getInstance();
        System.out.println("count1 = " + Count.count1);
        System.out.println("count2 = " + Count.count2);
        System.out.println("count3 = " + Count.count3);
    }
}
 
class Count {
    // 这个运行结果是 count1 = 1 count2 = 0 ; 因为按顺序执行1. Count(); 2. count1; 3. count2;
	
	static{
    	System.out.println("执行静态代码块");
    }
	
	public static int count1;
	private static Count count = new Count();
    public static int count2 = 0;
    public static int count3;
    // 所以这个运行结果是 count1 = 1 count2 = 1 ;
    // private static Count count = new Count();
    
    private Count() {
//    	System.out.println("init count");
//    	System.out.println("count1:"+count1);
//    	System.out.println("count2:"+count2);
//    	System.out.println("count3:"+count3);
    	System.out.println("执行构造函数");
    	count1++;
        count2++;
        count3++;
//        System.out.println("count1:"+count1);
//    	System.out.println("count2:"+count2);
    }
 
    public static Count getInstance() {
        return count;
    }
}