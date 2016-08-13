package com.cdo.business.rpc.client;

public class Call {

	private int callId;
	private boolean done;	
	private long sendTime;
	private RPCResponse response;
	
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
    
    public synchronized void setRPCResponse(RPCResponse response) {
        this.response=response;
        callComplete();
    }
      
    public synchronized RPCResponse getRPCResponse() {
        return response;
     }

	public long getSendTime(){
		return this.sendTime;
	}
}
