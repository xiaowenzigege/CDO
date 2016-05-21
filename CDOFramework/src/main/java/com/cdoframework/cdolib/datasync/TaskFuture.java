package com.cdoframework.cdolib.datasync;

import java.util.concurrent.Future;


public class TaskFuture {

	private Task task;
	private Future workerFuture;

	public void setTask(Task task) {
		this.task = task;
	}

	/** 取消任务 **/
	public synchronized void cancel() {
		task.setCancel(true);
	}

	public boolean isCancelled() {
		return task.isCancel();
	}

	public boolean isDone() {
		return task.isDone();
	}

	public void setWorkerFuture(Future workerFuture) {
		this.workerFuture = workerFuture;
	}

	public Future getWorkerFuture() {
		return workerFuture;
	}
}
