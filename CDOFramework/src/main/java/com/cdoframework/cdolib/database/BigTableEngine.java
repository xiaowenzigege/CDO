/**
 * www.woyo.com 2010版权所有
 *
 * $Header: $
 *
 * $Log: $
 *
 */

package com.cdoframework.cdolib.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.SortedSet;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOArrayField;
import com.cdoframework.cdolib.database.dataservice.BigTable;
import com.cdoframework.cdolib.database.dataservice.BigTableGroup;
import com.cdoframework.cdolib.database.dataservice.BlockType;
import com.cdoframework.cdolib.database.dataservice.BlockTypeItem;
import com.cdoframework.cdolib.database.dataservice.DataService;
import com.cdoframework.cdolib.database.dataservice.Delete;
import com.cdoframework.cdolib.database.dataservice.Else;
import com.cdoframework.cdolib.database.dataservice.For;
import com.cdoframework.cdolib.database.dataservice.If;
import com.cdoframework.cdolib.database.dataservice.Insert;
import com.cdoframework.cdolib.database.dataservice.NoSQLTrans;
import com.cdoframework.cdolib.database.dataservice.OnError;
import com.cdoframework.cdolib.database.dataservice.OnException;
import com.cdoframework.cdolib.database.dataservice.SQLBlockType;
import com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem;
import com.cdoframework.cdolib.database.dataservice.SQLElse;
import com.cdoframework.cdolib.database.dataservice.SQLFor;
import com.cdoframework.cdolib.database.dataservice.SQLIf;
import com.cdoframework.cdolib.database.dataservice.SQLThen;
import com.cdoframework.cdolib.database.dataservice.SQLTrans;
import com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem;
import com.cdoframework.cdolib.database.dataservice.SelectConnection;
import com.cdoframework.cdolib.database.dataservice.SelectField;
import com.cdoframework.cdolib.database.dataservice.SelectRecord;
import com.cdoframework.cdolib.database.dataservice.SelectRecordSet;
import com.cdoframework.cdolib.database.dataservice.SetVar;
import com.cdoframework.cdolib.database.dataservice.Then;
import com.cdoframework.cdolib.database.dataservice.Update;
import com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType;
import com.cdoframework.cdolib.database.dataservice.types.SetVarTypeType;

/**
 * @author Administrator
 */
public class BigTableEngine// extends ParallelTaskProcessor
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	Logger logger  = Logger.getLogger(BigTableEngine.class);
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private Random rand=new Random();
	private HashMap<String,ParsedSQL> hmParsedSQL;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	class ParsedSQL
	{
		public String strSQL;
		public String strIdFieldName;
		public ArrayList<String> alParaName;
		public ArrayList<String> alDataGroupId;
		public String strTableName;
		public String strCountFieldName;
		public String strGroupFieldName;
		public ArrayList<String> alTableName;
		public String strOrderBy;
		public String strPage;
		public String strBatchIds;
	}
	
	class DataAccess
	{
		public IDataEngine dataEngine;
		public Connection conn;
		public int nGroupIndex=-1;
		public int nTableIndex=-1;
	}
	
	class MetaData
	{
		public String[] strsFieldName;;
		public int[] nsFieldType;
		public int[] nsPrecision;
		public int[] nsScale;
	}
	
	private void handleReturn(com.cdoframework.cdolib.database.dataservice.Return returnObject,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException
	{
		int nReturnItemCount=returnObject.getReturnItemCount();
		for(int j=0;j<nReturnItemCount;j++)
		{
			String strFieldId=returnObject.getReturnItem(j).getFieldId();
			String strValueId=returnObject.getReturnItem(j).getValueId();
			strFieldId=strFieldId.substring(1,strFieldId.length()-1);
			strValueId=strValueId.substring(1,strValueId.length()-1);
			ObjectExt object=null;
			try
			{
				object=cdoRequest.getObject(strValueId);
			}
			catch(Exception e)
			{
				continue;
			}

			// 输出
			if(object==null)
			{
				continue;
			}
			int nType=object.getType();
			Object objValue=object.getValue();
			switch(nType)
			{
				case ObjectExt.BYTE_TYPE:
				{
					cdoResponse.setByteValue(strFieldId,((Byte)objValue).byteValue());
					break;
				}
				case ObjectExt.SHORT_TYPE:
				{
					cdoResponse.setShortValue(strFieldId,((Short)objValue).shortValue());
					break;
				}
				case ObjectExt.INTEGER_TYPE:
				{
					cdoResponse.setIntegerValue(strFieldId,((Integer)objValue).intValue());
					break;
				}
				case ObjectExt.LONG_TYPE:
				{
					cdoResponse.setLongValue(strFieldId,((Long)objValue).longValue());
					break;
				}
				case ObjectExt.FLOAT_TYPE:
				{
					cdoResponse.setFloatValue(strFieldId,((Float)objValue).floatValue());
					break;
				}
				case ObjectExt.DOUBLE_TYPE:
				{
					cdoResponse.setDoubleValue(strFieldId,((Double)objValue).doubleValue());
					break;
				}
				case ObjectExt.STRING_TYPE:
				{
					cdoResponse.setStringValue(strFieldId,((String)objValue));
					break;
				}
				case ObjectExt.DATE_TYPE:
				{
					cdoResponse.setDateValue(strFieldId,((String)objValue));
					break;
				}
				case ObjectExt.TIME_TYPE:
				{
					cdoResponse.setTimeValue(strFieldId,((String)objValue));
					break;
				}
				case ObjectExt.DATETIME_TYPE:
				{
					cdoResponse.setDateTimeValue(strFieldId,((String)objValue));
					break;
				}
				case ObjectExt.BYTE_ARRAY_TYPE:
				{
					cdoResponse.setByteArrayValue(strFieldId,((byte[])objValue));
					break;
				}
				case ObjectExt.CDO_TYPE:
				{
					cdoResponse.setCDOValue(strFieldId,(CDO)objValue);
					break;
				}
				case ObjectExt.CDO_ARRAY_TYPE:
				{
					cdoResponse.setCDOArrayValue(strFieldId,(CDO[])objValue);
					break;
				}
				default:
				{
					throw new SQLException("Unsupported type "+nType);
				}
			}
		}

		ret.setCode(returnObject.getCode());
		ret.setInfo(returnObject.getInfo());
		ret.setText(returnObject.getText());
	}

	// 去掉{和}，并得到是否为FieldId
	protected boolean handleFieldIdText(String strFieldIdText,StringBuilder strbOutput)
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
	protected byte getByteValue(String strFieldIdText,CDO cdoRequest)
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
	protected short getShortValue(String strFieldIdText,CDO cdoRequest)
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
	protected int getIntegerValue(String strFieldIdText,CDO cdoRequest)
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
	protected float getFloatValue(String strFieldIdText,CDO cdoRequest)
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
	protected double getDoubleValue(String strFieldIdText,CDO cdoRequest)
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
	protected long getLongValue(String strFieldIdText,CDO cdoRequest)
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
	protected String getStringValue(String strFieldIdText,CDO cdoRequest)
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
	protected boolean getBooleanValue(String strFieldIdText,CDO cdoRequest)
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
	protected String getDateValue(String strFieldIdText,CDO cdoRequest)
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
	protected String getTimeValue(String strFieldIdText,CDO cdoRequest)
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
	protected String getDateTimeValue(String strFieldIdText,CDO cdoRequest)
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

	protected Object getFieldValue(String strFieldId,CDO cdoRequest)
	{
		if(strFieldId.indexOf('{')>=0)
		{
			strFieldId=getFieldId(strFieldId,cdoRequest);
		}
		return cdoRequest.getObjectValue(strFieldId);
	}

	/**
	 * 根据FieldId text获得FieldId，允许嵌套
	 * 
	 * @param strFieldIdText
	 * @return
	 */
	protected String getFieldId(String strFieldIdText,CDO cdoRequest)
	{
		int nStartIndex=strFieldIdText.indexOf('{');
		if(nStartIndex<0)
		{
			return strFieldIdText;
		}

		// 存在{}
		int nEndIndex=Utility.findMatchedChar(nStartIndex,strFieldIdText);
		String strSubFieldId=strFieldIdText.substring(nStartIndex+1,nEndIndex);
		String strSubFieldValue=getFieldValue(strSubFieldId,cdoRequest).toString();

		return getFieldId(strFieldIdText.substring(0,nStartIndex)+strSubFieldValue
						+strFieldIdText.substring(nEndIndex+1),cdoRequest);
	}

	protected void setVar(SetVar sv,CDO cdoRequest)
	{
		String strVarId=sv.getVarId();
		String strFieldId=strVarId.substring(1,strVarId.length()-1);
		switch(sv.getType().getType())
		{
			case SetVarTypeType.BYTE_TYPE:
				cdoRequest.setByteValue(strFieldId,Byte.parseByte(sv.getValue()));
				break;
			case SetVarTypeType.SHORT_TYPE:
				cdoRequest.setShortValue(strFieldId,Short.parseShort(sv.getValue()));
				break;
			case SetVarTypeType.INTEGER_TYPE:
				cdoRequest.setIntegerValue(strFieldId,Integer.parseInt(sv.getValue()));
				break;
			case SetVarTypeType.LONG_TYPE:
				cdoRequest.setLongValue(strFieldId,Long.parseLong(sv.getValue()));
				break;
			case SetVarTypeType.FLOAT_TYPE:
				cdoRequest.setFloatValue(strFieldId,Float.parseFloat(sv.getValue()));
				break;
			case SetVarTypeType.DOUBLE_TYPE:
				cdoRequest.setDoubleValue(strFieldId,Double.parseDouble(sv.getValue()));
				break;
			case SetVarTypeType.STRING_TYPE:
				cdoRequest.setStringValue(strFieldId,sv.getValue());
				break;
			case SetVarTypeType.DATE_TYPE:
				cdoRequest.setDateValue(strFieldId,sv.getValue());
				break;
			case SetVarTypeType.TIME_TYPE:
				cdoRequest.setTimeValue(strFieldId,sv.getValue());
				break;
			case SetVarTypeType.DATETIME_TYPE:
				cdoRequest.setDateTimeValue(strFieldId,sv.getValue());
				break;
			default:
				throw new RuntimeException("Invalid type "+sv.getType().toString());
		}
	}

	/**
	 * 检查If的条件
	 * 
	 * @param strValue1
	 * @param strOperator
	 * @param strValue2
	 * @param strType
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	protected boolean checkCondition(String strValue1,String strOperator,String strValue2,int nType,String strType,CDO cdoRequest)
	{
		// IS
		if(strOperator.equalsIgnoreCase("IS")==true)
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
		if(strOperator.equalsIgnoreCase("="))
		{
			switch(nType)
			{
				case SQLIfTypeType.INTEGER_TYPE:
				{
					int value1=getIntegerValue(strValue1,cdoRequest);
					int value2=getIntegerValue(strValue2,cdoRequest);
					return value1==value2;
				}
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
					return value1.equals(value2);
				}
				case SQLIfTypeType.LONG_TYPE:
				{
					long value1=getLongValue(strValue1,cdoRequest);
					long value2=getLongValue(strValue2,cdoRequest);
					return value1==value2;
				}
				case SQLIfTypeType.BYTE_TYPE:
				{
					byte value1=getByteValue(strValue1,cdoRequest);
					byte value2=getByteValue(strValue2,cdoRequest);
					return value1==value2;
				}
				case SQLIfTypeType.SHORT_TYPE:
				{
					short value1=getShortValue(strValue1,cdoRequest);
					short value2=getShortValue(strValue2,cdoRequest);
					return value1==value2;
				}
				case SQLIfTypeType.DATE_TYPE:
				{
					String value1=getDateValue(strValue1,cdoRequest);
					String value2=getDateValue(strValue2,cdoRequest);
					return value1.equals(value2);
				}
				case SQLIfTypeType.TIME_TYPE:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return value1.equals(value2);
				}
				case SQLIfTypeType.DATETIME_TYPE:
				{
					String value1=getDateTimeValue(strValue1,cdoRequest);
					String value2=getDateTimeValue(strValue2,cdoRequest);
					return value1.equals(value2);
				}
				case SQLIfTypeType.FLOAT_TYPE:
				{
					float value1=getFloatValue(strValue1,cdoRequest);
					float value2=getFloatValue(strValue2,cdoRequest);
					return value1==value2;
				}
				case SQLIfTypeType.DOUBLE_TYPE:
				{
					double value1=getDoubleValue(strValue1,cdoRequest);
					double value2=getDoubleValue(strValue2,cdoRequest);
					return value1==value2;
				}
				case SQLIfTypeType.BOOLEAN_TYPE:
				{
					boolean value1=getBooleanValue(strValue1, cdoRequest);
					boolean value2=getBooleanValue(strValue2, cdoRequest);
					return value1==value2;
				}
				default:
				{
					throw new RuntimeException("Invalid type"+strType);
				}
			}
		}
		else if(strOperator.equalsIgnoreCase("!="))
		{
			switch(nType)
			{
				case SQLIfTypeType.INTEGER_TYPE:
				{
					int value1=getIntegerValue(strValue1,cdoRequest);
					int value2=getIntegerValue(strValue2,cdoRequest);
					return value1!=value2;
				}
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
					return !value1.equals(value2);
				}
				case SQLIfTypeType.LONG_TYPE:
				{
					long value1=getLongValue(strValue1,cdoRequest);
					long value2=getLongValue(strValue2,cdoRequest);
					return value1!=value2;
				}
				case SQLIfTypeType.BYTE_TYPE:
				{
					byte value1=getByteValue(strValue1,cdoRequest);
					byte value2=getByteValue(strValue2,cdoRequest);
					return value1!=value2;
				}
				case SQLIfTypeType.SHORT_TYPE:
				{
					short value1=getShortValue(strValue1,cdoRequest);
					short value2=getShortValue(strValue2,cdoRequest);
					return value1!=value2;
				}
				case SQLIfTypeType.DATE_TYPE:
				{
					String value1=getDateValue(strValue1,cdoRequest);
					String value2=getDateValue(strValue2,cdoRequest);
					return !value1.equals(value2);
				}
				case SQLIfTypeType.TIME_TYPE:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return !value1.equals(value2);
				}
				case SQLIfTypeType.DATETIME_TYPE:
				{
					String value1=getDateTimeValue(strValue1,cdoRequest);
					String value2=getDateTimeValue(strValue2,cdoRequest);
					return !value1.equals(value2);
				}
				case SQLIfTypeType.FLOAT_TYPE:
				{
					float value1=getFloatValue(strValue1,cdoRequest);
					float value2=getFloatValue(strValue2,cdoRequest);
					return value1!=value2;
				}
				case SQLIfTypeType.DOUBLE_TYPE:
				{
					double value1=getDoubleValue(strValue1,cdoRequest);
					double value2=getDoubleValue(strValue2,cdoRequest);
					return value1!=value2;
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
		}
		else if(strOperator.equalsIgnoreCase(">"))
		{
			switch(nType)
			{
				case SQLIfTypeType.INTEGER_TYPE:
				{
					int value1=getIntegerValue(strValue1,cdoRequest);
					int value2=getIntegerValue(strValue2,cdoRequest);
					return value1>value2;
				}
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>0;
				}
				case SQLIfTypeType.LONG_TYPE:
				{
					long value1=getLongValue(strValue1,cdoRequest);
					long value2=getLongValue(strValue2,cdoRequest);
					return value1>value2;
				}
				case SQLIfTypeType.BYTE_TYPE:
				{
					byte value1=getByteValue(strValue1,cdoRequest);
					byte value2=getByteValue(strValue2,cdoRequest);
					return value1>value2;
				}
				case SQLIfTypeType.SHORT_TYPE:
				{
					short value1=getShortValue(strValue1,cdoRequest);
					short value2=getShortValue(strValue2,cdoRequest);
					return value1>value2;
				}
				case SQLIfTypeType.DATE_TYPE:
				{
					String value1=getDateValue(strValue1,cdoRequest);
					String value2=getDateValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>0;
				}
				case SQLIfTypeType.TIME_TYPE:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>0;
				}
				case SQLIfTypeType.DATETIME_TYPE:
				{
					String value1=getDateTimeValue(strValue1,cdoRequest);
					String value2=getDateTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>0;
				}
				case SQLIfTypeType.FLOAT_TYPE:
				{
					float value1=getFloatValue(strValue1,cdoRequest);
					float value2=getFloatValue(strValue2,cdoRequest);
					return value1>value2;
				}
				case SQLIfTypeType.DOUBLE_TYPE:
				{
					double value1=getDoubleValue(strValue1,cdoRequest);
					double value2=getDoubleValue(strValue2,cdoRequest);
					return value1>value2;
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
		}
		else if(strOperator.equalsIgnoreCase("<"))
		{
			switch(nType)
			{
				case SQLIfTypeType.INTEGER_TYPE:
				{
					int value1=getIntegerValue(strValue1,cdoRequest);
					int value2=getIntegerValue(strValue2,cdoRequest);
					return value1<value2;
				}
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<0;
				}
				case SQLIfTypeType.LONG_TYPE:
				{
					long value1=getLongValue(strValue1,cdoRequest);
					long value2=getLongValue(strValue2,cdoRequest);
					return value1<value2;
				}
				case SQLIfTypeType.BYTE_TYPE:
				{
					byte value1=getByteValue(strValue1,cdoRequest);
					byte value2=getByteValue(strValue2,cdoRequest);
					return value1<value2;
				}
				case SQLIfTypeType.SHORT_TYPE:
				{
					short value1=getShortValue(strValue1,cdoRequest);
					short value2=getShortValue(strValue2,cdoRequest);
					return value1<value2;
				}
				case SQLIfTypeType.DATE_TYPE:
				{
					String value1=getDateValue(strValue1,cdoRequest);
					String value2=getDateValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<0;
				}
				case SQLIfTypeType.TIME_TYPE:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<0;
				}
				case SQLIfTypeType.DATETIME_TYPE:
				{
					String value1=getDateTimeValue(strValue1,cdoRequest);
					String value2=getDateTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<0;
				}
				case SQLIfTypeType.FLOAT_TYPE:
				{
					float value1=getFloatValue(strValue1,cdoRequest);
					float value2=getFloatValue(strValue2,cdoRequest);
					return value1<value2;
				}
				case SQLIfTypeType.DOUBLE_TYPE:
				{
					double value1=getDoubleValue(strValue1,cdoRequest);
					double value2=getDoubleValue(strValue2,cdoRequest);
					return value1<value2;
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
		}
		else if(strOperator.equalsIgnoreCase(">="))
		{
			switch(nType)
			{
				case SQLIfTypeType.INTEGER_TYPE:
				{
					int value1=getIntegerValue(strValue1,cdoRequest);
					int value2=getIntegerValue(strValue2,cdoRequest);
					return value1>=value2;
				}
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>=0;
				}
				case SQLIfTypeType.LONG_TYPE:
				{
					long value1=getLongValue(strValue1,cdoRequest);
					long value2=getLongValue(strValue2,cdoRequest);
					return value1>=value2;
				}
				case SQLIfTypeType.BYTE_TYPE:
				{
					byte value1=getByteValue(strValue1,cdoRequest);
					byte value2=getByteValue(strValue2,cdoRequest);
					return value1>=value2;
				}
				case SQLIfTypeType.SHORT_TYPE:
				{
					short value1=getShortValue(strValue1,cdoRequest);
					short value2=getShortValue(strValue2,cdoRequest);
					return value1>=value2;
				}
				case SQLIfTypeType.DATE_TYPE:
				{
					String value1=getDateValue(strValue1,cdoRequest);
					String value2=getDateValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>=0;
				}
				case SQLIfTypeType.TIME_TYPE:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>=0;
				}
				case SQLIfTypeType.DATETIME_TYPE:
				{
					String value1=getDateTimeValue(strValue1,cdoRequest);
					String value2=getDateTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)>=0;
				}
				case SQLIfTypeType.FLOAT_TYPE:
				{
					float value1=getFloatValue(strValue1,cdoRequest);
					float value2=getFloatValue(strValue2,cdoRequest);
					return value1>=value2;
				}
				case SQLIfTypeType.DOUBLE_TYPE:
				{
					double value1=getDoubleValue(strValue1,cdoRequest);
					double value2=getDoubleValue(strValue2,cdoRequest);
					return value1>=value2;
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
		}
		else if(strOperator.equalsIgnoreCase("<="))
		{
			switch(nType)
			{
				case SQLIfTypeType.INTEGER_TYPE:
				{
					int value1=getIntegerValue(strValue1,cdoRequest);
					int value2=getIntegerValue(strValue2,cdoRequest);
					return value1<value2;
				}
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<=0;
				}
				case SQLIfTypeType.LONG_TYPE:
				{
					long value1=getLongValue(strValue1,cdoRequest);
					long value2=getLongValue(strValue2,cdoRequest);
					return value1<=value2;
				}
				case SQLIfTypeType.BYTE_TYPE:
				{
					byte value1=getByteValue(strValue1,cdoRequest);
					byte value2=getByteValue(strValue2,cdoRequest);
					return value1<=value2;
				}
				case SQLIfTypeType.SHORT_TYPE:
				{
					short value1=getShortValue(strValue1,cdoRequest);
					short value2=getShortValue(strValue2,cdoRequest);
					return value1<=value2;
				}
				case SQLIfTypeType.DATE_TYPE:
				{
					String value1=getDateValue(strValue1,cdoRequest);
					String value2=getDateValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<=0;
				}
				case SQLIfTypeType.TIME_TYPE:
				{
					String value1=getTimeValue(strValue1,cdoRequest);
					String value2=getTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<=0;
				}
				case SQLIfTypeType.DATETIME_TYPE:
				{
					String value1=getDateTimeValue(strValue1,cdoRequest);
					String value2=getDateTimeValue(strValue2,cdoRequest);
					return value1.compareTo(value2)<=0;
				}
				case SQLIfTypeType.FLOAT_TYPE:
				{
					float value1=getFloatValue(strValue1,cdoRequest);
					float value2=getFloatValue(strValue2,cdoRequest);
					return value1<=value2;
				}
				case SQLIfTypeType.DOUBLE_TYPE:
				{
					double value1=getDoubleValue(strValue1,cdoRequest);
					double value2=getDoubleValue(strValue2,cdoRequest);
					return value1<=value2;
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
		}
		else if(strOperator.equalsIgnoreCase("MATCH"))
		{
			switch(nType)
			{
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
					return value1.matches(value2);
				}
				default:
				{
					throw new RuntimeException("Invalid type "+strType);
				}
			}
		}
		else if(strOperator.equalsIgnoreCase("NOTMATCH"))
		{
			switch(nType)
			{
				case SQLIfTypeType.STRING_TYPE:
				{
					String value1=getStringValue(strValue1,cdoRequest);
					String value2=getStringValue(strValue2,cdoRequest);
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
	 * 处理SQL语句中的If语句
	 * 
	 * @param sqlIf
	 * @param cdoRequest
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 * @throws Exception
	 */
	private int handleSQLIf(SQLIf sqlIf,CDO cdoRequest,StringBuilder strbSQL)
	{
		// 检查执行条件
		boolean bCondition=checkCondition(sqlIf.getValue1(),sqlIf.getOperator().toString(),sqlIf.getValue2(),sqlIf
						.getType().getType(),sqlIf.getType().toString(),cdoRequest);
		if(bCondition==true)
		{// Handle Then
			SQLThen sqlThen=sqlIf.getSQLThen();
			return handleSQLBlock(sqlThen,cdoRequest,strbSQL);
		}
		else
		{// handle Else
			SQLElse sqlElse=sqlIf.getSQLElse();
			if(sqlElse==null)
			{// 自然完成
				return 0;
			}
			return handleSQLBlock(sqlElse,cdoRequest,strbSQL);
		}
	}

	/**
	 * 处理SQL语句中的For语句
	 * 
	 * @param sqlFor
	 * @param cdoRequest
	 * @param strbSQL
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 * @throws Exception
	 */
	protected int handleSQLFor(SQLFor sqlFor,CDO cdoRequest,StringBuilder strbSQL)
	{
		// 获取循环数据
		int nFromIndex=this.getIntegerValue(sqlFor.getFromIndex(),cdoRequest);
		int nCount=this.getIntegerValue(sqlFor.getCount(),cdoRequest);
		String strIndexId=sqlFor.getIndexId();
		strIndexId=strIndexId.substring(1,strIndexId.length()-1);

		// 执行循环
		for(int i=nFromIndex;i<nFromIndex+nCount;i++)
		{
			// 设置IndexId
			cdoRequest.setIntegerValue(strIndexId,i);

			// 执行Block
			int nResult=handleSQLBlock(sqlFor,cdoRequest,strbSQL);
			if(nResult==0)
			{// 自然执行完毕
				continue;
			}
			else if(nResult==1)
			{// 碰到Break
				break;
			}
			else
			{// 碰到Return
				return nResult;
			}
		}

		return 0;
	}

	/**
	 * 处理SQLBlock对象，得到输出的SQL语句
	 * 
	 * @param sqlBlock
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 */
	protected int handleSQLBlock(SQLBlockType sqlBlock,CDO cdoRequest,StringBuilder strbSQL)
	{
		// 依次处理各个Item
		int nItemCount=sqlBlock.getSQLBlockTypeItemCount();
		for(int i=0;i<nItemCount;i++)
		{
			// 处理当前的Item
			SQLBlockTypeItem item=sqlBlock.getSQLBlockTypeItem(i);
			if(item.getOutputSQL()!=null)
			{// OutputSQL,直接输出源文本
				strbSQL.append(item.getOutputSQL());
			}
			else if(item.getOutputField()!=null)
			{// OutputField，输出文本代表的字段值
				String strOutputFieldId=item.getOutputField();
				strOutputFieldId=strOutputFieldId.substring(1,strOutputFieldId.length()-1);
				strbSQL.append(cdoRequest.getStringValue(strOutputFieldId));
			}
			else if(item.getSQLIf()!=null)
			{// SQLIf
				int nResult=handleSQLIf(item.getSQLIf(),cdoRequest,strbSQL);
				if(nResult==0)
				{// 自然执行完毕
					continue;
				}
				else
				{// 碰到Break或Return
					return nResult;
				}
			}
			else if(item.getSQLFor()!=null)
			{// SQLFor
				int nResult=handleSQLFor(item.getSQLFor(),cdoRequest,strbSQL);
				if(nResult==0)
				{// 自然执行完毕
					continue;
				}
				else
				{// 碰到Break或Return
					return nResult;
				}
			}
			else
			{
				continue;
			}
		}

		// 自然执行完毕
		return 0;
	}

	/**
	 * 处理If语句
	 * 
	 * @param ifItem
	 * @param cdoRequest
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 * @throws Exception
	 */
	private int handleIf(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccess,SQLTrans trans,If ifItem,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
		// 检查执行条件
		boolean bCondition=checkCondition(ifItem.getValue1(),ifItem.getOperator().toString(),ifItem.getValue2(),ifItem.getType().getType(),ifItem.getType().toString(),cdoRequest);
		if(bCondition==true)
		{// Handle Then
			Then thenItem=ifItem.getThen();
			return handleBlock(hmDataGroup,dataService,hmDataAccess,trans,thenItem,cdoRequest,cdoResponse,ret);
		}
		else
		{// handle Else
			Else elseItem=ifItem.getElse();
			if(elseItem==null)
			{// 没有else模块，当作自然执行完毕处理???
				return 0;
			}
			return handleBlock(hmDataGroup,dataService,hmDataAccess,trans,elseItem,cdoRequest,cdoResponse,ret);
		}
	}

	/**
	 * 处理For语句
	 * 
	 * @param sqlFor
	 * @param cdoRequest
	 * @param strbSQL
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 * @throws Exception
	 */
	private int handleFor(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccess,SQLTrans trans,For forItem,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
		// 获取循环数据
		int nFromIndex=this.getIntegerValue(forItem.getFromIndex(),cdoRequest);
		int nCount=this.getIntegerValue(forItem.getCount(),cdoRequest);
		String strIndexId=forItem.getIndexId();
		strIndexId=strIndexId.substring(1,strIndexId.length()-1);

		// 执行循环
		for(int i=nFromIndex;i<nFromIndex+nCount;i++)
		{
			// 设置IndexId
			cdoRequest.setIntegerValue(strIndexId,i);

			// 执行Block
			int nResult=handleBlock(hmDataGroup,dataService,hmDataAccess,trans,forItem,cdoRequest,cdoResponse,ret);
			if(nResult==0)
			{// 自然执行完毕
				continue;
			}
			else if(nResult==1)
			{// 碰到Break
				break;
			}
			else
			{// 碰到Return
				return nResult;
			}
		}

		return 0;
	}

	private BigTable selectBigTable(BigTable[] bts,String strTableName)
	{
		if (bts == null)
		{
			return  null;
		}
		for(int i=0;i<bts.length;i++)
		{
			if(bts[i].getName().equalsIgnoreCase(strTableName)==true)
			{
				return bts[i];
			}
		}
		return  null;
	}

	private DataAccess getDataAccess(HashMap<String,CycleList<IDataEngine>> hmDataGroup,String strDataGroupId) throws SQLException
	{
		//没有可重用的DataAccess
		CycleList<IDataEngine> clDataEngine=hmDataGroup.get(strDataGroupId);
		if(clDataEngine==null)
		{//DataGroupId错误
			return null;
		}
		//创建Connection
		IDataEngine dataEngine=clDataEngine.get();
		Connection conn=dataEngine.getConnection();
		if(conn==null)
		{
			return null;
		}
		DataAccess da=new DataAccess();
		da.dataEngine=dataEngine;
		da.conn=conn;
		
		return da;
	}

	private void selectTable(DataAccess dataAccess,BigTable bigTable,long lId)
	{
		dataAccess.nTableIndex=(int)((lId/bigTable.getGroupTableCapacity())%bigTable.getGroupTableCount());
	}

	private void selectTable(DataAccess dataAccess,BigTable bigTable,String strIdFieldId,boolean bRandSelect,CDO cdoRequest)
	{
		if(strIdFieldId==null)
		{//没有提供 IdFieldId
			if(bRandSelect==false)
			{//不可以随机获取
				return;
			}
			
			//可以随机，随机分配一个Table
			dataAccess.nTableIndex=rand.nextInt(bigTable.getGroupTableCount());

			return;
		}

		//提供了Id
		long lId=cdoRequest.getLongValue(strIdFieldId);
		dataAccess.nTableIndex=(int)((lId/bigTable.getGroupTableCapacity())%bigTable.getGroupTableCount());
	}
	
	/**
	 * 根据BigTableName和Id选择连接输出,批量操作使用
	 */
	private DataAccess selectConnection(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,String strDefaultDataGroupId,String strBigTableName,long lId,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans) throws SQLException
	{
		DataAccess da=null;
		if(strBigTableName==null || strBigTableName.length()==0)
		{//没有BigTable
			da=hmDataAccessUsed.get("group."+strDefaultDataGroupId);
			if(da!=null)
			{//有可重用的DataAccess
				return da;
			}
			//没有可重用的DataAccess
			da=this.getDataAccess(hmDataGroup,strDefaultDataGroupId);
			if(da==null)
			{
				return null;
			}
			
			DataAccess daOld=hmDataAccessUsed.put("group."+strDefaultDataGroupId,da);
//			if(daOld!=null)
//			{
//				try
//				{
//					daOld.conn.close();
//				}
//				catch(Exception e)
//				{
//				}
//			}
			daOld=hmDataAccessUsed.put(null,da);

			return da;
		}
		
		//有BigTable
		int nBigTableGroupCount=dataService.getBigTableGroupCount();
		if(nBigTableGroupCount==0)
		{//BigTable没有配置
			return null;
		}
		BigTable bigTable=selectBigTable(dataService.getBigTableArray(trans.getBigTableGroupId()),strBigTableName);
		if(bigTable==null)
		{//该BigTable没有配置
			return null;
		}

		//首先尝试重用已有的DataAccess
		da=hmDataAccessUsed.get("table."+strBigTableName);
		if(da!=null)
		{//有可重用的DataAccess
			long lStartId=bigTable.getGroupTableCount()*da.nGroupIndex*bigTable.getGroupTableCapacity();
			long lEndId=lStartId+bigTable.getGroupTableCapacity()*bigTable.getGroupTableCount();
			if(lId>=lStartId && lId<lEndId)
			{//确实可用
				hmDataAccessUsed.put(null,da);
				return da;
			}
		}

		//提供了Id
		int nGroupIndex=(int)(lId/bigTable.getGroupTableCapacity())/bigTable.getGroupTableCount();

		da=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+nGroupIndex);
		if(da==null)
		{
			return null;
		}
		da.nGroupIndex=nGroupIndex;
		
		hmDataAccessUsed.put("table."+strBigTableName+nGroupIndex,da);
		DataAccess daOld=hmDataAccessUsed.put("table."+strBigTableName,da);
//		if(daOld!=null)
//		{
//			try
//			{
//				daOld.conn.close();
//			}
//			catch(Exception e)
//			{
//			}
//		}

		hmDataAccessUsed.put(null,da);
		return da;
	}

	/**
	 * 根据BigTableName和Id选择连接输出
	 */
	private DataAccess selectConnection(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,String strDefaultDataGroupId,String strBigTableName,String strIdFieldId,boolean bRandSelect,CDO cdoRequest,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans) throws SQLException
	{
		DataAccess da=null;
		
		try
		{
			if(strBigTableName==null || strBigTableName.length()==0)
			{//没有BigTable
				da=hmDataAccessUsed.get("group."+strDefaultDataGroupId);
				if(da!=null)
				{//有可重用的DataAccess
					return da;
				}
				//没有可重用的DataAccess
				da=this.getDataAccess(hmDataGroup,strDefaultDataGroupId);
				if(da==null)
				{
					return null;
				}
				
				DataAccess daOld=hmDataAccessUsed.put("group."+strDefaultDataGroupId,da);
//				if(daOld!=null)
//				{
//					try
//					{
//						daOld.conn.close();
//					}
//					catch(Exception e)
//					{
//					}
//				}
				hmDataAccessUsed.put(null,da);
	
				return da;
			}
			

			if(dataService.hasBigTable()==false)
			{//BigTable没有配置
				return null;
			}
			BigTable bigTable=selectBigTable(dataService.getBigTableArray(trans.getBigTableGroupId()),strBigTableName);
			if(bigTable==null)
			{//该BigTable没有配置
				return null;
			}
	
			//首先尝试重用已有的DataAccess
			da=hmDataAccessUsed.get("table."+strBigTableName);
			if(da!=null)
			{//有可重用的DataAccess
				if(strIdFieldId==null||strIdFieldId.length()==0)
				{
					hmDataAccessUsed.put(null,da);
					return da;
				}
	
				long lStartId=bigTable.getGroupTableCount()*da.nGroupIndex*bigTable.getGroupTableCapacity();
				long lEndId=lStartId+bigTable.getGroupTableCapacity()*bigTable.getGroupTableCount();
				long lId=cdoRequest.getLongValue(strIdFieldId);
				if(lId>=lStartId && lId<lEndId)
				{//确实可用
					hmDataAccessUsed.put(null,da);
					return da;
				}
			}
			//没有可重用的DataAccess
			if(strIdFieldId==null||strIdFieldId.length()==0)
			{//没有提供 IdFieldId
				if(bRandSelect==false)
				{//不可以随机获取
					return null;
				}
				
				//可以随机，随机分配一个Table
				int nGroupIndex=rand.nextInt(bigTable.getGroupCount());
				
				da=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+nGroupIndex);
				if(da==null)
				{
					return null;
				}
				da.nGroupIndex=nGroupIndex;

				hmDataAccessUsed.put("table."+strBigTableName+nGroupIndex,da);
				DataAccess daOld=hmDataAccessUsed.put("table."+strBigTableName,da);
//				if(daOld!=null)
//				{
//					try
//					{
//						daOld.conn.close();
//					}
//					catch(Exception e)
//					{
//					}
//				}

				hmDataAccessUsed.put(null,da);
				return da;
			}
	
			//提供了Id
			long lId=cdoRequest.getLongValue(strIdFieldId);
			int nGroupIndex=(int)(lId/bigTable.getGroupTableCapacity())/bigTable.getGroupTableCount();
			
			//update by Aaron Lin 2011-03-28 原来的逻辑会重取连接，并覆盖老的已有连接，会造成连接上的事务不提交，不关闭
			da = hmDataAccessUsed.get("table."+strBigTableName+nGroupIndex);
			if(da==null)
			{	
				da=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+nGroupIndex);
				if(da==null)
				{
					return null;
				}
				da.nGroupIndex=nGroupIndex;
				
				hmDataAccessUsed.put("table."+strBigTableName+nGroupIndex,da);
			}
			DataAccess daOld=hmDataAccessUsed.put("table."+strBigTableName,da);
//			if(daOld!=null)
//			{
//				try
//				{
//					daOld.conn.close();
//				}
//				catch(Exception e)
//				{
//				}
//			}

			hmDataAccessUsed.put(null,da);
			return da;
		}
		finally
		{
			if(da!=null)
			{
				if(trans.getTransFlag().getType()!=0)
				{
					da.conn.setAutoCommit(false);
				}
			}
		}
	}

	/**
	 * 分析SQL语法 {}之内的为参数名，需要替换成? {{代表{字符 }}代表}字符
	 * strSQLType: insert,update,delete,selectfield,selectrecord,selectrecordset
	 */
	private ParsedSQL parseSourceSQL(String strSourceSQL)
	{
		ParsedSQL parsedSQL=null;
		
		synchronized(hmParsedSQL)
		{
			parsedSQL=hmParsedSQL.get(strSourceSQL);
		}
		if(parsedSQL!=null)
		{
			return parsedSQL;
		}

		String strIdParaName				=null;
		ArrayList<String> alParaName		=new ArrayList<String>();
		ArrayList<String> alDataGroupId		=new ArrayList<String>();
		ArrayList<String> alTableName		=new ArrayList<String>();
		String strTableName					=null;
		String strCountFieldName			=null;
		String strGroupFieldName			=null;
		String strOrderBy					=null;
		String strPage						=null;
		String strBatchIds					=null;

		StringBuilder strbSQL=new StringBuilder();

		int nState=0;// 0 : {} 之外的字符, 1: {}之内字符.
		int nLength=strSourceSQL.length();

		StringBuilder strbParaName=new StringBuilder(nLength);
		int i=0;
		while(i<nLength)
		{
			char ch=strSourceSQL.charAt(i);
			switch(ch)
			{
				case '{':
					if(nState==0)
					{// 在{}之外
						if(i+1<nLength&&strSourceSQL.charAt(i+1)=='{')
						{// 为普通字符
							strbSQL.append("{");
							i+=2;
						}
						else
						{// 字段开始
							nState=1;
							i++;
						}
					}
					else
					{// 在{}之内，语法错误
						callOnException("analyzeSQL error",new Exception("SQL syntax Error: "+strSourceSQL));
						return null;
					}
					break;
				case '}':
					if(nState==0)
					{// 在{}之外
						if(i+1<nLength&&strSourceSQL.charAt(i+1)=='}')
						{// 为普通字符
							strbSQL.append("}");
							i++;
						}
						else
						{// 语法错误
							callOnException("analyzeSQL error",new Exception("SQL syntax Error: "+strSourceSQL));
							return null;
						}
					}
					else
					{// 在{}之内，字段结束
						if(strbParaName.length()==0)
						{
							callOnException("analyzeSQL error",new Exception("SQL syntax Error: "+strSourceSQL));
							return null;
						}
						nState=0;
						
						//参数处理
						if(strbParaName.indexOf("T:")==0)
						{//Big Table Name: select * from {T:tbTable}
							strTableName=strbParaName.substring(2);
							strbSQL.append('{').append(strbParaName).append('}');
						}
						else if(strbParaName.indexOf("I:")==0)
						{//Id字段: where {I:strId}=1
							strIdParaName=strbParaName.substring(2);
							alParaName.add(strIdParaName);
							strbSQL.append('{').append(strbParaName.substring(2)).append('}');
						}
						else if(strbParaName.indexOf("S:")==0)
						{//Big Table Name: select last_insert_id() {S:tbTable}
							strTableName=strbParaName.substring(2);
						}
						else if(strbParaName.indexOf("C:")==0)
						{//Count处理: select count(*) {C:nCount} 
							String strParaName=strbParaName.substring(2);
							strCountFieldName=strParaName;
							strbSQL.append(strParaName);
						}
						else if(strbParaName.indexOf("O:")==0)
						{//排序语句的排序字段及顺序: order by {O:nAge desc}
							String strParaName=strbParaName.substring(2);
							strOrderBy=strParaName;
							strbSQL.append("order by ").append(strParaName);
						}
						else if(strbParaName.indexOf("G:")==0)
						{//Group by: group by {G:lBrandId}
							String strParaName=strbParaName.substring(2);
							strGroupFieldName=strParaName;
							strbSQL.append(strParaName);
						}
						else if(strbParaName.indexOf("L:")==0)
						{//{L:nPageIndex,nPageSize}
							String strParaName=strbParaName.substring(2);
							strPage=strParaName;
							strbSQL.append('{').append(strbParaName).append('}');
						}
						else if(strbParaName.indexOf("B:")==0)
						{//Batch Ids {B:lId,1,2,3}
							String strParaName=strbParaName.substring(2);
							int nSplitIndex=strParaName.indexOf(',');
							if(nSplitIndex<=0)
							{
								callOnException("parseSQL error",new Exception("SQL syntax Error: "+strSourceSQL));
								return null;
							}
							strBatchIds=strParaName;
							strbSQL.append('{').append(strParaName.substring(0,nSplitIndex)).append('}');
						}
						else
						{//普通参数
							strbSQL.append('{').append(strbParaName).append('}');
							alParaName.add(strbParaName.toString());
						}
						
						strbParaName.setLength(0);
					}
					i++;
					break;
				default:
					if(nState==0)
					{// 在{}之外
						strbSQL.append(ch);
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
			callOnException("parseSQL error",new Exception("SQL syntax Error: "+strSourceSQL));
			return null;
		}

		parsedSQL					=new ParsedSQL();
		parsedSQL.strSQL			=strbSQL.toString();
		parsedSQL.strIdFieldName	=strIdParaName;
		parsedSQL.alParaName		=alParaName;
		parsedSQL.alDataGroupId		=alDataGroupId;
		parsedSQL.strTableName		=strTableName;
		parsedSQL.strCountFieldName	=strCountFieldName;
		parsedSQL.strGroupFieldName	=strGroupFieldName;
		parsedSQL.alTableName		=alTableName;
		parsedSQL.strOrderBy		=strOrderBy;
		parsedSQL.strPage			=strPage;
		parsedSQL.strBatchIds		=strBatchIds;

		synchronized(hmParsedSQL)
		{
			hmParsedSQL.put(strSourceSQL,parsedSQL);
		}

		return parsedSQL;
	}
	
	/**
	 * 执行insert语句
	 * 
	 * @param hmDataGroup
	 * @param strSQL
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	protected int executeInsert(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans,String strSQL,CDO cdoRequest) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}
		String strDefaultDataGroupId = trans.getDataGroupId();
		DataAccess dataAccess=null;
		if(parsedSQL.strTableName==null)
		{//没有BigTable
			//尝试重用已有连接
			dataAccess=hmDataAccessUsed.get(null);
			if(dataAccess==null)
			{//当前没有连接
				//获取连接
				dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,null,null,false,cdoRequest,hmDataAccessUsed,trans);
				if(dataAccess==null)
				{
					throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
				}
			}
			
			//执行数据库操作
			dataAccess.dataEngine.executeUpdate(dataAccess.conn,strSQL,cdoRequest);
			
			return 0;
		}

		//有BigTable，尝试定位目标表
		BigTable bigTable=this.selectBigTable(dataService.getBigTableArray(trans.getBigTableGroupId()),parsedSQL.strTableName);
		if(bigTable==null)
		{
			throw new SQLException("Dest-table not found: "+strSQL);
		}
		//获取连接
		//尝试重用已有连接
		dataAccess=hmDataAccessUsed.get(null);
		if(dataAccess==null)
		{//没有可重用连接，选取连接，并确定目标表，可以随机确定目标表
			dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,parsedSQL.strIdFieldName,true,cdoRequest,hmDataAccessUsed,trans);
		}
		if(dataAccess==null)
		{
			throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
		}
		this.selectTable(dataAccess,bigTable,parsedSQL.strIdFieldName,true,cdoRequest);

		//修改SQL语句中的表名
		strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);
		

		//执行数据库操作
		dataAccess.dataEngine.executeUpdate(dataAccess.conn,strSQL,cdoRequest);
		
		return 0;
	}
	
	/**
	 * 通过一个传入的数据库连接查询并输出第一条记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	protected int executeQueryRecord(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans,String strSQL,CDO cdoRequest,CDO cdoResponse) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}

		DataAccess dataAccess=null;
		String strDefaultDataGroupId = trans.getDataGroupId();
		if(parsedSQL.strTableName==null)
		{//没有BigTable
			//尝试重用已有连接
			dataAccess=hmDataAccessUsed.get(null);
			if(dataAccess==null)
			{//当前没有连接
				//获取连接
				dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,null,null,false,cdoRequest,hmDataAccessUsed,trans);
				if(dataAccess==null)
				{
					throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
				}
			}
			
			//执行数据库操作
			return dataAccess.dataEngine.executeQueryRecord(dataAccess.conn,strSQL,cdoRequest,cdoResponse);
		}

		//有BigTable，尝试定位目标表
		BigTable bigTable=this.selectBigTable(dataService.getBigTableArray(trans.getBigTableGroupId()),parsedSQL.strTableName);
		if(bigTable==null)
		{
			throw new SQLException("Dest-table not found: "+strSQL);
		}
		//获取连接
		//尝试重用已有连接
		dataAccess=hmDataAccessUsed.get(null);
		if(dataAccess==null)
		{//没有可重用连接
			dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,parsedSQL.strIdFieldName,false,cdoRequest,hmDataAccessUsed,trans);
		}
		if(dataAccess!=null)
		{//拿到了连接
			this.selectTable(dataAccess,bigTable,parsedSQL.strIdFieldName,false,cdoRequest);

			//修改SQL语句中的表名
			strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);

			//执行数据库操作
			return dataAccess.dataEngine.executeQueryRecord(dataAccess.conn,strSQL,cdoRequest,cdoResponse);
		}

		//没有拿到连接
		if(parsedSQL.strIdFieldName!=null)
		{//不应该拿不到
			throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
		}

		//未提供Id，遍历数据库表读取记录
		int nCount=0;
		if(parsedSQL.strCountFieldName==null)
		{//一般性记录
			int nGroupCount=bigTable.getGroupCount();
			for(int i=0;i<nGroupCount;i++)
			{
				//获取连接
				dataAccess=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+i);
				if(dataAccess==null)
				{
					throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
				}
				
				try
				{
					int nTableCount=bigTable.getGroupTableCount();
					for(int j=0;j<nTableCount;j++)
					{
						//修改SQL语句中的表名
						strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+j);
	
						//执行数据库操作
						int nReadCount=dataAccess.dataEngine.executeQueryRecord(dataAccess.conn,strSQL,cdoRequest,cdoResponse);
						if(nReadCount>0)
						{
							nCount=nReadCount;
							break;
						}
					}
				}
				finally
				{
					try
					{
						dataAccess.conn.close();
					}
					catch(Exception e)
					{
					}
				}
				if(nCount>0)
				{
					break;
				}
			}
			
			return nCount;
		}

		//有Count统计，需要累加
		int nCountValue=0;
		int nGroupCount=bigTable.getGroupCount();
		for(int i=0;i<nGroupCount;i++)
		{
			//获取连接
			dataAccess=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+i);
			if(dataAccess==null)
			{
				throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
			}
			
			try
			{
				int nTableCount=bigTable.getGroupTableCount();
				for(int j=0;j<nTableCount;j++)
				{
					//修改SQL语句中的表名
					strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+j);
	
					//执行数据库操作
					int nReadCount=dataAccess.dataEngine.executeQueryRecord(dataAccess.conn,strSQL,cdoRequest,cdoResponse);
					if(nReadCount==0)
					{//未读取到数据
						continue;
					}
					
					//读取Count数据
					nCountValue+=cdoResponse.getIntegerValue(parsedSQL.strCountFieldName);
				}
			}
			finally
			{
				try
				{
					dataAccess.conn.close();
				}
				catch(Exception e)
				{
				}
			}
		}
		cdoResponse.setIntegerValue(parsedSQL.strCountFieldName,nCountValue);
		
		return nCount;
	}

	/**
	 * 从long数组中，挑选出最小的值的数组下标，输出
	 * @param cdosRecord
	 * @param strValueFieldId
	 * @return
	 */
	private int[] getMinMaxValueIndexList(long[] lsValue,boolean bMin)
	{
		int[] naIndex=new int[lsValue.length];
		int nCount=0;
		long lMinMaxValue=-1;
		
		if(bMin==true)
		{
			for(int i=0;i<lsValue.length;i++)
			{
				long lValue=lsValue[i];
				if(lValue==-1)
				{
					continue;
				}
				if(nCount==0 || lValue<lMinMaxValue)
				{
					naIndex[0]=i;
					lMinMaxValue=lValue;
					nCount=1;
				}
				else if(lValue==lMinMaxValue)
				{
					naIndex[nCount]=i;
					nCount++;
				}
				else
				{
					continue;
				}
			}
		}
		else
		{
			for(int i=0;i<lsValue.length;i++)
			{
				long lValue=lsValue[i];
				if(lValue==-1)
				{
					continue;
				}
				if(nCount==0 || lValue>lMinMaxValue)
				{
					naIndex[0]=i;
					lMinMaxValue=lValue;
					nCount=1;
				}
				else if(lValue==lMinMaxValue)
				{
					naIndex[nCount]=i;
					nCount++;
				}
				else
				{
					continue;
				}
			}
		}
		
		int[] naOutput=new int[nCount];
		System.arraycopy(naIndex,0,naOutput,0,nCount);
		
		return naOutput;
	}
	
	/**
	 * 执行查询操作，输出多条记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	protected int executeQueryRecordSet(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans,String strSQL,CDO cdoRequest,CDOArrayField cdoafOutput) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}

		DataAccess dataAccess=null;
		String strDefaultDataGroupId = trans.getDataGroupId();
		if(parsedSQL.strTableName==null)
		{//没有BigTable
			//尝试重用已有连接
			dataAccess=hmDataAccessUsed.get(null);
			if(dataAccess==null)
			{//当前没有连接
				//获取连接
				dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,null,null,false,cdoRequest,hmDataAccessUsed,trans);
				if(dataAccess==null)
				{
					throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
				}
			}
			
			//执行数据库操作
			return dataAccess.dataEngine.executeQueryRecordSet(dataAccess.conn,strSQL,cdoRequest,cdoafOutput);
		}

		//有BigTable，尝试定位目标表
		BigTable bigTable=this.selectBigTable(dataService.getBigTableArray(trans.getBigTableGroupId()),parsedSQL.strTableName);
		if(bigTable==null)
		{
			throw new SQLException("Dest-table not found: "+strSQL);
		}
		
		//执行特定全库全表扫描任务
		if (cdoRequest.exists("nBigTableScanType") && !(cdoRequest.getIntegerValue("nBigTableScanType")+"").equals("")) 
		{
//			int nCount=prepareScanTypeAndExecuteAllDBAllTableScan(hmDataGroup,
//					hmDataAccessUsed, strSQL, cdoRequest, cdoafOutput,
//					parsedSQL, dataAccess, bigTable, strDefaultDataGroupId, dataService, trans);
			int nCount=prepareScanTypeAndExecuteAllDBAllTableScan(hmDataGroup,
					hmDataAccessUsed, strSQL, cdoRequest, cdoafOutput,
					parsedSQL, dataAccess, bigTable, strDefaultDataGroupId, dataService, trans);
			if (nCount!=-1)
			{
				return nCount;
			}
		}
		
		//获取连接
		//尝试重用已有连接
		dataAccess=hmDataAccessUsed.get(null);
		if(dataAccess==null)
		{//没有可重用连接
			dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,parsedSQL.strIdFieldName,false,cdoRequest,hmDataAccessUsed,trans);
		}
		if(dataAccess!=null)
		{//拿到了连接
			this.selectTable(dataAccess,bigTable,parsedSQL.strIdFieldName,false,cdoRequest);

			//修改SQL语句中的表名
			strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);

			strSQL=preparePaging(strSQL, parsedSQL);

			//执行数据库操作
			return dataAccess.dataEngine.executeQueryRecordSet(dataAccess.conn,strSQL,cdoRequest,cdoafOutput);
		}

		//没有拿到连接
		if(parsedSQL.strIdFieldName!=null)
		{//不应该拿不到
			throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
		}

		//未提供Id，遍历数据库表读取记录，获取多个ResultSet
		//提供多个Id，获取Id对应ResultSet

		String[] strsPageItem=null;
		int nFromIndex=-1;
		int nToIndex=-1;
		if(parsedSQL.strPage!=null)
		{
			StringBuilder strbLimit=new StringBuilder("limit ");
			strsPageItem=parsedSQL.strPage.split(" ");
			if(Utility.isNumberText(strsPageItem[0])==true)
			{
				nFromIndex=Integer.parseInt(strsPageItem[0]);
			}
			else
			{
				nFromIndex=cdoRequest.getIntegerValue(strsPageItem[0]);
			}
			strbLimit.append("0,");
			if(Utility.isNumberText(strsPageItem[1])==true)
			{
				strbLimit.append(strsPageItem[1]);
				nToIndex=nFromIndex+Integer.parseInt(strsPageItem[1]);
			}
			else
			{
				nToIndex=nFromIndex+cdoRequest.getIntegerValue(strsPageItem[1]);
				strbLimit.append(nToIndex);
			}
			strSQL=parsedSQL.strSQL.replaceAll("\\{L:"+parsedSQL.strPage+"\\}",strbLimit.toString());
		}else{
			strSQL=parsedSQL.strSQL.toString();
		}


		//组建请求
		ArrayList<HashMap> alInputSet=new ArrayList<HashMap>();
		ArrayList<HashMap> alOutputSet=new ArrayList<HashMap>();
		
		if (parsedSQL.strBatchIds!=null)
		{
			buildRequest4QueryRecordSet(hmDataGroup, dataService,
					hmDataAccessUsed, trans, strSQL, cdoRequest, parsedSQL,
					strDefaultDataGroupId, bigTable, alInputSet, alOutputSet);
		}
		else
		{
			buildRequest4QueryRecordSet(hmDataGroup, strSQL, cdoRequest, parsedSQL,
					bigTable, alInputSet, alOutputSet, bigTable.getGroupCount(), bigTable.getGroupTableCount());
		}

		//检查执行结果
		boolean bOK=true;
		int nCount=alOutputSet.size();
		for(int i=0;i<nCount;i++)
		{
			ResultSet rs=(ResultSet)alOutputSet.get(i).get("rs");
			if(rs==null)
			{
				bOK=false;
				break;
			}
		}

		int nRecordCount=0;//最终输出的记录个数
		
		try
		{
			if(bOK==true)
			{//处理查询结果
				//获取Meta信息
				ResultSetMetaData meta=((ResultSet)alOutputSet.get(0).get("rs")).getMetaData();
				MetaData metaData=new MetaData();
				metaData.strsFieldName=new String[meta.getColumnCount()];
				metaData.nsFieldType=new int[metaData.strsFieldName.length];
				metaData.nsPrecision=new int[metaData.strsFieldName.length];
				metaData.nsScale=new int[metaData.strsFieldName.length];
				for(int i=0;i<metaData.strsFieldName.length;i++)
				{
					metaData.strsFieldName[i]=meta.getColumnName(i+1);
					metaData.nsFieldType[i]=meta.getColumnType(i+1);
					metaData.nsPrecision[i]=meta.getPrecision(i+1);
					metaData.nsScale[i]=meta.getScale(i+1);
				}
	
				//开始处理
				String strOrderFieldId=null;
				boolean bAscending=true;
				String strOrderBy=parsedSQL.strOrderBy;
				if(strOrderBy!=null && strOrderBy.length()>0)
				{//有排序要求
					String[] strsOrderItem=strOrderBy.split(" ");
					strOrderFieldId=strsOrderItem[0];
					if(strsOrderItem.length==2 && strsOrderItem[1].equalsIgnoreCase("desc"))
					{
						bAscending=false;
					}
				}
				List<CDO> alCDOList=new ArrayList<CDO>();//用于临时保存输出的记录
				SortedSet<Long,CDO> ssCDOSet=new SortedSet<Long,CDO>();
				if(parsedSQL.strCountFieldName!=null && parsedSQL.strGroupFieldName!=null)
				{
					//初始化每个ResultSet的Group字段当前值
					long[] lsSortedFieldValue=new long[nCount];
					for(int i=0;i<nCount;i++)
					{
						lsSortedFieldValue[i]=-1;
					}
					CDO[] cdosRecord=new CDO[nCount];
					
					//每个ResultSet都取出一个值
					for(int i=0;i<nCount;i++)
					{
						dataAccess=(DataAccess)alInputSet.get(i).get("dataAccess");
						ResultSet rs=(ResultSet)alOutputSet.get(i).get("rs");
						cdosRecord[i]=dataAccess.dataEngine.readRecord(rs,metaData.strsFieldName,metaData.nsFieldType,metaData.nsPrecision,metaData.nsScale);
						if (cdosRecord[i]!=null)
						{
							lsSortedFieldValue[i]=cdosRecord[i].getLongValue(parsedSQL.strGroupFieldName);
						}
					}

					//循环输出Group字段值最小的记录
					while(true)
					{
						int[] nsIndex=this.getMinMaxValueIndexList(lsSortedFieldValue,true);
						if(nsIndex.length==0)
						{
							break;
						}
						
						int nGroupFieldCount=cdosRecord[nsIndex[0]].getIntegerValue(parsedSQL.strCountFieldName);
						for(int i=1;i<nsIndex.length;i++)
						{
							nGroupFieldCount+=cdosRecord[nsIndex[i]].getIntegerValue(parsedSQL.strCountFieldName);
						}
						cdosRecord[nsIndex[0]].setIntegerValue(parsedSQL.strCountFieldName,nGroupFieldCount);
	
						//输出记录
						if(strOrderFieldId==null)
						{//不需要再排序
							alCDOList.add(cdosRecord[nsIndex[0]]);
							if(strsPageItem!=null && alCDOList.size()>=nToIndex)
							{//输出记录个数已经达到，停止输出
								break;
							}
						}
						else
						{//需要再排序，加入排序集合
							long lKey=cdosRecord[nsIndex[0]].getLongValue(strOrderFieldId);
							ssCDOSet.add(lKey,cdosRecord[nsIndex[0]]);
						}
						
						//已经输出的ResultSet，重新获取
						for(int i=0;i<nsIndex.length;i++)
						{
							dataAccess=(DataAccess)alInputSet.get(nsIndex[i]).get("dataAccess");
							ResultSet rs=(ResultSet)alOutputSet.get(nsIndex[i]).get("rs");
							cdosRecord[nsIndex[i]]=dataAccess.dataEngine.readRecord(rs,metaData.strsFieldName,metaData.nsFieldType,metaData.nsPrecision,metaData.nsScale);
							if(cdosRecord[nsIndex[i]]==null)
							{
								lsSortedFieldValue[nsIndex[i]]=-1;
							}
							else
							{
								lsSortedFieldValue[nsIndex[i]]=cdosRecord[nsIndex[i]].getLongValue(parsedSQL.strGroupFieldName);
							}
						}
					}
	
					if(strOrderFieldId!=null)
					{//输出结果需要排序，把ssCDOSet里面的结果加入alCDOList
						CDO[] cdosTemp=new CDO[ssCDOSet.size()];
						cdosTemp=ssCDOSet.toArray(bAscending,cdosTemp);
						for(int i=0;i<cdosTemp.length;i++)
						{
							alCDOList.add(cdosTemp[i]);
							if(strsPageItem!=null && alCDOList.size()>=nToIndex)
							{//输出记录个数已经达到，停止输出
								break;
							}
						}
					}
				}
				else if(strOrderFieldId!=null)
				{//需要排序
					//初始化每个ResultSet的Group字段当前值
					long[] lsSortedFieldValue=new long[nCount];
					for(int i=0;i<nCount;i++)
					{
						lsSortedFieldValue[i]=-1;
					}
					CDO[] cdosRecord=new CDO[nCount];
					
					//每个ResultSet都取出一个值
					for(int i=0;i<nCount;i++)
					{
						dataAccess=(DataAccess)alInputSet.get(i).get("dataAccess");
						ResultSet rs=(ResultSet)alOutputSet.get(i).get("rs");
						cdosRecord[i]=dataAccess.dataEngine.readRecord(rs,metaData.strsFieldName,metaData.nsFieldType,metaData.nsPrecision,metaData.nsScale);
						if(cdosRecord[i]==null)
						{
							lsSortedFieldValue[i]=-1;
						}
						else
						{
							lsSortedFieldValue[i]=cdosRecord[i].getLongValue(strOrderFieldId);
						}
					}
					
					//循环输出OrderBy字段值最小/最大的记录
					while(true)
					{
						int[] nsIndex=this.getMinMaxValueIndexList(lsSortedFieldValue,bAscending);
						if(nsIndex.length==0)
						{
							break;
						}
						
						//输出记录
						for(int i=0;i<nsIndex.length;i++)
						{
							alCDOList.add(cdosRecord[nsIndex[i]]);
							if(strsPageItem!=null && alCDOList.size()>=nToIndex)
							{//输出记录个数已经达到，停止输出
								break;
							}
						}
						if(strsPageItem!=null && alCDOList.size()>=nToIndex)
						{//输出记录个数已经达到，停止输出
							break;
						}
						
						//已经输出的ResultSet，重新获取
						for(int i=0;i<nsIndex.length;i++)
						{
							dataAccess=(DataAccess)alInputSet.get(nsIndex[i]).get("dataAccess");
							ResultSet rs=(ResultSet)alOutputSet.get(nsIndex[i]).get("rs");
							cdosRecord[nsIndex[i]]=dataAccess.dataEngine.readRecord(rs,metaData.strsFieldName,metaData.nsFieldType,metaData.nsPrecision,metaData.nsScale);
							if(cdosRecord[nsIndex[i]]==null)
							{
								lsSortedFieldValue[nsIndex[i]]=-1;
							}
							else
							{
								lsSortedFieldValue[nsIndex[i]]=cdosRecord[nsIndex[i]].getLongValue(strOrderFieldId);
							}
						}
					}
				}
				else
				{//普通查询输出
					boolean bStopFlag=false;
					for(int i=0;i<nCount;i++)
					{
						dataAccess=(DataAccess)alInputSet.get(i).get("dataAccess");
						ResultSet rs=(ResultSet)alOutputSet.get(i).get("rs");
						while(bStopFlag==false)
						{
							CDO cdoRecord=dataAccess.dataEngine.readRecord(rs,metaData.strsFieldName,metaData.nsFieldType,metaData.nsPrecision,metaData.nsScale);
							if(cdoRecord==null)
							{
								break;
							}
							alCDOList.add(cdoRecord);
							if(strsPageItem!=null && alCDOList.size()>=nToIndex)
							{//输出记录个数已经达到，停止输出
								bStopFlag=true;
								break;
							}
						}
					}
				}

				//输出读取的记录数据
				if(nToIndex>alCDOList.size()){
					nToIndex=alCDOList.size();
				}
				if(parsedSQL.strPage!=null)
				{
					alCDOList=alCDOList.subList(nFromIndex,nToIndex);
				}
				CDO[] cdosOutput=new CDO[alCDOList.size()];
				alCDOList.toArray(cdosOutput);
				cdoafOutput.setValue(cdosOutput);
				
				nRecordCount=cdosOutput.length;
			}
		}
		catch(Exception e)
		{
			throw new SQLException(e.getMessage());
		}
		finally
		{
			//关闭ResultSet
			for(int i=0;i<nCount;i++)
			{
				HashMap hmInput=alInputSet.get(i);
				dataAccess=(DataAccess)hmInput.get("dataAccess");
				ResultSet rs=(ResultSet)hmInput.get("rs");
				PreparedStatement ps=(PreparedStatement)hmInput.get("ps");
				dataAccess.dataEngine.closeResultSet(rs);
				dataAccess.dataEngine.closeStatement(strSQL,ps);
				try
				{
					dataAccess.conn.close();
				}
				catch(Exception e)
				{
				}
			}
		}
		
		if(bOK==false)
		{
			return 0;
		}

		return nRecordCount;
	}

	/**
	 * 查询第一条记录的第一个字段
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	protected ObjectExt executeQueryFieldExt(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans,String strSQL,CDO cdoRequest) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}

		DataAccess dataAccess=null;
		String strDefaultDataGroupId = trans.getDataGroupId();
		if(parsedSQL.strTableName==null)
		{//没有BigTable
			//尝试重用已有连接
			dataAccess=hmDataAccessUsed.get(null);
			if(dataAccess==null)
			{//当前没有连接
				//获取连接
				dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,null,null,false,cdoRequest,hmDataAccessUsed,trans);
				if(dataAccess==null)
				{
					throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
				}
			}
			
			//执行数据库操作
			return dataAccess.dataEngine.executeQueryFieldExt(dataAccess.conn,strSQL,cdoRequest);
		}

		//有BigTable，尝试定位目标表
		BigTable bigTable=this.selectBigTable(dataService.getBigTableArray(trans.getBigTableGroupId()),parsedSQL.strTableName);
		if(bigTable==null)
		{
			throw new SQLException("Dest-table not found: "+strSQL);
		}
		
		if(parsedSQL.strCountFieldName!=null && parsedSQL.strBatchIds!=null)
		{//有Count统计，批量执行SQL语句
			String[] strsIds=Utility.splitString(parsedSQL.strBatchIds,',');
			long[] lIds = getIds(parsedSQL, strsIds);

			int nCount=0;
			ObjectExt objExt=new ObjectExt();
			for(int i=0;i<lIds.length;i++)
			{
				//获取连接
				dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,lIds[i],hmDataAccessUsed,trans);

				if(dataAccess==null)
				{//Id找不到对应的连接
					continue;
				}
				
				try
				{
					this.selectTable(dataAccess,bigTable,lIds[i]);
	
					//修改SQL语句中的表名
					strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);
					cdoRequest.setLongValue(strsIds[0],lIds[i]);
	
					//分页处理
					strSQL=preparePaging(strSQL, parsedSQL);
					
					//执行数据库操作
					CDO cdoResponse = new CDO();
					dataAccess.dataEngine.executeQueryRecord(dataAccess.conn, strSQL, cdoRequest, cdoResponse);
//					nCount+=cdoResponse.getValueAt(0).getInteger();
					if(cdoResponse.iterator().hasNext()){
						nCount+=cdoResponse.iterator().next().getValue().getInteger();
					}					
				}
				finally
				{
					
				}
			}
			objExt.setValue(new Integer(nCount));
			objExt.setType(ObjectExt.INTEGER_TYPE);
			return objExt;
		}
		
		//获取连接
		//尝试重用已有连接
		dataAccess=hmDataAccessUsed.get(null);
		if(dataAccess==null)
		{//没有可重用连接
			dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,parsedSQL.strIdFieldName,false,cdoRequest,hmDataAccessUsed,trans);
		}
		if(dataAccess!=null)
		{//拿到了连接
			this.selectTable(dataAccess,bigTable,parsedSQL.strIdFieldName,false,cdoRequest);

			//修改SQL语句中的表名
			strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);

			//执行数据库操作
			return dataAccess.dataEngine.executeQueryFieldExt(dataAccess.conn,strSQL,cdoRequest);
		}

		//没有拿到连接
		if(parsedSQL.strIdFieldName!=null)
		{//不应该拿不到
			throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
		}

		//未提供Id，遍历数据库表读取记录
		ObjectExt objExt=null;
		if(parsedSQL.strCountFieldName==null)
		{//一般性记录
			int nGroupCount=bigTable.getGroupCount();
			for(int i=0;i<nGroupCount;i++)
			{
				//获取连接
				dataAccess=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+i);
				if(dataAccess==null)
				{
					throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
				}
				
				try
				{
					int nTableCount=bigTable.getGroupTableCount();
					for(int j=0;j<nTableCount;j++)
					{
						//修改SQL语句中的表名
						strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+j);
	
						//执行数据库操作
						objExt=dataAccess.dataEngine.executeQueryFieldExt(dataAccess.conn,strSQL,cdoRequest);
						if(objExt!=null)
						{
							break;
						}
					}
				}
				finally
				{
					try
					{
						dataAccess.conn.close();
					}
					catch(Exception e)
					{
					}
				}
				if(objExt!=null)
				{
					break;
				}
			}
			
			return objExt;
		}

		//有Count统计，需要累加
		int nCount=0;
		int nGroupCount=bigTable.getGroupCount();
		for(int i=0;i<nGroupCount;i++)
		{
			//获取连接
			dataAccess=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+i);
			if(dataAccess==null)
			{
				throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
			}
			
			try
			{
				int nTableCount=bigTable.getGroupTableCount();
				for(int j=0;j<nTableCount;j++)
				{
					//修改SQL语句中的表名
					strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+j);
	
					//执行数据库操作
					objExt=dataAccess.dataEngine.executeQueryFieldExt(dataAccess.conn,strSQL,cdoRequest);
					if(objExt!=null)
					{
						switch(objExt.getType())
						{
							case ObjectExt.BYTE_TYPE:
								nCount+=(Byte)objExt.getValue();
								break;
							case ObjectExt.SHORT_TYPE:
								nCount+=(Short)objExt.getValue();
								break;
							case ObjectExt.INTEGER_TYPE:
								nCount+=(Integer)objExt.getValue();
								break;
							case ObjectExt.LONG_TYPE:
								nCount+=(Long)objExt.getValue();
								break;
							default:
								throw new SQLException("Invaid bigtable SQL: "+strSQL);
						}
					}
				}
			}
			finally
			{
				try
				{
					dataAccess.conn.close();
				}
				catch(Exception e)
				{
				}
			}
		}
		objExt.setValue(new Integer(nCount));
		objExt.setType(ObjectExt.INTEGER_TYPE);
		
		return objExt;
	}

	/**
	 * 更新数据库记录或删除数据库记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	protected int executeUpdate(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans,String strSQL,CDO cdoRequest) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}

		DataAccess dataAccess=null;
		String strDefaultDataGroupId = trans.getDataGroupId();
		if(parsedSQL.strTableName==null)
		{//没有BigTable
			//尝试重用已有连接
			dataAccess=hmDataAccessUsed.get(null);
			if(dataAccess==null)
			{//当前没有连接
				//获取连接
				dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,null,null,false,cdoRequest,hmDataAccessUsed,trans);
				if(dataAccess==null)
				{
					throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
				}
			}
			
			//执行数据库操作
			return dataAccess.dataEngine.executeUpdate(dataAccess.conn,strSQL,cdoRequest);
		}

		//有BigTable，尝试定位目标表
		BigTable bigTable=this.selectBigTable(dataService.getBigTableArray(trans.getBigTableGroupId()),parsedSQL.strTableName);
		if(bigTable==null)
		{
			throw new SQLException("Dest-table not found: "+strSQL);
		}
		//获取连接
		//尝试重用已有连接
		dataAccess=hmDataAccessUsed.get(null);
		if(dataAccess==null)
		{//没有可重用连接
			dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,parsedSQL.strIdFieldName,false,cdoRequest,hmDataAccessUsed,trans);
		}
		if(dataAccess!=null)
		{//拿到了连接
			this.selectTable(dataAccess,bigTable,parsedSQL.strIdFieldName,false,cdoRequest);

			//修改SQL语句中的表名
			strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);

			//执行数据库操作
			return dataAccess.dataEngine.executeUpdate(dataAccess.conn,strSQL,cdoRequest);
		}

		//没有拿到连接
		if(parsedSQL.strIdFieldName!=null)
		{//不应该拿不到
			throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
		}
		
		if(parsedSQL.strBatchIds!=null)
		{//批量执行SQL语句
			String[] strsIds=Utility.splitString(parsedSQL.strBatchIds,',');
			long[] lIds = getIds(parsedSQL, strsIds);

			int nCount=0;
			for(int i=0;i<lIds.length;i++)
			{
				//获取连接
				dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,lIds[i],hmDataAccessUsed,trans);

				if(dataAccess==null)
				{//Id找不到对应的连接
					continue;
				}
				
				try
				{
					this.selectTable(dataAccess,bigTable,lIds[i]);
	
					//修改SQL语句中的表名
					strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);
					cdoRequest.setLongValue(strsIds[0],lIds[i]);
	
					//执行数据库操作
					nCount+=dataAccess.dataEngine.executeUpdate(dataAccess.conn,strSQL,cdoRequest);
				}
				finally
				{
					
				}
			}
			return nCount;
		}

		//未提供Id，遍历数据库表更新记录
		int nCount=0;
		int nGroupCount=bigTable.getGroupCount();
		for(int i=0;i<nGroupCount;i++)
		{
			//获取连接
			dataAccess=this.getDataAccess(hmDataGroup,bigTable.getGroupId()+i);
			if(dataAccess==null)
			{
				throw new SQLException("Invalid datagroup id: "+strDefaultDataGroupId);
			}
			
			try
			{
				int nTableCount=bigTable.getGroupTableCount();
				for(int j=0;j<nTableCount;j++)
				{
					//修改SQL语句中的表名
					strSQL=parsedSQL.strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+j);
	
					//执行数据库操作
					nCount+=dataAccess.dataEngine.executeUpdate(dataAccess.conn,strSQL,cdoRequest);
				}
			}
			finally
			{
				try
				{
					dataAccess.conn.close();
				}
				catch(Exception e)
				{
				}
			}
		}
		
		return nCount;
	}
	
	/**
	 * 执行commit语句
	 * 
	 * @throws Exception
	 */
	protected void executeCommit(HashMap<String,DataAccess> hmDataAccessUsed) throws SQLException,IOException
	{
		//提交事务
		Iterator iterator=hmDataAccessUsed.values().iterator();
		while(iterator.hasNext())
		{
			Connection conn=(Connection)iterator.next();
			if(conn.getAutoCommit()==false)
			{//尚未提交
				conn.commit();
			}
		}
	}
	
	/**
	 * 执行rollback语句
	 * 
	 * @throws Exception
	 */
	protected void executeRollback(HashMap<String,DataAccess> hmDataAccessUsed) throws SQLException,IOException
	{
		//提交事务
		Iterator<DataAccess> iterator=hmDataAccessUsed.values().iterator();
		while(iterator.hasNext())
		{
			DataAccess da = iterator.next();
			Connection conn= null;
			if(da!=null)
			{
				conn=da.conn;
			}
			if(conn!=null && conn.getAutoCommit()==false)
			{//尚未提交
				conn.rollback();
			}
		}
	}

	/*
	 * 处理一个Block
	 * 
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 */
	private int handleBlock(HashMap<String,CycleList<IDataEngine>> hmDataGroup,DataService dataService,HashMap<String,DataAccess> hmDataAccessUsed,SQLTrans trans,BlockType block,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
		String strDefaultDataGroupId = trans.getDataGroupId();
		int nItemCount=block.getBlockTypeItemCount();
		for(int i=0;i<nItemCount;i++)
		{
			BlockTypeItem blockItem=block.getBlockTypeItem(i);
			if(blockItem.getInsert()!=null)
			{// Insert
				// 获得将要执行的SQL
				Insert insert=(Insert)blockItem.getInsert();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(insert,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();
				// 执行SQL
				this.executeInsert(hmDataGroup,dataService,hmDataAccessUsed,trans,strSQL,cdoRequest);
			}
			else if(blockItem.getSelectRecord()!=null)
			{
				// 获得将要执行的SQL
				SelectRecord selectRecord=(SelectRecord)blockItem.getSelectRecord();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(selectRecord,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				CDO cdoRecord=new CDO();
				String strRecordCountId=selectRecord.getRecordCountId();
				if(strRecordCountId.length()>0)
				{//表示要 获取SQL查询条件中的总数量					
					cdoRequest.setBooleanValue("$$nRecordCountId$$", true);
				}
				int nRecordCount=this.executeQueryRecord(hmDataGroup,dataService,hmDataAccessUsed,trans,strSQL,cdoRequest,cdoRecord);
//				String strRecordCountId=selectRecord.getRecordCountId();
				if(strRecordCountId.length()>0)
				{// 输出受影响的记录数
					strRecordCountId=strRecordCountId.substring(1,strRecordCountId.length()-1);
					cdoRequest.setIntegerValue(strRecordCountId,nRecordCount);
				}

				String strOutputId=selectRecord.getOutputId();
				strOutputId=strOutputId.substring(1,strOutputId.length()-1);
				if(nRecordCount>0)
				{
					cdoRequest.setCDOValue(strOutputId,cdoRecord);
				}
			}
			else if(blockItem.getUpdate()!=null)
			{// Update
				// 获得将要执行的SQL
				Update update=(Update)blockItem.getUpdate();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(update,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				int nRecordCount=this.executeUpdate(hmDataGroup,dataService,hmDataAccessUsed,trans,strSQL,cdoRequest);
				String strRecordCountId=update.getRecordCountId();
				if(strRecordCountId.length()>0)
				{// 输出受影响的记录数
					strRecordCountId=strRecordCountId.substring(1,strRecordCountId.length()-1);
					cdoRequest.setIntegerValue(strRecordCountId,nRecordCount);
				}
			}
			else if(blockItem.getDelete()!=null)
			{// Delete
				// 获得将要执行的SQL
				Delete delete=(Delete)blockItem.getDelete();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(delete,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				int nRecordCount=this.executeUpdate(hmDataGroup,dataService,hmDataAccessUsed,trans,strSQL,cdoRequest);
				String strRecordCountId=delete.getRecordCountId();
				if(strRecordCountId.length()>0)
				{// 输出受影响的记录数
					strRecordCountId=strRecordCountId.substring(1,strRecordCountId.length()-1);
					cdoRequest.setIntegerValue(strRecordCountId,nRecordCount);
				}
			}
			else if(blockItem.getSelectField()!=null)
			{
				// 获得将要执行的SQL
				SelectField selectField=(SelectField)blockItem.getSelectField();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(selectField,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				ObjectExt objFieldValue=this.executeQueryFieldExt(hmDataGroup,dataService,hmDataAccessUsed,trans,strSQL,cdoRequest);
				if(objFieldValue==null)
				{
					continue;
				}
				int nType=objFieldValue.getType();
				Object objValue=objFieldValue.getValue();

				String strOutputId=selectField.getOutputId();
				strOutputId=strOutputId.substring(1,strOutputId.length()-1);
				switch(nType)
				{
					case ObjectExt.BYTE_TYPE:
					{
						cdoRequest.setByteValue(strOutputId,((Byte)objValue).byteValue());
						break;
					}
					case ObjectExt.SHORT_TYPE:
					{
						cdoRequest.setShortValue(strOutputId,((Short)objValue).shortValue());
						break;
					}
					case ObjectExt.INTEGER_TYPE:
					{
						cdoRequest.setIntegerValue(strOutputId,((Integer)objValue).intValue());
						break;
					}
					case ObjectExt.LONG_TYPE:
					{
						cdoRequest.setLongValue(strOutputId,((Long)objValue).longValue());
						break;
					}
					case ObjectExt.FLOAT_TYPE:
					{
						cdoRequest.setFloatValue(strOutputId,((Float)objValue).floatValue());
						break;
					}
					case ObjectExt.DOUBLE_TYPE:
					{
						cdoRequest.setDoubleValue(strOutputId,((Double)objValue).doubleValue());
						break;
					}
					case ObjectExt.STRING_TYPE:
					{
						cdoRequest.setStringValue(strOutputId,((String)objValue));
						break;
					}
					case ObjectExt.DATE_TYPE:
					{
						cdoRequest.setDateValue(strOutputId,((String)objValue));
						break;
					}
					case ObjectExt.TIME_TYPE:
					{
						cdoRequest.setTimeValue(strOutputId,((String)objValue));
						break;
					}
					case ObjectExt.DATETIME_TYPE:
					{
						cdoRequest.setDateTimeValue(strOutputId,((String)objValue));
						break;
					}
					case ObjectExt.BYTE_ARRAY_TYPE:
					{
						cdoRequest.setByteArrayValue(strOutputId,((byte[])objValue));
						break;
					}
					default:
					{
						throw new SQLException("Unsupported type "+nType);
					}
				}
			}
			else if(blockItem.getSelectRecordSet()!=null)
			{// SelectRecordSet
				// 获得将要执行的SQL
				SelectRecordSet selectRecordSet=(SelectRecordSet)blockItem.getSelectRecordSet();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(selectRecordSet,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				CDOArrayField cdoArrayField=new CDOArrayField("");
				String strRecordCountId=selectRecordSet.getRecordCountId();
				if(strRecordCountId.length()>0)
				{//表示要 获取SQL查询条件中的总数量					
					cdoRequest.setBooleanValue("$$nRecordCountId$$", true);
				}				
				int nRecordCount=this.executeQueryRecordSet(hmDataGroup,dataService,hmDataAccessUsed,trans,strSQL,cdoRequest,cdoArrayField);
				
				if(strRecordCountId.length()>0)
				{// 输出受影响的记录数
					strRecordCountId=strRecordCountId.substring(1,strRecordCountId.length()-1);
					cdoRequest.setIntegerValue(strRecordCountId,nRecordCount);
				}
				
				String strOutputId=selectRecordSet.getOutputId();
				strOutputId=strOutputId.substring(1,strOutputId.length()-1);
				String strKeyFieldName=selectRecordSet.getKeyFieldName();
				if(strKeyFieldName.length()==0)
				{// RecordSet输出到数组
					cdoRequest.setCDOArrayValue(strOutputId,cdoArrayField.getValue());
				}
				else
				{// RecordSet输出到HashMap
					CDO[] cdosRecordSet=cdoArrayField.getValue();
					CDO cdoRecordSet=new CDO();
					for(int j=0;j<cdosRecordSet.length;j++)
					{
						cdoRecordSet.setCDOValue(cdosRecordSet[j].getObjectValue(strKeyFieldName).toString(),
										cdosRecordSet[j]);
					}
					cdoRequest.setCDOValue(strOutputId,cdoRecordSet);
				}
			}
			else if(blockItem.getSetVar()!=null)
			{
				SetVar sv=blockItem.getSetVar();
				setVar(sv,cdoRequest);
			}
			else if(blockItem.getSelectConnection()!=null)
			{//SelectTable选择BigTable的单个表
				SelectConnection st=blockItem.getSelectConnection();
				
				String strIdFieldId=st.getIdFieldId();
				if(strIdFieldId.length()>0)
				{	
					strIdFieldId=strIdFieldId.substring(1,strIdFieldId.length()-1);
				}
				this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,st.getBigTableName(),strIdFieldId,true,cdoRequest,hmDataAccessUsed,trans);
			}
			else if(blockItem.getIf()!=null)
			{
				int nResult=this.handleIf(hmDataGroup,dataService,hmDataAccessUsed,trans,(If)blockItem.getIf(),cdoRequest,cdoResponse,ret);
				if(nResult==0)
				{// 自然执行完毕
					continue;
				}
				else
				{// 碰到Break或Return
					return nResult;
				}
			}
			else if(blockItem.getFor()!=null)
			{
				int nResult=this.handleFor(hmDataGroup,dataService,hmDataAccessUsed,trans,(For)blockItem.getFor(),cdoRequest,cdoResponse,ret);
				if(nResult==0)
				{// 自然执行完毕
					continue;
				}
				else
				{// 碰到Break或Return
					return nResult;
				}
			}
			else if(blockItem.getReturn()!=null)
			{
				com.cdoframework.cdolib.database.dataservice.Return returnObject=(com.cdoframework.cdolib.database.dataservice.Return)blockItem.getReturn();
				this.handleReturn(returnObject,cdoRequest,cdoResponse,ret);

				return 2;
			}
			else if(blockItem.getBreak()!=null)
			{// Break退出
				return 1;
			}
			else if(blockItem.getCommit()!=null)
			{
				this.executeCommit(hmDataAccessUsed);
			}
			else if(blockItem.getRollback()!=null)
			{
				this.executeRollback(hmDataAccessUsed);
			}
		}

		return 0;
	}
	
	private void callOnException(String strText,Exception e)
	{
		try
		{
			onException(strText,e);
		}
		catch(Exception ex)
		{
		}
	}

	protected Return executeTrans(HashMap<String,CycleList<IDataEngine>> hmDataGroup,SQLTrans trans,CDO cdoRequest,CDO cdoResponse)
	{
    	//检查是否能处理该请求
    	String strTransName=cdoRequest.getStringValue("strTransName");
    	if(trans==null)
    	{//不能处理该请求
    		return null;
    	}
    	//可以处理该请求
    	DataService dataService = trans.getDataService();
    	//处理事务
    	Return ret=new Return();
		HashMap<String,DataAccess> hmDataAccessUsed=new HashMap<String,DataAccess>();//key格式：bigtable.<name>或者group.<id>或者当前DataAccess使用的null
		hmDataAccessUsed.put(null,null);//设置当前使用的dataaccess
		
		try
		{
			// 生成Block对象
			BlockType block=new BlockType();
			int nTransItemCount=trans.getSQLTransChoice(0).getSQLTransChoiceItemCount();
			for(int i=0;i<nTransItemCount;i++)
			{
				SQLTransChoiceItem transItem=trans.getSQLTransChoice(0).getSQLTransChoiceItem(i);
				BlockTypeItem blockItem=null;
				if(transItem.getInsert()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setInsert(transItem.getInsert());
				}
				else if(transItem.getUpdate()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setUpdate(transItem.getUpdate());
				}
				else if(transItem.getDelete()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setDelete(transItem.getDelete());
				}
				else if(transItem.getSelectRecordSet()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setSelectRecordSet(transItem.getSelectRecordSet());
				}
				else if(transItem.getSelectRecord()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setSelectRecord(transItem.getSelectRecord());
				}
				else if(transItem.getSelectField()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setSelectField(transItem.getSelectField());
				}
				else if(transItem.getSelectConnection()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setSelectConnection(transItem.getSelectConnection());
				}
				else if(transItem.getIf()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setIf(transItem.getIf());
				}
				else if(transItem.getFor()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setFor(transItem.getFor());
				}
				else if(transItem.getDelete()!=null)
				{
					blockItem=new BlockTypeItem();
					blockItem.setDelete(transItem.getDelete());
				}

				if(blockItem!=null)
				{
					block.addBlockTypeItem(blockItem);
				}
			}

			// 处理事务
//			Hashtable<String,Integer> htTableIdSelected=new Hashtable<String,Integer>();//TODO 这个东西是干什么的?
			int nResult=handleBlock(hmDataGroup,dataService,hmDataAccessUsed,trans,block,cdoRequest,cdoResponse,ret);
			if(nResult!=2)
			{// Break或自然执行完毕退出
				com.cdoframework.cdolib.database.dataservice.Return returnObject=trans.getReturn();
				this.handleReturn(returnObject,cdoRequest,cdoResponse,ret);
			}
			
			//提交事务
			Iterator iterator=hmDataAccessUsed.values().iterator();
			while(iterator.hasNext())
			{
				DataAccess dataAccess=(DataAccess)iterator.next();
				if(dataAccess==null || dataAccess.conn.isClosed())
				{
					continue;
				}
				if(dataAccess.conn.getAutoCommit()==false)
				{//尚未提交
					dataAccess.conn.setAutoCommit(true);
				}
			}
		}
		catch(SQLException e)
		{
			callOnException("executeTrans Exception: "+strTransName,e);

			ret=null;

			OnException onException=trans.getOnException();
			int nErrorCount=onException.getOnErrorCount();
			for(int i=0;i<nErrorCount;i++)
			{
				OnError onError=onException.getOnError(i);
				if(onError.getCode()==e.getErrorCode())
				{
					ret=Return.valueOf(onError.getReturn().getCode(),onError.getReturn().getText(),onError.getReturn().getInfo(),e);
					break;
				}
			}
			if(ret==null)
			{// 没有定义OnError
				String strText = onException.getReturn().getText();
				if("OK".equalsIgnoreCase(strText))
				{
					strText="系统服务器故障";
				}
				ret=Return.valueOf(onException.getReturn().getCode(),strText,onException.getReturn().getInfo(),e);
			}

			return ret;
		}
		catch(IOException e)
		{
			callOnException("executeTrans Exception: "+strTransName,e);

			OnException onException=trans.getOnException();
			ret=Return.valueOf(onException.getReturn().getCode(),onException.getReturn().getText(),onException.getReturn().getInfo());

			return ret;
		}
		catch(Exception e)
		{
			callOnException("executeTrans Exception: "+strTransName,e);

			OnException onException=trans.getOnException();
			ret=Return.valueOf(onException.getReturn().getCode(),onException.getReturn().getText(),onException.getReturn().getInfo());

			return ret;
		}
		finally
		{
			//关闭连接
			Iterator<DataAccess> iterator=hmDataAccessUsed.values().iterator();
			while(iterator.hasNext())
			{
				DataAccess dataAccess=iterator.next();
				try
				{
					if (dataAccess!=null && !dataAccess.conn.isClosed())
					{
						dataAccess.conn.close();
					}
				}
				catch(Exception e)
				{
					logger.error("Close database connection error!", e); 
				}
			}
			hmDataAccessUsed.clear();
		}
		
		return ret;
	}


	public void onException(String strText,Exception e)
	{
		logger.error(strText, e); 
	}

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	
	public Return handleTrans(HashMap<String,CycleList<IDataEngine>> hmDataGroup,SQLTrans sqlTrans, CDO cdoRequest,CDO cdoResponse)
	{
		return this.executeTrans(hmDataGroup,sqlTrans,cdoRequest,cdoResponse);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	protected HashMap handleTask(HashMap hmInput) throws SQLException
	{
		//修改SQL语句中的表名
		String strSQL=(String)hmInput.get("strSQL");
		DataAccess dataAccess=(DataAccess)hmInput.get("dataAccess");
		CDO cdoRequest=(CDO)hmInput.get("cdoRequest");

		//获得数据库连接
		IDataEngine dataEngine=dataAccess.dataEngine;
		Connection conn=dataAccess.conn;
		
		//执行数据库操作
		PreparedStatement ps=null;
		ResultSet rs=null;
		HashMap hmOutput=null;
		try
		{
			ps=dataEngine.prepareStatement(conn,strSQL,cdoRequest);

			// 执行查询
			rs=ps.executeQuery();
			
			hmOutput=new HashMap();
			hmOutput.put("rs",rs);
			hmOutput.put("ps",ps);
			
			return hmOutput;
		}
		catch(SQLException e)
		{
			dataEngine.closeResultSet(rs);
			dataEngine.closeStatement(strSQL,ps);
			this.callOnException("query recordset error: "+strSQL,e);
			throw e;
//			throw new SQLException("ExecuteSQL failed: "+strSQL, e);
//			return null;
		}
	}
	
	private MetaData getMetaData(ResultSet rs) throws SQLException
	{
		ResultSetMetaData meta=rs.getMetaData();
		MetaData metaData=new MetaData();
		metaData.strsFieldName=new String[meta.getColumnCount()];
		metaData.nsFieldType=new int[metaData.strsFieldName.length];
		metaData.nsPrecision=new int[metaData.strsFieldName.length];
		metaData.nsScale=new int[metaData.strsFieldName.length];
		for(int i=0;i<metaData.strsFieldName.length;i++)
		{
			metaData.strsFieldName[i]=meta.getColumnName(i+1);
			metaData.nsFieldType[i]=meta.getColumnType(i+1);
			metaData.nsPrecision[i]=meta.getPrecision(i+1);
			metaData.nsScale[i]=meta.getScale(i+1);
		}
		return metaData;
	}

	private long[] getIds(ParsedSQL parsedSQL, String[] strsIds)
			throws SQLException
	{
		long[] lIds = new long[strsIds.length - 1];
		try
		{
			for (int i = 1; i < strsIds.length; i++)
			{
				lIds[i - 1] = Long.parseLong(strsIds[i]);
			}
		}
		catch (Exception e)
		{
			throw new SQLException("Invalid data id: " + parsedSQL.strBatchIds);
		}
		return lIds;
	}
	
	private void buildRequest4QueryRecordSet(
			HashMap<String, CycleList<IDataEngine>> hmDataGroup,
			DataService dataService,
			HashMap<String, DataAccess> hmDataAccessUsed, SQLTrans trans,
			String strSQL, CDO cdoRequest, ParsedSQL parsedSQL,
			String strDefaultDataGroupId, BigTable bigTable,
			ArrayList<HashMap> alInputSet, ArrayList<HashMap> alOutputSet)
			throws SQLException
	{
		DataAccess dataAccess;
		String[] strsIds=Utility.splitString(parsedSQL.strBatchIds,',');
		long[] lIds = getIds(parsedSQL, strsIds);

		for(int i=0;i<lIds.length;i++)
		{
			//获取连接
			dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,lIds[i],hmDataAccessUsed,trans);

			if(dataAccess==null)
			{//Id找不到对应的连接
				continue;
			}
			
			this.selectTable(dataAccess,bigTable,lIds[i]);

			//修改SQL语句中的表名
			String strUsedSQL=strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);
			cdoRequest.setLongValue(strsIds[0],lIds[i]);

			HashMap hmInput=new HashMap();
			hmInput.put("strSQL",strUsedSQL);
			hmInput.put("dataAccess",dataAccess);
			hmInput.put("cdoRequest",cdoRequest);

			alInputSet.add(hmInput);

			//执行请求
			HashMap hmOutput=this.handleTask(hmInput);
			alOutputSet.add(hmOutput);
		}
	}

	private void buildRequest4QueryRecordSet(
			HashMap<String, CycleList<IDataEngine>> hmDataGroup, String strSQL,
			CDO cdoRequest, ParsedSQL parsedSQL, BigTable bigTable,
			ArrayList<HashMap> alInputSet, ArrayList<HashMap> alOutputSet,
			int nGroupCount, int nTableCount) throws SQLException
	{
		DataAccess dataAccess;
		for(int i=0;i<nGroupCount;i++)
		{
			//获取连接
			String strTempDataGroupId = bigTable.getGroupId()+i;
			dataAccess=this.getDataAccess(hmDataGroup,strTempDataGroupId);
			if(dataAccess==null)
			{
				throw new SQLException("Invalid datagroup id: "+strTempDataGroupId);
			}
			
			for(int j=0;j<nTableCount;j++)
			{
				//修改SQL语句中的表名
				String strUsedSQL=strSQL.replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+j);

				HashMap hmInput=new HashMap();
				hmInput.put("strSQL",strUsedSQL);
				hmInput.put("dataAccess",dataAccess);
				hmInput.put("cdoRequest",cdoRequest);

				alInputSet.add(hmInput);

				//执行请求
				HashMap hmOutput=this.handleTask(hmInput);
				if(hmOutput==null)
				{
					throw new SQLException("ExecuteSQL failed: "+strUsedSQL);
				}
				alOutputSet.add(hmOutput);
			}
		}
	}
	
	private String preparePaging(String strSQL, ParsedSQL parsedSQL)
	{
		String[] strsPageItem=null;
		if(parsedSQL.strPage!=null)
		{
			StringBuilder strbLimit=new StringBuilder("limit ");
			strsPageItem=parsedSQL.strPage.split(" ");
			if(Utility.isNumberText(strsPageItem[0])==true)
			{
				strbLimit.append(strsPageItem[0]).append(',');
			}
			else
			{
				strbLimit.append('{').append(strsPageItem[0]).append("},");
			}
			if(Utility.isNumberText(strsPageItem[1])==true)
			{
				strbLimit.append(strsPageItem[1]);
			}
			else
			{
				strbLimit.append('{').append(strsPageItem[1]).append('}');
			}
			strSQL=strSQL.replaceAll("\\{L:"+parsedSQL.strPage+"\\}",strbLimit.toString());
		}
		return strSQL;
	}
	
	private int prepareScanTypeAndExecuteAllDBAllTableScan(
			HashMap<String, CycleList<IDataEngine>> hmDataGroup,
			HashMap<String, DataAccess> hmDataAccessUsed, String strSQL,
			CDO cdoRequest, CDOArrayField cdoafOutput, ParsedSQL parsedSQL,
			DataAccess dataAccess, BigTable bigTable,
			String strDefaultDataGroupId, DataService dataService, SQLTrans trans) throws SQLException
	{
		boolean bIsSequenceScan=false;
		boolean bIsUpdate=false;
		int nBigTableScanType=cdoRequest.getIntegerValue("nBigTableScanType");
		
		if (nBigTableScanType==BigTableScanType.BIGTABLE_SEQUENCE_SCAN_NOUPDATE) 
		{
			bIsSequenceScan=true;
			bIsUpdate=false;
		}
		else if (nBigTableScanType==BigTableScanType.BIGTABLE_BALANCE_SCAN_UPDATE) 
		{
			bIsSequenceScan=false;
			bIsUpdate=true;
		}
		else if (nBigTableScanType==BigTableScanType.BIGTABLE_SEQUENCE_SCAN_UPDATE) 
		{
			bIsSequenceScan=true;
			bIsUpdate=true;
		}
		
		if (!(bIsSequenceScan==false && bIsUpdate==false)) 
		{
			if (parsedSQL.strBatchIds == null)
			{
				return executeAllDBAllTableScan(hmDataGroup, hmDataAccessUsed, strSQL, cdoRequest, cdoafOutput, dataAccess, bigTable, parsedSQL, bIsSequenceScan, bIsUpdate);
			}
			else
			{
				return executeMutilTableScan(hmDataGroup, hmDataAccessUsed, strSQL, cdoRequest, cdoafOutput, dataAccess, bigTable, parsedSQL, bIsSequenceScan, bIsUpdate, strDefaultDataGroupId, dataService, trans);
			}
		}
		
		return -1;
	}
	
	private int executeAllDBAllTableScan(HashMap<String,CycleList<IDataEngine>> hmDataGroup,HashMap<String,DataAccess> hmDataAccessUsed,String strSQL,CDO cdoRequest,CDOArrayField cdoafOutput, DataAccess dataAccess, BigTable bigTable, ParsedSQL parsedSQL, boolean bIsSequenceScan, boolean bIsUpdate) throws SQLException//,IOException
	{
		String[] strsPageItem=null;
		int nFromIndex=-1;
		int nToIndex=-1;
		//当前表上次扫描到的页索引，默认为第一页
		int nCurTableCurPageIndex=cdoRequest.exists("nCurTableCurPageIndex")?cdoRequest.getIntegerValue("nCurTableCurPageIndex"):1;
		//当前表上次扫描到的记录索引，默认为0
		int nCurTableStartRowIndex=cdoRequest.exists("nCurTableStartRowIndex")?cdoRequest.getIntegerValue("nCurTableStartRowIndex"):0;
		boolean bNewTableFlag=cdoRequest.exists("bNewTableFlag")?cdoRequest.getBooleanValue("bNewTableFlag"):false;
		
		if(parsedSQL.strPage!=null)
		{
			StringBuilder strbLimit=new StringBuilder("limit ");
			strsPageItem=parsedSQL.strPage.split(" ");
			if(Utility.isNumberText(strsPageItem[1])==true)
			{
				nToIndex=Integer.parseInt(strsPageItem[1]);
			}
			else
			{
				nToIndex=cdoRequest.getIntegerValue(strsPageItem[1]);
			}
			if (bIsUpdate)
			{
				nFromIndex=0;
			}
			else
			{
				if(Utility.isNumberText(strsPageItem[0])==true)
				{
					nFromIndex=nCurTableCurPageIndex*nToIndex+nCurTableStartRowIndex;
				}
				else
				{
					if (bNewTableFlag)
					{
						nFromIndex=nCurTableStartRowIndex;
					}
					else
					{
						nFromIndex=(nCurTableCurPageIndex-1)*nToIndex+nCurTableStartRowIndex;
					}
				}
			}
			strbLimit.append(nFromIndex + ", ");
			strbLimit.append(nToIndex);
			strSQL=parsedSQL.strSQL.replaceAll("\\{L:"+parsedSQL.strPage+"\\}",strbLimit.toString());
		}
		else
		{
			strSQL=parsedSQL.strSQL.toString();
		}
		
		ResultSet rs=null;
		HashMap hmInput=null;
		HashMap hmOutput=null;
		int nRecordCount=0;
		try
		{
			int nGroupCount=bigTable.getGroupCount();
			int nTableCount=bigTable.getGroupTableCount();
			//当前库索引，默认为0
			int nCurDBIndex=cdoRequest.exists("nCurDBIndex")?cdoRequest.getIntegerValue("nCurDBIndex"):0;
			String strTempDataGroupId = bigTable.getGroupId()+nCurDBIndex;
			dataAccess=this.getDataAccess(hmDataGroup,strTempDataGroupId);
			if(dataAccess==null)
			{
				throw new SQLException("Invalid datagroup id: "+strTempDataGroupId);
			}
			//当前表索引，默认为0
			int nCurTableIndex=cdoRequest.exists("nCurTableIndex")?cdoRequest.getIntegerValue("nCurTableIndex"):0;
			//当前表已经处理过的记录数量，默认为0
			int nCurTableProcessRecordCount=cdoRequest.exists("nCurTableProcessRecordCount")?cdoRequest.getIntegerValue("nCurTableProcessRecordCount"):0;
			String strUsedSQL=strSQL.replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+nCurTableIndex);
			
			hmInput=new HashMap();
			hmInput.put("strSQL",strUsedSQL);
			hmInput.put("dataAccess",dataAccess);
			hmInput.put("cdoRequest",cdoRequest);
			
			//执行请求
			hmOutput=this.handleTask(hmInput);
			if(hmOutput==null)
			{
				throw new SQLException("ExecuteSQL failed: "+strUsedSQL);
			}
			
			rs=((ResultSet)hmOutput.get("rs"));
			MetaData metaData = getMetaData(rs);
			
			List<CDO> alCDOList=new ArrayList<CDO>();
			boolean bStopFlag=false;
			bNewTableFlag=false;
			int nTempCurTableStartRowIndex=0;
			//读取到一条非空记录扫描过多少张表
			int nAScanTableCount=1;
			//是否还有记录
			boolean bHasRecord=true;
			int nScanCountFlag=0;
			rs=((ResultSet)hmOutput.get("rs"));
			while(bStopFlag==false)
			{
				CDO cdoRecord=dataAccess.dataEngine.readRecord(rs,metaData.strsFieldName,metaData.nsFieldType,metaData.nsPrecision,metaData.nsScale);
				if((cdoRecord==null || (nCurTableProcessRecordCount == nToIndex && bIsUpdate && !bIsSequenceScan)) && bHasRecord)
				{
					if (bIsSequenceScan || (!bIsSequenceScan && bIsUpdate))
					{
						nAScanTableCount++;
						nCurTableProcessRecordCount=0;
					}
					bNewTableFlag=true;
					//重置当前表页索引，处理过记录条数
					nTempCurTableStartRowIndex=0;
					nCurTableCurPageIndex=1;
					//关闭ResultSet和Statement
					PreparedStatement ps=(PreparedStatement) hmOutput.get("ps");
					dataAccess.dataEngine.closeResultSet(rs);
					dataAccess.dataEngine.closeStatement(strSQL,ps);
					if (nCurTableIndex == nTableCount-1)
					{
						try
						{
							dataAccess.conn.close();
						}
						catch(Exception e)
						{
						}
					}
					if (nCurTableIndex < nTableCount-1) 
					{//当前库中还有表没有被扫描过
						nCurTableIndex++;
						strSQL=strSQL.replaceFirst("limit "+nFromIndex,"limit 0");
					}
					else if (nCurDBIndex < nGroupCount-1)
					{//还有库没被扫描过
						nCurDBIndex++;
						nCurTableIndex=0;
						strSQL=strSQL.replaceFirst("limit "+nFromIndex,"limit 0");
					}
					else if (nCurDBIndex == nGroupCount-1 && nCurTableIndex == nTableCount-1 && !bIsSequenceScan)
					{//完成一次全库全表扫描并且是均衡扫描
						nCurDBIndex=0;
						nCurTableIndex=0;
						strSQL=strSQL.replaceFirst("limit "+nFromIndex,"limit 0");
						if (nAScanTableCount > nGroupCount*nTableCount)
						{
							bHasRecord=false;
							nCurDBIndex=nGroupCount-1;
							nCurTableIndex=nTableCount-1;
							nScanCountFlag=nToIndex-1;
						}
					}
					else
					{//完成一次全库全表扫描
						if (bIsSequenceScan) 
						{//按顺序扫描
							nScanCountFlag=1;
						}
						bHasRecord=false;
					}
					
					if (dataAccess.conn.isClosed())
					{
						strTempDataGroupId = bigTable.getGroupId()+nCurDBIndex;
						dataAccess=this.getDataAccess(hmDataGroup,strTempDataGroupId);
						if(dataAccess==null)
						{
							throw new SQLException("Invalid datagroup id: "+strTempDataGroupId);
						}
					}
					strUsedSQL=strSQL.replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+nCurTableIndex);
					
					hmInput=new HashMap();
					hmInput.put("strSQL",strUsedSQL);
					hmInput.put("dataAccess",dataAccess);
					hmInput.put("cdoRequest",cdoRequest);
					
					//执行请求
					hmOutput=this.handleTask(hmInput);
					if(hmOutput==null)
					{
						throw new SQLException("ExecuteSQL failed: "+strUsedSQL);
					}
					rs=((ResultSet)hmOutput.get("rs"));
					cdoRequest.setIntegerValue("nCurDBIndex", nCurDBIndex);
					cdoRequest.setIntegerValue("nCurTableIndex", nCurTableIndex);
					continue;
				}
				if (nAScanTableCount > nGroupCount*nTableCount)
				{
					nScanCountFlag++;
					if (nScanCountFlag==1)
					{
						alCDOList.clear();
						nCurTableProcessRecordCount=0;
					}
				}
				if (bIsSequenceScan)
				{
					nAScanTableCount=1;
				}
				nCurTableProcessRecordCount++;
				if (bNewTableFlag)
				{
					nTempCurTableStartRowIndex++;
				}
				if (nScanCountFlag!=nToIndex+1 && bHasRecord)
				{
					alCDOList.add(cdoRecord);
				}
				if(strsPageItem!=null && alCDOList.size()>=nToIndex || !bHasRecord || nScanCountFlag==nToIndex)
				{//输出记录个数已经达到，停止输出
					bStopFlag=true;
					if (bNewTableFlag)
					{
						cdoRequest.setIntegerValue("nCurTableStartRowIndex", nTempCurTableStartRowIndex);
						if (bIsSequenceScan)
						{
							if (nCurTableProcessRecordCount==nToIndex)
							{
								nCurTableProcessRecordCount=0;
							}
						}
						else if(bIsUpdate)
						{
							cdoRequest.setIntegerValue("nCurTableProcessRecordCount", nCurTableProcessRecordCount);
						}
					}
					else
					{
						nCurTableCurPageIndex++;
					}
					cdoRequest.setIntegerValue("nCurTableProcessRecordCount", nCurTableProcessRecordCount);
					cdoRequest.setIntegerValue("nCurTableCurPageIndex", nCurTableCurPageIndex);
					cdoRequest.setBooleanValue("bNewTableFlag", bNewTableFlag);
					cdoRequest.setBooleanValue("bHasRecord", bHasRecord);
					break;
				}
			}
			
			CDO[] cdosOutput=new CDO[alCDOList.size()];
			alCDOList.toArray(cdosOutput);
			cdoafOutput.setValue(cdosOutput);
			nRecordCount=cdosOutput.length;
		}
		catch(Exception e)
		{
			throw new SQLException(e.getMessage());
		}
		finally
		{
		//关闭ResultSet
			PreparedStatement ps=(PreparedStatement) hmOutput.get("ps");
			dataAccess.dataEngine.closeResultSet(rs);
			dataAccess.dataEngine.closeStatement(strSQL,ps);
			try
			{
				dataAccess.conn.close();
			}
			catch(Exception e)
			{
			}
		}
		return nRecordCount;
	}

	private int executeMutilTableScan(HashMap<String,CycleList<IDataEngine>> hmDataGroup,HashMap<String,DataAccess> hmDataAccessUsed,String strSQL,CDO cdoRequest,
			CDOArrayField cdoafOutput, DataAccess dataAccess, BigTable bigTable, ParsedSQL parsedSQL, boolean bIsSequenceScan, boolean bIsUpdate,
			String strDefaultDataGroupId, DataService dataService, SQLTrans trans) throws SQLException//,IOException
	{
		String[] strsPageItem=null;
		int nFromIndex=-1;
		int nToIndex=-1;
		//当前表上次扫描到的页索引，默认为第一页
		int nCurTableCurPageIndex=cdoRequest.exists("nCurTableCurPageIndex")?cdoRequest.getIntegerValue("nCurTableCurPageIndex"):1;
		//当前表上次扫描到的记录索引，默认为0
		int nCurTableStartRowIndex=cdoRequest.exists("nCurTableStartRowIndex")?cdoRequest.getIntegerValue("nCurTableStartRowIndex"):0;
		boolean bNewTableFlag=cdoRequest.exists("bNewTableFlag")?cdoRequest.getBooleanValue("bNewTableFlag"):false;
		
		if(parsedSQL.strPage!=null)
		{
			StringBuilder strbLimit=new StringBuilder("limit ");
			strsPageItem=parsedSQL.strPage.split(" ");
			if(Utility.isNumberText(strsPageItem[1])==true)
			{
				nToIndex=Integer.parseInt(strsPageItem[1]);
			}
			else
			{
				nToIndex=cdoRequest.getIntegerValue(strsPageItem[1]);
			}
			if (bIsUpdate)
			{//业务改变扫描返回的记录使其不再符合查询条件
				nFromIndex=0;
			}
			else
			{
				if(Utility.isNumberText(strsPageItem[0])==true)
				{
					nFromIndex=nCurTableCurPageIndex*nToIndex+nCurTableStartRowIndex;
				}
				else
				{
					if (bNewTableFlag)
					{
						nFromIndex=nCurTableStartRowIndex;
					}
					else
					{
						nFromIndex=(nCurTableCurPageIndex-1)*nToIndex+nCurTableStartRowIndex;
					}
				}
			}
			strbLimit.append(nFromIndex + ", ");
			strbLimit.append(nToIndex);
			strSQL=parsedSQL.strSQL.replaceAll("\\{L:"+parsedSQL.strPage+"\\}",strbLimit.toString());
		}
		else
		{
			strSQL=parsedSQL.strSQL.toString();
		}
		
		String[] strsIds=Utility.splitString(parsedSQL.strBatchIds,',');
		long[] lIds = getIds(parsedSQL, strsIds);

		ResultSet rs=null;
		HashMap hmInput=null;
		HashMap hmOutput=null;
		int nRecordCount=0;
		
		try
		{
			//当前表索引，默认为0
			int nCurIdIndex=cdoRequest.exists("nCurIdIndex")?cdoRequest.getIntegerValue("nCurIdIndex"):0;
			//当前表已经处理过的记录数量，默认为0
			int nCurTableProcessRecordCount=cdoRequest.exists("nCurTableProcessRecordCount")?cdoRequest.getIntegerValue("nCurTableProcessRecordCount"):0;
			dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,lIds[nCurIdIndex],hmDataAccessUsed,trans);

			if(dataAccess==null)
			{//Id找不到对应的连接
				return -1;
			}
			
			this.selectTable(dataAccess,bigTable,lIds[nCurIdIndex]);

			//修改SQL语句中的表名
			String strUsedSQL=strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);
			cdoRequest.setLongValue(strsIds[0],lIds[nCurIdIndex]);
			
			hmInput=new HashMap();
			hmInput.put("strSQL",strUsedSQL);
			hmInput.put("dataAccess",dataAccess);
			hmInput.put("cdoRequest",cdoRequest);
			
			//执行请求
			hmOutput=this.handleTask(hmInput);
			if(hmOutput==null)
			{
				throw new SQLException("ExecuteSQL failed: "+strUsedSQL);
			}
			
			rs=((ResultSet)hmOutput.get("rs"));
			MetaData metaData = getMetaData(rs);
			
			List<CDO> alCDOList=new ArrayList<CDO>();
			boolean bStopFlag=false;
			bNewTableFlag=false;
			int nTempCurTableStartRowIndex=0;
			//读取到一条非空记录扫描过多少张表
			int nAScanTableCount=1;
			//是否还有记录
			boolean bHasRecord=true;
			int nScanCountFlag=0;
			rs=((ResultSet)hmOutput.get("rs"));
			while(bStopFlag==false)
			{
				CDO cdoRecord=dataAccess.dataEngine.readRecord(rs,metaData.strsFieldName,metaData.nsFieldType,metaData.nsPrecision,metaData.nsScale);
				if((cdoRecord==null || (nCurTableProcessRecordCount == nToIndex && bIsUpdate && !bIsSequenceScan)) && bHasRecord)
				{
					if (bIsSequenceScan || (!bIsSequenceScan && bIsUpdate))
					{
						nAScanTableCount++;
						nCurTableProcessRecordCount=0;
					}
					bNewTableFlag=true;
					//重置当前表页索引，处理过记录条数
					nTempCurTableStartRowIndex=0;
					nCurTableCurPageIndex=1;
					//关闭ResultSet和Statement
					PreparedStatement ps=(PreparedStatement) hmOutput.get("ps");
					dataAccess.dataEngine.closeResultSet(rs);
					dataAccess.dataEngine.closeStatement(strSQL,ps);
					try
					{
						dataAccess.conn.close();
					}
					catch(Exception e)
					{
					}
					if (nCurIdIndex < lIds.length-1) 
					{//当前库中还有表没有被扫描过
						nCurIdIndex++;
						strSQL=strSQL.replaceFirst("limit "+nFromIndex,"limit 0");
					}
					else if (nCurIdIndex == lIds.length-1 && !bIsSequenceScan)
					{//完成一次全库全表扫描并且是均衡扫描
						nCurIdIndex=0;
						strSQL=strSQL.replaceFirst("limit "+nFromIndex,"limit 0");
						if (nAScanTableCount > (lIds.length))
						{
							bHasRecord=false;
							nCurIdIndex=lIds.length-1;
							nScanCountFlag=nToIndex-1;
						}
					}
					else
					{//完成一次全库全表扫描
						if (bIsSequenceScan) 
						{//按顺序扫描
							nScanCountFlag=1;
						}
						bHasRecord=false;
					}
					
					if (bHasRecord)
					{
						hmDataAccessUsed.clear();
						dataAccess=this.selectConnection(hmDataGroup,dataService,strDefaultDataGroupId,parsedSQL.strTableName,lIds[nCurIdIndex],hmDataAccessUsed,trans);
						
						if(dataAccess==null)
						{//Id找不到对应的连接
							return -1;
						}
						
						this.selectTable(dataAccess,bigTable,lIds[nCurIdIndex]);
						
						//修改SQL语句中的表名
						strUsedSQL=strSQL.toString().replaceFirst("\\{T:"+parsedSQL.strTableName+"\\}",parsedSQL.strTableName+dataAccess.nTableIndex);
						cdoRequest.setLongValue(strsIds[0],lIds[nCurIdIndex]);
						
						hmInput=new HashMap();
						hmInput.put("strSQL",strUsedSQL);
						hmInput.put("dataAccess",dataAccess);
						hmInput.put("cdoRequest",cdoRequest);
						
						//执行请求
						hmOutput=this.handleTask(hmInput);
						if(hmOutput==null)
						{
							throw new SQLException("ExecuteSQL failed: "+strUsedSQL);
						}
						rs=((ResultSet)hmOutput.get("rs"));
						cdoRequest.setIntegerValue("nCurIdIndex", nCurIdIndex);
						continue;
					}
				}
				if (nAScanTableCount > (lIds.length))
				{
					nScanCountFlag++;
					if (nScanCountFlag==1)
					{
						alCDOList.clear();
						nCurTableProcessRecordCount=0;
					}
				}
				if (bIsSequenceScan)
				{//有序扫描，重置读取到一条非空记录扫描过多少张表
					nAScanTableCount=1;
				}
				nCurTableProcessRecordCount++;
				if (bNewTableFlag)
				{
					nTempCurTableStartRowIndex++;
				}
				if (nScanCountFlag!=nToIndex+1 && bHasRecord)
				{
					alCDOList.add(cdoRecord);
				}
				if(strsPageItem!=null && alCDOList.size()>=nToIndex || !bHasRecord || nScanCountFlag==nToIndex)
				{//输出记录个数已经达到，停止输出
					bStopFlag=true;
					if (bNewTableFlag)
					{
						cdoRequest.setIntegerValue("nCurTableStartRowIndex", nTempCurTableStartRowIndex);
						if (bIsSequenceScan)
						{
							if (nCurTableProcessRecordCount==nToIndex)
							{
								nCurTableProcessRecordCount=0;
							}
						}
						else if(bIsUpdate)
						{
							cdoRequest.setIntegerValue("nCurTableProcessRecordCount", nCurTableProcessRecordCount);
						}
					}
					else
					{
						nCurTableCurPageIndex++;
					}
					cdoRequest.setIntegerValue("nCurTableProcessRecordCount", nCurTableProcessRecordCount);
					cdoRequest.setIntegerValue("nCurTableCurPageIndex", nCurTableCurPageIndex);
					cdoRequest.setBooleanValue("bNewTableFlag", bNewTableFlag);
					cdoRequest.setBooleanValue("bHasRecord", bHasRecord);
					break;
				}
			}
			
			CDO[] cdosOutput=new CDO[alCDOList.size()];
			alCDOList.toArray(cdosOutput);
			cdoafOutput.setValue(cdosOutput);
			nRecordCount=cdosOutput.length;
		}
		catch(Exception e)
		{
			throw new SQLException(e.getMessage());
		}
		finally
		{
		//关闭ResultSet
			PreparedStatement ps=(PreparedStatement) hmOutput.get("ps");
			dataAccess.dataEngine.closeResultSet(rs);
			dataAccess.dataEngine.closeStatement(strSQL,ps);
			try
			{
				dataAccess.conn.close();
			}
			catch(Exception e)
			{
			}
		}
		return nRecordCount;
	}
	
	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public BigTableEngine()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		hmParsedSQL=new HashMap<String,ParsedSQL>(100);
	}
	
}
