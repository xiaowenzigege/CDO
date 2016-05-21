package com.cdoframework.cdolib.datasync;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.datasync.exception.ExecuteException;

/**********************************************
 * 任何需要保持数据一致性的操作，继承该类。实现execute方法
 * @author doing_jiang
 *
 **********************************************/
public abstract class Task<T>
{	
	private static final Logger logger = Logger.getLogger(Task.class);

	public static int TYPE_MEMCACHE_PUT = 1001;		//MEMCACHE更新或新增缓存
	public static int TYPE_MEMCACHE_REMOVE = 1002;	//MEMCACHE删除缓存
	public static int TYPE_HANDTRANS = 1003;	//handtrans操作
	
	protected TaskInfo taskInfo;
	
	protected boolean isCancel = false;
	
	protected boolean isDone = false;
	
	protected int nRepairTimes = 0;

	private TaskFuture taskFuture;
	
	public String toString(){
		return taskInfo.toString();
	}
	
	public synchronized boolean isDone() {
		return isDone;
	}


	public synchronized void setDone(boolean isDone) {
		this.isDone = isDone;
	}


	public synchronized boolean isCancel() {
		return isCancel;
	}

	public synchronized void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public abstract T execute() throws ExecuteException;
	
	public abstract T repair() throws ExecuteException;

	public synchronized TaskInfo getTaskInfo() {
		return taskInfo;
	}

	public synchronized void setTaskInfo(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}
	
	public synchronized boolean needExecute(){
		return (!this.isCancel && !this.isDone && this.nRepairTimes<taskInfo.getFailPolicy().getNMaxRepeatTimes());
	}
	
	public synchronized int getMaxExecuteTimes() {
		return taskInfo.getFailPolicy().getNMaxRepeatTimes();
	}

	public synchronized void setTaskFuture(TaskFuture taskFuture) {
		this.taskFuture = taskFuture;
	}
	
	public synchronized TaskFuture getTaskFuture() {
		return taskFuture;
	}

}
