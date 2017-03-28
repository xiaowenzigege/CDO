/**
 * www.cdoforum.com 2007版权所有
 ** empty log message ***
 *
 *CDO 维护一个通用数据类型
 *key dot符号[即 .]，表示cdo的层级关系 
 *
 */

package com.cdoframework.cdolib.data.cdo;


import java.io.File;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nanoxml.XMLElement;

import org.apache.log4j.Logger;

import com.cdo.avro.protocol.AvroCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Utility;
/**
 * @author Frank
 * modify by @author KenelLiu
 * delete 2 list[vecTerm,objectExt]
 * change map<String,ObjectExt>  to  LinkedHashMap<String,Field>
 * change toXml  travel
 * add avro Serializable
 * add support file
 
 */
public class CDO implements java.io.Serializable
{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID=1L;
	
	private static final Logger logger = Logger.getLogger(CDO.class);

	int fileCount;//CDO里是否设置了文件，若设置了文件 则在网络传输需要特别处理 ,仅对最外层cdo有文件类型的进行处理
	//内部类,所有内部类在此声明----------------------------------------------------------------------------------
	final class FieldId
	{
		static final int SIMPLE=0;
		static final int MULTI_LEVEL=1;
		static final int ARRAY_ELEMENT=2;
		
		int nType;//0-简单类型，1-多级类型，2-数组元素
		
		String strMainFieldId;
		String strFieldId;
		String strIndexFieldId;
	};

	
	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	 void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private HashMap<String,Field> hmItem;
	
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法---------------------------------------------------------------------------------------------------
	protected void putItem(String strKey,Field objExt){
		hmItem.put(strKey,objExt);
	}
	
	//0-简单类型，1-多级，2-数组元素
	//如果FieldId错误，则返回null
	 FieldId parseFieldId(String strFieldId)
	{
		char[] chsFieldId=strFieldId.toCharArray();
		
		int nLength=chsFieldId.length;;
		
		FieldId fieldId=null;
		if(chsFieldId[nLength-1]==']')
		{//数组元素
			int nMatchIndex=Utility.findMatchedChar(nLength-1,chsFieldId);
			if(nMatchIndex<1)
			{
				return null;
			}
			
			fieldId=new FieldId();
			fieldId.nType=2;
			fieldId.strMainFieldId=strFieldId.substring(0,nMatchIndex);
			if(fieldId.strMainFieldId.length()==0)
			{
				return null;
			}
			fieldId.strIndexFieldId=strFieldId.substring(nMatchIndex+1,nLength-1);
			if(fieldId.strIndexFieldId.length()==0)
			{
				return null;
			}
			
			return fieldId;
		}
		
		for(int i=nLength-1;i>=0;i--)
		{
			char ch=chsFieldId[i];
			if(ch=='.')
			{//多级
				fieldId=new FieldId();
				fieldId.nType=1;
				fieldId.strMainFieldId=strFieldId.substring(0,i);
				if(fieldId.strMainFieldId.length()==0)
				{
					return null;
				}
				fieldId.strFieldId=strFieldId.substring(i+1);
				if(fieldId.strFieldId.length()==0)
				{
					return null;
				}

				return fieldId;
			}
		}
		
		//简单FieldId
		fieldId=new FieldId();
		fieldId.nType=0;
		fieldId.strFieldId=strFieldId;
		
		return fieldId;
	}

	//---------------------- 序列化方法    cdo2xml  cdo2Avro cdo2proto------------------------//
	/**
	 * 转换成avro
	 * @return
	 */
	public  AvroCDO toAvro(){		
		LinkedHashMap<CharSequence,ByteBuffer> fieldMap=new LinkedHashMap<CharSequence, ByteBuffer>();
		String prefixField="";	
		int maxLevel=toAvro(prefixField,fieldMap,0);			
		AvroCDO arvo=new AvroCDO();			
		arvo.setFields(fieldMap);						
		arvo.setLevel(maxLevel);		
		return arvo;
	}

	/**
	 * 供 CDOField 调用,返回最大层级
	 * @param prefixField
	 * @param fieldMap
	 * @param maxLevel
	 * @return
	 */
	int toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap,int maxLevel){
		Entry<String, Field> entry=null;
		int curLevel=0;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			curLevel=entry.getValue().toAvro(prefixField, fieldMap,maxLevel);
			if(curLevel>maxLevel)
				maxLevel=curLevel;
		}
		return maxLevel;
	}	
	
	/**
	 * 供非CDOField 字段调用，输出数据
	 * @param prefixField
	 * @param fieldMap
	 */
	 void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			entry.getValue().toAvro(prefixField, fieldMap);
		}
	}
	
	public GoogleCDO.CDOProto.Builder toProtoBuilder(){
		GoogleCDO.CDOProto.Builder cdoProto=GoogleCDO.CDOProto.newBuilder();
		String prefixField="";	
		int maxLevel=toProto(prefixField,cdoProto,0);
		cdoProto.setLevel(maxLevel);		
		return cdoProto;
	}
	
	
	/**
	 * 供 CDOField,CDOArrayField 调用,返回最大层级
	 * @param prefixField
	 * @param fieldMap
	 * @param maxLevel
	 * @return
	 */
	int toProto(String prefixField,GoogleCDO.CDOProto.Builder cdoProto,int maxLevel){
		Entry<String, Field> entry=null;
		int curLevel=0;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			curLevel=entry.getValue().toProto(prefixField,cdoProto,maxLevel);
			if(curLevel>maxLevel)
				maxLevel=curLevel;
		}
		return maxLevel;
	}	
	
	/**
	 * 供非CDOField  CDOArrayField 基础字段调用，输出数据
	 * @param prefixField
	 * @param fieldMap
	 */
	 void toProto(String prefixField,GoogleCDO.CDOProto.Builder cdoProto){
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			entry.getValue().toProto(prefixField, cdoProto);
		}
	}	
	
	 
	public String toXML()
	{
		StringBuilder strbXML=new StringBuilder(500);
		strbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");				
		strbXML.append("<CDO>");
		appendFieldXML(strbXML);
		strbXML.append("</CDO>");
		String strXML=strbXML.toString();
		
		return strXML;
	}


	 String toXML(StringBuilder strbXML)
	{				
		strbXML.append("<CDO>");
		appendFieldXML(strbXML);
		strbXML.append("</CDO>");
		
		return strbXML.toString();
	}
	
	 private void appendFieldXML(StringBuilder strbXML){
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();		
			entry.getValue().toXML(strbXML);
		}
	}
	
	 
	public String toXMLLog()
	{
		StringBuilder strbXML=new StringBuilder(500);
		strbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");				
		strbXML.append("<CDO>");
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			entry.getValue().toXMLLog(strbXML);
		}				
		strbXML.append("</CDO>");				
		return strbXML.toString();
	}
	
	
	public String toXMLWithIndent(){
		StringBuilder strbXML=new StringBuilder(500);
		strbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		strbXML.append("<CDO>\r\n");
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			entry.getValue().toXMLWithIndent(1,strbXML);
		}			
		strbXML.append("</CDO>\r\n");	
		
		return strbXML.toString();
	}
	
	void toXMLWithIndent(String strIndent,StringBuilder strbXML){
		strbXML.append(strIndent).append("<CDO>\r\n");		
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			entry.getValue().toXMLWithIndent(1+strIndent.length(),strbXML);
		}			
		strbXML.append(strIndent).append("</CDO>\r\n");
	}

	/**
	 * 把CDO对象转换成JSON格式的字符串
	 * 
	 * @param cdo
	 * @return JSON格式的字符串
	 * 
	 */
	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer("{");
		
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			str_JSON.append(entry.getValue().toJSON());
		}
		
		// ugly 方法去掉最后一个","
		int _lastComma=str_JSON.lastIndexOf(",");
		int _length=str_JSON.length();
		if(_lastComma==_length-1)
		{
			str_JSON.replace(_lastComma,_lastComma+1,"");
		}

		str_JSON.append("}");
		return str_JSON.toString();
	}

	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer("{");
		
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			str_JSON.append(entry.getValue().toJSONString());
		}
		// ugly 方法去掉最后一个","
		int _lastComma=str_JSON.lastIndexOf(",");
		int _length=str_JSON.length();
		if(_lastComma==_length-1)
		{
			str_JSON.replace(_lastComma,_lastComma+1,"");
		}

		str_JSON.append("}");
		return str_JSON.toString();
	}
	

//-----------------------------xml2CDO 反序列化方法    avro2CDO 参看AvroCDODeserialize-----------------------------------------------//
	
	public static CDO fromXML(String strXML)
	{
		CDO cdo=new CDO();
		xmlToCDO(strXML,cdo);			
		return cdo;
	}

    public void copyFrom(String strCDOXML)
    {
		xmlToCDO(strCDOXML,this);
    }

    
	public static void xmlToCDO(String strXML,CDO cdoOutPut)
	{
		XMLElement xmlNode=new XMLElement();
		xmlNode.parseString(strXML);
		
		ParseXmlCDO.xml2CDO(cdoOutPut, xmlNode, true);
	}	
	
	
    public CDO clone()
    {
    	CDO cdo=new CDO();
    	copy(this,cdo);
    	return cdo;
    }	
	
    public void copyFrom(CDO cdoSource)
    {
    	copy(cdoSource, this);
    }
    
    private void copy(CDO src,CDO dst){
    	for(Iterator<Map.Entry<String,Field>> iterator=src.iterator();iterator.hasNext();){
    		Entry<String,Field> entry=iterator.next();
    		DataBufferUtil.setField(dst, entry.getKey(), entry.getValue());    		
    	}
    }
    
   
    /**
     * 根据key,获取Field
     * @param fieldId
     * @param cdoRoot
     * @return
     */
    private Field getObject(FieldId fieldId,CDO cdoRoot)
	{
    	if(fieldId.nType==FieldId.SIMPLE)
    	{//简单类型    		
    		return this.hmItem.get(fieldId.strFieldId);
    	}

    	if(fieldId.nType==FieldId.MULTI_LEVEL)
    	{//多级FieldId
    		FieldId fieldIdMain=this.parseFieldId(fieldId.strMainFieldId);
    		if(fieldIdMain==null)
    		{
    			throw new RuntimeException("Invalid FieldId "+fieldId.strMainFieldId);
    		}
    		if(fieldIdMain.nType==FieldId.ARRAY_ELEMENT)
    		{
    			Field objExt=this.getObject(this.parseFieldId(fieldIdMain.strMainFieldId),cdoRoot);
    			if(objExt==null)
    			{
    				return null;
    			}
    			int nIndex=this.getIndexValue(fieldIdMain.strIndexFieldId,cdoRoot);
//    			return ((CDO)objExt.getValueAt(nIndex)).getObject(fieldId.strFieldId);    			
    			return (((CDOArrayField)objExt).getValueAt(nIndex)).getObject(fieldId.strFieldId);
    		}
    		Field cdoMainField=getObject(fieldIdMain,cdoRoot);
    		if(cdoMainField==null)
    		{
    			return null;
    		}
    		if(cdoMainField.getType().getDataType()>=DataType.BOOLEAN_ARRAY_TYPE && fieldId.strFieldId.equalsIgnoreCase("length")==true)
    		{//是数组类型
//    			return new ObjectExt(DataType.INTEGER_TYPE,cdoMainField.getLength());
    			return new IntegerField(((ArrayField)cdoMainField).getLength());
    		}
//    		return ((CDO)cdoMainField.getValue()).hmItem.get(fieldId.strFieldId);
    		return (((CDOField)cdoMainField).getValue()).hmItem.get(fieldId.strFieldId);
    	}

    	//数组元素
		int nIndex=this.getIndexValue(fieldId.strIndexFieldId,cdoRoot);
		FieldId fieldIdMain=this.parseFieldId(fieldId.strMainFieldId);
		Field objExt=this.getObject(fieldIdMain,cdoRoot);	
//		return objExt.getValueAtExt(nIndex);
		return getValueAtExt(objExt,nIndex);
	}

    //根据带路径的FieldId获取对应的Value
    private int getIndexValue(String strIndex,CDO cdoRoot)
    {
		int nIndex=0;
		strIndex=strIndex.trim();
		if(strIndex.charAt(0)>='0' &&strIndex.charAt(0)<='9')
		{//下标是数字，直接使用
			nIndex=Integer.parseInt(strIndex);
		}
		else
		{//下标为字段Id，获取字段值当作索引
			Object objIndex=cdoRoot.getObjectValue(strIndex);
			if(objIndex instanceof Byte)
			{
				nIndex=((Byte)objIndex).byteValue();
			}
			else if(objIndex instanceof Short)
			{
				nIndex=((Short)objIndex).shortValue();
			}
			else if(objIndex instanceof Integer)
			{
				nIndex=((Integer)objIndex).intValue();
			}
			else if(objIndex instanceof Long)
			{
				nIndex=(int)((Long)objIndex).longValue();
			}
			else
			{
				throw new RuntimeException("Invalid array index");
			}
		}
		
		return nIndex;
    } 
    /**
     *  获取数组 指定index的值
     * @param field
     * @param nIndex
     * @return
     */
    private Field getValueAtExt(Field field,int nIndex)
	{
		switch(field.getType().getDataType())
		{
			case DataType.BOOLEAN_ARRAY_TYPE:
			{
				return new BooleanField(((BooleanArrayField)field).getValueAt(nIndex));
			}
			case DataType.BYTE_ARRAY_TYPE:
			{
				return new ByteField(((ByteArrayField)field).getValueAt(nIndex));
			}
			case DataType.SHORT_ARRAY_TYPE:
			{
				return new ShortField(((ShortArrayField)field).getValueAt(nIndex));
			}
			case DataType.INTEGER_ARRAY_TYPE:
			{
				return new IntegerField(((IntegerArrayField)field).getValueAt(nIndex));
			}
			case DataType.LONG_ARRAY_TYPE:
			{
				return  new LongField(((LongArrayField)field).getValueAt(nIndex));
			}
			case DataType.FLOAT_ARRAY_TYPE:
			{
				return new FloatField(((FloatArrayField)field).getValueAt(nIndex));
			}
			case DataType.DOUBLE_ARRAY_TYPE:
			{
				return new DoubleField(((DoubleArrayField)field).getValueAt(nIndex));
			}
			case DataType.STRING_ARRAY_TYPE:
			{
				return new StringField("",((StringArrayField)field).getValueAt(nIndex));
			}
			case DataType.DATE_ARRAY_TYPE:
			{
				return new DateField("",((DateArrayField)field).getValueAt(nIndex));
			}
			case DataType.TIME_ARRAY_TYPE:
			{
				return new TimeField("",((TimeArrayField)field).getValueAt(nIndex));
			}
			case DataType.DATETIME_ARRAY_TYPE:
			{
				return new DateTimeField("",((DateTimeArrayField)field).getValueAt(nIndex));
			}
			case DataType.CDO_ARRAY_TYPE:
			{
				return new CDOField(((CDOArrayField)field).getValueAt(nIndex));
			}
		}
		throw new RuntimeException(field.getType().getFieldType()+" Type cast failed");	
	}
    
    /**
     * 根据 strFieldId 获取Field
     * @param strFieldId 
     * @return
     */
    public Field getObject(String strFieldId)
    {
    	Field objExt=this.hmItem.get(strFieldId);
    	if(objExt!=null)
    	{//直接子节点
    		return objExt;
    	}
    	
    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{//无效FieldId
    		logger.warn("Invalid FieldId "+strFieldId+" cdo:"+this.toJSONString());
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	objExt=getObject(fieldId,this);
    	if(objExt==null)
    	{
    		logger.warn("Invalid FieldId "+strFieldId+" cdo:"+this.toJSONString());
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	return objExt;
    }

    /**
     * 根据 strFieldId 获取Field的value值
     * @param strFieldId
     * @return
     */
    public Object getObjectValue(String strFieldId)
    {
    	Field objExt=this.getObject(strFieldId);
    	return objExt.getObjectValue();
	}

    //----------获取指定类型Field 的value------------------------//
    public boolean getBooleanValue(String strFieldId)
    {
    	BooleanField objExt=(BooleanField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public byte getByteValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getByte(field);
    }

    public short getShortValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getShort(field);
    }

    public int getIntegerValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getInteger(field);
    }

    public long getLongValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getLong(field);
    }

    public float getFloatValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getFloat(field);
    }

    public double getDoubleValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getDouble(field);
    }

    public String getStringValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getString(field);
    }

    public File getFileValue(String strFieldId)
    {
    	FileField objExt=(FileField)this.getObject(strFieldId);
    	return objExt.getValue();
    }
    public String getDateValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getDate(field);
    }

    public String getTimeValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getTime(field);
    }

    public String getDateTimeValue(String strFieldId)
    {
    	Field field=this.getObject(strFieldId);
    	return DataBufferUtil.getDateTime(field);
    }

    public CDO getCDOValue(String strFieldId)
    {
    	CDOField objExt=(CDOField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public boolean[] getBooleanArrayValue(String strFieldId)
    {
    	BooleanArrayField objExt=(BooleanArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public byte[] getByteArrayValue(String strFieldId)
    {
    	ByteArrayField objExt=(ByteArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public short[] getShortArrayValue(String strFieldId)
    {
    	ShortArrayField objExt=(ShortArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public int[] getIntegerArrayValue(String strFieldId)
    {
    	IntegerArrayField objExt=(IntegerArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public long[] getLongArrayValue(String strFieldId)
    {
    	LongArrayField objExt=(LongArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public float[] getFloatArrayValue(String strFieldId)
    {
    	FloatArrayField objExt=(FloatArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public double[] getDoubleArrayValue(String strFieldId)
    {
    	DoubleArrayField objExt=(DoubleArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public String[] getStringArrayValue(String strFieldId)
    {
    	StringArrayField objExt=(StringArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public String[] getDateArrayValue(String strFieldId)
    {
    	DateArrayField objExt=(DateArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public String[] getTimeArrayValue(String strFieldId)
    {
    	TimeArrayField objExt=(TimeArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public String[] getDateTimeArrayValue(String strFieldId)
    {
    	DateTimeArrayField objExt=(DateTimeArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public CDO[] getCDOArrayValue(String strFieldId)
    {
    	CDOArrayField objExt=(CDOArrayField)this.getObject(strFieldId);
    	return objExt.getValue().toArray(new CDO[objExt.getValue().size()]);
    	
    }
    
    public List<CDO> getCDOListValue(String strFieldId)
    {
    	CDOArrayField objExt=(CDOArrayField)this.getObject(strFieldId);
    	return objExt.getValue();
    }   

    
    //----------设置 指定类型Field的name和value------------------------//
   
    public void setBooleanValue(String strFieldId,boolean bValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new BooleanField(fieldId.strFieldId,bValue);
   		this.setObjectValue(fieldId,DataType.BOOLEAN_TYPE,bValue,field,this);
    }
  
    public void setByteValue(String strFieldId,byte byValue)
    {
    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new ByteField(fieldId.strFieldId,byValue);
		this.setObjectValue(fieldId,DataType.BYTE_TYPE,byValue,field,this);
    }
    
    
    public void setShortValue(String strFieldId,short shValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}    	
    	
    	Field field=new ShortField(fieldId.strFieldId,shValue);
		this.setObjectValue(fieldId,DataType.SHORT_TYPE,shValue,field,this);
    }

    public void setIntegerValue(String strFieldId,int nValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new IntegerField(fieldId.strFieldId,nValue);
		this.setObjectValue(fieldId,DataType.INTEGER_TYPE,nValue,field,this);
    }

    public void setLongValue(String strFieldId,long lValue)
    {
    	
    	
    	FieldId fieldId=this.parseFieldId(strFieldId);

    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new LongField(fieldId.strFieldId,lValue);
		this.setObjectValue(fieldId,DataType.LONG_TYPE,lValue,field,this);
    }

    public void setFloatValue(String strFieldId,float fValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new FloatField(fieldId.strFieldId,fValue);
		this.setObjectValue(fieldId,DataType.FLOAT_TYPE,fValue,field,this);
    }

    public void setDoubleValue(String strFieldId,double dValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new DoubleField(fieldId.strFieldId,dValue);
		this.setObjectValue(fieldId,DataType.DOUBLE_TYPE,dValue,field,this);
    }

    public void setStringValue(String strFieldId,String strValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new StringField(fieldId.strFieldId,strValue);
		this.setObjectValue(fieldId,DataType.STRING_TYPE,strValue,field,this);
    }
    /**
     * 
     * @param strFieldId
     * @param file
     */
    public void setFileValue(String strFieldId,File file)
    {
    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new FileField(fieldId.strFieldId,file);
		this.setObjectValue(fieldId,DataType.FILE_TYPE,file,field,this);
		if(fieldId.nType==FieldId.SIMPLE)
			fileCount++;
    }
    
    public void setDateValue(String strFieldId,String strValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new DateField(fieldId.strFieldId,strValue);
		this.setObjectValue(fieldId,DataType.DATE_TYPE,strValue,field,this);
    }

    public void setTimeValue(String strFieldId,String strValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new TimeField(fieldId.strFieldId,strValue);
		this.setObjectValue(fieldId,DataType.TIME_TYPE,strValue,field,this);
    }

    public void setDateTimeValue(String strFieldId,String strValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new DateTimeField(fieldId.strFieldId,strValue);
		this.setObjectValue(fieldId,DataType.DATETIME_TYPE,strValue,field,this);
    }

    public void setCDOValue(String strFieldId,CDO cdoValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new CDOField(fieldId.strFieldId,cdoValue);
		this.setObjectValue(fieldId,DataType.CDO_TYPE,cdoValue,field,this);
    }

    public void setBooleanArrayValue(String strFieldId,boolean[] bsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new BooleanArrayField(fieldId.strFieldId,bsValue);
    		this.setObjectValue(fieldId,DataType.BOOLEAN_ARRAY_TYPE,bsValue,field,this);
    	}
    }

    public void setByteArrayValue(String strFieldId,byte[] bysValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new ByteArrayField(fieldId.strFieldId,bysValue);
    		this.setObjectValue(fieldId,DataType.BYTE_ARRAY_TYPE,bysValue,field,this);
    	}
    }

    public void setShortArrayValue(String strFieldId,short[] shsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new ShortArrayField(fieldId.strFieldId,shsValue);
    		this.setObjectValue(fieldId,DataType.SHORT_ARRAY_TYPE,shsValue,field,this);
    	}
    }

    public void setIntegerArrayValue(String strFieldId,int[] nsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new IntegerArrayField(fieldId.strFieldId,nsValue); 
    		this.setObjectValue(fieldId,DataType.INTEGER_ARRAY_TYPE,nsValue,field,this);
    	}
    }
    public void setFloatArrayValue(String strFieldId,float[] fsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new FloatArrayField(fieldId.strFieldId,fsValue); 
    		this.setObjectValue(fieldId,DataType.FLOAT_ARRAY_TYPE,fsValue,field,this);
    	}
    }
    public void setDoubleArrayValue(String strFieldId,double[] dblsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new DoubleArrayField(fieldId.strFieldId,dblsValue); 
    		this.setObjectValue(fieldId,DataType.DOUBLE_ARRAY_TYPE,dblsValue,field,this);
    	}
    }
    public void setLongArrayValue(String strFieldId,long[] lsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new LongArrayField(fieldId.strFieldId,lsValue); 
    		this.setObjectValue(fieldId,DataType.LONG_ARRAY_TYPE,lsValue,field,this);
    	}
    }
    public void setStringArrayValue(String strFieldId,String[] strsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new StringArrayField(fieldId.strFieldId,strsValue);  
    		this.setObjectValue(fieldId,DataType.STRING_ARRAY_TYPE,strsValue,field,this);
    	}
    }

    public void setDateArrayValue(String strFieldId,String[] strsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new DateArrayField(fieldId.strFieldId,strsValue);  
    		this.setObjectValue(fieldId,DataType.DATE_ARRAY_TYPE,strsValue,field,this);
    	}
    }

    public void setTimeArrayValue(String strFieldId,String[] strsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new TimeArrayField(fieldId.strFieldId,strsValue);  
    		this.setObjectValue(fieldId,DataType.TIME_ARRAY_TYPE,strsValue,field,this);
    	}
    }

    public void setDateTimeArrayValue(String strFieldId,String[] strsValue)
    {
    	

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new DateTimeArrayField(fieldId.strFieldId,strsValue);     		
    		this.setObjectValue(fieldId,DataType.DATETIME_ARRAY_TYPE,strsValue,field,this);
    	}
    }

    public void setCDOArrayValue(String strFieldId,CDO[] cdosValue)
    {    	
    	this.setCDOListValue(strFieldId, Arrays.asList(cdosValue));
    }
    
    public void setCDOListValue(String strFieldId, List<CDO> cdosValue) {
    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new CDOArrayField(fieldId.strFieldId,cdosValue); 
    		this.setObjectValue(fieldId,DataType.CDO_ARRAY_TYPE,cdosValue,field,this);
    	}		
	}
  
    
    /**
     *  构造结构，保存数值到指定位置
     * @param fieldId
     * @param nType
     * @param objValue
     * @param field
     * @param cdoRoot
     */
   void setObjectValue(FieldId fieldId,int nType,Object objValue,Field field,CDO cdoRoot)
	{
   	if(fieldId.nType==FieldId.SIMPLE)
   	{
//   	this.putItem(fieldId.strFieldId,new ObjectExt(nType,objValue));
   		this.putItem(fieldId.strFieldId,field);
   	}
   	else if(fieldId.nType==FieldId.MULTI_LEVEL)
   	{
   		CDO cdoMain=this.getCDOValue(fieldId.strMainFieldId);    	
//   	cdoMain.setField(fieldId.strFieldId,new ObjectExt(nType,objValue));
   		cdoMain.putItem(fieldId.strFieldId, field);
   	}
   	else
   	{//Array Element    		
   		FieldId fieldIdMain=this.parseFieldId(fieldId.strMainFieldId);
   		if(fieldIdMain==null)
   		{
   			throw new RuntimeException("Invalid FieldId "+fieldId.strFieldId);
   		}
   		
   		Field arrField= null;
   		int nIndex =-1;
		arrField=this.getObject(fieldIdMain,this);
	    if(arrField==null)
	    {
			throw new RuntimeException("FieldId "+fieldId.strMainFieldId+" not exist");
	    }
		nIndex=this.getIndexValue(fieldId.strIndexFieldId,cdoRoot);	
		
   		switch(arrField.getType().getDataType())
   		{
   			case DataType.BOOLEAN_ARRAY_TYPE:
   			{
   				((BooleanArrayField)arrField).setValueAt(nIndex,(Boolean)objValue);
   				break;
   			}
   			case DataType.BYTE_ARRAY_TYPE:
   			{
   				((ByteArrayField)arrField).setValueAt(nIndex,(Byte)objValue);
   				break;
   			}
   			case DataType.SHORT_ARRAY_TYPE:
   			{
   				((ShortArrayField)arrField).setValueAt(nIndex,(Short)objValue);
   				break;
   			}
   			case DataType.INTEGER_ARRAY_TYPE:
   			{
   				((IntegerArrayField)arrField).setValueAt(nIndex,(Integer)objValue);
   				break;
   			}
   			case DataType.LONG_ARRAY_TYPE:
   			{
   				((LongArrayField)arrField).setValueAt(nIndex,(Long)objValue);
   				break;
   			}
   			case DataType.STRING_ARRAY_TYPE:
   			{
   				((StringArrayField)arrField).setValueAt(nIndex,(String)objValue);
   				break;
   			}
   			case DataType.DATE_ARRAY_TYPE:
   			{
   				((DateArrayField)arrField).setValueAt(nIndex,(String)objValue);
   				break;
   			}
   			case DataType.TIME_ARRAY_TYPE:
   			{
   				((TimeArrayField)arrField).setValueAt(nIndex,(String)objValue);
   				break;
   			}
   			case DataType.DATETIME_ARRAY_TYPE:
   			{
   				((DateTimeArrayField)arrField).setValueAt(nIndex,(String)objValue);
   				break;
   			}
	   		case DataType.CDO_ARRAY_TYPE:
	   		{
	   				((CDOArrayField)arrField).setValueAt(nIndex,(CDO)objValue);
	   				break;
	   		}
   		}
   	}
	}
    
    /**
     * 检查一个字段是否存在
     * 
     * @param strFieldId
     * @return
     */
    public boolean exists(String strFieldId)
    {
    	try
		{
			return checkField(strFieldId);
		}
		catch(Exception e)
		{
			return false;
		}
    }
    
    /**
     * 提供非抛异常的方式检查
     * 
     * @param strFieldId
     * @return
     */
    private boolean checkField(String strFieldId)
    {
    	Field objExt=this.hmItem.get(strFieldId);
    	if(objExt!=null)
    	{//直接子节点
    		return true;
    	}
    	
    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
    		return false;
    	}
    	
    	objExt=getObject(fieldId,this);
    	return objExt!=null;
    }
    
    /**
     * 判断CDO容器中是否有对象
     * CDO容器中如果没有任何对象则返回true
     * CDO容器中有对象,则返回false
     * @return true:
     */
    public boolean isEmpty()
    {
    	if(this.hmItem.size()==0)
    	{
    		return true;
    	}
    	return false;
    }
    
    /**
     * 判断CDO容器中指定对象是否为空
     * 如果指定对象不存在,则返回true;如果指定对象也是个容器且容器为空,则返回true;其它情况为false.
     * @param strFieldId
     * @return
     */
    public boolean isEmpty(String strFieldId)
    {
    	Field objExt = null;
    	try
		{    		
    		objExt = getObject(strFieldId);  
		}catch(Exception e)
		{//不存在
			return true;
		}
		if(objExt==null)
		{
			return true;
		}
		try
		{
			int length=0;
			if(objExt.getType().getDataType()>=DataType.BOOLEAN_ARRAY_TYPE){
				length=((ArrayField)objExt).getLength();
			}
			return length==0;
			
		}catch(Exception e)
		{//有值,但不是数据组类型
			return false;
		}
    }
        
   
    public Field getField(String strFieldId)
    {
    	return this.getObject(strFieldId);
    }
    
    
    public void setObjectExt(String strFieldId, Field field) throws RuntimeException
    {
    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
   		this.setObjectValue(fieldId,field.getType().getDataType(),field.getObjectValue(),field,this);
 
    }
	
	public void remove(String key)
	{
		Field objExt=hmItem.remove(key);
		if(objExt.getType().getDataType()==DataType.FILE_TYPE && !key.contains(".")){
			fileCount--;
		}
	}

	public void clear(){
		hmItem.clear();
	}
	public Set<String> keySet()
	{
		return hmItem.keySet();
	}
	
	private  void release(){
		hmItem.clear();
		hmItem=null;		
	}
	
	public static void release(CDO cdo){
		if(cdo!=null)
			cdo.release();
		cdo=null;		
	}
	
	public int size(){
		return hmItem.size();
	}
	public int getSerialFileCount(){
		return this.fileCount;
	}
	
	public Set<Map.Entry<String,Field>> entrySet(){
		return this.hmItem.entrySet();
	}
    
    public Iterator<Map.Entry<String,Field>> iterator(){
    	return this.hmItem.entrySet().iterator();
    }
    
 
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CDO(){
		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		hmItem	=new LinkedHashMap<String,Field>();		
	}
	
	/**
	 * toString 采用类似 JSON格式
	 */
	public String toString()
	{
		StringBuffer str_String=new StringBuffer("{");
		
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
			str_String.append(entry.getValue().toString());
		}		
		int _lastComma=str_String.lastIndexOf(",");
		int _length=str_String.length();
		if(_lastComma==_length-1)
		{
			str_String.replace(_lastComma,_lastComma+1,"");
		}
		str_String.append("}");
		return str_String.toString();
	}	
	
}
