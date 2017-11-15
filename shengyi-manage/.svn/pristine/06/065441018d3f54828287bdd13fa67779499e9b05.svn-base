package com.sz.dao.manage.impl;

import com.sz.common.db.JDBCBaseDao;
import com.sz.dao.manage.OperateDao;
import com.sz.pojo.manage.auth.AuthIp;
import com.sz.pojo.manage.log.OperateLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: xin.fang
 * Date: 14-3-5
 * Time: 下午5:45
 *  管理ip权限的Dao实现类
 */
@Repository
public class OperateLogDaoImpl extends BaseDaoImpl implements OperateDao {

	private static final String QUERY_ALL_AUTH_IP = "SELECT id, ip, type, available FROM t_auth_ip ";


	@Autowired
	private JDBCBaseDao baseDao;

	@Override
	public List<AuthIp> queryAllAuthIp() {
		List<AuthIp> authIpList = baseDao.query(QUERY_ALL_AUTH_IP, new Object[]{}, AuthIp.class);
		return authIpList;
	}

	@Override
	public Integer addOperatorLog(OperateLog log) {
		return baseDao.save(log);
	}
}
