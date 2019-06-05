package com.cdo.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.cdoframework.cdolib.base.DataType;

public class CalendarUtil {
	/**
	 * 获取当前日期所属的月份的第一天的日期
	 * @param strDate
	 * @throws ParseException 
	 */
	public static String getFirstDayOfMonth(String strDate) throws ParseException{
	      return getFirstDay(strDate,0,DataType.PATTERN_DATE); 
	}		
	/**
	 * 获取当前日期几个月后的所属月份的第一天日期
	 * @param strDate
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static String getFirstDay(String strDate,int month,String pattern) throws ParseException{
	      SimpleDateFormat format = new SimpleDateFormat(pattern);  	      
	      Calendar cale = Calendar.getInstance();  
	      cale.setTime(format.parse(strDate));  	      
	      cale.add(Calendar.MONTH, month);  
	      cale.set(Calendar.DAY_OF_MONTH, 1);  	      
	      return format.format(cale.getTime()); 
	}	
	/**
	 * 获取当前日期所属的月份的最后一天的日期
	 * @param strDate
	 * @throws ParseException 
	 */
	public static String getLastDayOfMonth(String strDate) throws ParseException{
	      return getLastDay(strDate,0,DataType.PATTERN_DATE);
	}

	/**
	 * 获取当前日期几个月后的所属月份的最后一天日期
	 * @param strDate
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static String getLastDay(String strDate,int month,String pattern) throws ParseException{
	      SimpleDateFormat format = new SimpleDateFormat(pattern);  	      
	      Calendar cale = Calendar.getInstance();  
	      cale.setTime(format.parse(strDate));
	      cale.add(Calendar.MONTH, month+1);  
	      cale.set(Calendar.DAY_OF_MONTH, 0);    	      
	      return format.format(cale.getTime()); 
	}
	/**
	 * 当前日期 +year后的日期
	 * @param strDate 日期
	 * @param year 年
	 * @return
	 * @throws ParseException
	 */
	public static String getAddYear(String strDate,int year) throws ParseException{    	      
	      return getAddYear(strDate,year,DataType.PATTERN_DATE);
	}
	/**
	 * 当前日期 +year后的日期
	 * @param strDate 日期
	 * @param year 年
	 * @return
	 * @throws ParseException
	 */
	public static String getAddYear(String strDate,int year,String pattern) throws ParseException{    	      
	      return getAddDate(strDate,year,0,0,pattern);
	}	
	/**
	 * 当前日期 +month后的日期
	 * @param strDate 日期
	 * @param month 月
	 * @return
	 * @throws ParseException
	 */
	public static String getAddMonth(String strDate,int month) throws ParseException{    	      
	      return getAddMonth(strDate,month,DataType.PATTERN_DATE);
	}
	/**
	 * 当前日期 +month后的日期
	 * @param strDate 日期
	 * @param month 月
	 * @return
	 * @throws ParseException
	 */
	public static String getAddMonth(String strDate,int month,String pattern) throws ParseException{    	      
	      return getAddDate(strDate,0,month,0,pattern);
	}	
	/**
	 * 当前日期 +days后的日期
	 * @param strDate 日期
	 * @param days 天数
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDay(String strDate,int days) throws ParseException{    	      
	      return getAddDay(strDate,days,DataType.PATTERN_DATE);
	}
	/**
	 * 当前日期 +days后的日期
	 * @param strDate 日期
	 * @param days 天数
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDay(String strDate,int days,String pattern) throws ParseException{    	      
	      return getAddDate(strDate,0,0,days,pattern);
	}	
	
	/**
	 * 当前日期 +hours后的日期
	 * @param strDate 日期
	 * @param hours 小时
	 * @return
	 * @throws ParseException
	 */
	public static String getAddHours(String strDate,int hours,String pattern) throws ParseException{    	      
	      return getAddDate(strDate,0,0,0,hours,0,0,pattern);
	}	
	
	/**
	 * 当前日期 +minitues后的日期
	 * @param strDate 日期
	 * @param minitues 分钟
	 * @return
	 * @throws ParseException
	 */
	public static String getAddMinitues(String strDate,int minitues,String pattern) throws ParseException{    	      
	      return getAddDate(strDate,0,0,0,0,minitues,pattern);
	}
	/**
	 * 当前日期 +minitues后的日期
	 * @param strDate 日期
	 * @param minitues 分钟
	 * @return
	 * @throws ParseException
	 */
	public static String getAddSeconds(String strDate,int seconds,String pattern) throws ParseException{    	      
	      return getAddDate(strDate,0,0,0,0,0,seconds,pattern);
	}
	
	/**
	 * 当前日期 +year+month+days后的日期
	 * @param strDate
	 * @param year
	 * @param month
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(String strDate,int year,int month,int days,String pattern) throws ParseException{    	      
	      return getAddDate(strDate,year,month,days,0,0,0,pattern);
	}
	/**
	 * 当前日期 +year+month+days+hours后的日期
	 * @param strDate
	 * @param year
	 * @param month
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(String strDate,int year,int month,int days,int hours,String pattern) throws ParseException{	   	     
	      return getAddDate(strDate,year,month,days,hours,0,0,pattern);
	}
	/**
	 * 当前日期 +year+month+days+hours+minitues后的日期
	 * @param strDate
	 * @param year
	 * @param month
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(String strDate,int year,int month,int days,int hours,int minitues,String pattern) throws ParseException{
		 return getAddDate(strDate,year,month,days,hours,minitues,0,pattern);
	}
	/**
	 * 当前日期 +year+month+days+hours+minitues+seconds后的日期
	 * @param strDate
	 * @param year
	 * @param month
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(String strDate,int year,int month,int days,int hours,int minitues,int seconds,String pattern) throws ParseException{
	      SimpleDateFormat format = new SimpleDateFormat(pattern);  	      
	      Calendar cale = Calendar.getInstance();  
	      cale.setTime(format.parse(strDate));
	      cale.add(Calendar.YEAR, year);
	      cale.add(Calendar.MONTH, month);  
	      cale.add(Calendar.DAY_OF_MONTH, days); 
	      cale.add(Calendar.HOUR_OF_DAY, hours);
	      cale.add(Calendar.MINUTE, minitues);
	      cale.add(Calendar.SECOND, seconds);
	      return format.format(cale.getTime()); 
	}
	
}
