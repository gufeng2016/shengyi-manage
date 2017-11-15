package com.sz.dao.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.sz.common.db.JDBCBaseDao;
import com.sz.common.util.AppConstant;
import com.sz.dao.manage.AuthDao;
import com.sz.pojo.manage.auth.Auth;

/**
 * User: xin.fang
 * Date: 14-3-6
 * Time: 下午3:52
 * .
 */
@Repository
public class AuthDaoImpl extends BaseDaoImpl implements AuthDao {

	@Autowired
	private JDBCBaseDao baseDao;

	/**
	 * 查询权限的url
	 */
	private static final String QUERY_JOIN_AUTH = "SELECT DISTINCT a.* FROM t_user u, t_user_role ur, t_role r, t_role_auth ra, t_auth a "
			+ " WHERE u.user_id = ur.user_id AND ur.role_id = r.role_id AND r.role_id = ra.role_id AND ra.auth_id = a.auth_id "
			+ " AND u.delete_state = 1 AND ur.delete_state = 1 AND r.delete_state = 1 AND ra.delete_state = 1 AND a.delete_state = 1 ";


	private static final String QUERY_AUTH = "SELECT * FROM t_auth WHERE delete_state = 1 ";
	
	private static final String QUERY_AUTH_BY_ROLE = "SELECT DISTINCT a.* FROM t_role r, t_role_auth ra, t_auth a "
			+ " WHERE r.role_id = ra.role_id AND ra.auth_id = a.auth_id AND r.delete_state = 1 AND ra.delete_state = 1 AND a.delete_state = 1 ";


	@Override
	public List<Auth> queryAuthByUserId(int userId) throws Exception {
		StringBuilder sql = new StringBuilder(QUERY_JOIN_AUTH);
		sql.append(" and u.user_id = ? ");
		return baseDao.query(sql.toString(), new Object[]{userId}, Auth.class);
	}

	@Override
	public List<Auth> queryChildAuthByUserIdAndAuthId(int userId, int parentAuthId) throws Exception {
			StringBuilder sql = new StringBuilder(QUERY_JOIN_AUTH);
			sql.append(" and u.user_id = ? and a.parent_auth_id = ? ");
			return baseDao.query(sql.toString(), new Object[]{userId, parentAuthId}, Auth.class);
	}

	@Override
	public List<Auth> queryRootAndTopAuth() throws Exception {
		StringBuilder sql = new StringBuilder(QUERY_AUTH);
		sql.append(" and (parent_auth_id = ? or parent_auth_id = ?)");
		return baseDao.query(sql.toString(), new Object[]{AppConstant.ROOT_AUTH_PARENT_ID, AppConstant.TOP_AUTH_PARENT_ID}, Auth.class);
	}


	@Override
	public List<Auth> queryAuthByUserIdAndSiteId(int userId) throws Exception {
		StringBuilder sql = new StringBuilder(QUERY_JOIN_AUTH);
		sql.append(" and u.user_id = ? order by a.order_id, a.auth_id");
		return baseDao.query(sql.toString(), new Object[]{userId}, Auth.class);
	}

	@Override
	public List<Auth> queryAuthByRole(int roleId) throws Exception {
		StringBuffer sql = new StringBuffer(QUERY_AUTH_BY_ROLE).append(" AND r.role_id = ?");
		return baseDao.queryList(Auth.class, sql.toString(), roleId);
	}
	
	@Override
	public Auth queryAuthByUrl(String url) throws Exception {
		StringBuffer sql = new StringBuffer(QUERY_AUTH).append(" AND url = ?");
		List<Auth> list = baseDao.queryList(Auth.class, sql.toString(), url);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

}
