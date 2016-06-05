/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/DoubleArrayField.java,v 1.4 2008/03/12 10:30:56 Frank Exp $
 *
 * $Log: DoubleArrayField.java,v $
 * Revision 1.4  2008/03/12 10:30:56  Frank
 * *** empty log message ***

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
public class DoubleArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -2827948642997814796L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private ByteBuffer buffer;
	private final int dataIndex=3;//数据保存的起始位置
	private final int databuffer=8;//数据占用字节
	
	public void setValue(double[] dblValue)
	{
		if(dblValue==null)
		{
			dblValue=new double[0];
		}
		allocate(dblValue);
	}
	public double[] getValue()
	{
		buffer.position(1);
		int len=buffer.getShort();
		double[] result=new double[len];
		buffer.position(dataIndex);
		for(int i=0;i<result.length;i++){			
			result[i]=buffer.getDouble();
		}
		buffer.clear();
		return result;
	}

	public double getValueAt(int nIndex)
	{
		checkArrayIndex(nIndex);
		
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		double result=buffer.getDouble();		
		buffer.clear();
		return result;
	}

	public void setValueAt(int nIndex,double dblValue)
	{	
		checkArrayIndex(nIndex);
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		buffer.putDouble(dblValue);
		buffer.clear();		
	}
	
	public int getLength()
	{
		buffer.position(1);
		int len=buffer.getShort();
		buffer.clear();
		return len;
	}
	public Object getObjectValue()
	{
		return getValue();
	}

	public Object getObjectValueAt(int nIndex)
	{
		return new Double(getValueAt(nIndex));
	}
	
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}

	private ByteBuffer allocate(double[] dblsValue){
		buffer=DateBufferUtil.allocate(dblsValue.length, DataType.DOUBLE_ARRAY_TYPE, buffer, dataIndex, databuffer);
		//设置起始位置  
		buffer.position(dataIndex);
		for(int i=0;i<dblsValue.length;i++){
			buffer.putDouble(dblsValue[i]);
		}
		buffer.flip();
		return buffer;
	}
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){

		fieldMap.put(prefixField+this.getName(), buffer);
	}		
	
	public void toXML(StringBuilder strbXML)
	{
		double[] dblsValue=getValue();
		strbXML.append("<DBLAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		for(int i=0;i<dblsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(dblsValue[i]);	
		}
		strbXML.append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		double[] dblsValue=getValue();
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<DBLAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<dblsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(dblsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}	

	public String toJSON()
	{
		double[] dblsValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=dblsValue.length;
		for(int i=0;i<dblsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(dblsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		double[] dblsValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=dblsValue.length;
		for(int i=0;i<dblsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(dblsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public DoubleArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(DataType.DOUBLE_ARRAY_TYPE);

		setValue(new double[0]);
	}

	public DoubleArrayField(String strFieldName,double[] dblValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(DataType.DOUBLE_ARRAY_TYPE);
		
		if(dblValue==null)
		{
			dblValue=new double[0];
		}

		setValue(dblValue);
	}
	 
	  DoubleArrayField(String strFieldName,ByteBuffer buffer)
     {

			//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
			super(strFieldName);
			
			setType(DataType.DOUBLE_ARRAY_TYPE);

			this.buffer=buffer;
		}
}
