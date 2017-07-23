package com.cdo.xml.handle;

import com.cdo.protocol.Protocol;

public interface XMLProtocol extends Protocol{
	 /**
	  * XML CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象CDO内容长度(3个字节)+4个字节callId值
	  * 其中文件 为可选项
	  * 消息为 TYPE_HEARTBEAT_REQ,TYPE_HEARTBEAT_RES 心跳检测类型,则无实际内容传输，即不存在CDO对象
	  */
	 final int   PROTOCOL_LEN=2+1+3+4;	

}
