package com.cdo.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
/**
 * 
 * @author KenelLiu
 *
 */
public class URLUtil{
  private static int TIME_OUT = 1000;

  public static String getParamsString(Map<String, String> params) {
    StringBuffer rtn = new StringBuffer();
    for (Map.Entry<String,String> entry : params.entrySet()) {
      rtn.append("&" +entry.getKey() + "=" +entry.getValue());
    }
    return rtn.toString().replaceFirst("&", "");
  }

  private static String read(InputStream is) throws IOException {
    StringBuffer rtn = new StringBuffer();
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(is));
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

  public static String doPost(String path, Map<String, String> params) throws IOException {
    path = checkPath(path);
    URL urlObject = new URL(path);
    URLConnection conn = null;
    String rtn = null;
    PrintWriter pw = null;
    try {
      conn = urlObject.openConnection();
      conn.setReadTimeout(TIME_OUT);
      conn.setDoOutput(true);
      conn.setDoInput(true);
      pw = new PrintWriter(conn.getOutputStream(), true);
      pw.print(getParamsString(params));
      pw.flush();      
      rtn = read(conn.getInputStream());
      return rtn;
    }catch(Exception ex){
    	throw new IOException(ex);
  	}finally {
      if (pw != null) {try{pw.close();}catch(Exception ex){}}
    }     
  }

  private static String checkPath(String path) {
    path = path.replaceAll("\\?$", "");
    path = path + "?";
    return path;
  }

}