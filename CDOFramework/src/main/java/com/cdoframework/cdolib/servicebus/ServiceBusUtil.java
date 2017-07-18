package com.cdoframework.cdolib.servicebus;

import java.util.Date;

import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.xsd.Creteria;
import com.cdoframework.cdolib.servicebus.xsd.Creterias;
import com.cdoframework.cdolib.servicebus.xsd.CreteriasType;
import com.cdoframework.cdolib.servicebus.xsd.CreteriasTypeItem;
import com.cdoframework.cdolib.servicebus.xsd.types.CriteriaTypeTypeType;

public class ServiceBusUtil
{
	  public static String 	IN_SEPRATOR	=",";
	    
	public static boolean isFitCreterias(CreteriasType cts,CDO cdoRequest) throws Exception
	{
    	if(cts==null)
    	{
    		return true;
    	}
    	if(cts.getType().value().equals(com.cdoframework.cdolib.servicebus.xsd.types.CreteriasTypeItemTypeType.AND.value()))
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
		CriteriaTypeTypeType nType = ct.getType();

		switch ( ct.getOperator())
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
			case VALUE_6:
				{//IS TODO 暂不支持					
					return false;
				}
			case VALUE_7:
				{//IS NOT TODO 暂不支持
					break;
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
								
								Date dt = DateTime.parse(Utility.parseDateTimeValue(objValue1),com.cdoframework.cdolib.base.DataType.PATTERN_DATETIME).toDate();
								Date dt1 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[0]),com.cdoframework.cdolib.base.DataType.PATTERN_DATETIME).toDate();
								Date dt2 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[1]),com.cdoframework.cdolib.base.DataType.PATTERN_DATETIME).toDate();
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
								
								Date dt = DateTime.parse(Utility.parseDateTimeValue(objValue1),com.cdoframework.cdolib.base.DataType.PATTERN_DATETIME).toDate();
								Date dt1 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[0]),com.cdoframework.cdolib.base.DataType.PATTERN_DATETIME).toDate();
								Date dt2 = DateTime.parse(Utility.parseDateTimeValue(strsDateTime[1]),com.cdoframework.cdolib.base.DataType.PATTERN_DATETIME).toDate();
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
		return false;
	}
}
