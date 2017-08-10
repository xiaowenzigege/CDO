package com.cdo.util.algorithm;

import com.cdo.util.exception.QueueLengthException;

public class CircleQueue<E> {
	  static final int defaultSize = 5; //默认队列的长度
	  protected int front;  //队头
	  protected int rear;   //队尾
	  protected int count;  //统计队列元素个数
	  protected int capacity; //队的最大长度
	  protected Object[] queue;  //队列
	  protected int index;  //依次循环   获取队列元素
	  
	  public CircleQueue() {
		 init(defaultSize);
	  }
	  
	  public CircleQueue(int capacity) {
		  init(capacity);
	  }
	  
	  private void init(int capacity) {		  
		     this.capacity = capacity;
		     index=front = rear=0;
		     count = 0;
		     queue = new Object[capacity];
		}

	  public void add(E e) throws QueueLengthException {	   
	      if (isFull()) {
	          throw new QueueLengthException("队列已满！");
	      }
	      queue[rear] = e;
	      rear = (rear + 1) % capacity;
	      count++;
	    }
	  
	@SuppressWarnings("unchecked")
	public E delete() throws QueueLengthException {		        
        if (isEmpty()) {
            throw new QueueLengthException("队列为空！");
        }
        E obj = (E)queue[front];
        queue[front]=null;
        front = (front + 1) % capacity;
        count--;
        return obj;
	 }
	  
	/**
	 * 获取最前面的个数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E getCircleFront(){	        
	          if (!isEmpty()) {
	        	  E obj=null;
	        	  try{
	        		  obj =(E)(queue[index]);
	        	  }catch(ArrayIndexOutOfBoundsException ex){
	        		  obj =(E)(queue[0]);//并发出现异常 返回第一个，不做同步,同步导致并发下降.	        		  
	        	  }	        	
	        	  index++;
	        	  index = index% capacity;
	        	  return obj;
	        } else {
	              return null;
	         }
	 }

    public boolean isEmpty() {	        
	           return count == 0;
	    }
    
    public boolean isFull(){
    	//return count > 0 && front == rear;
    	return count==capacity;
    }
    
    public int getQueueSize(){
    	return count;
    }	
    
    public int getCapacity(){
    	return capacity;
    }
}
