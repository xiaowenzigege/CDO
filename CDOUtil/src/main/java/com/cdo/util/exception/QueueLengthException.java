package com.cdo.util.exception;

public class QueueLengthException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8257782775777706728L;

	public QueueLengthException(String message) {
        super(message);
    }
 
    public QueueLengthException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public QueueLengthException(Throwable cause) {
        super(cause);
    }
}
