package com.cdo.util.server;

public class Server {
	private String hostPost; //格式 ip:port
	private int weight=1;
	
    public Server(String hostPost, int weight) {
        super();
        this.hostPost = hostPost;
        this.weight = weight;
    }
    
	public String getHostPost() {
		return hostPost;
	}
	public void setHostPost(String hostPost) {
		this.hostPost = hostPost;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return " hostPort="+this.hostPost+",weight="+this.weight;
	}
}
