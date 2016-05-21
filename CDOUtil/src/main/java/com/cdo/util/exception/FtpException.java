package com.cdo.util.exception;

/**
 * 
 * @author KenelLiu
 *
 */
public class FtpException extends Exception{
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = -878791122827796377L;

	public FtpException(String message) {
        super(message);
    }
 
    public FtpException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public FtpException(Throwable cause) {
        super(cause);
    }
}
