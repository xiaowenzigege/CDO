package com.cdoframework.cdolib.service.framework;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.ValueField;
import com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey;
import com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue;
import com.cdoframework.cdolib.service.framework.transfilter.schema.Creteria;
import com.cdoframework.cdolib.service.framework.transfilter.schema.Creterias;
import com.cdoframework.cdolib.service.framework.transfilter.schema.CreteriasType;
import com.cdoframework.cdolib.service.framework.transfilter.schema.CreteriasTypeItem;
import com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode;
import com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeOperatorType;
import com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeTypeType;
import com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType;
import com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyTypeType;

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
    	if(cts.getType().getType()==com.cdoframework.cdolib.service.framework.transfilter.schema.types.CreteriasTypeItemTypeType.AND_TYPE)
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
		int nOperatorType = ct.getOperator().getType();
		if(!cdoRequest.exists(strName))
		{
			if(nOperatorType == CriteriaTypeOperatorType.VALUE_6_TYPE)
			{//ISNULL
				return true;
			}
			else 
			{
				return false;
			}
		}
		if(nOperatorType==CriteriaTypeOperatorType.VALUE_7_TYPE)
		{//ISNOTNULL
			return true;
		}
		if(nOperatorType==CriteriaTypeOperatorType.VALUE_6_TYPE)
		{//ISNULL
			return false;
		}
		Object objValue1 = cdoRequest.getObjectValue(strName);

		
		String strValue = ct.getValue();
		int nType = ct.getType().getType();
		
		switch ( nOperatorType)
		{
			case CriteriaTypeOperatorType.VALUE_0_TYPE:
				{//=
					if(strValue==null)
					{
						return false;
					}
					
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:
						case CriteriaTypeTypeType.STRING_TYPE:
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
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
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
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
			case CriteriaTypeOperatorType.VALUE_1_TYPE:
				{//!=
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:
						case CriteriaTypeTypeType.STRING_TYPE:
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
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
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
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
			case CriteriaTypeOperatorType.VALUE_2_TYPE:
				{//>
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:						
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
							{
								return Utility.compareLong(objValue1,strValue)>0;
							}
						case CriteriaTypeTypeType.STRING_TYPE:
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
							{
								return Utility.compareString(objValue1,strValue)>0;
							}
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
							{
								return Utility.compareDouble(objValue1,strValue)>0;
							}
						default:
							{
								return false;
							}
					}
				}
			case CriteriaTypeOperatorType.VALUE_3_TYPE:
				{//>=
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:						
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
							{
								return Utility.compareLong(objValue1,strValue)>=0;
							}
						case CriteriaTypeTypeType.STRING_TYPE:
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
							{
								return Utility.compareString(objValue1,strValue)>=0;
							}
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
							{
								return Utility.compareDouble(objValue1,strValue)>=0;
							}
						default:
							{
								return false;
							}
					}
				}
			case CriteriaTypeOperatorType.VALUE_4_TYPE:
				{//<
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:						
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
							{
								return Utility.compareLong(objValue1,strValue)<0;
							}
						case CriteriaTypeTypeType.STRING_TYPE:
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
							{
								return Utility.compareString(objValue1,strValue)<0;
							}
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
							{
								return Utility.compareDouble(objValue1,strValue)<0;
							}
						default:
							{
								return false;
							}
					}
				}
			case CriteriaTypeOperatorType.VALUE_5_TYPE:
				{//<=
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:						
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
							{
								return Utility.compareLong(objValue1,strValue)<=0;
							}
						case CriteriaTypeTypeType.STRING_TYPE:
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
							{
								return Utility.compareString(objValue1,strValue)<=0;
							}
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
							{
								return Utility.compareDouble(objValue1,strValue)<=0;
							}
						default:
							{
								return false;
							}
					}
				}
			case CriteriaTypeOperatorType.VALUE_8_TYPE:
				{//MATCH
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.STRING_TYPE:
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
			case CriteriaTypeOperatorType.VALUE_9_TYPE:
				{//NOT MATCH
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.STRING_TYPE:
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
			case CriteriaTypeOperatorType.VALUE_10_TYPE:
				{//IN
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:						
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
						case CriteriaTypeTypeType.STRING_TYPE:
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
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
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
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
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
			case CriteriaTypeOperatorType.VALUE_11_TYPE:
				{//NOT IN
					if(strValue==null)
					{
						return false;
					}
					switch(nType)
					{
						case CriteriaTypeTypeType.INTEGER_TYPE:
						case CriteriaTypeTypeType.LONG_TYPE:						
						case CriteriaTypeTypeType.SHORT_TYPE:
						case CriteriaTypeTypeType.BYTE_TYPE:
						case CriteriaTypeTypeType.STRING_TYPE:
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
						case CriteriaTypeTypeType.DATETIME_TYPE:
						case CriteriaTypeTypeType.DATE_TYPE:
						case CriteriaTypeTypeType.TIME_TYPE:							
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
						case CriteriaTypeTypeType.DOUBLE_TYPE:
						case CriteriaTypeTypeType.FLOAT_TYPE:
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
		int nType = cacheKey.getType().getType();
		return FrameworkUtil.getString(nType,strContent,cdoRequest,cdoResponse);
	}
	
	public static String getKey(int fromIndex,String strIndexId,CacheKey cacheKey,CDO cdoRequest,CDO cdoResponse) throws Exception
	{
		if(cacheKey==null)
		{
			return cdoRequest.toJSON();
		}
		String strContent = cacheKey.getContent();
		int nType = cacheKey.getType().getType();
		return FrameworkUtil.getString(fromIndex,strIndexId,nType,strContent,cdoRequest,cdoResponse);
	}	
	
	

	private static void callOnException(String strMessage,Exception e)
	{
		logger.error(strMessage,e);
		
	}


	private static String getFiledString(int nStringType,String strFieldId,CDO cdoRequest,CDO cdoResponse) throws Exception
	{
		ValueField vf = null;
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
		switch(returnCode.getOperator().getType())
		{
			case ReturnCodeOperatorType.VALUE_0_TYPE:
			{//=
				return nCode == nValue;
			}
			case ReturnCodeOperatorType.VALUE_1_TYPE:
			{//!=
				return nCode != nValue;
			}
			case ReturnCodeOperatorType.VALUE_2_TYPE:
			{//>
				return nCode > nValue; 
			}
			case ReturnCodeOperatorType.VALUE_3_TYPE:
			{//>=
				return nCode >= nValue;
			}
			case ReturnCodeOperatorType.VALUE_4_TYPE:
			{//<
				return nCode < nValue;
			}
			case ReturnCodeOperatorType.VALUE_5_TYPE:
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
		int nType = type.getType();
		switch(nType)
		{
			case RequestKeyTypeType.STRING_TYPE:
				{
					return DataType.STRING_TYPE;
				}
			case RequestKeyTypeType.INTEGER_TYPE:
				{
					return DataType.INTEGER_TYPE;
				}
			case RequestKeyTypeType.LONG_TYPE:
				{
					return DataType.LONG_TYPE;
				}
			case RequestKeyTypeType.DATETIME_TYPE:
				{
					return DataType.DATETIME_TYPE;
				}
			case RequestKeyTypeType.STRINGARRAY_TYPE:
				{
					return DataType.STRING_ARRAY_TYPE;
				}
			case RequestKeyTypeType.BOOLEAN_TYPE:
				{
					return DataType.BOOLEAN_TYPE;
				}
			case RequestKeyTypeType.BOOLEANARRAY_TYPE:
				{
					return DataType.BOOLEAN_ARRAY_TYPE;
				}
			case RequestKeyTypeType.BYTE_TYPE:
				{
					return DataType.BYTE_TYPE;
				}
			case RequestKeyTypeType.BYTEARRAY_TYPE:
				{
					return DataType.BYTE_ARRAY_TYPE;
				}
			case RequestKeyTypeType.CDO_TYPE:
				{
					return DataType.CDO_TYPE;
				}
			case RequestKeyTypeType.CDOARRAY_TYPE:
				{
					return DataType.CDO_ARRAY_TYPE;
				}
			case RequestKeyTypeType.DATE_TYPE:
				{
					return DataType.DATE_TYPE;
				}
			case RequestKeyTypeType.DATEARRAY_TYPE:
				{
					return DataType.DATE_ARRAY_TYPE;
				}
			
			case RequestKeyTypeType.DATETIMEARRAY_TYPE:
				{
					return DataType.DATETIME_ARRAY_TYPE;
				}
			case RequestKeyTypeType.DOUBLE_TYPE:
				{
					return DataType.DOUBLE_TYPE;
				}
			case RequestKeyTypeType.DOUBLEARRAY_TYPE:
				{
					return DataType.DOUBLE_ARRAY_TYPE;
				}
			case RequestKeyTypeType.FLOAT_TYPE:
				{
					return DataType.FLOAT_TYPE;
				}
			case RequestKeyTypeType.FLOATARRAY_TYPE:
				{
					return DataType.FLOAT_ARRAY_TYPE;
				}
			
			case RequestKeyTypeType.INTEGERARRAY_TYPE:
				{
					return DataType.INTEGER_ARRAY_TYPE;
				}
			
			case RequestKeyTypeType.LONGARRAY_TYPE:
				{
					return DataType.LONG_ARRAY_TYPE;
				}
			case RequestKeyTypeType.SHORT_TYPE:
				{
					return DataType.SHORT_TYPE;
				}
			case RequestKeyTypeType.SHORTARRAY_TYPE:
				{
					return DataType.SHORT_ARRAY_TYPE;
				}
			case RequestKeyTypeType.TIME_TYPE:
				{
					return DataType.TIME_TYPE;
				}
			case RequestKeyTypeType.TIMEARRAY_TYPE:
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
		switch(returnCode.getOperator().getType()) 
		{
			case ReturnCodeOperatorType.VALUE_0_TYPE://=
			{
				return nCode==nValue;
			}
			case ReturnCodeOperatorType.VALUE_1_TYPE://!=
			{
				return nCode!=nValue;
			}
			case ReturnCodeOperatorType.VALUE_2_TYPE://>
				{
					return nCode>nValue;
				}
			case ReturnCodeOperatorType.VALUE_3_TYPE://>=
				{
					return nCode>=nValue;
				}
			case ReturnCodeOperatorType.VALUE_4_TYPE://<
				{
					return nCode<nValue;
				}
			case ReturnCodeOperatorType.VALUE_5_TYPE://<=
				{
					return nCode<=nValue;
				}
		}
		return false;
	}
	
	public  static void main(String[] args)
	{
		CDO[] cdos = new CDO[2];
		for(int i=0;i<2;i++)
		{
			cdos[i] = new CDO();
			cdos[i].setStringValue("str","imaya"+i);
		}
		CDO cdo = new CDO();
		cdo.setCDOArrayValue("cdos",cdos);
		//System.out.println(cdo.toJSON());
	}
}
