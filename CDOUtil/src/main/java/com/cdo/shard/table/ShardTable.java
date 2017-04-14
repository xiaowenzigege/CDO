package com.cdo.shard.table;

import java.util.List;
import java.util.regex.Pattern;

import com.cdo.shard.Hashing;
import com.cdo.shard.Sharded;



public class  ShardTable extends Sharded<Table, ShardTableInfo>{

	public ShardTable(List<ShardTableInfo> shards) {
		super(shards);		
	}
	
	
	public ShardTable(List<ShardTableInfo> shards, Hashing algo) {
		 super(shards, algo);
	 }

	public ShardTable(List<ShardTableInfo> shards, Pattern tagPattern) {
		 	super(shards,tagPattern);
		  }

    public ShardTable(List<ShardTableInfo> shards, Hashing algo, Pattern tagPattern) {
    	super(shards,algo,tagPattern);
    }

    public String getTableName(String key){
    	Table table = getShard(key);
    	return table.getName();
    } 
    
}
