package com.cdo.google.handle;

public interface Proto {
	 final String PROTOCOL_CDO="CDO";
	 /**
	  * CDO 协议头=魔数(2个字节)+协议标识+协议类型(1个字节)+CDO封装 对象内容长度(4个字节)
	  */
	 final int PROTOCOL_CDO_HEADER_TOTAL_LEN=2+Proto.PROTOCOL_CDO.length()+1+4;	
	 
	 public enum Type{
		 	CDO((short)0xCDF,(byte)0x00,"CDO");
			private byte type;
			private short magic;
		    private String protoName;
		    
		    private Type(short magic,byte type,String protoName){
		    	this.magic=magic;
		        this.type = type;		       
		        this.protoName = protoName;
		    }

			public byte getType() {
				return type;
			}

			public void setType(byte type) {
				this.type = type;
			}

			public short getMagic() {
				return magic;
			}

			public void setMagic(short magic) {
				this.magic = magic;
			}

			public String getProtoName() {
				return protoName;
			}

			public void setProtoName(String protoName) {
				this.protoName = protoName;
			}
		    
		}
}
