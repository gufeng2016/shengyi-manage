package com.sz.dao.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.sz.common.db.JDBCBaseDao;
import com.sz.common.util.AppConstant;
import com.sz.dao.manage.RoleDao;
import com.sz.pojo.manage.role.Role;

/**
 * User: xin.fang
 * Date: 14-3-20
 * Time: 下午2:43
 * 角色管理DAO实现
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	@Autowired
	private JDBCBaseDao baseDao;

	private static final String QUERY_ROLE = "SELECT * FROM t_role WHERE delete_state = 1";


	@Override
	public List<Role> queryRootAndTopRole() throws Exception {
		StringBuilder sql = new StringBuilder(QUERY_ROLE);
		sql.append(" and (parent_id = ? or parent_id = ?) ");
		return baseDao.query(sql.toString(), new Object[]{AppConstant.ROOT_ROLE_PARENT_ID, AppConstant.TOP_ROLE_PARENT_ID}, Role.class);
	}

	@Override
	public List<Role> queryParentRole(int roleId) {
		StringBuilder sql = new StringBuilder(QUERY_ROLE);
		sql.append(" and role_id = (SELECT parent_id FROM t_role WHERE role_id = ?) ");
		return baseDao.query(sql.toString(), new Object[]{roleId}, Role.class);
	}

	@Override
	public Role queryByName(String roleName) throws Exception {
		StringBuffer sql = new StringBuffer(QUERY_ROLE).append(" AND role_name = ?");
		List<Role> list = baseDao.queryList(Role.class, sql.toString(), roleName);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	
}
