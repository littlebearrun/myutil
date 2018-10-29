package com.likang.myutil.util.tools;
/**  
* @author likang  2018年10月23日 下午11:01:33
*/

public class SomeStringUtil {

    /**
     * 	首字母转换小写
     * @param str
     * @return
     */
	public static String firstCharToLowerCase(String str) {
		if(null == str || Character.isLowerCase(str.charAt(0))) {
			return str;
		}
		
		char[] c = str.toCharArray();
		c[0] += 32;
		return String.valueOf(c);
		
	}
	
	public static void main(String[] args) {
		System.err.println(firstCharToLowerCase("ANV"));
	}
	
}


