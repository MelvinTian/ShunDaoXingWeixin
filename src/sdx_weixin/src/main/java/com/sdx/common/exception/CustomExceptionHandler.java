package com.sdx.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionHandler implements HandlerExceptionResolver {
	private static final Log logger = LogFactory
			.getLog(CustomExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {
		logger.error("oms_business custom_error", exception);
		try {
			response.setStatus(500);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			if (exception instanceof NestedUmaException) {
				writer.print(((NestedUmaException) exception).getMessage()
						+ ",请您联系管理员!");
			} else {
				writer.print("出错了哦，请再试试或联系管理员");
			}
			response.flushBuffer();
		} catch (IOException e) {
			logger.error("io_error", e);
			e.printStackTrace();
		}
		return null;
	}
}
