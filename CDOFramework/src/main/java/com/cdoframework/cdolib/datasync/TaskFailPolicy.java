package com.cdoframework.cdolib.datasync;

/**
 * Task任务辅佐类(任务失败方案)
 * @author dylan_xu
 */
public class TaskFailPolicy
{
	private int nMaxRepeatTimes;//最大重复执行次数
	
	public TaskFailPolicy(){}
	
	public TaskFailPolicy(int nMaxRepeatTimes)
	{
		this.nMaxRepeatTimes = nMaxRepeatTimes;
	}
	
	public int getNMaxRepeatTimes()
	{
		return nMaxRepeatTimes;
	}

	public void setNMaxRepeatTimes(int maxRepeatTimes)
	{
		nMaxRepeatTimes=maxRepeatTimes;
	}

}
