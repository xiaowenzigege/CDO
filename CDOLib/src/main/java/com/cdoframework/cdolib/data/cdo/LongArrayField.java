/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/LongArrayField.java,v 1.4 2008/03/12 10:30:58 Frank Exp $
 *
 * $Log: LongArrayField.java,v $
 * Revision 1.4  2008/03/12 10:30:58  Frank
 * *** empty log message ***
 *
 * Revision 1.4  2008/03/11 15:08:33  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/03/10 14:54:15  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/08 12:10:54  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/07 11:20:21  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/11/03 02:25:42  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/10/12 02:36:16  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/11 13:41:24  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/11 01:10:57  Frank
 * *** empty log message ***
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
public class LongArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -4517167042801694678L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private ByteBuffer buffer;
	private final int dataIndex=3;//数据保存的起始位置
	private final int databuffer=8;//数据占用字节
	public void setValue(long[] lsValue)
	{
		if(lsValue==null)
		{
			lsValue=new long[0];
		}
		allocate(lsValue);
	}
	public long[] getValue()
	{
		
		buffer.position(1);
		int len=buffer.getShort();
		long[] result=new long[len];
		buffer.position(dataIndex);
		for(int i=0;i<result.length;i++){			
			result[i]=buffer.getLong();
		}
		buffer.clear();
		return result;
	}

	public long getValueAt(int nIndex)
	{
		checkArrayIndex(nIndex);
		
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		long result=buffer.getLong();		
		buffer.clear();
		return result;
	}

	public void setValueAt(int nIndex,long lValue)
	{
		checkArrayIndex(nIndex);
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		buffer.putLong(lValue);
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
		return new Long(getValueAt(nIndex));
	}
	@Override
	public Buffer getBuffer() {	
		return buffer;
	}

	private ByteBuffer allocate(long[] nsValue){
		buffer=DataBufferUtil.allocate(nsValue.length, DataType.LONG_ARRAY_TYPE, buffer, dataIndex, databuffer);
		//设置起始位置  
		buffer.position(dataIndex);
		for(int i=0;i<nsValue.length;i++){
			buffer.putLong(nsValue[i]);
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
		long[] lsValue=getValue();
		strbXML.append("<LAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		for(int i=0;i<lsValue.length;i=i+1)
		{
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(lsValue[i]);	
		}
		strbXML.append("\"/>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		long[] lsValue=getValue();
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<LAF N=\"").append(this.getName()).append("\"");
		strbXML.append(" V=\"");
		for(int i=0;i<lsValue.length;i=i+1)
		{		
			if(i>0)
			{
				strbXML.append(",");	
			}
			strbXML.append(lsValue[i]);				
		}
		strbXML.append("\"/>\r\n");
	}	
	
	public String toJSON()
	{
		long[] lsValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=lsValue.length;
		for(int i=0;i<lsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(lsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		long[] lsValue=getValue();
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=lsValue.length;
		for(int i=0;i<lsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(lsValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}		



	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public LongArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.LONG_ARRAY);
		

		setValue(new long[0]);
	}

	public LongArrayField(String strFieldName,long[] lsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.LONG_ARRAY);
		
		if(lsValue==null)
		{
			lsValue=new long[0];
		}

		setValue(lsValue);
	}

	LongArrayField(String strFieldName,ByteBuffer buffer)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.LONG_ARRAY);
		
		this.buffer=buffer;
	}
}
