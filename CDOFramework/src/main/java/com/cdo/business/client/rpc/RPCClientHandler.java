package com.cdo.business.client.rpc;


import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.Header;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.common.UUidGenerator;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.ByteString;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class RPCClientHandler extends  ChannelInboundHandlerAdapter {
	private static Logger logger=Logger.getLogger(RPCClientHandler.class);

    private volatile Channel channel;
    private ByteString clientId; 
    final CallsLinkedHashMap calls = new CallsLinkedHashMap();
    /** A counter for generating call IDs. */
    static final AtomicInteger callIdCounter = new AtomicInteger();
           
    public GoogleCDO.CDOProto handleTrans(CDO cdoRequest) {    	
    	//CDO请求数据
    	int callId=callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
        final Call call =new Call(callId);    
    	GoogleCDO.CDOProto.Builder proto=cdoRequest.toProtoBuilder();
    	proto.setCallId(callId);
		proto.setClientId(clientId);	
		//构造发送message 类型数据
		Header reqHeader=new Header();
		reqHeader.setType(ProtoProtocol.TYPE_CDO);
		CDOMessage reqMessage=new CDOMessage();
		reqMessage.setHeader(reqHeader);
		reqMessage.setBody(proto.build());
		
        channel.writeAndFlush(reqMessage);    
        calls.put(callId, call);
        boolean interrupted = false;
        synchronized (call) {
          while (!call.done()) {
            try {
              call.wait();                 
            } catch (InterruptedException ie) {
              // save the fact that we were interrupted
              interrupted = true;
            }
          }
          if (interrupted) {
            // set the interrupt flag now that we are done waiting
            Thread.currentThread().interrupt();
          }    
         return call.getRpcResponse();
       }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
        clientId=ByteString.copyFrom(UUidGenerator.ClientId.getClientId());
    }
    
    
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {// ping message when there is no outbound traffic for 10 seconds  see RPCClient,RPCServerInitializer         	
                case WRITER_IDLE:
        			CDOMessage heartBeat=new CDOMessage();
        			Header header=new Header();
        			header.setType(ProtoProtocol.TYPE_HEARTBEAT_REQ);
        			heartBeat.setHeader(header);
        			ctx.writeAndFlush(heartBeat);
                    break;
                default:
                    break;
            }
        }
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
    	if(msg instanceof CDOMessage){   
    		CDOMessage cdoMessage=(CDOMessage)msg;
    		if(cdoMessage.getHeader().getType()==ProtoProtocol.TYPE_HEARTBEAT_RES){
    			if(logger.isInfoEnabled())
    				logger.info("client receive server heart msg:"+msg);
    		}else if(cdoMessage.getHeader().getType()==ProtoProtocol.TYPE_CDO){
        		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)(cdoMessage.getBody());
    			int callId=proto.getCallId();
    			Call call = calls.get(callId);
    	        calls.remove(callId);
    	        call.setRpcResponse(proto);		    			
    		}
    	}else{
    		ctx.fireChannelRead(msg);
    	}        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error(cause.getMessage(),cause);
    	ctx.fireExceptionCaught(cause);
    }
      
	
}
