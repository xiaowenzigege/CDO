/**
 * www.cdoforum.com 2007版权所有
 ** empty log message ***
 *
 *CDO 维护一个通用数据类型
 *key 点符号[即 .]，表示cdo的层级关系 
 *
 */

package com.cdoframework.cdolib.data.cdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nanoxml.XMLElement;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.log4j.Logger;

import com.cdo.avro.schema.ArvoMain;
import com.cdo.avro.schema.AvroCDO;
import com.cdo.avro.serialize.AvroCDODeserialize;
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
public class CDO implements Serializable
{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID=1L;
	
	private static final Logger logger = Logger.getLogger(CDO.class);

	private  int fileCount;//CDO里是否设置了文件，若设置了文件 则在网络传输需要特别处理 ,仅对最外层cdo有文件类型的进行处理
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

	//序列化方法-------------------------------------------------------------------------------------------------

	protected void fromXML(XMLElement nodeCDO,boolean isRootNode)
	{
		Iterator enumNodes=nodeCDO.enumerateChildren();

		while(enumNodes.hasNext())
		{
			XMLElement node=(XMLElement)enumNodes.next();

			String strTag=node.getName();
			if(strTag.equals("BF"))//BooleanField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				boolean bValue=false;
				if(strValue.equalsIgnoreCase("true"))
				{
					bValue=true;
				}
				
//				ObjectExt objExt=new ObjectExt(DataType.BOOLEAN_TYPE,new Boolean(bValue));
				putItem(strName,new BooleanField(strName,bValue));
			}
			else if(strTag.equals("BYF"))//ByteField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.BYTE_TYPE,new Byte(strValue));				
				putItem(strName,new ByteField(strName, new Byte(strValue)));
			}
			else if(strTag.equals("SF"))//ShortField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.SHORT_TYPE,new Short(strValue));
				putItem(strName,new ShortField(strName, new Short(strValue)));
			}
			else if(strTag.equals("NF"))//IntegerField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.INTEGER_TYPE,new Integer(strValue));
				putItem(strName,new IntegerField(strName, new Integer(strValue)));
			}
			else if(strTag.equals("LF"))//LongField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.LONG_TYPE,new Long(strValue));
				putItem(strName,new LongField(strName, new Long(strValue)));
			}
			else if(strTag.equals("FF"))//FloatField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.FLOAT_TYPE,new Float(strValue));
				putItem(strName,new FloatField(strName, new Float(strValue)));
			}
			else if(strTag.equals("DBLF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.DOUBLE_TYPE,new Double(strValue));
				putItem(strName,new DoubleField(strName, new Double(strValue)));
			}
			else if(strTag.equals("STRF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.STRING_TYPE,strValue);
				putItem(strName,new StringField(strName, strValue));
			}
			else if(strTag.equals("DF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.DATE_TYPE,strValue);
				putItem(strName,new DateField(strName, strValue));
			}
			else if(strTag.equals("TF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.TIME_TYPE,strValue);
				putItem(strName,new TimeField(strName, strValue));
			}
			else if(strTag.equals("DTF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.DATETIME_TYPE,strValue);
				putItem(strName,new DateTimeField(strName, strValue));
			}
			else if(strTag.equals("CDOF"))
			{
				String strName	=node.getStringAttribute("N");
				CDO cdoValue	=new CDO();
				cdoValue.fromXML((XMLElement)node.getChildren().get(0),false);
//				ObjectExt objExt=new ObjectExt(DataType.CDO_TYPE,cdoValue);
				putItem(strName,new CDOField(strName, cdoValue));
			}else if(strTag.equals("FILE"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
//				ObjectExt objExt=new ObjectExt(DataType.FILE_TYPE,new File(strValue));
				putItem(strName,new FileField(strName, new File(strValue)));
			   //仅对最顶级CDO里的file 做字节流传输,嵌套里的文件在网络传输中不处理
				if(isRootNode)
					fileCount++;
			}
			else if(strTag.equals("BAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				boolean[] bsValue=new boolean[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					if(strValueArr[i].equalsIgnoreCase("true"))
					{
						bsValue[i]=true;	
					}
				}
				
//				ObjectExt objExt=new ObjectExt(DataType.BOOLEAN_ARRAY_TYPE,bsValue);
				putItem(strName,new BooleanArrayField(strName, bsValue));
			}
			else if(strTag.equals("BYAF"))
			{//				
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				//分成四等份，循环1/4 即可完成原来遍历所有数据处理。
				byte[] bysValue=new byte[strValueArr.length];
				int length=bysValue.length;				
				int mid=length/2;
				int quarter=mid/2;
				int j=quarter+1;
				int m=mid+1;
				int quarter3=mid+quarter;
				int n=mid+quarter+1;
							
				for(int i=0;i<=quarter ;i++){
					try{
						bysValue[i]=Byte.parseByte(strValueArr[i]);				
						if(j<=mid){
							bysValue[j]=Byte.parseByte(strValueArr[j]);
							j++;
						}
						if(m<=quarter3){
							bysValue[m]=Byte.parseByte(strValueArr[m]);
							m++;
						}
						if(n<length){
							bysValue[n]=Byte.parseByte(strValueArr[n]);
							n++;
						}				
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected short value "+strValueArr[i]+" under "+strTag);
					}					
				}			
				
//				ObjectExt objExt=new ObjectExt(DataType.BYTE_ARRAY_TYPE,bysValue);
				putItem(strName,new ByteArrayField(strName, bysValue));
			}
			else if(strTag.equals("SAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				short[] shsValue=new short[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						shsValue[i]=Short.parseShort(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected short value "+strValueArr[i]+" under "+strTag);
					}
				}			
//				ObjectExt objExt=new ObjectExt(DataType.SHORT_ARRAY_TYPE,shsValue);
				putItem(strName,new ShortArrayField(strName,shsValue));
			}
			else if(strTag.equals("NAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				int[] nsValue=new int[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						nsValue[i]=Integer.parseInt(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected int value "+strValueArr[i]+" under "+strTag);
					}
				}			
//				ObjectExt objExt=new ObjectExt(DataType.INTEGER_ARRAY_TYPE,nsValue);
				putItem(strName,new IntegerArrayField(strName, nsValue));
			}
			else if(strTag.equals("LAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				long[] lsValue=new long[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						lsValue[i]=Long.parseLong(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected long value "+strValueArr[i]+" under "+strTag);
					}
				}			
//				ObjectExt objExt=new ObjectExt(DataType.LONG_ARRAY_TYPE,lsValue);
				putItem(strName,new LongArrayField(strName, lsValue));
			}
			else if(strTag.equals("FAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				float[] fsValue=new float[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						fsValue[i]=Float.parseFloat(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected float value "+strValueArr[i]+" under "+strTag);
					}
				}			
//				ObjectExt objExt=new ObjectExt(DataType.FLOAT_ARRAY_TYPE,fsValue);
				putItem(strName,new FloatArrayField(strName,fsValue));
				
			}
			else if(strTag.equals("DBLAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
				double[] dblsValue=new double[strValueArr.length];
				for(int i=0;i<strValueArr.length;i++)
				{
					try{
						dblsValue[i]=Double.parseDouble(strValueArr[i]);
					}catch(Exception ex)
					{
						throw new RuntimeException("Parse xml error: unexpected float value "+strValueArr[i]+" under "+strTag);
					}
				}			
//				ObjectExt objExt=new ObjectExt(DataType.DOUBLE_ARRAY_TYPE,dblsValue);
				putItem(strName,new DoubleArrayField(strName, dblsValue));
			}
			else if(strTag.equals("STRAF"))
			{
				String strName	=node.getStringAttribute("N");
				Iterator enumItems	=node.enumerateChildren();

				String[] strsValue=new String[node.countChildren()];
				int nIndex=0;
				while(enumItems.hasNext())
				{
					XMLElement subNode=(XMLElement)enumItems.next();
					String strSubNodeTag=subNode.getName();
					if(strSubNodeTag.equals("STR")==false)
					{
						throw new RuntimeException("Parse xml error: unexpected Tag name "+strSubNodeTag+" under "+strTag);
					}
					strsValue[nIndex]=subNode.getContent();
					nIndex++;
				}
//				ObjectExt objExt=new ObjectExt(DataType.STRING_ARRAY_TYPE,strsValue);
				putItem(strName,new StringArrayField(strName, strsValue));
			}
			else if(strTag.equals("DAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strValueArr=null;
				if(strValue.length()==0)
				{
					strValueArr=new String[0];
				}
				else
				{
					strValueArr=strValue.split(",");
				}
//				ObjectExt objExt=new ObjectExt(DataType.DATE_ARRAY_TYPE,strValueArr);
				putItem(strName,new DateArrayField(strName, strValueArr));
			}
			else if(strTag.equals("TAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strsValue=null;
				if(strValue.length()==0)
				{
					strsValue=new String[0];
				}
				else
				{
					strsValue=strValue.split(",");
				}
//				ObjectExt objExt=new ObjectExt(DataType.TIME_ARRAY_TYPE,strsValue);
				putItem(strName,new TimeArrayField(strName, strsValue));
			}
			else if(strTag.equals("DTAF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				String[] strsValue=null;
				if(strValue.length()==0)
				{
					strsValue=new String[0];
				}
				else
				{
					strsValue=strValue.split(",");
				}
//				ObjectExt objExt=new ObjectExt(DataType.DATETIME_ARRAY_TYPE,strsValue);
				putItem(strName,new DateTimeArrayField(strName, strsValue) );
			}
			else if(strTag.equals("CDOAF"))
			{
				String strName	=node.getStringAttribute("N");
				Iterator enumItems	=node.enumerateChildren();

				CDO[] cdosValue=new CDO[node.countChildren()];
				int nIndex=0;
				while(enumItems.hasNext())
				{
					XMLElement subNode=(XMLElement)enumItems.next();
					String strSubNodeTag=subNode.getName();
					if(strSubNodeTag.equals("CDO")==false)
					{
						throw new RuntimeException("Parse xml error: unexpected Tag name "+strSubNodeTag+" under "+strTag);
					}
					CDO cdoValue=new CDO();
					cdoValue.fromXML(subNode,false);
					cdosValue[nIndex]=cdoValue;
					nIndex++;
				}
//				ObjectExt objExt=new ObjectExt(DataType.CDO_ARRAY_TYPE,cdosValue);
				putItem(strName,new CDOArrayField(strName, cdosValue));
			}
			else
			{
				throw new RuntimeException("Parse xml error: unexpected Tag name ["+strTag+"]");
			}
		}
	}

	public static CDO fromXML(String strXML)
	{
		XMLElement xmlNode=new XMLElement();
		xmlNode.parseString(strXML);

		CDO cdo=new CDO();
		cdo.fromXML(xmlNode,true);

		return cdo;
	}

	public static void xmlToCDO(String strXML,CDO cdoOutPut)
	{
		XMLElement xmlNode=new XMLElement();
		xmlNode.parseString(strXML);
		cdoOutPut.fromXML(xmlNode,true);		
	}	
	
	public  AvroCDO toAvro(){
		Map<CharSequence,ByteBuffer> fieldMap=new LinkedHashMap<CharSequence, ByteBuffer>();
		String prefixField="";
		int maxLevel=toAvro(prefixField,fieldMap,0);
		AvroCDO arvo=new AvroCDO();
		arvo.setFields(fieldMap);
		arvo.setLevel(maxLevel);
		return arvo;
	}
	
	protected void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());			
			entry.getValue().toAvro(prefixField, fieldMap);
		}
	}
	
	public int toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap,int maxLevel){
		Entry<String, Field> entry=null;
		int curLevel=0;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());			
			curLevel=entry.getValue().toAvro(prefixField, fieldMap,maxLevel);
			if(curLevel>maxLevel)
				maxLevel=curLevel;
		}
		return maxLevel;
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


	protected String toXML(StringBuilder strbXML)
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
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());			
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
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());
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
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());
			entry.getValue().toXMLWithIndent(1,strbXML);
		}			
		strbXML.append("</CDO>\r\n");	
		
		return strbXML.toString();
	}
	
	protected void toXMLWithIndent(String strIndent,StringBuilder strbXML){
		strbXML.append(strIndent).append("<CDO>\r\n");		
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());
			entry.getValue().toXMLWithIndent(1+strIndent.length(),strbXML);
		}			
		strbXML.append(strIndent).append("</CDO>\r\n");
	}


	//操作方法---------------------------------------------------------------------------------------------------
	protected void setField(String strName,Field field)
	{
		putItem(strName,field);		
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
    
    //自定义方法
    protected Field getObject(FieldId fieldId,CDO cdoRoot)
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
    			(((CDOArrayField)objExt).getValueAt(nIndex)).getObject(fieldId.strFieldId);
    		}
    		Field cdoMainField=getObject(fieldIdMain,cdoRoot);
    		if(cdoMainField==null)
    		{
    			return null;
    		}
    		if(cdoMainField.getType()>=DataType.BOOLEAN_ARRAY_TYPE && fieldId.strFieldId.equalsIgnoreCase("length")==true)
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

    private Field getValueAtExt(Field field,int nIndex)
	{
		switch(field.getType())
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
		throw new RuntimeException("Type cast failed");	
	}
    
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

    public Field getChild(String strFieldId)
    {
    	Field objExt=this.hmItem.get(strFieldId);
    	if(objExt==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	return objExt;
	}
    

    public Object getObjectValue(String strFieldId)
    {
    	Field objExt=this.getObject(strFieldId);
    	return objExt.getObjectValue();
	}

    public boolean getBooleanValue(String strFieldId)
    {
    	BooleanField objExt=(BooleanField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public byte getByteValue(String strFieldId)
    {
    	ByteField objExt=(ByteField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public short getShortValue(String strFieldId)
    {
    	ShortField objExt=(ShortField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public int getIntegerValue(String strFieldId)
    {
    	IntegerField objExt=(IntegerField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public long getLongValue(String strFieldId)
    {
    	LongField objExt=(LongField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public float getFloatValue(String strFieldId)
    {
    	FloatField objExt=(FloatField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public double getDoubleValue(String strFieldId)
    {
    	DoubleField objExt=(DoubleField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public String getStringValue(String strFieldId)
    {
    	StringField objExt=(StringField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public File getFileValue(String strFieldId)
    {
    	FileField objExt=(FileField)this.getObject(strFieldId);
    	return objExt.getValue();
    }
    public String getDateValue(String strFieldId)
    {
    	DateField objExt=(DateField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public String getTimeValue(String strFieldId)
    {
    	TimeField objExt=(TimeField)this.getObject(strFieldId);
    	return objExt.getValue();
    }

    public String getDateTimeValue(String strFieldId)
    {
    	DateTimeField objExt=(DateTimeField)this.getObject(strFieldId);
    	return objExt.getValue();
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
    	return objExt.getValue();
    	
    }

	 void setObjectValue(FieldId fieldId,int nType,Object objValue,Field field,CDO cdoRoot)
	{
    	if(fieldId.nType==FieldId.SIMPLE)
    	{
//    		this.putItem(fieldId.strFieldId,new ObjectExt(nType,objValue));
    		this.putItem(fieldId.strFieldId,field);
    	}
    	else if(fieldId.nType==FieldId.MULTI_LEVEL)
    	{
    		CDO cdoMain=this.getCDOValue(fieldId.strMainFieldId);    	
//    		cdoMain.setField(fieldId.strFieldId,new ObjectExt(nType,objValue));
    		cdoMain.setField(fieldId.strFieldId,field);
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

    		switch(arrField.getType())
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
     * 检查一个字段是否存在的时候不应该抛出异常，改方法是对原来方法的改写
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
			if(objExt.getType()>=DataType.BOOLEAN_ARRAY_TYPE){
				length=((ArrayField)objExt).getLength();
			}
			return length==0;
			
		}catch(Exception e)
		{//有值,但不是数据组类型
			return false;
		}
    }
        
   /** 
    private ValueField createField(String strFieldId,ObjectExt objExt)
    {
    	ValueField vf=null;
		switch(objExt.getType())
		{
			case DataType.BOOLEAN_TYPE:
				vf=new BooleanField(strFieldId,objExt.getBoolean());
				break;
			case DataType.BYTE_TYPE:
				vf=new ByteField(strFieldId,objExt.getByte());
				break;
			case DataType.SHORT_TYPE:
				vf=new ShortField(strFieldId,objExt.getShort());
				break;
			case DataType.INTEGER_TYPE:
				vf=new IntegerField(strFieldId,objExt.getInteger());
				break;
			case DataType.LONG_TYPE:
				vf=new LongField(strFieldId,objExt.getLong());
				break;
			case DataType.FLOAT_TYPE:
				vf=new FloatField(strFieldId,objExt.getFloat());
				break;
			case DataType.DOUBLE_TYPE:
				vf=new DoubleField(strFieldId,objExt.getDouble());
				break;
			case DataType.STRING_TYPE:
				vf=new StringField(strFieldId,objExt.getString());
				break;
			case DataType.DATE_TYPE:
				vf=new DateField(strFieldId,objExt.getDate());
				break;
			case DataType.TIME_TYPE:
				vf=new TimeField(strFieldId,objExt.getTime());
				break;
			case DataType.DATETIME_TYPE:
				vf=new DateTimeField(strFieldId,objExt.getDateTime());
				break;
			case DataType.CDO_TYPE:
				vf=new CDOField(strFieldId,objExt.getCDO());
				break;
			case DataType.FILE_TYPE:
				vf=new FileField(strFieldId,objExt.getFile());
				break;
			case DataType.BOOLEAN_ARRAY_TYPE:
				vf=new BooleanArrayField(strFieldId,objExt.getBooleanArray());
				break;
			case DataType.BYTE_ARRAY_TYPE:
				vf=new ByteArrayField(strFieldId,objExt.getByteArray());
				break;
			case DataType.SHORT_ARRAY_TYPE:
				vf=new ShortArrayField(strFieldId,objExt.getShortArray());
				break;
			case DataType.INTEGER_ARRAY_TYPE:
				vf=new IntegerArrayField(strFieldId,objExt.getIntegerArray());
				break;
			case DataType.LONG_ARRAY_TYPE:
				vf=new LongArrayField(strFieldId,objExt.getLongArray());
				break;
			case DataType.FLOAT_ARRAY_TYPE:
				vf=new FloatArrayField(strFieldId,objExt.getFloatArray());
				break;
			case DataType.DOUBLE_ARRAY_TYPE:
				vf=new DoubleArrayField(strFieldId,objExt.getDoubleArray());
				break;
			case DataType.STRING_ARRAY_TYPE:
				vf=new StringArrayField(strFieldId,objExt.getStringArray());
				break;
			case DataType.DATE_ARRAY_TYPE:
				vf=new DateArrayField(strFieldId,objExt.getDateArray());
				break;
			case DataType.TIME_ARRAY_TYPE:
				vf=new TimeArrayField(strFieldId,objExt.getTimeArray());
				break;
			case DataType.DATETIME_ARRAY_TYPE:
				vf=new DateTimeArrayField(strFieldId,objExt.getDateTimeArray());
				break;
			case DataType.CDO_ARRAY_TYPE:
				vf=new CDOArrayField(strFieldId,objExt.getCDOArray());
				break;
		}
		
		return vf;
    }
    
  **/
    public Field getField(String strFieldId)
    {
//    	ObjectExt objExt=this.getObject(strFieldId);
//    	
//    	return this.createField(strFieldId, objExt);
    	return this.getObject(strFieldId);
    }
    
    public void copyFrom(CDO cdoSource)
    {
		XMLElement xmlNode=new XMLElement();
		xmlNode.parseString(cdoSource.toXML());

		this.fromXML(xmlNode,true);
    }
    
    public void copyFrom(String strCDOXML)
    {
		XMLElement xmlNode=new XMLElement();
		xmlNode.parseString(strCDOXML);

		this.fromXML(xmlNode,true);
    }

    public CDO clone()
    {
    	String strXML=this.toXML();
    	return CDO.fromXML(strXML);
    }
    
    
    public void setObjectExt(String strFieldId, Field field) throws RuntimeException
    {
    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
   		this.setObjectValue(fieldId,DataType.BOOLEAN_TYPE,field.getObjectValue(),field,this);
   		/**
  		int nType=object.getType();
		Object objValue=object.getObjectValue();
		switch(nType)
		{
			case DataType.BYTE_TYPE:
			{
				this.setByteValue(strFieldId,((Byte)objValue).byteValue());
				break;
			}
			case DataType.SHORT_TYPE:
			{
				this.setShortValue(strFieldId,((Short)objValue).shortValue());
				break;
			}
			case DataType.INTEGER_TYPE:
			{
				this.setIntegerValue(strFieldId,((Integer)objValue).intValue());
				break;
			}
			case DataType.LONG_TYPE:
			{
				this.setLongValue(strFieldId,((Long)objValue).longValue());
				break;
			}
			case DataType.FLOAT_TYPE:
			{
				this.setFloatValue(strFieldId,((Float)objValue).floatValue());
				break;
			}
			case DataType.DOUBLE_TYPE:
			{
				this.setDoubleValue(strFieldId,((Double)objValue).doubleValue());
				break;
			}
			case DataType.STRING_TYPE:
			{
				this.setStringValue(strFieldId,((String)objValue));
				break;
			}
			case DataType.DATE_TYPE:
			{
				this.setDateValue(strFieldId,((String)objValue));
				break;
			}
			case DataType.TIME_TYPE:
			{
				this.setTimeValue(strFieldId,((String)objValue));
				break;
			}
			case DataType.DATETIME_TYPE:
			{
				this.setDateTimeValue(strFieldId,((String)objValue));
				break;
			}
			case DataType.BYTE_ARRAY_TYPE:
			{
				this.setByteArrayValue(strFieldId,((byte[])objValue));
				break;
			}
			case DataType.CDO_TYPE:
			{
				this.setCDOValue(strFieldId,(CDO)objValue);
				break;
			}
			case DataType.CDO_ARRAY_TYPE:
				{
					this.setCDOArrayValue(strFieldId,(CDO[])objValue);
					break;
				}
			case DataType.STRING_ARRAY_TYPE:
			{
				this.setStringArrayValue(strFieldId,(String[])objValue);
				break;
			}
			case  DataType.LONG_ARRAY_TYPE:
			{
				this.setLongArrayValue(strFieldId,(long[])objValue);
				break;				
			}
			case DataType.INTEGER_ARRAY_TYPE:
			{
				this.setIntegerArrayValue(strFieldId,(int[])objValue);
				break;						
			}
			case DataType.SHORT_ARRAY_TYPE:
			{
				this.setShortArrayValue(strFieldId,(short[])objValue);
				break;						
			}
			case DataType.DATETIME_ARRAY_TYPE:
			{
				this.setDateTimeArrayValue(strFieldId,(String[])objValue);
				break;				
			}
			case DataType.DATE_ARRAY_TYPE:
			{
				this.setDateArrayValue(strFieldId,(String[])objValue);
				break;				
			}
			case DataType.TIME_ARRAY_TYPE:
			{
				this.setTimeArrayValue(strFieldId,(String[])objValue);
				break;				
			}
			default://TODO 目前还不支持ReocrdSet
			{
				throw new RuntimeException("Unsupported type "+nType);
			}
		}
		**/
    }
	
	public void remove(String key)
	{
		Field objExt=hmItem.remove(key);
		if(objExt.getType()==DataType.FILE_TYPE && !key.contains(".")){
			fileCount--;
		}
	}

	public Set<String> keySet()
	{
		return hmItem.keySet();
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

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CDO(){
		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		hmItem	=new LinkedHashMap<String,Field>();		
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
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());
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
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());
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
	
	public String toString()
	{
		StringBuffer str_String=new StringBuffer("{");
		
		Entry<String, Field> entry=null;
		for(Iterator<Map.Entry<String, Field>> it=this.entrySet().iterator();it.hasNext();){
			entry=it.next();
//			Field fieldItem=this.createField(entry.getKey(),entry.getValue());
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
	

	
	
	
				
	
	
	public static void main(String[] args) throws IOException
	{
		CDO cdo = new CDO();

		
		cdo.setByteValue("byte", (byte)2);
		cdo.setByteArrayValue("bytes", new byte[]{1,2,3});
		cdo.setBooleanValue("bvalue", true);
		cdo.setBooleanArrayValue("bsValue", new boolean[]{false,true,true,false});
		cdo.setShortValue("short", (short)100);
		cdo.setShortArrayValue("shorts", new short[]{100,200,300});
		cdo.setIntegerValue("int", 300);
		cdo.setIntegerArrayValue("ints", new int[]{400,500,600});
		cdo.setLongValue("long", 7000);
		cdo.setLongArrayValue("longs", new long[]{9000,10000});
		cdo.setFloatValue("float", 3.0f);
		cdo.setFloatArrayValue("floats", new float[]{1.0f,2.0f,3.0f});
		cdo.setDoubleValue("double", 5.0);
		cdo.setDoubleArrayValue("doubles", new double[]{6.0,7.0,8.0});
		cdo.setStringValue("str", "张三");
		cdo.setStringArrayValue("strvalues", new String[]{ "张3", "张4", "张5"});
		cdo.setDateValue("date", "2016-05-01");
		cdo.setDateArrayValue("date1", new String[]{"2012-05-01","2013-05-01","2014-05-01"});
		cdo.setTimeValue("time", "20:00:00");
		cdo.setTimeArrayValue("times", new String[]{"17:00:00","18:00:00","20:00:00"});
		cdo.setDateTimeValue("dateTime", "2012-05-01 20:00:00");
		cdo.setDateTimeArrayValue("dateTimeValues", new String[]{"2012-05-01 20:00:00","2013-05-01 21:00:00","2014-05-01 22:00:00"});
		
		CDO cdoResponse=new CDO();
		cdoResponse.setIntegerValue("ncount", 5);
		CDO[] cdosList=new CDO[5];
		for(int i=0;i<cdosList.length;i++)
		{			
			cdosList[i]=new CDO();

			CDO[] cdo1=new CDO[2];
			for(int j=0;j<cdo1.length;j++){
				cdo1[j]=new CDO();
				cdo1[j].setStringValue("strName2","张三"+i);
				cdo1[j].setDateValue("dBirthday2","2000-01-01");
			}
			cdosList[i].setCDOArrayValue("cdoArr", cdo1);
			cdosList[i].setBooleanValue("booleanValue", true);
			cdosList[i].setStringValue("strValue", "cdosList张"+i);
			cdosList[i].setIntegerArrayValue("nsCDOList"+i, new int[]{1,2,3});
			CDO subcdo=new CDO();
			subcdo.setStringArrayValue("xx", new String[]{"ss","x"});
			subcdo.setIntegerArrayValue("nsCDOList"+i, new int[]{1,2,3});
			cdosList[i].setCDOValue("subCDO", subcdo);
			
		}
		cdoResponse.setCDOArrayValue("cdosList", cdosList);

		
		CDO cdoReturn=new CDO();
		cdoReturn.setIntegerValue("nCode",0);
		cdoReturn.setStringValue("strText","测试");
		cdoReturn.setStringValue("strInfo","测试");
		cdo.setCDOArrayValue("cdosList", cdosList);
		cdo.setCDOValue("cdoReturn",cdoReturn);
		cdo.setCDOValue("cdoResponse", cdoResponse);
		cdo.setStringArrayValue("cdoResponse.str",  new String[]{"xxx","yyy"});
		
		long startTime=System.currentTimeMillis();
		CDO cdo2=null;
		   for(int i=0;i<1;i++){
				AvroCDO arvo=cdo.toAvro();
		        ByteArrayOutputStream out=new ByteArrayOutputStream();  		        
		        DatumWriter<AvroCDO> writer=new SpecificDatumWriter<AvroCDO>(AvroCDO.class);  
		        Encoder encoder= EncoderFactory.get().binaryEncoder(out,null);  
		        writer.write(arvo, encoder);  
		        encoder.flush();  
		        out.close();  
		        
		        DatumReader<AvroCDO> reader=new SpecificDatumReader<AvroCDO>(AvroCDO.class);  
		        Decoder decoder= DecoderFactory.get().binaryDecoder(out.toByteArray(),null);  
		        AvroCDO result=reader.read(null,decoder);
		        AvroCDODeserialize a1=new AvroCDODeserialize();
		         cdo2=a1.fromAvro(result);
		   }	
		   System.out.println(System.currentTimeMillis()-startTime);
		   System.out.println(cdo2.toXMLWithIndent());
		   startTime=System.currentTimeMillis();
		   String xml=cdo.toXML();
		   CDO cdo3=CDO.fromXML(xml);
		   System.out.println("xml==="+(System.currentTimeMillis()-startTime));
		   System.out.println("cdo3 length==="+cdo3.toXML());	   
//        System.out.println("avro ="+(System.currentTimeMillis()-startTime));
//        startTime=System.currentTimeMillis();
//        for(int i=0;i<1;i++){
//            String xml=cdo.toXML();
//            cdo.fromXML(xml);
//        }
//        System.out.println("xml serialize "+(System.currentTimeMillis()-startTime));
////        System.out.println(cdo.tox);
//    	HashMap<String,ValueFieldImpl> hm=new LinkedHashMap<String, ValueFieldImpl>();
//    	hm.put("a", new LongField("ab",100));
//        for(Iterator<Map.Entry<String, ValueFieldImpl>> iterator=hm.entrySet().iterator();iterator.hasNext();){
//        	StringBuilder sBuilder=new StringBuilder();
//        	System.out.println( iterator.next().getValue().toString());
//        }s
		   
//		   ByteBuffer buffer=ByteBuffer.allocate(5);
//		   buffer.put((byte)1);
//		   buffer.putInt(6);
//		   buffer.flip();
//		   
//		   buffer.position(1);
//		   System.out.println(buffer.getInt());
//		   buffer.position(1);
//		   System.out.println(buffer.getInt());
//		   BooleanField b=new BooleanField("b", true);
//		   System.out.println(b.getValue());
//		   System.out.println(b.getValue());
//		   System.out.println(b.getValue());
//		   b.setValue(false);
//		   System.out.println(b.getValue());
//		   b.setValue(true);
//		   System.out.println(b.getValue());
//		   System.out.println(b.getValue());
	
//		    byte[] bsValue=new byte[]{32,45,12};
			ByteBuffer buffer=null;
			buffer=ByteBuffer.allocate(1+10*2);
			buffer.put((byte)DataType.DATE_TYPE);
			
//			buffer.putInt(bsValue.length);
//			buffer.put(" ".getBytes());
//			buffer.put("".getBytes());
//			buffer.put("".getBytes());
//			buffer.put("".getBytes());
//			buffer.put("1111-11-11".getBytes());
//			buffer.flip();
			
//			buffer.position(1);
//			buffer.limit(11);
			byte[] bsValue=new byte[10]; 
			buffer.clear();
//			buffer.ge
//			(buffer.slice()).get(bsValue);
//			buffer.get(bsValue, 2, 12);
//			buffer.clear();
//			System.out.println("bytes="+new String(bsValue));
//			String[] a=new String[]{"2012-05-01","2013-05-01","2014-05-01"};
//			DateArrayField ab=new DateArrayField("feild", a);
//			System.out.println(ab.getObjectValueAt(2));
//			System.out.println(ab.getObjectValueAt(2));
//			ab.setValueAt(2, "2014");
//			System.out.println("xx="+ab.getObjectValueAt(2));
			
	}
	
}
