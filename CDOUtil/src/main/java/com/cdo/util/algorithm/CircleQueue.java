package com.cdo.util.algorithm;

import com.cdo.util.exception.QueueLengthException;

public class CircleQueue<E> {
	  static final int defaultSize = 5; //默认队列的长度
	  int front;  //队头
	  int rear;   //队尾
	  int count;  //统计元素个数的计数器
	  int maxSize; //队的最大长度
	  Object[] queue;  //队列
	
	  
	  public CircleQueue() {
		 init(defaultSize);
	  }
	  
	  public CircleQueue(int size) {
		  init(size);
	  }
	  
	  private void init(int size) {
		     maxSize = size;
		     front = rear=0;
		     count = 0;
		     queue = new Object[size];
		}

	  public void add(E e) throws QueueLengthException {	   
	      if (count > 0 && front == rear) {
	          throw new QueueLengthException("队列已满！");
	      }
	      queue[rear] = e;
	      rear = (rear + 1) % maxSize;
	      count++;
	    }
	  
	@SuppressWarnings("unchecked")
	public E delete() throws QueueLengthException {		        
        if (isEmpty()) {
            throw new QueueLengthException("队列为空！");
        }
        E obj = (E)queue[front];
        queue[front]=null;
        front = (front + 1) % maxSize;
        count--;
        return obj;
	 }
	  

	@SuppressWarnings("unchecked")
	public E getCircleFront(){	        
	          if (!isEmpty()) {
	        	  E obj =(E)(queue[front]);	        	
	        	  front++;
	        	  front = front% maxSize;
	        	  return obj;
	        } else {
	              return null;
	         }
	 }

    public boolean isEmpty() {	        
	           return count == 0;
	    }	  
	
    public static void main(String[] args){
    	CircleQueue<String> q=new CircleQueue<String>(1);
    	q.add("a");


    	for(int i=0;i<10;i++){
    		System.out.println(q.getCircleFront());
    	}
    	q.add("d");

    	for(int i=0;i<6;i++){
    		System.out.println(q.getCircleFront());
    	}    	
    	
    }
}
