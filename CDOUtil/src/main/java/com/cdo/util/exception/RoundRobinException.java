package com.cdo.util.exception;

/**
 * 
 * @author KenelLiu
 *
 */
public class RoundRobinException extends Exception{
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = -878791122827796377L;

	public RoundRobinException(String message) {
        super(message);
    }
 
    public RoundRobinException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public RoundRobinException(Throwable cause) {
        super(cause);
    }
}
