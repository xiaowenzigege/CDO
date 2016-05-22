/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/StringArrayField.java,v 1.4 2008/03/12 10:30:56 Frank Exp $
 *
 * $Log: StringArrayField.java,v $
 * Revision 1.4  2008/03/12 10:30:56  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;

import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.util.Function;

/**
 * @author Frank
 * modify by @author KenelLiu
 */
public class StringArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1892117383525261592L;
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
		strbXML.append("<STRAF N=\"").append(this.getName()).append("\">");
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			strbXML.append("<STR>").append(Function.FormatTextForXML(this.strsValue[i])).append("</STR>");
		}
		strbXML.append("</STRAF>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);
		
		strbXML.append(strIndent).append("<STRAF N=\"").append(this.getName()).append("\">\r\n");
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			strbXML.append(strIndent).append("\t<STR>").append(Function.FormatTextForXML(this.strsValue[i])).append("</STR>\r\n");
		}
		strbXML.append(strIndent).append("</STRAF>\r\n");
	}	
	@Override
	public void toXMLLog(StringBuilder strbXML) {
		

		String value="";
		strbXML.append("<STRAF N=\"").append(this.getName()).append("\">");
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			value=this.strsValue[i];
			if(value.length()>100){
				value=value.substring(0, 100)+"......";
			}
			strbXML.append("<STR>").append(Function.FormatTextForXML(value)).append("</STR>");
		}
		strbXML.append("</STRAF>");		
		
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
		return new ObjectExt(ValueField.STRING_TYPE,strsValue[nIndex]);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public StringArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.STRING_ARRAY_TYPE);
		
		this.strsValue	=new String[0];
	}

	public StringArrayField(String strFieldName,String[] strsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.STRING_ARRAY_TYPE);
		
		if(strsValue==null)
		{
			strsValue=new String[0];
		}

		this.strsValue	=strsValue;
	}

	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=strsValue.length;
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\"":"\",";
			str_JSON.append("\"").append(Function.FormatTextForJson(this.strsValue[i])).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=strsValue.length;
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\\\"":"\\\",";
			str_JSON.append("\\\"").append(Function.FormatTextForJson(this.strsValue[i])).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}	
	
	public String toString(){

		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=strsValue.length;
		String value=null;
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\\\"":"\\\",";
			
			value=this.strsValue[i];
			if(value.length()>100){
				value=value.substring(0, 100)+"......";
			}			
			str_JSON.append("\\\"").append(Function.FormatTextForJson(value)).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}
}
