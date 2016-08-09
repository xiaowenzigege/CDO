package com.cdo.business.app;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdoframework.cdolib.base.Return;
/**
 * 该类主要用于启动   后台定时任务 处理数据使用
 * @author KenelLiu
 *
 */
public class AppBusiness {
	private static final Logger logger = Logger.getLogger(AppBusiness.class);
	private final static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args){
    	Return ret = Return.OK;
		BusinessService app = BusinessService.getInstance();		
		try
		{
			ret =app.start();			
			if(ret.getCode()==0 && logger.isInfoEnabled());
				logger.info("business service started-----------------");
			if(ret.getCode()==0){	
				latch.await();
			}else{
				logger.fatal(ret.getText());
				logger.fatal("||*****************************************||\r\n||*****************************************||\r\n||  started faild and will exit            ||\r\n||*****************************************||\r\n||*****************************************||");
				System.exit(0);
			}			
		}catch(Throwable e){
			ret	=Return.valueOf(-1,e.getLocalizedMessage());
			logger.error(e.getMessage(),e);
			logger.fatal("||*****************************************||\r\n||*****************************************||\r\n||  started faild and will exit            ||\r\n||*****************************************||\r\n||*****************************************||");
			System.exit(-1);
		}			
    }
        
}
