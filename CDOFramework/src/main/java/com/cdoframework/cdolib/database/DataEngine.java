package com.cdoframework.cdolib.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Date;
import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Time;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOArrayField;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.database.xsd.BlockType;
import com.cdoframework.cdolib.database.xsd.BlockTypeItem;
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
import com.cdoframework.cdolib.database.xsd.types.SQLTransTransFlagType;

/**
 * @author Frank
 */
public class DataEngine implements IDataEngine
{
	private static final Logger log = Logger.getLogger(DataEngine.class);
	class AnalyzedSQL
	{
		public String strSQL;
		public ArrayList<String> alParaName;
	}

	// 静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	public final int RETURN_SYSTEMERROR=-1;

	// 定义数据库类型
	public static final String SQLSERVER="SQLServer";
	public static final String ORACLE="Oracle";
	public static final String DB2="DB2";
	public static final String MYSQL="MySQL";

	// 内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	protected BasicDataSource ds;   
	protected String strSystemCharset;
	private HashMap<String,AnalyzedSQL> hmAnalyzedSQL;

	// 属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	protected String strDriver;

	public void setDriver(String strDriver)
	{
		this.strDriver=strDriver;
	}

	public String getDriver()
	{
		return this.strDriver;
	}

	protected String strURI;

	public void setURI(String strURI)
	{
		this.strURI=strURI;
	}

	public String getURI()
	{
		return this.strURI;
	}

	protected String strCharset;

	public String getCharset()
	{
		return strCharset;
	}

	public void setCharset(String strCharset)
	{
		this.strCharset=strCharset;
	}

	protected Properties properties;

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties=properties;
	}

	protected String strUserName;

	public String getUserName()
	{
		return strUserName;
	}

	public void setUserName(String strUserName)
	{
		this.strUserName=strUserName;
	}

	protected String strPassword;

	public String getPassword()
	{
		return strPassword;
	}

	public void setPassword(String strPassword)
	{
		this.strPassword=strPassword;
	}

	protected int nMinConnectionCount;

	public void setMinConnectionCount(int nMinConnectionCount)
	{
		this.nMinConnectionCount=nMinConnectionCount;
	}

	public int getMinConnectionCount()
	{
		return this.nMinConnectionCount;
	}

	protected int nMaxConnectionCount;

	public void setMaxConnectionCount(int nMaxConnectionCount)
	{
		this.nMaxConnectionCount=nMaxConnectionCount;
	}

	public int getMaxConnectionCount()
	{
		return this.nMaxConnectionCount;
	}

	protected long lTimeout;

	public void setTimeout(long lTimeout)
	{
		this.lTimeout=lTimeout;
	}

	public long getTimeout()
	{
		return this.lTimeout;
	}

	public boolean isOpened()
	{
		if(ds==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	protected int nLoadLevel;
	public void setLoadLevel(int nLoadlevel)
	{
		this.nLoadLevel=nLoadlevel;
	}
	public int getLoadLevel()
	{
		return this.nLoadLevel;
	}

	// 引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	// 内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	protected void callOnException(String strText,Exception e)
	{
		try
		{
			onException(strText,e);
		}
		catch(Exception ex)
		{
		}
	}

	/**
	 * 分析SQL语法 {}之内的为参数名，需要替换成? {{代表{字符 }}代表}字符
	 */
	protected AnalyzedSQL analyzeSourceSQL(String strSourceSQL)
	{
		AnalyzedSQL anaSQL=null;
		synchronized(hmAnalyzedSQL)
		{
			anaSQL=hmAnalyzedSQL.get(strSourceSQL);
		}
		if(anaSQL!=null)
		{
			return anaSQL;
		}

		ArrayList<String> alParaName=new ArrayList<String>();

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
						strbSQL.append("?");
						alParaName.add(strbParaName.toString());
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
			callOnException("analyzeSQL error",new Exception("SQL syntax Error: "+strSourceSQL));
			return null;
		}

		anaSQL=new AnalyzedSQL();
		anaSQL.strSQL=strbSQL.toString();
		anaSQL.alParaName=alParaName;

		synchronized(hmAnalyzedSQL)
		{
			hmAnalyzedSQL.put(strSourceSQL,anaSQL);
		}

		return anaSQL;
	}

	public PreparedStatement prepareStatement(Connection conn,String strSourceSQL,CDO cdoRequest) throws SQLException
	{
		onSQLStatement(strSourceSQL);

		PreparedStatement ps=null;

		// 分析原始SQL语句，得到其中的变量
		AnalyzedSQL anaSQL=analyzeSourceSQL(strSourceSQL);
		if(anaSQL==null)
		{
			throw new SQLException("Analyze source SQL exception: "+strSourceSQL);
		}

		// 准备JDBC语句
		try
		{
			if(ps==null)
			{
				ps=conn.prepareStatement(anaSQL.strSQL);
			}

			int nParaCount=anaSQL.alParaName.size();
			for(int i=0;i<nParaCount;i++)
			{
				String strParaName=anaSQL.alParaName.get(i);
				Field  object=cdoRequest.getObject(strParaName);
				Object objValue=object.getObjectValue();
				if(objValue==null){
					ps.setNull(i+1,java.sql.Types.NULL);
					continue;
				}
				int nType=object.getType().getDataType();
				switch(nType)
				{
					case DataType.BYTE_TYPE:
					case DataType.SHORT_TYPE:
					case DataType.INTEGER_TYPE:
					case DataType.LONG_TYPE:
					case DataType.FLOAT_TYPE:
					case DataType.DOUBLE_TYPE:						
					{
						
						ps.setObject(i+1,objValue);
						break;
					}
					case DataType.STRING_TYPE:
					{
						String strValue=(String)objValue;
						strValue=Utility.encodingText(strValue,strSystemCharset,strCharset);
						ps.setString(i+1,strValue);
						break;
					}
					case DataType.DATE_TYPE:
					{
						String strValue=(String)objValue;
						if(strValue.length()==0)
						{
							ps.setNull(i+1,java.sql.Types.TIMESTAMP);
						}
						else
						{
							Date date=new Date((String)objValue);
							ps.setTimestamp(i+1,date.getTimestamp());
						}
						break;
					}
					case DataType.TIME_TYPE:
					{
						String strValue=(String)objValue;
						if(strValue.length()==0)
						{
							ps.setNull(i+1,java.sql.Types.TIMESTAMP);
						}
						else
						{
							Time time=new Time((String)objValue);
							ps.setTimestamp(i+1,time.getTimestamp());
						}
						break;
					}
					case DataType.DATETIME_TYPE:
					{
						String strValue=(String)objValue;
						if(strValue.length()==0)
						{
							ps.setNull(i+1,java.sql.Types.TIMESTAMP);
						}
						else
						{
							DateTime datetime=new DateTime((String)objValue);
							ps.setTimestamp(i+1,datetime.getTimestamp());
						}
						break;
					}
					case DataType.BOOLEAN_TYPE:
					{
						ps.setBoolean(i+1,(Boolean)objValue);
						break;
					}

					case DataType.BYTE_ARRAY_TYPE:
					{
						ps.setBytes(i+1,(byte[])objValue);
						break;
					}
					default:
					{
						throw new SQLException("Unsupported type's object "+objValue);
					}
				}
			}
			logExecutedSql(cdoRequest, anaSQL);
		}
		catch(SQLException e)
		{
			closeStatement(strSourceSQL,ps);
			throw e;
		}

		return ps;
	}

	private void logExecutedSql(CDO cdoRequest, AnalyzedSQL anaSQL) {
		if (log.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder("\n{");
			for (int i = 0; i < anaSQL.alParaName.size(); i++) {
				Field object = cdoRequest.getObject(anaSQL.alParaName.get(i));
				Object objValue = object.getObjectValue();
				int nType = object.getType().getDataType();
				sb.append(nType==DataType.BYTE_ARRAY_TYPE?new String((byte[]) objValue):objValue);
				sb.append(',');
			}
			sb.append('}');
			log.debug(anaSQL.strSQL + sb.toString());
		}
	}
	
	public CDO readRecord(ResultSet rs,String[] strsFieldName,int[] naFieldType,int[] nsPrecision,int[] nsScale) throws SQLException,IOException
	{
		CDO cdoRecord=new CDO();

		if(readRecord(rs,strsFieldName,naFieldType,nsPrecision,nsScale,cdoRecord)==0)
		{
			return null;
		}
		
		return cdoRecord;
	}

	/**
	 * 读取当前的记录数据
	 * 
	 * @param rs
	 */
	public int readRecord(ResultSet rs,String[] strsFieldName,int[] naFieldType,int[] nsPrecision,int[] nsScale,CDO cdoRecord) throws SQLException,IOException
	{
		if(rs.next()==false)
		{
			return 0;
		}
		
		for(int i=0;i<strsFieldName.length;i++)
		{
			String strFieldName=strsFieldName[i];
			
			try
			{
				if(rs.getObject(i+1)==null)
				{
					continue;
				}
			}catch(Exception e)
			{//已反序列化对象,getObject应该是绝对安全的,不应该有异常,但有的driver处理空时会抛出异常,此处做兼容性处理,不需要抛出异常
				continue;				
			}

			int nFieldType=naFieldType[i];
			switch(nFieldType)
			{
				case Types.BIT:
				{
					byte byValue=rs.getByte(i+1);
					if(byValue==0)
					{
						cdoRecord.setBooleanValue(strFieldName,false);
					}
					else
					{
						cdoRecord.setBooleanValue(strFieldName,true);
					}
					break;
				}
				case Types.TINYINT:
				{
					cdoRecord.setByteValue(strFieldName,rs.getByte(i+1));
					break;
				}
				case Types.SMALLINT:
				{
					cdoRecord.setShortValue(strFieldName,rs.getShort(i+1));
					break;
				}
				case Types.INTEGER:
				{
					cdoRecord.setIntegerValue(strFieldName,rs.getInt(i+1));
					break;
				}
				case Types.BIGINT:
				{
					cdoRecord.setLongValue(strFieldName,rs.getLong(i+1));
					break;
				}
				case Types.REAL:
				{
					cdoRecord.setFloatValue(strFieldName,rs.getFloat(i+1));
					break;
				}
				case Types.DOUBLE:
				case Types.FLOAT:
				{
					cdoRecord.setDoubleValue(strFieldName,rs.getDouble(i+1));
					break;
				}
				case Types.DECIMAL:
				case Types.NUMERIC:
				{
					if(nsScale[i]==0)
					{// 整数
						if(nsPrecision[i]<3)
						{
							cdoRecord.setByteValue(strFieldName,rs.getByte(i+1));
						}
						else if(nsPrecision[i]<5)
						{
							cdoRecord.setShortValue(strFieldName,rs.getShort(i+1));
						}
						else if(nsPrecision[i]<10)
						{
							cdoRecord.setIntegerValue(strFieldName,rs.getInt(i+1));
						}
						else if(nsPrecision[i]<20)
						{
							cdoRecord.setLongValue(strFieldName,rs.getLong(i+1));
						}
						else
						{
							cdoRecord.setDoubleValue(strFieldName,rs.getDouble(i+1));
						}
					}
					else
					{// 小数
						cdoRecord.setDoubleValue(strFieldName,rs.getDouble(i+1));
					}
					break;
				}
				case Types.VARCHAR:
				case Types.LONGVARCHAR:
				case Types.CHAR:
				{
					String strValue=rs.getString(i+1);
					strValue=Utility.encodingText(strValue,strCharset,strSystemCharset);

					cdoRecord.setStringValue(strFieldName,strValue);
					break;
				}
				case Types.CLOB:
				{
					String strValue="";
					Clob clobValue=rs.getClob(i+1);
					char[] chsValue=new char[(int)clobValue.length()];
					clobValue.getCharacterStream().read(chsValue);
					strValue=new String(chsValue);
					strValue=Utility.encodingText(strValue,strCharset,strSystemCharset);
					cdoRecord.setStringValue(strFieldName,strValue.trim());
					break;
				}
				case Types.DATE:
				case Types.TIME:
				case Types.TIMESTAMP:
				{
					try
					{
						String strValue="";
						DateTime dtValue=new DateTime(rs.getTimestamp(i+1));
						strValue=dtValue.toString("yyyy-MM-dd HH:mm:ss");
						cdoRecord.setDateTimeValue(strFieldName,strValue);						
					}
					catch(Exception e)
					{						
					}
					
					break;
				}
				case Types.BINARY:
				case Types.VARBINARY:	
				case Types.LONGVARBINARY:
				{
					byte[] bysValue=null;
					InputStream stream=null;
					try
					{
						stream=rs.getBinaryStream(i+1);
						bysValue=new byte[stream.available()];
						stream.read(bysValue);
					}
					finally
					{
						Utility.closeStream(stream);
					}

					cdoRecord.setByteArrayValue(strFieldName,bysValue);
					break;
				}
			
				case Types.BLOB:
				{
					byte[] bysValue=null;
					Blob blobValue=rs.getBlob(i+1);
					bysValue=blobValue.getBytes(1,(int)blobValue.length());
					cdoRecord.setByteArrayValue(strFieldName,bysValue);
					break;
				}
				default:
					throw new SQLException("Unsupported sql data type "+nFieldType+" on field "+strFieldName);
			}
		}
		return 1;
	}

//	public Object[] readRecord(ResultSet rs,int[] naFieldType,int[] nsPrecision,int[] nsScale) throws SQLException,IOException
//	{
//		Object[] objsRecord=new Object[naFieldType.length];
//
//		if(readRecord(rs,naFieldType,nsPrecision,nsScale,objsRecord)==0)
//		{
//			return null;
//		}
//		
//		return objsRecord;
//	}

//	/**
//	 * 读取当前的记录数据
//	 * 
//	 * @param rs
//	 */
//	public int readRecord(ResultSet rs,int[] naFieldType,int[] nsPrecision,int[] nsScale,Object[] objsRecord) throws SQLException,IOException
//	{
//		if(rs.next()==false)
//		{
//			return 0;
//		}
//		
//		for(int i=0;i<naFieldType.length;i++)
//		{
//			Object obj=rs.getObject(i);
//			if(obj==null)
//			{
//				continue;
//			}
//
//			int nFieldType=naFieldType[i];
//			switch(nFieldType)
//			{
//				case Types.BIT:
//				{
//					byte byValue=rs.getByte(i);
//					if(byValue==0)
//					{
//						obj=new Boolean(false);
//					}
//					else
//					{
//						obj=new Boolean(true);
//					}
//					objsRecord[i]=obj;
//					
//					break;
//				}
//				case Types.TINYINT:
//				case Types.SMALLINT:
//				case Types.INTEGER:
//				case Types.BIGINT:
//				{
//					objsRecord[i]=obj;
//					break;
//				}
//				case Types.REAL:
//				{
//					objsRecord[i]=rs.getFloat(i);
//					break;
//				}
//				case Types.DOUBLE:
//				case Types.FLOAT:
//				{
//					objsRecord[i]=rs.getDouble(i);
//					break;
//				}
//				case Types.DECIMAL:
//				case Types.NUMERIC:
//				{
//					if(nsScale[i]==0)
//					{// 整数
//						if(nsPrecision[i]<3)
//						{
//							objsRecord[i]=rs.getByte(i);
//						}
//						else if(nsPrecision[i]<5)
//						{
//							objsRecord[i]=rs.getShort(i);
//						}
//						else if(nsPrecision[i]<10)
//						{
//							objsRecord[i]=rs.getInt(i);
//						}
//						else if(nsPrecision[i]<20)
//						{
//							objsRecord[i]=rs.getLong(i);
//						}
//						else
//						{
//							objsRecord[i]=rs.getDouble(i);
//						}
//					}
//					else
//					{// 小数
//						objsRecord[i]=rs.getDouble(i);
//					}
//					break;
//				}
//				case Types.VARCHAR:
//				case Types.LONGVARCHAR:
//				case Types.CHAR:
//				{
//					String strValue=rs.getString(i);
//					strValue=Utility.encodingText(strValue,strCharset,strSystemCharset);
//
//					objsRecord[i]=strValue;
//					break;
//				}
//				case Types.CLOB:
//				{
//					String strValue="";
//					Clob clobValue=rs.getClob(i);
//					char[] chsValue=new char[(int)clobValue.length()];
//					clobValue.getCharacterStream().read(chsValue);
//					strValue=new String(chsValue);
//					strValue=Utility.encodingText(strValue,strCharset,strSystemCharset);
//					objsRecord[i]=strValue.trim();
//					break;
//				}
//				case Types.DATE:
//				case Types.TIME:
//				case Types.TIMESTAMP:
//				{
//					String strValue="";
//					DateTime dtValue=new DateTime(rs.getTimestamp(i));
//					try
//					{
//						strValue=dtValue.toString("yyyy-MM-dd HH:mm:ss");
//					}
//					catch(Exception e)
//					{
//					}
//					objsRecord[i]=strValue;
//					break;
//				}
//				case Types.BINARY:
//				case Types.LONGVARBINARY:
//				{
//					byte[] bysValue=null;
//					InputStream stream=null;
//					try
//					{
//						stream=rs.getBinaryStream(i);
//						bysValue=new byte[stream.available()];
//						stream.read(bysValue);
//					}
//					finally
//					{
//						Utility.closeStream(stream);
//					}
//
//					objsRecord[i]=bysValue;
//					break;
//				}
//				case Types.BLOB:
//				{
//					byte[] bysValue=null;
//					Blob blobValue=rs.getBlob(i);
//					bysValue=blobValue.getBytes(1,(int)blobValue.length());
//					objsRecord[i]=bysValue;
//					break;
//				}
//				default:
//					throw new SQLException("Unsupported sql data type "+nFieldType);
//			}
//		}
//		return 1;
//	}

	

	

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
		DataEngineHelp.setVar(sv, cdoRequest);
//		String strVarId=sv.getVarId();
//		String strFieldId=strVarId.substring(1,strVarId.length()-1);
//		switch(sv.getType().getType())
//		{
//			case SetVarTypeType.BYTE_TYPE:
//				cdoRequest.setByteValue(strFieldId,Byte.parseByte(sv.getValue()));
//				break;
//			case SetVarTypeType.SHORT_TYPE:
//				cdoRequest.setShortValue(strFieldId,Short.parseShort(sv.getValue()));
//				break;
//			case SetVarTypeType.INTEGER_TYPE:
//				cdoRequest.setIntegerValue(strFieldId,Integer.parseInt(sv.getValue()));
//				break;
//			case SetVarTypeType.LONG_TYPE:
//				cdoRequest.setLongValue(strFieldId,Long.parseLong(sv.getValue()));
//				break;
//			case SetVarTypeType.FLOAT_TYPE:
//				cdoRequest.setFloatValue(strFieldId,Float.parseFloat(sv.getValue()));
//				break;
//			case SetVarTypeType.DOUBLE_TYPE:
//				cdoRequest.setDoubleValue(strFieldId,Double.parseDouble(sv.getValue()));
//				break;
//			case SetVarTypeType.STRING_TYPE:
//				cdoRequest.setStringValue(strFieldId,sv.getValue());
//				break;
//			case SetVarTypeType.DATE_TYPE:
//				cdoRequest.setDateValue(strFieldId,sv.getValue());
//				break;
//			case SetVarTypeType.TIME_TYPE:
//				cdoRequest.setTimeValue(strFieldId,sv.getValue());
//				break;
//			case SetVarTypeType.DATETIME_TYPE:
//				cdoRequest.setDateTimeValue(strFieldId,sv.getValue());
//				break;
//			default:
//				throw new RuntimeException("Invalid type "+sv.getType().toString());
//		}
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
	protected boolean checkCondition(String strValue1,String strOperator,String strValue2,IfTypeType ifType,String strType,
					CDO cdoRequest)
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
	protected boolean checkCondition(String strValue1,String strOperator,String strValue2,SQLIfTypeType sqlIfType,String strType,
					CDO cdoRequest)
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
	protected int handleSQLIf(SQLIf sqlIf,CDO cdoRequest,StringBuilder strbSQL)
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
//		int nFromIndex=this.getIntegerValue(sqlFor.getFromIndex(),cdoRequest);
//		int nCount=this.getIntegerValue(sqlFor.getCount(),cdoRequest);
		int nFromIndex=DataEngineHelp.getIntegerValue(sqlFor.getFromIndex(),cdoRequest);
		int nCount=DataEngineHelp.getIntegerValue(sqlFor.getCount(),cdoRequest);
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
			// 在相邻Item之间加入空格
//			if(i>0)
//			{
//				strbSQL.append(' ');
//			}

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
	protected int handleIf(Connection conn,HashMap<String,SQLTrans> hmTrans,If ifItem,CDO cdoRequest,
					boolean bUseTransFlag,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
		// 检查执行条件
		boolean bCondition=checkCondition(ifItem.getValue1(),ifItem.getOperator().toString(),ifItem.getValue2(),ifItem
						.getType(),ifItem.getType().toString(),cdoRequest);
		if(bCondition==true)
		{// Handle Then
			Then thenItem=ifItem.getThen();
			return handleBlock(conn,hmTrans,thenItem,cdoRequest,bUseTransFlag,cdoResponse,ret);
		}
		else
		{// handle Else
			Else elseItem=ifItem.getElse();
			if(elseItem==null)
			{// 没有else模块，当作自然执行完毕处理???
				return 0;
			}
			return handleBlock(conn,hmTrans,elseItem,cdoRequest,bUseTransFlag,cdoResponse,ret);
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
	protected int handleFor(Connection conn,HashMap<String,SQLTrans> hmTrans,For forItem,CDO cdoRequest,
					boolean bUseTransFlag,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
		// 获取循环数据
//		int nFromIndex=this.getIntegerValue(forItem.getFromIndex(),cdoRequest);
//		int nCount=this.getIntegerValue(forItem.getCount(),cdoRequest);
		int nFromIndex=DataEngineHelp.getIntegerValue(forItem.getFromIndex(),cdoRequest);
		int nCount=DataEngineHelp.getIntegerValue(forItem.getCount(),cdoRequest);		
		String strIndexId=forItem.getIndexId();
		strIndexId=strIndexId.substring(1,strIndexId.length()-1);

		// 执行循环
		for(int i=nFromIndex;i<nFromIndex+nCount;i++)
		{
			// 设置IndexId
			cdoRequest.setIntegerValue(strIndexId,i);

			// 执行Block
			int nResult=handleBlock(conn,hmTrans,forItem,cdoRequest,bUseTransFlag,cdoResponse,ret);
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

	protected void handleReturn(com.cdoframework.cdolib.database.xsd.Return returnObject,CDO cdoRequest,CDO cdoResponse,Return ret) throws SQLException
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
				object=cdoRequest.getObject(strValueId);
				cdoResponse.setObjectExt(strFieldId,object);
			}
			catch(Exception e)
			{
				continue;
			}
		}
		ret.setCode(returnObject.getCode());
		ret.setInfo(returnObject.getInfo());
		ret.setText(returnObject.getText());
	}
	
	/*
	 * 处理一个Block
	 * 
	 * @return 0-自然执行完毕，1-碰到Break退出，2-碰到Return退出
	 */
	protected int handleBlock(Connection conn,HashMap<String,SQLTrans> hmTrans,BlockType block,CDO cdoRequest,
					boolean bUseTransFlag,CDO cdoResponse,Return ret) throws SQLException,IOException
	{
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
				this.executeUpdate(conn,strSQL,cdoRequest);
			}
			else if(blockItem.getUpdate()!=null)
			{// Update
				// 获得将要执行的SQL
				Update update=(Update)blockItem.getUpdate();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(update,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				int nRecordCount=this.executeUpdate(conn,strSQL,cdoRequest);
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
				int nRecordCount=this.executeUpdate(conn,strSQL,cdoRequest);
				String strRecordCountId=delete.getRecordCountId();
				if(strRecordCountId.length()>0)
				{// 输出受影响的记录数
					strRecordCountId=strRecordCountId.substring(1,strRecordCountId.length()-1);
					cdoRequest.setIntegerValue(strRecordCountId,nRecordCount);
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
				int nRecordCount=this.executeQueryRecordSet(conn,strSQL,cdoRequest,cdoArrayField);				
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
			else if(blockItem.getSelectRecord()!=null)
			{
				// 获得将要执行的SQL
				SelectRecord selectRecord=(SelectRecord)blockItem.getSelectRecord();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(selectRecord,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				CDO cdo=new CDO();
				String strRecordCountId=selectRecord.getRecordCountId();
				if(strRecordCountId.length()>0)
				{//表示要 获取SQL查询条件中的总数量					
					cdoRequest.setBooleanValue("$$nRecordCountId$$", true);
				}
				int nRecordCount=this.executeQueryRecord(conn,strSQL,cdoRequest,cdo);				
				if(strRecordCountId.length()>0)
				{// 输出受影响的记录数
					strRecordCountId=strRecordCountId.substring(1,strRecordCountId.length()-1);
					cdoRequest.setIntegerValue(strRecordCountId,nRecordCount);
				}

				String strOutputId=selectRecord.getOutputId();
				strOutputId=strOutputId.substring(1,strOutputId.length()-1);
				cdoRequest.setCDOValue(strOutputId,cdo);
			}
			else if(blockItem.getSelectField()!=null)
			{
				// 获得将要执行的SQL
				SelectField selectField=(SelectField)blockItem.getSelectField();
				StringBuilder strbSQL=new StringBuilder();
				handleSQLBlock(selectField,cdoRequest,strbSQL);
				String strSQL=strbSQL.toString();

				// 执行SQL
				Field objFieldValue=this.executeQueryFieldExt(conn,strSQL,cdoRequest);
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
						cdoRequest.setByteValue(selectField.getOutputId(),((Byte)objValue).byteValue());
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
			else if(blockItem.getSetVar()!=null)
			{
				SetVar sv=blockItem.getSetVar();
				setVar(sv,cdoRequest);
			}
			else if(blockItem.getIf()!=null)
			{
				int nResult=handleIf(conn,hmTrans,(If)blockItem.getIf(),cdoRequest,bUseTransFlag,cdoResponse,ret);
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
				int nResult=handleFor(conn,hmTrans,(For)blockItem.getFor(),cdoRequest,bUseTransFlag,cdoResponse,ret);
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
				conn.commit();
			}
			else if(blockItem.getRollback()!=null)
			{
				conn.rollback();
			}
		}

		return 0;
	}

	// 处理一个Trans
	protected Return handleTrans(Connection conn,HashMap<String,SQLTrans> hmTrans,SQLTrans trans,CDO cdoRequest,
					CDO cdoResponse,boolean bUseTransFlag) throws SQLException,IOException
	{
		Return ret=new Return();

		// 开始执行事务
//		int nTransFlag=trans.getTransFlag().getType();
		int nTransFlag=trans.getTransFlag().value().equals(SQLTransTransFlagType.VALUE_1.value())?1:0;

		try
		{
			if(bUseTransFlag==true)
			{// 设置事务
				conn.setAutoCommit(false);
			}

			// 生成Block对象
			BlockType block=new BlockType();
//			int nTransItemCount=trans.getSQLTransChoice(0).getSQLTransChoiceItemCount();
			int nTransItemCount=trans.getSQLTransChoice().getSQLTransChoiceItemCount();
			for(int i=0;i<nTransItemCount;i++)
			{
//				SQLTransChoiceItem transItem=trans.getSQLTransChoice(0).getSQLTransChoiceItem(i);
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
			int nResult=handleBlock(conn,hmTrans,block,cdoRequest,bUseTransFlag,cdoResponse,ret);
			if(nResult!=2)
			{// Break或自然执行完毕退出
				com.cdoframework.cdolib.database.xsd.Return returnObject=trans.getReturn();
				this.handleReturn(returnObject,cdoRequest,cdoResponse,ret);
			}

			return ret;
		}
		finally
		{
			if(ret.getCode()==0)
			{//提交事务
				if((nTransFlag&0x1)==0x1)
				{//事务处理
					conn.commit();
				}
			}
			else
			{//回滚事务
				if((nTransFlag&0x1)==0x1)
				{//事务处理
					conn.rollback();
					conn.setAutoCommit(true);
				}
			}
		}
	}

	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	/**
	 * 打开数据库
	 */
	public synchronized Return open()
	{
		if(ds!=null)
		{
			return Return.OK;
		}

		ds=new BasicDataSource();   
        ds.setDriverClassName(strDriver);   
        ds.setUsername(strUserName);   
        ds.setPassword(strPassword);   
        ds.setUrl(strURI);   
        ds.setInitialSize(nMinConnectionCount);
        ds.setMaxActive(nMaxConnectionCount);   
        ds.setMaxIdle(10);
        ds.setMinIdle(nMinConnectionCount);
        ds.setMaxWait(10000);//最大等待空闲连接的时间
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(true);
        ds.setValidationQuery("select 1");
        ds.setPoolPreparedStatements(true);
        ds.setRemoveAbandoned(true);
        ds.setRemoveAbandonedTimeout(60);
        ds.setLogAbandoned(true);
        
		// 打开连接
		try
		{
			Connection conn=ds.getConnection();
			closeConnection(conn);
		}
		catch(Exception e)
		{
			callOnException("Open database error: "+e.getMessage(),e);

			Return ret=Return.valueOf(-1,e.getMessage(),"System.Error");
			ret.setThrowable(e);
			return ret;

		}

		return Return.OK;
	}

	/**
	 * 关闭数据库
	 * 
	 */
	public synchronized void close()
	{
		if(ds!=null)
		{
			try
			{
				ds.close();
			}
			catch(Exception e)
			{
			}
			ds=null;
		}
		
		synchronized(this.hmAnalyzedSQL)
		{
			this.hmAnalyzedSQL.clear();
		}
	}

	/**
	 * 获取一个数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() throws SQLException
	{
		Return ret=this.open();
		if(ret.getCode()!=0)
		{
			return null;
		}

		return ds.getConnection();
	}

	public void commit(Connection conn) throws SQLException
	{
		conn.commit();
	}

	public void rollback(Connection conn)
	{
		try
		{
			if(conn.getAutoCommit()==false)
			{
				conn.rollback();
			}
		}
		catch(Exception e)
		{
		}
	}

	/**
	 * 关闭结果集
	 * 
	 * @param rs
	 */
	public void closeResultSet(ResultSet rs)
	{
		if(rs==null)
		{
			return;
		}

		try
		{
			rs.close();
		}
		catch(Exception e)
		{
		}
	}

	/**
	 * 关闭Statement
	 * 
	 * @param stat
	 */
	public void closeStatement(String strSQL,PreparedStatement stat)
	{
		if(stat==null)
		{
			return;
		}

		try
		{
			stat.close();
		}
		catch(Exception e)
		{
		}
	}

	/**
	 * 关闭Statement
	 * 
	 * @param stat
	 */
	public void closeStatement(Statement stat)
	{
		if(stat==null)
		{
			return;
		}

		try
		{
			stat.close();
		}
		catch(Exception e)
		{
		}
	}

	/**
	 * 关闭Connection
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn)
	{
		if(conn==null)
		{
			return;
		}
		try
		{
			if(conn.getAutoCommit()==false)
			{
				conn.setAutoCommit(true);
			}
		}
		catch(SQLException e1)
		{
		}

		try
		{
			conn.close();
		}
		catch(Exception e)
		{
		}
	}

	/**
	 * 关闭Connection
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn,boolean bAutoCommit)
	{
		if(conn==null)
		{
			return;
		}

		try
		{
			if(conn.getAutoCommit()!=bAutoCommit)
			{
				conn.setAutoCommit(bAutoCommit);
			}
		}
		catch(Exception e)
		{
		}
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
		}
	}

	/**
	 * 通过一个传入的数据库连接查询并输出第一条记录的第一个字段
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	public Field executeQueryField(Connection conn,String strSQL,CDO cdoRequest) throws SQLException,IOException
	{
		// 准备JDBC语句
		PreparedStatement ps=prepareStatement(conn,strSQL,cdoRequest);

		// 输出查询结果
		ResultSet rs=null;
		try
		{
			// 执行查询
			rs=ps.executeQuery();

			// 读取记录信息
			ResultSetMetaData meta=rs.getMetaData();
			String[] strsFieldName=new String[1];
			int[] nsFieldType=new int[1];
			int[] nsPrecision=new int[1];
			int[] nsScale=new int[1];
			for(int i=0;i<strsFieldName.length;i++)
			{
//				strsFieldName[i]=meta.getColumnName(i+1);
				strsFieldName[i]=meta.getColumnLabel(i+1);
				nsFieldType[i]=meta.getColumnType(i+1);
				nsPrecision[i]=meta.getPrecision(i+1);
				nsScale[i]=meta.getScale(i+1);
			}

			CDO cdoRecord=readRecord(rs,strsFieldName,nsFieldType,nsPrecision,nsScale);
			if(cdoRecord==null)
			{
				return null;
			}

			// 输出
			if(cdoRecord.exists(strsFieldName[0]))
			{
				return cdoRecord.getObject(strsFieldName[0]);
			}
			else
			{
				return null;
			}
		}
		catch(SQLException e)
		{
			callOnException("executeQueryField Exception: "+strSQL,e);
			throw e;
		}
		finally
		{
			this.closeResultSet(rs);
			this.closeStatement(strSQL,ps);
		}
	}

	/**
	 * 通过一个传入的数据库连接查询并输出第一条记录的第一个字段(含类型)
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	public Field executeQueryFieldExt(Connection conn,String strSQL,CDO cdoRequest) throws SQLException,IOException
	{
		// 准备JDBC语句
		PreparedStatement ps=prepareStatement(conn,strSQL,cdoRequest);

		// 输出查询结果
		ResultSet rs=null;
		try
		{
			// 执行查询
			rs=ps.executeQuery();
			ResultSetMetaData meta=rs.getMetaData();
			String[] strsFieldName=new String[1];
			int[] nsFieldType=new int[1];
			int[] nsPrecision=new int[1];
			int[] nsScale=new int[1];
			for(int i=0;i<strsFieldName.length;i++)
			{
//				strsFieldName[i]=meta.getColumnName(i+1);
				strsFieldName[i]=meta.getColumnLabel(i+1);
				nsFieldType[i]=meta.getColumnType(i+1);
				nsPrecision[i]=meta.getPrecision(i+1);
				nsScale[i]=meta.getScale(i+1);
			}

			// 读取记录信息
			CDO cdoRecord=readRecord(rs,strsFieldName,nsFieldType,nsPrecision,nsScale);
			if(cdoRecord==null)
			{
				return null;
			}

			// 输出
			if(cdoRecord.exists(strsFieldName[0]))
			{
				return cdoRecord.getObject(strsFieldName[0]);
//				return new ObjectExt(cdoRecord.getObject(strsFieldName[0]).getType(), cdoRecord.getObject(strsFieldName[0]).getObjectValue());
			}
			else
			{
				return null;
			}
		}
		catch(SQLException e)
		{
			callOnException("executeQueryField Exception: "+strSQL,e);
			throw e;
		}
		finally
		{
			this.closeResultSet(rs);
			this.closeStatement(strSQL,ps);
		}
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
	public int executeQueryRecord(Connection conn,String strSQL,CDO cdoRequest,CDO cdoResponse) throws SQLException,
					IOException
	{
		// 准备JDBC语句 执行sql查询记录
		PreparedStatement ps=prepareStatement(conn,strSQL,cdoRequest);

		// 输出查询结果
		ResultSet rs=null;
		try
		{
			// 执行查询
			rs=ps.executeQuery();
			ResultSetMetaData meta=rs.getMetaData();
			String[] strsFieldName=new String[meta.getColumnCount()];
			int[] nsFieldType=new int[strsFieldName.length];
			int[] nsPrecision=new int[strsFieldName.length];
			int[] nsScale=new int[strsFieldName.length];
			for(int i=0;i<strsFieldName.length;i++)
			{
				/**支持JDBC4**/
				strsFieldName[i]=meta.getColumnLabel(i+1);
				nsFieldType[i]=meta.getColumnType(i+1);
				nsPrecision[i]=meta.getPrecision(i+1);
				nsScale[i]=meta.getScale(i+1);
			}
			// 读取记录信息
			int nRecordCount=readRecord(rs,strsFieldName,nsFieldType,nsPrecision,nsScale,cdoResponse);
			//统计查询
			int nCount=executeCount(conn, strSQL, cdoRequest);
			if(nCount==0)
				nCount=nRecordCount;
			return nCount;
			
		}
		catch(SQLException e)
		{
			callOnException("executeQueryRecord Exception: "+strSQL,e);
			throw e;
		}
		finally
		{
			this.closeResultSet(rs);
			this.closeStatement(strSQL,ps);
		}
	}

	private int executeCount(Connection conn,String strSQL,CDO cdoRequest){
		if(!cdoRequest.exists("$$nRecordCountId$$") || !cdoRequest.getBooleanValue("$$nRecordCountId$$"))
			return 0;
		if(!getDriver().trim().equals("com.mysql.jdbc.Driver"))
			return 0;

		String strTempSQL=strSQL.toUpperCase();
		//是否是分组
		int index=-1;
		boolean isGroup=false;
		if(strTempSQL.contains("GROUP")){
			index=strTempSQL.lastIndexOf("GROUP");
			index=index+5;
			if(strTempSQL.charAt(index)==' ' || strTempSQL.charAt(index)!='\t'
					|| strTempSQL.charAt(index)=='\n'){	
				index++;
				while(true){
					try{
						if(strTempSQL.charAt(index)==' ' || strTempSQL.charAt(index)=='\t'
								|| strTempSQL.charAt(index)=='\n'){
							System.out.println(strTempSQL.charAt(index));
							index++;
							continue;
						}						
						if(strTempSQL.charAt(index)=='B' && strTempSQL.charAt(index+1)=='Y'
								&& (strTempSQL.charAt(index+2)==' ' || strTempSQL.charAt(index+2)!='\t'
										|| strTempSQL.charAt(index+2)!='\n') ){
							isGroup=true;
							break;
						}else{
							break;
						}
					}catch(Exception ex){
						isGroup=false;
					}
				}				
			}
		}
		
		//如果不是Group by  
		if(!isGroup){
			//去掉 原始SQL From前面 字段			
			while(true){
				strTempSQL=strSQL.toUpperCase();
				index=strTempSQL.lastIndexOf("FROM");			
				if(index==-1 || strTempSQL.length()<(index+4))
					break;
				if(strSQL.charAt(index-1)=='{' && strSQL.charAt(index+4)=='}'){
					strSQL=" "+strSQL.substring(index+4);
					continue;
				}
				//如果前一个是空格 ,回车 且 From后面是空格，回车
				if((strTempSQL.charAt(index-1)==' ' ||strTempSQL.charAt(index-1)=='\t' || strTempSQL.charAt(index-1)=='\n')
						&& (strTempSQL.charAt(index+4)==' ' ||strTempSQL.charAt(index+4)=='\t' || strTempSQL.charAt(index+4)=='\n')){
					strSQL=strSQL.substring(index);
					break;
				}else{
					for(int i=(index+4);i<strSQL.length();i++){
						if(strSQL.charAt(i)==' '||strSQL.charAt(i)=='\t'||strSQL.charAt(i)=='\n'){
							strSQL=strSQL.substring(i);
							break;
						}
					}
				}
			}
		}
		/** strSQL得到 从from 后 截取的字符串 
		 * 去掉 limit限制
		 */
		//统计查询语句总数量
		StringBuilder sb=new StringBuilder(30);
		sb.append("SELECT count(*) as nCount ");
		if(isGroup)
			sb.append(" FROM (");
		delOrderLimit(sb, strSQL, "LIMIT");		
		/**去掉Order  By**/
		strSQL=sb.toString();
		sb=new StringBuilder(30);
		delOrderLimit(sb, strSQL, "ORDER");
		if(isGroup)
			sb.append(" )T");		
		// 准备JDBC语句 执行sql查询记录
		PreparedStatement ps=null;			
		// 输出查询结果
		ResultSet rs=null;
		int nCount=0;
		try
		{
			ps=prepareStatement(conn,sb.toString(),cdoRequest);
			rs=ps.executeQuery();	
			while(rs.next()){
				nCount=rs.getInt("nCount");
			}
		}catch(Exception e){		
			nCount=0;
			callOnException("executeQueryRecord Count Exception: "+strSQL,e);			
		}finally{
			this.closeResultSet(rs);
			this.closeStatement(strSQL,ps);
		}
		return nCount;
		
	}
	/**去掉strSQL 中的 Limit,ORDER BY ，统计总数用到
	 * delKeys="LIMIT","ORDER"
	**/
	private void delOrderLimit(StringBuilder sb,String strSQL,String delKeys){
		String strTempSQL=null;
		int index=-1;
		int delKeyLength=delKeys.length();
		boolean isbreak=false;
		while(true){
			strTempSQL=strSQL.toUpperCase();
			index=strTempSQL.lastIndexOf(delKeys);			
			if(index==-1 || strTempSQL.length()<(index+delKeyLength)){
				if(delKeys.equals("ORDER"))
					sb.append(strSQL);
				break;
			}				
			if(strSQL.charAt(index-1)=='{' && strSQL.charAt(index+delKeyLength)=='}'){
				sb.append(strSQL.substring(0,index+delKeyLength));
				strSQL=" "+strSQL.substring(index+delKeyLength);
				continue;
			}
			if((strTempSQL.charAt(index-1)==' ' ||strTempSQL.charAt(index-1)=='\t' || strTempSQL.charAt(index-1)=='\n')
				&& (strTempSQL.charAt(index+delKeyLength)==' '||strTempSQL.charAt(index+delKeyLength)=='\t'||strTempSQL.charAt(index+delKeyLength)=='\n')){
				sb.append(strSQL.substring(0,index));
				break;
			}else{
				isbreak=false;
				for(int i=(index+delKeyLength);i<strSQL.length();i++){
					if(strSQL.charAt(i)==' ' || strSQL.charAt(i)=='\t' || strSQL.charAt(index+delKeyLength)=='\n'){
						sb.append(strSQL.substring(0,i));
						strSQL=strSQL.substring(i);
						isbreak=true;
						break;
					}
				}
				if(!isbreak)
					break;
			}	
		}	
	}
	
	/**
	 * 通过一个传入的数据库连接查询并输出所有记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cafRecordSet
	 * @return
	 * @throws Exception
	 */
	public int executeQueryRecordSet(Connection conn,String strSQL,CDO cdoRequest,CDOArrayField cafRecordSet)
					throws SQLException,IOException
	{
		// 准备JDBC语句 执行记录查询
		PreparedStatement ps=prepareStatement(conn,strSQL,cdoRequest);		
		// 输出查询结果
		ResultSet rs=null;
		try
		{
			// 执行查询
			rs=ps.executeQuery();
			// 读取Meta信息
			ResultSetMetaData meta=rs.getMetaData();
			String[] strsFieldName=new String[meta.getColumnCount()];
			int[] nsFieldType=new int[strsFieldName.length];
			int[] nsPrecision=new int[strsFieldName.length];
			int[] nsScale=new int[strsFieldName.length];
		
			for(int i=0;i<strsFieldName.length;i++)
			{
				/**支持JDBC4**/
				strsFieldName[i]=meta.getColumnLabel(i+1);
				/**支持JDBC3**/
				/**strsFieldName[i]=meta.getColumnName(i+1);**/
				nsFieldType[i]=meta.getColumnType(i+1);
				nsPrecision[i]=meta.getPrecision(i+1);
				nsScale[i]=meta.getScale(i+1);
			}

			// 读取记录
			ArrayList<CDO> alRecord=new ArrayList<CDO>();
			while(true)
			{
				// 读取记录信息
				CDO cdoRecord=readRecord(rs,strsFieldName,nsFieldType,nsPrecision,nsScale);
				if(cdoRecord==null)
				{
					break;
				}
				alRecord.add(cdoRecord);
			}

			// 输出记录
			CDO[] cdosRecord=new CDO[alRecord.size()];
			for(int i=0;i<cdosRecord.length;i++)
			{
				cdosRecord[i]=(CDO)alRecord.get(i);
			}
			cafRecordSet.setValue(cdosRecord);
			
			//统计总数量查询
			int nCount=executeCount(conn, strSQL, cdoRequest);
			if(nCount==0)
				nCount=cdosRecord.length;
			
			return nCount;
		}
		catch(SQLException e)
		{
			callOnException("executeQueryRecordSet Exception: "+strSQL,e);
			throw e;
		}
		finally
		{
			this.closeResultSet(rs);
			this.closeStatement(strSQL,ps);
		}
	}

	/**
	 * 执行数据库更新语句
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	public int executeUpdate(Connection conn,String strSQL,CDO cdoRequest) throws SQLException
	{
		// 准备JDBC语句
		PreparedStatement ps=prepareStatement(conn,strSQL,cdoRequest);

		// 输出查询结果
		try
		{
			// 执行查询
			int nCount=ps.executeUpdate();

			return nCount;
		}
		catch(SQLException e)
		{
			callOnException("executeUpdate Exception: "+strSQL,e);
			throw e;
		}
		finally
		{
			this.closeStatement(strSQL,ps);
		}
	}

	/**
	 * 执行数据库更新语句
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	public void executeSQL(Connection conn,String strSQL,CDO cdoRequest) throws SQLException
	{
		// 准备JDBC语句
		PreparedStatement ps=prepareStatement(conn,strSQL,cdoRequest);

		// 输出查询结果
		try
		{
			// 执行查询
			ps.execute();
		}
		catch(SQLException e)
		{
			callOnException("executeUpdate Exception: "+strSQL,e);
			throw e;
		}
		finally
		{
			this.closeStatement(strSQL,ps);
		}
	}

	/**
	 * 使用指定的连接执行数据库事务
	 * 
	 * @param conn
	 * @param cdoRequest
	 * @return
	 */
	public Return executeTrans(Connection conn,HashMap<String,SQLTrans> hmTrans,CDO cdoRequest,CDO cdoResponse,boolean bUseTransFlag)
	{
		Return ret=null;

		// 得到TransName
		String strTransName="";
		try
		{
			strTransName=cdoRequest.getStringValue("strTransName");
		}
		catch(Exception e)
		{
			return null;
		}

		// 查找Trans对象
		SQLTrans trans=(SQLTrans)hmTrans.get(strTransName);
		if(trans==null)
		{// 未找到
			return null;
		}

		// Trans找到，开始执行事务
		try
		{
			return handleTrans(conn,hmTrans,trans,cdoRequest,cdoResponse,bUseTransFlag);
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
					ret=Return.valueOf(onError.getReturn().getCode(),onError.getReturn().getText(),onError.getReturn()
									.getInfo());
					break;
				}
			}
			if(ret==null)
			{// 没有定义OnError
				ret=Return.valueOf(onException.getReturn().getCode(),onException.getReturn().getText(),onException
								.getReturn().getInfo());
			}

			return ret;
		}
		catch(IOException e)
		{
			callOnException("executeTrans Exception: "+strTransName,e);

			OnException onException=trans.getOnException();
			ret=Return.valueOf(onException.getReturn().getCode(),onException.getReturn().getText(),onException
							.getReturn().getInfo());

			return ret;
		}
		catch(Exception e)
		{
			callOnException("executeTrans Exception: "+strTransName,e);

			OnException onException=trans.getOnException();
			ret=Return.valueOf(onException.getReturn().getCode(),onException.getReturn().getText(),onException
							.getReturn().getInfo());

			return ret;
		}
	}

	/**
	 * 使用指定的连接执行数据库事务，启用TransFlag
	 * 
	 * @param conn
	 * @param cdoRequest
	 * @return
	 */
	public Return executeTrans(Connection conn,HashMap<String,SQLTrans> hmTrans,CDO cdoRequest,CDO cdoResponse)
	{
		return executeTrans(conn,hmTrans,cdoRequest,cdoResponse,true);
	}

	/**
	 * 执行数据库事务
	 * 
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 */
	public Return executeTrans(HashMap<String,SQLTrans> hmTrans,CDO cdoRequest,CDO cdoResponse)
	{
		Return ret=null;

		// 获取数据库联接
		Connection conn=null;
		try
		{
			conn=this.getConnection();
		}
		catch(SQLException e)
		{
			ret=Return.valueOf(RETURN_SYSTEMERROR,"Cannot obtain database connection","System.Error");
			return ret;
		}

		// 执行数据库事务
		try
		{
			ret=executeTrans(conn,hmTrans,cdoRequest,cdoResponse);
		}
		finally
		{
			closeConnection(conn);
		}

		return ret;
	}


	// 接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	// 事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	public void onException(String strText,Exception e)
	{
	}

	public void onSQLStatement(String strSQL)
	{
		
	}

	// 构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public DataEngine()
	{
		// 请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		strDriver="";
		strURI="";
		strCharset="GBK";
		properties=null;

		ds=null;

		strUserName="";
		strPassword="";
		strSystemCharset=System.getProperty("sun.jnu.encoding");

		nMinConnectionCount=10;
		nMaxConnectionCount=100;
		lTimeout=60000L;

		hmAnalyzedSQL	=new HashMap<String,AnalyzedSQL>(100);
	}
	
}
