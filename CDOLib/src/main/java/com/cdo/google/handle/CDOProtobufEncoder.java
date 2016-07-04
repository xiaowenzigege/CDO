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
		if(msg instanceof GoogleCDO.CDOProto){
			byte[] body=msg.toByteArray();
			byte[] header=encodeCDOHeader(msg, body.length);
			out.writeBytes(header);
	        out.writeBytes(body);
	        return; 
		}		
	}

	private byte[] encodeCDOHeader(MessageLite msg,int bodyLength){	
			// CDO 协议=魔数(2个字节)+协议标识+协议类型(1个字节)+对象内容长度(4个字节)				
		    byte[] header = new byte[Proto.PROTOCOL_CDO_HEADER_TOTAL_LEN];		    
		    header[0]=(byte)(Proto.Type.CDO.getMagic()&0xff);	
		    header[1]=(byte)((Proto.Type.CDO.getMagic()>>8)&0xff);
		    //协议标识
		    for(int i=2;i<(2+Proto.PROTOCOL_CDO.length());i++){
		    	header[i]=(byte)Proto.PROTOCOL_CDO.charAt(i-2);		    
		    }
		    //协议类型
		    int start=2+Proto.PROTOCOL_CDO.length();
		    header[start]=Proto.Type.CDO.getType();
		    //设置字节表示 内容长度
		    for(int i=0;i<4;i++){
		    	header[start+(i+1)]=(byte)((bodyLength>>(i*8))&0xff);
		    }		    		    
	        return header;
	}
}
