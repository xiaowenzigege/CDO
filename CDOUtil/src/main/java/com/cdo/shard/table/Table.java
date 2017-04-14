package com.cdo.shard.table;

public class Table {
	private String name;
	
    public Table(ShardTableInfo shardInfo) {
		  this.name=shardInfo.getTableName();
	}

	public String getName(){
		return this.name;
	}
}
