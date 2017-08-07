package com.cdo.google.handle;

import com.cdo.protocol.Protocol;

public interface ProtoProtocol extends Protocol{
	 /**
	  *  	CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(3个字节)	
	  *  	  * 
	  * 消息为 TYPE_HEARTBEAT_REQ,TYPE_HEARTBEAT_RES 心跳检测类型,则无实际内容传输，即不存在CDO对象
	  * 内容长度=3个字节   包含1个字节消息类型+CDO内容的长度
	  * 
	  */
	 final int   PROTOCOL_LEN=2+1+3;
	 
	
}
