package com.cdo.business.client;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

public interface IClient {
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse);
}
