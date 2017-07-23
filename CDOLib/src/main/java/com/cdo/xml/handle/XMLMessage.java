package com.cdo.xml.handle;


public class XMLMessage {
	private XMLHeader header;
	private String body;
	private int callId;
	
	public int getCallId() {
		return callId;
	}
	public void setCallId(int callId) {
		this.callId = callId;
	}
	public XMLHeader getHeader() {
		return header;
	}
	public void setHeader(XMLHeader header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
		
	public String toString(){
		return "XML Message [header="+header+"],callId="+callId;
	}
	
}
