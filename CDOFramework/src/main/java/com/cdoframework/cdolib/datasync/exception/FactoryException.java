package com.cdoframework.cdolib.datasync.exception;

public class FactoryException extends Exception {

	public FactoryException() {
		super();
	}
	
	public FactoryException(String msg){
		super(msg);
	}
	
	/**
	 * 构造函数要保留异常堆栈
	 * 
	 * @param msg
	 * @param t
	 */
	public FactoryException(String msg, Throwable t) {
		super(msg, t);
	}
	
	/**
	 * 构造函数要保留异常堆栈
	 * 
	 * @param t
	 */
	public FactoryException(Throwable t) {
		super(t);
	}
}
