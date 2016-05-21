/**
 * 
 */
package com.cdoframework.cdolib.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengyong
 * 
 * IdGenerator factory
 *
 */
public class IdGeneratorFactory
{
	private static IdGeneratorFactory instance = new   IdGeneratorFactory();
	private Map<String,IdGenerator> mapGenerator;
	private IdGenerator defaultIdGenerator;

	/**
	 * 取得单例
	 * @return
	 */
	public static IdGeneratorFactory getInstance()
	{
		return instance;
	}
	public void put(String strObjectId,int nNodeId)
	{
		IdGenerator idc = mapGenerator.get(strObjectId);
		if(idc==null)
		{
			idc = new IdGenerator(nNodeId);
			mapGenerator.put(strObjectId,idc);
		}else
		{
			idc.setNode(nNodeId);
		}
	}
	public long getNextId(String strObjectId)
	{
		IdGenerator idc = mapGenerator.get(strObjectId);
		if(idc==null)
		{
			throw new RuntimeException(strObjectId + " does not exist");
		}
		return idc.nextId();
	}
	public long getNextId()
	{
		return this.defaultIdGenerator.nextId();
	}
	/*
	 * nNodeId要小于2的24次方
	 */
	private IdGeneratorFactory()
	{
		defaultIdGenerator = new IdGenerator();
		this.mapGenerator = new HashMap<String,IdGenerator>(10);
	}
}
