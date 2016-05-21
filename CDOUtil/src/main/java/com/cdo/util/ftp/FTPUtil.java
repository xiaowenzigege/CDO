package com.cdo.util.ftp;

import java.io.File;
import java.io.IOException;

import com.cdo.util.bean.FtpInfo;
import com.cdo.util.exception.FtpException;
/**
 * 
 * @author KenelLiu
 *
 */
public class FTPUtil {
	/**
	 * 
	 * @param ftpInfo  ftp认证信息
	 * @param srcFile  上传文件
	 * @return 默认上传到 ftp服务器根路径
	 * @throws FtpException
	 */
	public static boolean FTPUpload(FtpInfo ftpInfo,String srcFile) throws FtpException{
		File file=new File(srcFile);		
		return FTPUpload(ftpInfo, srcFile, file.getName());
	}	
	/**
	 * 
	 * @param ftpInfo ftp认证信息
	 * @param srcFile 待上传文件
	 * @param dstFile 上传到ftp服务器  后的文件
	 * @return
	 * @throws FtpException
	 */
	public static boolean FTPUpload(FtpInfo ftpInfo,String srcFile,String dstFile) throws FtpException{
		IFTP ftp=new FTP(ftpInfo);
		return upload(ftp, srcFile, dstFile);
	}
	
	/**
	 * 
	 * @param ftpInfo sftp认证信息
	 * @param srcFile 上传文件 上传到ftp根路径
	 * @return  
	 * @throws FtpException
	 */
	public static boolean SFTPUpload(FtpInfo ftpInfo,String srcFile) throws FtpException{
		File file=new File(srcFile);		
		return SFTPUpload(ftpInfo, srcFile, file.getName());
	}	
	/**
	 * 
	 * @param ftpInfo sftp认证信息
	 * @param srcFile 待上传文件
	 * @param dstFile 上传到ftp服务器  后的文件
	 * @return
	 * @throws FtpException
	 */
	public static boolean SFTPUpload(FtpInfo ftpInfo,String srcFile,String dstFile) throws FtpException{
		IFTP ftp=new SFTP(ftpInfo);
		return upload(ftp, srcFile, dstFile);
	} 
	/**
	 * 
	 * @param ftp
	 * @param srcFile
	 * @param dstFile
	 * @return
	 * @throws FtpException
	 */
	private static boolean upload(IFTP ftp,String srcFile,String dstFile)throws FtpException{
		boolean isSuccess=false;
		try{
		 ftp.open();
		 isSuccess=ftp.upload(srcFile, dstFile);		 
		}catch(Exception ex){
			throw new FtpException(ex);
		}finally{
			if(ftp!=null){try{ftp.close();}catch(Exception ex){}};
		}		
		return isSuccess;
	}
	
	/**
	 * 
	 * @param ftpInfo  ftp认证信息
	 * @param srcFile  待下载文件 (ftp服务器上的文件)
	 * @param dstFile  本机文件
	 * @return
	 * @throws FtpException
	 * @throws IOException 
	 */
	public static boolean FTPDownload(FtpInfo ftpInfo,String srcFile) throws FtpException{		
		String fileName=System.getProperty("user.dir")+"/"+srcFile.substring(srcFile.lastIndexOf("/")+1);
		return FTPDownload(ftpInfo, srcFile, fileName);
	}
	
	/**
	 * 
	 * @param ftpInfo  ftp认证信息
	 * @param srcFile  待下载文件 (ftp服务器上的文件)
	 * @param dstFile  本机文件
	 * @return
	 * @throws FtpException
	 */
	public static boolean FTPDownload(FtpInfo ftpInfo,String srcFile,String dstFile) throws FtpException{
		IFTP ftp=new FTP(ftpInfo);
		return download(ftp, srcFile, dstFile);
	}	
	
	/**
	 * 
	 * @param ftpInfo  ftp认证信息
	 * @param srcFile  待下载文件 (ftp服务器上的文件)
	 * @param dstFile  本机文件
	 * @return
	 * @throws FtpException
	 * @throws IOException 
	 */
	public static boolean SFTPDownload(FtpInfo ftpInfo,String srcFile) throws FtpException{		
		String fileName=System.getProperty("user.dir")+"/"+srcFile.substring(srcFile.lastIndexOf("/")+1);
		return SFTPDownload(ftpInfo, srcFile, fileName);
	}
	/**
	 * 
	 * @param ftpInfo   sftp认证信息
	 * @param srcFile  待下载文件 (ftp服务器上的文件)
	 * @param dstFile  输出到本机文件
	 * @return
	 * @throws FtpException
	 */
	public static boolean SFTPDownload(FtpInfo ftpInfo,String srcFile,String dstFile) throws FtpException{
		IFTP ftp=new SFTP(ftpInfo);
		return download(ftp, srcFile, dstFile);
	} 
	
	private static boolean download(IFTP ftp,String srcFile,String dstFile) throws FtpException{
		boolean isSuccess=false;
		try{
			File file=new File(dstFile);
			String parent=file.getParent();
			File dirFile=new File(parent);
			dirFile.mkdirs();
			file.createNewFile();
			ftp.open();
			isSuccess=ftp.download(srcFile, dstFile);		 
		}catch(Exception ex){
			throw new FtpException(ex);
		}finally{
			if(ftp!=null){try{ftp.close();}catch(Exception ex){}};
		}		
		return isSuccess;	
	} 	
}
