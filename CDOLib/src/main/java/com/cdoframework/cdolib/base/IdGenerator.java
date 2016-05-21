/**
 * 
 */
package com.cdoframework.cdolib.base;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author dengyong
 * 
 * 生成64位的Id，前24位为NodeId，后40位为Id序号
 *
 */
public class IdGenerator
{
	private AtomicLong atomLong; 
	
	private int nNodeId;
	/**
	 * 设置IdGenerator的NodeId
	 * @param nNodeId nNodeId要位于0和2的24次方
	 */
	public void setNode(int nNodeId)
	{
		this.nNodeId=nNodeId;
	}
	
	public long nextId()
	{
		long lId=nNodeId;
		lId<<=40;
		lId+=atomLong.addAndGet(1);
		
		return lId;
	}

	/*
	 * nNodeId要小于2的24次方
	 */
	public IdGenerator()
	{
		this.nNodeId=0;
		this.atomLong=new AtomicLong(0);
	}

	/*
	 * nNodeId要小于2的24次方
	 */
	public IdGenerator(int nNode)
	{
		this.nNodeId=nNode;
		this.atomLong=new AtomicLong(0);
	}
}
