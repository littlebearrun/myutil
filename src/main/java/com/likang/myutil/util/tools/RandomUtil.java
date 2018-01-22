package com.likang.myutil.util.tools;
import java.util.Random;

/**
 * 
 * @author LiKang
 * 随机字符/数字
 */
public class RandomUtil {
	private static final String ALLCHAR = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	private static final String NUM_CHAR = "0123456789";  
	
	public static final int NUM_CHAR_CODE = 1;
//	public static final int STRING_CHAR_CODE = 0;
	
	/**
	 * 获取两个数间随即值[begin,end)
	 * @param begin
	 * @param end
	 * @return
	 */
	public static Integer randmonRangeValue(Integer begin, Integer end) {
		Random rand = new Random();
		int offset = end - begin;
		return rand.nextInt(offset) + begin;
	}
	
	/**
	 * 返回指定长度的大小写数字混合字符串
	 * @param length
	 * @return
	 */
	public static String randomString(Integer length,int pos){
		String charString = ALLCHAR;
		if(pos == NUM_CHAR_CODE){
			charString = NUM_CHAR;
		}
		StringBuffer sb = new StringBuffer();  
	    Random random = new Random();  
	    for (int i = 0; i < length; i++) {  
	        sb.append(charString.charAt(random.nextInt(charString.length())));  
	    }
	    
	    return sb.toString();
	}
}
