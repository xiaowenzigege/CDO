package com.cdoframework.cdolib.database;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.BooleanArrayField;
import com.cdoframework.cdolib.data.cdo.BooleanField;
import com.cdoframework.cdolib.data.cdo.ByteArrayField;
import com.cdoframework.cdolib.data.cdo.ByteField;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOArrayField;
import com.cdoframework.cdolib.data.cdo.CDOField;
import com.cdoframework.cdolib.data.cdo.DateArrayField;
import com.cdoframework.cdolib.data.cdo.DateField;
import com.cdoframework.cdolib.data.cdo.DateTimeArrayField;
import com.cdoframework.cdolib.data.cdo.DateTimeField;
import com.cdoframework.cdolib.data.cdo.DoubleArrayField;
import com.cdoframework.cdolib.data.cdo.DoubleField;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.FloatArrayField;
import com.cdoframework.cdolib.data.cdo.FloatField;
import com.cdoframework.cdolib.data.cdo.IntegerArrayField;
import com.cdoframework.cdolib.data.cdo.IntegerField;
import com.cdoframework.cdolib.data.cdo.LongArrayField;
import com.cdoframework.cdolib.data.cdo.LongField;
import com.cdoframework.cdolib.data.cdo.ShortArrayField;
import com.cdoframework.cdolib.data.cdo.ShortField;
import com.cdoframework.cdolib.data.cdo.StringArrayField;
import com.cdoframework.cdolib.data.cdo.StringField;
import com.cdoframework.cdolib.data.cdo.TimeArrayField;
import com.cdoframework.cdolib.data.cdo.TimeField;
import com.cdoframework.cdolib.database.xsd.SetVar;
import com.cdoframework.cdolib.database.xsd.types.IfTypeType;
import com.cdoframework.cdolib.database.xsd.types.SQLIfTypeType;

public class DataEngineHelp {
	private static final Logger logger=Logger.getLogger(DataEngineHelp.class);
	
	static void setVar(SetVar sv,CDO cdoRequest){
		String strVarId=sv.getVarId();
		String strValue=sv.getValue();
		
		StringBuilder strbText1=new StringBuilder();
		StringBuilder strbText2=new StringBuilder();
		boolean bIsFieldId1=handleFieldIdText(strVarId,strbText1);
		boolean bIsFieldId2=handleFieldIdText(strValue,strbText2);
		
		String strFieldId=strbText1.toString();
		String strValueId=strbText2.toString();
		if(bIsFieldId2){
			if(!cdoRequest.exists(strValueId)){
				logger.warn("setVal cdoRequest["+strValueId+"]不存在.");
				return ;
			}
			//value 为变量形式数据
			byte type =cdoRequest.getObject(strValueId).getType().getDataType();
			switch(type){
				case DataType.BOOLEAN_TYPE:
					cdoRequest.setBooleanValue(strFieldId, cdoRequest.getBooleanValue(strValueId));
					break;
				case DataType.BYTE_TYPE:
					cdoRequest.setByteValue(strFieldId, cdoRequest.getByteValue(strValueId));
					break;
				case DataType.CDO_TYPE:
					cdoRequest.setCDOValue(strFieldId, cdoRequest.getCDOValue(strValueId));					
					break;
				case DataType.DATE_TYPE:
					cdoRequest.setDateValue(strFieldId, cdoRequest.getDateValue(strValueId));
					break;
				case DataType.DATETIME_TYPE:
					cdoRequest.setDateTimeValue(strFieldId, cdoRequest.getDateTimeValue(strValueId));
					break;
				case DataType.DOUBLE_TYPE:
					cdoRequest.setDoubleValue(strFieldId, cdoRequest.getDoubleValue(strValueId));
					break;
				case DataType.FLOAT_TYPE:
					cdoRequest.setFloatValue(strFieldId, cdoRequest.getFloatValue(strValueId));
					break;
				case DataType.INTEGER_TYPE:
					cdoRequest.setIntegerValue(strFieldId, cdoRequest.getIntegerValue(strValueId));
					break;
				case DataType.LONG_TYPE:
					cdoRequest.setLongValue(strFieldId, cdoRequest.getLongValue(strValueId));
					break;
				case DataType.SHORT_TYPE:
					cdoRequest.setShortValue(strFieldId, cdoRequest.getShortValue(strValueId));
					break;
				case DataType.STRING_TYPE:
					cdoRequest.setStringValue(strFieldId, cdoRequest.getStringValue(strValueId));
					break;
				case DataType.TIME_TYPE:
					cdoRequest.setTimeValue(strFieldId, cdoRequest.getTimeValue(strValueId));
					break;
					
				case DataType.BOOLEAN_ARRAY_TYPE:
					cdoRequest.setBooleanArrayValue(strFieldId, cdoRequest.getBooleanArrayValue(strValueId));
					break;
				case DataType.BYTE_ARRAY_TYPE:
					cdoRequest.setByteArrayValue(strFieldId, cdoRequest.getByteArrayValue(strValueId));
					break;
				case DataType.CDO_ARRAY_TYPE:
					cdoRequest.setCDOArrayValue(strFieldId, cdoRequest.getCDOArrayValue(strValueId));					
					break;
				case DataType.DATE_ARRAY_TYPE:
					cdoRequest.setDateArrayValue(strFieldId, cdoRequest.getDateArrayValue(strValueId));
					break;
				case DataType.DATETIME_ARRAY_TYPE:
					cdoRequest.setDateTimeArrayValue(strFieldId, cdoRequest.getDateTimeArrayValue(strValueId));
					break;
				case DataType.DOUBLE_ARRAY_TYPE:
					cdoRequest.setDoubleArrayValue(strFieldId, cdoRequest.getDoubleArrayValue(strValueId));
					break;
				case DataType.FLOAT_ARRAY_TYPE:
					cdoRequest.setFloatArrayValue(strFieldId, cdoRequest.getFloatArrayValue(strValueId));
					break;
				case DataType.INTEGER_ARRAY_TYPE:
					cdoRequest.setIntegerArrayValue(strFieldId, cdoRequest.getIntegerArrayValue(strValueId));
					break;
				case DataType.LONG_ARRAY_TYPE:
					cdoRequest.setLongArrayValue(strFieldId, cdoRequest.getLongArrayValue(strValueId));
					break;
				case DataType.SHORT_ARRAY_TYPE:
					cdoRequest.setShortArrayValue(strFieldId, cdoRequest.getShortArrayValue(strValueId));
					break;
				case DataType.STRING_ARRAY_TYPE:
					cdoRequest.setStringArrayValue(strFieldId, cdoRequest.getStringArrayValue(strValueId));
					break;
				case DataType.TIME_ARRAY_TYPE:
					cdoRequest.setTimeArrayValue(strFieldId, cdoRequest.getTimeArrayValue(strValueId));
					break;					
			}
			return;
		}
		//sv.getValue() 为普通文本,strbText2	
		if(sv.getType()==null){
			throw new RuntimeException("setVal,类型为定义, 当value值是普通文本时 ,需要定义类型");
		}
		switch(sv.getType())
		{
			case BYTE:
				cdoRequest.setByteValue(strFieldId,Byte.parseByte(sv.getValue()));
				break;
			case SHORT:
				cdoRequest.setShortValue(strFieldId,Short.parseShort(sv.getValue()));
				break;
			case INTEGER:
				cdoRequest.setIntegerValue(strFieldId,Integer.parseInt(sv.getValue()));
				break;
			case LONG:
				cdoRequest.setLongValue(strFieldId,Long.parseLong(sv.getValue()));
				break;
			case FLOAT:
				cdoRequest.setFloatValue(strFieldId,Float.parseFloat(sv.getValue()));
				break;
			case DOUBLE:
				cdoRequest.setDoubleValue(strFieldId,Double.parseDouble(sv.getValue()));
				break;
			case STRING:
				cdoRequest.setStringValue(strFieldId,sv.getValue());
				break;
			case DATE:
				cdoRequest.setDateValue(strFieldId,sv.getValue());
				break;
			case TIME:
				cdoRequest.setTimeValue(strFieldId,sv.getValue());
				break;
			case DATETIME:
				cdoRequest.setDateTimeValue(strFieldId,sv.getValue());
				break;
			default:
				throw new RuntimeException("Invalid type "+sv.getType().toString());
		}
	}
	
	static boolean checkCondition(String strValue1,String strOperator,String strValue2,SQLIfTypeType sqlIfTypeType,String strType,
			CDO cdoRequest){
		IfTypeType ifType=null;
		switch (sqlIfTypeType) {
			case BOOLEAN:
				ifType=IfTypeType.BOOLEAN;
				break;
			case BYTE:
				ifType=IfTypeType.BYTE;
				break;
			case SHORT:
				ifType=IfTypeType.SHORT;
				break;
			case INTEGER:
				ifType=IfTypeType.INTEGER;
				break;
			case LONG:
				ifType=IfTypeType.LONG;
				break;
			case FLOAT:
				ifType=IfTypeType.FLOAT;
				break;
			case DOUBLE:
				ifType=IfTypeType.DOUBLE;
				break;
			case STRING:
				ifType=IfTypeType.STRING;
				break;
			case DATE:
				ifType=IfTypeType.DATE;
				break;
			case TIME:
				ifType=IfTypeType.TIME;
				break;
			case DATETIME:
				ifType=IfTypeType.DATETIME;
				break;				
			default:
				break;
		}
		return checkCondition(strValue1, strOperator, strValue2, ifType, strType, cdoRequest);
	}   
	static boolean checkCondition(String strValue1,String strOperator,String strValue2,IfTypeType ifType,String strType,
			CDO cdoRequest){
    	// IS
		if(strOperator.equalsIgnoreCase("IS")==true){
			// 解析FieldId
			StringBuilder strbText1=new StringBuilder();
			StringBuilder strbText2=new StringBuilder();
			boolean bIsFieldId1=handleFieldIdText(strValue1,strbText1);
			boolean bIsFieldId2=handleFieldIdText(strValue2,strbText2);

			if(bIsFieldId1&&!bIsFieldId2)
			{// Value1是FieldId
				if(cdoRequest.exists(strbText1.toString()))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else if(bIsFieldId2&&!bIsFieldId1)
			{// Value2是FieldId
				if(cdoRequest.exists(strbText2.toString()))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			
			throw new RuntimeException("Invalid IF condition");
		}
		// ISNOT
		if(strOperator.equalsIgnoreCase("ISNOT")==true)
		{
			// 解析FieldId
			StringBuilder strbText1=new StringBuilder();
			StringBuilder strbText2=new StringBuilder();
			boolean bIsFieldId1=handleFieldIdText(strValue1,strbText1);
			boolean bIsFieldId2=handleFieldIdText(strValue2,strbText2);

			if(bIsFieldId1&&!bIsFieldId2)
			{// Value1是FieldId
				if(cdoRequest.exists(strbText1.toString()))
				{
					return true;
				}
				else
				{// Value2是FieldId
					return false;
				}
			}
			else if(bIsFieldId2&&!bIsFieldId1)
			{
				if(cdoRequest.exists(strbText2.toString()))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			throw new RuntimeException("Invalid IF condition");
		}

		// =
		if(strOperator.equalsIgnoreCase("=")){
			return checkEqual(strValue1, strValue2, ifType, cdoRequest);
		}else if(strOperator.equalsIgnoreCase("!=")){
			return checkNotEqual(strValue1, strValue2, ifType, cdoRequest);
		}else if(strOperator.equalsIgnoreCase(">")){
			return checkGreaterThan(strValue1, strValue2, ifType, cdoRequest);
		}else if(strOperator.equalsIgnoreCase("<")){
			return checkLessThan(strValue1, strValue2, ifType, cdoRequest);
		}else if(strOperator.equalsIgnoreCase(">=")){
			return checkGatherAndEqual(strValue1, strValue2, ifType, cdoRequest);
		}else if(strOperator.equalsIgnoreCase("<=")){
			return checkLessAndEqual(strValue1, strValue2, ifType, cdoRequest);
			
		}else if(strOperator.equalsIgnoreCase("MATCH")){
			switch(ifType)
			{
				case STRING:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return value1.matches(value2);
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
	  }else if(strOperator.equalsIgnoreCase("NOTMATCH")){
			switch(ifType)
			{
				case STRING:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return !value1.matches(value2);
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
		}
		else
		{
			throw new RuntimeException("Invalid operator "+strOperator);
		}
    }
    /**
     * 判断是否相等	
     * @param strValue1
     * @param strValue2
     * @param ifType
     * @param cdoRequest
     * @return
     */
    private static boolean checkEqual(String strValue1,String strValue2,IfTypeType ifType,CDO cdoRequest){
    	switch(ifType)
		{
			case INTEGER:
			{
				int value1=getIntegerValue(strValue1,cdoRequest);
				int value2=getIntegerValue(strValue2,cdoRequest);
				return value1==value2;
			}
			case STRING:
			{
				String value1=getStringValue(strValue1,cdoRequest);
				String value2=getStringValue(strValue2,cdoRequest);
				return value1.equals(value2);
			}
			case LONG:
			{
				long value1=getLongValue(strValue1,cdoRequest);
				long value2=getLongValue(strValue2,cdoRequest);
				return value1==value2;
			}
			case BYTE:
			{
				byte value1=getByteValue(strValue1,cdoRequest);
				byte value2=getByteValue(strValue2,cdoRequest);
				return value1==value2;
			}
			case SHORT:
			{
				short value1=getShortValue(strValue1,cdoRequest);
				short value2=getShortValue(strValue2,cdoRequest);
				return value1==value2;
			}
			case DATE:
			{
				String value1=getDateValue(strValue1,cdoRequest);
				String value2=getDateValue(strValue2,cdoRequest);
				return value1.equals(value2);
			}
			case TIME:
			{
				String value1=getTimeValue(strValue1,cdoRequest);
				String value2=getTimeValue(strValue2,cdoRequest);
				return value1.equals(value2);
			}
			case DATETIME:
			{
				String value1=getDateTimeValue(strValue1,cdoRequest);
				String value2=getDateTimeValue(strValue2,cdoRequest);
				return value1.equals(value2);
			}
			case FLOAT:
			{
				float value1=getFloatValue(strValue1,cdoRequest);
				float value2=getFloatValue(strValue2,cdoRequest);
				return value1==value2;
			}
			case DOUBLE:
			{
				double value1=getDoubleValue(strValue1,cdoRequest);
				double value2=getDoubleValue(strValue2,cdoRequest);
				return value1==value2;
			}
			case BOOLEAN:
			{
				boolean value1=getBooleanValue(strValue1,cdoRequest);
				boolean value2=getBooleanValue(strValue2,cdoRequest);
				return value1==value2;
			}
			default:
			{
				throw new RuntimeException("Invalid type "+ifType.value());
			}
		}
    }
    /**
     * 判断不相等
     * @param strValue1
     * @param strValue2
     * @param ifType
     * @param cdoRequest
     * @return
     */
    private static boolean checkNotEqual(String strValue1,String strValue2,IfTypeType ifType,CDO cdoRequest){
    	switch(ifType)
		{
			case INTEGER:
			{
				int value1=getIntegerValue(strValue1,cdoRequest);
				int value2=getIntegerValue(strValue2,cdoRequest);
				return value1!=value2;
			}
			case STRING:
			{
				String value1=getStringValue(strValue1,cdoRequest);
				String value2=getStringValue(strValue2,cdoRequest);
				return !value1.equals(value2);
			}
			case LONG:
			{
				long value1=getLongValue(strValue1,cdoRequest);
				long value2=getLongValue(strValue2,cdoRequest);
				return value1!=value2;
			}
			case BYTE:
			{
				byte value1=getByteValue(strValue1,cdoRequest);
				byte value2=getByteValue(strValue2,cdoRequest);
				return value1!=value2;
			}
			case SHORT:
			{
				short value1=getShortValue(strValue1,cdoRequest);
				short value2=getShortValue(strValue2,cdoRequest);
				return value1!=value2;
			}
			case DATE:
			{
				String value1=getDateValue(strValue1,cdoRequest);
				String value2=getDateValue(strValue2,cdoRequest);
				return !value1.equals(value2);
			}
			case TIME:
			{
				String value1=getTimeValue(strValue1,cdoRequest);
				String value2=getTimeValue(strValue2,cdoRequest);
				return !value1.equals(value2);
			}
			case DATETIME:
			{
				String value1=getDateTimeValue(strValue1,cdoRequest);
				String value2=getDateTimeValue(strValue2,cdoRequest);
				return !value1.equals(value2);
			}
			case FLOAT:
			{
				float value1=getFloatValue(strValue1,cdoRequest);
				float value2=getFloatValue(strValue2,cdoRequest);
				return value1!=value2;
			}
			case DOUBLE:
			{
				double value1=getDoubleValue(strValue1,cdoRequest);
				double value2=getDoubleValue(strValue2,cdoRequest);
				return value1!=value2;
			}
			case BOOLEAN:
			{
				boolean value1=getBooleanValue(strValue1,cdoRequest);
				boolean value2=getBooleanValue(strValue2,cdoRequest);
				return value1!=value2;
			}				
			default:
			{
				throw new RuntimeException("Invalid type "+ifType.value());
			}
		}
    } 
    /**
     * 判断是否 大于等于
     * @param strValue1
     * @param strValue2
     * @param ifType
     * @param cdoRequest
     * @return
     */
    private static boolean checkGreaterThan(String strValue1,String strValue2,IfTypeType ifType,CDO cdoRequest){
		switch(ifType)
		{
			case INTEGER:
			{
				int value1=getIntegerValue(strValue1,cdoRequest);
				int value2=getIntegerValue(strValue2,cdoRequest);
				return value1>value2;
			}
			case STRING:
			{
				String value1=getStringValue(strValue1,cdoRequest);
				String value2=getStringValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>0;
			}
			case LONG:
			{
				long value1=getLongValue(strValue1,cdoRequest);
				long value2=getLongValue(strValue2,cdoRequest);
				return value1>value2;
			}
			case BYTE:
			{
				byte value1=getByteValue(strValue1,cdoRequest);
				byte value2=getByteValue(strValue2,cdoRequest);
				return value1>value2;
			}
			case SHORT:
			{
				short value1=getShortValue(strValue1,cdoRequest);
				short value2=getShortValue(strValue2,cdoRequest);
				return value1>value2;
			}
			case DATE:
			{
				String value1=getDateValue(strValue1,cdoRequest);
				String value2=getDateValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>0;
			}
			case TIME:
			{
				String value1=getTimeValue(strValue1,cdoRequest);
				String value2=getTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>0;
			}
			case DATETIME:
			{
				String value1=getDateTimeValue(strValue1,cdoRequest);
				String value2=getDateTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>0;
			}
			case FLOAT:
			{
				float value1=getFloatValue(strValue1,cdoRequest);
				float value2=getFloatValue(strValue2,cdoRequest);
				return value1>value2;
			}
			case DOUBLE:
			{
				double value1=getDoubleValue(strValue1,cdoRequest);
				double value2=getDoubleValue(strValue2,cdoRequest);
				return value1>value2;
			}
			default:
			{
				throw new RuntimeException("Invalid type "+ifType.value());
			}
		}
    }
    
    /**
     * 判断小于
     * @param strValue1
     * @param strValue2
     * @param ifType
     * @param cdoRequest
     * @return
     */
    private static boolean checkLessThan(String strValue1,String strValue2,IfTypeType ifType,CDO cdoRequest){
    	switch(ifType)
		{
			case INTEGER:
			{
				int value1=getIntegerValue(strValue1,cdoRequest);
				int value2=getIntegerValue(strValue2,cdoRequest);
				return value1<value2;
			}
			case STRING:
			{
				String value1=getStringValue(strValue1,cdoRequest);
				String value2=getStringValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<0;
			}
			case LONG:
			{
				long value1=getLongValue(strValue1,cdoRequest);
				long value2=getLongValue(strValue2,cdoRequest);
				return value1<value2;
			}
			case BYTE:
			{
				byte value1=getByteValue(strValue1,cdoRequest);
				byte value2=getByteValue(strValue2,cdoRequest);
				return value1<value2;
			}
			case SHORT:
			{
				short value1=getShortValue(strValue1,cdoRequest);
				short value2=getShortValue(strValue2,cdoRequest);
				return value1<value2;
			}
			case DATE:
			{
				String value1=getDateValue(strValue1,cdoRequest);
				String value2=getDateValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<0;
			}
			case TIME:
			{
				String value1=getTimeValue(strValue1,cdoRequest);
				String value2=getTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<0;
			}
			case DATETIME:
			{
				String value1=getDateTimeValue(strValue1,cdoRequest);
				String value2=getDateTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<0;
			}
			case FLOAT:
			{
				float value1=getFloatValue(strValue1,cdoRequest);
				float value2=getFloatValue(strValue2,cdoRequest);
				return value1<value2;
			}
			case DOUBLE:
			{
				double value1=getDoubleValue(strValue1,cdoRequest);
				double value2=getDoubleValue(strValue2,cdoRequest);
				return value1<value2;
			}
			default:
			{
				throw new RuntimeException("Invalid type "+ifType.value());
			}
		}    	
    }
    /**
     * 判断大于等于
     * @param strValue1
     * @param strValue2
     * @param ifType
     * @param cdoRequest
     * @return
     */
    private static boolean checkGatherAndEqual(String strValue1,String strValue2,IfTypeType ifType,CDO cdoRequest){
    	switch(ifType)
		{
			case INTEGER:
			{
				int value1=getIntegerValue(strValue1,cdoRequest);
				int value2=getIntegerValue(strValue2,cdoRequest);
				return value1>=value2;
			}
			case STRING:
			{
				String value1=getStringValue(strValue1,cdoRequest);
				String value2=getStringValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>=0;
			}
			case LONG:
			{
				long value1=getLongValue(strValue1,cdoRequest);
				long value2=getLongValue(strValue2,cdoRequest);
				return value1>=value2;
			}
			case BYTE:
			{
				byte value1=getByteValue(strValue1,cdoRequest);
				byte value2=getByteValue(strValue2,cdoRequest);
				return value1>=value2;
			}
			case SHORT:
			{
				short value1=getShortValue(strValue1,cdoRequest);
				short value2=getShortValue(strValue2,cdoRequest);
				return value1>=value2;
			}
			case DATE:
			{
				String value1=getDateValue(strValue1,cdoRequest);
				String value2=getDateValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>=0;
			}
			case TIME:
			{
				String value1=getTimeValue(strValue1,cdoRequest);
				String value2=getTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>=0;
			}
			case DATETIME:
			{
				String value1=getDateTimeValue(strValue1,cdoRequest);
				String value2=getDateTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)>=0;
			}
			case FLOAT:
			{
				float value1=getFloatValue(strValue1,cdoRequest);
				float value2=getFloatValue(strValue2,cdoRequest);
				return value1>=value2;
			}
			case DOUBLE:
			{
				double value1=getDoubleValue(strValue1,cdoRequest);
				double value2=getDoubleValue(strValue2,cdoRequest);
				return value1>=value2;
			}
			default:
			{
				throw new RuntimeException("Invalid type "+ifType.value());
			}
		}    	
    }
    /**
     * 判断小于等于
     * @param strValue1
     * @param strValue2
     * @param ifType
     * @param cdoRequest
     * @return
     */ 
    private static boolean checkLessAndEqual(String strValue1,String strValue2,IfTypeType ifType,CDO cdoRequest){
    	switch(ifType)
		{
			case INTEGER:
			{
				int value1=getIntegerValue(strValue1,cdoRequest);
				int value2=getIntegerValue(strValue2,cdoRequest);
				return value1<=value2;
			}
			case STRING:
			{
				String value1=getStringValue(strValue1,cdoRequest);
				String value2=getStringValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<=0;
			}
			case LONG:
			{
				long value1=getLongValue(strValue1,cdoRequest);
				long value2=getLongValue(strValue2,cdoRequest);
				return value1<=value2;
			}
			case BYTE:
			{
				byte value1=getByteValue(strValue1,cdoRequest);
				byte value2=getByteValue(strValue2,cdoRequest);
				return value1<=value2;
			}
			case SHORT:
			{
				short value1=getShortValue(strValue1,cdoRequest);
				short value2=getShortValue(strValue2,cdoRequest);
				return value1<=value2;
			}
			case DATE:
			{
				String value1=getDateValue(strValue1,cdoRequest);
				String value2=getDateValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<=0;
			}
			case TIME:
			{
				String value1=getTimeValue(strValue1,cdoRequest);
				String value2=getTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<=0;
			}
			case DATETIME:
			{
				String value1=getDateTimeValue(strValue1,cdoRequest);
				String value2=getDateTimeValue(strValue2,cdoRequest);
				return value1.compareTo(value2)<=0;
			}
			case FLOAT:
			{
				float value1=getFloatValue(strValue1,cdoRequest);
				float value2=getFloatValue(strValue2,cdoRequest);
				return value1<=value2;
			}
			case DOUBLE:
			{
				double value1=getDoubleValue(strValue1,cdoRequest);
				double value2=getDoubleValue(strValue2,cdoRequest);
				return value1<=value2;
			}
			default:
			{
				throw new RuntimeException("Invalid type "+ifType.value());
			}
		}
    }
 // 去掉{和}，并得到是否为FieldId
 	static boolean handleFieldIdText(String strFieldIdText,StringBuilder strbOutput)
 	{
 		// modified at 2006-12-21
 		if(strFieldIdText==null||strFieldIdText.length()==0)
 		{
 			return false;
 		}
 		strbOutput.setLength(0);

 		char chFirst=strFieldIdText.charAt(0);
 		int nIndex=0;
 		int nLength=strFieldIdText.length();
 		while(true)
 		{
 			if(nIndex>=nLength)
 			{
 				break;
 			}

 			char ch=strFieldIdText.charAt(nIndex);
 			if(ch=='{'||ch=='}')
 			{
 				if(nIndex==nLength-1)
 				{
 					break;
 				}
 				if(strFieldIdText.charAt(nIndex+1)==ch)
 				{
 					strbOutput.append(ch);
 				}
 			}
 			else
 			{
 				strbOutput.append(ch);
 			}
 			nIndex++;
 		}

 		if(chFirst=='{'&&strbOutput.charAt(0)!='{')
 		{// 为FieldId
 			return true;
 		}
 		else
 		{// 为一般文本
 			return false;
 		}
 	}    
 	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static byte getByteValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return Byte.parseByte(strbFieldIdText.toString());
		}
		else
		{
			return cdoRequest.getByteValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static short getShortValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return Short.parseShort(strbFieldIdText.toString());
		}
		else
		{
			return cdoRequest.getShortValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static int getIntegerValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return Integer.parseInt(strbFieldIdText.toString());
		}
		else
		{
			return cdoRequest.getIntegerValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static float getFloatValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return Float.parseFloat(strbFieldIdText.toString());
		}
		else
		{
			return cdoRequest.getFloatValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static double getDoubleValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return Double.parseDouble(strbFieldIdText.toString());
		}
		else
		{
			return cdoRequest.getDoubleValue(strbFieldIdText.toString());
		}
	}
	
	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static boolean getBooleanValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return Boolean.parseBoolean(strbFieldIdText.toString());
		}
		else
		{
			return cdoRequest.getBooleanValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static long getLongValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return Long.parseLong(strbFieldIdText.toString());
		}
		else
		{
			return cdoRequest.getLongValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static String getStringValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return strbFieldIdText.toString();
		}
		else
		{
			return cdoRequest.getStringValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static String getDateValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return strbFieldIdText.toString();
		}
		else
		{
			return cdoRequest.getDateValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static String getTimeValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return strbFieldIdText.toString();
		}
		else
		{
			return cdoRequest.getTimeValue(strbFieldIdText.toString());
		}
	}

	/**
	 * 根据FieldIdText得到值
	 * 
	 * @param strFieldIdText
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	static String getDateTimeValue(String strFieldIdText,CDO cdoRequest)
	{
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		boolean bIsFieldId=handleFieldIdText(strFieldIdText,strbFieldIdText);

		if(bIsFieldId==false)
		{
			return strbFieldIdText.toString();
		}
		else
		{
			return cdoRequest.getDateTimeValue(strbFieldIdText.toString());
		}
	}
    /**
     * 解释数组长度使用
     * @param strFieldIdText
     * @param cdoRequest
     * @return
     */
	static int getArrayLength(String strFieldIdText,CDO cdoRequest){
		// 解析strFieldIdText,判断出是否为FieldId
		StringBuilder strbFieldIdText=new StringBuilder();
		handleFieldIdText(strFieldIdText,strbFieldIdText);
		String arrKey= strbFieldIdText.toString();
		if(!cdoRequest.exists(arrKey))
			return 0;
		 Field field=cdoRequest.getObject(arrKey);
		switch(field.getType().getDataType()){
			case DataType.BOOLEAN_ARRAY_TYPE:
			{
				return ((BooleanArrayField)field).getLength();
			}
			case DataType.BYTE_ARRAY_TYPE:
			{
				return ((ByteArrayField)field).getLength();
			}
			case DataType.SHORT_ARRAY_TYPE:
			{
				return ((ShortArrayField)field).getLength();
			}
			case DataType.INTEGER_ARRAY_TYPE:
			{
				return ((IntegerArrayField)field).getLength();
			}
			case DataType.LONG_ARRAY_TYPE:
			{
				return  ((LongArrayField)field).getLength();
			}
			case DataType.FLOAT_ARRAY_TYPE:
			{
				return ((FloatArrayField)field).getLength();
			}
			case DataType.DOUBLE_ARRAY_TYPE:
			{
				return ((DoubleArrayField)field).getLength();
			}
			case DataType.STRING_ARRAY_TYPE:
			{
				return ((StringArrayField)field).getLength();
			}
			case DataType.DATE_ARRAY_TYPE:
			{
				return ((DateArrayField)field).getLength();
			}
			case DataType.TIME_ARRAY_TYPE:
			{
				return ((TimeArrayField)field).getLength();
			}
			case DataType.DATETIME_ARRAY_TYPE:
			{
				return ((DateTimeArrayField)field).getLength();
			}
			case DataType.CDO_ARRAY_TYPE:
			{
				return ((CDOArrayField)field).getLength();
			}
		 }
		logger.warn(field.getType().getFieldType()+" Type is not array");	
		return 0;
	}
}
