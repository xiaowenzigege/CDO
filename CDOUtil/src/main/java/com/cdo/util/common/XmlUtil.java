package com.cdo.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author KenelLiu
 *
 */
public class XmlUtil {
	  private static final String PRE_FIX_UTF = "&#x";
	  private static final String POS_FIX_UTF = ";";
	  public static String encode(String strTmp){		  
		    if ((strTmp == null) || (strTmp.equals(""))) {
		      return "";
		    }
		    String s = CharsetUtil.encodeGBK(strTmp);
		    if(s==null)
		    	return "";
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < s.length(); i++) {
		      char cChar = s.charAt(i);
		      if (CharsetUtil.isGBK(cChar)) {
		        sb.append(PRE_FIX_UTF);
		        sb.append(Integer.toHexString(cChar));
		        sb.append(POS_FIX_UTF);
		      }
		      else {
		        switch (cChar) {
		        case ' ':
		          sb.append(" ");
		          break;
		        case '"':
		          sb.append("&quot;");
		          break;
		        case '&':
		          sb.append("&amp;");
		          break;
		        case '<':
		          sb.append("&lt;");
		          break;
		        case '>':
		          sb.append("&gt;");
		          break;
		        default:
		          sb.append(cChar);
		        }
		      }
		    }
		    return sb.toString();
		  }
	  
	  public static String decode(String strTmp){
//		        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
			    Pattern pattern = Pattern.compile("(&#x(\\p{XDigit}{4});)");
		        Matcher matcher = pattern.matcher(strTmp);
		        char ch;
		        while (matcher.find()) {
		            ch = (char) Integer.parseInt(matcher.group(2), 16);
		            strTmp = strTmp.replace(matcher.group(1), ch+"" );
		        }
		        return strTmp;
	  }
	  
}
