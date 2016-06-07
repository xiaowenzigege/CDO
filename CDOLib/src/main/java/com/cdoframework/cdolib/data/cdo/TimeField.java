/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/TimeField.java,v 1.4 2008/03/12 10:28:13 Frank Exp $
 *
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Map;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Utility;


/**
 * @author Frank
 * modify by @author KenelLiu 
 */
public class TimeField extends ValueFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 500768167125762567L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private ByteBuffer buffer;
	private final int dataIndex=1;//数据保存的起始位置
	private final int databuffer=TIME_FORMAT_STRING.length();//数据占用字节
	private static String defaultWhiteSpace="";
	
	static{
		for(int i=0;i<TIME_FORMAT_STRING.length();i++){//空格用于占位使用
			defaultWhiteSpace=defaultWhiteSpace+" ";
		}
	}
	
	public void setValue(String strValue)
	{
		if(Utility.checkTime(strValue)==false)
		{
			throw new RuntimeException("Invalid time or Invalid time format,time format is "+TIME_FORMAT_STRING);
		}
		allocate(strValue);
	}
	
	public String getValue()
	{
		byte[] bsValue=new byte[databuffer];
		buffer.position(dataIndex);
		buffer.limit(buffer.capacity());
		(buffer.slice()).get(bsValue);
	
		return new String(bsValue).trim();
	}

	public Object getObjectValue()
	{
		return getValue();
	}
	
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}
	
	private void allocate(String strValue){
		if(buffer==null){
			int len=dataIndex+databuffer;
			buffer=ByteBuffer.allocate(len);
			buffer.put((byte)DataType.TIME_TYPE);
		}
		buffer.position(dataIndex);
		buffer.put(strValue.getBytes());
		buffer.flip();
	}		
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){
				
		fieldMap.put(prefixField+this.getName(), buffer);
	}	
	
	public void toXML(StringBuilder strbXML)
	{
		String strValue=getValue();
		strbXML.append("<TF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(strValue).append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strValue=getValue();		
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);
		
		strbXML.append(strIndent).append("<TF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(strValue).append("\"/>\r\n");
	}

	public String toJSON()
	{
		String strValue=getValue();	
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":\"").append(strValue).append("\",");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		String strValue=getValue();	
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":\\\"").append(strValue).append(
						"\\\",");
		return str_JSON.toString();
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public TimeField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.TIME);
		
		allocate(defaultWhiteSpace);
	}

	public TimeField(String strFieldName,String strValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.TIME);

		if(strValue==null)
		{
			strValue="";
		}
		setValue(strValue);
	}
	
	TimeField(String strFieldName,ByteBuffer buffer)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.TIME);

		this.buffer=buffer;
	}
}
