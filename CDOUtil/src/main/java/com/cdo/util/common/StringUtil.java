package com.cdo.util.common;

import com.cdoframework.cdolib.util.Function;

/**
 * 
 * @author KenelLiu
 *
 */
public class StringUtil{
  
  public static boolean isNotEmpty(CharSequence charSeq){
    return !isEmpty(charSeq);
  }
  
  public static boolean isEmpty(CharSequence charSeq){
	    return (charSeq == null) || (charSeq.length() == 0);
  }
  
  public static boolean isNotBlank(CharSequence cs){
    return !isBlank(cs);
  } 
  
  public static boolean isBlank(CharSequence cs)
  {
    int strLen;
    if ((cs == null) || ((strLen = cs.length()) == 0))
      return true;

    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isNumber(String str){
    return (str != null) && (str.trim().length() != 0) && (str.matches("\\d*"));
  }

  public static String join(Object... args) {
	StringBuilder builder = new StringBuilder();
	for(Object item : args) {
		builder.append(item);
	}
	return builder.toString();
} 


  
 public static boolean isMail(String strEmail){
	 	if(strEmail.length()<=5)
	 		return false;
	 	String tmpEmail=strEmail.substring(1,strEmail.length()-1);	 	
		if(!tmpEmail.contains("@")){			
			return false;
		}else if(!tmpEmail.contains(".")){
			return false;
		}
		return true;
	}

  public static String htmlEncode(String str){    
	  return Function.FormatTextForHTML(str);
  } 
  
	/**
	 * 当strSeq长度不够length时，前面补prefixChar字符
	 * @param strSeq
	 * @param length
	 * @return
	 */
	public static String fillPrefixLength(String strSeq,CharSequence prefixChar,int length){
		 int  len=length-strSeq.length();
		 String prefix="";
		 for(int i=0;i<len;i++){
			 prefix+=prefixChar;
		 }
		 prefix+=strSeq;
		 return prefix;		
	}
	/**
	 * 当strSeq长度不够length时，后面补suffixChar字符
	 * @param strSeq
	 * @param suffixChar
	 * @param length
	 * @return
	 */
	public static String fillSuffixLength(String strSeq,CharSequence suffixChar,int length){
			 int  len=length-strSeq.length();
			 String suffix="";
			 for(int i=0;i<len;i++){
				 suffix+=suffixChar;
			 }
			 strSeq+=suffix;
			 return strSeq;		
	}	
	
	
}