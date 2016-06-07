/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/FloatField.java,v 1.4 2008/03/12 10:28:13 Frank Exp $
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
public class FloatField extends ValueFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -6190593964278306132L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private ByteBuffer buffer;
	private final int dataIndex=1;//数据保存的起始位置
	private final int databuffer=4;//数据占用字节
	
	public void setValue(float fValue)
	{
		allocate(fValue);
	}
	public float getValue()
	{
		
		buffer.position(dataIndex);
		return buffer.getFloat();
	}
	
	public Object getObjectValue()
	{
		return new Float(getValue());
	}
	
	
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}
	
	private void allocate(float dblValue){
		if(buffer==null){
			int len=dataIndex+databuffer;
			buffer=ByteBuffer.allocate(len);
			buffer.put((byte)DataType.FLOAT_TYPE);
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
		float fValue=getValue();
		strbXML.append("<FF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(fValue).append("\"/>");
	}

	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		float fValue=getValue();
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);		

		strbXML.append(strIndent).append("<FF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"").append(fValue).append("\"/>\r\n");
	}

	public String toJSON()
	{
		float fValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append(fValue).append(",");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		float fValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append(fValue).append(",");
		return str_JSON.toString();
	}	

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public FloatField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.FLOAT);
				
		setValue(0);
	}

	public FloatField(String strFieldName,float fValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.FLOAT);
		
		setValue(fValue);
	}


	public FloatField(float fValue)
	{

		setType(Data.FLOAT);
		
		setValue(fValue);
	}
	
	 //仅作反序列化使用  不对外开放
	 FloatField(String strFieldName,ByteBuffer buffer)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.FLOAT);
		
		this.buffer=buffer;
	}
}
