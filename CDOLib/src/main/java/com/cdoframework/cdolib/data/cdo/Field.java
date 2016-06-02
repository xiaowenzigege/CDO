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


import java.nio.ByteBuffer;
import java.util.Map;

import com.cdoframework.cdolib.base.DataType;

/**
 * @author Frank
 * modify by @author KenelLiu
 * add method toXMLLog  toString  toAvro 
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
	public static final String CDO_Field_Max_Level="$Level"; 
	public void setType(int nType);
	public int getType();
	public void setName(String strName);
	public String getName();
	
	public  Object getObjectValue();
	
	public  String toJSON();
	public  String toJSONString();
	public  String toString();
	
	public void toXML(StringBuilder strbXML);
	public void toXMLLog(StringBuilder strbXML);
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML);
	
	
	public void toAvro(String prefixField,Map<String,ByteBuffer> fieldMap);
	/**
	 *  A 类型-数据	
	 *  对  boolean,short,int,long,float,double,string 序列化
	 *  第一个字节  字段类型参数
	 *  第2个字节到末尾为数据
		 * 每个数据 占字节
		 * boolean  1个字节
		 * short 2个字节
		 * int   4个字节
		 * long  8个字节
		 * float 4个字节
		 * doulbe 8个字节
		 * string utf8实际占用的字节长度

	 * B 类型-数组个数(Short型)-数据-数据-数据。。。。
	 *  对于 boolean,short,int,long,float,double数组序列化 
	 * 第一个字节  字段类型参数
	 * 第2,3个字节组合  为数组长度。short型  最多(Short.MAX_VALUE)个数组
	 * 第4个到末尾 数据,
	 * 每个数据 占字节
		 * boolean  1个字节
		 * short 2个字节
		 * int   4个字节
		 * long  8个字节
		 * float 4个字节
		 * doulbe 8个字节
		 * 
	         对于 bytes数组 类型-数组个数(int型)-数据-数据-数据

	   C  类型-数组个数(short型)-int长度-utf8数据-int长度-utf8数据-int长度-utf8数据	
	             对String 数组序列化 
	   	  第一个字节  字段类型参数 
	   	  第2,3个字节组合  为数组长度。short型  最多(Short.MAX_VALUE)个数组
	   	 第4-7个字节 4个字节长度，后面为第一个数据UTF8字符长度
	   	 之后为第二个字符的长度[4个字节表示] 后面为第二个数据UTF8字符内容
	  
	   D 对文件类型  在传输中  特别处理，不再这儿做处理	 
	 */
	public int toAvro(String prefixField,Map<String,ByteBuffer> fieldMap,int maxLevel);
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------
	// 添加转化成JSON的格式

}
