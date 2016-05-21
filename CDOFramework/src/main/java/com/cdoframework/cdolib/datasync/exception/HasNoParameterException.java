package com.cdoframework.cdolib.datasync.exception;

public class HasNoParameterException extends Exception {

	public HasNoParameterException() {
		super();
	}
	
	public HasNoParameterException(String msg){
		super(msg);
	}
	
	/**
	 * 构造函数要保留异常堆栈
	 * 
	 * @param msg
	 * @param t
	 */
	public HasNoParameterException(String msg, Throwable t) {
		super(msg, t);
	}
	
	/**
	 * 构造函数要保留异常堆栈
	 * 
	 * @param t
	 */
	public HasNoParameterException(Throwable t) {
		super(t);
	}
}
