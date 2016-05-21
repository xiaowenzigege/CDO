package com.cdoframework.cdolib.datasync;

import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * TaskExt任务类
 * @author dylan_xu
 */
public class TaskInfo
{
	
	private int nType;
	private TaskFailPolicy failPolicy;
	
	//Memcache -----------------------------------------------------------------
	private String strKey;
	private Object objValue;
	private int nExpireTime;
	private com.cdoframework.cdolib.framework.CacheHandlerTemplate cacheHandler;
	protected int nCurrentExecuteTime;
	protected Runnable command;
	/*** 是否取消 ***/
	private boolean isCancel = false;
	/*** 任务执行是否成功 ***/
	private boolean statue = false;
	private CDO cdoRequest;
	
	public TaskInfo(int type,String strKey,Object objValue,com.cdoframework.cdolib.framework.CacheHandlerTemplate cacheHandler,TaskFailPolicy failPolicy)
	{
		super();
		this.nType = type;
		this.failPolicy = failPolicy;
		this.strKey = strKey;
		this.objValue = objValue;
		this.cacheHandler = cacheHandler;
	}
	
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("[nType:").append(this.nType).append("|strKey:").append(this.strKey)
			.append("|objValue:").append(this.objValue).append("|nExpireTime:").append(this.nExpireTime).append("]");
		if(cdoRequest != null)
			str.append(cdoRequest.toJSONString());
		return str.toString();
	}
	public int getNType()
	{
		return nType;
	}
	public void setNType(int type)
	{
		nType=type;
	}
	public TaskFailPolicy getFailPolicy()
	{
		return failPolicy;
	}
	public void setFailPolicy(TaskFailPolicy failPolicy)
	{
		this.failPolicy=failPolicy;
	}
	public String getStrKey()
	{
		return strKey;
	}
	public void setStrKey(String strKey)
	{
		this.strKey=strKey;
	}
	public Object getObjValue()
	{
		return objValue;
	}
	public void setObjValue(Object objValue)
	{
		this.objValue=objValue;
	}
	public com.cdoframework.cdolib.framework.CacheHandlerTemplate getCacheHandler()
	{
		return cacheHandler;
	}
	public void setCacheHandler(com.cdoframework.cdolib.framework.CacheHandlerTemplate cacheHandler)
	{
		this.cacheHandler=cacheHandler;
	}
	protected synchronized boolean cancel()
	{
		return this.isCancel = true;
	}
	public boolean isCancel(){
		return this.isCancel;
	}
	public boolean getStatue(){
		return this.statue;
	}
	public synchronized void setStatue(boolean statue) {
		this.statue = statue;
	}
	public int getExpairTimes(){
		return this.nExpireTime;
	}
	public void setExpairTimes(int nExpireTime) {
		this.nExpireTime = nExpireTime;
	}

	public void setCdoRequest(CDO cdoRequest) {
		this.cdoRequest = cdoRequest;
	}
	
	public CDO getCdoRequest() {
		return this.cdoRequest;
	}
}
