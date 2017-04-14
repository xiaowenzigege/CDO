package com.cdo.util.exception;

public class HttpException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8257782775777706728L;

	public HttpException(String message) {
        super(message);
    }
 
    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public HttpException(Throwable cause) {
        super(cause);
    }
}
