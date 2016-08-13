package com.cdo.business.web.client;

import com.cdo.business.client.IClient;
/** 
 * 设置请求url
 * 
 **/
public interface IWebClient extends IClient{
	
	
	public String getUrl();

	public void setUrl(String url);

}
