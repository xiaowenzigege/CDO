/**
 * 定义类型
 */
package com.cdoframework.cdolib.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
/**
 * Type为Object的实际类型的定义字符串，比如Byte,byte[],Date,Date[]
 * 其中Date,Time,DateTime实际为特定格式的String，但是Type不是String
 */
public interface DataType extends Serializable
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	byte NONE_TYPE				=0;

	byte BOOLEAN_TYPE			=1;
	byte BYTE_TYPE				=2;
	byte SHORT_TYPE				=3;
	byte INTEGER_TYPE			=4;
	byte LONG_TYPE				=5;
	byte FLOAT_TYPE				=6;
	byte DOUBLE_TYPE				=7;
	byte STRING_TYPE				=8;
	byte DATE_TYPE				=9;
	byte TIME_TYPE				=10;
	byte DATETIME_TYPE			=11;
	byte CDO_TYPE				=12;
	
	
	byte FILE_TYPE				=50;
	
	
	byte BOOLEAN_ARRAY_TYPE		=101;
	byte BYTE_ARRAY_TYPE			=102;
	byte SHORT_ARRAY_TYPE		=103;
	byte INTEGER_ARRAY_TYPE		=104;
	byte LONG_ARRAY_TYPE			=105;
	byte FLOAT_ARRAY_TYPE		=106;
	byte DOUBLE_ARRAY_TYPE		=107;
	byte STRING_ARRAY_TYPE		=108;
	byte DATE_ARRAY_TYPE			=109;
	byte TIME_ARRAY_TYPE			=110;
	byte DATETIME_ARRAY_TYPE		=111;
	byte CDO_ARRAY_TYPE			=112;
	byte EMPTY_CDO_ARRAY_TYPE	=115;  
	
	String	DATETIME_FORMAT_STRING		="yyyy-MM-dd HH:mm:ss";
	String	DATE_FORMAT_STRING			="yyyy-MM-dd";
	String	TIME_FORMAT_STRING			="HH:mm:ss";
	
	SimpleDateFormat DATETIME_FORMAT	=new SimpleDateFormat (DATETIME_FORMAT_STRING);
	SimpleDateFormat DATE_FORMAT		=new SimpleDateFormat (DATE_FORMAT_STRING);
	SimpleDateFormat TIME_FORMAT		=new SimpleDateFormat (TIME_FORMAT_STRING);	
	
	//新增的写法
	public enum Data{
		NONE(NONE_TYPE,"none"),
		BOOLEAN(BOOLEAN_TYPE,"boolean"),
		BYTE(BYTE_TYPE,"byte"),
		SHORT(SHORT_TYPE,"short"),
		INTEGER(INTEGER_TYPE,"integer"),
		LONG(LONG_TYPE,"long"),
		FLOAT(FLOAT_TYPE,"float"),
		DOUBLE(DOUBLE_TYPE,"double"),
		STRING(STRING_TYPE,"string"),
		DATE(DATE_TYPE,"date"),
		TIME(TIME_TYPE,"time"),
		DATETIME(DATETIME_TYPE,"dateTime"),
		
		CDO(CDO_TYPE,"cdo"),
		FILE(FILE_TYPE,"file"),
		
		BOOLEAN_ARRAY(BOOLEAN_ARRAY_TYPE,"boolean array "),
		BYTE_ARRAY(BYTE_ARRAY_TYPE,"byte array "),
		SHORT_ARRAY(SHORT_ARRAY_TYPE,"short array "),
		INTEGER_ARRAY(INTEGER_ARRAY_TYPE,"integer array "),
		LONG_ARRAY(LONG_ARRAY_TYPE,"long array "),
		FLOAT_ARRAY(FLOAT_ARRAY_TYPE,"float array "),
		DOUBLE_ARRAY(DOUBLE_ARRAY_TYPE,"double array "),
		STRING_ARRAY(STRING_ARRAY_TYPE,"string"),
		DATE_ARRAY(DATE_ARRAY_TYPE,"date array "),
		TIME_ARRAY(TIME_ARRAY_TYPE,"time array "),
		DATETIME_ARRAY(DATETIME_ARRAY_TYPE,"dateTime array "),
		CDO_ARRAY(CDO_ARRAY_TYPE,"cdo array ");
		
	     private byte dataType;
	     private String fieldType;
	     private Data(byte dataType,String fieldType){
	         this.dataType = dataType;
	         this.fieldType = fieldType;
	     }
		public byte getDataType() {
			return dataType;
		}
		public void setDataType(byte dataType) {
			this.dataType = dataType;
		}
		public String getFieldType() {
			return fieldType;
		}
		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}
	    
	}
}
