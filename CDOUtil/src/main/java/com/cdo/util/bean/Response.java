package com.cdo.util.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.cdo.util.exception.ResponseException;

/**
 * 
 * @author KenelLiu
 *
 */
public class Response implements Serializable {

	private static final long serialVersionUID = -2137058860119989585L;

	protected int statusCode;
	//返回字符串
	protected String responseText;
	//返回二进制数组 
	protected byte[] responseBytes=new byte[0];
	protected Header[] allHeaders;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseText()  throws ResponseException{		
		return getResponseText(null);
	}
	
	public String getResponseText(String charset) throws ResponseException{
		if(responseBytes==null)
			return null;
		try{
			if(charset==null)
				responseText=new String(responseBytes); 
			else
				responseText=new String(responseBytes,charset);
			return responseText;	
		}catch(Exception ex){
			throw new ResponseException(ex.getMessage(),ex);
		}
	}
	
	public byte[] getResponseBytes() {
		return responseBytes;
	}

	public void setResponseBytes(byte[] responseBytes) {
		this.responseBytes = responseBytes;
	}
	
	
	public int hashCode(){
		try {
			return this.statusCode+getResponseText().hashCode()+this.allHeaders.hashCode();
		} catch (ResponseException e) {
			return this.statusCode+this.allHeaders.hashCode();
		}
	}
	
	public Header[] getAllHeaders() {
		return allHeaders;
	}
	public void setAllHeaders(Header[] allHeaders) {
		this.allHeaders = allHeaders;
	}
	
    public Header[] getHeaders(final String name) {
    	if(name==null|| this.allHeaders==null)
    		return new Header[0];
        List<Header> headersFound = null;
        for (int i = 0; i < this.allHeaders.length; i++) {
            final Header header =allHeaders[i];
            if (header.getName().equalsIgnoreCase(name)) {
                if (headersFound == null) {
                    headersFound = new ArrayList<Header>();
                }
                headersFound.add(header);
            }
        }
        return headersFound != null ? headersFound.toArray(new Header[headersFound.size()]) : new Header[0];
    }
    
    public Header getFirstHeader(final String name) {
    	if(name==null|| this.allHeaders==null)
    		return null;    	
        for (int i = 0; i < this.allHeaders.length; i++) {
            final Header header = this.allHeaders[i];
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        return null;
    }

    public Header getLastHeader(final String name) {  
    	if(name==null|| this.allHeaders==null)
    		return null;        	
        for (int i = this.allHeaders.length - 1; i >= 0; i--) {
            final Header header = this.allHeaders[i];
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        return null;
    }    
    
	public String toString(){
        StringBuilder sbBuilder=new StringBuilder();
        if( this.allHeaders!=null){
            for (int i = 0; i < this.allHeaders.length; i++) {
                final Header header =allHeaders[i];
                sbBuilder.append(header.getName()+"="+header.getValue()+" ");
            }        	
        }
		try {
			return "statusCode="+statusCode+",responseText="+getResponseText()+",header=["+sbBuilder+"]";
		} catch (Exception e) {
			return "statusCode="+statusCode+",responseText=发生异常:"+e.getMessage()+",header=["+sbBuilder+"]";
		}
	}		
}
