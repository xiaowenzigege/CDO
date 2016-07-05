package com.cdo.business.client.rpc;

import com.cdo.google.protocol.GoogleCDO;

public class Call {

	private int callId;
	private boolean done;	
	private long sendTime;
	private GoogleCDO.CDOProto rpcResponse;
	
	public Call(int callId){
		this.callId=callId;
		this.sendTime=System.currentTimeMillis();
	}
	public int getCallId(){
		return this.callId;
	}
	public synchronized boolean done(){
		return this.done;
	}
    protected synchronized void callComplete() {
        this.done = true;
        notify();                                
    }
    
    public synchronized void setRpcResponse(GoogleCDO.CDOProto rpcResponse) {
        this.rpcResponse = rpcResponse;
        callComplete();
    }
      
    public synchronized GoogleCDO.CDOProto getRpcResponse() {
        return rpcResponse;
     }
    
	public long getSendTime(){
		return this.sendTime;
	}
}
