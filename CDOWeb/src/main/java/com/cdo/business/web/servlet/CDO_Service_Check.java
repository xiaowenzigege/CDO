
package com.cdo.business.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdoframework.cdolib.servicebus.IServiceBus;

public class CDO_Service_Check extends HttpServlet {

	private static final long serialVersionUID = -9079375979374666080L;
	// 静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private Logger logger = Logger.getLogger(CDO_Service_Check.class);

	/*
	 * 通过监控程序监控：
	 * 
	 * 正确的页面 输出ok； 错误的 输出错误提示信息；
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 扑捉异常
		try {
			// 通过businessService获取ServiceBus实力
			BusinessService businessService = BusinessService.getInstance();
			// 正确性验证
			if (null != businessService) {
				IServiceBus serviceBus = businessService.getServiceBus();
				if (null != serviceBus) {
					// 调用ServiceBus里的验证方法，true:正常 false：不正常
					if (serviceBus.testServerState()) {
						// 验证通过输出ok
						response.getWriter().write("ok");
					} else {
						final String error = serviceBus.detectServerState();
						// final String error =
						// "error <input type=\"hidden\" name=\"woyo-state\" id=\"woyo-state\" value=\"+serviceBus.detectServerState()+\"/>";
						// 验证不通过输出false
						response.getWriter().write(error);
					}
					// serviceBus null
				} else {
					response.getWriter().write("ServiceBus is null");
				}
				// businessService null
			} else {
				response.getWriter().write(" BusinessService is null");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.getWriter().write(e.getMessage());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
