package com.cdoframework.cdolib.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.TypeMapping;
import com.cdoframework.cdolib.database.xsd.QField;
import com.cdoframework.cdolib.database.xsd.types.FieldTypeTypeType;

public class JsonUtil4NoSQL {
	private static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	private static DateFormat df=new SimpleDateFormat(DATE_TIME_FORMAT);

	/**
	 * 根据参数前缀 装配CDORequest
	 */
	private static final String CDOTYPE_LONG_PREFIX="l";
	private static final String CDOTYPE_STRING_PREFIX="str";
	private static final String CDOTYPE_INTEGER_PREFIX="n";
	private static final String CDOTYPE_BOOLEAN_PREFIX="b";
	private static final String CDOTYPE_DATETIME_PREFIX="dt";
	private static final String CDOTYPE_SHORT_PREFIX="sh";
	private static final String CDOTYPE_CDO_PREFIX="cdo";
	
	/**
	 * 
	 * @param cdoRequest
	 * @param map  查询字段 <字段名,字段值>
	 * @param isCDOType
	 * @param qFields  xml中<字段名，xml元数据[字段类型，用户自定义值等]>
	 * @param parentKey 
	 * @throws Exception
	 */
	public static void setCDORequest(CDO cdoRequest,Map map,boolean isCDOType, Map<String, QField> qFields,String parentKey) throws Exception
	{
		Object obj=null;
		CDO tmpCDO=null;
		for(Iterator it=map.keySet().iterator();it.hasNext();)
		{
			String key=(String)it.next();
			if(parentKey==null)
			{
				parentKey=key;
			}
			else if(parentKey!=null)
			{
				parentKey=parentKey+"."+key;
			}
			obj=map.get(key);
			if (obj instanceof Map) 
			{
				tmpCDO=new CDO();
				cdoRequest.setCDOValue(key, tmpCDO);
				setCDORequest(tmpCDO,(Map)obj,isCDOType, qFields,parentKey);
			}
			else if(obj instanceof List)
			{
				setCDORequest(cdoRequest,key,(List)obj,isCDOType, qFields,parentKey);
			}
			else
			{
				//设置字段的值
				setResulstDataType(qFields, parentKey, cdoRequest, key, obj, null, isCDOType);
				//处理复杂字段 [该字段不是 主类型 如 字段包含了map对象] 			
				parentKey=parentKey.substring(0,parentKey.lastIndexOf('.')!=-1?parentKey.lastIndexOf('.'):parentKey.length());
				
			}
		}
	}
	
	private static void setResulstDataType(Map<String, QField> qFields,String parentKey,
			CDO cdoRequest,String key,Object obj,List list,boolean isCDOType) throws Exception
	{
		QField qField = qFields.get(parentKey);
		if (qField==null)
		{
			if(list==null)
				setCDORequest(null,cdoRequest,key,obj,null,isCDOType);
			else
				//根据参数前缀字符判断要插入的 数据类型
				setCDORequest(null,cdoRequest,key,"",list,isCDOType);
		}
		else
		{
			String strValue=qField.getValue();
			String strName=qField.getName();
			String strAlias=qField.getAlias();
			FieldTypeTypeType xmlFieldType=qField.getType();
			int nDataType=DataType.NONE_TYPE;	
			if(qField.getName().contains("."))
			{//如果查询field name含有层级. 则必须有 alias别名
				if(strAlias==null||strAlias.trim().equals(""))
					throw new RuntimeException("QField ["+strName+"] 含有层级(.) 必须 设定alias属性");
			}							
			if(strAlias==null)
			{
				strAlias = strName;
			}						
			if(strValue==null)
			{//获取查询字段的值
				if(xmlFieldType!=null)
				{
					nDataType=TypeMapping.mappingToDataType(xmlFieldType);
					if(list==null)
						setCDOValue(cdoRequest,nDataType,strAlias,obj);
					else
						setCDOValue(cdoRequest,nDataType,strAlias,list.toArray()); 
				}
				else
				{
					if(list==null)
						setCDORequest(strAlias,cdoRequest,key,obj,null,isCDOType);
					else
						//根据参数前缀字符判断要插入的 数据类型
						setCDORequest(strAlias,cdoRequest,key,"",list,isCDOType);
				}	
			}
			else
			{//设定xml中 常量的值
				setFieldXmlValue(qField,cdoRequest,strAlias);									
			}		
		}
	}
		
	private static void setCDORequest(CDO cdoRequest,String key,List list,boolean isCDOType, Map<String, QField> qFields,String parentKey) throws Exception
	{
		Object obj=null;
		CDO[] tmpCDOs=new CDO[list.size()];
		if(isCDOType && list.size()==0)
		{
			if(key.startsWith(CDOTYPE_STRING_PREFIX))
			{
				cdoRequest.setStringArrayValue(key, new String[0]);	
			}
			else if(key.startsWith(CDOTYPE_DATETIME_PREFIX))
			{
				cdoRequest.setDateTimeArrayValue(key, new String[0]);	
			}
			else if(key.startsWith(CDOTYPE_CDO_PREFIX))
			{
				cdoRequest.setCDOArrayValue(key, new CDO[0]);	
			}
			else if(key.startsWith(CDOTYPE_SHORT_PREFIX))
			{
				cdoRequest.setShortArrayValue(key, new short[0]);	
			}
			else if(key.startsWith(CDOTYPE_LONG_PREFIX))
			{
				cdoRequest.setLongArrayValue(key, new long[0]);				
			}
			else if(key.startsWith(CDOTYPE_INTEGER_PREFIX))
			{
				cdoRequest.setIntegerArrayValue(key, new int[0]);	
			}
			else if(key.startsWith(CDOTYPE_BOOLEAN_PREFIX))
			{
				cdoRequest.setBooleanArrayValue(key, new boolean[0]);
			}		
		}

		for(int i=0;i<list.size();i++)
		{
			obj=list.get(i);
			if(obj instanceof Map)
			{
				tmpCDOs[i]=new CDO();
				cdoRequest.setCDOArrayValue(key,tmpCDOs);
				setCDORequest(tmpCDOs[i],(Map)obj,isCDOType, qFields,parentKey);
			}
			else
			{// 如果list的内容不为Map形式，则必为主数据类型
				if(i==0)
				{
					setResulstDataType(qFields, parentKey, cdoRequest, parentKey, "", list, isCDOType);
					break;
				}
			}
		}
	}

	/**
	 * 根据 参数key前缀 装配CDO 数据类型
	* @param isCDOType true 表示转换成CDO对应的类型 
	*         必须满足 CDOTYPE_前缀条件
	 *        false 表示 按照一般性处理 转换成CDO String类型
	 * @param cdoRequest
	 * @param key
	 * @param list
	 * @throws Exception
	 */
	public static void setCDORequest(String strAlias,CDO cdoRequest,String key,Object obj,List list,boolean isCDOType) throws Exception
	{
		if(strAlias==null)
		{
			strAlias = key;
		}
		boolean isArrflag=false;
		if(list!=null)
			isArrflag=true;
		try
		{
			if(isCDOType)
			{
				if(key.startsWith(CDOTYPE_STRING_PREFIX))
				{// 处理 String
					String value="";
					if(isArrflag)
					{
						String str[]=new String[list.size()];
						for(int i=0;i<list.size();i++)
						{
							value=getStr(list.get(i));
							str[i]=value;
						}
						cdoRequest.setStringArrayValue(strAlias,str);
					}
					else
					{
						value = getStr(obj);
						if(value==null)
					    	return;
						cdoRequest.setStringValue(strAlias,value);
					}
					return;
				}
				else if(key.startsWith(CDOTYPE_DATETIME_PREFIX))
				{// 处理 DateTime
					String value="";
					if(isArrflag)
					{
						String str[]=new String[list.size()];
						for(int i=0;i<list.size();i++)
						{
							value=getStr(list.get(i));
							if(value!=null&&value.trim().length()>0)
							{
								str[i]=Utility.parseDateTimeValue(value);
							}else
							{
								str[i]=value;
							}
						}
						cdoRequest.setDateTimeArrayValue(strAlias,str);
					}
					else
					{
						//处理mongodb null数据
						value = getStr(obj);
						if(value==null)
					    	return;
						value=Utility.parseDateTimeValue(value);
						cdoRequest.setDateTimeValue(strAlias,value);
					}
					return;
				}
				else if(key.startsWith(CDOTYPE_BOOLEAN_PREFIX))
				{// 处理 BOOEAN
					String value="";
					if(isArrflag)
					{
						boolean b[]=new boolean[list.size()];
						for(int i=0;i<list.size();i++)
						{
							value=getStr(list.get(i));
							b[i]=false;
							if(value!=null&& value.trim().length()>0)
								b[i]=Utility.parseBooleanValue(value);							
						}
						cdoRequest.setBooleanArrayValue(strAlias,b);
					}
					else
					{
						value=getStr(obj);
						if(value==null)
					    	return;					
						boolean b=false;					
						if(value!=null&& value.trim().length()>0)
							b=Utility.parseBooleanValue(value);
						cdoRequest.setBooleanValue(strAlias,b);
					}
					return;
				}
				else if(key.startsWith(CDOTYPE_LONG_PREFIX))
				{// 处理 Long 
					String value="";
					if(isArrflag)
					{
						long ls[]=new long[list.size()];
						for(int i=0;i<list.size();i++)
						{
							value=getStr(list.get(i));
							if(value!=null&& value.trim().length()>0)
//								ls[i]=new Long(value);
								ls[i]=new Double(value).longValue();
						}
						cdoRequest.setLongArrayValue(strAlias,ls);
					}
					else
					{
						value=getStr(obj);
						if(value==null || value.trim().equals(""))
					    	return;										
						long l=0;				
//						l=new Long(value);
						l=new Double(value).longValue();
						cdoRequest.setLongValue(strAlias,l);

					}
					return;
				}
				else if(key.startsWith(CDOTYPE_INTEGER_PREFIX) || key.startsWith(CDOTYPE_SHORT_PREFIX))
				{// 处理 Integer ,后期修改, 因为传过来的有可能
					String value="";
					if(isArrflag)
					{
						int ns[]=new int[list.size()];
						for(int i=0;i<list.size();i++)
						{
							value=getStr(list.get(i));
							if(value!=null&&value.trim().length()>0)
//								ns[i]=new Integer(value);
								ns[i]=new Double(value).intValue();
						}
						cdoRequest.setIntegerArrayValue(strAlias,ns);
					}
					else
					{
						value=getStr(obj);
						if(value==null || value.trim().equals(""))
					    	return;	
						int n=0;
//						n=new Integer(value);
						n=new Double(value).intValue();
						cdoRequest.setIntegerValue(strAlias,n);
					}
				}				
			}else
			{//按照一般性处理，都作为String 处理
				String value="";
				if(isArrflag)
				{
					String str[]=new String[list.size()];
					for(int i=0;i<list.size();i++)
					{
						value=getStr(list.get(i));
						str[i]=value;
					}
					cdoRequest.setStringArrayValue(strAlias,str);
				}
				else
				{
					value = getStr(obj);
					if(value==null)
				    	return;
					cdoRequest.setStringValue(strAlias,value);
				}				
				
			}
		}
		catch(Exception ex)
		{
			throw new Exception(ex);
		}
	}
	
	/**
	 * 查询取xml中的值
	 */
	private static void setFieldXmlValue(QField qField, CDO cdo, String key)
	{
		try
		{
			String strValue = qField.getValue();
			int dataType = TypeMapping.mappingToDataType(qField.getType());
			if (strValue != null)
			{
				setCDOValue(cdo, dataType, key, strValue);
			}
		}
		catch (Exception ex)
		{
			throw new RuntimeException(" Constants XML Error:"
					+ ex.getMessage());
		}
	}

	public static void setCDOValue(CDO cdo, int nDataType, String key,
			Object value)
	{
		switch (nDataType)
		{
		case DataType.STRING_TYPE:
			cdo.setStringValue(key, Utility.parseStingValue(value));
			break;
		case DataType.INTEGER_TYPE:
			cdo.setIntegerValue(key, Utility.parseIntegerValue(value));
			break;
		case DataType.LONG_TYPE:
			cdo.setLongValue(key, Utility.parseLongValue(value));
			break;
		case DataType.DATE_TYPE:
			cdo.setDateValue(key, Utility.parseDateValue(value));
			break;
		case DataType.DATETIME_TYPE:
			cdo.setDateTimeValue(key, Utility.parseDateTimeValue(value));
			break;
		case DataType.TIME_TYPE:
			cdo.setTimeValue(key, Utility.parseTimeValue(value));
			break;
		case DataType.SHORT_TYPE:
			cdo.setShortValue(key, Utility.parseShortValue(value));
			break;
		case DataType.FLOAT_TYPE:
			cdo.setFloatValue(key, Utility.parseFloatValue(value));
			break;
		case DataType.BOOLEAN_TYPE:
			cdo.setBooleanValue(key, Utility.parseBooleanValue(value));
			break;
		case DataType.DOUBLE_TYPE:
			cdo.setDoubleValue(key, Utility.parseDoubleValue(value));
			break;
		case DataType.CDO_TYPE:
			cdo.setCDOValue(key, (CDO) value);
			break;
		case DataType.BOOLEAN_ARRAY_TYPE:
			cdo.setBooleanArrayValue(key, Utility.parseBooleanArrayValue(value));
			break;
		case DataType.SHORT_ARRAY_TYPE:
			cdo.setShortArrayValue(key, Utility.parseShortArrayValue(value));
			break;
		case DataType.INTEGER_ARRAY_TYPE:
			cdo.setIntegerArrayValue(key, Utility.parseIntegerArrayValue(value));
			break;
		case DataType.LONG_ARRAY_TYPE:
			cdo.setLongArrayValue(key, Utility.parseLongArrayValue(value));
			break;
		case DataType.FLOAT_ARRAY_TYPE:
			cdo.setFloatArrayValue(key, Utility.parseFloatArrayValue(value));
			break;
		case DataType.DOUBLE_ARRAY_TYPE:
			cdo.setDoubleArrayValue(key, Utility.parseDoubleArrayValue(value));
			break;
		case DataType.STRING_ARRAY_TYPE:
			cdo.setStringArrayValue(key, (String[]) value);
			break;
		case DataType.DATE_ARRAY_TYPE:
			cdo.setDateArrayValue(key, (String[]) value);
		case DataType.TIME_ARRAY_TYPE:
			cdo.setTimeArrayValue(key, (String[]) value);
		case DataType.DATETIME_ARRAY_TYPE:
			cdo.setDateTimeArrayValue(key, (String[]) value);
		case DataType.CDO_ARRAY_TYPE:
			cdo.setCDOArrayValue(key, (CDO[]) value);
			break;
		default:
			throw new RuntimeException("  Type is not unSupported!");
		}
	}

	private static String getStr(Object obj)
	{
		if(obj==null)
			return null;
		String str = obj.toString();
		if("null".equals(str))
		{
			return null;
			
		}		
		return str;
	}
	
}
