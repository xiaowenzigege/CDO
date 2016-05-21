package com.cdoframework.cdolib.framework;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.IActiveObject;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.IDataEngine;

/**
 * @author Frank
 */
public class ClusterController extends ThreadGroup
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	Logger logger = Logger.getLogger(ClusterController.class);
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private String		strServerIP;
	private ArrayList<ClusteredService>	alServiceObject;
	private int			nIndex;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private int			nMaxDeadSecond;
	private int			nPulseSecond;
	
	public int			getMaxDeadSecond(){return this.nMaxDeadSecond;}
	public void			setMaxDeadSecond(int nMaxDeadSecond){this.nMaxDeadSecond=nMaxDeadSecond;}
	
	public int			getPulseSecond(){return this.nPulseSecond;}
	public void			setPulseSecond(int nPulseSecond){this.nPulseSecond=nPulseSecond;}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private CycleList<IDataEngine> clDataEngine;
	public void setDataGroup(CycleList<IDataEngine> clDataEngine){this.clDataEngine=clDataEngine;}

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	/**
	 * 尝试获取指定名称的动态对象的锁
	 * 
	 * @param strName	锁对应的动态对象的名字
	 * @return 1表示成功获得锁，0表示未获得锁，-1表示执行失败
	 */
	private int tryLockServiceObject(String strName)
	{
		//获取数据库连接
		if(clDataEngine==null || clDataEngine.size()==0)
		{
			return -1;
		}
		IDataEngine dataEngine=clDataEngine.get();
		Connection conn= null;
		try
		{
			conn=dataEngine.getConnection();
			if(conn==null)
			{
				if(conn == null)
				{
					onError("Get database connection error");
					return -1;
				}
			}

			String strSQL="";
			if(dataEngine.getURI().indexOf("sqlserver")>=0)
			{
				strSQL="UPDATE tbClusteredService SET strServerIP={strServerIP},dtActiveTime=GETDATE() WHERE strServiceId={strServiceId} AND DATEDIFF(second,dtActiveTime,GETDATE())>"+nMaxDeadSecond;
			}
			else if(dataEngine.getURI().indexOf("oracle")>=0)
			{
				strSQL="UPDATE tbClusteredService SET strServerIP={strServerIP},dtActiveTime=SYSDATE WHERE strServiceId={strServiceId} AND dtActiveTime<SYSDATE-"+nMaxDeadSecond+"/86400";
			}
			else if(dataEngine.getURI().indexOf("mysql")>=0)
			{
				strSQL="UPDATE tbClusteredService SET strServerIP={strServerIP},dtActiveTime=now() WHERE strServiceId={strServiceId} AND dtActiveTime<date_sub(now(),interval "+nMaxDeadSecond+" second)";
			}
			else
			{
				onError("Invalid database type");
				return -1;
			}
			
			CDO cvParameter=new CDO();
			cvParameter.setStringValue("strServerIP",strServerIP);
			cvParameter.setStringValue("strServiceId",strName);
			
			int nCount=dataEngine.executeUpdate(conn,strSQL,cvParameter);
			if(nCount==0)
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			onError(e.getMessage());
			return -1;
		}
		finally
		{
			if(dataEngine!=null)
			{
				dataEngine.closeConnection(conn);
			}
		}
		
		return 1;
	}

	/**
	 * 更新锁对应对象的活动时间
	 * 
	 * @param strName	锁对应的动态对象的名字
	 * @return 1表示成功，0表示更新成功-个记录，-1表示执行失败
	 */
	private int updateActiveTime(String strServiceId)
	{
		//获取数据库连接
		if(clDataEngine==null || clDataEngine.size()==0)
		{
			return -1;
		}
		IDataEngine dataEngine=clDataEngine.get();
		Connection conn=null;
		try
		{
			conn=dataEngine.getConnection();
			if(conn==null)
			{
				onError("Get database connection error");
				return -1;
			}

			String strSQL="";
			if(dataEngine.getURI().indexOf("sqlserver")>=0)
			{
				strSQL="UPDATE tbClusteredService SET dtActiveTime=GETDATE() WHERE strServiceId={strServiceId} AND strServerIP={strServerIP} AND DATEDIFF(second,dtActiveTime,GETDATE())<"+nMaxDeadSecond;
			}
			else if(dataEngine.getURI().indexOf("oracle")>=0)
			{
				strSQL="UPDATE tbClusteredService SET dtActiveTime=SYSDATE WHERE strServiceId={strServiceId} AND strServerIP={strServerIP} AND SYSDATE-dtActiveTime<"+nMaxDeadSecond+"/86400";
			}
			else if(dataEngine.getURI().indexOf("mysql")>=0)
			{
				strSQL="UPDATE tbClusteredService SET dtActiveTime=now() WHERE strServiceId={strServiceId} AND strServerIP={strServerIP} AND dtActiveTime>date_sub(now(),interval "+nMaxDeadSecond+" second)";
			}
			else
			{
				onError("Invalid database type");
				return -1;
			}
			
			CDO cvParameter=new CDO();
			cvParameter.setStringValue("strServerIP",strServerIP);
			cvParameter.setStringValue("strServiceId",strServiceId);
			
			int nCount=dataEngine.executeUpdate(conn,strSQL,cvParameter);
			if(nCount==0)
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			onError(e.getMessage());
			return -1;
		}
		finally
		{
			if(dataEngine!=null)
			{
				dataEngine.closeConnection(conn);
			}
		}
		
		return 1;
	}
	/**
	 * 更新锁对应对象的活动时间
	 * 
	 * @param strName	锁对应的动态对象的名字
	 * @return 1表示成功，0表示更新成功-个记录，-1表示执行失败
	 */
	private int initServiceRecord(String strServiceId,String strServerIP)
	{
		//获取数据库连接
		if(clDataEngine==null || clDataEngine.size()==0)
		{
			return -1;
		}
		IDataEngine dataEngine=clDataEngine.get();
		Connection conn=null;
		try
		{
			conn=dataEngine.getConnection();
			if(conn==null)
			{
				onError("Get database connection error");
				return -1;
			}

			String strSelectSQL="SELECT 1 FROM tbClusteredService WHERE strServiceId={strServiceId} ";
			String strInsertSQL="";
			if(dataEngine.getURI().indexOf("sqlserver")>=0)
			{
				strInsertSQL="INSERT INTO tbClusteredService (strServiceId,strServerIP,dtActiveTime) VALUES({strServiceId},{strServerIP},GETDATE())";
			}
			else if(dataEngine.getURI().indexOf("oracle")>=0)
			{
				strInsertSQL="INSERT INTO tbClusteredService (strServiceId,strServerIP,dtActiveTime) VALUES({strServiceId},{strServerIP},SYSDATE)";
			}
			else if(dataEngine.getURI().indexOf("mysql")>=0)
			{
				strInsertSQL="INSERT INTO tbClusteredService (strServiceId,strServerIP,dtActiveTime) VALUES({strServiceId},{strServerIP},now())";
			}
			else
			{
				onError("Invalid database type");
				return -1;
			}
			
			CDO cvParameter=new CDO();
			cvParameter.setStringValue("strServerIP",strServerIP);
			cvParameter.setStringValue("strServiceId",strServiceId);
			
			int nCount = dataEngine.executeQueryRecord(conn,strSelectSQL,cvParameter,new CDO());
			if(nCount==0)
			{
				try
				{
					nCount=dataEngine.executeUpdate(conn,strInsertSQL,cvParameter);
					if(nCount==0)
					{
						return 0;
					}
				}
				catch(Exception e)
				{
					logger.error("initServiceRecord:"+e.getMessage(),e);
				}
			}
			return nCount;
		}
		catch(Exception e)
		{
			onError(e.getMessage());
			return -1;
		}
		finally
		{
			if(dataEngine!=null)
			{
				dataEngine.closeConnection(conn);
			}
		}
	}
	private void initServiceRecord(String strServerIP) throws Exception
	{
		for(ClusteredService service:alServiceObject)
		{
			String strServiceId = service.getObjectId();
			int nCount = initServiceRecord(strServiceId,strServerIP);
			if(nCount<1)
			{
				throw new Exception("cluster db error");
			}
		}
		
	}

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void addService(String strServiceId,IActiveObject aoServiceObject)
	{
		if(strServiceId==null || strServiceId.length()==0 || aoServiceObject==null)
		{
			return;
		}

		ClusteredService obj=new ClusteredService();
		obj.setObjectId(strServiceId);
		obj.setObject(aoServiceObject);

		synchronized(alServiceObject)
		{
			alServiceObject.add(obj);
		}
	}

	public Return start()
	{
		strServerIP	=Utility.getLocalIp();
		if(strServerIP==null)
		{
			return Return.valueOf(-1,"Get IP Address Error");
		}
		try
		{
			initServiceRecord(strServerIP);
		}
		catch(Exception e)
		{
			logger.error("start:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		return super.start();
	}
	
	public void stop()
	{
		synchronized(alServiceObject)
		{
			try
			{
				for(int i=0;i<alServiceObject.size();i++)
				{
					IActiveObject aoModuleObject=((ClusteredService)alServiceObject.get(i)).getModuleObject();
					aoModuleObject.stop();
				}
			}catch(Exception e)
			{
				logger.error("stop:"+e.getMessage(),e);
			}
		}

		super.stop();
	}
	
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	/**
	 * 监视对应于ActiveObject的锁记录，并尝试获得锁，如果获得了锁，就启动对应的ActiveObject
	 */
	protected void threadProc()
	{
		while(bCanExit==false)
		{
			//检查是否对象集合为空
			int nSize=0;
			synchronized(alServiceObject)
			{
				nSize=alServiceObject.size();
			}
			if(nSize==0)
			{
				sleep(10);
				continue;
			}

			//获得一个处理模块对象
			long lNow=System.currentTimeMillis();
			int nCurIndex			=0;
			ClusteredService service	=null;
			synchronized(alServiceObject)
			{
				nIndex++;
				if(nIndex>=alServiceObject.size())
				{
					nIndex=0;
				}
				nCurIndex=nIndex;

				service=(ClusteredService)alServiceObject.get(nCurIndex);
				if(service.isLocked()==true)
				{
					sleep(10);
					continue;
				}
				//检查CheckTime，以避免一个ModuleObject被频繁处理
				if(lNow-service.getCheckTime()<nPulseSecond*1000)
				{
					sleep(10);
					continue;
				}
				service.setCheckTime(lNow);
				service.setLocked(true);
			}

			try
			{
				//开始处理ModuleObject
				if(service.getState()==0)
				{//未获得锁，尝试获得锁
					int nResult=tryLockServiceObject(service.getObjectId());
					if(nResult<0)
					{//错误
						sleep(10);
						continue;
					}
					else if(nResult==0)
					{//未获得锁
						sleep(10);
						continue;
					}
					else
					{//获得锁
						//启动ModuleObject
						try
						{
							beforeStartingObject(service.getObjectId());
							Return ret=service.getModuleObject().start();
							onObjectStarted(service.getObjectId(),ret);
							if(ret.getCode()!=0)
							{
								sleep(10);
								continue;
							}
						}
						catch(Exception e)
						{
							sleep(10);
							continue;
						}
						//修改状态
						service.setState(1);
					}
				}
				else
				{//获得锁，更新活动时间
					int nResult=updateActiveTime(service.getObjectId());
	
					if(nResult<0)
					{//错误
						sleep(10);
						continue;
					}
					else if(nResult==0)
					{//丢失锁
						//停止ModuleObject
						try
						{
							beforeStoppingObject(service.getObjectId());
							service.getModuleObject().stop();
							onObjectStopped(service.getObjectId());
						}
						catch(Exception e)
						{
							sleep(10);
							continue;
						}
						//修改状态
						service.setState(0);
						sleep(10);
						continue;
					}
					else
					{//更新活动时间成功
						sleep(10);
						continue;
					}
				}
			}
			catch(Exception e)
			{
			}
			finally
			{
				service.setLocked(false);
			}

			sleep(10);
			continue;
		}
	}

	private void beforeStoppingObject(String strObjectId)
	{
		if(logger.isDebugEnabled()){logger.debug("===Stopping Active service: "+strObjectId+" ========================");}		
	}
	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	private void beforeStartingObject(String strObjectId)
	{
		if(logger.isDebugEnabled()){logger.debug("===Starting Active service: "+strObjectId+" ========================");		}
	}
	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	protected void onObjectStarted(String strObjectId,Return ret)
	{
		if(logger.isDebugEnabled()){logger.debug("===Active service: "+strObjectId+" started: "+ret.getText());}
	}
	protected void onObjectStopped(String strObjectId)
	{
		if(logger.isDebugEnabled()){logger.debug("===stop active service: "+strObjectId+": "+strObjectId+"========================");}
	}
	protected void onError(String strErrorInfo)
	{
		if(logger.isDebugEnabled()){logger.debug("=== caught error: "+strErrorInfo+"===========");}
	}

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ClusterController()
	{
		super();

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		strServerIP		="";
		alServiceObject		=new ArrayList<ClusteredService>();
		nMaxDeadSecond	=5;
		nPulseSecond	=2;
		
		clDataEngine		=null;
		nIndex			=0;
	}

}
