 package com.cdo.util.common;

 import java.nio.ByteBuffer;
import java.util.UUID;

/**
 *  
 * @author KenelLiu
 *
 */
public class UUidGenerator {
	  /** The byte array of a UUID should be 16 */ 
	public static final int BYTE_LENGTH = 16;
	private static final int shiftWidth = 8;
	  
	public static synchronized String genenator(){
		  return UUID.randomUUID().toString().replaceAll("-", "");
	  }
  
	public static byte[] getUUId() {
	    UUID uuid = UUID.randomUUID();
	    return toBytes(uuid);
	  }

	public static String toString(byte[] uuid) {		    
		    if (uuid == null || uuid.length == 0) {
		      return "";
		    }
		    // otherwise should be 16 bytes
		    if(uuid.length != BYTE_LENGTH)
		    	throw new IllegalArgumentException("clientId should be 16 bytes ");
		    long msb = getMsb(uuid);
		    long lsb = getLsb(uuid);
		    return (new UUID(msb, lsb)).toString();
	}
	  	 
	public static byte[] toBytes(String strUUId) {
	    if (strUUId == null || "".equals(strUUId)) {
	      return new byte[0];
	    }
	    UUID uuid = UUID.fromString(strUUId);
	    return toBytes(uuid);
	  }	  

	 private static  byte[] toBytes(UUID uuid){		 
		    ByteBuffer buf = ByteBuffer.wrap(new byte[BYTE_LENGTH]);
		    buf.putLong(uuid.getMostSignificantBits());
		    buf.putLong(uuid.getLeastSignificantBits());
		    return buf.array();
	 }	
	 
	  private static long getMsb(byte[] uuid) {
		    long msb = 0;
		    for (int i = 0; i < BYTE_LENGTH/2; i++) {
		      msb = (msb << shiftWidth) | (uuid[i] & 0xff);
		    }
		    return msb;
		  }
		  
	  private static long getLsb(byte[] uuid) {
		    long lsb = 0;
		    for (int i = BYTE_LENGTH/2; i < BYTE_LENGTH; i++) {
		      lsb = (lsb << shiftWidth) | (uuid[i] & 0xff);
		    }
		    return lsb;
		  }	
	  

  public static class ClientId { 	  
	  public static byte[] getClientId() {
		    return UUidGenerator.getUUId();
		  }
		    
	  public static String toString(byte[] clientId) {
		  	return UUidGenerator.toString(clientId);
		  }
  }
}
