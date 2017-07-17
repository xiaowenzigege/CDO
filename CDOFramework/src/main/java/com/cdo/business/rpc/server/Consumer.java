package com.cdo.business.rpc.server;

import java.util.concurrent.TransferQueue;

import org.apache.log4j.Logger;

import com.cdo.google.protocol.GoogleCDO;

public class Consumer implements Runnable {
	private static Logger logger=Logger.getLogger(Consumer.class);
	private boolean stop=false;
	private String name;
	private TransferQueue<GoogleCDO.CDOProto> lnkTransQueue;
	private RPCServerHandler handle;

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public Consumer(String name,TransferQueue<GoogleCDO.CDOProto> lnkTransQueue,RPCServerHandler handle){
		this.name=name;
		this.lnkTransQueue=lnkTransQueue;
		this.handle=handle;
	}
	@Override
	public void run() {	
		while(true){
			GoogleCDO.CDOProto proto=null;
			try {
				if(logger.isDebugEnabled())
					logger.debug("name="+name+" is waiting to take element....,Thread="+Thread.currentThread().getId());
				proto=lnkTransQueue.take();
				if(logger.isDebugEnabled())							
					logger.debug("name="+name+" received Element....,Thread="+Thread.currentThread().getId());
			} catch (InterruptedException  ex) {
				if(logger.isDebugEnabled())
					logger.debug("name="+name+"  Thread break,sleep 0.5 seconds,continue run()");
				try{Thread.sleep(500);}catch(Exception e){}									
			}catch(Exception ex){
				if(lnkTransQueue==null || stop){
					logger.warn("name="+name+",stop="+stop+",lnkTransQueue="+lnkTransQueue+",break Consumer queue");					
					break;
				}
			}
			if(proto!=null){
				handle.process(proto,null);					
			}
		}
	}
	
	/**
	public void run() {	
		while(true){
			HandleData handleData=null;
			try {
				if(logger.isDebugEnabled())
					logger.debug("Consumer is waiting to take element....Thread="+Thread.currentThread().getId());
				handleData=lnkTransQueue.take();
				if(logger.isDebugEnabled())
					logger.debug("Consumer received Element:"+handleData+",Thread="+Thread.currentThread().getId());					
			} catch (Exception  ex) {
				if(logger.isInfoEnabled())
					logger.info("Consumer Thread break,sleep 1 seconds,continue run()");
				try{Thread.sleep(1000);}catch(Exception e){}									
			}
			if(handleData!=null){
				handle.process(handleData.getProto(), handleData.getListFile());
			}
		}
	}	
	**/
}
