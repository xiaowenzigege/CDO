package com.cdo.util.ftp;

import com.cdo.util.exception.FtpException;
/**
 * 
 * @author KenelLiu
 *
 */
public interface IFTP {

	public void open()throws FtpException;
	
	public boolean upload(String srcFile,String dstFile) throws FtpException;
	
	public boolean download(String srcFile,String dstFile)throws FtpException;
	
	public void close() throws FtpException;
}
