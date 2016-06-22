package com.cdo.business.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.util.common.StringUtil;
import com.cdo.util.http.IPUtil;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.web.CDOJSONServlet;


public class CDOJSONBusiness extends CDOJSONServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 619753331713405559L;
	
	private BusinessService serviceBus=BusinessService.getInstance();
	private Logger log = Logger.getLogger(CDOJSONBusiness.class);
	
	protected Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,CDO cdoResponse)
	{
		String strIp = IPUtil.getIpAddr(request);
		if(StringUtil.isNotEmpty(strIp) && log.isInfoEnabled()){
			log.info("RemoteHost: " + strIp);
		}
		Return ret=serviceBus.handleTrans(cdoRequest,cdoResponse);
		return ret;
	}

}
