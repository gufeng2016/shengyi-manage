package com.sz.service.game.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.dao.app.SystemConfigDao;
import com.sz.pojo.app.SystemConfig;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.service.app.SystemConfigService;

/**
 * @author : yunxing.li
 * @date   : 2017年6月26日 上午11:24:00
 * @version: v1.0
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

	@Autowired
	private SystemConfigDao systemConfigDao;

	@Override
	public PaginationResult<SystemConfig> queryByPage(String key, PageInfo page) throws Exception {
		int total = systemConfigDao.count(key);
		List<SystemConfig> result = systemConfigDao.queryByPage(key, page);
		return new PaginationResult<SystemConfig>(total, result);
	}

	@Override
	public boolean update(SystemConfig config) throws Exception {
		return systemConfigDao.update(config);
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		return systemConfigDao.deleteById(id);
	}

	@Override
	public boolean batchDelete(List<Integer> ids) throws Exception {
		return systemConfigDao.batchDeleteByIds(ids);
	}

	@Override
	public boolean checkKey(String key) throws Exception {
		return systemConfigDao.queryByKey(key) != null;
	}

	@Override
	public boolean add(SystemConfig config) throws Exception {
		return systemConfigDao.add(config);
	}
	
	@Override
	public SystemConfig queryById(Integer id) throws Exception {
		return systemConfigDao.queryById(id);
	}
	
	@Override
	public SystemConfig queryByKey(String key) throws Exception {
		return systemConfigDao.queryByKey(key);
	}
}
