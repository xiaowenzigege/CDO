package com.cdo.business.client;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

public interface IClient {
	public static final String DEFAULT_ZKID="default_zkId";
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse);
	
	public Return asyncHandleTrans(CDO cdoRequest,CDO cdoResponse);
	
	
}
