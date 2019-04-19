package com.cdo.util.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
	private static final float   DEFAULT_LOAD_FACTOR = 0.75f; //HashMap  Default The load factor
	private LinkedHashMap<K,V>   map;  
	private int  cacheSize;
	
	
	public LRUCache(int cacheSize){		
		this.cacheSize=cacheSize;
		int capacity=(int)Math.ceil(cacheSize/DEFAULT_LOAD_FACTOR) + 1;
		//使用linkHashMap中的方法进行LRU算法
		map = new LinkedHashMap<K,V>(capacity, DEFAULT_LOAD_FACTOR, true) {  			     
			      private static final long serialVersionUID = 1;  
			      @Override 
			      protected boolean removeEldestEntry (Map.Entry<K,V> eldest) {  
			         return size() > LRUCache.this.cacheSize; }
			      };   
	}
	
	public synchronized V get (K key) {  
		   return map.get(key); 
	}  
	
	public synchronized void put (K key, V value) {  
		   map.put (key, value); 
	}  
	
	public synchronized boolean containsKey(K key){
		return map.containsKey(key);
	}
	public synchronized void clear() {  
		   map.clear(); 
	}  
	public synchronized int size() {  
		   return map.size();
	}  
	public synchronized V remove(K key){
		  return map.remove(key);
	}
	
	public synchronized Collection<Map.Entry<K,V>> getAll() {  
		   return new ArrayList<Map.Entry<K,V>>(map.entrySet()); 
		  } 
}
