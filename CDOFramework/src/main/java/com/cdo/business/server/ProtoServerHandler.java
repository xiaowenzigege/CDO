package com.cdo.business.server;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.business.client.rpc.ClientId;
import com.cdo.google.handle.ProtoCDOParse;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;
import com.google.protobuf.MessageLite;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class ProtoServerHandler extends SimpleChannelInboundHandler<MessageLite> {

	private static Logger logger=Logger.getLogger(ProtoServerHandler.class);
	private final  BusinessService serviceBus=BusinessService.getInstance();
	static Map<String,SocketChannel> nettyChannelMap=new ConcurrentHashMap<String, SocketChannel>();
	private ExecutorService executor =Executors.newFixedThreadPool(30); 
    private  Channel channel;
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }	
    /**
     * 防止   单一机器的长连接   发生阻塞,事务采用线程池处理,长连接channel仅用于数据传输，
     * 不能在上面处理事务
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageLite msg)
			throws Exception {		
		if(msg instanceof GoogleCDO.CDOProto){			
			GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)msg;
			nettyChannelMap.put(ClientId.toString((""+proto.getLevel()).getBytes()),(SocketChannel)ctx.channel());
			Task task=new Task(proto);
			executor.submit(task);
		}
	}

	private class Task implements Runnable{
		private GoogleCDO.CDOProto proto;
		public Task(GoogleCDO.CDOProto proto){
			this.proto=proto;
		}
		@Override
		public void run() {				
			GoogleCDO.CDOProto retCDOProto=handleTrans(this.proto);	
			String key=ClientId.toString((""+proto.getLevel()).getBytes());
			SocketChannel channel=nettyChannelMap.get(key);
			channel.writeAndFlush(retCDOProto);
			nettyChannelMap.remove(key);
			
		}
		
		private GoogleCDO.CDOProto handleTrans(GoogleCDO.CDOProto proto){
			
			CDO cdoOutput=new CDO();
			int callId=0;
			try{
				CDO cdoRequest=ProtoCDOParse.ProtoParse.parse(proto);
				callId=cdoRequest.getIntegerValue(ITransService.CALL_ID);
				
				CDO cdoResponse=cdoRequest.clone();
				System.out.println("server req="+cdoRequest);
				cdoResponse.setStringValue("response", "response");
//				Return ret=serviceBus.handleTrans(cdoRequest, cdoResponse);
				Return ret=Return.OK;
				if(cdoRequest.exists("wait")){
					Thread.sleep(10*1000);
				}
				if(ret==null){
					String strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
					String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
					setOutCDO(cdoOutput," ret is null,Request method :strServiceName="+strServiceName+",strTransName="+strTransName,callId);	
					logger.error("ret is null,Request method:strServiceName="+strServiceName+",strTransName="+strTransName);
				}else{
					CDO cdoReturn=new CDO();
					cdoReturn.setIntegerValue("nCode",ret.getCode());
					cdoReturn.setStringValue("strText",ret.getText());
					cdoReturn.setStringValue("strInfo",ret.getInfo());

					cdoOutput.setCDOValue("cdoReturn",cdoReturn);
					cdoOutput.setCDOValue("cdoResponse", cdoResponse);
					
				}
			}catch(Throwable ex){
				logger.error(ex.getMessage(), ex);	
				setOutCDO(cdoOutput,"服务端处理异常:"+ex.getMessage(),callId);
			} 	
			return cdoOutput.toProto();
		}
		
		private void setOutCDO(CDO cdoOutput,String message,int callId){
			
			CDO cdoReturn=new CDO();
			cdoReturn.setIntegerValue(ITransService.CALL_ID, callId);
			cdoReturn.setIntegerValue("nCode",-1);
			cdoReturn.setStringValue("strText",message);
			cdoReturn.setStringValue("strInfo",message);
			
			cdoOutput.setCDOValue("cdoReturn",cdoReturn);
			cdoOutput.setCDOValue("cdoResponse",new CDO());		
		}
		
	}
	
}
