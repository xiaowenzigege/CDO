package com.cdo.business.rpc.route;

import com.cdo.business.rpc.client.ClientHandler;
import com.cdo.util.algorithm.CircleQueue;
import com.cdo.util.exception.QueueLengthException;

public class CircleRPCQueue<E> extends CircleQueue<E> {

	  public CircleRPCQueue() {
		 super();
	  }
	  
	  public CircleRPCQueue(int size) {
		  super(size);
	  }
	  
	  
	  public boolean addRPCHandle(E e){	   
	      for(int i=0;i<getCapacity();i++){
	    	  if(queue[i]==null){
	    		  queue[i]=e;
	    		  count++;
	    		  return true;
	    	  }
	      }
	      return false;
	   }
	  
	/**
	 * 
	 * @param handle  删除handle
	 * @return
	 * @throws QueueLengthException
	 */
	@SuppressWarnings("unchecked")
	public boolean deleteRPCHandle(ClientHandler handle) {		        
		ClientHandler dataHandle=null;
	    for(int i=0;i<getCapacity();i++){
	    	 if(queue[i]==null)
	    		 continue;
	    	 dataHandle=(ClientHandler)queue[i];
	    	 if(dataHandle.equals(handle)){
	    		 queue[i]=null;
	    		 count--;
	    		 return true;
	    	 }	    	 
	      }
        return false;
	 }
	
		
	public String toString(){
		return "capacity="+capacity+",QueueSize="+count;
	}
}
