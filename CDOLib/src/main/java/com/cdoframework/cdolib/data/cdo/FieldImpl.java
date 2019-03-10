package com.cdoframework.cdolib.data.cdo;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Map;

import com.cdo.google.protocol.GoogleCDO;
import com.google.protobuf.ByteString;


/**
 * 重新构造
 * @author KenelLiu
 *
 */
public abstract class FieldImpl implements Field
{


	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	private static final long serialVersionUID = 1324693182949266208L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private Data nType;
	private String strName;
	protected ByteBuffer buffer=null;//需要谨慎操作，仅在内部字段里使用
	
	public void setType(Data nType)
	{
		this.nType=nType;
	}
	public Data getType()
	{
		return nType;
	}

	public void setName(String strName)
	{
		this.strName=strName;
	}
	public String getName()
	{
		return strName;
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public FieldImpl()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		nType=Data.NONE;
		strName="";
	}

	public FieldImpl(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		nType=Data.NONE;
		strName=strFieldName;
	}
	@Override
	public String toHtmlJSON(){
		return toJSON();
	}	
	@Override
	public String toString() {	
		return toJSON();		
	}
	
	@Override
	public void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){
		fieldMap.put(prefixField+this.getName(), buffer);		
	}

	@Override
	public void toProto(String prefixField,GoogleCDO.CDOProto.Builder cdoProto){
		GoogleCDO.CDOProto.Entry.Builder entry=GoogleCDO.CDOProto.Entry.newBuilder();
		entry.setName(prefixField+this.getName());
		entry.setValue(ByteString.copyFrom(buffer));
		buffer.flip();
		cdoProto.addFields(entry);
	}
	
	@Override
	public Buffer getBuffer() {		
		return buffer;
	}
	
	@Override
	public void release() {		
		 this.buffer=null;
	}
	
}
