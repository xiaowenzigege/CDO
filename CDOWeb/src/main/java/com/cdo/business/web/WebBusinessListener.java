package com.cdo.business.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdoframework.cdolib.base.Return;

/**
 * 在web容器[resin,tomcat等]里  初始化配置，读取业务开发插件
 * 
 *客户端   通过接口 
 *  @see {@link com.cdo.business.web.servlet.CDOWebBusiness#doPost(HttpServletRequest, javax.servlet.http.HttpServletResponse)}    * 
 * 调用service服务
 *  * 
 * 即客户端 请求 http://ip:port/web项目名/handTrans.cdo,执行service服务
 * 请求,返回数据为 CDO封装,客户端请求 调用此接口即可
 * @see {@link com.cdo.business.web.client.WebHttpClient#handleTrans(com.cdoframework.cdolib.data.cdo.CDO, com.cdoframework.cdolib.data.cdo.CDO)}}
 * @see web.xml配置
 * @author KenelLiu
 *
 */
public class WebBusinessListener implements ServletContextListener,ServletRequestListener
{
	private static final Logger logger = Logger.getLogger(WebBusinessListener.class);
	
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
