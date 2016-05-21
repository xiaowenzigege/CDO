package com.cdo.util.exception;

/**
 * 
 * @author KenelLiu
 *
 */
public class EncryptException extends Exception {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2418041647078449374L;

	public EncryptException() {
        super();
    }
 
    public EncryptException(String message) {
        super(message);
    }
 
    public EncryptException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public EncryptException(Throwable cause) {
        super(cause);
    }
}
