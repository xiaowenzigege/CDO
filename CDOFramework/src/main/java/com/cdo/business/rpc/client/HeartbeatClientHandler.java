package com.cdo.business.rpc.client;

import org.apache.log4j.Logger;

import com.cdo.google.Header;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.ProtoProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class HeartbeatClientHandler extends  ChannelInboundHandlerAdapter {
	private  Logger logger=Logger.getLogger(HeartbeatClientHandler.class);

    /**
     * 客端设定 5秒是空闲写时,发起心跳检查
     * 服务端在60秒内 read_idea处于空闲状态,则会认为该连接无效[即客户端发起了12次连接，服务端均未收到消息]，进行关闭
     */
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {//  message when there is no outbound traffic for 10 seconds  see NettyClientFactory,RPCServerInitializer         	
                case WRITER_IDLE:                	
        			CDOMessage heartBeat=new CDOMessage();
        			try{
	        			Header header=new Header();
	        			header.setType(ProtoProtocol.TYPE_HEARTBEAT_REQ);
	        			heartBeat.setHeader(header);
	        			ctx.writeAndFlush(heartBeat);
        			}finally{
        				ReferenceCountUtil.release(heartBeat);        				
        			}
                    break;
                case READER_IDLE:
                	// message when there is no inbound traffic for 60 seconds see NettyClientFactory,RPCServerInitializer   
                	ctx.close();
                default:
                    break;
            }
        }
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
    	if(msg instanceof CDOMessage){   
    		CDOMessage cdoMessage=(CDOMessage)msg;   
    		switch(cdoMessage.getHeader().getType()){
    			case ProtoProtocol.TYPE_HEARTBEAT_REQ:
					Header resHeader=new Header();
					resHeader.setType(ProtoProtocol.TYPE_HEARTBEAT_RES);
					CDOMessage resMessage=new CDOMessage();
					resMessage.setHeader(resHeader);
					ctx.writeAndFlush(resMessage);
				    if(logger.isDebugEnabled())
			    		logger.debug("client  receive server heartbeat-Request ["+ctx.channel()+"] heart msg:"+msg);
    				break;
    			case 	ProtoProtocol.TYPE_HEARTBEAT_RES:
    				if(logger.isDebugEnabled())
		    			logger.debug("client receive server heartbeat-Response ["+ctx.channel()+"] heart msg:"+msg);
    				break;
    			default:
    				ctx.fireChannelRead(msg);
    		}
    	}else{
    		ctx.fireChannelRead(msg);
    	}        
    }

}
