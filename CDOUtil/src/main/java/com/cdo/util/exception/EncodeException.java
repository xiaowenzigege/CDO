package com.cdo.util.exception;

public class EncodeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8257782775777706728L;

	public EncodeException(String message) {
        super(message);
    }
 
    public EncodeException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public EncodeException(Throwable cause) {
        super(cause);
    }
}
