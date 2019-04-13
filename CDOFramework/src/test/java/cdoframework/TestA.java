package cdoframework;

import org.bouncycastle.jce.provider.symmetric.SEED;

import com.cdo.business.BusinessService;
import com.cdoframework.cdolib.annotation.TransName;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.TransService;

public class TestA extends TransService{


	public TestA(){
		inject(this);
		
	}
	
	@TransName(name="saveHandle")
	public Return saveHandle(CDO cdoRequest,CDO cdoResponse){
		System.out.print("xxxxx");
		return null;
	}

	@Override
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse) {
		// TODO Auto-generated method stub
		return null;
	} 
}
