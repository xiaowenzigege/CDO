/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/IntegerField.java,v 1.4 2008/03/12 10:28:13 Frank Exp $
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
public class IntegerField extends ValueFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -4743998197912167653L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private ByteBuffer buffer;
	private final int dataIndex=1;//数据保存的起始位置
	private final int databuffer=4;//数据占用字节
	
	public void setValue(int nValue)
	{
		allocate(nValue);
	}
	public int getValue()
	{
		buffer.position(dataIndex);
		return buffer.getInt();
	}
	
	public Object getObjectValue()
	{
		return new Integer(getValue());
	}

	
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}
	
	private void allocate(int dblValue){
		if(buffer==null){
			int len=dataIndex+databuffer;
			buffer=ByteBuffer.allocate(len);
			buffer.put((byte)DataType.INTEGER_TYPE);
		}
		buffer.position(dataIndex);
		buffer.putFloat(dblValue);
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
		int nValue=getValue();
		strbXML.append("<NF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(nValue).append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		int nValue=getValue();
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);
		
		strbXML.append(strIndent).append("<NF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(nValue).append("\"/>\r\n");
	}

	public String toJSON()
	{
		int nValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append(nValue).append(",");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		int nValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append(nValue).append(",");
		return str_JSON.toString();
	}	

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public IntegerField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(DataType.INTEGER_TYPE);

		setValue(0);
	}

	public IntegerField(int nValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系		
		
		setType(DataType.INTEGER_TYPE);
		setValue(nValue);
	}
	
	public IntegerField(String strFieldName,int nValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(DataType.INTEGER_TYPE);
		
		setValue(nValue);
	}
	
	IntegerField(String strFieldName,ByteBuffer buffer)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(DataType.INTEGER_TYPE);
		
		this.buffer=buffer;
	}
}
