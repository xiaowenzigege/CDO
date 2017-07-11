package com.cdo.util.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.cdo.util.constants.Constants;

public class BytesStream {

	public static byte[] readStream(InputStream in) throws IOException{
		try{
			ByteArrayOutputStream out=new ByteArrayOutputStream();                  
	        byte[] buf = new byte[Constants.Byte.defaultSize];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        return out.toByteArray();
		}catch(Exception ex){
			throw new IOException(ex.getMessage(), ex);
		}finally{
		   	if(in!=null){try{ in.close();}catch(Exception ex){}}	
		}
	}
}
