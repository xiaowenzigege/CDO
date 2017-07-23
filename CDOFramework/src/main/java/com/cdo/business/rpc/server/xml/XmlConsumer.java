package com.cdo.business.rpc.server.xml;

import java.util.concurrent.TransferQueue;

import org.apache.log4j.Logger;
public class XmlConsumer implements Runnable {
	private static Logger logger=Logger.getLogger(XmlConsumer.class);
	private boolean stop=false;
	private String name;
	private TransferQueue<BusinessHandle> lnkTransQueue;
	
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public XmlConsumer(String name,TransferQueue<BusinessHandle> lnkTransQueue){
		this.name=name;
		this.lnkTransQueue=lnkTransQueue;
	}
	@Override
	public void run() {	
		while(true){
			BusinessHandle handle=null;
			try {
				if(logger.isDebugEnabled())
					logger.debug("name="+name+" is waiting to take element....,Thread="+Thread.currentThread().getId());
				handle=lnkTransQueue.take();
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
			if(handle!=null){
				handle.process();					
			}
		}
	}
	
}
