package com.cdoframework.cdolib.data.cdo;

import java.nio.ByteBuffer;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.ObjectExt;

public class DataBufferUtil {

	
	private static ByteBuffer allocateBuffer(int arrlen,int dataType,ByteBuffer buffer,int dataIndex,int databuffer){
		int len=dataIndex+arrlen*databuffer;
		buffer=ByteBuffer.allocate(len);
		buffer.put((byte)dataType);
		buffer.putShort((short)arrlen);
		return buffer;
	}
	/**
	 * 分配数组内存
	 * @param strsValue 数组
	 * @param dataType 字段类型
	 * @param buffer  
	 * @param dataIndex  数据保存的起始位置
	 * @param databuffer 每个数据内容所占字节
	 */
	static ByteBuffer allocate(int arrlen,int dataType,ByteBuffer buffer,int dataIndex,int databuffer){
		if(buffer==null){
			buffer=allocateBuffer(arrlen, dataType, buffer, dataIndex, databuffer);
		}else{
			buffer.position(1);
			int len=buffer.getShort();
			if(len<arrlen){
				//最新数组大于原来数组, 重新分配长度
				buffer=allocateBuffer(arrlen, dataType, buffer, dataIndex, databuffer);
			}else if(len>arrlen){
				//最新数组小于原来数组  截取原长度的一部分 作为本次数据存放,不需要重新分配内存
				buffer.position(1);
				buffer.putShort((short)arrlen);
				len=dataIndex+arrlen*databuffer;
				buffer.position(0);
				buffer.limit(len);
				buffer=buffer.slice();
			}
			//若相等，数据进行覆盖即可;
		}
		return buffer;
	}
	/**
	 * 
	 * @param buffer
	 * @param dataIndex  数据保存的起始位置
	 * @param databuffer 每个数据内容所占字节
	 * @return
	 */
	static String[] getDateArrayValue(ByteBuffer buffer,int dataIndex,int databuffer)
	{
		buffer.position(1);
		int len=buffer.getShort();
		String[] strsValue=new String[len];
		byte[] bsValue=new byte[databuffer];
		int index;
		for(int i=0;i<strsValue.length;i++){			
			index=dataIndex+i*databuffer;
			buffer.position(index);
			buffer.limit(index+databuffer);
			(buffer.slice()).get(bsValue);
			strsValue[i]=new String(bsValue).trim();
//			buffer.clear();
		}
		return strsValue;		
	}
	/**
	 * 
	 * @param nIndex
	 * @param buffer
	 * @param dataIndex 数据保存的起始位置
	 * @param databuffer 每个数据内容所占字节
	 * @return
	 */
	static String getDateArrayValueAt(int nIndex,ByteBuffer buffer,int dataIndex,int databuffer)
	{	
		
		byte[] bsValue=new byte[databuffer];
		int pos=dataIndex+databuffer*nIndex;
		buffer.position(pos);
		buffer.limit(pos+databuffer);
		(buffer.slice()).get(bsValue);
		return new String(bsValue).trim();
	}
	
	static byte getByte(Field field)
	{
		Object objValue=field;
		switch(field.getType().getDataType())
		{		
			case DataType.BYTE_TYPE:
			{
				return ((ByteField)field).getValue();
			}
			case DataType.SHORT_TYPE:
			{
				return ((Short)(objValue)).byteValue();
			}
			case DataType.INTEGER_TYPE:
			{
				return ((Integer)(objValue)).byteValue();
			}
			case DataType.LONG_TYPE:
			{
				return ((Long)(objValue)).byteValue();
			}
			case DataType.FLOAT_TYPE:
			{
				return ((Float)(objValue)).byteValue();
			}
			case DataType.DOUBLE_TYPE:
			{
				return ((Double)(objValue)).byteValue();
			}
			case DataType.STRING_TYPE:		
			{
				return Byte.parseByte(objValue+"");
			}
		}		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to byte,field name="+field.getName());
	}
	
	static short getShort(Field field)
	{
		Object objValue=field.getObjectValue();
		switch(field.getType().getDataType())
		{
			case DataType.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case DataType.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case DataType.INTEGER_TYPE:
			{
				return ((Integer)objValue).shortValue();
			}
			case DataType.LONG_TYPE:
			{
				return ((Long)objValue).shortValue();
			}
			case DataType.FLOAT_TYPE:
			{
				return ((Float)objValue).shortValue();
			}
			case DataType.DOUBLE_TYPE:
			{
				return ((Double)objValue).shortValue();
			}
			case DataType.STRING_TYPE:
			{
				return Short.parseShort((String)objValue);
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to short,field name="+field.getName());
	}

	static int getInteger(Field field)
	{
		Object objValue=field.getObjectValue();
		switch(field.getType().getDataType())
		{
			case DataType.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case DataType.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case DataType.INTEGER_TYPE:
			{
				return ((Integer)objValue).intValue();
			}
			case DataType.LONG_TYPE:
			{
				return ((Long)objValue).intValue();
			}
			case DataType.FLOAT_TYPE:
			{
				return ((Float)objValue).intValue();
			}
			case DataType.DOUBLE_TYPE:
			{
				return ((Double)objValue).intValue();
			}
			case DataType.STRING_TYPE:
			{
				return Integer.parseInt((String)objValue);
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to integer,field name="+field.getName());
	}

	static  long getLong(Field field)
	{
		Object objValue=field.getObjectValue();
		switch(field.getType().getDataType())
		{
			case DataType.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case DataType.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case DataType.INTEGER_TYPE:
			{
				return ((Integer)objValue).intValue();
			}
			case DataType.LONG_TYPE:
			{
				return ((Long)objValue).longValue();
			}
			case DataType.FLOAT_TYPE:
			{
				return ((Float)objValue).longValue();
			}
			case DataType.DOUBLE_TYPE:
			{
				return ((Double)objValue).longValue();
			}
			case DataType.STRING_TYPE:
			{
				return Long.parseLong((String)objValue);
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to long,field name="+field.getName());
	}

	static float getFloat(Field field)
	{
		Object objValue=field.getObjectValue();
		switch(field.getType().getDataType())
		{
			case DataType.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case DataType.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case DataType.INTEGER_TYPE:
			{
				return ((Integer)objValue).floatValue();
			}
			case DataType.LONG_TYPE:
			{
				return ((Long)objValue).floatValue();
			}
			case DataType.FLOAT_TYPE:
			{
				return ((Float)objValue).floatValue();
			}
			case DataType.DOUBLE_TYPE:
			{
				return ((Double)objValue).floatValue();
			}
			case DataType.STRING_TYPE:
			{
				return Float.parseFloat((String)objValue);
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to float,field name="+field.getName());
	}

	static double getDouble(Field field)
	{
		Object objValue=field.getObjectValue();
		switch(field.getType().getDataType())
		{
			case DataType.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case DataType.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case DataType.INTEGER_TYPE:
			{
				return ((Integer)objValue).intValue();
			}
			case DataType.LONG_TYPE:
			{
				return ((Long)objValue).longValue();
			}
			case DataType.FLOAT_TYPE:
			{
				return ((Float)objValue).floatValue();
			}
			case DataType.DOUBLE_TYPE:
			{
				return ((Double)objValue).doubleValue();
			}
			case DataType.STRING_TYPE:
			{
				return Double.parseDouble((String)objValue);
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to double,field name="+field.getName());
	}

	static String getString(Field field)
	{
		Object objValue=field.getObjectValue();
		if(objValue == null) {
			return null;
		}
		switch(field.getType().getDataType())
		{
			case DataType.BYTE_TYPE:
			case DataType.SHORT_TYPE:
			case DataType.INTEGER_TYPE:
			case DataType.LONG_TYPE:
			case DataType.FLOAT_TYPE:
			case DataType.DOUBLE_TYPE:
			case DataType.STRING_TYPE:
			case DataType.DATE_TYPE:
			case DataType.TIME_TYPE:
			case DataType.DATETIME_TYPE:
			{
				return objValue.toString();
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to string,field name="+field.getName());
	}
	
	static String getDate(Field field)
	{
		Object objValue=field.getObjectValue();
		if(objValue == null) {
			return null;
		}
		switch(field.getType().getDataType())
		{
			case DataType.DATE_TYPE:
			{
				return objValue.toString();
			}
			case DataType.DATETIME_TYPE:
			{
				return objValue.toString().substring(0,10);
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to date,field name="+field.getName());
	}

	static String getTime(Field field)
	{
		Object objValue=field.getObjectValue();
		if(objValue == null) {
			return null;
		}
		switch(field.getType().getDataType())
		{
			case DataType.DATE_TYPE:
			{
				return objValue.toString();
			}
			case DataType.DATETIME_TYPE:
			{
				return objValue.toString().substring(11);
			}
		}
		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to time,field name="+field.getName());
	}

	static String getDateTime(Field field)
	{
		
		Object objValue=field.getObjectValue();
		if(objValue == null) {
			return null;
		}
		switch(field.getType().getDataType())
		{
			case DataType.DATETIME_TYPE:
			{
				return objValue.toString();
			}
		}		
		throw new RuntimeException(field.getType().getFieldType()+" cannot be cast to dateTime,field name="+field.getName());
	}
	
}
