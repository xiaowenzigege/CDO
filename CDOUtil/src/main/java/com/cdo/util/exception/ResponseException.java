package com.cdo.util.exception;
 
 public class ResponseException extends Exception{
	 
	private static final long serialVersionUID = -9151850155683559654L;

	public ResponseException(String msg){
	     super(msg);
	 }
	public ResponseException(String msg,Throwable t){
	     super(msg,t);
	 }	
}
