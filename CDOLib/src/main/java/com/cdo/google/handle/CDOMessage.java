package com.cdo.google.handle;

import com.google.protobuf.MessageLite;

public class CDOMessage {
	private Header header;
	private MessageLite body;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public MessageLite getBody() {
		return body;
	}
	public void setBody(MessageLite body) {
		this.body = body;
	}
	
	
}
