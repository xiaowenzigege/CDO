/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/DateArrayField.java,v 1.4 2008/03/12 10:30:57 Frank Exp $
 *
 * $Log: DateArrayField.java,v $
 * Revision 1.4  2008/03/12 10:30:57  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/03/10 14:54:16  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/08 12:10:54  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/07 11:20:19  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/11/03 02:25:42  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/10/12 02:36:16  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/11 13:41:24  Frank
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
public class DateArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -6305770982050527951L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private String[] strsValue;
	public void setValue(String[] strsValue)
	{
		if(strsValue==null)
		{
			strsValue=new String[0];
		}
		for(int i=0;i<strsValue.length;i++)
		{
			if(strsValue[i]==null)
			{
				strsValue[i]="";
			}
			else if(Utility.checkDate(strsValue[i])==false)
			{
				throw new RuntimeException("Invalid date format");
			}
		}
		this.strsValue=strsValue;
	}

	public String[] getValue()
	{
		return this.strsValue;
	}

	public String getValueAt(int nIndex)
	{
		return strsValue[nIndex];
	}

	public void setValueAt(int nIndex,String strValue)
	{
		if(strValue==null)
		{
			strValue="";
		}
		else if(Utility.checkDate(strValue)==false)
		{
			throw new RuntimeException("Invalid date format");
		}

		strsValue[nIndex]=strValue;
	}
	
	public int getLength()
	{
		return strsValue.length;
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void toXML(StringBuilder strbXML)
	{
		strbXML.append("<DAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(this.strsValue[i]);	
		}
		strbXML.append("\"/>");
	}

	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<DAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<this.strsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(this.strsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}
	
	public Object getObjectValue()
	{
		return strsValue;
	}

	public Object getObjectValueAt(int nIndex)
	{
		return strsValue[nIndex];
	}

	public ObjectExt getObject()
	{
		return new ObjectExt(this.getType(),strsValue);
	}

	public ObjectExt getObjectAt(int nIndex)
	{
		return new ObjectExt(ValueField.DATE_TYPE,strsValue[nIndex]);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public DateArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.DATE_ARRAY_TYPE);
		
		this.strsValue	=new String[0];
	}

	public DateArrayField(String strFieldName,String[] strsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.DATE_ARRAY_TYPE);
		
		if(strsValue==null)
		{
			strsValue=new String[0];
		}

		setValue(strsValue);
	}

	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=this.strsValue.length;
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"\\\"":"\\\",";
			str_JSON.append("\\\"").append(this.strsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=this.strsValue.length;
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"\"":"\",";
			str_JSON.append("\"").append(this.strsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}	
}
