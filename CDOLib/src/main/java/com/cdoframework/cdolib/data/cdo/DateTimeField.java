/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/DateTimeField.java,v 1.4 2008/03/12 10:30:58 Frank Exp $
 *
 * $Log: DateTimeField.java,v $
 * Revision 1.4  2008/03/12 10:30:58  Frank
 * *** empty log message ***
 *
 * Revision 1.4  2008/03/11 15:05:55  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/03/10 14:54:14  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/08 12:10:53  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/07 11:20:20  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/11/03 02:25:42  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/10/11 13:55:05  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/11 01:10:57  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;

import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Utility;


/**
 * @author Frank
 */
public class DateTimeField extends ValueFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 885706546812383630L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private String strValue;
	public void setValue(String strValue)
	{
		if(strValue.length()!=19)
		{
			throw new RuntimeException("Invalid date format");
		}
		
		//处理日期
		if(strValue.charAt(4)!='-' && strValue.charAt(7)!='-')
		{
			throw new RuntimeException("Invalid date format");
		}
		String strYear=strValue.substring(0,4);
		String strMonth=strValue.substring(5,7);
		String strDay=strValue.substring(8,10);
		if(Utility.isIntText(strYear)==false || Utility.isIntText(strMonth)==false || Utility.isIntText(strDay)==false)
		{
			throw new RuntimeException("Invalid date format");
		}
		int nMonth=Integer.parseInt(strMonth);
		if(nMonth<1 || nMonth>12)
		{
			throw new RuntimeException("Invalid date format");
		}
		int nDay=Integer.parseInt(strDay);
		if(nDay<1 || nDay>31)
		{
			throw new RuntimeException("Invalid date format");
		}
		if((nMonth==1 || nMonth==3 || nMonth==5 || nMonth==7 || nMonth==8 || nMonth==10 || nMonth==12) && nDay>31)
		{//大月
			throw new RuntimeException("Invalid date format");
		}
		if((nMonth==4 || nMonth==6 || nMonth==9 || nMonth==11) && nDay>30)
		{//小月
			throw new RuntimeException("Invalid date format");
		}
		int nYear=Integer.parseInt(strYear);
		if(Utility.isLeapYear(nYear)==false && nDay>28)
		{
			throw new RuntimeException("Invalid date format");
		}
		if(Utility.isLeapYear(nYear) && nDay>29)
		{
			throw new RuntimeException("Invalid date format");
		}

		//处理时间
		if(strValue.charAt(13)!=':' && strValue.charAt(16)!=':')
		{
			throw new RuntimeException("Invalid date format");
		}
		String strHour=strValue.substring(11,13);
		String strMinute=strValue.substring(14,16);
		String strSecond=strValue.substring(17);
		if(Utility.isIntText(strHour)==false || Utility.isIntText(strMinute)==false || Utility.isIntText(strSecond)==false)
		{
			throw new RuntimeException("Invalid date format");
		}
		int nHour=Integer.parseInt(strHour);
		if(nHour<0 || nHour>23)
		{
			throw new RuntimeException("Invalid date format");
		}
		int nMinute=Integer.parseInt(strMinute);
		if(nMinute<0 || nMinute>59)
		{
			throw new RuntimeException("Invalid date format");
		}
		int nSecond=Integer.parseInt(strSecond);
		if(nSecond<0 || nSecond>59)
		{
			throw new RuntimeException("Invalid date format");
		}
		
		this.strValue=strValue;
	}
	
	public String getValue()
	{
		return this.strValue;
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void toXML(StringBuilder strbXML)
	{
		strbXML.append("<DTF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(this.strValue).append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);
		
		strbXML.append(strIndent).append("<DTF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(this.strValue).append("\"/>\r\n");
	}

	public Object getObjectValue()
	{
		return strValue;
	}

	public ObjectExt getObject()
	{
		return new ObjectExt(this.getType(),strValue);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public DateTimeField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.DATETIME_TYPE);
		
		this.strValue	="";
	}

	public DateTimeField(String strFieldName,String strValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.DATETIME_TYPE);
		
		this.strValue	=strValue;
	}

	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":\"").append(this.strValue).append("\",");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":\\\"").append(this.strValue).append(
						"\\\",");
		return str_JSON.toString();
	}	
}
