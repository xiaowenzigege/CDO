package com.cdo.util.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.cdo.util.codec.MD5;
import com.cdo.util.common.RandomUtil;


/**
 * 目前只提供支持[qop="auth" ]  消息摘要认证
 *  
 * @author KenelLiu
 *
 */
public class Credential {
	private static Logger logger=Logger.getLogger(Credential.class);
	//客服端传递过来的待验证的字符串,或者为  服务端生成www-authorization信息
	private String credentail;
	
	private String urlMethod;
	
	private DigestData digestData=new DigestData();
	
	/**
	 * 未认证通过时，生成认证消息给客服端
	 * @param realm
	 * @return
     * WWW-Authenticate: Digest
              realm="www.china-cos.com",
              qop="auth",
              nonce="dcd98b7102dd2f0e8b11d0f600bfb0c093",
          	 opaque="5ccc069c403ebaf9f0171e9517f40e41"
     */ 	
	public static String genUnauthorized(String realm){		 
    	String nonce=System.currentTimeMillis()+" "+MD5.encode(System.currentTimeMillis()+":"+RandomUtil.getHexRandom(15));
    	nonce=Base64.encodeBase64String(nonce.getBytes());    	
    	return "Digest realm=\""+realm+"\",qop=\"auth\",nonce=\""+nonce+"\","
    			+"opaque=\""+RandomUtil.getHexRandom(32)+"\"";
	}
	
	/**
	 * 
	 * @param credentail  客户端传过来的摘要认证信息
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
    public Credential(String credentail,String urlMethod){
    	this.credentail=credentail;
    	this.urlMethod=urlMethod;
    	if(credentail!=null&& !credentail.trim().equals("")){
    		initDigestData(credentail.split(","));     		
    	}
    }
	/**
	 * 
	 * @param userName  用户名
	 * @param password  密码
	 * @return
	 */
    public  boolean checkAuth(String userName,String password){
    	if(credentail==null || credentail.trim().equals(""))
    		return false;
    	if(urlMethod==null || urlMethod.trim().equals("") )
    		return false;    	    	    	     	
    	try{
    		if(digestData.getResponse().equalsIgnoreCase(authorMd5(userName,password)))
    			return true;
    	}catch(Exception ex){    		
    		  logger.error("checkAuth："+ex.getMessage(),ex);
    	}    	
    	return false;
    } 
    
    /**
     *  String A1=user+":"+digestData.getRealm()+":" +password;  
        String HA1 = MD5.encode(A1);    
     * @param HA1  
     * @return
     */
    public  boolean checkAuth(String HA1){
    	if(credentail==null || credentail.trim().equals(""))
    		return false;
    	if(urlMethod==null || urlMethod.trim().equals("") )
    		return false;    	    	
    	try{
    		if(digestData.getResponse().equalsIgnoreCase(authorMd5(HA1)))
    			return true;
    	}catch(Exception ex){    		
    		  logger.error("checkAuth："+ex.getMessage(),ex);
    	}    	
    	return false;
    } 
    
    public DigestData getDigestData(){
    	return digestData;
    }
    private  void initDigestData(String[] resourceStr){
    	
        String prefix="Digest ";
        for (String str : resourceStr){
        	str=str.trim();        	
        	if(str.startsWith("username") || str.startsWith(prefix+"username") ){        	
	            digestData.setUsername(getValueByName(str));		
        	}else if(str.startsWith("realm")|| str.startsWith(prefix+"realm")){       
        		digestData.setRealm(getValueByName(str));
        	}else if(str.startsWith("nonce")|| str.startsWith(prefix+"nonce")){        		
        		digestData.setNonce(getValueByName(str));
        	}else if(str.startsWith("uri")|| str.startsWith(prefix+"uri")){
        		digestData.setUri(getValueByName(str));
        	}else if(str.startsWith("response")|| str.startsWith(prefix+"response")){
        		digestData.setResponse(getValueByName(str));
        	}else if(str.startsWith("opaque")|| str.startsWith(prefix+"opaque")){        		
        		digestData.setOpaque(getValueByName(str));
        	}else if(str.startsWith("qop")|| str.startsWith(prefix+"qop")){        		
        		digestData.setQop(getValueByName(str));
        	}else if(str.startsWith("nc")|| str.startsWith(prefix+"nc")){        		
        		digestData.setNc(getValueByName(str));
        	}else if(str.startsWith("cnonce")|| str.startsWith(prefix+"cnonce")){        		
        		digestData.setCnonce(getValueByName(str));
        	}        	       
        }
        
    }
    
    private  String getValueByName(String resourceStr){
    	if(resourceStr.contains("\"")){
    		return resourceStr.substring(resourceStr.indexOf("\"") + 1,resourceStr.lastIndexOf("\""));
    	}else{
    		return resourceStr.substring(resourceStr.indexOf("=") + 1);
    	}    	
    }    
        
    private  String authorMd5(String user,String password) throws Exception
    {
    	//String A1=user+":"+credentailMap.get("realm")+":" +password;   
    	String A1=user+":"+digestData.getRealm()+":" +password;  
        String HA1 = MD5.encode(A1);        
        return authorMd5(HA1);
    }  
   
    private  String authorMd5(String HA1) throws Exception{     
        //当qop="auth" HA2=MD5(A2)=MD5(method:digestURI) 
       // String A2=this.urlMethod+":" +credentailMap.get("uri");
    	String A2=this.urlMethod+":"+digestData.getUri();
        String HA2 = MD5.encode(A2);      
        //HA3=MD5(HA1:nonce:nc:cnonce:qop:HA2)
       // String A3=HA1+":"+credentailMap.get("nonce") + ":"+credentailMap.get("nc")+":"+credentailMap.get("cnonce")+":"+credentailMap.get("qop")+":"+HA2;  
        String A3=HA1+":"+digestData.getNonce() + ":"+digestData.getNc()+":"+digestData.getCnonce()+":"+digestData.getQop()+":"+HA2; 
        String HA3 = MD5.encode(A3);        
        if(logger.isDebugEnabled())
        	logger.debug("\r\n Credential server response:" + HA3+",\r\n client response:"+digestData.getResponse());
        return HA3;
    } 	

 
}
