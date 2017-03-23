package com.cdo.util.exception;

public class NotEstablishConnectException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3321518717733585464L;

	public NotEstablishConnectException(String message) {
        super(message);
    }
 
    public NotEstablishConnectException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public NotEstablishConnectException(Throwable cause) {
        super(cause);
    }
}
