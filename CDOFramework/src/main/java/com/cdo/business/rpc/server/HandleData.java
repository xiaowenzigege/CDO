package com.cdo.business.rpc.server;

import java.io.File;
import java.util.List;

import com.cdo.google.protocol.GoogleCDO;

public class HandleData {
	private GoogleCDO.CDOProto proto;
	private List<File> listFile;
	
	public GoogleCDO.CDOProto getProto() {
		return proto;
	}
	public void setProto(GoogleCDO.CDOProto proto) {
		this.proto = proto;
	}
	
	public List<File> getListFile() {
		return listFile;
	}
	public void setListFile(List<File> listFile) {
		this.listFile = listFile;
	}
	 
}
