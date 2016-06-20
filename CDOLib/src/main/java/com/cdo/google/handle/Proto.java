package com.cdo.google.handle;

public interface Proto {
	 final String PROTOCOL_CDO_HEADER="CDO";
	 
	 public enum Type{
			CDO((byte)0X00,"CDO");
			private byte type;
		    private String protoName;
		    private Type(byte type,String protoName){
		        this.type = type;
		        this.protoName = protoName;
		    }
			public byte getType() {
				return type;
			}
			public void setType(byte type) {
				this.type = type;
			}
			public String getProtoName() {
				return this.protoName;
			}
			public void setProtoName(String protoName) {
				this.protoName = protoName;
			}
		}
}
