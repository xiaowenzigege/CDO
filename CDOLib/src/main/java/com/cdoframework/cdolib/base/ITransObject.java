package com.cdoframework.cdolib.base;

import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Frank
 */
public interface ITransObject
{
	///初始化对象
	Return init();

	///销毁对象
	void destroy();

	///处理事务
	Return handleTrans(CDO cdoRequest,CDO cdoResponse);
}
