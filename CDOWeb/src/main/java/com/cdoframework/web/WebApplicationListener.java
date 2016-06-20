package com.cdoframework.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdoframework.cdolib.base.Return;

public class WebApplicationListener implements ServletContextListener,ServletRequestListener
{
	private static final Logger logger = Logger.getLogger(WebApplicationListener.class);
	public void contextInitialized(ServletContextEvent arg0)
	{//web start business		
		Return ret = Return.OK;
		BusinessService app = BusinessService.getInstance();		
		try
		{
			ret = app.start();
		}catch(Throwable e){
			ret	=Return.valueOf(-1,e.getLocalizedMessage());
			logger.error(e.getMessage(),e);
		}
		if(ret.getCode()!=0)
		{
			logger.fatal(ret.getText());
			logger.fatal("||*****************************************||\r\n||*****************************************||\r\n||  started faild and will exit            ||\r\n||*****************************************||\r\n||*****************************************||");
			System.exit(-1);
			return;
		}
		if(logger.isInfoEnabled()){
			logger.info("business service started-----------------");
		}
		
	}

	public void contextDestroyed(ServletContextEvent arg0)
	{
		BusinessService app = BusinessService.getInstance();	
		if(app.isRunning()==false)
		{
			return;
		}		
		app.stop();
		if(logger.isInfoEnabled()){
			logger.info(" business service stopped-----------------");				
		}
	}

	public void requestInitialized(ServletRequestEvent arg0)
	{
		HttpServletRequest request=(HttpServletRequest)arg0.getServletRequest();		
		String strClientIP=request.getRemoteAddr();
		String strURL=request.getRequestURI();
		if(request.getQueryString()!=null)
		{
			strURL+="?"+request.getQueryString();
		}
		if(logger.isInfoEnabled()){
			logger.info(strClientIP+": "+strURL);
		}
		
	}
	
	public void requestDestroyed(ServletRequestEvent arg0)
	{
		
	}

}
