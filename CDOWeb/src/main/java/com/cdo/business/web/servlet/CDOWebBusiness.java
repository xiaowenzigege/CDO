package com.cdo.business.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdo.business.BusinessService;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.web.CDOServlet;

public class CDOWebBusiness extends CDOServlet
{
	private static final long serialVersionUID = 355127761185942169L;
	private BusinessService businessService=BusinessService.getInstance();		
	
	
	protected Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,CDO cdoResponse)
	{
		return businessService.handleTrans(cdoRequest,cdoResponse);
	}
}
