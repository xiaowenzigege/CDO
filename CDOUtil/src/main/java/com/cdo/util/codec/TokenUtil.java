package com.cdo.util.codec;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeoutException;

import com.cdo.util.exception.EncryptException;

public class TokenUtil {
	private static String modulus="d4377df7de49df182da1";
	private static String publicExponent="10001";
	private static String primodulus="45039aeabd7f0c7d3231";
	
	/**
	 * 
	 * @param strId 在服务端获取到用户id明文，加密后给
	 * strId有16位，没有16位，前面补0
	 * @return
	 * @throws EncryptException
	 */
	public static String genEncryptToken(String strId) throws EncryptException{
			StringBuilder sb=new StringBuilder();			
			int length=16-strId.length();
			for(int i=0;i<length;i++){
				sb.append("0");
			}						
			strId=sb+strId+";"+(System.currentTimeMillis()/1000);
	        byte[] b_modulus = new BigInteger(modulus,16).toByteArray();       
	        byte[] b_publicExponent = new BigInteger(publicExponent,16).toByteArray();
	        RSAPublicKey recoveryPubKey = RSA.getRSAPublicKeyBouncy(b_modulus, b_publicExponent);	        
	        byte[] encrypt = RSA.encryptBouncy(recoveryPubKey,strId.getBytes());
	        String strtext=RSA.byte2hex(encrypt);
	        
	        //TODO 存储最新的token值,如果tokenMap.MaxSize=5000,后期需要做个LRU算法 
	        return strtext;
	}
	
	/**
	 * @param token  为 终端客服传过来的密文
	 * @return
	 * @throws EncryptException
	 * @throws TimeoutException 
	 */
	public static String genDecryptToken(String token) throws EncryptException, TimeoutException{
		String strId="";
		long start=0;		
		try{
	        byte[] priModBytes = new BigInteger(modulus,16).toByteArray();
	        byte[] priPriExpBytes = new BigInteger(primodulus,16).toByteArray();
	        RSAPrivateKey recoveryPriKey = RSA.getRSAPrivateKeyBouncy(priModBytes, priPriExpBytes);   
	        byte[] decoder=RSA.hex2byte(token);
	        byte[] decrypt = RSA.decryptBouncy(recoveryPriKey, decoder);
	        String decToken=new String(decrypt);
	        strId=decToken.split(";")[0];
	        start=Long.parseLong(decToken.split(";")[1]);
		}catch(EncryptException ex){
			throw new EncryptException("非法的token值,"+ex.getMessage());
		}
	   	 //则判断 token值 在有效时间里
	     if((System.currentTimeMillis()/1000-start)>(3600*12)){
	       	//超过了12小时 ，服务端未接受手机请求，需要手机端重新登录
	       	throw new TimeoutException("长时间未操作,系统需要重新登录");
	      }
	       //检查id 是否存在缓存 或 数据库中
	     return strId;
      
		
	} 

	
    public static void main(String[] args) throws Exception {
    	//"80f429dc90a0ce93ab29"; 80f429dc90a0ce93ab29
        KeyPair keyPair = RSA.generateKeyPairBouncy();
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println(" priKey:"+priKey);
        System.out.println(" pubKey:"+pubKey);
    	long strId=2013122911111300L;
    	String s=genEncryptToken(strId+"");
    	System.out.println(s+","+s.length());
    	System.out.println(genDecryptToken(s));
    }
}
