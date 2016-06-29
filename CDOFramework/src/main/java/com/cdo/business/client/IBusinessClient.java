package com.cdo.business.client;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

public interface IBusinessClient {
	
	/** 通过HttpCient httpUrl处理数据**/
	public String getUrl();

	public void setUrl(String url);
				
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse);
}
