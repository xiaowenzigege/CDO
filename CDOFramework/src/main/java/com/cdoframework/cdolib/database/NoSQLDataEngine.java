package com.cdoframework.cdolib.database;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.xsd.NoSQLTrans;
import com.cdoframework.cdolib.servicebus.xsd.NoSQLDB;
import com.cdoframework.cdolib.servicebus.xsd.PoolConfig;
import com.cdoframework.cdolib.servicebus.xsd.ServerAddr;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

/**
 * @author Administrator
 */
public class NoSQLDataEngine implements INoSQLDataEngine
{
	//内部类,所有内部类在此声明----------------------------------------------------------------------------------
	
	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	static Logger logger = Logger.getLogger(NoSQLDataEngine.class);
	public static String FUNCTION_DATETIME = "now()";
	
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private Mongo mongo;
	private MongoOptions option;
	private DB mainDB;
	
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private List<ServerAddress> lsAddress;
	private DBConfig[] dbConfig;
	private String strId;
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.database.INoSQLDataEngine#close()
	 */

	public void close()
	{
		this.mongo.close();
	}

	

	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.database.INoSQLDataEngine#getCollection(java.lang.String)
	 */
	public DBCollection getCollection(String strCollectionName)
	{
		return this.mainDB.getCollection(strCollectionName);
	}
	public DBCollection getCollection(String strDBName,String strCollectionName)
	{
		if(strDBName==null || strDBName.length()==0)
		{
			return this.mainDB.getCollection(strCollectionName);
		}
		return this.mongo.getDB(strDBName).getCollection(strCollectionName);
	}

	public Return initNoSQLDataEngine(NoSQLDB define) throws Exception
	{	  
		//connection pools configuration parameter
		PoolConfig poolConfig = define.getPoolConfig();
		this.setConnectionPoolConfig(poolConfig.getConnectTimeout(),poolConfig.getAutoConnectRetry(),poolConfig.getMaxWaitTime(),poolConfig.getSocketTimeout(),poolConfig.getSize(),poolConfig.getMaxBlockPerConn());
		poolConfig.getConnectTimeout();
		poolConfig.getAutoConnectRetry();
		poolConfig.getMaxWaitTime();
		poolConfig.getSize();
		poolConfig.getSocketTimeout();
		poolConfig.getMaxBlockPerConn();
	  
		//server address
		ServerAddr[] addrs=define.getServerAddr();
		if(addrs==null)
		{
			logger.error("ServerAddr must be defined in serviceBus.xml ");
			throw new Exception("ServerAddr must be defined in serviceBus.xml ");
		}
		List<ServerAddress> list=new ArrayList<ServerAddress>();
		for(ServerAddr addr:addrs)
		{
			try
			{
				ServerAddress sa=new ServerAddress(addr.getIP(),addr.getPort());
				list.add(sa);
			}
			catch(UnknownHostException e)
			{
				logger.error("when init Server address in NoSQLDB define ",e);
				throw e;
			}
		}
		this.setServerAddressList(list);
		
		//db names		
		com.cdoframework.cdolib.servicebus.xsd.DBConfig[] dbConfig = define.getDBConfig();
		if(dbConfig==null)
		{
			logger.error("dbConfig must be defined in serviceBus.xml ");
			throw new Exception("dbConfig must be defined in serviceBus.xml ");
		}
		com.cdoframework.cdolib.database.DBConfig[] dbsConfig = new com.cdoframework.cdolib.database.DBConfig[dbConfig.length];
		for(int i=0;i<dbConfig.length;i++)
		{
			dbsConfig[i] = new com.cdoframework.cdolib.database.DBConfig();
			dbsConfig[i].setDBName(dbConfig[i].getDBName());
			dbsConfig[i].setUserName(dbConfig[i].getUserName());
			if(dbConfig[i].getPassword()!=null)
			{
				dbsConfig[i].setPassword(dbConfig[i].getPassword().toCharArray());
			}
		}
		this.setDBConfig(dbsConfig);
		
		return this.open();
	}
	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.database.INoSQLDataEngine#open()
	 */
	public Return open()
	{
		boolean authenResult = true;
		mongo = new Mongo(lsAddress.get(0),option);
		mongo.setWriteConcern(com.mongodb.WriteConcern.SAFE);
		int index = 0;
		try
		{
			for(DBConfig config:dbConfig)
			{			
				DB db = mongo.getDB(config.getDBName());
				if(config.getUserName()!=null)
				{
					authenResult = db.authenticate(config.getUserName(),config.getPassword());
					if(authenResult==false)
					{
						break;
					}
				}
				else
				{
						db.requestStart();
						//测试连接
						db.getPreviousError();
						db.requestDone();
					
				}
				if(index==0)
				{
					this.mainDB = db;
				}
				index++;
			}
		}
		catch (Exception e)
		{
			logger.error("open:"+e.getMessage(),e);
			Return ret = Return.valueOf(-1,e.getLocalizedMessage());
			ret.setThrowable(e);
			return ret;
		}
		if(authenResult)
		{
			return Return.OK;
		}
		else
		{
			return Return.valueOf(01,dbConfig[index].getDBName()+" did not pass the authentication ");
		}
		
	}
	public boolean testConnection()
	{
		try
		{
			this.mainDB.requestStart();
			this.mainDB.getLastError();
			this.mainDB.requestDone();
			return true;
		}
		catch(Exception e)
		{
			logger.error("testConnection:"+e.getMessage(),e);
			return false;
		}
	}
	public DB getDB()
	{		
		return mainDB;
	}
	public DB getDB(String strDBName)
	{
		if(strDBName==null || strDBName.length()==0)
		{
			return mainDB;
		}
		return this.mongo.getDB(strDBName);
	}

	public void setConnectionPoolConfig(int connectTimeout,boolean autoConnectRetry,int maxWaitTime,int socketTimeout,
					int connectionsPerHost,int getMaxBlockPerConn)
	{
		option.connectTimeout 		= connectTimeout;
		option.autoConnectRetry 	= autoConnectRetry;	   
		option.maxWaitTime 			= maxWaitTime;	   
		option.connectionsPerHost	= connectionsPerHost;
		option.socketTimeout		= socketTimeout;
		option.threadsAllowedToBlockForConnectionMultiplier = getMaxBlockPerConn;		
	}
	public void setDBConfig(DBConfig[] dbConfig)
	{
		this.dbConfig = dbConfig;		
	}
	public void setServerAddressList(List<ServerAddress> lsAddress)
	{
		this.lsAddress = lsAddress;		
	}


	
	public String getId()
	{
		return this.strId;
	}

	public void setId(String strId)
	{
		this.strId = strId;
	}

	public Return executeTrans(NoSQLTrans noSqlTrans,CDO cdoRequest,CDO cdoResponse)
	{
		return NoSQLUtil.executeTrans(noSqlTrans,cdoRequest,cdoResponse);
	}
	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public NoSQLDataEngine()
	{
		option  		= new MongoOptions();
		option.connectTimeout 		= 0;
		option.autoConnectRetry 	= false;	   
		option.maxWaitTime 			= 0;	   
		option.connectionsPerHost	= 10;
		option.socketTimeout		= 0;
		option.threadsAllowedToBlockForConnectionMultiplier = 5;
	}

}
