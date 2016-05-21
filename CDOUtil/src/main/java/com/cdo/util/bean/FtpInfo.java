package com.cdo.util.bean;

/**
 *  ftp认证信息
 * @author KenelLiu
 *
 */
public class FtpInfo {
	private String RSAFile;
	private String passphrase;
	private String user;
	private String password;
	private String host;	
	private int port;
	
	public FtpInfo(){		
	}
	
	public FtpInfo(String host,int port){
		this(null,null,host,port);
	}
	
	public FtpInfo(String user,String password,String host,int port){
		this(null,null,user,password,host,port);
	}
	
	public FtpInfo(String RSAFile,String  passphrase,String user,String password,String host,int port){
		this.RSAFile=RSAFile;
		this.passphrase=passphrase;				
		this.user=user;
		this.password=password;
		this.host=host;
		this.port=port;
	}
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRSAFile() {
		return RSAFile;
	}

	public void setRSAFile(String rSAFile) {
		RSAFile = rSAFile;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}	
}
