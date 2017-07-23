package com.cdo.business.web.client;

import java.util.Iterator;

import nanoxml.XMLElement;

import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.ParseXmlCDO;



public class ParseHTTPXmlCDO extends ParseXmlCDO {

	public static void xmlToCDO(String strXML,CDO cdoReturn,CDO cdoResponse)
	{
		XMLElement xmlNode=new XMLElement();
		xmlNode.parseString(strXML);
		
		Iterator enumNodes=xmlNode.enumerateChildren();
		while(enumNodes.hasNext())
		{
			XMLElement node=(XMLElement)enumNodes.next();
			String strTag=node.getName();
			if(strTag.equals("CDOF"))
			{
				String strName	=node.getStringAttribute("N");
				if(strName.equals("cdoReturn")){
					xml2CDO(cdoReturn,(XMLElement)node.getChildren().get(0),false);
				}
				if(strName.equals("cdoResponse")){
					xml2CDO(cdoResponse,(XMLElement)node.getChildren().get(0),true);
				}
			}
		}	
	}	
	
}
