package com.qk.trains.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: trains
 * @description: 格式工具类
 * @author: Xiaotian
 * @create: 2018-08-28 17:20
 **/
public class Format {
	public static Date getDateIn12Char(String date) throws NumberFormatException {
		if (date.length() == 12) {
			int year = Integer.valueOf(date.substring(0, 2)) + 2000;
			int month = Integer.valueOf(date.substring(2, 4)) - 1;
			int day = Integer.valueOf(date.substring(4, 6));
			int hour = Integer.valueOf(date.substring(6, 8));
			int minute = Integer.valueOf(date.substring(8, 10));
			int second = Integer.valueOf(date.substring(10, 12));
			Calendar calendar = Calendar.getInstance();
			//设置一个临时值使calendar内部毫秒数归零
			calendar.setTimeInMillis(0);
			calendar.set(year, month, day, hour, minute, second);
			return calendar.getTime();
		}
		throw new NumberFormatException("Not 12 char date format");
	}

	public static Date getDateIn12Char(char[] date) throws NumberFormatException {
		return getDateIn12Char(new String(date));
	}

	public static String date(String pattern, Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String date(String pattern) {
		return date(pattern, new Date(SystemClock.now()));
	}
	
	 public static String dateFormat(Date date,String format) {  
		        if(date==null) {
		        	
		        	return "";
		        }
		 
		         if(format == null || format.isEmpty()){
		            format = "yyyy-MM-dd HH:mm:ss";
		         }   
		          SimpleDateFormat sdf = new SimpleDateFormat(format);  
		       return sdf.format(date);  
		     }  
	
}
