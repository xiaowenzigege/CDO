package com.cdo.protocol;

public interface Protocol {
	
	 final String PROTOCOL_CDO="CDO";
	 //魔数
	 final short MAGIC_NUMBER=0xCDF;
	 /**
	  * 消息为 TYPE_HEARTBEAT_REQ,TYPE_HEARTBEAT_RES 心跳检测类型,则无实际内容传输，即不存在CDO对象
	  */
	 final byte  TYPE_CDO=0x00;
	 final byte  TYPE_HEARTBEAT_REQ=0x01;
	 final byte  TYPE_HEARTBEAT_RES=0x02;
	 final byte  TYPE_STOPLOCALServer=0x03;
	 public enum Type{
		 	CDO(TYPE_CDO,"CDO"),HEARTBEAT_REQ(TYPE_HEARTBEAT_REQ,"heartBeat Req"),HEARTBEAT_RES(TYPE_HEARTBEAT_RES,"heartBeat Res"),
		 	STOPLOCALServer(TYPE_STOPLOCALServer,"stop server");
			private byte type;
		    private String msg;
		    
		    private Type(byte type,String msg){		    	
		        this.type = type;		       
		        this.msg = msg;
		    }

			public byte getType() {
				return type;
			}

			public void setType(byte type) {
				this.type = type;
			}
			
			public String getMsg() {
				return msg;
			}

			public void setMsg(String msg) {
				this.msg = msg;
			}
		    
		}
}
