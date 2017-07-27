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
public class TimeArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	private static final long serialVersionUID = -9060053020629106038L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private final int dataIndex=1;//数据保存的起始位置
	private final int databuffer=PATTERN_TIME.length();//数据占用字节
	private static String defaultWhiteSpace="";
	
	static{
		for(int i=0;i<PATTERN_TIME.length();i++){//空格用于占位使用
			defaultWhiteSpace=defaultWhiteSpace+" ";
		}
	}	
	public void setValue(String[] strsValue)
	{
		if(strsValue==null)
		{
			strsValue=new String[0];
		}
		for(int i=0;i<strsValue.length;i++)
		{
			if(strsValue[i]==null){
				strsValue[i]=defaultWhiteSpace;
			}else if(Utility.checkTime(strsValue[i])==false){
				throw new RuntimeException("Invalid time or Invalid time format,time format is "+PATTERN_TIME);
			}
		}
		allocate(strsValue);
	}

	public String[] getValue()
	{
		return DataBufferUtil.getDateArrayValue(buffer, dataIndex, databuffer);
	}

	public String getValueAt(int nIndex)
	{
		checkArrayIndex(nIndex);
		return DataBufferUtil.getDateArrayValueAt(nIndex, buffer, dataIndex, databuffer);
	}
	
	public void setValueAt(int nIndex,String strValue)
	{
		if(strValue==null)
		{
			strValue="";
		}
		else if(Utility.checkTime(strValue)==false)
		{
			throw new RuntimeException("Invalid time or Invalid time format,time format is "+PATTERN_TIME);
		}

		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		buffer.put(strValue.getBytes());
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
		return getValueAt(nIndex);
	}
	
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}

	private void allocate(String[] strsValue){

		buffer=DataBufferUtil.allocate(strsValue.length, DataType.TIME_ARRAY_TYPE, buffer, dataIndex, databuffer);
		//设置起始位置  
		buffer.position(dataIndex);
		for(int i=0;i<strsValue.length;i++){
			buffer.put(strsValue[i].getBytes());
		}
		buffer.flip();
	}	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	@Override
	public void toXML(StringBuilder strbXML)
	{
		String[] strsValue=getValue();
		strbXML.append("<TAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		for(int i=0;i<strsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(strsValue[i]);	
		}
		strbXML.append("\"/>");
	}
	@Override
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String[] strsValue=getValue();
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<TAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<strsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(strsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}	
	@Override
	public String toJSON()
	{
		String[] strsValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=strsValue.length;
		for(int i=0;i<strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\"":"\",";
			str_JSON.append("\"").append(strsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public TimeArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.TIME_ARRAY);
		
		setValue(new String[0]);
	}

	public TimeArrayField(String strFieldName,String[] strsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.TIME_ARRAY);
		
		if(strsValue==null)
		{
			strsValue=new String[0];
		}

		setValue(strsValue);
	}
	
	 TimeArrayField(String strFieldName,ByteBuffer buffer)
	 {
			super(strFieldName);
			
			setType(Data.TIME_ARRAY);
			
			this.buffer=buffer;
	}
}
