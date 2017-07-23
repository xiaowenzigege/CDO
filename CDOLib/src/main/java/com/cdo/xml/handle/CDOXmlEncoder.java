package com.cdo.xml.handle;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * 使用 XML 	CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(3个字节)+4个字节为callId值
 * @author KenelLiu
 *
 */
public class CDOXmlEncoder extends MessageToByteEncoder<XMLMessage>{
	
	 private static Logger log=Logger.getLogger(CDOXmlEncoder.class);
	@Override
	protected void encode(ChannelHandlerContext ctx, XMLMessage msg,
			ByteBuf out) throws Exception {		
		try{
			byte[] body=msg.getBody()==null?new byte[0]:msg.getBody().getBytes();				
			byte[] header=encodeCDOHeader(msg.getHeader(), body.length,msg.getCallId());
			out.writeBytes(header);		
			if(body.length>0){
				out.writeBytes(body);							
			}
		}finally{			
			ReferenceCountUtil.release(msg);
			msg=null;
		}
	}

	
	private byte[] encodeCDOHeader(XMLHeader msgHeader,int bodyLength,int callId){	
				    
		    byte[] header = new byte[XMLProtocol.PROTOCOL_LEN];	
		    //cdo魔数
		    header[0]=(byte)(msgHeader.getCrcCode()&0xff);	
		    header[1]=(byte)((msgHeader.getCrcCode()>>8)&0xff);
		    //协议消息类型
		    int index=2;
		    header[index]=msgHeader.getType();
		    //设置3个字节表示  cdo内容长度
		    for(int i=0;i<3;i++){		    	
		    	header[++index]=(byte)((bodyLength>>(i*8))&0xff);
		    }	
		    //callId值
		    for(int i=0;i<4;i++){		    	
		    	header[++index]=(byte)((callId>>(i*8))&0xff);
		    }			    
	        return header;
	}
}
