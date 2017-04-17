package com.cdo.util.exception;

public class ZookeeperException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3755635915856008147L;
	public ZookeeperException(String msg){
	     super(msg);
	 }
	public ZookeeperException(String msg,Throwable t){
	     super(msg,t);
	 }	
}
