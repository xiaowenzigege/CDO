package com.cdo.exception;

public class StateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3673428114022869168L;
	
	public StateException(String message) {
        super(message);
    }
 
    public StateException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public StateException(Throwable cause) {
        super(cause);
    }
}
