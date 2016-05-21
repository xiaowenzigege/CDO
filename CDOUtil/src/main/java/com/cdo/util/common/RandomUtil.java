package com.cdo.util.common;

import java.util.Random;

/**
 * 
 * @author KenelLiu
 *
 */
public class RandomUtil {

	/**
	 * 
	 * @param length
	 * @return 返回一个指定长度(length)的 十六进制随机 字符串
	 */
	public static String getHexRandom(int length){	 
	     StringBuffer buffer=new StringBuffer("0123456789abcdef");
	     return getRandom(buffer, length);
	}
	/**
	 * 
	 * @param length
	 * @return  返回一个指定长度(length)的数字 随机字符串
	 */
	public static String getDigitalRandom(int length){		 
	     StringBuffer buffer=new StringBuffer("0123456789");
	     return getRandom(buffer, length);
	}	
	/**
	 * 
	 * @param length
	 * @return  返回一个指定长度(length)的字符 随机字符串
	 */
	public static String getAlphabetRandom(int length){		 
	    StringBuffer buffer=new StringBuffer("abcdefghijklmnopqrstuvwxyz");
	   return getRandom(buffer, length);
	}	
	
	/**
	 * 
	 * @param length
	 * @return 返回一个指定长度(length)的数字和字符 随机字符串
	 */
	public static String getAlphabetMixDigitalRandom(int length){		 
	   StringBuffer buffer=new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
	   return getRandom(buffer, length);
	}		
	
	private static String getRandom(StringBuffer buffer,int length){
	     StringBuffer sb=new StringBuffer();
	     Random r=new Random();
	     int range=buffer.length();
	     for(int i=0;i<length;i++){
	         sb.append(buffer.charAt(r.nextInt(range)));
	    }
	   return sb.toString();
	}	
}
