package com.cdo.util.common;

/**
 * 
 * @author KenelLiu
 *
 */
public class Xml {
	  private static final String PRE_FIX_UTF = "&#x";
	  private static final String POS_FIX_UTF = ";";
	  public static String xmlFormalize(String sTemp){
		    StringBuffer sb = new StringBuffer();
		    if ((sTemp == null) || (sTemp.equals(""))) {
		      return "";
		    }
		    String s = CharsetUtil.encodeGB2312(sTemp);
		    for (int i = 0; i < s.length(); i++) {
		      char cChar = s.charAt(i);
		      if (CharsetUtil.isGB2312(cChar)) {
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
}
