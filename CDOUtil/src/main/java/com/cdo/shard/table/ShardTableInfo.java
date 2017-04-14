package com.cdo.shard.table;


import com.cdo.shard.ShardInfo;
import com.cdo.shard.Sharded;

public class ShardTableInfo extends ShardInfo<Table> {	
	private String shardName;
	private String tableName;
	
	public ShardTableInfo(String tableName){		
		this(null,tableName);
	}
	
	public ShardTableInfo(String shardName,String tableName){
		this(shardName,tableName,Sharded.DEFAULT_WEIGHT);
	}
	
	public ShardTableInfo(String shardName,String tableName,int weight){
		super(weight);
		this.shardName=shardName;
		this.tableName=tableName;		
	}
	
	@Override
	protected Table createResource() {
		return new Table(this);
	}

	public String getTableName(){
		return this.tableName;
	}
	@Override
	public String getName() {
		return this.shardName;
	}

}
