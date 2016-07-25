package com.cdo.google.handle;
import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.cdo.google.protocol.GoogleCDO;
import com.google.protobuf.MessageLite;
public class CDOProtobufEncoder extends MessageToByteEncoder<CDOMessage>{
	
	 private static Logger log=Logger.getLogger(CDOProtobufEncoder.class);
	@Override
	protected void encode(ChannelHandlerContext ctx, CDOMessage msg,
			ByteBuf out) throws Exception {		
		byte[] body=msg.getBody()==null?new byte[0]:msg.getBody().toByteArray();
		byte[] header=encodeCDOHeader(msg.getHeader(), body.length);
		out.writeBytes(header);		
		if(body.length>0)
			out.writeBytes(body);
	}

	
	private byte[] encodeCDOHeader(Header msgHeader,int bodyLength){	
			// CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(4个字节)				
		    byte[] header = new byte[ProtoProtocol.PROTOCOL_LEN];		    
		    header[0]=(byte)(msgHeader.getCrcCode()&0xff);	
		    header[1]=(byte)((msgHeader.getCrcCode()>>8)&0xff);
		    //协议消息类型
		    int start=2;
		    header[start]=msgHeader.getType();
		    //设置字节表示 内容长度
		    for(int i=0;i<4;i++){
		    	header[start+(i+1)]=(byte)((bodyLength>>(i*8))&0xff);
		    }		    		    
	        return header;
	}
}
