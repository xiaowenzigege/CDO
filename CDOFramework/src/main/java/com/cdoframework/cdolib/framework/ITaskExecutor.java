/**
 * 
 */
package com.cdoframework.cdolib.framework;

import com.cdoframework.cdolib.base.IActiveObject;


/**
 * @author Aaron
 *
 */
public interface ITaskExecutor extends IActiveObject
{
	/**
	 * 执行任务
	 * @param task
	 * @return
	 */
	void handleTask(Task task);
}
