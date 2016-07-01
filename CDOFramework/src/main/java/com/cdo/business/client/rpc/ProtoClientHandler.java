package com.cdo.business.client.rpc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;




import com.cdo.example.ExampleCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;
import com.google.protobuf.MessageLite;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ProtoClientHandler extends  ChannelInboundHandlerAdapter {
	private static Logger logger=Logger.getLogger(ProtoClientHandler.class);
	
    private volatile Channel channel;
    private final BlockingQueue<GoogleCDO.CDOProto> answer = new LinkedBlockingQueue<GoogleCDO.CDOProto>();
    /** A counter for generating call IDs. */
    private static final AtomicInteger callIdCounter = new AtomicInteger();
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       test(ctx);
    }
    
    public GoogleCDO.CDOProto handleTrans(CDO cdoRequest) {
    	//发送请求
    	
    	
    	cdoRequest.setIntegerValue(ITransService.CALL_ID, nextCallId());
    	GoogleCDO.CDOProto proto=cdoRequest.toProto();
//    	proto.set
        channel.writeAndFlush(cdoRequest.toProto());    
//        System.out.println("send req"+cdoRequest);
        //等待response 参见netty4.1 官网example
//        boolean interrupted = false;
//        GoogleCDO.CDOProto local;
//        for (;;) {
//            try {            	
//            	local = answer.take();
//                break;
//            } catch (InterruptedException ignore) {
//                interrupted = true;
//            }            
//        }
//        
//        if (interrupted) {
//            Thread.currentThread().interrupt();
//        }        
        return cdoRequest.toProto();               
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
    	if(msg instanceof GoogleCDO.CDOProto){    		
//    		answer.add((GoogleCDO.CDOProto)msg);
    		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)msg;
		  	CDO cdoReturn=new CDO();
		  	CDO cdoResponse=new CDO();
//			GoogleCDO.CDOProto proto=handle.handleTrans(cdoRequest);
			ProtoRPCCDOParse.ProtoRPCParse.parse(proto, cdoResponse, cdoReturn);	
			System.out.println(System.currentTimeMillis()+";return;"+cdoResponse.toString());
    	}else{
    		ctx.fireChannelRead(msg);
    	}        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error(cause.getMessage(),cause);
    }
    
    public  void test(ChannelHandlerContext ctx){
		ProtoPRCClient client=new ProtoPRCClient();
		CDO cdoReq=ExampleCDO.getCDO();
		
		CDO cdoReq2=ExampleCDO.getCDO();
		cdoReq2.setStringValue("wait", "wait");
		CDOThread t2=new CDOThread(cdoReq2,ctx);	
		new Thread(t2).start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CDOThread t1=new CDOThread(cdoReq,ctx);	
		new Thread(t1).start();

	}
	private class CDOThread implements Runnable{

		private CDO cdoReq;
		private ChannelHandlerContext ctx;
		public CDOThread(CDO cdoReq,ChannelHandlerContext ctx){
			this.cdoReq=cdoReq;
			this.ctx=ctx;
		}
		@Override
		public void run() {
//			Integer.MAX_VALUE;
//			CDO cdoResponse=new CDO();
//			Return return1=handleTrans(cdoReq, cdoResponse);
//			System.out.println(System.currentTimeMillis()+";;"+cdoResponse.toString());
			ctx.writeAndFlush(cdoReq.toProto());
		}
		
	}
	
	  public static int nextCallId() {
		    return callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
		  }
}
