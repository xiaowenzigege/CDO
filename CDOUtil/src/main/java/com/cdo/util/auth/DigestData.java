package com.cdo.util.auth;
/**
 * 
 * @param credentail
 * credentail 值为
 * Authorization: Digest username="userName",   //用户名
                 realm="www.china-cos.com",          //域名
                 nonce="L4qfzASytyQJAC2B1Lvy2llPpj9R8Jd3", //服务器端的随机数一起带回
                 uri="/ACSCenter/Service/ACSControl",     // 必须跟请求行一致
                 qop=auth,                              //  /保护质量参数                              
                 nc=00000001,							 //认证的次数	
                 cnonce="c2dc5b32ad69187a",              //客户端随机数，用于对称校验                        
                 response="2f22e6d56dabb168702b8bb2d4e72453"  //最终摘要 
                 opaque="5ccc069c403ebaf9f0171e9517f40e41"
                        
                 
 * @param urlMethod
 */
public class DigestData {
	private String username;//用户名
	private String realm;//域名
	private String nonce;//服务器端的随机数
	private String uri;//请求uri
	private String qop;	//保护质量参数  

	private String nc;//认证的次数
	private String cnonce;//客户端随机数
	private String response;//最终摘要 
	private String opaque;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealm() {
		return realm;
	}
	public void setRealm(String realm) {
		this.realm = realm;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getQop() {
		return qop;
	}
	public void setQop(String qop) {
		this.qop = qop;
	}
	public String getNc() {
		return nc;
	}
	public void setNc(String nc) {
		this.nc = nc;
	}
	public String getCnonce() {
		return cnonce;
	}
	public void setCnonce(String cnonce) {
		this.cnonce = cnonce;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getOpaque() {
		return opaque;
	}
	public void setOpaque(String opaque) {
		this.opaque = opaque;
	}	

}
