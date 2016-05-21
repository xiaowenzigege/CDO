package com.cdo.util.business;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

public interface IBusinessClient {
	
	public String getStrUrl();

	public void setStrUrl(String strUrl);
	
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse);
}
