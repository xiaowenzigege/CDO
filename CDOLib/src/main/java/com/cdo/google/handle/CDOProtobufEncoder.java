package com.cdo.google.handle;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.cdo.google.Header;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * 	CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(3个字节)
 * @author KenelLiu
 *
 */
public class CDOProtobufEncoder extends MessageToByteEncoder<CDOMessage>{
	
	 private static Logger log=Logger.getLogger(CDOProtobufEncoder.class);
	@Override
	protected void encode(ChannelHandlerContext ctx, CDOMessage msg,
			ByteBuf out) throws Exception {		
		try{
			byte[] body=msg.getBody()==null?new byte[0]:msg.getBody().toByteArray();				
			byte[] header=encodeCDOHeader(msg.getHeader(), body.length);
			out.writeBytes(header);		
			if(body.length>0){
				out.writeBytes(body);			
			}
		}finally{			
			ReferenceCountUtil.release(msg);
			msg=null;
		}
	}
	
	private byte[] encodeCDOHeader(Header msgHeader,int bodyLength){	

		    byte[] header = new byte[ProtoProtocol.PROTOCOL_LEN];	
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
	        return header;
	}
}
