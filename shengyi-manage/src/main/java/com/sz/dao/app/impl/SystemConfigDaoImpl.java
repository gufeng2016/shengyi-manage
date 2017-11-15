package com.sz.dao.app.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sz.common.annotation.ChooseDataSource;
import com.sz.common.constant.DataSourceConstant;
import com.sz.common.db.JDBCBaseDao;
import com.sz.dao.app.SystemConfigDao;
import com.sz.pojo.app.SystemConfig;
import com.sz.pojo.manage.page.PageInfo;

/**
 * @author : yunxing.li
 * @date   : 2017年6月26日 上午10:57:40
 * @version: v1.0
 */
@Repository
public class SystemConfigDaoImpl implements SystemConfigDao {
	
	@Autowired
	private JDBCBaseDao baseDao;
	
	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public SystemConfig queryByKey(String key) throws Exception {
		StringBuffer sql = new StringBuffer(SQL_QUERY).append(" AND `key` = ?");
		List<SystemConfig> list = baseDao.queryList(SystemConfig.class, sql.toString(), key);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public SystemConfig queryById(Integer id) throws Exception {
		return baseDao.queryById(SystemConfig.class, id);
	}
	
	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public int count(String key) throws Exception {
		StringBuffer sql = new StringBuffer(SQL_COUNT);
		List<Object> params = Lists.newArrayList();
		if(!Strings.isNullOrEmpty(key)){
			sql.append(" AND `key` like ?");
			params.add("%" + key + "%");
		}
		return baseDao.queryForInt(sql.toString(), params.toArray());
	}

	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public List<SystemConfig> queryByPage(String key, PageInfo page) throws Exception {
		StringBuffer sql = new StringBuffer(SQL_QUERY);
		List<Object> params = Lists.newArrayList();
		if(!Strings.isNullOrEmpty(key)){
			sql.append(" AND `key` like ?");
			params.add("%" + key + "%");
		}
		sql.append(" ORDER BY id LIMIT ?,?");
		params.add(page.getStartRow());
		params.add(page.getPageSize());
		return baseDao.queryList(SystemConfig.class, sql.toString(), params.toArray());
	}
	
	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public boolean add(SystemConfig config) throws Exception {
		return baseDao.update(SQL_INSERT, config.getKey(), config.getValue(), config.getComment()) > 0;
	}

	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public boolean update(SystemConfig config) throws Exception {
		return baseDao.update(SQL_UPDATE, config.getKey(), config.getValue(), config.getComment(), config.getId()) > 0;
	}

	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public boolean deleteById(Integer id) throws Exception {
		StringBuffer sql = new StringBuffer(SQL_DELETE).append(" AND id = ?");
		return baseDao.update(sql.toString(), id) > 0;
	}

	@Override
	@ChooseDataSource(mainType = DataSourceConstant.BIZ_TYPE_CONF, bizType = DataSourceConstant.PROJECT_SUFFIX)
	public boolean batchDeleteByIds(List<Integer> ids) throws Exception {
		final List<Integer> list = new ArrayList<Integer>(ids);
		StringBuffer sql = new StringBuffer(SQL_DELETE).append(" AND id = ?");
		BatchPreparedStatementSetter statement = new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setObject(1, list.get(i));
			}
			@Override
			public int getBatchSize() {
				return list.size();
			}
		};
		int[] res = baseDao.batchUpdate(sql.toString(), statement);
		if(res == null || res.length < list.size()){
			return false;
		}
		for(int re : res){
			if(re < 1) return false;
		}
		return true;
	}


}
