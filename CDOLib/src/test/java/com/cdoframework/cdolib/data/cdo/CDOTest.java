package com.cdoframework.cdolib.data.cdo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CDOTest {

	@Test
	public void test() {
		CDO cdo=new CDO();
		cdo.setStringValue("str", "A");
		cdo.setStringArrayValue("strArr", new String[]{"ab","cd"});
		cdo.setIntegerValue("n", 1);
		cdo.setIntegerArrayValue("nArr", new int[]{1,3});
		cdo.setCDOValue("cdo", cdo.clone());
		cdo.setCDOArrayValue("cdos", new CDO[]{cdo.clone()});
	
		CDO  cdoResponse=cdo.clone();
		cdo.setCDOValue("cdoResponse", cdoResponse);
		CDO.release(cdo);
		//System.out.println(cdo.toString());
		CDO.release(cdo);
		CDO.release(cdo);
		CDO.release(cdo);	
	
	}

}
