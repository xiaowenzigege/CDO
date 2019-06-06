package com.cdo.util.common;

public class NetUtil {
	/**
	 *  根据ip和掩码位数   获取该网络段的第一个有效ip地址, 格式  ip/maskbit   
	 * @param ip  10.0.207.5
	 * @param maskBit  29
	 * @return 10.0.207.1/29
	 */
	public static String getNSIDFirstIP(String ip,String maskBit){
	       int type = Integer.parseInt(maskBit);
	       int mask = 0xFFFFFFFF << (32 - type);      
	       String[] cidrIps = ip.split("\\.");
	       int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
	               | (Integer.parseInt(cidrIps[1]) << 16)
	               | (Integer.parseInt(cidrIps[2]) << 8)
	               | Integer.parseInt(cidrIps[3]);
	       String  binary=StringUtil.fillPrefixLength(Integer.toBinaryString((cidrIpAddr & mask)+1),"0",32);
	       
	       String[] ipNSID=new String[4];
	       ipNSID[3]=binary.substring(binary.length()-8);
	       ipNSID[2]=binary.substring(binary.length()-16,binary.length()-8);
	       ipNSID[1]=binary.substring(binary.length()-24,binary.length()-16);
	       ipNSID[0]=binary.substring(0,binary.length()-24);
	       String firstNSID=Integer.parseInt(ipNSID[0],2)+"."+Integer.parseInt(ipNSID[1],2)+"."+Integer.parseInt(ipNSID[2],2)+"."+Integer.parseInt(ipNSID[3],2);
	       //00001010,00000000,11001111,00000001   
		   return firstNSID+"/"+maskBit;
	   }
	/**
	 * sourcIP 与 destIP 是否在同一网段内
	 * @param sourcIP  10.27.122.36
	 * @param destIP   10.27.122.34	
	 * @param maskBit  28
	 * @return
	 */
	public static boolean isSameNSID(String sourcIP, String destIP,String maskBit){
	      int type = Integer.parseInt(maskBit);//cidr.replaceAll(".*/", "")
	      int mask = 0xFFFFFFFF << (32 - type);
	      
	      String[] sourcIPS = sourcIP.split("\\.");
	      int ipAddr1 = (Integer.parseInt(sourcIPS[0]) << 24)
	              | (Integer.parseInt(sourcIPS[1]) << 16)
	              | (Integer.parseInt(sourcIPS[2]) << 8) 
	              | Integer.parseInt(sourcIPS[3]);
	      
	      String[] destIPS = destIP.split("\\.");
	      int ipAddr2 = (Integer.parseInt(destIPS[0]) << 24)
	              | (Integer.parseInt(destIPS[1]) << 16)
	              | (Integer.parseInt(destIPS[2]) << 8)
	              | Integer.parseInt(destIPS[3]);
	      
	      return (ipAddr1 & mask) == (ipAddr2 & mask);
	}
}
