package com.cdoframework.cdolib.data.cdo;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Map;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Utility;

/**
 * @author Frank
 * 重新构造，全部采用 buffer进行管理
 * @author KenelLiu
 *  
 */
public class BooleanArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private static final long serialVersionUID = -4963315441783800310L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------	
	private final int dataIndex=1;//数据保存的起始位置
	private final int databuffer=1;//数据占用字节
	
	public void setValue(boolean[] bsValue)
	{
		if(bsValue==null)
		{
			bsValue=new boolean[0];
		}
		allocate(bsValue);
	}
	
	public boolean[] getValue()
	{
		buffer.position(1);
		int len=getLength();		
		boolean[] bsValue=new boolean[len];
		for(int i=0;i<bsValue.length;i++){
			byte b=buffer.get();
			if(b==1)
				bsValue[i]=true;
			else
				bsValue[i]=false;
		}	
		buffer.clear();
		return bsValue;
	}

	public boolean getValueAt(int nIndex)
	{
		checkArrayIndex(nIndex);
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		byte b=buffer.get();
		buffer.clear();
		if(b==1)
			return true;
		return false;
	}

	public void setValueAt(int nIndex,boolean bValue)
	{	
		checkArrayIndex(nIndex);
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		if(bValue)
			buffer.put((byte)1);
		else
			buffer.put((byte)0);
		buffer.clear();
	}

	public int getLength()
	{
		return (buffer.capacity()-dataIndex)/databuffer;
	}
	
	public Object getObjectValue()
	{
		return getValue();
	}

	public Object getObjectValueAt(int nIndex)
	{
		return new Boolean(getValueAt(nIndex));
	}
	
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}
	
	private void allocate(boolean[] bsValue){
		buffer=DataBufferUtil.allocate(bsValue.length, DataType.BOOLEAN_ARRAY_TYPE, buffer, dataIndex, databuffer);
		//设置起始位置  
		buffer.position(dataIndex);
		for(int i=0;i<bsValue.length;i++){
			if(bsValue[i])
				buffer.put((byte)1);
			else
				buffer.put((byte)0);
		}
		buffer.flip();
	}	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	public void toXML(StringBuilder strbXML)
	{
		strbXML.append("<BAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		boolean[] bsValue=getValue(); 
		for(int i=0;i<bsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(bsValue[i]);	
		}
		strbXML.append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<BAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		boolean[] bsValue=getValue(); 
		for(int i=0;i<bsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(bsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}

	@Override
	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");		
		boolean[] bsValue=getValue(); 
		int _length=bsValue.length;
		for(int i=0;i<bsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(bsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	@Override
	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		boolean[] bsValue=getValue(); 
		int _length=bsValue.length;
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(bsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public BooleanArrayField(String strName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strName);
		setType(Data.BOOLEAN_ARRAY);
		allocate(new boolean[0]);
	}

	public BooleanArrayField(String strName,boolean[] bsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strName);
		
		setType(Data.BOOLEAN_ARRAY);
		
		if(bsValue==null)
		{
			bsValue=new boolean[0];
		}
		allocate(bsValue);
	}
	 
	BooleanArrayField(String strName,ByteBuffer buffer)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strName);
		
		setType(Data.BOOLEAN_ARRAY);
		
		this.buffer=buffer;
	}
}
