
package com.sz.common.listener;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.sz.service.manage.ConfigService;
@Repository
public class InitAppContextAware implements ApplicationContextAware {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private WebApplicationContext wac;
	
	@Resource
	private ConfigService configService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		// 加载配置缓存配置
		_initConfigCache();
	}

	/*
	 * 加载配置缓存配置
	 */
	private void _initConfigCache(){
	    configService.reload();
	}
}
