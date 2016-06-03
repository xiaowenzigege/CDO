package com.cdoframework.cdolib.service.framework;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheKey;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheValue;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.Creteria;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.Creterias;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasType;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CriteriaTypeOperatorType;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CriteriaTypeTypeType;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyTypeType;

public class FrameworkUtil
{
	  public static String 	IN_SEPRATOR	=",";
	  public static String FUNCTION_DATETIME="now()";
	  public static String TRANSACTION_OLD_REQUEST = "cdoOldRequest";
	  public static String TRANSACTION_OLD_RESPONSE = "cdoOldResponse";
	  public static String TRANSACTION_OLD_RESULT = "cdoOldResult";
	  
	  static Logger logger = Logger.getLogger(FrameworkUtil.class);
	    
	public static boolean isFitCreterias(CreteriasType cts,CDO cdoRequest) throws Exception
	{
    	if(cts==null)
    	{
    		return true;
    	}
    	if(cts.getType().value().equals(com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CreteriasTypeItemTypeType.AND.value()))
    	{
    		return isAndFitCreterias(cts,cdoRequest);
    	}
    	else
    	{
    		return isOrFitCreterias(cts,cdoRequest);
    	}
	}


	public static boolean isOrFitCreterias(CreteriasType cts,CDO cdoRequest) throws Exception
	{
		int nCount = cts.getCreteriasTypeItemCount();
		for(int i=0;i<nCount;i++)
		{
			CreteriasTypeItem item = cts.getCreteriasTypeItem(i);
			Creteria ct = item.getCreteria();
			boolean isFit = false;
			if(ct!=null)
			{
				isFit = isFitCreteria(ct,cdoRequest);
			}
			else
			{
				Creterias subCts = item.getCreterias();
				isFit = isFitCreterias(subCts,cdoRequest);
			}
			if(isFit==true)
			{
				return true;
			}
		}
		return false;
	}


	public static  boolean isAndFitCreterias(CreteriasType cts,CDO cdoRequest) throws Exception
	{
		int nCount = cts.getCreteriasTypeItemCount();
		for(int i=0;i<nCount;i++)
		{
			CreteriasTypeItem item = cts.getCreteriasTypeItem(i);
			Creteria ct = item.getCreteria();
			boolean isFit = true;
			if(ct!=null)
			{
				isFit = isFitCreteria(ct,cdoRequest);
			}
			else
			{
				Creterias subCts = item.getCreterias();
				isFit = isFitCreterias(subCts,cdoRequest);
			}
			if(isFit==false)
			{
				return false;
			}
		}
		return true;
	}


	public static boolean isFitCreteria(Creteria ct,CDO cdoRequest) throws Exception
	{
		String strName = ct.getName();
		if(strName==null )
		{
			return false;
		}
		if(strName.startsWith("{") && strName.endsWith("}"))
		{
			strName = strName.substring(1,strName.length()-1);
		}
		CriteriaTypeOperatorType OperatorType = ct.getOperator();
		if(!cdoRequest.exists(strName))
		{
			if(OperatorType.value().equals(CriteriaTypeOperatorType.VALUE_6.value()))
			{//ISNULL
				return true;
			}
			else 
			{
				return false;
			}
		}
		if(OperatorType.value().equals(CriteriaTypeOperatorType.VALUE_7.value()))
		{//ISNOTNULL
			return true;
		}
		if(OperatorType.value().equals(CriteriaTypeOperatorType.VALUE_6.value()))
		{//ISNULL
			return false;
		}
		Object objValue1 = cdoRequest.getObjectValue(strName);

		
		String strValue = ct.getValue();
		CriteriaTypeTypeType nType = ct.getType();
		
		switch ( OperatorType)
		{
			case VALUE_0:
				{//=
					if(strValue==null)
					{
						return false;
					}
					
					switch(nType)
					{
						case INTEGER:
						case LONG:
						case STRING:
						case SHORT:
						case BYTE:
						case DATETIME:
						case DATE:
						case TIME:							
							{
								if(objValue1.toString().equals(strValue))
								{
									return true;
								}
								else
								{
									return false;
								}
							}
						case DOUBLE:
						case FLOAT:
							{
								double value1 = Double.valueOf(objValue1.toString());
								double value2 = Double.valueOf(strValue);
								if(value1==value2)
								{
									return true;
								}
								else
								{
									return false;
								}
							}
						default:
							{
								return false;
							}
					}
				}
			case VALUE_1:
				{//!=
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case INTEGER:
						case LONG:
						case STRING:
						case SHORT:
						case BYTE:
						case DATETIME:
						case DATE:
						case TIME:							
							{
								if(objValue1.toString().equals(strValue))
								{
									return false;
								}
								else
								{
									return true;
								}
							}
						case DOUBLE:
						case FLOAT:
							{
								double value1 = Double.valueOf(objValue1.toString());
								double value2 = Double.valueOf(strValue);
								if(value1==value2)
								{
									return false;
								}
								else
								{
									return true;
								}
							}
						default:
							{
								return false;
							}
					}
				}
			case VALUE_2:
				{//>
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case INTEGER:
						case LONG:						
						case SHORT:
						case BYTE:
							{
								return Utility.compareLong(objValue1,strValue)>0;
							}
						case STRING:
						case DATETIME:
						case DATE:
						case TIME:							
							{
								return Utility.compareString(objValue1,strValue)>0;
							}
						case DOUBLE:
						case FLOAT:
							{
								return Utility.compareDouble(objValue1,strValue)>0;
							}
						default:
							{
								return false;
							}
					}
				}
			case VALUE_3:
				{//>=
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case INTEGER:
						case LONG:						
						case SHORT:
						case BYTE:
							{
								return Utility.compareLong(objValue1,strValue)>=0;
							}
						case STRING:
						case DATETIME:
						case DATE:
						case TIME:							
							{
								return Utility.compareString(objValue1,strValue)>=0;
							}
						case DOUBLE:
						case FLOAT:
							{
								return Utility.compareDouble(objValue1,strValue)>=0;
							}
						default:
							{
								return false;
							}
					}
				}
			case VALUE_4:
				{//<
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case INTEGER:
						case LONG:						
						case SHORT:
						case BYTE:
							{
								return Utility.compareLong(objValue1,strValue)<0;
							}
						case STRING:
						case DATETIME:
						case DATE:
						case TIME:							
							{
								return Utility.compareString(objValue1,strValue)<0;
							}
						case DOUBLE:
						case FLOAT:
							{
								return Utility.compareDouble(objValue1,strValue)<0;
							}
						default:
							{
								return false;
							}
					}
				}
			case VALUE_5:
				{//<=
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case INTEGER:
						case LONG:						
						case SHORT:
						case BYTE:
							{
								return Utility.compareLong(objValue1,strValue)<=0;
							}
						case STRING:
						case DATETIME:
						case DATE:
						case TIME:							
							{
								return Utility.compareString(objValue1,strValue)<=0;
							}
						case DOUBLE:
						case FLOAT:
							{
								return Utility.compareDouble(objValue1,strValue)<=0;
							}
						default:
							{
								return false;
							}
					}
				}
			case VALUE_8:
				{//MATCH
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case STRING:
							{
								String strObjectValue1 = objValue1.toString();
								if(strObjectValue1.matches(strValue))
								{
									return true;
								}
								else
								{
									return false;
								}
							}
						default:
							{
								throw new Exception("The match operator does not support all type but String ");
							}							
					}
					
				}
			case VALUE_9:
				{//NOT MATCH
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case STRING:
							{
								String strObjectValue1 = objValue1.toString();
								if(strObjectValue1.matches(strValue))
								{
									return false;
								}
								else
								{
									return true;
								}
							}
						default:
							{
								throw new Exception("The not match operator does not support all type but String ");
							}							
					}
				}
			case VALUE_10:
				{//IN
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case INTEGER:
						case LONG:						
						case SHORT:
						case BYTE:
						case STRING:
							{
								String strObjectValue1 = objValue1.toString();
								if(strObjectValue1.equalsIgnoreCase(strValue))
								{
									return true;
								}
								strValue =	IN_SEPRATOR+strValue+IN_SEPRATOR;
								strObjectValue1	=	IN_SEPRATOR+strObjectValue1+IN_SEPRATOR;
								if(strValue.indexOf(strObjectValue1)!=-1)
								{
									return true;
								}
								else
								{
									return false;
								}
							}
						case DATETIME:
						case DATE:
						case TIME:							
							{
								String[] strsDateTime = strValue.toString().split(IN_SEPRATOR);
								if(strsDateTime.length!=2)
								{
									throw new Exception("AS to Date,DateTime,Time type, two value ard expected under the IN operator  ");
								}
								
								Date dt = DateTime.parse(Utility.parseDateTimeValue(objValue1),com.cdoframework.cdolib.base.DataType.DATETIME_FORMAT_STRING).toDate();
								Date dt1 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[0]),com.cdoframework.cdolib.base.DataType.DATETIME_FORMAT_STRING).toDate();
								Date dt2 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[1]),com.cdoframework.cdolib.base.DataType.DATETIME_FORMAT_STRING).toDate();
								if(dt.compareTo(dt1)>=0 && dt.compareTo(dt2)<=0)
								{
									return true;
								}
								else
								{
									return false;
								}
							}
						case DOUBLE:
						case FLOAT:
							{
								double d1 = Utility.parseDoubleValue(objValue1);
								String strObjectValue2 = strValue.toString();
								String[] strsValue2 = strObjectValue2.split(IN_SEPRATOR);
								for(String s:strsValue2)
								{
									if(d1==Double.parseDouble(s))
									{
										return true;
									}
								}
								return false;
							}
						default:
							{
								return false;
							}
					}
				}
			case VALUE_11:
				{//NOT IN
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case INTEGER:
						case LONG:						
						case SHORT:
						case BYTE:
						case STRING:
							{
								String strObjectValue1 = objValue1.toString();
								String strObjectValue2 = strValue;
								if(strObjectValue1.equalsIgnoreCase(strObjectValue2))
								{
									return true;
								}
								strObjectValue2 =	IN_SEPRATOR+strObjectValue2+IN_SEPRATOR;
								strObjectValue1	=	IN_SEPRATOR+strObjectValue1+IN_SEPRATOR;
								if(strObjectValue2.indexOf(strObjectValue1)==-1)
								{
									return true;
								}
								else
								{
									return false;
								}
							}
						case DATETIME:
						case DATE:
						case TIME:							
							{
								String[] strsDateTime = strValue.toString().split(IN_SEPRATOR);
								if(strsDateTime.length!=2)
								{
									throw new Exception("AS to Date,DateTime,Time type, two value ard expected under the IN operator  ");
								}
								
								Date dt = DateTime.parse(Utility.parseDateTimeValue(objValue1),com.cdoframework.cdolib.base.DataType.DATETIME_FORMAT_STRING).toDate();
								Date dt1 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[0]),com.cdoframework.cdolib.base.DataType.DATETIME_FORMAT_STRING).toDate();
								Date dt2 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[1]),com.cdoframework.cdolib.base.DataType.DATETIME_FORMAT_STRING).toDate();
								if(dt.compareTo(dt1)<0 || dt.compareTo(dt2)>0)
								{
									return true;
								}
								else
								{
									return false;
								}
							}
						case DOUBLE:
						case FLOAT:
							{
								double d1 = Utility.parseDoubleValue(objValue1);
								String strObjectValue2 = strValue.toString();
								String[] strsValue2 = strObjectValue2.split(IN_SEPRATOR);
								for(String s:strsValue2)
								{
									if(d1==Double.parseDouble(s))
									{
										return false;
									}
								}
								return true;
							}
						default:
							{
								return false;
							}
					}
				}				
			default:
				{
					throw new Exception ("unsupport operator type "+ct.getOperator());
				}
		}
	}
	public static String getString(int nStringType,String strContent,CDO cdoRequest,CDO cdoResponse,char left,char right) throws Exception
	{
		return getString(-1,null,nStringType,strContent,cdoRequest,cdoResponse);
	}
	
	public static String getString(int fromIndex,String strIndexId,int nStringType,String strContent,CDO cdoRequest,CDO cdoResponse,char left,char right) throws Exception
	{
		int nLength = strContent.length();
		int nState=0;// 0 : () 之外的字符, 1: ()之内字符.
		StringBuilder strbURL=new StringBuilder(nLength);
		StringBuilder strbParaName=new StringBuilder(nLength);
		
		int i=0;
		while(i<nLength)
		{
			char ch=strContent.charAt(i);
			if(ch==left)
			{
				if(nState==0)
				{// 在()之外
					if(i+1<nLength&&strContent.charAt(i+1)==left)
					{// 为普通字符
						strbURL.append(left);
						i+=2;
					}
					else
					{// 字段开始
						nState=1;
						i++;
					}
				}
				else
				{// 在()之内，语法错误
					callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
					return null;
				}
			}
			else if(ch==right)
			{
				if(nState==0)
				{// 在()之外
					if(i+1<nLength&&strContent.charAt(i+1)==right)
					{// 为普通字符
						strbURL.append(right);
						i++;
					}
					else
					{// 语法错误
						callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
						return null;
					}
				}
				else
				{// 在()之内，字段结束
					if(strbParaName.length()==0)
					{
						callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
						return null;
					}
					nState=0;
					String strFieldId = strbParaName.toString();
					if(fromIndex>-1)
					{
						String reg = "["+strIndexId+"]";
						String rep = "["+fromIndex+"]";
						strFieldId = strFieldId.replace(reg,rep);
					}
					String strValue = getFiledString(nStringType,strFieldId,cdoRequest,cdoResponse);
					strbURL.append(strValue);
					strbParaName.setLength(0);
				}
				i++;
			}
			else
			{
				if(nState==0)
				{// 在()之外
					strbURL.append(ch);
				}
				else
				{
					strbParaName.append(ch);
				}
				i++;
			}
		}

		if(nState==1)
		{
			callOnException("parse trans url error",new Exception("trans url  syntax Error: "+strContent));
			return null;
		}

		return strbURL.toString();
	}
	
	public static String getString(int nStringType,String strContent,CDO cdoRequest,CDO cdoResponse) throws Exception
	{
		int nLength = strContent.length();
		int nState=0;// 0 : () 之外的字符, 1: ()之内字符.
		StringBuilder strbURL=new StringBuilder(nLength);
		StringBuilder strbParaName=new StringBuilder(nLength);
		
		int i=0;
		while(i<nLength)
		{
			char ch=strContent.charAt(i);
			switch(ch)
			{
				case '(':
					if(nState==0)
					{// 在()之外
						if(i+1<nLength&&strContent.charAt(i+1)=='(')
						{// 为普通字符
							strbURL.append("(");
							i+=2;
						}
						else
						{// 字段开始
							nState=1;
							i++;
						}
					}
					else
					{// 在()之内，语法错误
						callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
						return null;
					}
					break;
				case ')':
					if(nState==0)
					{// 在()之外
						if(i+1<nLength&&strContent.charAt(i+1)==')')
						{// 为普通字符
							strbURL.append(")");
							i++;
						}
						else
						{// 语法错误
							callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
							return null;
						}
					}
					else
					{// 在()之内，字段结束
						if(strbParaName.length()==0)
						{
							callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
							return null;
						}
						nState=0;
						String strFieldId = strbParaName.toString();
						String strValue = getFiledString(nStringType,strFieldId,cdoRequest,cdoResponse);
						strbURL.append(strValue);
						strbParaName.setLength(0);
					}
					i++;
					break;
				default:
					if(nState==0)
					{// 在()之外
						strbURL.append(ch);
					}
					else
					{
						strbParaName.append(ch);
					}
					i++;
					break;
			}
		}

		if(nState==1)
		{
			callOnException("parse trans url error",new Exception("trans url  syntax Error: "+strContent));
			return null;
		}

		return strbURL.toString();
	}

	public static String getString(int fromIndex,String strIndexId,int nStringType,String strContent,CDO cdoRequest,CDO cdoResponse) throws Exception
	{

		int nLength = strContent.length();
		int nState=0;// 0 : () 之外的字符, 1: ()之内字符.
		StringBuilder strbURL=new StringBuilder(nLength);
		StringBuilder strbParaName=new StringBuilder(nLength);
		
		int i=0;
		while(i<nLength)
		{
			char ch=strContent.charAt(i);
			switch(ch)
			{
				case '(':
					if(nState==0)
					{// 在()之外
						if(i+1<nLength&&strContent.charAt(i+1)=='(')
						{// 为普通字符
							strbURL.append("(");
							i+=2;
						}
						else
						{// 字段开始
							nState=1;
							i++;
						}
					}
					else
					{// 在()之内，语法错误
						callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
						return null;
					}
					break;
				case ')':
					if(nState==0)
					{// 在()之外
						if(i+1<nLength&&strContent.charAt(i+1)==')')
						{// 为普通字符
							strbURL.append(")");
							i++;
						}
						else
						{// 语法错误
							callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
							return null;
						}
					}
					else
					{// 在()之内，字段结束
						if(strbParaName.length()==0)
						{
							callOnException("analyze trans url error",new Exception("trans url  syntax Error: "+strContent));
							return null;
						}
						nState=0;
						String strFieldId = strbParaName.toString();
						if(fromIndex>-1)
						{
							String reg = "["+strIndexId+"]";
							String rep = "["+fromIndex+"]";
							strFieldId = strFieldId.replace(reg,rep);
						}
						String strValue = getFiledString(nStringType,strFieldId,cdoRequest,cdoResponse);
						strbURL.append(strValue);
						strbParaName.setLength(0);
					}
					i++;
					break;
				default:
					if(nState==0)
					{// 在()之外
						strbURL.append(ch);
					}
					else
					{
						strbParaName.append(ch);
					}
					i++;
					break;
			}
		}

		if(nState==1)
		{
			callOnException("parse trans url error",new Exception("trans url  syntax Error: "+strContent));
			return null;
		}

		return strbURL.toString();
	}
	public static Object getValue(CacheValue cacheValue,CDO cdoRequest,CDO cdoResponse)
	{
		return getValue(-1,null,cacheValue,cdoRequest,cdoResponse);
	}
	
	public static Object getValue(int fromIndex,String strIndexId,CacheValue cacheValue,CDO cdoRequest,CDO cdoResponse)
	{
		if(cacheValue==null)
		{
			if(cdoResponse==null){return null;}
			return cdoResponse.toXML();
		}
		String strFieldId = cacheValue.getFieldId();
		if(strFieldId==null)
		{
			return cdoResponse.toXML();
		}
		if(fromIndex>-1)
		{
			String reg = "["+strIndexId+"]";
			String rep = "["+fromIndex+"]";
			strFieldId = strFieldId.replace(reg,rep);
		}
		if(cdoResponse!=null && cdoResponse.exists(strFieldId))
		{
			return cdoResponse.getField(strFieldId);
		}
		else if(cdoRequest!=null &&cdoRequest.exists(strFieldId))
		{
			return cdoRequest.getField(strFieldId);
		}
		return null;
	}
	
	public static String getKey(CacheKey cacheKey,CDO cdoRequest,CDO cdoResponse) throws Exception
	{
		if(cacheKey==null)
		{
			return cdoRequest.toJSON();
		}
		String strContent = cacheKey.getContent();
//		int nType = cacheKey.getType().getType();
		return FrameworkUtil.getString(getKeyType(cacheKey),strContent,cdoRequest,cdoResponse);
	}
	
	public static String getKey(int fromIndex,String strIndexId,CacheKey cacheKey,CDO cdoRequest,CDO cdoResponse) throws Exception
	{
		if(cacheKey==null)
		{
			return cdoRequest.toJSON();
		}
		String strContent = cacheKey.getContent();
//		int nType = cacheKey.getType().getType();
		return FrameworkUtil.getString(fromIndex,strIndexId,getKeyType(cacheKey),strContent,cdoRequest,cdoResponse);
	}	
	
	

	private static void callOnException(String strMessage,Exception e)
	{
		logger.error(strMessage,e);
		
	}


	private static String getFiledString(int nStringType,String strFieldId,CDO cdoRequest,CDO cdoResponse) throws Exception
	{
		Field vf = null;
		if(cdoResponse.exists(strFieldId))
		{
			vf = cdoResponse.getField(strFieldId);
		}
		else if(cdoRequest.exists(strFieldId))
		{
			vf = cdoRequest.getField(strFieldId);
		}
		else
		{
			return null;
		}
		String strResult = null;
		switch (nStringType)
		{
			case 0:
				{//普通字串
					strResult = vf.getObjectValue().toString();
					break;
				}
			case 1:
				{//json串
					strResult = vf.toJSON();
					int index = strResult.indexOf(":");
					if(index!=-1)
					{
						strResult = strResult.substring(index+1);
					}
					index = strResult.lastIndexOf(",");
					if(index !=-1)
					{
						strResult = strResult.substring(0,index);
					}
					break;
				}
			case 2:
				{//XML串
					StringBuilder sb = new StringBuilder();
					vf.toXML(sb);
					strResult = sb.toString();
				}
		}
		return strResult;
	}
	

	public static boolean isFitReturnCodeCondition(ReturnCode returnCode,Return transHandleResult)
	{
		if(transHandleResult==null)
		{
			return true;
		}
		int nValue = returnCode.getValue();
		int nCode = transHandleResult.getCode();
		switch(returnCode.getOperator())
		{
			case VALUE_0:
			{//=
				return nCode == nValue;
			}
			case VALUE_1:
			{//!=
				return nCode != nValue;
			}
			case VALUE_2:
			{//>
				return nCode > nValue; 
			}
			case VALUE_3:
			{//>=
				return nCode >= nValue;
			}
			case VALUE_4:
			{//<
				return nCode < nValue;
			}
			case VALUE_5:
			{//<=
				return nCode <= nValue;
			}
			default:
			{
				return nCode == nValue;
			}
		}
	}
	
	public static int mappingToDataType(RequestKeyTypeType type)
	{
		if(type==null)
		{
			return DataType.NONE_TYPE;
		}
//		int nType = type.getType();
		switch(type)
		{
			case STRING:
				{
					return DataType.STRING_TYPE;
				}
			case INTEGER:
				{
					return DataType.INTEGER_TYPE;
				}
			case LONG:
				{
					return DataType.LONG_TYPE;
				}
			case DATETIME:
				{
					return DataType.DATETIME_TYPE;
				}
			case STRINGARRAY:
				{
					return DataType.STRING_ARRAY_TYPE;
				}
			case BOOLEAN:
				{
					return DataType.BOOLEAN_TYPE;
				}
			case BOOLEANARRAY:
				{
					return DataType.BOOLEAN_ARRAY_TYPE;
				}
			case BYTE:
				{
					return DataType.BYTE_TYPE;
				}
			case BYTEARRAY:
				{
					return DataType.BYTE_ARRAY_TYPE;
				}
			case CDO:
				{
					return DataType.CDO_TYPE;
				}
			case CDOARRAY:
				{
					return DataType.CDO_ARRAY_TYPE;
				}
			case DATE:
				{
					return DataType.DATE_TYPE;
				}
			case DATEARRAY:
				{
					return DataType.DATE_ARRAY_TYPE;
				}
			
			case DATETIMEARRAY:
				{
					return DataType.DATETIME_ARRAY_TYPE;
				}
			case DOUBLE:
				{
					return DataType.DOUBLE_TYPE;
				}
			case DOUBLEARRAY:
				{
					return DataType.DOUBLE_ARRAY_TYPE;
				}
			case FLOAT:
				{
					return DataType.FLOAT_TYPE;
				}
			case FLOATARRAY:
				{
					return DataType.FLOAT_ARRAY_TYPE;
				}
			
			case INTEGERARRAY:
				{
					return DataType.INTEGER_ARRAY_TYPE;
				}
			
			case LONGARRAY:
				{
					return DataType.LONG_ARRAY_TYPE;
				}
			case SHORT:
				{
					return DataType.SHORT_TYPE;
				}
			case SHORTARRAY:
				{
					return DataType.SHORT_ARRAY_TYPE;
				}
			case TIME:
				{
					return DataType.TIME_TYPE;
				}
			case TIMEARRAY:
				{
					return DataType.TIME_ARRAY_TYPE;
				}
			default:
				{
					return DataType.NONE_TYPE;
				}
		}
	}
	
	public static void setConstantsObject(CDO cdoData,String fieldName,int nDataType,Object value) throws Exception
	{		
		switch(nDataType)
		{	
			case DataType.STRING_TYPE:
				cdoData.setStringValue(fieldName, value.toString());
				break;	
			case DataType.LONG_TYPE:
				cdoData.setLongValue(fieldName, Utility.parseLongValue(value));
				break;				
			case DataType.SHORT_TYPE:
				cdoData.setShortValue(fieldName, Utility.parseShortValue(value));
				break;
			case DataType.INTEGER_TYPE:
				cdoData.setIntegerValue(fieldName, Utility.parseIntegerValue(value));
				break;
			case DataType.DATETIME_TYPE:
				{
					String strDT = null;
					if(FUNCTION_DATETIME.equalsIgnoreCase(value.toString()))
					{
						try
						{
							strDT = DateTime.Now().toString(DataType.DATETIME_FORMAT_STRING);
						}catch(Exception e)
						{//保证不会出错							
						}
					}
					else
					{
						strDT = Utility.parseDateTimeValue(value);
					}
					cdoData.setDateTimeValue(fieldName,strDT);
					break;	
				}
			case DataType.BOOLEAN_TYPE:
				cdoData.setBooleanValue(fieldName,Utility.parseBooleanValue(value));
				break;					
			case DataType.DATE_TYPE:
				{
					String strDate = null;
					if(FUNCTION_DATETIME.equalsIgnoreCase(value.toString()))
					{
						try
						{
							strDate = DateTime.Now().toString(DataType.DATE_FORMAT_STRING);
						}catch(Exception e)
						{//保证不会出错							
						}
					}
					else
					{
						strDate = Utility.parseDateValue(value);
					}
					cdoData.setDateValue(fieldName,strDate);
					break;	
				}
			case DataType.TIME_TYPE:
				{
					String strTime = null;
					if(FUNCTION_DATETIME.equalsIgnoreCase(value.toString()))
					{
						try
						{
							strTime = DateTime.Now().toString(DataType.TIME_FORMAT_STRING);
						}catch(Exception e)
						{//保证不会出错							
						}
					}
					else
					{
						strTime = Utility.parseTimeValue(value);
					}
					cdoData.setTimeValue(fieldName,strTime);
					break;	
				}
			case DataType.DOUBLE_TYPE:
				cdoData.setDoubleValue(fieldName,Utility.parseDoubleValue(value));
				break;
			case DataType.FLOAT_TYPE:
				cdoData.setFloatValue(fieldName,Utility.parseFloatValue(value));
				break;
			default:								
				throw new Exception( " Constants fieldName ["+fieldName+"]  unsupport type!");
		}
	}
	
	/**
	 * @param returnCode
	 * @param ret
	 * @return
	 */
	
	public static boolean isFitReturn(ReturnCode returnCode,Return ret)
	{
		if(returnCode==null )
		{
			return true;
		}			
		if(ret==null){return false;}
		
		int nCode = ret.getCode();
		int nValue = returnCode.getValue();
		switch(returnCode.getOperator()) 
		{
			case VALUE_0://=
			{
				return nCode==nValue;
			}
			case VALUE_1://!=
			{
				return nCode!=nValue;
			}
			case VALUE_2://>
				{
					return nCode>nValue;
				}
			case VALUE_3://>=
				{
					return nCode>=nValue;
				}
			case VALUE_4://<
				{
					return nCode<nValue;
				}
			case VALUE_5://<=
				{
					return nCode<=nValue;
				}
		}
		return false;
	}
	
	private static int getKeyType(CacheKey cacheKey){
		
		  int nType=0;
		  switch (cacheKey.getType()) {
			case COMMON:
				nType=0;
				break;
			case JSON:
				nType=1;
			case XML:
				nType=2;					
			default:
				 nType=0;
			}
		  return nType;
	}	
	
}
