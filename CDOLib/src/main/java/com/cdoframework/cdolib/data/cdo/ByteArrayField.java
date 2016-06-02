/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/ByteArrayField.java,v 1.4 2008/03/12 10:30:58 Frank Exp $
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;

import java.nio.ByteBuffer;
import java.util.Map;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Utility;

/**
 * @author Frank
 * modify  Kenel Liu 
 */
public class ByteArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1390757657933478538L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private byte[] bysValue;
	public void setValue(byte[] bysValue)
	{
		if(bysValue==null)
		{
			bysValue=new byte[0];
		}
		this.bysValue=bysValue;
	}
	public byte[] getValue()
	{
		return this.bysValue;
	}

	public byte getValueAt(int nIndex)
	{
		return bysValue[nIndex];
	}

	public void setValueAt(int nIndex,byte byValue)
	{
		bysValue[nIndex]=byValue;
	}
	
	public int getLength()
	{
		return bysValue.length;
	}
	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void toAvro(String prefixField,Map<String,ByteBuffer> fieldMap){		
		//为数组分配字节  boolean型为一个字节  1表示true,0表示fasle. 数组
		int len=1+2+this.bysValue.length;//字段类型所占字节+数组个数所占字节+数据所占字节
		ByteBuffer buffer=ByteBuffer.allocate(len);
		buffer.put((byte)DataType.BYTE_ARRAY_TYPE);
		buffer.putInt(this.bysValue.length);		
		for(int i=0;i<this.bysValue.length ;i++){
			buffer.put(bysValue[i]);
		}		
		buffer.flip();			
		fieldMap.put(prefixField+this.getName(), buffer);
	}
	
	public void toXML(StringBuilder strbXML)
	{			
		strbXML.append("<BYAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		//拆分成2段，循环一半，对数据量多的平均速度提高15-20%
		int length=this.bysValue.length;
		int mid=length/2;								
		StringBuilder tmpBuild=new StringBuilder(mid);
		int j=mid+1;
		int tmpLength=length-1;//用于判断是否需要添加逗号
		for(int i=0;i<=mid ;i++){
			strbXML.append(bysValue[i]);
			if(i<tmpLength)
				strbXML.append(",");					
			if(j<length){
				tmpBuild.append(bysValue[j]);
				j++;
				if(j<length)
					tmpBuild.append(",");	
			}
		}
		strbXML.append(tmpBuild);			
		strbXML.append("\"/>");		
	}
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);
		
		strbXML.append(strIndent).append("<BYAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		//拆分成2段，循环一半，对数据量多的平均速度提高15-20%
		int length=this.bysValue.length;
		int mid=length/2;								
		StringBuilder tmpBuild=new StringBuilder(mid);
		int j=mid+1;
		int tmpLength=length-1;//用于判断是否需要添加逗号
		for(int i=0;i<=mid ;i++){
			strbXML.append(bysValue[i]);
			if(i<tmpLength)
				strbXML.append(",");					
			if(j<length){
				tmpBuild.append(bysValue[j]);
				j++;
				if(j<length)
					tmpBuild.append(",");	
			}
		}
		strbXML.append(tmpBuild);			
		strbXML.append("\"/>\r\n");
	}
	

	public Object getObjectValue()
	{
		return bysValue;
	}

	public Object getObjectValueAt(int nIndex)
	{
		return new Byte(bysValue[nIndex]);
	}

	public ObjectExt getObject()
	{
		return new ObjectExt(this.getType(),bysValue);
	}

	public ObjectExt getObjectAt(int nIndex)
	{
		return new ObjectExt(ValueField.BYTE_TYPE,bysValue[nIndex]);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ByteArrayField(String strName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strName);
		setType(ValueField.BYTE_ARRAY_TYPE);
		
		this.bysValue	=new byte[0];
	}

	public ByteArrayField(String strName,byte[] bysValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strName);
		
		setType(ValueField.BYTE_ARRAY_TYPE);
		
		if(bysValue==null)
		{
			bysValue=new byte[0];
		}

		this.bysValue	=bysValue;
	}
	
	public void toXMLLog(StringBuilder strbXML){
		strbXML.append("<BYAF N=\"").append(this.getName()).append("\"");;
		strbXML.append(" V=\"");
		strbXML.append(this.bysValue);		
		strbXML.append("\"/>");		
	}

	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=this.bysValue.length;
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(this.bysValue[i]).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}	
	
	
	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		str_JSON.append("").append(this.bysValue);
		str_JSON.append("],");
		return str_JSON.toString();
	}
	
}
