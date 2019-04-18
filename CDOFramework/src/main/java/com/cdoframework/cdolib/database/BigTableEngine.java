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

import com.cdo.util.sql.SQLUtil;
import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.SortedSet;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOArrayField;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.IntegerField;
import com.cdoframework.cdolib.database.xsd.BlockType;
import com.cdoframework.cdolib.database.xsd.BlockTypeItem;
import com.cdoframework.cdolib.database.xsd.DataService;
import com.cdoframework.cdolib.database.xsd.Delete;
import com.cdoframework.cdolib.database.xsd.Else;
import com.cdoframework.cdolib.database.xsd.For;
import com.cdoframework.cdolib.database.xsd.If;
import com.cdoframework.cdolib.database.xsd.Insert;
import com.cdoframework.cdolib.database.xsd.OnError;
import com.cdoframework.cdolib.database.xsd.OnException;
import com.cdoframework.cdolib.database.xsd.SQLBlockType;
import com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem;
import com.cdoframework.cdolib.database.xsd.SQLElse;
import com.cdoframework.cdolib.database.xsd.SQLFor;
import com.cdoframework.cdolib.database.xsd.SQLIf;
import com.cdoframework.cdolib.database.xsd.SQLThen;
import com.cdoframework.cdolib.database.xsd.SQLTrans;
import com.cdoframework.cdolib.database.xsd.SQLTransChoiceItem;
import com.cdoframework.cdolib.database.xsd.SelectField;
import com.cdoframework.cdolib.database.xsd.SelectRecord;
import com.cdoframework.cdolib.database.xsd.SelectRecordSet;
import com.cdoframework.cdolib.database.xsd.SetVar;
import com.cdoframework.cdolib.database.xsd.Then;
import com.cdoframework.cdolib.database.xsd.Update;
import com.cdoframework.cdolib.database.xsd.types.IfTypeType;
import com.cdoframework.cdolib.database.xsd.types.SQLIfTypeType;

/**
 * @author KenelLiu
 */
public class BigTableEngine
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	Logger logger  = Logger.getLogger(BigTableEngine.class);
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

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
	

	
	class MetaData
	{
		public String[] strsFieldName;;
		public int[] nsFieldType;
		public int[] nsPrecision;
		public int[] nsScale;
	}
	
	private void handleReturn(com.cdoframework.cdolib.database.xsd.Return returnObject,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException
	{
		int nReturnItemCount=returnObject.getReturnItemCount();
		for(int j=0;j<nReturnItemCount;j++)
		{
			String strFieldId=returnObject.getReturnItem(j).getFieldId();
			String strValueId=returnObject.getReturnItem(j).getValueId();
			strFieldId=strFieldId.substring(1,strFieldId.length()-1);
			strValueId=strValueId.substring(1,strValueId.length()-1);
			Field object=null;
			try
			{
				if(cdoRequest.exists(strValueId))
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
			int nType=object.getType().getDataType();
			Object objValue=object.getObjectValue();
			switch(nType)
			{
				case DataType.BYTE_TYPE:
				{
					cdoResponse.setByteValue(strFieldId,((Byte)objValue).byteValue());
					break;
				}
				case DataType.SHORT_TYPE:
				{
					cdoResponse.setShortValue(strFieldId,((Short)objValue).shortValue());
					break;
				}
				case DataType.INTEGER_TYPE:
				{
					cdoResponse.setIntegerValue(strFieldId,((Integer)objValue).intValue());
					break;
				}
				case DataType.LONG_TYPE:
				{
					cdoResponse.setLongValue(strFieldId,((Long)objValue).longValue());
					break;
				}
				case DataType.FLOAT_TYPE:
				{
					cdoResponse.setFloatValue(strFieldId,((Float)objValue).floatValue());
					break;
				}
				case DataType.DOUBLE_TYPE:
				{
					cdoResponse.setDoubleValue(strFieldId,((Double)objValue).doubleValue());
					break;
				}
				case DataType.STRING_TYPE:
				{
					cdoResponse.setStringValue(strFieldId,((String)objValue));
					break;
				}
				case DataType.DATE_TYPE:
				{
					cdoResponse.setDateValue(strFieldId,((String)objValue));
					break;
				}
				case DataType.TIME_TYPE:
				{
					cdoResponse.setTimeValue(strFieldId,((String)objValue));
					break;
				}
				case DataType.DATETIME_TYPE:
				{
					cdoResponse.setDateTimeValue(strFieldId,((String)objValue));
					break;
				}
				case DataType.BYTE_ARRAY_TYPE:
				{
					cdoResponse.setByteArrayValue(strFieldId,((byte[])objValue));
					break;
				}
				case DataType.CDO_TYPE:
				{
					cdoResponse.setCDOValue(strFieldId,(CDO)objValue);
					break;
				}
				case DataType.CDO_ARRAY_TYPE:
				{
					cdoResponse.setCDOListValue(strFieldId,(List<CDO>)objValue);
//					cdoResponse.setCDOArrayValue(strFieldId,(CDO[])objValue);
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
	protected boolean checkCondition(String strValue1,String strOperator,String strValue2,IfTypeType ifType,String strType,CDO cdoRequest)
	{
		return DataEngineHelp.checkCondition(strValue1, strOperator, strValue2, ifType, strType, cdoRequest);
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
	protected boolean checkCondition(String strValue1,String strOperator,String strValue2,SQLIfTypeType sqlIfType,String strType,CDO cdoRequest)
	{
		return DataEngineHelp.checkCondition(strValue1, strOperator, strValue2, sqlIfType, strType, cdoRequest);
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
						.getType(),sqlIf.getType().toString(),cdoRequest);
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
		int nFromIndex=0;
		int nStep=1;
		int nCount=DataEngineHelp.getArrayLength(sqlFor.getArrKey(), cdoRequest);
		if(sqlFor.getFromIndex()!=null)
			nFromIndex=DataEngineHelp.getIntegerValue(sqlFor.getFromIndex(),cdoRequest);
		if(sqlFor.getStep()!=null)
			nStep=DataEngineHelp.getIntegerValue(sqlFor.getStep(),cdoRequest);
		if(sqlFor.getCount()!=null)
			nCount=DataEngineHelp.getIntegerValue(sqlFor.getCount(),cdoRequest);	
					
		String strIndexId=sqlFor.getIndexId();
		strIndexId=strIndexId.substring(1,strIndexId.length()-1);

		// 执行循环
		for(int i=nFromIndex;i<nFromIndex+nCount;i=nStep+i)
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
	private int handleIf(IDataEngine dataEngine ,Connection connection,SQLTrans trans,If ifItem,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
		// 检查执行条件
		boolean bCondition=checkCondition(ifItem.getValue1(),ifItem.getOperator().toString(),ifItem.getValue2(),ifItem.getType(),ifItem.getType().toString(),cdoRequest);
		if(bCondition==true)
		{// Handle Then
			Then thenItem=ifItem.getThen();
			return handleBlock(dataEngine,connection,trans,thenItem,cdoRequest,cdoResponse,ret);
		}
		else
		{// handle Else
			Else elseItem=ifItem.getElse();
			if(elseItem==null)
			{// 没有else模块，当作自然执行完毕处理???
				return 0;
			}
			return handleBlock(dataEngine,connection,trans,elseItem,cdoRequest,cdoResponse,ret);
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
	private int handleFor(IDataEngine dataEngine ,Connection connection,SQLTrans trans,For forItem,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
		// 获取循环数据
		int nFromIndex=0;
		int nStep=1;
		int nCount=DataEngineHelp.getArrayLength(forItem.getArrKey(), cdoRequest);
		if(forItem.getFromIndex()!=null)
			nFromIndex=DataEngineHelp.getIntegerValue(forItem.getFromIndex(),cdoRequest);
		if(forItem.getStep()!=null)
			nStep=DataEngineHelp.getIntegerValue(forItem.getStep(),cdoRequest);
		if(forItem.getCount()!=null)
			nCount=DataEngineHelp.getIntegerValue(forItem.getCount(),cdoRequest);	
		
		String strIndexId=forItem.getIndexId();
		strIndexId=strIndexId.substring(1,strIndexId.length()-1);
		
		// 执行循环
		for(int i=nFromIndex;i<nFromIndex+nCount;i=i+nStep)
		{
			// 设置IndexId
			cdoRequest.setIntegerValue(strIndexId,i);

			// 执行Block
			int nResult=handleBlock(dataEngine,connection,trans,forItem,cdoRequest,cdoResponse,ret);
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
	 * 通过一个传入的数据库连接查询并输出第一条记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	protected int executeQueryRecord(IDataEngine dataEngine,Connection connection,SQLTrans trans,String strSQL,CDO cdoRequest,CDO cdoResponse) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}		
		//执行数据库操作
		return dataEngine.executeQueryRecord(connection,strSQL,cdoRequest,cdoResponse);
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
	protected int executeQueryRecordSet(IDataEngine dataEngine,Connection connection,SQLTrans trans,String strSQL,CDO cdoRequest,CDOArrayField cdoafOutput) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}
		//执行数据库操作
		return dataEngine.executeQueryRecordSet(connection,strSQL,cdoRequest,cdoafOutput);		
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
	protected Field executeQueryFieldExt(IDataEngine dataEngine,Connection connection,SQLTrans trans,String strSQL,CDO cdoRequest) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}
		//执行数据库操作
		return dataEngine.executeQueryFieldExt(connection,strSQL,cdoRequest);		
	}

	/**
	 * 插入,更新数据库记录或删除数据库记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	protected int executeUpdate(IDataEngine dataEngine,Connection connection,SQLTrans trans,String strSQL,CDO cdoRequest) throws SQLException,IOException
	{
		//分析原先的SQL语句
		ParsedSQL parsedSQL=this.parseSourceSQL(strSQL);
		if(parsedSQL==null)
		{//SQL语句分析错误
			throw new SQLException("Parse SQL failed: "+strSQL);
		}
		//执行数据库操作
		return dataEngine.executeUpdate(connection,strSQL,cdoRequest);		
	}
	
	/**
	 * 执行commit语句
	 * 
	 * @throws Exception
	 */
	protected void executeCommit(Connection conn) throws SQLException,IOException
	{
		//提交事务
		if(conn.getAutoCommit()==false){//尚未提交
			conn.commit();
		}
	}
	
	/**
	 * 执行rollback语句
	 * 
	 * @throws Exception
	 */
	protected void executeRollback(Connection conn) throws SQLException,IOException
	{
		//提交事务
		if(conn!=null && conn.getAutoCommit()==false){//尚未提交
			conn.rollback();
		}
	}

	/*
	 * 处理一个Block
	 * 
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 */
	private int handleBlock(IDataEngine dataEngine,Connection connection,SQLTrans trans,BlockType block,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
//		String strDefaultDataGroupId = trans.getDataGroupId();
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
				this.executeUpdate(dataEngine,connection,trans,strSQL,cdoRequest);
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
				int nRecordCount=this.executeQueryRecord(dataEngine,connection,trans,strSQL,cdoRequest,cdoRecord);
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
				int nRecordCount=this.executeUpdate(dataEngine,connection,trans,strSQL,cdoRequest);
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
				int nRecordCount=this.executeUpdate(dataEngine,connection,trans,strSQL,cdoRequest);
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
				Field objFieldValue=this.executeQueryFieldExt(dataEngine,connection,trans,strSQL,cdoRequest);
				if(objFieldValue==null)
				{
					continue;
				}
				int nType=objFieldValue.getType().getDataType();
				Object objValue=objFieldValue.getObjectValue();

				String strOutputId=selectField.getOutputId();
				strOutputId=strOutputId.substring(1,strOutputId.length()-1);
				switch(nType)
				{
					case DataType.BYTE_TYPE:
					{
						cdoRequest.setByteValue(strOutputId,((Byte)objValue).byteValue());
						break;
					}
					case DataType.SHORT_TYPE:
					{
						cdoRequest.setShortValue(strOutputId,((Short)objValue).shortValue());
						break;
					}
					case DataType.INTEGER_TYPE:
					{
						cdoRequest.setIntegerValue(strOutputId,((Integer)objValue).intValue());
						break;
					}
					case DataType.LONG_TYPE:
					{
						cdoRequest.setLongValue(strOutputId,((Long)objValue).longValue());
						break;
					}
					case DataType.FLOAT_TYPE:
					{
						cdoRequest.setFloatValue(strOutputId,((Float)objValue).floatValue());
						break;
					}
					case DataType.DOUBLE_TYPE:
					{
						cdoRequest.setDoubleValue(strOutputId,((Double)objValue).doubleValue());
						break;
					}
					case DataType.STRING_TYPE:
					{
						cdoRequest.setStringValue(strOutputId,((String)objValue));
						break;
					}
					case DataType.DATE_TYPE:
					{
						cdoRequest.setDateValue(strOutputId,((String)objValue));
						break;
					}
					case DataType.TIME_TYPE:
					{
						cdoRequest.setTimeValue(strOutputId,((String)objValue));
						break;
					}
					case DataType.DATETIME_TYPE:
					{
						cdoRequest.setDateTimeValue(strOutputId,((String)objValue));
						break;
					}
					case DataType.BYTE_ARRAY_TYPE:
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
				int nRecordCount=this.executeQueryRecordSet(dataEngine,connection,trans,strSQL,cdoRequest,cdoArrayField);
				
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
					cdoRequest.setCDOListValue(strOutputId, cdoArrayField.getValue());
				}
				else
				{// RecordSet输出到HashMap					
					List<CDO> cdosRecordSet=cdoArrayField.getValue();
					CDO cdoRecordSet=new CDO();
					for(int j=0;j<cdosRecordSet.size();j++)
					{
						cdoRecordSet.setCDOValue(cdosRecordSet.get(j).getObjectValue(strKeyFieldName).toString(),
										cdosRecordSet.get(j));
					}
					cdoRequest.setCDOValue(strOutputId,cdoRecordSet);
				}
			}
			else if(blockItem.getSetVar()!=null)
			{
				SetVar sv=blockItem.getSetVar();				
				DataEngineHelp.setVar(sv, cdoRequest);
			}
			else if(blockItem.getIf()!=null)
			{
				int nResult=this.handleIf(dataEngine,connection,trans,(If)blockItem.getIf(),cdoRequest,cdoResponse,ret);
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
				int nResult=this.handleFor(dataEngine,connection,trans,(For)blockItem.getFor(),cdoRequest,cdoResponse,ret);
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
				com.cdoframework.cdolib.database.xsd.Return returnObject=(com.cdoframework.cdolib.database.xsd.Return)blockItem.getReturn();
				this.handleReturn(returnObject,cdoRequest,cdoResponse,ret);

				return 2;
			}
			else if(blockItem.getBreak()!=null)
			{// Break退出
				return 1;
			}
			else if(blockItem.getCommit()!=null)
			{
				this.executeCommit(connection);
			}
			else if(blockItem.getRollback()!=null)
			{
				this.executeRollback(connection);
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

	protected Return executeTrans(HashMap<String,IDataEngine> hmDataGroup,SQLTrans trans,CDO cdoRequest,CDO cdoResponse)
	{
    	//检查是否能处理该请求
    	String strTransName=cdoRequest.getStringValue("strTransName");
    	if(trans==null)
    	{//不能处理该请求
    		return null;
    	}
    	//可以处理该请求
    	//DataService dataService = trans.getDataService();
    	//处理事务
    	Return ret=new Return();

    	String strDataGroupId=trans.getDataGroupId();
    	IDataEngine dataEngine=hmDataGroup.get(strDataGroupId);
		Connection connection=null;
		try
		{
			if(dataEngine==null){//DataGroupId错误
				throw new SQLException("Invalid datagroup id: "+strDataGroupId);
			}
			//创建Connection	
			connection=dataEngine.getConnection();
			if(connection==null)
			{
				throw new SQLException("datagroup id: "+strDataGroupId+",Invalid Connection,Connection is null");
			}
			if(!trans.getTransFlag().value().equals(0))
			{
				connection.setAutoCommit(false);
			}
			
			
			// 生成Block对象
			BlockType block=new BlockType();
			int nTransItemCount=trans.getSQLTransChoice().getSQLTransChoiceItemCount();
			for(int i=0;i<nTransItemCount;i++)
			{
				SQLTransChoiceItem transItem=trans.getSQLTransChoice().getSQLTransChoiceItem(i);
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
			int nResult=handleBlock(dataEngine,connection,trans,block,cdoRequest,cdoResponse,ret);
			if(nResult!=2)
			{// Break或自然执行完毕退出
				com.cdoframework.cdolib.database.xsd.Return returnObject=trans.getReturn();
				this.handleReturn(returnObject,cdoRequest,cdoResponse,ret);
			}
			
			if(!trans.getTransFlag().value().equals(0)){
				connection.commit();
			}
		}
		catch(SQLException e)
		{
			try{this.executeRollback(connection);}catch(Exception ex){};
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
			try{this.executeRollback(connection);}catch(Exception ex){};
			callOnException("executeTrans Exception: "+strTransName,e);

			OnException onException=trans.getOnException();
			ret=Return.valueOf(onException.getReturn().getCode(),onException.getReturn().getText(),onException.getReturn().getInfo());

			return ret;
		}
		catch(Exception e)
		{
			try{this.executeRollback(connection);}catch(Exception ex){};
			callOnException("executeTrans Exception: "+strTransName,e);
			OnException onException=trans.getOnException();
			ret=Return.valueOf(onException.getReturn().getCode(),onException.getReturn().getText(),onException.getReturn().getInfo());

			return ret;
		}
		finally
		{
			//关闭连接
			SQLUtil.closeConnection(connection);
		}		
		return ret;
	}


	public void onException(String strText,Exception e)
	{
		logger.error(strText, e); 
	}

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	
	public Return handleTrans(HashMap<String,IDataEngine> hmDataGroup,SQLTrans sqlTrans, CDO cdoRequest,CDO cdoResponse)
	{
		return this.executeTrans(hmDataGroup,sqlTrans,cdoRequest,cdoResponse);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------


	
	
	
	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public BigTableEngine()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		hmParsedSQL=new HashMap<String,ParsedSQL>(100);
	}
	
}
