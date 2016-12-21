package com.cdo.util.common;

/**
 * 
 * @author KenelLiu
 *
 */
public class NumberUtil {
	public static String[] NUMBER = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "百", "千", "万", "亿" };

	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){		  
		   if (!Character.isDigit(str.charAt(i))){
			   return false;
		   	}
		  }
		  return true;
	}
}