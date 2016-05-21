/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/ShortArrayField.java,v 1.4 2008/03/12 10:30:56 Frank Exp $
 *
 * $Log: ShortArrayField.java,v $
 * Revision 1.4  2008/03/12 10:30:56  Frank
 * *** empty log message ***
 *
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;

import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Utility;

/**
 * @author Frank
 */
public class ShortArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 2227539383025705302L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private short[] shsValue;
	public void setValue(short[] shsValue)
	{
		if(shsValue==null)
		{
			shsValue=new short[0];
		}
		this.shsValue=shsValue;
	}
	public short[] getValue()
	{
		return this.shsValue;
	}

	public short getValueAt(int nIndex)
	{
		return shsValue[nIndex];
	}

	public void setValueAt(int nIndex,short shValue)
	{
		shsValue[nIndex]=shValue;
	}
	
	public int getLength()
	{
		return shsValue.length;
	}

	public ObjectExt getObject()
	{
		return new ObjectExt(this.getType(),shsValue);
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	public void toXML(StringBuilder strbXML)
	{
		strbXML.append("<SAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		for(int i=0;i<this.shsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(this.shsValue[i]);	
		}
		strbXML.append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<SAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<this.shsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(this.shsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}	

	public Object getObjectValue()
	{
		return shsValue;
	}

	public Object getObjectValueAt(int nIndex)
	{
		return new Short(shsValue[nIndex]);
	}

	public ObjectExt getObjectAt(int nIndex)
	{
		return new ObjectExt(ValueField.SHORT_TYPE,shsValue[nIndex]);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ShortArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.SHORT_ARRAY_TYPE);
		
		this.shsValue	=new short[0];
	}

	public ShortArrayField(String strFieldName,short[] shsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(ValueField.SHORT_ARRAY_TYPE);
		
		if(shsValue==null)
		{
			shsValue=new short[0];
		}

		this.shsValue	=shsValue;
	}

	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=shsValue.length;
		for(int i=0;i<this.shsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(this.shsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=shsValue.length;
		for(int i=0;i<this.shsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(this.shsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}	
}
