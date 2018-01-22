package com.likang.myutil.util.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 *@Author likang  2017年10月16日 下午2:47:22
 *日期操作类
 */
public class DateUtil {

	public static final String PATTERN_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_2 = "yyyy-MM-dd";
	public static final String PATTERN_3 = "yyyyMMdd";
	public static final String PATTERN_4 = "yyyyMMddHHmmss";
	public static final String PATTERN_5 = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		String strDateTime = null;
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		strDateTime = date == null ? null : formater.format(date);
		return strDateTime;
	}
	
	/**
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String str, String pattern) {
		Date dateTime = null;
		try {
			if (str != null && !str.trim().equals("")) {
			    SimpleDateFormat formater = new SimpleDateFormat(pattern);
			    dateTime = formater.parse(str);
			}
		} catch (Exception ex) {
			System.err.println("日期格式化失败:" + str + "\t" + pattern);
		}
		return dateTime;
	}
	
	
	 /**
	  * 功能: 根据传入的年月日返回相应的日期对象
	  * 
	  * @param year 年份
	  * @param month 月份
	  * @param day 天
	  * @param elseParam  时,分,秒
	  * @return Date 日期对象
	  */
	 public static Date ymdToDate(int year, int month, int day, int... elseParam) {
		  Calendar calendar = Calendar.getInstance();
		  
		  int hour = 0;
		  int minute = 0;
		  int second = 0;	
		  if(elseParam.length >= 1){
			  hour = elseParam[0];
		  }
		  if(elseParam.length > 1){
			  minute = elseParam[1];
		  }
		  if(elseParam.length > 2){
			  second = elseParam[2];
		  }
		  calendar.set(year, month - 1, day, hour, minute, second);
		  return calendar.getTime();
	 }
	
	 
	/**
	 * 获取date的最大值xxx  23:59:59
	 * @param date
	 * @return
	 */
	public static Date getMaxDateOfDay(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(11, calendar.getActualMaximum(11));
			calendar.set(12, calendar.getActualMaximum(12));
			calendar.set(13, calendar.getActualMaximum(13));
			calendar.set(14, calendar.getActualMaximum(14));
			return calendar.getTime();
		}
	}
	 
	/**
	 * 获取date的零点时间
	 * @param date
	 * @return
	 */
	public static Date getMinDateOfDay(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(11, calendar.getActualMinimum(11));
			calendar.set(12, calendar.getActualMinimum(12));
			calendar.set(13, calendar.getActualMinimum(13));
			calendar.set(14, calendar.getActualMinimum(14));
			return calendar.getTime();
		  }
	}
	 
	 /**
	  * 功能：返回传入日期对象（date）之后afterDays天数的日期对象
	  * 
	  * @param date 日期对象
	  * @param afterDays 往后天数     负值是往前的天数
	  * @return java.util.Date 返回值
	  */
	public static Date getAfterDay(Date date, int afterDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, afterDays);
		return cal.getTime();
	}	 
	 
	 /**
	  * 功能: 返回date2-date1相差的天数
	  * 
	  * @param date1
	  * @param date2
	  * @return int
	  */
	public static int dateDiff(Date date1, Date date2) {
		int i = (int) ((date2.getTime() - date1.getTime()) / 3600 / 24 / 1000);
		return i;
	}
	
	 /**
	  * 功能: 返回date2-date1相差的分钟数
	  * 
	  * @param date1
	  * @param date2
	  * @return int
	  */
	public static int minDiff(Date date1, Date date2) {
		int i = (int) ((date2.getTime() - date1.getTime()) / 1000 / 60);
		return i;
	}
	 /**
	  * 功能: 返回date2-date1相差的秒数
	  * 
	  * @param date1
	  * @param date2
	  * @return int
	  */
	public static int secondDiff(Date date1, Date date2) {
		int i = (int) ((date2.getTime() - date1.getTime()));
		return i;
	}
	 
	 
	 public static void main(String[] args) {
//		Date d = YmdToDate(2017, 3, 1,1,2,3);
		System.err.println(dateToString(getAfterDay(new Date(),-2), PATTERN_1));
//		System.err.println(dateDiff(ymdToDate(2017, 3, 1,1,2,3), ymdToDate(2017, 3, 2,1,2,2)));
		
		
		
		
		
//		Calendar now = Calendar.getInstance();  
//        System.out.println("年: " + now.get(Calendar.YEAR));  
//        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
//        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));  
//        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));  
//        System.out.println("分: " + now.get(Calendar.MINUTE));  
//        System.out.println("秒: " + now.get(Calendar.SECOND));  
//        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());  
//        System.out.println(now.getTime());  
	}
	
}
