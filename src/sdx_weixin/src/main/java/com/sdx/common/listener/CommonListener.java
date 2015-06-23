package com.sdx.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
		// PropertiesCacheController.initJsonData(arg0.getServletContext().getRealPath("/"));
	}

}
