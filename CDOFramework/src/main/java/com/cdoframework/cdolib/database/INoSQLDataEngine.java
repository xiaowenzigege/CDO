package com.cdoframework.cdolib.database;

import java.util.List;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.xsd.NoSQLTrans;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.ServerAddress;

/**
 * 
 * @author KenelLiu
 *
 */
public interface INoSQLDataEngine
{
	//-----------------modify method---------------------	
	String	UPDATE_METHOD_INC=		"$inc";    
	String	UPDATE_METHOD_SET=		"$set";    
	String	UPDATE_METHOD_UNSET=	"$unset";  
	String	UPDATE_METHOD_PUSH=		"$push";   
	String	UPDATE_METHOD_PUTALL=	"$pushAll";
	String	UPDATE_METHOD_ADDTOSET=	"$addToSet";
	String	UPDATE_METHOD_POP=		"$pop";    
	String	UPDATE_METHOD_PULL=		"$pull";   
	String	UPDATE_METHOD_PULLALL=	"$pullAll";
	
	String	REMOVE_METHOD_ATOMIC = "$atomic";
	//-----------------查询条件相关--------------------------
	String CERTERIAS_TYPE_AND="AND";
	String CERTERIAS_TYPE_OR="OR";
	
	String 	IN_SEPRATOR	=",";
	String	FIND_GT		= "$gt";
	String	FIND_GTE	= "$gte";
	String	FIND_LT		= "$lt";
	String	FIND_LTE	= "$lte";
	String	FIND_NE		= "$ne";
	String	FIND_IN		= "$in";
	String	FIND_NIN	= "$nin";
	String	FIND_MOD	= "$mod";
	String	FIND_ALL	= "$all";
	String	FIND_SIZE	= "$size";
	String	FIND_EXISTS = "$exists";
	String	FIND_WHERE	= "$where";
	String	FIND_NEAR	= "$near";	
	String	FIND_TYPE	= "$type";
	String	FIND_REGEX	= "$regex";
	
	
	//---------------------------data type
	int TYPE_DOUBLE 			=1;
	int TYPE_STRING				=2;
	int TYPE_OBJECT				=3;
	int TYPE_ARRAY				=4;
	int TYPE_BINARY_DATA		=5;
	int TYPE_OBJECT_ID			=7;
	int TYPE_BOOLEAN			=8;
	int TYPE_DATE				=9;
	int TYPE_NULL				=10;  
	int TYPE_REGULAR_EXPRESSION	=11;  
	int TYPE_JAVASCRIPT_CODE	=13;  
	int TYPE_SYMBOL				=14;  
	int TYPE_JAVASCRIPT_CODE_WITH_SCOPE=15;  
	int TYPE_32_BIT_INTEGER		=16;  
	int TYPE_TIMESTAME			=17;  
	int TYPE_64_BIT_INTEGER		=18;  
	int TYPE_MIN_KEY			=255;  
	int TYPE_MAX_KEY			=127;
	
	//---------------------排序相关-----------------------------
	String CERTERIAS_OPERATION_OR="$or";
	String CERTERIAS_OPERATION_AND="$and";
	String CERTERIAL_SYSTEM_ID_PREFIX="{ \"_id\" :";
	String SORT_CDO_KEY="cdosSortConditon";
	String SORT_CDO_FIELD_NAME="strFieldName";
	String SORT_CDO_ORDER="strOrder";
	
	
	
	String getId();
	void setId(String strId);
	/**
	 * set server address list
	 * @param lsAddress
	 */
	void setServerAddressList(List<ServerAddress> lsAddress);
	/**
	 * set connection pool configuration
	 * @param connectTimeout 
	 * @param autoConnectRetry 连接失败后是否自动重试
	 * @param maxWaitTime 			最长等待时间
	 * @param socketTimeout			连接超时
	 * @param connectionsPerHost	每个客户端的最大连接数,如果超过,将等待
	 * @param getMaxBlockPerConn	每次连接最长等待时间
	 */
	void setConnectionPoolConfig(int connectTimeout,boolean autoConnectRetry,int maxWaitTime,int socketTimeout, int connectionsPerHost,int getMaxBlockPerConn);
	/**
	 * set db configuration
	 * @param dbConfig
	 */
	void setDBConfig(DBConfig[] dbConfig);
	
	/**
	 * 取指定集合的数据库操作对象
	 * @param strCollectionName 集合名
	 * @return 指定集合的数据库操作对象
	 */
	DBCollection getCollection(String strCollectionName);
	/**
	 * 取指定集合的数据库操作对象
	 * @param strDBName 数据库名
	 * @param strCollectionName 集合名
	 * @return 指定集合的数据库操作对象
	 */
	DBCollection getCollection(String strDBName,String strCollectionName);
	DB				getDB();
	DB				getDB(String strDBName);
	Return executeTrans(NoSQLTrans noSqlTrans,CDO cdoRequest,CDO cdoResponse);
	Return open();
	void close();
}
