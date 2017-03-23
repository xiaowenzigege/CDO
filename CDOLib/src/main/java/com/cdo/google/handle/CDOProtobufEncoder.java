package com.cdo.google.handle;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * 	CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(3个字节)+文件数量(1个字节)+文件数量*8个字节(每8个字节表示文件长度)
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
			byte[] header=encodeCDOHeader(msg.getHeader(), body.length,msg.getFiles());
			out.writeBytes(header);		
			if(body.length>0){
				out.writeBytes(body);
				
				//文件长度
				List<File> files=msg.getFiles();
				if(files!=null){
			    	for(int i=0;i<files.size();i++){
						int l;	
						FileInputStream inStream=null;
						long total=0;
						try{
				    		inStream=new FileInputStream(files.get(i));
				    		long fileLen=files.get(i).length();
			        		long remainLen=fileLen-total;
			        		int length=remainLen>2048?2048:(int)remainLen;
				    		byte[] tmp =new byte[length];
				    		while((l = inStream.read(tmp)) != -1){
				    			out.writeBytes(tmp);	
				    			
				    			total=total+l;
				    			if(total>=fileLen)
				    				break;
				    			remainLen=files.get(i).length()-total;
				        	    length=remainLen>2048?2048:(int)remainLen;
					    	    tmp =new byte[length];
				    		}
						}catch(Exception ex){
							log.error("file:"+ex.getMessage(), ex);
						}finally{
							if(inStream!=null){try{inStream.close();}catch(Exception ex){}}
						}
			    	}								
				}			
			}
		}finally{			
			ReferenceCountUtil.release(msg);
			msg=null;
		}
	}

	
	private byte[] encodeCDOHeader(Header msgHeader,int bodyLength,List<File> files){	
		
		    int fileCount=files==null?0:files.size();
		    byte[] header = new byte[ProtoProtocol.PROTOCOL_LEN+8*fileCount];	
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
		    //文件数量
		    header[++index]=(byte)fileCount;
		    //设置文件内容长度
		    if(fileCount>0){
		    	for(int i=0;i<files.size();i++){
		    		long filelLen=files.get(i).length();
				    for(int k=0;k<8;k++){		    	
				    	header[++index]=(byte)((filelLen>>(k*8))&0xff);
				    }	
		    	}
		    }
	        return header;
	}
}
