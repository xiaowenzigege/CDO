/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/CDO.java,v 1.6 2008/03/27 09:42:40 Frank Exp $
 *
 * $Log: CDO.java,v $
 * Revision 1.6  2008/03/27 09:42:40  Frank
 * *** empty log message ***
 *
 * Revision 1.7  2008/03/22 13:32:10  Frank
 * *** empty log message ***
 *
 * Revision 1.4  2008/03/12 10:30:56  Frank
 * *** empty log message ***
 *
 * Revision 1.6  2008/03/11 15:13:33  Frank
 * *** empty log message ***
 *
 * Revision 1.5  2008/03/11 13:41:31  Frank
 * *** empty log message ***
 *
 * Revision 1.4  2008/03/10 14:54:17  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/03/08 12:10:54  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/08 06:16:23  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/07 11:20:20  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/11/15 12:02:32  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/11/03 02:25:41  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/11 01:10:56  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nanoxml.XMLElement;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Utility;

/**
 * @author Frank
 * modify by @author KenelLiu 
 */
public class CDO implements Serializable
{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID=1L;
	
	private static final Logger logger = Logger.getLogger(CDO.class);

	private  int fileCount;//CDO里是否设置了文件，若设置了文件 则在网络传输需要特别处理 
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
	private HashMap<String,ObjectExt> hmItem;
	private ArrayList<ObjectExt> vecItem;
	private ArrayList<String> vecFieldId;
//	private String strXML;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法---------------------------------------------------------------------------------------------------
	protected void putItem(String strKey,ObjectExt objExt)
	{
		ObjectExt fdOldItem=(ObjectExt)hmItem.put(strKey,objExt);
		if(fdOldItem==null)
		{
			vecItem.add(objExt);
			vecFieldId.add(strKey);
		}
		else
		{
			int nIndex=vecItem.indexOf(fdOldItem);
			vecItem.set(nIndex,objExt);
			vecFieldId.set(nIndex,strKey);
		}
	}
	
	//0-简单类型，1-多级，2-数组元素
	//如果FieldId错误，则返回null
	private FieldId parseFieldId(String strFieldId)
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
				else if(strValue.equalsIgnoreCase("false"))
				{
				}
				else
				{
					throw new RuntimeException("Parse xml error: unexpected boolean value "+strValue+" under "+strTag);
				}
				ObjectExt objExt=new ObjectExt(ObjectExt.BOOLEAN_TYPE,new Boolean(bValue));
				putItem(strName,objExt);
			}
			else if(strTag.equals("BYF"))//ByteField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.BYTE_TYPE,new Byte(strValue));
				putItem(strName,objExt);
			}
			else if(strTag.equals("SF"))//ShortField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.SHORT_TYPE,new Short(strValue));
				putItem(strName,objExt);
			}
			else if(strTag.equals("NF"))//IntegerField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.INTEGER_TYPE,new Integer(strValue));
				putItem(strName,objExt);
			}
			else if(strTag.equals("LF"))//LongField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.LONG_TYPE,new Long(strValue));
				putItem(strName,objExt);
			}
			else if(strTag.equals("FF"))//FloatField
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.FLOAT_TYPE,new Float(strValue));
				putItem(strName,objExt);
			}
			else if(strTag.equals("DBLF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.DOUBLE_TYPE,new Double(strValue));
				putItem(strName,objExt);
			}
			else if(strTag.equals("STRF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.STRING_TYPE,strValue);
				putItem(strName,objExt);
			}
			else if(strTag.equals("DF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.DATE_TYPE,strValue);
				putItem(strName,objExt);
			}
			else if(strTag.equals("TF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.TIME_TYPE,strValue);
				putItem(strName,objExt);
			}
			else if(strTag.equals("DTF"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.DATETIME_TYPE,strValue);
				putItem(strName,objExt);
			}
			else if(strTag.equals("CDOF"))
			{
				String strName	=node.getStringAttribute("N");
				CDO cdoValue	=new CDO();
				cdoValue.fromXML((XMLElement)node.getChildren().get(0),false);

				ObjectExt objExt=new ObjectExt(ObjectExt.CDO_TYPE,cdoValue);
				putItem(strName,objExt);
			}else if(strTag.equals("FILE"))
			{
				String strName	=node.getStringAttribute("N");
				String strValue=node.getStringAttribute("V");
				ObjectExt objExt=new ObjectExt(ObjectExt.FILE_TYPE,new File(strValue));
				putItem(strName,objExt);
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
					if(strValueArr[i].equalsIgnoreCase("false"))
					{
						bsValue[i]=false;
						
					}else if(strValueArr[i].equalsIgnoreCase("true"))
					{
						bsValue[i]=true;	
					}else
					{
						throw new RuntimeException("Parse xml error: unexpected boolean value "+strValueArr[i]+" under "+strTag);	
					}
				}
				ObjectExt objExt=new ObjectExt(ObjectExt.BOOLEAN_ARRAY_TYPE,bsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.BYTE_ARRAY_TYPE,bysValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.SHORT_ARRAY_TYPE,shsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.INTEGER_ARRAY_TYPE,nsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.LONG_ARRAY_TYPE,lsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.FLOAT_ARRAY_TYPE,fsValue);
				putItem(strName,objExt);
				
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
				ObjectExt objExt=new ObjectExt(ObjectExt.DOUBLE_ARRAY_TYPE,dblsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.STRING_ARRAY_TYPE,strsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.DATE_ARRAY_TYPE,strValueArr);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.TIME_ARRAY_TYPE,strsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.DATETIME_ARRAY_TYPE,strsValue);
				putItem(strName,objExt);
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
				ObjectExt objExt=new ObjectExt(ObjectExt.CDO_ARRAY_TYPE,cdosValue);
				putItem(strName,objExt);
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

	public String toXML()
	{
		StringBuilder strbXML=new StringBuilder(500);
		strbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");		
		int nSize=vecItem.size();
		strbXML.append("<CDO>");
		for(int i=0;i<nSize;i++)
		{
			Field fieldItem=this.getFieldAt(i);
			fieldItem.toXML(strbXML);
		}
		strbXML.append("</CDO>");
		String strXML=strbXML.toString();
		
		return strXML;
	}


	protected String toXML(StringBuilder strbXML)
	{
		
		int nSize=vecItem.size();
		strbXML.append("<CDO>");
		for(int i=0;i<nSize;i++)
		{
			Field fieldItem=this.getFieldAt(i);
			fieldItem.toXML(strbXML);
		}
		strbXML.append("</CDO>");
		
		return strbXML.toString();
	}
	
	public String toXMLLog()
	{
		StringBuilder strbXML=new StringBuilder(500);
		strbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");		
		int nSize=vecItem.size();
		strbXML.append("<CDO>");
		for(int i=0;i<nSize;i++)
		{
			Field fieldItem=this.getFieldAt(i);
			fieldItem.toXMLLog(strbXML);
		}
		strbXML.append("</CDO>");
		String strXML=strbXML.toString();
		
		return strXML;
	}
	public String toXMLWithIndent()
	{
		StringBuilder strbXML=new StringBuilder(500);
		strbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		
		int nSize=vecItem.size();
		strbXML.append("<CDO>\r\n");
		for(int i=0;i<nSize;i++)
		{
			Field fieldItem=this.getFieldAt(i);
			fieldItem.toXMLWithIndent(1,strbXML);
		}
		strbXML.append("</CDO>\r\n");	
		
		return strbXML.toString();
	}
	
	protected void toXMLWithIndent(String strIndent,StringBuilder strbXML)
	{
		if(strIndent!=null)
		{
			strbXML.append(strIndent).append("<CDO>\r\n");
		}
		else
		{
			strbXML.append("<CDO>");
		}

		int nSize=vecItem.size();
		strbXML.append("<CDO>\r\n");
		for(int i=0;i<nSize;i++)
		{
			Field fieldItem=this.getFieldAt(i);
			fieldItem.toXMLWithIndent(1+strIndent.length(),strbXML);
		}
		strbXML.append(strIndent).append("</CDO>\r\n");
	}


	//操作方法---------------------------------------------------------------------------------------------------
	protected void setField(String strName,ObjectExt field)
	{
		putItem(strName,field);
		//strXML=null;
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
    protected ObjectExt getObject(FieldId fieldId,CDO cdoRoot)
	{
    	if(fieldId.nType==FieldId.SIMPLE)
    	{//简单类型
    		ObjectExt objExt=this.hmItem.get(fieldId.strFieldId);
    		return objExt;
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
    			ObjectExt objExt=this.getObject(this.parseFieldId(fieldIdMain.strMainFieldId),cdoRoot);
    			if(objExt==null)
    			{
    				return null;
    			}
    			int nIndex=this.getIndexValue(fieldIdMain.strIndexFieldId,cdoRoot);
    			return ((CDO)objExt.getValueAt(nIndex)).getObject(fieldId.strFieldId);
    		}
    		ObjectExt cdoMainField=getObject(fieldIdMain,cdoRoot);
    		if(cdoMainField==null)
    		{
    			return null;
    		}
    		if(cdoMainField.isArrayType() && fieldId.strFieldId.equalsIgnoreCase("length")==true)
    		{
    			return new ObjectExt(ObjectExt.INTEGER_TYPE,cdoMainField.getLength());
    		}
    		return ((CDO)cdoMainField.getValue()).hmItem.get(fieldId.strFieldId);
    	}

    	//数组元素
		int nIndex=this.getIndexValue(fieldId.strIndexFieldId,cdoRoot);
		FieldId fieldIdMain=this.parseFieldId(fieldId.strMainFieldId);
		ObjectExt objExt=this.getObject(fieldIdMain,cdoRoot);

		return objExt.getValueAtExt(nIndex);
	}

    public ObjectExt getObject(String strFieldId)
    {
    	ObjectExt objExt=this.hmItem.get(strFieldId);
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

    public ObjectExt getChild(String strFieldId)
    {
    	ObjectExt objExt=this.hmItem.get(strFieldId);
    	if(objExt==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	return objExt;
	}
    
    public ObjectExt getObjectAt(int nIndex)
    {
    	return this.vecItem.get(nIndex);
    }
    
    public String getFieldIdAt(int nIndex)
    {
    	return this.vecFieldId.get(nIndex);
    }

    public Object getObjectValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getValue();
	}

    public boolean getBooleanValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getBoolean();
    }

    public byte getByteValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getByte();
    }

    public short getShortValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getShort();
    }

    public int getIntegerValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getInteger();
    }

    public long getLongValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getLong();
    }

    public float getFloatValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getFloat();
    }

    public double getDoubleValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getDouble();
    }

    public String getStringValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getString();
    }

    public File getFileValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getFile();
    }
    public String getDateValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getDate();
    }

    public String getTimeValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getTime();
    }

    public String getDateTimeValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getDateTime();
    }

    public CDO getCDOValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getCDO();
    }

    public boolean[] getBooleanArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getBooleanArray();
    }

    public byte[] getByteArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getByteArray();
    }

    public short[] getShortArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getShortArray();
    }

    public int[] getIntegerArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getIntegerArray();
    }

    public long[] getLongArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getLongArray();
    }

    public float[] getFloatArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getFloatArray();
    }

    public double[] getDoubleArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getDoubleArray();
    }

    public String[] getStringArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getStringArray();
    }

    public String[] getDateArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getDateArray();
    }

    public String[] getTimeArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getTimeArray();
    }

    public String[] getDateTimeArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getDateTimeArray();
    }

    public CDO[] getCDOArrayValue(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	return objExt.getCDOArray();
    }

	private void setObjectValue(FieldId fieldId,int nType,Object objValue,CDO cdoRoot)
	{
    	if(fieldId.nType==FieldId.SIMPLE)
    	{
    		this.putItem(fieldId.strFieldId,new ObjectExt(nType,objValue));
    	}
    	else if(fieldId.nType==FieldId.MULTI_LEVEL)
    	{
    		CDO cdoMain=this.getCDOValue(fieldId.strMainFieldId);    	
    		cdoMain.setField(fieldId.strFieldId,new ObjectExt(nType,objValue));
    	}
    	else
    	{//Array Element    		
    		FieldId fieldIdMain=this.parseFieldId(fieldId.strMainFieldId);
    		if(fieldIdMain==null)
    		{
    			throw new RuntimeException("Invalid FieldId "+fieldId.strFieldId);
    		}
    		
    		ObjectExt arrField= null;
    		int nIndex =-1;
			arrField=this.getObject(fieldIdMain,this);
	    	if(arrField==null)
	    	{
				throw new RuntimeException("FieldId "+fieldId.strMainFieldId+" not exist");
	    	}
			nIndex=this.getIndexValue(fieldId.strIndexFieldId,cdoRoot);	

    		switch(arrField.getType())
    		{
    			case ObjectExt.BOOLEAN_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(Boolean)objValue);
    				break;
    			}
    			case ObjectExt.BYTE_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(Byte)objValue);
    				break;
    			}
    			case ObjectExt.SHORT_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(Short)objValue);
    				break;
    			}
    			case ObjectExt.INTEGER_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(Integer)objValue);
    				break;
    			}
    			case ObjectExt.LONG_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(Long)objValue);
    				break;
    			}
    			case ObjectExt.STRING_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(String)objValue);
    				break;
    			}
    			case ObjectExt.DATE_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(String)objValue);
    				break;
    			}
    			case ObjectExt.TIME_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(String)objValue);
    				break;
    			}
    			case ObjectExt.DATETIME_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(String)objValue);
    				break;
    			}
    			case ObjectExt.CDO_ARRAY_TYPE:
    			{
    				arrField.setValueAt(nIndex,(CDO)objValue);
    				break;
    			}
    			
    		}
    	}
	}

    public void setBooleanValue(String strFieldId,boolean bValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
   		this.setObjectValue(fieldId,ObjectExt.BOOLEAN_TYPE,bValue,this);
    }

    public void setByteValue(String strFieldId,byte byValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.BYTE_TYPE,byValue,this);
    }

    public void setShortValue(String strFieldId,short shValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.SHORT_TYPE,shValue,this);
    }

    public void setIntegerValue(String strFieldId,int nValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.INTEGER_TYPE,nValue,this);
    }

    public void setLongValue(String strFieldId,long lValue)
    {
    	//this.strXML=null;
    	
    	FieldId fieldId=this.parseFieldId(strFieldId);

    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.LONG_TYPE,lValue,this);
    }

    public void setFloatValue(String strFieldId,float fValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.FLOAT_TYPE,fValue,this);
    }

    public void setDoubleValue(String strFieldId,double dValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.DOUBLE_TYPE,dValue,this);
    }

    public void setStringValue(String strFieldId,String strValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.STRING_TYPE,strValue,this);
    }

    public void setFileValue(String strFieldId,File file)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.FILE_TYPE,file,this);
		if(fieldId.nType==FieldId.SIMPLE)
			fileCount++;
    }
    
    public void setDateValue(String strFieldId,String strValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.DATE_TYPE,strValue,this);
    }

    public void setTimeValue(String strFieldId,String strValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.TIME_TYPE,strValue,this);
    }

    public void setDateTimeValue(String strFieldId,String strValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.DATETIME_TYPE,strValue,this);
    }

    public void setCDOValue(String strFieldId,CDO cdoValue)
    {
    	//this.strXML=null;

    	FieldId fieldId=this.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
		this.setObjectValue(fieldId,ObjectExt.CDO_TYPE,cdoValue,this);
    }

    public void setBooleanArrayValue(String strFieldId,boolean[] bsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.BOOLEAN_ARRAY_TYPE,bsValue,this);
    	}
    }

    public void setByteArrayValue(String strFieldId,byte[] bysValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.BYTE_ARRAY_TYPE,bysValue,this);
    	}
    }

    public void setShortArrayValue(String strFieldId,short[] shsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.SHORT_ARRAY_TYPE,shsValue,this);
    	}
    }

    public void setIntegerArrayValue(String strFieldId,int[] nsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.INTEGER_ARRAY_TYPE,nsValue,this);
    	}
    }
    public void setFloatArrayValue(String strFieldId,float[] fsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.FLOAT_ARRAY_TYPE,fsValue,this);
    	}
    }
    public void setDoubleArrayValue(String strFieldId,double[] dblsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.DOUBLE_ARRAY_TYPE,dblsValue,this);
    	}
    }
    public void setLongArrayValue(String strFieldId,long[] lsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.LONG_ARRAY_TYPE,lsValue,this);
    	}
    }
    public void setStringArrayValue(String strFieldId,String[] strsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.STRING_ARRAY_TYPE,strsValue,this);
    	}
    }

    public void setDateArrayValue(String strFieldId,String[] strsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.DATE_ARRAY_TYPE,strsValue,this);
    	}
    }

    public void setTimeArrayValue(String strFieldId,String[] strsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.TIME_ARRAY_TYPE,strsValue,this);
    	}
    }

    public void setDateTimeArrayValue(String strFieldId,String[] strsValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.DATETIME_ARRAY_TYPE,strsValue,this);
    	}
    }

    public void setCDOArrayValue(String strFieldId,CDO[] cdosValue)
    {
    	//this.strXML=null;

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
    		this.setObjectValue(fieldId,ObjectExt.CDO_ARRAY_TYPE,cdosValue,this);
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
    	ObjectExt objExt=this.hmItem.get(strFieldId);
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
    	ObjectExt objExt = null;
    	try
		{    		
    		objExt = getObject(strFieldId);  
		}
		catch(Exception e)
		{//不存在
			return true;
		}
		if(objExt==null)
		{
			return true;
		}
		try
		{
			return objExt.getLength()==0;
		}catch(Exception e)
		{//有值,但不是数据组类型
			return false;
		}
    }

    public ObjectExt[] getFieldValues()
    {
    	ObjectExt[] objs=new ObjectExt[this.vecItem.size()];

    	return this.vecItem.toArray(objs);
    }

    public String[] getFieldIds()
    {
    	String[] strs=new String[this.vecItem.size()];

    	return this.vecFieldId.toArray(strs);
    }

    public int getFieldCount()
    {
    	return vecItem.size();
    }
    
    public ObjectExt getValueAt(int nIndex)
    {
    	return vecItem.get(nIndex);
    }

    private ValueField createField(String strFieldId,ObjectExt objExt)
    {
    	ValueField vf=null;
		switch(objExt.getType())
		{
			case ObjectExt.BOOLEAN_TYPE:
				vf=new BooleanField(strFieldId,objExt.getBoolean());
				break;
			case ObjectExt.BYTE_TYPE:
				vf=new ByteField(strFieldId,objExt.getByte());
				break;
			case ObjectExt.SHORT_TYPE:
				vf=new ShortField(strFieldId,objExt.getShort());
				break;
			case ObjectExt.INTEGER_TYPE:
				vf=new IntegerField(strFieldId,objExt.getInteger());
				break;
			case ObjectExt.LONG_TYPE:
				vf=new LongField(strFieldId,objExt.getLong());
				break;
			case ObjectExt.FLOAT_TYPE:
				vf=new FloatField(strFieldId,objExt.getFloat());
				break;
			case ObjectExt.DOUBLE_TYPE:
				vf=new DoubleField(strFieldId,objExt.getDouble());
				break;
			case ObjectExt.STRING_TYPE:
				vf=new StringField(strFieldId,objExt.getString());
				break;
			case ObjectExt.DATE_TYPE:
				vf=new DateField(strFieldId,objExt.getDate());
				break;
			case ObjectExt.TIME_TYPE:
				vf=new TimeField(strFieldId,objExt.getTime());
				break;
			case ObjectExt.DATETIME_TYPE:
				vf=new DateTimeField(strFieldId,objExt.getDateTime());
				break;
			case ObjectExt.CDO_TYPE:
				vf=new CDOField(strFieldId,objExt.getCDO());
				break;
			case ObjectExt.FILE_TYPE:
				vf=new FileField(strFieldId,objExt.getFile());
				break;
			case ObjectExt.BOOLEAN_ARRAY_TYPE:
				vf=new BooleanArrayField(strFieldId,objExt.getBooleanArray());
				break;
			case ObjectExt.BYTE_ARRAY_TYPE:
				vf=new ByteArrayField(strFieldId,objExt.getByteArray());
				break;
			case ObjectExt.SHORT_ARRAY_TYPE:
				vf=new ShortArrayField(strFieldId,objExt.getShortArray());
				break;
			case ObjectExt.INTEGER_ARRAY_TYPE:
				vf=new IntegerArrayField(strFieldId,objExt.getIntegerArray());
				break;
			case ObjectExt.LONG_ARRAY_TYPE:
				vf=new LongArrayField(strFieldId,objExt.getLongArray());
				break;
			case ObjectExt.FLOAT_ARRAY_TYPE:
				vf=new FloatArrayField(strFieldId,objExt.getFloatArray());
				break;
			case ObjectExt.DOUBLE_ARRAY_TYPE:
				vf=new DoubleArrayField(strFieldId,objExt.getDoubleArray());
				break;
			case ObjectExt.STRING_ARRAY_TYPE:
				vf=new StringArrayField(strFieldId,objExt.getStringArray());
				break;
			case ObjectExt.DATE_ARRAY_TYPE:
				vf=new DateArrayField(strFieldId,objExt.getDateArray());
				break;
			case ObjectExt.TIME_ARRAY_TYPE:
				vf=new TimeArrayField(strFieldId,objExt.getTimeArray());
				break;
			case ObjectExt.DATETIME_ARRAY_TYPE:
				vf=new DateTimeArrayField(strFieldId,objExt.getDateTimeArray());
				break;
			case ObjectExt.CDO_ARRAY_TYPE:
				vf=new CDOArrayField(strFieldId,objExt.getCDOArray());
				break;
		}
		
		return vf;
    }
    
    public ValueField getFieldAt(int nIndex)
    {
    	String strFieldId=this.vecFieldId.get(nIndex);
    	ObjectExt objExt=this.vecItem.get(nIndex);
    	
    	return this.createField(strFieldId, objExt);
    }
    
    public ValueField getField(String strFieldId)
    {
    	ObjectExt objExt=this.getObject(strFieldId);
    	
    	return this.createField(strFieldId, objExt);
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
    
    
    public void setObjectExt(String strFieldId, ObjectExt object) throws RuntimeException
    {
  		int nType=object.getType();
		Object objValue=object.getValue();
		switch(nType)
		{
			case ObjectExt.BYTE_TYPE:
			{
				this.setByteValue(strFieldId,((Byte)objValue).byteValue());
				break;
			}
			case ObjectExt.SHORT_TYPE:
			{
				this.setShortValue(strFieldId,((Short)objValue).shortValue());
				break;
			}
			case ObjectExt.INTEGER_TYPE:
			{
				this.setIntegerValue(strFieldId,((Integer)objValue).intValue());
				break;
			}
			case ObjectExt.LONG_TYPE:
			{
				this.setLongValue(strFieldId,((Long)objValue).longValue());
				break;
			}
			case ObjectExt.FLOAT_TYPE:
			{
				this.setFloatValue(strFieldId,((Float)objValue).floatValue());
				break;
			}
			case ObjectExt.DOUBLE_TYPE:
			{
				this.setDoubleValue(strFieldId,((Double)objValue).doubleValue());
				break;
			}
			case ObjectExt.STRING_TYPE:
			{
				this.setStringValue(strFieldId,((String)objValue));
				break;
			}
			case ObjectExt.DATE_TYPE:
			{
				this.setDateValue(strFieldId,((String)objValue));
				break;
			}
			case ObjectExt.TIME_TYPE:
			{
				this.setTimeValue(strFieldId,((String)objValue));
				break;
			}
			case ObjectExt.DATETIME_TYPE:
			{
				this.setDateTimeValue(strFieldId,((String)objValue));
				break;
			}
			case ObjectExt.BYTE_ARRAY_TYPE:
			{
				this.setByteArrayValue(strFieldId,((byte[])objValue));
				break;
			}
			case ObjectExt.CDO_TYPE:
			{
				this.setCDOValue(strFieldId,(CDO)objValue);
				break;
			}
			case ObjectExt.CDO_ARRAY_TYPE:
				{
					this.setCDOArrayValue(strFieldId,(CDO[])objValue);
					break;
				}
			case ObjectExt.STRING_ARRAY_TYPE:
			{
				this.setStringArrayValue(strFieldId,(String[])objValue);
				break;
			}
			case  ObjectExt.LONG_ARRAY_TYPE:
			{
				this.setLongArrayValue(strFieldId,(long[])objValue);
				break;				
			}
			case ObjectExt.INTEGER_ARRAY_TYPE:
			{
				this.setIntegerArrayValue(strFieldId,(int[])objValue);
				break;						
			}
			case ObjectExt.SHORT_ARRAY_TYPE:
			{
				this.setShortArrayValue(strFieldId,(short[])objValue);
				break;						
			}
			case ObjectExt.DATETIME_ARRAY_TYPE:
			{
				this.setDateTimeArrayValue(strFieldId,(String[])objValue);
				break;				
			}
			case ObjectExt.DATE_ARRAY_TYPE:
			{
				this.setDateArrayValue(strFieldId,(String[])objValue);
				break;				
			}
			case ObjectExt.TIME_ARRAY_TYPE:
			{
				this.setTimeArrayValue(strFieldId,(String[])objValue);
				break;				
			}
			default://TODO 目前还不支持ReocrdSet
			{
				throw new RuntimeException("Unsupported type "+nType);
			}
		}
    }
	
	public void remove(String key)
	{
		//strXML=null;

		ObjectExt objExt=hmItem.remove(key);
		int nIndex=vecItem.indexOf(objExt);
		vecItem.remove(nIndex);
		vecFieldId.remove(nIndex);
		if(objExt.getType()==ObjectExt.FILE_TYPE){
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
	
	public Set<Map.Entry<String,ObjectExt>> entrySet(){
		return hmItem.entrySet();
	}
    
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CDO()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		hmItem	=new HashMap<String,ObjectExt>();
		//strXML	=null;
		vecItem	=new ArrayList<ObjectExt>();
		vecFieldId=new ArrayList<String>();
		
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

		int nSize=vecItem.size();
		for(int i=0;i<nSize;i++)
		{
			Field fieldItem=this.getFieldAt(i);
			str_JSON.append(fieldItem.toJSON());
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

		int nSize=vecItem.size();
		for(int i=0;i<nSize;i++)
		{
			Field fieldItem=(Field)this.getFieldAt(i);
			str_JSON.append(fieldItem.toJSONString());
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
		return this.toJSONString();
	}
	
	public static void main(String[] args)
	{
		CDO cdo = new CDO();
		System.out.print(cdo.clone().toXML());
		cdo.setStringValue("strName","张三");
		cdo.setIntegerValue("nAge",0);
		cdo.setDateValue("dBirthday","2000-01-01");
		int[] nsValue=new int[3];
		for(int i=0;i<nsValue.length;i++)
		{
			nsValue[i]=i;
		}
		cdo.setIntegerArrayValue("nsValue",nsValue);
		String[] strsValue=new String[3];
		for(int i=0;i<strsValue.length;i++)
		{
			strsValue[i]="Value"+i;
		}
		cdo.setStringArrayValue("strsValue",strsValue);
		CDO[] cdosList=new CDO[5];
		for(int i=0;i<cdosList.length;i++)
		{			
			cdosList[i]=new CDO();
			cdosList[i].setStringValue("strName","张三"+i);
			cdosList[i].setIntegerValue("nAge",0);
			cdosList[i].setDateValue("dBirthday","2000-01-01");
		}
		cdo.setCDOArrayValue("cdosList",cdosList);
		CDO cdoChild=new CDO();
		cdoChild.setStringValue("strName","张三");
		cdoChild.setIntegerValue("nAge",0);
		cdoChild.setDateValue("dBirthday","2000-01-01");
		cdoChild.setCDOArrayValue("cdosList",cdosList);
		cdoChild.setIntegerArrayValue("nsValue",nsValue);
		cdo.setCDOValue("cdoChild",cdoChild);
		cdo.setByteArrayValue("bytes", new byte[]{1,2,3});
//		String strXML=cdo.toXMLWithIndent();
		cdo=CDO.fromXML(cdo.toXML());
//		strXML=cdo.toXMLWithIndent();
		System.out.println(cdo.toXMLWithIndent());
		System.out.println(cdo.getCDOValue("cdoChild").toXML());
		System.out.println(cdo.toXML());
		System.out.println(cdo.toXMLLog());
		System.out.println(cdo.clone().toXML());
	}
}
