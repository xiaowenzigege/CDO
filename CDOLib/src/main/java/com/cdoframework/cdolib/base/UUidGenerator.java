package com.cdoframework.cdolib.base;

import java.util.UUID;

public class UUidGenerator {
	
	public synchronized static String  genenator(){		
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}
