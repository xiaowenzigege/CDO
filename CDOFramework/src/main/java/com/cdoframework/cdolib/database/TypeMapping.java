package com.cdoframework.cdolib.database;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.database.dataservice.types.CriteriaTypeTypeType;
import com.cdoframework.cdolib.database.dataservice.types.FieldTypeTypeType;

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
		int nType = type.getType();
		switch(nType)
		{
			case FieldTypeTypeType.STRING_TYPE:
				{
					return DataType.STRING_TYPE;
				}
			case FieldTypeTypeType.INTEGER_TYPE:
				{
					return DataType.INTEGER_TYPE;
				}
			case FieldTypeTypeType.LONG_TYPE:
				{
					return DataType.LONG_TYPE;
				}
			case FieldTypeTypeType.DATETIME_TYPE:
				{
					return DataType.DATETIME_TYPE;
				}
			case FieldTypeTypeType.STRINGARRAY_TYPE:
				{
					return DataType.STRING_ARRAY_TYPE;
				}
			case FieldTypeTypeType.BOOLEAN_TYPE:
				{
					return DataType.BOOLEAN_TYPE;
				}
			case FieldTypeTypeType.BOOLEANARRAY_TYPE:
				{
					return DataType.BOOLEAN_ARRAY_TYPE;
				}
			case FieldTypeTypeType.BYTE_TYPE:
				{
					return DataType.BYTE_TYPE;
				}
			case FieldTypeTypeType.BYTEARRAY_TYPE:
				{
					return DataType.BYTE_ARRAY_TYPE;
				}
			case FieldTypeTypeType.CDO_TYPE:
				{
					return DataType.CDO_TYPE;
				}
			case FieldTypeTypeType.CDOARRAY_TYPE:
				{
					return DataType.CDO_ARRAY_TYPE;
				}
			case FieldTypeTypeType.DATE_TYPE:
				{
					return DataType.DATE_TYPE;
				}
			case FieldTypeTypeType.DATEARRAY_TYPE:
				{
					return DataType.DATE_ARRAY_TYPE;
				}
			
			case FieldTypeTypeType.DATETIMEARRAY_TYPE:
				{
					return DataType.DATETIME_ARRAY_TYPE;
				}
			case FieldTypeTypeType.DOUBLE_TYPE:
				{
					return DataType.DOUBLE_TYPE;
				}
			case FieldTypeTypeType.DOUBLEARRAY_TYPE:
				{
					return DataType.DOUBLE_ARRAY_TYPE;
				}
			case FieldTypeTypeType.FLOAT_TYPE:
				{
					return DataType.FLOAT_TYPE;
				}
			case FieldTypeTypeType.FLOATARRAY_TYPE:
				{
					return DataType.FLOAT_ARRAY_TYPE;
				}
			
			case FieldTypeTypeType.INTEGERARRAY_TYPE:
				{
					return DataType.INTEGER_ARRAY_TYPE;
				}
			
			case FieldTypeTypeType.LONGARRAY_TYPE:
				{
					return DataType.LONG_ARRAY_TYPE;
				}
			case FieldTypeTypeType.SHORT_TYPE:
				{
					return DataType.SHORT_TYPE;
				}
			case FieldTypeTypeType.SHORTARRAY_TYPE:
				{
					return DataType.SHORT_ARRAY_TYPE;
				}
			case FieldTypeTypeType.TIME_TYPE:
				{
					return DataType.TIME_TYPE;
				}
			case FieldTypeTypeType.TIMEARRAY_TYPE:
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
		int nType = type.getType();
		switch(nType)
		{
			case CriteriaTypeTypeType.STRING_TYPE:
				{
					return DataType.STRING_TYPE;
				}
			case CriteriaTypeTypeType.INTEGER_TYPE:
				{
					return DataType.INTEGER_TYPE;
				}
			case CriteriaTypeTypeType.LONG_TYPE:
				{
					return DataType.LONG_TYPE;
				}
			case CriteriaTypeTypeType.DATETIME_TYPE:
				{
					return DataType.DATETIME_TYPE;
				}
			case CriteriaTypeTypeType.BOOLEAN_TYPE:
				{
					return DataType.BOOLEAN_TYPE;
				}
			case CriteriaTypeTypeType.BOOLEANARRAY_TYPE:
				{
					return DataType.BOOLEAN_ARRAY_TYPE;
				} 
			case CriteriaTypeTypeType.STRINGARRAY_TYPE:
				{
					return DataType.STRING_ARRAY_TYPE;
				}
			case CriteriaTypeTypeType.BYTE_TYPE:
				{
					return DataType.BYTE_TYPE;
				}
			case CriteriaTypeTypeType.BYTEARRAY_TYPE:
				{
					return DataType.BYTE_ARRAY_TYPE;
				}
			case CriteriaTypeTypeType.DATE_TYPE:
				{
					return DataType.DATE_TYPE;
				}
			case CriteriaTypeTypeType.DATEARRAY_TYPE:
				{
					return DataType.DATE_ARRAY_TYPE;
				}
			
			case CriteriaTypeTypeType.DATETIMEARRAY_TYPE:
				{
					return DataType.DATETIME_ARRAY_TYPE;
				}
			case CriteriaTypeTypeType.DOUBLE_TYPE:
				{
					return DataType.DOUBLE_TYPE;
				}
			case CriteriaTypeTypeType.DOUBLEARRAY_TYPE:
				{
					return DataType.DOUBLE_ARRAY_TYPE;
				}
			case CriteriaTypeTypeType.FLOAT_TYPE:
				{
					return DataType.FLOAT_TYPE;
				}
			case CriteriaTypeTypeType.FLOATARRAY_TYPE:
				{
					return DataType.FLOAT_ARRAY_TYPE;
				}
			
			case CriteriaTypeTypeType.INTEGERARRAY_TYPE:
				{
					return DataType.INTEGER_ARRAY_TYPE;
				}
			
			case CriteriaTypeTypeType.LONGARRAY_TYPE:
				{
					return DataType.LONG_ARRAY_TYPE;
				}
			case CriteriaTypeTypeType.SHORT_TYPE:
				{
					return DataType.SHORT_TYPE;
				}
			case CriteriaTypeTypeType.SHORTARRAY_TYPE:
				{
					return DataType.SHORT_ARRAY_TYPE;
				}
			case CriteriaTypeTypeType.TIME_TYPE:
				{
					return DataType.TIME_TYPE;
				}
			case CriteriaTypeTypeType.TIMEARRAY_TYPE:
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
