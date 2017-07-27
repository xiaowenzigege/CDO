
package com.cdoframework.cdolib.data.cdo;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Utility;
/**
 * 重新构造
 * @author KenelLiu
 *
 */
public class FloatArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	private static final long serialVersionUID = -9036771599110221502L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private final int dataIndex=1;//数据保存的起始位置
	private final int databuffer=4;//数据占用字节
	
	public void setValue(float[] fValue)
	{
		if(fValue==null)
		{
			fValue=new float[0];
		}
		allocate(fValue);
	}
	public float[] getValue()
	{
		buffer.position(1);
		int len=getLength();
		float[] result=new float[len];
		for(int i=0;i<result.length;i++){			
			result[i]=buffer.getFloat();
		}
		buffer.clear();
		return result;
	}

	public float getValueAt(int nIndex)
	{
		checkArrayIndex(nIndex);
		
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);		
		float f=buffer.getFloat();
		buffer.clear();
		return f;
	}

	public void setValueAt(int nIndex,float fValue)
	{
		checkArrayIndex(nIndex);
		
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		buffer.putFloat(fValue);
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
		return new Float(getValueAt(nIndex));
	}
	
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}

	private ByteBuffer allocate(float[] fsValue){
		buffer=DataBufferUtil.allocate(fsValue.length, DataType.FLOAT_ARRAY_TYPE, buffer, dataIndex, databuffer);
		//设置起始位置  
		buffer.position(dataIndex);
		for(int i=0;i<fsValue.length;i++){
			buffer.putFloat(fsValue[i]);
		}
		buffer.flip();
		return buffer;
	}	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------	
	@Override
	public void toXML(StringBuilder strbXML)
	{
		float[] fsValue=getValue();
		strbXML.append("<FAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		for(int i=0;i<fsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(fsValue[i]);	
		}
		strbXML.append("\"/>");
	}
	@Override
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		float[] fsValue=getValue();
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<FAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<fsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(fsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}	
	
	@Override
	public String toJSON()
	{
		float[] fsValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=fsValue.length;
		for(int i=0;i<fsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(fsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}	

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public FloatArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.FLOAT_ARRAY);
		
		setValue(new float[0]);
	}

	public FloatArrayField(String strFieldName,float[] fValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.FLOAT_ARRAY);
		
		if(fValue==null)
		{
			fValue=new float[0];
		}
		setValue(fValue);
	}
	
	FloatArrayField(String strFieldName,ByteBuffer buffer)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.FLOAT_ARRAY);
		
		this.buffer=buffer;
	}


}
