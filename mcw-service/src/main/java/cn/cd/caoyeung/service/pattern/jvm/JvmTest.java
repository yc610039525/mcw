package cn.cd.caoyeung.service.pattern.jvm;

import java.util.Random;
/**
 * @link  http://www.cnblogs.com/qq610039525/articles/8003827.html
 * @author yc
 */
public class JvmTest {
    public static void main(String[] args) {
//        System.out.println(FinalTest1.x);
        System.out.println(FinalTest2.x);
    }
}
/**
 * 编译时常量的处理
 * @author Administrator
 *
 */
class FinalTest1 {
//    public static final int x = 6 / 3;  //x是一个编译时的常量，编译的时候就知道值是多少，不需要对类进行初始化
    public static  int x = 6 / 3;  // 如果 final 去除掉以后，就会执行“FinalTest staic block!”了
 
    static {
        System.out.println("FinalTest staic block!");
    }
}
 
class FinalTest2 {
	// 运行才知道的就需要对类进行初始化，对类进行初始化static代码快就会执行,x非编译时的常量，x在编译时不知道是多少
    public static final int x = new Random().nextInt(100);
 
    static {
        System.out.println("FinalTest2 staic block!");
    }
}