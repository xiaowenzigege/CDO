/**
 * www.cdoforum.com 2007版权所有
 * 
 */

package com.cdoframework.cdolib.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cdo.util.constants.Constants;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.util.JsonUtil;
import com.cdoframework.cdolib.web.util.CacheUtil;

/**
 * woyo 公共接口
 * 
 */
public abstract class CDOJSONComServlet extends HttpServlet
{
	static Logger log=Logger.getLogger(CDOJSONComServlet.class);

	// 静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	// 内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	protected ServletConfig servletConfig;

	// 属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	// 引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	// 内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	protected String getRequestParameter(HttpServletRequest request,String strName)
	{
		String strValue=request.getParameter(strName);
		if(strValue==null)
		{
			strValue=request.getHeader(strName);
		}

		return strValue;
	}

	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	// 接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		// 获取参数
		String strTransName=getRequestParameter(request,"strTransName");

		// 构造请求对象
		CDO cdoRequest=null;
		String strCDORequest=request.getParameter(Constants.CDO.HTTP_CDO_REQUEST);
		if(log.isDebugEnabled())
		{
			log.debug(" json request="+strCDORequest);
		}
		try
		{
			if(strCDORequest==null)
			{
				cdoRequest=new CDO();
			}
			else
			{
				cdoRequest=JsonUtil.jsonToCDO(strCDORequest);
			}
			cdoRequest.setStringValue("strTransName",strTransName);
		}
		catch(Exception e)
		{
			log.error(" error:"+e.getMessage(),e);
			outPut(response,400," Request Parameter Error :"+e.getMessage());		
			return;
		}

		// 执行事务
		CDO cdoResponse=new CDO();
		Return ret=null;
		try{
			ret=handleTrans(request,response,cdoRequest,cdoResponse);
		}catch (Throwable e)
		{			
			log.error(e.getMessage(), e); 		
			outPut(response,500," Service Internal Error :"+e.getMessage());
			return; 				
		}		
		if(ret==null)
		{						
			outPut(response,404," Request method not found:strTransName="+strTransName);
			return;
		}

		// 输出结果
		String strOutput="";
		CDO cdoOutput=new CDO();
		CDO cdoReturn=new CDO();
		try
		{			
			cdoReturn.setIntegerValue("nCode",ret.getCode());
			cdoReturn.setStringValue("strText",ret.getText());
			cdoReturn.setStringValue("strInfo",ret.getInfo());
			if(ret.getCode()==0)
			{
				cdoOutput.setIntegerValue("code",200);
				cdoOutput.setStringValue("message"," Request processing succeeded ");
				cdoOutput.setCDOValue("header",new CDO());
				cdoOutput.setCDOValue("result",cdoResponse);
			}else if(ret.getCode()==-1)
			{
				cdoOutput.setIntegerValue("code",410);
				cdoOutput.setStringValue("message"," Request processing failed: "+ret.getText()+" "+ret.getInfo());
				cdoOutput.setCDOValue("header",new CDO());
				cdoOutput.setCDOValue("result",cdoResponse);				
			}else
			{
				cdoOutput.setIntegerValue("code",420);
				cdoOutput.setStringValue("message"," Request processing failed: "+ret.getText()+" "+ret.getInfo());
				cdoOutput.setCDOValue("header",new CDO());
				cdoOutput.setCDOValue("result",cdoResponse);				
			}
			strOutput=cdoOutput.toJSON();
		}
		catch(Exception e)
		{
			log.error(" error:"+e.getMessage(),e);
		}

		response.setContentType("text/json;charset=utf-8");
		//处理cache
		CacheUtil.handleReqCache(request, response, ret);
		outputReponse(response, strOutput);
	}

	private void outPut(HttpServletResponse response,int httpCode,String message) throws IOException
	{
		String strOutput="";
		CDO cdoOutput=new CDO();
		try
		{							
			cdoOutput.setIntegerValue("code",httpCode);
			cdoOutput.setStringValue("message",message);
			cdoOutput.setCDOValue("header",new CDO());
			cdoOutput.setCDOValue("result",new CDO());

			strOutput=cdoOutput.toJSON();
		}
		catch(Exception e)
		{
			log.error("error:"+e.getMessage(),e);
		}	
		response.setContentType("text/json;charset=utf-8");
		outputReponse(response, strOutput);
	}
	
	/**
	 * 安全输出http响应
	 * @param response
	 * @param strOutput
	 */
	private void outputReponse(HttpServletResponse response, String strOutput) {
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.write(strOutput);
			out.flush();
		} catch (Exception e) {
			log.error("wirte response errr", e);
		}finally{
			if(out != null){
				out.close();		
			}
		}
	}
	
	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	// 事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	protected abstract Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,
					CDO cdoResponse);

	// 构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CDOJSONComServlet()
	{
		// 请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
	}
}
