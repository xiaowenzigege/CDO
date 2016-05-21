package com.cdoframework.cdolib.datasync.exception;

public class ExecuteException extends Exception {

	public ExecuteException() {
		super();
	}
	
	public ExecuteException(String msg){
		super(msg);
	}
	
	/**
	 * 构造函数要保留异常堆栈
	 * 
	 * @param msg
	 * @param t
	 */
	public ExecuteException(String msg, Throwable t) {
		super(msg, t);
	}
	
	/**
	 * 构造函数要保留异常堆栈
	 * 
	 * @param t
	 */
	public ExecuteException(Throwable t) {
		super(t);
	}
}
