/**
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/framework/RMIServer.java,v 1.1 2008/04/12 13:44:36 Frank Exp $
 * 
 * $Log: RMIServer.java,v $
 * Revision 1.1  2008/04/12 13:44:36  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:06  Frank
 * *** empty log message ***
 *
 * Revision 1.4  2008/01/07 10:55:30  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/12/28 09:07:58  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/27 12:50:10  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/15 10:06:09  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/12/15 09:35:38  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/12/15 09:07:11  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/31 07:08:26  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/29 07:46:25  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/08 09:32:18  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2006/08/23 16:49:26  Frank
 * support existed port
 *
 * Revision 1.2  2006/08/22 02:35:09  linshihong
 * *** empty log message ***
 * 
 *
 */

package com.cdoframework.cdolib.framework;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;




/***********************************************************************************************************************
 * 在此添加该类的功能描述
 **********************************************************************************************************************/
public class RMIServer
{
	//	内部对象
	protected ArrayList alServiceName;
	protected boolean m_bIsRunning;
	protected String m_strHostName;

	//属性
	protected int m_nPort;
	public void setPort(int nPort)
	{
		m_nPort=nPort;
	}
	public int getPort()
	{
		return m_nPort;
	}
	
	//构造函数
	public RMIServer()
	{
		super();
		
		m_strHostName	=Utility.getHostName();
		m_bIsRunning	=false;
		alServiceName	=new ArrayList();
		
		m_nPort			=19000;
	}

	//方法
	//作为服务器端运行
	synchronized public Return start()
	{
		try
		{
			LocateRegistry.createRegistry(m_nPort);
		}
		catch(Exception e)
		{
			try
			{
				Registry registry=LocateRegistry.getRegistry(m_nPort);

				m_bIsRunning=true;
				return Return.OK;
			}
			catch(Exception ex)
			{
				OnException("createRegistry Exception: "+e.getMessage());
			}

			return Return.valueOf(-1,e.getMessage());
		}

		m_bIsRunning=true;
		return Return.OK;
	}

	synchronized public Return bindServiceObject(String strServiceName,Remote remoteServiceObject)
	{
		try
		{
			Registry registry=LocateRegistry.getRegistry(m_nPort);
			registry.rebind(strServiceName.toUpperCase(),remoteServiceObject);

			alServiceName.add(strServiceName);
			
			return Return.OK;
		}
		catch(Exception e)
		{
			OnException("bindServiceObject Exception: "+e.getMessage());

			return Return.valueOf(-1,e.getMessage());
		}
	}
	
	synchronized public void stop()
	{
		if(m_bIsRunning==false)
		{
			return;
		}

		for(int i=0;i<alServiceName.size();i++)
		{
			String strServiceName=(String)alServiceName.get(i);
			try
			{
				Naming.unbind("//"+m_strHostName+":"+m_nPort+"/"+strServiceName.toUpperCase());
			}
			catch(Exception e)
			{
				
			}
		}
		
		m_bIsRunning=false;

	}

	public void OnException(String strMessage)
	{
		
	}
}
