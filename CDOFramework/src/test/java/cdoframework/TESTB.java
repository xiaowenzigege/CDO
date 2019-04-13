package cdoframework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.cdo.business.BusinessService;
import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.IDataEngine;
import com.cdoframework.cdolib.servicebus.ITransService;

public class TESTB {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		try {

			
			ITransService transService=(ITransService) Class.forName("cdoframework.TestA").newInstance();			
			CDO cdoRequest=new CDO();
			CDO cdoResponse=new CDO();
			cdoRequest.setStringValue("strTransName", "saveHandle");			
			Return ret = transService.processTrans(cdoRequest, cdoResponse);

			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
