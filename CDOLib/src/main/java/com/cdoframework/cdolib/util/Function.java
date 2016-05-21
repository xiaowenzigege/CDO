/**
 * www.cdoforum.com 2007版权所有
 * 
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOWeb/Source/com/cdoframework/cdolib/web/util/Function.java,v 1.1
 * 2008/03/05 01:35:47 Frank Exp $
 * 
 * $Log: Function.java,v $ Revision 1.1 2008/03/05 01:35:47 Frank *** empty log message ***
 * 
 * Revision 1.1 2007/12/25 14:11:29 Frank *** empty log message ***
 * 
 * Revision 1.1 2007/11/13 03:22:57 Frank *** empty log message ***
 * 
 * Revision 1.1 2007/11/13 03:11:34 Frank *** empty log message ***
 * 
 * Revision 1.4 2007/09/30 00:10:22 Frank *** empty log message ***
 * 
 * Revision 1.3 2007/08/30 06:46:47 Frank *** empty log message ***
 * 
 * Revision 1.1 2007/08/29 08:49:15 Frank *** empty log message ***
 * 
 * Revision 1.1 2007/08/08 09:32:20 Frank *** empty log message ***
 * 
 * Revision 1.1 2006/08/04 14:08:07 Frank Init Project
 * 
 * Revision 1.1 2006/07/10 22:38:37 Frank Init
 * 
 * Revision 1.4 2005/09/16 03:16:18 frank *** empty log message ***
 * 
 * Revision 1.3 2005/09/16 02:32:38 frank *** empty log message ***
 * 
 * Revision 1.2 2005/09/16 02:11:14 frank *** empty log message ***
 * 
 * Revision 1.1 2005/09/16 02:05:58 frank *** empty log message ***
 * 
 * 
 */

package com.cdoframework.cdolib.util;

public class Function
{	
	/**
	 * 将文本内容格式化以符合HTML显示要求
	 */
	public static String FormatTextForMongodb(String strText)
	{
		if(strText==null)
		{
			return null;
		}
		StringBuilder strbText=new StringBuilder(strText.length());
		int nLength=strText.length();
		for(int i=0;i<nLength;i++)
		{
			char ch=strText.charAt(i);
			switch(ch)
			{
			case '[':
				strbText.append("\\[");
				break;
			case ']':
				strbText.append("\\]");
				break;
			case '(':
				strbText.append("\\(");
				break;
			case ')':
				strbText.append("\\)");
				break;	
			case '{':
				strbText.append("\\{");
				break;
			case '}':
				strbText.append("\\}");
				break;	
			case '\\':
				strbText.append("\\\\");
				break;
			case '+':
				strbText.append("\\+");
				break;
			case '*':
				strbText.append("\\*");
				break;
			case '?':
				strbText.append("\\?");
				break;
			case '.':
				strbText.append("\\.");
				break;
			default:
				strbText.append(ch);
				break;
			}
		}
		return strbText.toString();
	}		
	public static String FormatTextForXML(String strText)
	{
		if(strText==null)
		{
			return null;
		}
		StringBuilder strbText=new StringBuilder(strText.length());
		int nLength=strText.length();
		for(int i=0;i<nLength;i++)
		{
			char ch=strText.charAt(i);
			switch(ch)
			{
			case '&':
				strbText.append("&amp;");
				break;
			case '>':
				strbText.append("&gt;");
				break;
			case '<':
				strbText.append("&lt;");
				break;
			case '\'':
				strbText.append("&apos;");
				break;
			case '\"':
				strbText.append("&quot;");
				break;
			case '\r':
				strbText.append("");
				break;
			case '\n':
				strbText.append("&#xa;");
				break;
			default:
				strbText.append(ch);
				break;
			}
		}

		return strbText.toString();
	}


	
	public static String FormatTextForJson(String strText)
	{
		if(strText==null)
		{
			return null;
		}
		strText=strText.replace("\\","\\\\");
		strText=strText.replaceAll("\"","\\\\\"");
		return strText;
	}	

}
