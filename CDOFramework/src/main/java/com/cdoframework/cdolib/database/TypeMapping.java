package com.cdoframework.cdolib.database;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.database.xsd.types.CriteriaTypeTypeType;
import com.cdoframework.cdolib.database.xsd.types.FieldTypeTypeType;

public class TypeMapping
{
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static int mappingToDataType(FieldTypeTypeType type)
	{
		if(type==null)
		{
			return DataType.NONE_TYPE;
		}		
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
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static int mappingToDataType(CriteriaTypeTypeType type)
	{
		if(type==null)
		{
			return DataType.NONE_TYPE;
		}
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
			case BOOLEAN:
				{
					return DataType.BOOLEAN_TYPE;
				}
			case BOOLEANARRAY:
				{
					return DataType.BOOLEAN_ARRAY_TYPE;
				} 
			case STRINGARRAY:
				{
					return DataType.STRING_ARRAY_TYPE;
				}
			case BYTE:
				{
					return DataType.BYTE_TYPE;
				}
			case BYTEARRAY:
				{
					return DataType.BYTE_ARRAY_TYPE;
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
	
	public static int  mappingToMonGoType(int nDataType)
	{
		switch(nDataType)
		{
			case DataType.STRING_TYPE:
				{
					return INoSQLDataEngine.TYPE_STRING;
				}
			case DataType.FLOAT_TYPE:
				{
					return INoSQLDataEngine.TYPE_DOUBLE;
				}
			case DataType.INTEGER_TYPE:
				{
					return INoSQLDataEngine.TYPE_32_BIT_INTEGER;
				}
			
			case DataType.LONG_TYPE:
				{
					return INoSQLDataEngine.TYPE_64_BIT_INTEGER;
				}
			
			case DataType.BOOLEAN_TYPE:
				{
					return INoSQLDataEngine.TYPE_BOOLEAN;
				}
			case DataType.DATETIME_TYPE:
			case DataType.TIME_TYPE:
				{
					return INoSQLDataEngine.TYPE_TIMESTAME;
				}
			case DataType.DATE_TYPE:
				{
					return INoSQLDataEngine.TYPE_DATE;
				}
			default:
				{
					return -1;
				}
		}
	}

}
