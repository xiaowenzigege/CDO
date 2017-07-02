package com.cdo.util.server;

/**
 * 通过算法,将客户端与调用server 分发到服务器上
 * @author kenelLiu
 *
 */
public interface ServerScheduling {
	
	public Server getServer();
}
