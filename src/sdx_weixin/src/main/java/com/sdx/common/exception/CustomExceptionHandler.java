package com.sdx.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionHandler implements HandlerExceptionResolver
{
	private static final Log logger = LogFactory.getLog(CustomExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
	{
		logger.error("sdx_weixin custom_error", exception);
		Map<String, String> errorInfo = new HashMap<String, String>();
		String errorPage = "500_page";
		if (exception instanceof JsonRequestException)
		{
			errorPage = "500_json";
			errorInfo.put("message", ((JsonRequestException) exception).getMsg());
			errorInfo.put("code", ((JsonRequestException) exception).getCode());
		} else {
			errorInfo.put("message", ((PageRequestException) exception).getMsg());
		}
		
		return new ModelAndView("/error/" + errorPage, "error", errorInfo);
	}
}
