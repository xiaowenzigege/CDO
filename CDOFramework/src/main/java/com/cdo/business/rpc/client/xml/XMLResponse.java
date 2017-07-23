package com.cdo.business.rpc.client.xml;


import com.cdo.business.rpc.client.IRPCResponse;


public class XMLResponse implements IRPCResponse{

	private int  callId;
	private String strXML;
	
	public XMLResponse(){}
	public XMLResponse(int callId,String strXML){
		this.callId=callId;
		this.strXML=strXML;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}
	public String getStrXML() {
		return strXML;
	}
	public void setStrXML(String strXML) {
		this.strXML = strXML;
	}







}
