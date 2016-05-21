 package com.cdo.util.common;

 import java.util.UUID;

/**
 *  
 * @author KenelLiu
 *
 */
public class UUidGenerator {
  public static synchronized String genenator(){
	  return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
