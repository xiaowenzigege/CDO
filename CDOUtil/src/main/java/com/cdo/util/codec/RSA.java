package com.cdo.util.codec;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import com.cdo.util.exception.EncryptException;

/**
 * 
 * @author KenelLiu
 *
 * 1 使用SUN RSA 公私钥对
 * 	  generateKeyPair (至少是512位   数据量太大,可使用Bouncy来处理)
 * 
 * 2 使用 第三方Bouncy 可指定长度生成 公私钥对
 *    generateKeyPairBouncy
 */
public class RSA {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
            
    /**
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decodeBase64(key);
    }
    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.encodeBase64String(key);
    }
    
    /**  
     *用私钥对信息生成数字签名
     * @param data
     *            加密数据 
     * @param privateKey
     *            私钥 
     *
     * @return String 返回签名字符串
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥   
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象   
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象   
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名   
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }
    
    /**
     * 验证 校验数字签名     
     * @param data
     *            加密数据 
     * @param publicKey
     *            公钥 
     * @param sign
     *            数字签名 
     * @return 校验成功返回true 失败返回false 
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        
        // 解密由base64编码的公钥   
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象   
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象   
        PublicKey pubKey=keyFactory.generatePublic(keySpec);   
        
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常   
        return signature.verify(decryptBASE64(sign));
    }    

    /**
     * 
     * 用私钥解密
     *
     * @param data 加密的字节数组
     * @param privateKey 私钥
     * @return byte[] 解密后的字节数组
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
          return codecByPrivateKey(data, privateKey, Cipher.DECRYPT_MODE);
    }   

    /** 
     * 用私钥加密 
     *
     * @param data 加密前字节数组
     * @param privateKey 私钥
     * @return 加密后的字节数组
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        return codecByPrivateKey(data, privateKey, Cipher.ENCRYPT_MODE);
    }  
    
    /**
     * 
     * @param data
     * @param privateKey
     * @param mode  加密 或解密
     * @return
     * @throws Exception
     */
    private static byte[] codecByPrivateKey(byte[] data, String strPrivateKey,int mode) throws Exception{
        // 对密钥解密   
        byte[] keyBytes = decryptBASE64(strPrivateKey);        
        // 取得私钥   
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密 或解密          
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(mode, privateKey);
        return cipher.doFinal(data);
    }
    
    /** 
     * 用公钥解密 
     *
     * @param data 加密后的字节数组
     * @param key 公钥
     * @return 返回解密后的字节数组
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        return codecByPublicKey(data, publicKey, Cipher.DECRYPT_MODE);
    }     
    /** 
     * 用公钥加密 
     *
     * @param data 加密前字节数组
     * @param publicKey 公钥
     * @return 加密后的字节数组
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        return codecByPublicKey(data, publicKey, Cipher.ENCRYPT_MODE);
    }
    /**
     * 
     * @param data
     * @param strPublicKey
     * @param mode
     * @return
     * @throws Exception
     */
    private static byte[] codecByPublicKey(byte[] data, String strPublicKey,int mode) throws Exception{
        // 对密钥解密   
        byte[] keyBytes = decryptBASE64(strPublicKey);
        // 取得公钥   
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);     
        // 对数据解密   或解密 
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(mode, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 对私钥进行64加密
     * 可选择其他加密方方式  
     * @param keyMap rsa信息
     * @return 私钥字符串
     * @throws Exception
     */
    public static String getPrivateKey(KeyPair  keyPair)
            throws Exception {        
        return encryptBASE64(keyPair.getPrivate().getEncoded());
    }

    /** 
     *  对公钥 进行64位加密 
     *可选择其他加密方方式 
     * @param keyMap rsa信息
     * @return 公钥字符串
     * @throws Exception
     */
    public static String getPublicKey(KeyPair  keyPair)
            throws Exception {
        return encryptBASE64(keyPair.getPublic().getEncoded());
    }

    /** 
     * 初始化密钥 
     * 
     * @return rsa密钥信息
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(512);
        return keyPairGen.generateKeyPair();       
    } 
    
    //---------------------------RSA bouncycastle 指定长度生成公私钥对----------------------//
    
    /**
     * 　　*      随机生成密钥对
     * 　　* @return KeyPair
     * 　　* @throws EncryptException
     */
    
    public static KeyPair generateKeyPairBouncy() throws EncryptException{
    	return generateKeyPairBouncy(80);
    }
    
    public static KeyPair generateKeyPairBouncy(int size) throws EncryptException {
        try {
        	if(size<=0){
        		size=80;
        	}
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM,
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());          
            keyPairGen.initialize(size, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();          
            return keyPair;
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }
    /**
     * 　　* 获取公钥
     * 　　* @param modulus
     * 　　* @param publicExponent
     * 　　* @return RSAPublicKey
     * 　　* @throws EncryptException
     */
    public static RSAPublicKey getRSAPublicKeyBouncy(byte[] modulus, byte[] publicExponent) throws EncryptException {    
        try {
        	KeyFactory keyFac = KeyFactory.getInstance(KEY_ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
        	RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        	return (RSAPublicKey)keyFac.generatePublic(pubKeySpec);
        } catch (NoSuchAlgorithmException ex) {
            throw new EncryptException(ex.getMessage());
        }catch (InvalidKeySpecException ex) {
            throw new EncryptException(ex.getMessage());
        }
    }    
    
    /**
     * 　　* 获取私钥
     * 　　* @param modulus
     * 　　* @param privateExponent
     * 　　* @return RSAPrivateKey
     * 　　* @throws EncryptException
     */
    public static RSAPrivateKey getRSAPrivateKeyBouncy(byte[] modulus, byte[] privateExponent) throws EncryptException {        
        try {
        	KeyFactory  keyFac = KeyFactory.getInstance(KEY_ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
        	RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        	return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (NoSuchAlgorithmException ex) {
            throw new EncryptException(ex.getMessage());
        }catch (InvalidKeySpecException ex) {
            throw new EncryptException(ex.getMessage());
        }
    }
    /**
     * 　　* 加密
     * 　　* @param key 加密的密钥
     * 　　* @param data 待加密的明文数据
     * 　　* @return 加密后的数据
     * 　　* @throws EncryptException
     */
    public static byte[] encryptBouncy(Key key, byte[] data) throws EncryptException {
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            int blockSize = cipher.getBlockSize();//获得加密块大小，如：加密前数据为128个byte，而key_size=1024 加密块大小为127 byte,加密后为128个byte;因此共有2个加密块，第一个127 byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);//获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                i++;
            }
            return raw;
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }
 
    /**
     * 　　* 解密
     * 与加密 对应 encryptBouncy,若加密使用公钥,则解密使用私钥
     * 　　* @param key 解密的密钥    
     * 　　* @param raw 已经加密的数据
     * 　　* @return 解密后的明文
     * 　　* @throws EncryptException
     */
    public static byte[] decryptBouncy(Key key, byte[] raw) throws EncryptException {
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, key);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }
 
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0xFF);
            if (stmp.length() == 1) {
                hs += "0" + stmp;
            } else {
                hs += stmp;
            }
        }      
        return hs.toUpperCase();
    }
 
 
    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];            
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }        
}
