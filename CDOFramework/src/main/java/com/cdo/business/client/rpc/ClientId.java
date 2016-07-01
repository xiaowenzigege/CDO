package com.cdo.business.client.rpc;

import java.nio.ByteBuffer;
import java.util.UUID;


public class ClientId {
	  /** The byte array of a UUID should be 16 */ 
	  public static final int BYTE_LENGTH = 16;
	  private static final int shiftWidth = 8;
	  
	  public static byte[] getClientId() {
		    UUID uuid = UUID.randomUUID();
		    ByteBuffer buf = ByteBuffer.wrap(new byte[BYTE_LENGTH]);
		    buf.putLong(uuid.getMostSignificantBits());
		    buf.putLong(uuid.getLeastSignificantBits());
		    return buf.array();
		  }
		    
	  public static String toString(byte[] clientId) {
		    // clientId can be null or an empty array
		    if (clientId == null || clientId.length == 0) {
		      return "";
		    }
		    // otherwise should be 16 bytes
		    if(clientId.length != BYTE_LENGTH)
		    	throw new IllegalArgumentException("clientId should be 16 bytes ");
		    long msb = getMsb(clientId);
		    long lsb = getLsb(clientId);
		    return (new UUID(msb, lsb)).toString();
		  }
		  
	  public static long getMsb(byte[] clientId) {
		    long msb = 0;
		    for (int i = 0; i < BYTE_LENGTH/2; i++) {
		      msb = (msb << shiftWidth) | (clientId[i] & 0xff);
		    }
		    return msb;
		  }
		  
	  public static long getLsb(byte[] clientId) {
		    long lsb = 0;
		    for (int i = BYTE_LENGTH/2; i < BYTE_LENGTH; i++) {
		      lsb = (lsb << shiftWidth) | (clientId[i] & 0xff);
		    }
		    return lsb;
		  }
		  
		  /** Convert from clientId string byte[] representation of clientId */
	  public static byte[] toBytes(String id) {
		    if (id == null || "".equals(id)) {
		      return new byte[0];
		    }
		    UUID uuid = UUID.fromString(id);
		    ByteBuffer buf = ByteBuffer.wrap(new byte[BYTE_LENGTH]);
		    buf.putLong(uuid.getMostSignificantBits());
		    buf.putLong(uuid.getLeastSignificantBits());
		    return buf.array();
		  }
}
