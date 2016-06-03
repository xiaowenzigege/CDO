/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/BooleanArrayField.java,v 1.1 2008/03/22 13:32:54 Frank Exp $
 *
 * $Log: BooleanArrayField.java,v $
 * Revision 1.1  2008/03/22 13:32:54  Frank
 * *** empty log message ***

 *
 *
 */

package com.cdoframework.cdolib.data.cdo;

import java.nio.ByteBuffer;
import java.util.Map;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Utility;

/**
 * @author Frank
 * modify by @author KenelLiu 
 */
public class BooleanArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -4963315441783800310L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private boolean[] bsValue;
	public void setValue(boolean[] bsValue)
	{
		if(bsValue==null)
		{
			bsValue=new boolean[0];
		}
		this.bsValue=bsValue;
	}
	public boolean[] getValue()
	{
		return this.bsValue;
	}

	public boolean getValueAt(int nIndex)
	{
		return bsValue[nIndex];
	}

	public void setValueAt(int nIndex,boolean bValue)
	{
		bsValue[nIndex]=bValue;
	}
	
	public int getLength()
	{
		return bsValue.length;
	}
	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	public void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){
		//为数组分配字节  boolean型为一个字节  1表示true,0表示fasle. 数组
		int len=1+2+this.bsValue.length;//字段类型所占字节+数组个数所占字节+数据所占字节
		ByteBuffer buffer=ByteBuffer.allocate(len);
		buffer.put((byte)DataType.BOOLEAN_ARRAY_TYPE);
		buffer.putShort((short)this.bsValue.length);
		for(int i=0;i<this.bsValue.length;i++){
			if(this.bsValue[i])
				buffer.put((byte)1);
			else
				buffer.put((byte)0);
		}
		buffer.flip();		
		fieldMap.put(prefixField+this.getName(), buffer);		
	}	
	
	public void toXML(StringBuilder strbXML)
	{
		strbXML.append("<BAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<this.bsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(this.bsValue[i]);	
		}
		strbXML.append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<BAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<this.bsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(this.bsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}
	
	public Object getObjectValue()
	{
		return bsValue;
	}

	public Object getObjectValueAt(int nIndex)
	{
		return new Boolean(bsValue[nIndex]);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public BooleanArrayField(String strName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strName);
		setType(DataType.BOOLEAN_ARRAY_TYPE);
		
		this.bsValue	=new boolean[0];
	}

	public BooleanArrayField(String strName,boolean[] bsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strName);
		
		setType(DataType.BOOLEAN_ARRAY_TYPE);
		
		if(bsValue==null)
		{
			bsValue=new boolean[0];
		}

		this.bsValue	=bsValue;
	}

	@Override
	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=this.bsValue.length;
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(this.bsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	@Override
	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=this.bsValue.length;
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(this.bsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}
	

}
