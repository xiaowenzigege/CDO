package com.cdo.xml.handle;

public final class XMLHeader {
	private final short crcCode=XMLProtocol.MAGIC_NUMBER;//cdo协议魔数
	private byte type; //消息类型
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public short getCrcCode() {
		return crcCode;
	}
	
	public String toString(){
		
		return "XML Header [ crcCode="+crcCode+",type="+type+"]";
	}
}
