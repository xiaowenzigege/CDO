package com.cdo.google.fileHandle;



import java.io.File;
import java.util.List;

import com.cdo.google.Header;
import com.google.protobuf.MessageLite;

public class CDOFileMessage {
	private Header header;
	private MessageLite body;
	private List<File> files;
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
		
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public String toString(){
		return "Message [header="+header+"]";
	}
	
}
