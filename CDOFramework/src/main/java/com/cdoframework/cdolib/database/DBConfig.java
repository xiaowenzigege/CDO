package com.cdoframework.cdolib.database;

public class DBConfig
{
	private String strDBName;
	private String strUserName;
	private char[]	charSPassword;
	public String getDBName()
	{
		return strDBName;
	}
	public void setDBName(String strDBName)
	{
		this.strDBName=strDBName;
	}
	public String getUserName()
	{
		return strUserName;
	}
	public void setUserName(String strUserName)
	{
		this.strUserName=strUserName;
	}
	public char[] getPassword()
	{
		return charSPassword;
	}
	public void setPassword(char[] charSPassword)
	{
		this.charSPassword=charSPassword;
	}
	
}
