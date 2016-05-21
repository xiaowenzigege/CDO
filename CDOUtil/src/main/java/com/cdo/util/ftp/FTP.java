package com.cdo.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.cdo.util.bean.FtpInfo;
import com.cdo.util.exception.FtpException;
/**
 * 
 * @author KenelLiu
 *
 */
public class FTP implements IFTP {
	private static Logger logger=Logger.getLogger(FTP.class); 
	private static String LOCAL_CHARSET =System.getProperty("file.encoding");
	private FtpInfo ftpInfo;	
    private FTPClient ftpClient;
	
	public FTP(FtpInfo ftpInfo){
		this.ftpInfo=ftpInfo;
	}
	
	
	public void open() throws FtpException {
        try {
            ftpClient = new FTPClient();  
            ftpClient.connect(ftpInfo.getHost(),ftpInfo.getPort());
            if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            	if (ftpClient.login(ftpInfo.getUser(), ftpInfo.getPassword())) {
            		if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))){
            			LOCAL_CHARSET = "UTF-8";
            		}            	
            	}            
            }
            ftpClient.setControlEncoding(LOCAL_CHARSET);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            ftpClient.setBufferSize(1024);
        } catch (IOException ex) {            
            throw new FtpException(ex);
        }
	}

	public boolean upload(String srcFile, String dstFile) throws FtpException {
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File(srcFile));
            String[] dirs=dstFile.split("/");
            String parentDir="/";
            for(int i=0;i<dirs.length;i++){
            	if(i==dirs.length-1)
            		break;
            	if(dirs[i]==null || dirs[i].equals("")){
            		continue;
            	}
            	ftpClient.makeDirectory(dirs[i]);
            	parentDir=parentDir+dirs[i]+"/";
            	ftpClient.changeWorkingDirectory(dirs[i]);
            }         
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);            
            boolean isSuccess =ftpClient.storeFile(dstFile, is);            
            if(isSuccess){
            	logger.info("upload success!");
            }else{
            	logger.error("upload fail!");
            }
            return isSuccess;
        } catch (Exception ex) {
            throw new FtpException(ex);
        } finally{
        	if(is!=null)try{is.close();}catch(Exception ex){};             	         
        }		
	}

	public boolean download(String srcFile, String dstFile) throws FtpException {			
	        FileOutputStream os = null;
	        try {	    	        	
	            os = new FileOutputStream(dstFile);	        
	            boolean isSuccess=ftpClient.retrieveFile(srcFile, os); 
	            if(isSuccess){
	            	logger.info("download success");
	            }else {
	            	logger.warn("download fail!");
				}
	            return isSuccess;
	        } catch (Exception ex) {
	            throw new FtpException(ex);
	        } finally{	        	
	        	if(os!=null)try{os.close();}catch(Exception ex){};	        	          
	        }	
		
	}

	public void close() throws FtpException {		
		try {
			ftpClient.disconnect();
		}catch(Exception ex){
			 throw new FtpException(ex);
		}
	}
	
	public static void main(String[] args ){
		FtpInfo ftpInfo=new FtpInfo("ftptest","ftptest","10.27.122.249",21);				
		try {
			System.out.println(FTPUtil.FTPUpload(ftpInfo, "d:/a/b/c/d.txt","/cc/dd/123.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
