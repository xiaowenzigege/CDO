package com.cdo.business.rpc.server.proto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

import org.apache.log4j.Logger;
import com.cdo.google.Header;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.constants.Constants;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.SystemPropertyUtil;
/**
 * 
 * @author KenelLiu
 *
 */
public class RPCServerHandler extends SimpleChannelInboundHandler<CDOMessage> {

	private static Logger logger=Logger.getLogger(RPCServerHandler.class);
	private int consumerCount=Math.max(1,SystemPropertyUtil.getInt(Constants.Consumer.COUNT,Runtime.getRuntime().availableProcessors()));
	private ExecutorService exService=null;
	//使用 io channel处理业务，则是   一个服务器对应了大量的客户端
//	private boolean directNioChannel=SystemPropertyUtil.getBoolean(Constants.Business.DIRECT_NIO_CHANNEL,false);
	
	private Channel channel;
	private LinkedTransferQueue<GoogleCDO.CDOProto> lnkTransQueue;
	private  Consumer[] consumer;
	public RPCServerHandler(ExecutorService exService){
		this.exService=exService;
	}
    /**
     * 防止   客户机与服务器之间的长连接   发生阻塞,业务数据采用线程池处理,长连接channel仅用于数据读取io数据
     * 多个action通过长连接 发送请求到service端 
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CDOMessage msg)
			throws Exception {	
		  Header header=msg.getHeader();
		  switch(header.getType()){
		  	  case ProtoProtocol.TYPE_CDO:
					GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)(msg.getBody());
					try{
						lnkTransQueue.transfer(proto);
					}catch(InterruptedException ex){
						logger.warn("add element to TransferQueue occured InterruptedException,retry add... ...");
						tryAddElement(proto);
					}
					break;
		  	 default:
		  		ctx.fireChannelRead(msg); 
		  }		  	
	}
	
	private void tryAddElement(GoogleCDO.CDOProto proto){
		int retry=1;
		while(retry<=3){
			try{
				lnkTransQueue.transfer(proto);
				break;
			}catch(InterruptedException ex){
				retry++;
				try{Thread.sleep(500);}catch(Exception e){};
			}
		}
	}
	
	
	public void writeAndFlush(Object msg){
		channel.writeAndFlush(msg);	
	}
	
	
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();   
        
		lnkTransQueue = new LinkedTransferQueue<GoogleCDO.CDOProto>();
		
		consumer=new Consumer[consumerCount]; 
		for(int i=0;i<consumerCount;i++){
			consumer[i]=new Consumer(channel+",Consumer"+i,lnkTransQueue,this);
			exService.submit(consumer[i]);
		}		
		if(logger.isInfoEnabled())
			logger.info("channel="+channel+" is channelRegistered, lnkTransQueue  create consumer count="+consumerCount);
    }
    
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelUnregistered();        
        logger.warn("channel="+channel+" is channelUnregistered,lnkTransQueue executor will be shutdown");
        lnkTransQueue.clear();
		for(int i=0;i<consumer.length;i++){
			consumer[i].setStop(true);	
		    consumer[i]=null;   
		}	               
        lnkTransQueue=null;            
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {        
        super.channelInactive(ctx);     
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);	
    }

}
