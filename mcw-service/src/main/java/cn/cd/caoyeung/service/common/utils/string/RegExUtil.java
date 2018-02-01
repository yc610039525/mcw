package cn.cd.caoyeung.service.common.utils.string;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 这是个正则表达式应用类，用来匹配和替换字串用的
 * @author 
 * @version 
 */

public class RegExUtil {

  /**
   * 要求大小写都匹配正则表达式
   * @param pattern 正则表达式模式
   * @param str 要匹配的字串
   * @return boolean值
   * @since  1.0
   */
  public static final boolean ereg(String pattern, String str)  throws PatternSyntaxException
  {
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      return m.find();
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

  /**
   * 匹配且替换字串
   * @param pattern 正则表达式模式
   * @param newstr 要替换匹配到的新字串
   * @param str 原始字串
   * @return 匹配后的字符串
   * @since  1.0
   */

  public static final String ereg_replace(String pattern, String newstr, String str)  throws PatternSyntaxException
  {
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      return m.replaceAll(newstr);
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

  /**
   * 主要用于模板中模块标记分析函数 把查找到的元素加到vector中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return vector
   * @since  1.0
   */
  public static final Vector<String> splitTags2Vector(String pattern, String str) throws PatternSyntaxException
  {
    Vector<String> vector = new Vector<String>();
    try {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      while (m.find())
      {
        vector.add(ereg_replace("(\\[\\#)|(\\#\\])", "", m.group()));
      }
      return vector;
    }
    catch (PatternSyntaxException e) {
      throw e;
    }
  }
  /**
   * 模块标记分析函数
   * 功能主要是把查找到的元素加到vector中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @since  1.0
   */
  public static final String[] splitTags(String pattern, String str)
  {
    try {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      String[] array = new String[m.groupCount()];
      int i = 0;
      while (m.find())
      {
        array[i] = ereg_replace("(\\[\\#)|(\\#\\])", "", m.group());
        i++;
      }
      return array;
    }
    catch (PatternSyntaxException e) {
      throw e;
    }
  }


  /**
   * 匹配所有符合模式要求的字串并加到矢量vector数组中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return vector
   * @since  1.0
   */
  public static final Vector<String> regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException
  {
    Vector<String> vector = new Vector<String>();
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      while (m.find())
      {
        vector.add(m.group());
      }
      return vector;
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

  /**
   * 匹配所有符合模式要求的字串并加到字符串数组中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return array
   * @since  1.0
   */
  public static final String[] regMatchAll2Array(String pattern, String str) throws PatternSyntaxException
  {
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      String[] array = new String[m.groupCount()];
      int i = 0;
      while (m.find())
      {
        array[i] = m.group();
        i++;
      }
      return array;
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }
  /**
   * 转义正则表达式字符(之所以需要将\和$字符用escapeDollarBackslash方法的方式是因为用repalceAll是不行的，简单的试试"$".repalceAll("\\$","\\\\$")你会发现这个调用会导致数组越界错误)
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return array
   * @since  1.0
   */
  public static String escapeDollarBackslash(String original) {
      StringBuffer buffer=new StringBuffer(original.length());
      for (int i=0;i<original.length();i++) {
        char c=original.charAt(i);
        if (c=='\\'||c=='$') {
          buffer.append("\\").append(c);
        } else{
          buffer.append(c);
        }
      }
      return buffer.toString();
    }

    /**
     * 提取指定字串的函数
     * 功能主要是把查找到的元素
     * @param pattern为正则表达式模式
     * @param str 原始字串
     * @since  1.0
     */
    public static final String fetchStr(String pattern, String str) {
        String returnValue = null;
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                returnValue = m.group();
            }
            return returnValue;
        } catch (PatternSyntaxException e) {
            return returnValue;
        }
  }
    public static void main(String[] args) {
		// 按指定模式在字符串查找
		String line = "ThisA 3000 **#@ ThisB 3000 %%@";
		String pattern = "((\\D*)+?(\\d+)+?(\\W*)+?)+?";//非贪婪匹配
		/*	
	    Found value 0-: ThisA 3000 **#@ //非贪婪匹配
		Found value 1-: ThisA 3000 **#@ 
		Found value 2-: ThisA 
		Found value 3-: 3000
		Found value 4-:  **#@ 
		*/
		
		
//		String pattern = "((\\D*)+?(\\d+)+?(\\W*)+?)+";//贪婪匹配
		
		/*
		Found value 0-: ThisA 3000 **#@ ThisB 3000 %%@ //贪婪匹配
		Found value 1-: ThisB 3000 %%@
		Found value 2-: ThisB 
		Found value 3-: 3000
		Found value 4-:  %%@
		*/
		
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		
		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		System.out.println("groupCount:"+m.groupCount());
		if (m.find()) {//下标开始搜索 m.find(2)
			System.out.println("Found value: 0-" + m.group(0));
			System.out.println("Found value: 1-" + m.group(1));
			System.out.println("Found value: 2-" + m.group(2));
			System.out.println("Found value: 3-" + m.group(3));
			System.out.println("Found value: 4-" + m.group(4));
			System.out.println("start:"+m.start());
			System.out.println("end:"+m.end());
			System.out.println("start-2:"+m.start(2));
			System.out.println("end-2:"+m.end(2));
		}
		m.reset();
		while(m.find()){
			System.out.println("match value: " + m.group());
			
			/*
			 * 非贪婪匹配
			 * match value: ThisA 3000 **#@ 
			 * match value: ThisB 3000 %%@
			 */
			
			/*
			 * 贪婪匹配
			 * match value: ThisA 3000 **#@ ThisB 3000 %%@
			 */
			
		}
		String regxp0 = "[\u4e00-\u9fa5]";
		Pattern p = r.compile(regxp0);
		Matcher matcher = p.matcher("1212kkKkkkk开口12");
//		while(matcher.find()){
//			System.out.println("matcher V:"+matcher.group());
//		}
		String regxp1 = "[0-9]+[a-zA-Z]";
		p =  r.compile(regxp1);
		matcher = p.matcher("1212kkKkkkk开口121212kkKkkkk开口12");
		while(matcher.find()){
			System.out.println("matcher V:"+matcher.group());
		}
	}
}
