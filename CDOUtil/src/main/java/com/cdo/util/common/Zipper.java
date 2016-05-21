package com.cdo.util.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import com.cdo.util.constants.Constants;

public class Zipper {
	public static final int BUFFER_SIZE = Constants.Byte.defaultSize;
    public static final String ZIP_ENTRY_NAME = "zipped_object";
    public static final Logger log=Logger.getLogger(Zipper.class);

    /**
     * 
     * @param src 源二进制数组
     * @return    压缩后字节数组
     * @throws IOException
     */
    public static byte[] zipBytes(byte[] src) throws IOException {
    	InputStream bis = new ByteArrayInputStream(src);
        return zipStream(bis).toByteArray();
    }    
    /**
     *  
     * @param in 源输入流
     * @return   压缩后字节数组
     * @throws IOException
     */
    public static byte[] zipSteam2Bytes(InputStream in) throws IOException {    	
        return zipStream(in).toByteArray();
    }      
    /**
     * 
     * @param bis 输入流
     * @return    压缩流
     * @throws IOException
     */
    public static ByteArrayOutputStream zipStream(InputStream bis) throws IOException {
        ByteArrayOutputStream zbos=null;
        ZipOutputStream zout=null;
        long size=0;
        try{
	        zbos=new ByteArrayOutputStream();
	        zout = new ZipOutputStream(zbos);
	        ZipEntry entry=new ZipEntry(ZIP_ENTRY_NAME);
	        zout.putNextEntry(entry);
	        byte[] buf = new byte[BUFFER_SIZE];
	        int len;
	        while ((len = bis.read(buf)) > 0) {
	        	size=len+size;
	            zout.write(buf, 0, len);
	        }
	        entry.setMethod(ZipEntry.DEFLATED);
	        entry.setCrc(1024);
	        entry.setCompressedSize(zbos.size());
	        entry.setSize(zbos.size());
	        zout.closeEntry();
        }catch(Exception ex){
        	throw new IOException(ex.getMessage(), ex);
        }finally{        	
        	if(bis!=null){try{ bis.close();}catch(Exception ex){}}
        	if(zbos!=null){try{ zbos.close();}catch(Exception ex){}}
        	if(zout!=null){try{ zout.close();}catch(Exception ex){}}
        }
        if(log.isDebugEnabled()){
            log.debug("before zipped object size="+size);
        	log.debug("after zipped object size=" + zbos.size());
        }
        return zbos;
    }    


    /**
     * 
     * @param src 压缩流
     * @return    解压流
     * @throws IOException
     */
    public static ByteArrayOutputStream unzipOutputStream(ByteArrayOutputStream zipStream) throws IOException {    	
        return unzipBytes2Stream(zipStream.toByteArray());
    }
    /**
     * 
     * @param src 源压缩字节数组
     * @return    解压后字节数组
     * @throws IOException
     */
    public static byte[] unzipBytes(byte[] src) throws IOException {    
        return unzipBytes2Stream(src).toByteArray();
    }

/**
 *     
 * @param zipStream  源压缩字节数组
 * @return          解压流
 * @throws IOException
 */
   public static ByteArrayOutputStream unzipBytes2Stream(byte[] zipSrc) throws IOException {
        ZipInputStream zin=null;
        ByteArrayOutputStream bot=null;
        InputStream zipStream =null;
        try{
	        byte[] buf = new byte[BUFFER_SIZE];
	        int len = 0;
	        zipStream= new ByteArrayInputStream(zipSrc);
	        zin = new ZipInputStream(zipStream);
	        bot = new ByteArrayOutputStream();
	        ZipEntry entry = zin.getNextEntry();
	        if (entry != null) {
	            while ((len = zin.read(buf)) > 0) {	            	
	                bot.write(buf, 0, len);
	            }
	        }
	    }catch(Exception ex){
	    	throw new IOException(ex.getMessage(), ex);
	    }finally{        	
	    	if(zin!=null){try{ zin.close();}catch(Exception ex){}}	 
	    	if(bot!=null){try{ bot.close();}catch(Exception ex){}}	
	    }
        if(log.isDebugEnabled()){
        	log.debug("before unzipped object size="+zipSrc.length); 
        	log.debug("after unzipped object size="+bot.toByteArray().length);
        }
      return bot;
  }   
   
   public static ByteArrayOutputStream unzipStream(InputStream zipStream) throws IOException {
       ZipInputStream zin=null;
       ByteArrayOutputStream bot=null;
       long size=0;
       try{
	        byte[] buf = new byte[BUFFER_SIZE];
	        int len = 0;	       
	        zin = new ZipInputStream(zipStream);
	        bot = new ByteArrayOutputStream();
	        ZipEntry entry = zin.getNextEntry();
	        if (entry != null) {
	        	size=size+entry.getSize(); //获取不到压缩的数据尺寸大小
	            while ((len = zin.read(buf)) > 0) {	            	
	                bot.write(buf, 0, len);
	            }
	        }
	    }catch(Exception ex){
	    	throw new IOException(ex.getMessage(), ex);
	    }finally{        	
	    	if(zin!=null){try{ zin.close();}catch(Exception ex){}}	 
	    	if(bot!=null){try{ bot.close();}catch(Exception ex){}}	
	    }
       if(log.isDebugEnabled()){
	       	log.debug("before unzipped object size="+size+",size -1 if not known"); 
	       	log.debug("after unzipped object size="+bot.toByteArray().length);
       }
     return bot;
 } 
 }
