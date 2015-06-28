package com.sdx.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdx.common.service.SdxConstants;
import com.sdx.utils.PropertiesCacheController;

public class CommonListener implements ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		PropertiesCacheController.initBasicData();
		// 以下是需要重新加载的属性部分
		SdxConstants.loadConfig(PropertiesCacheController.getBasicData());
		String baseClassPath = null;
		baseClassPath = arg0.getServletContext().getRealPath("WEB-INF/classes");
		SdxConstants.WEB_CLASS_PATH = baseClassPath.toString();
	}

}
