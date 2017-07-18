/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/DateTimeArrayField.java,v 1.4 2008/03/12 10:30:55 Frank Exp $
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
public class DateTimeArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -1218499970415772864L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private final int dataIndex=1;//数据保存的起始位置
	private final int databuffer=PATTERN_DATETIME.length();//数据占用字节
	private static String defaultWhiteSpace="";
	
	static{
		for(int i=0;i<PATTERN_DATETIME.length();i++){//空格用于占位使用
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
			}else if(Utility.checkDateTime(strsValue[i])==false){
				throw new RuntimeException("Invalid datetime or Invalid datetime format,dateTime format is "+PATTERN_DATETIME);
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
		checkArrayIndex(nIndex);
		if(strValue==null)
		{
			strValue=defaultWhiteSpace;
		}
		else if(Utility.checkDateTime(strValue)==false)
		{
			throw new RuntimeException("Invalid datetime or Invalid datetime format,dateTime format is "+PATTERN_DATETIME);
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

		buffer=DataBufferUtil.allocate(strsValue.length, DataType.DATETIME_ARRAY_TYPE, buffer, dataIndex, databuffer);
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

	public void toXML(StringBuilder strbXML)
	{
		String[]  strsValue=getValue();
		strbXML.append("<DTAF N=\"").append(this.getName()).append("\"");;
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
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);
		
		String[]  strsValue=getValue();
		strbXML.append(strIndent).append("<DTAF N=\"").append(this.getName()).append("\"");
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

	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		String[] strsValue=getValue();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=strsValue.length;
		for(int i=0;i<strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\\\"":"\\\",";
			str_JSON.append("\\\"").append(strsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		String[] strsValue=getValue();
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

	public DateTimeArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.DATETIME_ARRAY);
		
		setValue(new String[0]);
	}

	public DateTimeArrayField(String strFieldName,String[] strsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.DATETIME_ARRAY);
		
		if(strsValue==null)
		{
			strsValue=new String[0];
		}

		setValue(strsValue);
	}

	 DateTimeArrayField(String strFieldName,ByteBuffer buffer)
	 {
			super(strFieldName);
			
			setType(Data.DATETIME_ARRAY);
			
			this.buffer=buffer;
	}
}
