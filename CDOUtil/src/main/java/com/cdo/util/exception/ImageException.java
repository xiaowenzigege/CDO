package com.cdo.util.exception;
 
/**
 * 
 * @author KenelLiu
 *
 */
 public class ImageException extends Exception{
	 
	private static final long serialVersionUID = -9151850155683559654L;

	public ImageException(String msg){
	     super(msg);
	 }
	
	public ImageException(String msg,Throwable cause){
	     super(msg,cause);
	 }

}
