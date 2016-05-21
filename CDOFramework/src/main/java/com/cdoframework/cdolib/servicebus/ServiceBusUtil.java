package com.cdoframework.cdolib.servicebus;

import java.util.Date;

import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.schema.Creteria;
import com.cdoframework.cdolib.servicebus.schema.Creterias;
import com.cdoframework.cdolib.servicebus.schema.CreteriasType;
import com.cdoframework.cdolib.servicebus.schema.CreteriasTypeItem;
import com.cdoframework.cdolib.servicebus.schema.types.CriteriaTypeOperatorType;
import com.cdoframework.cdolib.servicebus.schema.types.CriteriaTypeTypeType;

public class ServiceBusUtil
{
	  public static String 	IN_SEPRATOR	=",";
	    
	public static boolean isFitCreterias(CreteriasType cts,CDO cdoRequest) throws Exception
	{
    	if(cts==null)
    	{
    		return true;
    	}
    	if(cts.getType().getType()==com.cdoframework.cdolib.servicebus.schema.types.CreteriasTypeItemTypeType.AND_TYPE)
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
		if(!cdoRequest.exists(strName))
		{
			return false;
		}
		
		Object objValue1 = cdoRequest.getObjectValue(strName);

		
		String strValue = ct.getValue();
		int nType = ct.getType().getType();

		switch ( ct.getOperator().getType())
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
			case CriteriaTypeOperatorType.VALUE_6_TYPE:
				{//IS TODO 暂不支持					
					return false;
				}
			case CriteriaTypeOperatorType.VALUE_7_TYPE:
				{//IS NOT TODO 暂不支持
					break;
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
		return false;
	}
}
