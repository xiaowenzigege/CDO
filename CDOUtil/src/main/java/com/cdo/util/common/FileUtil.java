package com.cdo.util.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.cdo.util.constants.Constants;

/**
 * 
 * @author KenelLiu
 *
 */
public class FileUtil {
	
	/**
	 * 
	 * @param file 文件
	 * @return     返回文件二进制
	 * @throws IOException
	 */
	public static byte[] readFile2Byte(File file) throws IOException{
		  FileInputStream fin=null;
		try{
	        ByteArrayOutputStream zbos=new ByteArrayOutputStream();
	        fin = new FileInputStream(file);
	        byte[] buf = new byte[Constants.Byte.defaultSize];
	        int len;
	        while ((len = fin.read(buf)) > 0) {
	        	zbos.write(buf, 0, len);
	        }
	        return zbos.toByteArray();
		}catch(Exception ex){			
			throw new IOException(ex.getMessage(),ex);
		}finally{
			if(fin!=null)try{fin.close();}catch(Exception ex){};
		}     
	}
	/**
	 * 
	 * @param file 按UTF-8字符集 读取文件内容  
	 * @return
	 * @throws IOException
	 */
	public static String readFile(File file) throws IOException{
		return readFile(file, Constants.Encoding.CHARSET_UTF8);
	}
	/**
	 * 按指定的字符集 读取文件内容  
	 *  转换成字符串 
	 * @param file 输入文件
	 * @param charset 字符
	 * @return
	 * @throws IOException 
	 */
	public static String readFile(File file,String strCharset) throws IOException{
		    FileInputStream fin=null;
		    FileChannel fc =null;
		    try{
			    fin = new FileInputStream(file);
			    fc = fin.getChannel();
		        Charset charset=Charset.forName(strCharset);	         
		        ByteBuffer buffer = ByteBuffer.allocate(Constants.Byte.defaultSize);
		        StringBuilder sb=new StringBuilder();		 
		        while(true){ 	    	  
		    	   	buffer.clear();
			        int r=fc.read(buffer);
			        if (r==-1) {
			            break;
			          }
			        buffer.flip();				        
			        sb.append(charset.decode(buffer).toString());
		        }
		       return sb.toString();
		    }catch(Exception ex){
		    	throw new IOException(ex.getMessage(),ex);
		    }finally{
		    	if(fc!=null)try{fc.close();}catch(Exception ex){};
		    	if(fin!=null)try{fin.close();}catch(Exception ex){};
		    }	        	     
	}
	
	/**
	 * 
	 * @param buf  文件二进制
	 * @param outFile 输出文件
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFile(byte[] buf,File outFile) throws IOException{
		  FileOutputStream fout=null;
		try{	      
	        fout = new FileOutputStream(outFile);
	        fout.write(buf);
	        fout.flush();
	        return true;
		}catch(Exception ex){			
			throw new IOException(ex.getMessage(),ex);
		}finally{
			if(fout!=null)try{fout.close();}catch(Exception ex){};
		}   
	}
	/**
	 * 
	 * @param strContent将内容以UTF-8编码 输出到文件
	 * @param outFile
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFile(String strContent,File outFile)throws IOException {
		return writeFile(strContent,outFile,Constants.Encoding.CHARSET_UTF8);
	}
	
	
	/**
	 * 将内容以指定编码 输出到文件.
	 * @param strContent 内容
	 * @param outFile 输出文件
	 * @param strCharset 字符
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFile(String strContent,File outFile,String strCharset) throws IOException {
		FileOutputStream os =null;
		FileChannel ch=null;
		try{
			os = new FileOutputStream(outFile);
			ch=os.getChannel();
			int maxSize=Constants.Byte.maxSize;
			ByteBuffer buffer=ByteBuffer.allocate(maxSize);;
			byte[] xmlByte=strContent.getBytes(strCharset);
			int len=xmlByte.length;
			int offset=0;			
			while(len>=0){	
				buffer.clear();
			    int length=len>maxSize?maxSize:len;
				buffer.put(xmlByte, offset, length);
				buffer.flip();
				ch.write(buffer);				
				offset=offset+maxSize;
				len=len-maxSize;			
			}
			os.flush();
			return true;
		}catch(Exception ex){
	    	throw new IOException(ex.getMessage(),ex);
	    }finally{
			if(ch!=null)try{ch.close();}catch(Exception ex){};	
			if(os!=null)try{os.close();}catch(Exception ex){};				
		}
	}
	/**
	 * copy 文件 
	 * @param inFile
	 * @param outFile
	 * @throws IOException
	 */
	public static void copyFile(File inFile,File outFile) throws IOException{
		FileInputStream fin=null;
		FileChannel fcin=null;
		FileOutputStream fout=null;
		FileChannel fcout=null;
		try{			
			 fin = new FileInputStream(inFile);
			 fout = new FileOutputStream(outFile);
		     fcin = fin.getChannel();
		     fcout = fout.getChannel();
		     ByteBuffer buffer = ByteBuffer.allocate(Constants.Byte.maxSize);
		     
		    while (true) {
		      buffer.clear();
		      int r = fcin.read(buffer);
		      if (r==-1) {
		        break;
		      }
		      buffer.flip();
		      fcout.write(buffer);
		    }
		}catch(Exception ex){
	    	throw new IOException(ex.getMessage(),ex);
	    }finally{
			if(fcin!=null)try{fcin.close();}catch(Exception ex){};	
			if(fin!=null)try{fin.close();}catch(Exception ex){};
			if(fcout!=null)try{fcout.close();}catch(Exception ex){};	
			if(fout!=null)try{fout.close();}catch(Exception ex){};	
		}
	}	
	/**
	 * 
	 * @param file 递归删除文件夹或文件 
	 */
   public static void deleteDir(File file){	  
		  if(file.isFile() || file.list().length == 0){
			  file.delete();
		  	}else{
		  		File[] files = file.listFiles();
		  		for(File f : files){
		  			deleteDir(f);//递归删除每一个文件
		  			f.delete();//删除该文件夹
		  		}
		  	}
		 }	
   
   /**
    * 
    * @param zipFile 压缩文件 格式为zip,jar
    * @param unZipDir 解压目录
    * @throws IOException
    */
   public static void unZipFile(String zipFile,String unZipDir) throws IOException{
	   
       ZipInputStream Zin=null;
       BufferedInputStream Bin=null;
       FileOutputStream out=null;
       BufferedOutputStream Bout=null;
       try{
    		 Zin=new ZipInputStream(new FileInputStream(zipFile));
    		 Bin=new BufferedInputStream(Zin);  
    		 String Parent=unZipDir; 
    		 //输出路径（文件夹目录）  
    		 File Fout=null;  
    		 ZipEntry entry;       
    		 while((entry = Zin.getNextEntry())!=null){  
    			 Fout=new File(Parent,entry.getName());  
    			 if(entry.isDirectory()){
    				 Fout.mkdirs();
    				 continue;
    			 }
    			 Fout=new File(Parent,entry.getName());
    			 if(!Fout.exists()){  
    				 (new File(Fout.getParent())).mkdirs();  
    			 }  
    			 out=new FileOutputStream(Fout);  
    			 Bout=new BufferedOutputStream(out);  
    			 int b;  
    			 while((b=Bin.read())!=-1){  
    				 Bout.write(b);  
    			 }  
    			 Bout.close();  
    			 out.close();      
    		 }  
       }catch(Exception ex){
	    	throw new IOException(ex.getMessage(),ex);
	    }finally{
			if(Zin!=null)try{Zin.close();}catch(Exception ex){};	
			if(Bin!=null)try{Bin.close();}catch(Exception ex){};
			if(out!=null)try{out.close();}catch(Exception ex){};	
			if(Bout!=null)try{Bout.close();}catch(Exception ex){};	
       }
  		
	}
	
   /**
    * 
    * @param dir 压缩目录
    * @param zipFile  输出压缩文件
    * @throws IOException
    */
   public static void zipFile(String dir,String zipFile) throws IOException {
		ZipOutputStream zos=null;
		InputStream fileStream=null; 
		try{
			File fileDir = new File(dir);
			zos = new ZipOutputStream(new FileOutputStream(zipFile));
			zos.setComment(fileDir.getName());
			zipFile(fileDir,fileStream,zos, "");		
		}catch(Exception ex){
	    	throw new IOException(ex.getMessage(),ex);
	    }finally{
			if(zos!=null)try{zos.close();}catch(Exception ex){};
			if(fileStream!=null)try{fileStream.close();}catch(Exception ex){};	
       }		
	}

	private static void zipFile(File dirFile,InputStream fileStream, ZipOutputStream zos, String dir) throws IOException {
		if (dirFile.isDirectory()) {
		    File[] files = dirFile.listFiles();
		    for (File file:files)
		        zipFile(file,fileStream,zos, dir + File.separator + dirFile.getName());
		} else {
		    String entryName = null;
		    if (!"".equals(dir))
		        entryName = dir +File.separator+ dirFile.getName();
		    else
		        entryName = dirFile.getName();
		    ZipEntry entry = new ZipEntry(entryName);
		    zos.putNextEntry(entry);
		    fileStream= new FileInputStream(dirFile);
		    int len = 0;
		    byte[] buf=new byte[1024];
		    while ((len = fileStream.read(buf)) != -1)
		        zos.write(buf,0,len);
		    fileStream.close();
		}
   }   

}
