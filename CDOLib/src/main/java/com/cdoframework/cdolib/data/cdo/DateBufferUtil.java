package com.cdoframework.cdolib.data.cdo;

import java.nio.ByteBuffer;

public class DateBufferUtil {

	
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
			buffer.clear();
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
		String strValue=new String(bsValue).trim();		
		buffer.clear();
		return strValue;
	}
}
