/**
 * $Header: /data/cvs/chinasiteplatform/src/java/com/gs/sitecore/common/utils/SystemConfig.java,v 1.7 2009/03/05 06:25:24 mzheng Exp $ 
 * $Revision: 1.7 $ 
 * $Date: 2009/03/05 06:25:24 $ 
 * 
 * ==================================================================== 
 * 
 * Copyright (c) 2006 Media Data Systems Pte Ltd All Rights Reserved. 
 * This software is the confidential and proprietary information of 
 * Media Data Systems Pte Ltd. You shall not disclose such Confidential 
 * Information. 
 * 
 * ==================================================================== 
 * 
 */

package com.sz.common.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.sz.pojo.app.SystemConfig;

/**
 * Property file loader, this class will load the properties file in $CONFIG_PATH/config into Properties.
 * and it also provide a method to read the value of the properties by key. 
 * @author Jimmy Shi
 * @version $Id: SystemConfig.java,v 1.7 2009/03/05 06:25:24 mzheng Exp $
 */
public class SystemConfigUtil {

	private static Properties properties = new Properties();

	private static Logger logger = Logger.getLogger(SystemConfigUtil.class);

	public SystemConfigUtil(ServletContext context){
		initProperties(context);
	}


	/**
	 * Load some config files into properties object.
	 * 
	 * @return ""
	 */
	private static void initProperties(ServletContext context) {
		Properties prop = new Properties();
		try {
            String[] files = {"messages.properties", "AppConfig.properties"};
            for(String file : files) {
                InputStream is = SystemConfigUtil.class.getClassLoader().getResourceAsStream(file);
                prop.load(is);
                if(null!=is){
                    is.close();
                }
            }

		} catch (Exception e) {
			logger.error("Init Properties error!" + e.getMessage(), e);
		}
		properties = prop;
		logger.info("===Init all of the properties success===");
	}

	/**
	 * Get property value by property name.
	 * @param name The property name
	 * @return The property value.
	 */
	public static String getProperty(String name) {
		return properties.getProperty(name);
	}
	
	/**
	 * 判断是否是本地测试环境
	 * @return
	 */
	public boolean getTest(){
	    return getTestLocal();
	}
	
	/**
     * 判断是否是本地测试环境
     * @return
     */
	public static boolean getTestLocal(){
	    String value = properties.getProperty("test.local.sign");
        if(value != null && value.trim().equals("1")) {
            return true;
        }
        return false;
	}
}
