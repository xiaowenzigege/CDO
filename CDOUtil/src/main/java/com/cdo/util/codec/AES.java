package com.cdo.util.codec;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.cdo.util.exception.EncryptException;
import com.sun.crypto.provider.AESKeyGenerator;

public class AES {
	/**
	 * 密钥算法
	 */
	public static final String KEY_ALGORITHM = "AES";

	/**
	 * 加密/解密算法 / 工作模式 / 填充方式 
	 * Java 6支持PKCS5Padding填充方式 
	 * Bouncy Castle支持PKCS7Padding填充方式
	 */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 解密
	 * 
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key)  throws GeneralSecurityException {
		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为解密模式		
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * 
	 * @param data 待加密数据
	 * @param key 密钥
	 * @return byte[] 加密数据
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws GeneralSecurityException{

		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
		
	}

	
	public static byte[] initKey()  throws GeneralSecurityException{
		return initKey(128);
	}
	/**
	 * 生成密钥 <br>
	 * 
	 * @return byte[] 二进制密钥
	 * @throws Exception
	 */
	public static byte[] initKey(int size)  throws GeneralSecurityException {
		// 实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM,"BC");
		/*
		 * AES 要求密钥长度为 128位、192位或 256位
		 */
		kg.init(size);

		// 生成秘密密钥
		SecretKey secretKey = kg.generateKey();

		// 获得密钥的二进制编码形式
		return secretKey.getEncoded();
	}
}
