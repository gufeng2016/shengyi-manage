package com.sz.dao.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sz.common.db.JDBCBaseDao;
import com.sz.common.util.AppConstant;
import com.sz.dao.manage.UserRoleAuthSiteDao;
import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.role.Role;
import com.sz.pojo.manage.user.UserRole;

/**
 * User: xin.fang
 * Date: 14-3-28
 * Time: 上午10:29
 * .
 */
@Repository
public class UserRoleAuthSiteDaoImpl implements UserRoleAuthSiteDao {

	@Autowired
	private JDBCBaseDao baseDao;

	private static final String QUERY_AUTH_BY_ROLE = "SELECT a.* FROM t_role r, t_role_auth ra, t_auth a "
			+ " WHERE r.role_id = ra.role_id AND ra.auth_id = a.auth_id "
			+ " AND r.delete_state = 1 AND ra.delete_state = 1 AND a.delete_state = 1  ";

	private static final String QUERY_ROLE_BY_SITE = "SELECT r.* FROM t_role r, t_role_site rs, t_site s "
			+ " WHERE r.role_id = rs.role_id AND rs.site_id = s.site_id "
			+ " AND r.delete_state = 1 AND rs.delete_state = 1 AND s.delete_state = 1  ";

	@Override
	public int delByRoleId(Class clazz, int roleId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		return baseDao.deleteByProperties(clazz, map);
	}

	@Override
	public <T> List<T> queryByRoleId(Class<T> clazz, int roleId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		return baseDao.queryByProperties(clazz, map);
	}
	
	

	@Override
	public List<Auth> queryRoleAuth(int roleId) throws Exception {
		StringBuilder builder = new StringBuilder(QUERY_AUTH_BY_ROLE);
		builder.append(" and r.role_id = ? ");
		return baseDao.query(builder.toString(), new Object[]{roleId}, Auth.class);
	}

	@Override
	public List<Auth> queryRoleTopAuth(int roleId) {
		StringBuilder builder = new StringBuilder(QUERY_AUTH_BY_ROLE);
		builder.append(" and r.role_id = ? and a.parent_auth_id = ? ");
		return baseDao.query(builder.toString(), new Object[]{roleId, AppConstant.TOP_ROLE_PARENT_ID}, Auth.class);
	}


	@Override
	public <T> int[] addBatch(List<T> list) {
		return baseDao.saveBacth(list);
	}

	@Override
	public int delUserRoleByUserId(int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return baseDao.deleteByProperties(UserRole.class, map);
	}

	@Override
	public <T> List<T> queryByProperty(Class<T> clazz, Map<String, Object> map) {
		return baseDao.queryByProperties(clazz, map);
	}

	@Override
	public List<Role> queryRoleBySite(int siteId) throws Exception {
		StringBuilder sql = new StringBuilder(QUERY_ROLE_BY_SITE);
		sql.append(" and s.site_id = ? ");
		return baseDao.query(sql.toString(), new Object[]{siteId}, Role.class);
	}

}
