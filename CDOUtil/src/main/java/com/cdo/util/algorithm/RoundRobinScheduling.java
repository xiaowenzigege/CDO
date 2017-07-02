package com.cdo.util.algorithm;
import java.math.BigInteger;
import java.util.List;

import com.cdo.util.exception.RoundRobinException;
import com.cdo.util.server.Server;
import com.cdo.util.server.ServerScheduling;

public class RoundRobinScheduling implements ServerScheduling{
	    private int currentIndex = -1;// 上一次选择的服务器
	    private int currentWeight = 0;// 当前调度的权值
	    private int maxWeight = 0; // 最大权重
	    private int gcdWeight = 0; //所有服务器权重的最大公约数
	    private int serverCount = 0; //服务器数量
	    private List<Server> serverList; //服务器集合
	    
	    public RoundRobinScheduling(List<Server> serverList) throws RoundRobinException{
	    	if(serverList==null)
	    		throw new RoundRobinException(" serverList is null");
	    	if(serverList.size()==0)
	    		throw new RoundRobinException(" serverList size="+serverList.size());
	    	
	    	this.serverList=serverList;
	        currentIndex = -1;
	        currentWeight = 0;
	        serverCount = serverList.size();
	        maxWeight = getMaxWeightForServers(serverList);
	        gcdWeight = getGCDForServers(serverList);
	    }
	    
	    public List<Server> getServerList(){
	    	return this.serverList;
	    }
	    /**
	     * 返回最大公约数
	     * @param a
	     * @param b
	     * @return
	     */
	    private  int gcd(int a, int b) {
	        BigInteger b1 = new BigInteger(String.valueOf(a));
	        BigInteger b2 = new BigInteger(String.valueOf(b));
	        BigInteger gcd = b1.gcd(b2);
	        return gcd.intValue();
	    }
	    
	     
	    /**
	     * 返回所有服务器权重的最大公约数
	     * @param serverList
	     * @return
	     */
	    private  int getGCDForServers(List<Server> serverList ) {
	        int w = 0;
	        for (int i = 0, len = serverList.size(); i < len - 1; i++) {
	            if (w == 0) {
	                w = gcd(serverList.get(i).getWeight(), serverList.get(i + 1).getWeight());
	            } else {
	                w = gcd(w, serverList.get(i + 1).getWeight());
	            }
	        }
	        return w;
	    }
	    

	    /**
	     * 返回所有服务器中的最大权重
	     * @param serverList
	     * @return
	     */
	    private  int getMaxWeightForServers(List<Server> serverList) {
	        int w = 0;
	        for (int i = 0, len = serverList.size(); i < len - 1; i++) {
	            if (w == 0) {
	                w = Math.max(serverList.get(i).getWeight(), serverList.get(i + 1).getWeight());
	            } else {
	                w = Math.max(w, serverList.get(i + 1).getWeight());
	            }
	        }
	        return w;
	    }
	    
	    /**
	     *  算法流程： 
	     *  假设有一组服务器 S = {S0, S1, …, Sn-1}
	     *  有相应的权重，变量currentIndex表示上次选择的服务器
	     *  权值currentWeight初始化为0，currentIndex初始化为-1 ，当第一次的时候返回 权值取最大的那个服务器，
	     *  通过权重的不断递减 寻找 适合的服务器返回，直到轮询结束，权值返回为0 
	     */
	    @Override
	    public Server getServer(){
	    	if(serverList.size()==1)
	    		return serverList.get(0);
	        while (true) {
	            currentIndex = (currentIndex + 1) % serverCount;
	            if (currentIndex == 0) {
	                currentWeight = currentWeight - gcdWeight;
	                if (currentWeight <= 0) {
	                    currentWeight = maxWeight;
	                    if (currentWeight == 0)
	                        return serverList.get(0);
	                }
	            }
	            if (serverList.get(currentIndex).getWeight() >= currentWeight) {
	                return serverList.get(currentIndex);
	            }
	        }
	    }
}
