/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/Field.java,v 1.3 2008/03/22 13:32:54 Frank Exp $
 *
 * $Log: Field.java,v $
 * Revision 1.3  2008/03/22 13:32:54  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/12 10:30:57  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/07 11:20:20  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/11/03 02:25:40  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/10/11 13:55:05  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/11 01:10:56  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;


import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Map;

import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.DataType;

/**
 * 重新构造,采用buffer进行管理CDO 定义字段
 * @author KenelLiu
 *
 */
public interface Field extends DataType
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	
	public void setType(DataType.Data data);
	public DataType.Data getType();	
	public void setName(String strName);
	public String getName();
	
	public  Object getObjectValue();
	/**
	 * 字段key:value内容转换成json格式
	 * @return
	 */
	public  String toJSON();
	
	/**
	 * 输出string,为json格式.
	 * @return
	 */
	public  String toString();
	
	/**
	 * 输出string,为json格式.对字符串内容转义
	 * @return
	 */
	public  String toHtmlJSON();
	
	/**
	 * CDO里字段转换成 xml文件 格式
	 * @param strbXML
	 */
	public void toXML(StringBuilder strbXML);
	/**
	 * CDO里字段转换成 xml文件 格式
	 * @param strbXML
	 */
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML);
	/**
	 * CDO字段转换成 apache avro格式
	 * @param prefixField
	 * @param fieldMap
	 */
	public void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap);
	/**
	 * CDO字段转换成 google protobuf格式
	 * @param prefixField
	 * @param fieldMap
	 */
	public void toProto(String prefixField,GoogleCDO.CDOProto.Builder cdoProto);
	/**
	 * 释放对象
	 */
	public void release();
	
	
	/**
	 * [CDOField,CDOArrayField]字段  由CDO构成，CDO保存的数据是由 以下基础数据字段组成
	 * 即对基础字段序列化即可
	 * 
	 * 数据存储采用buffer 字节存储。方便转化成 xml,json,apache avro,google proto 等字节序列化,反序列化
	 * I 类型-数据
	 * 	 boolean,short,int,long,float,double,date,dataTime,Time,String 序列化
	 *   第一个字节  字段类型参数	
	 *   第二个字节到末尾为数据
		 * 每个数据 内容  所占字节
		 * boolean  1个字节
		 * byte  1个字节
		 * short 2个字节
		 * int   4个字节
		 * long  8个字节
		 * float 4个字节
		 * doulbe 8个字节
		 * date   10个字节  格式(2012-10-15)
		 * dateTime 19字节 格式(2012-10-15 20:12:10)
		 * Time 8个字节   格式(20:12:10)
		 * string utf8实际占用的字节长度
	  
	   II 类型-数据-数据-数据-数据-数据-数据	   
	      byte Array,boolean Array,short Array,int Array,long Array,float Array,double Array,date Array,dataTime Array,Time Array数组序列化 
	                    第一个字节  字段类型参数	       
	                    数组中的每个数据 所占字节参考 I
	                    
	   III string数组类型-数组长度-数据长度-数据内容-数据长度-数据内容....--数据长度--数据内容-.....
	                       第一个字节  字段类型参数    
	                        数组长度     占2个字节 为short型,数组最多为 (Short.MAX_VALUE)
	                        每个数据长度     占4个字节 为int 型,表示  实际数据内容的字节长度,最多有(Integer.MAX_VALUE)个UTF8字节         
	                        每个数据内容   utf8实际占用的字节长度
	   IV 文件类型  考虑到文件数据量大,不在这儿序列化,在传输中做字节流特别处理,且仅对CDO 最外层的文件类型 做传输
	                    对CDO里嵌套CDO包含的文件对象，考虑复杂度,性能,实际用途均会忽略掉文件传输.	                         	                                                                                   
	 */
	public  Buffer getBuffer();

}
