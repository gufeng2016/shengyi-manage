package com.sz.dao.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sz.common.db.JDBCBaseDao;
import com.sz.dao.manage.ConfigDao;
import com.sz.pojo.manage.config.Config;

/**
 * @author jiangfan.zhou
 * date Jun 4, 2014
 */
@Repository
public class ConfigDaoImpl implements ConfigDao {

	@Autowired
	private JDBCBaseDao baseDao;
	
	private static final String TABLE = "`t_config`";

	private static final String QUERY_CONFIG = "SELECT * FROM " + TABLE + " where delete_state = 1 ";

	private static final String SQL_ADD = "INSERT INTO " + TABLE + "(`key`,`value`,`remark`) VALUES (?,?,?);";
	
	private static final String SQL_DELETE = "UPDATE " + TABLE + " SET delete_state = -1 WHERE id=?";
	
	private static final String SQL_UPDATE = "UPDATE " + TABLE + " SET `key`=?, `value`=?, `remark`=? WHERE id=?";

	private static final String SQL_QUERY_COUNT = "SELECT COUNT(1) AS count FROM " + TABLE + " where delete_state = 1 ";

	@Override
	public List<Config> queryAllConfig() throws Exception {
		StringBuilder sql = new StringBuilder(QUERY_CONFIG);
		sql.append(" order by `key`");
		return baseDao.query(sql.toString(), new Object[]{}, Config.class);
	}
	
	@Override
	public List<Config> queryByKeyFuzzy(String key) throws Exception {
		StringBuffer sql = new StringBuffer(QUERY_CONFIG);
		List<Object> params = Lists.newArrayList();
		if(!Strings.isNullOrEmpty(key)){
			sql.append(" AND `key` like ?");
			params.add("%" + key + "%");
		}
		sql.append(" order by `key`");
		return baseDao.queryList(Config.class, sql.toString(), params.toArray());
	}

	@Override
	public int addConfig(Config entity) throws Exception {
	    return baseDao.update(SQL_ADD, entity.getKey(), entity.getValue(), entity.getRemark());
		//return baseDao.save(entity);
	}
	

    @Override
    public Config queryConfigById(int id) throws Exception {
        return baseDao.queryById(Config.class, id);
    }

	@Override
	public Config queryConfigByKey(String key) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("`key`", key);
		return baseDao.queryForObject(Config.class, map);
	}

	@Override
	public int updateConfig(Config entity) throws Exception {
	    return baseDao.update(SQL_UPDATE, entity.getKey(), entity.getValue(), entity.getRemark(), entity.getId());
		//return baseDao.update(entity);
	}

	@Override
	public int delConfig(int id) throws Exception {
	    return baseDao.update(SQL_DELETE, id);
		//return baseDao.deleteById(Config.class, id);
	}

	@Override
	public int queryConfigCount() throws Exception {
		StringBuilder sql = new StringBuilder(SQL_QUERY_COUNT);
		return baseDao.queryForInt(sql.toString(), new Object[]{});
	}


}
