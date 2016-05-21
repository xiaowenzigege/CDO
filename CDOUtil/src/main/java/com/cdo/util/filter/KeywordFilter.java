package com.cdo.util.filter;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.log4j.Logger;

/**
 * 
 * @author KenelLiu
 *
 */
public class KeywordFilter {
	private static Pattern pattern = null;
	private static Logger logger=Logger.getLogger(KeywordFilter.class);
	
	private  static void initPattern() {
		StringBuffer patternBuf = new StringBuffer("");
		try {
			if(pattern==null){
				if(logger.isInfoEnabled())
						logger.info("init keyword Filter......");
				InputStream in = KeywordFilter.class.getClassLoader().getResourceAsStream("taboos.txt");
				Properties pro = new Properties();
				pro.load(in);
				Enumeration enu = pro.propertyNames();
				while (enu.hasMoreElements()) {
					patternBuf.append((String) enu.nextElement() + "|");
	
				}
				patternBuf.deleteCharAt(patternBuf.length() - 1);
				pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO-8859-1"), "UTF-8"));
			}			
		} catch (Exception ex) {			
			logger.error(ex.getMessage(), ex);
			pattern=null;
		}
	}

	public static String doFilter(String originString) {
		try {
			initPattern();
			Matcher m = pattern.matcher(originString);
			originString = m.replaceAll("**");//替换字符
		} catch (Exception e) {			
			logger.warn("Keyword doFilter..."+e.getMessage(),e);
		}
		return originString;
	}
}
