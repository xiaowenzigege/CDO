package com.cdo.business.rpc.server.xml;

import java.io.File;
//import java.net.InetSocketAddress;
import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
//import java.util.concurrent.SynchronousQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.business.rpc.RPCFile;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.protocol.Protocol;
import com.cdo.util.constants.Constants;
import com.cdo.xml.handle.XMLHeader;
import com.cdo.xml.handle.XMLMessage;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.timeout.IdleStateEvent;
//import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.SystemPropertyUtil;
/**
 * 不考虑  处理CDO 文件流
 * @author KenelLiu
 *
 */
public class XMLServerHandler extends SimpleChannelInboundHandler<XMLMessage> {

	private static Logger logger=Logger.getLogger(XMLServerHandler.class);

	private int corePoolSize=Math.max(1,SystemPropertyUtil.getInt(Constants.Business.CoreSize,Runtime.getRuntime().availableProcessors()));
	private ExecutorService exService=null;
	//使用 io channel处理业务，则是   一个服务器对应了大量的客户端
	private boolean directNioChannel=SystemPropertyUtil.getBoolean(Constants.Business.DIRECT_NIO_CHANNEL,false);
	
	private Channel channel;
	private LinkedTransferQueue<BusinessHandle> lnkTransQueue;
	private  XmlConsumer[] consumer;
	public XMLServerHandler(){

	}
    /**
     * 防止   客户机与服务器之间的长连接   发生阻塞,业务数据采用线程池处理,长连接channel仅用于数据读取io数据
     * 多个action通过长连接 发送请求到service端 
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, XMLMessage msg)
			throws Exception {	
		 XMLHeader header=msg.getHeader();
		  switch(header.getType()){
		  	  case Protocol.TYPE_CDO:
		  		  	String strCDORequest=msg.getBody();
		  		  	int callId=msg.getCallId();
		  			BusinessHandle handle=new BusinessHandle(channel,strCDORequest,callId);
					if(directNioChannel){					
						handle.process();
					}else{
						try{
							lnkTransQueue.transfer(handle);
						}catch(InterruptedException ex){
							logger.warn("add element to TransferQueue occured InterruptedException,retry add... ...");
							tryAddElement(handle);
						}
					}			
				break;		  		 
		  	 default:
		  		ctx.fireChannelRead(msg); 
		  }		  	
	}
	
	private void tryAddElement(BusinessHandle handle){
		int retry=1;
		while(retry<=3){
			try{
				lnkTransQueue.transfer(handle);
				break;
			}catch(InterruptedException ex){
				retry++;
				try{Thread.sleep(500);}catch(Exception e){};
			}
		}
	}
	
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();   
        
        if(corePoolSize==1){
        	if(logger.isDebugEnabled())
        		logger.debug("channel="+channel+" is channelRegistered, corePoolSize="+corePoolSize+",reset directNioChannel=true");
        	directNioChannel=true;        	
        }
        if(directNioChannel){
        	if(logger.isDebugEnabled())
        		logger.debug("channel="+channel+" is channelRegistered, directNioChannel=true");
        	return;
        }
        
		lnkTransQueue = new LinkedTransferQueue<BusinessHandle>();
		exService=Executors.newFixedThreadPool(corePoolSize);
		consumer=new XmlConsumer[corePoolSize]; 
		for(int i=0;i<corePoolSize;i++){
			consumer[i]=new XmlConsumer(channel+",Consumer"+i,lnkTransQueue);
			exService.submit(consumer[i]);
		}		
		if(logger.isInfoEnabled())
			logger.info("channel="+channel+" is channelRegistered, lnkTransQueue executor create newFixedThreadPool coresize="+corePoolSize);
    }
    
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelUnregistered();        
        logger.warn("channel="+channel+" is channelUnregistered,lnkTransQueue executor will be shutdown");
        if(directNioChannel){
        	return;
        }
        lnkTransQueue.clear();
		for(int i=0;i<consumer.length;i++){
			consumer[i].setStop(true);	
		    consumer[i]=null;   
		}	               
        lnkTransQueue=null;            
        exService.shutdownNow();
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
