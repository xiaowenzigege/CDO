package com.cdo.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author KenelLiu
 *
 */
public class URLUtil{
  private static int TIME_OUT =30*1000;

  private static String reqParams(Map<String, String> params){
    StringBuffer rtn = new StringBuffer();
    int index=0;
    for (Map.Entry<String,String> entry : params.entrySet()) {
    	if(index>0)
    		rtn.append("&");
    	try {
			rtn.append(entry.getKey() + "="+URLEncoder.encode(entry.getValue(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			//不支持UTF-8 编码,则使用默认值
			rtn.append(entry.getKey() + "="+entry.getValue());
		}
    	index++;
    }
    return rtn.toString();
  }

  private static String read(InputStream is) throws IOException {
    StringBuffer rtn = new StringBuffer();
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(is,"UTF-8"));
      String line;
      while ((line = in.readLine()) != null){
        rtn.append(line);
      }
      return rtn.toString();
    }catch(Exception ex){
    	throw new IOException(ex);
  	}finally {
      if (in != null) {try{in.close();}catch(Exception ex){}}
    }   
  }

  public static String doPost(String url, Map<String, String> params) throws IOException {
    URL urlConnetion = new URL(url);
    URLConnection conn = null;
    String rtn = null;
    PrintWriter pw = null;
    try {
      conn = urlConnetion.openConnection();
      conn.setReadTimeout(TIME_OUT);      
      conn.setDoOutput(true);
      conn.setDoInput(true);
      pw = new PrintWriter(conn.getOutputStream(), true);
      pw.print(reqParams(params));
      pw.flush();      
      rtn = read(conn.getInputStream());      
      return rtn;
    }catch(Exception ex){
    	throw new IOException(ex);
  	}finally {
      if (pw != null) {try{pw.close();}catch(Exception ex){}}      
    }     
  }
}