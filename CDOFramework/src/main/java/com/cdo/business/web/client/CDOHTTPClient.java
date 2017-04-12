package com.cdo.business.web.client;

import com.cdo.util.bean.CDOHTTPResponse;
import com.cdo.util.bean.OutStreamCDO;
import com.cdo.util.http.HttpClient;
import com.cdoframework.cdolib.data.cdo.CDO;

class CDOHTTPClient extends HttpClient {
	private CDO cdoResponse;

	public CDOHTTPClient(String url) {
		super(url);
	}
	public CDO getCdoResponse() {
		return cdoResponse;
	}

	public void setCdoResponse(CDO cdoResponse) {
		this.cdoResponse = cdoResponse;
	} 
	
	public CDOHTTPResponse execute(){	
		HTTPResponse handler=new HTTPResponse();
		if(cdoResponse!=null && cdoResponse instanceof OutStreamCDO){
			OutStreamCDO streamCDO=(OutStreamCDO)cdoResponse;
			if(streamCDO.getOutputStream()!=null){
				handler.setOutputStream(streamCDO.getOutputStream());
				handler.setAutoCloseStream(streamCDO.isAutoCloseStream());
			}			
		}
		return handleResponse(handler);
	}
}
