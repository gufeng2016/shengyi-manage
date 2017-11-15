package com.sz.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


public class ApplicationInitListener implements ServletContextListener {


	private static ServletContext context = null;
	private static Logger logger = Logger.getLogger(ApplicationInitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("===========Listener start.===========");
		context = event.getServletContext();
		try {
			logger.info("Getting properties");
			event.getServletContext().setAttribute("SystemConfigUtil", new SystemConfigUtil(context));
		} catch (Exception ioe) {
			logger.error(ioe.getMessage(), ioe);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("===========Listener stop.===========");
	}

}
