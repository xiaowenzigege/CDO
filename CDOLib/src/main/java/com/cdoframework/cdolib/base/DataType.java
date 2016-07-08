/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/base/ObjectExt.java,v 1.2 2008/03/22 13:32:28 Frank Exp $
 *
 * $Log: ObjectExt.java,v $
 * Revision 1.2  2008/03/22 13:32:28  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:43  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/27 12:28:05  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/15 09:35:37  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/09/30 00:11:39  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/08/23 10:22:12  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/07/24 13:40:40  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/07/24 13:36:57  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2006/09/02 14:43:40  Frank
 * �޸�DataTrans,�޸�Complex
 *
 *
 */

package com.cdoframework.cdolib.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.omg.CORBA.INTERNAL;

/**
 * Type为Object的实际类型的定义字符串，比如Byte,byte[],Date,Date[]
 * 其中Date,Time,DateTime实际为特定格式的String，但是Type不是String
 */
public interface DataType extends Serializable
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	int NONE_TYPE				=0;

	int BOOLEAN_TYPE			=1;
	int BYTE_TYPE				=2;
	int SHORT_TYPE				=3;
	int INTEGER_TYPE			=4;
	int LONG_TYPE				=5;
	int FLOAT_TYPE				=6;
	int DOUBLE_TYPE				=7;
	int STRING_TYPE				=8;
	int DATE_TYPE				=9;
	int TIME_TYPE				=10;
	int DATETIME_TYPE			=11;
	int CDO_TYPE				=12;
	
	
	int FILE_TYPE				=50;
	
	int BOOLEAN_ARRAY_TYPE		=101;
	int BYTE_ARRAY_TYPE			=102;
	int SHORT_ARRAY_TYPE		=103;
	int INTEGER_ARRAY_TYPE		=104;
	int LONG_ARRAY_TYPE			=105;
	int FLOAT_ARRAY_TYPE		=106;
	int DOUBLE_ARRAY_TYPE		=107;
	int STRING_ARRAY_TYPE		=108;
	int DATE_ARRAY_TYPE			=109;
	int TIME_ARRAY_TYPE			=110;
	int DATETIME_ARRAY_TYPE		=111;
	int CDO_ARRAY_TYPE			=112;
	int CDO_LIST_TYPE           =113;
	
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
		CDO_ARRAY(CDO_ARRAY_TYPE,"cdo array "),	
		CDO_LIST(CDO_LIST_TYPE,"cdo list ");		
	     private int dataType;
	     private String fieldType;
	     private Data(int dataType,String fieldType){
	         this.dataType = dataType;
	         this.fieldType = fieldType;
	     }
		public int getDataType() {
			return dataType;
		}
		public void setDataType(int dataType) {
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
