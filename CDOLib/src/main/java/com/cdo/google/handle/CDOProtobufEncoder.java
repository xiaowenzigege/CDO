package com.cdo.google.handle;
import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.cdo.google.protocol.GoogleCDO;
import com.google.protobuf.MessageLite;
public class CDOProtobufEncoder extends MessageToByteEncoder<MessageLite>{
	
	 private static Logger log=Logger.getLogger(CDOProtobufEncoder.class);
	@Override
	protected void encode(ChannelHandlerContext ctx, MessageLite msg,
			ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		if(msg instanceof GoogleCDO.CDOProto){
			byte[] body=msg.toByteArray();
			byte[] header=encodeCDOHeader(msg, body.length);
			out.writeBytes(header);
	        out.writeBytes(body);
	        return; 
		}
		log.warn("object is not GoogleCDO.CDOProto,ignore CDOProto encode .......");

	}

	private byte[] encodeCDOHeader(MessageLite msg,int bodyLength){	
			// 协议头类型(1个字节表示)+协议头标识+4个字节表示对象内容长度
			int protoLen=Proto.PROTOCOL_CDO_HEADER.length();
		    byte[] header = new byte[protoLen+5];
		    
		    header[0]=Proto.Type.CDO.getType();		
		    //协议头标识
		    for(int i=1;i<=Proto.PROTOCOL_CDO_HEADER.length();i++){
		    	header[i]=(byte)Proto.PROTOCOL_CDO_HEADER.charAt(i-1);		    
		    }
		    //设置字节表示 内容长度
		    for(int i=0;i<4;i++){
		    	header[protoLen+(i+1)]=	(byte)((bodyLength>>(i*8))&0xff);
		    }
	        return header;
	}
}
