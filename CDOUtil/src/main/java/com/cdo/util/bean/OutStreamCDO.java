package com.cdo.util.bean;

import java.io.OutputStream;

import com.cdoframework.cdolib.data.cdo.CDO;

public class OutStreamCDO extends CDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7208179367491854574L;
	private OutputStream  outputStream;
	private boolean autoCloseStream;
	public OutStreamCDO(){
		this(true);
	}
	public OutStreamCDO(boolean autoCloseStream){
		this.autoCloseStream=autoCloseStream;
	}
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	public boolean isAutoCloseStream() {
		return this.autoCloseStream;
	}
	public void setAutoCloseStream(boolean autoCloseStream) {
		this.autoCloseStream = autoCloseStream;
	}	
	
}
