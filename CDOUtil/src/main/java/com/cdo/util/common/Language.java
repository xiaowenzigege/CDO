package com.cdo.util.common;

/**
 * 
 * @author KenelLiu
 *
 */
public enum Language {
	 zh_CH("zh_cn","086","中国"),en_US("en_us","001","美国");
     private String local;
     private String localCode;
     private String country;
     private Language(String local,String localCode,String country){
         this.local = local;
         this.localCode = localCode;
         this.country = country;
     }

	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
