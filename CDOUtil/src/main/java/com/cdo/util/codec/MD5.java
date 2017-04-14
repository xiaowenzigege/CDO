package com.cdo.util.codec;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5 {
	private static Logger logger=Logger.getLogger(MD5.class);
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	
	//--------------字符串 MD5-----------------------//
	private static String byteStringToHex(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHex(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHex(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String encode(String str) {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteStringToHex(md.digest(str.getBytes()));
		} catch (Exception ex) {
			logger.error("orgin text="+str+";error message="+ex.getMessage(),ex);
		}
		return resultString;
	}


	//--------------文件摘要 MD5-----------------------// 
	private static char fileHexDigits[] = { '0', '1', '2', '3', '4', '5', 
		    '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private static String byteFileToHex(byte[] tmp) {
		String s;
		char str[] = new char[16 * 2];
		int k = 0;
		for (int i = 0; i < 16; i++) {
			byte byte0 = tmp[i];
			str[k++] = fileHexDigits[byte0 >>> 4 & 0xf];
			str[k++] = fileHexDigits[byte0 & 0xf];
		}
		s = new String(str).toUpperCase();
		return s;
	}	
	
	public static String getFileMD5(InputStream stream) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[2048];
			int length = -1;			
			while ((length = stream.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			byte[] b = md.digest();						
			return byteFileToHex(b);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				stream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
