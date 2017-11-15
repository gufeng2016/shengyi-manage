package com.sz.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 类描述：多数据源中需要根据key查找当前数据源，该类就是从数据源上下文中获取当前数据源的key
 * 创建者： wuhaifei
 * 项目名称： 7road-common
 * 创建时间： 2013-7-25 下午06:28:57
 * 版本号： v1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceKey();
	}

}
