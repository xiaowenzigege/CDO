package com.cdo.util.ftp;

import com.cdo.util.bean.FtpInfo;
import com.cdo.util.exception.FtpException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
/**
 * 
 * @author KenelLiu
 *
 */
public class SFTP implements IFTP{
	private FtpInfo ftpInfo;	
	private ChannelSftp sftp;
	
	public SFTP(FtpInfo ftpInfo){
		this.ftpInfo=ftpInfo;
	}
	
	public void open() throws FtpException{
		try{
			JSch jsch = new JSch();
			if(ftpInfo.getRSAFile()!=null){
				 jsch.addIdentity(ftpInfo.getRSAFile());
			}
		   Session session = jsch.getSession(ftpInfo.getUser(), ftpInfo.getHost(), ftpInfo.getPort());
		   SFTPUserInfo userinfo= new SFTPUserInfo(ftpInfo.getPassword(),ftpInfo.getPassphrase());
		   session.setUserInfo(userinfo);
		   session.connect();
		   Channel channel = session.openChannel("sftp");
		   channel.connect();
		   sftp= (ChannelSftp) channel;
		}catch(Exception ex){
			throw new FtpException(ex.getMessage(),ex);
		}	   
	}
	
	public boolean upload(String srcFile,String dstDir) throws FtpException{
		try{
			if(sftp==null)
				throw new JSchException("sftp connect is null");
			sftp.put(srcFile, dstDir);
			return true;
		}catch(Exception ex){
			throw new FtpException(ex.getMessage(),ex);
		}
	}
	
	public boolean download(String srcFile,String dstDir)throws FtpException{
	 try{
		if(sftp==null)
			throw new JSchException("sftp connect is null");
			sftp.get(srcFile, dstDir);
			return true;
		}catch(Exception ex){
			throw new FtpException(ex.getMessage(),ex);
		}	
	}
	
	public void close(){
		if(sftp!=null)
			sftp.disconnect();
	}

	


	final class SFTPUserInfo implements UserInfo {
	   private String passphrase = null;
	   private String password=null;
	   
	   public SFTPUserInfo(){}
	  
	   public SFTPUserInfo(String password) {
		    this(password,null);
		 }
	   
	   public SFTPUserInfo(String password,String passphrase) {
		this.password=password;   
	    this.passphrase = passphrase;
	   }

	   public String getPassphrase() {
		   return passphrase;
	   }

	   public String getPassword() {
		   return password;
	   }

	   public boolean promptPassphrase(String s) {		   
		   return true;
	   }

	   public boolean promptPassword(String s) {
		   return true;
	   }

	   public boolean promptYesNo(String s) {
		   return true;
	   }

	   public void showMessage(String s) {	    
	   }
	}	
}
