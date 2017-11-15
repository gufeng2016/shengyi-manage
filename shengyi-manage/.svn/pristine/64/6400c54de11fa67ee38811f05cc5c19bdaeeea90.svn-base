package com.sz.service.manage.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sz.common.util.AppConstant;
import com.sz.dao.manage.RoleDao;
import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.role.Role;
import com.sz.pojo.manage.role.RoleAuth;
import com.sz.pojo.manage.sqlEntry.SQLCondition;
import com.sz.pojo.manage.user.UserRole;
import com.sz.service.manage.RoleService;
import com.sz.service.manage.UserRoleAuthSiteService;

/**
 * User: xin.fang
 * Date: 14-3-12
 * Time: 上午11:44
 * 角色管理的service
 */
@Service
public class RoleServieImpl implements RoleService {

	private Logger logger = Logger.getLogger(RoleServieImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleAuthSiteService service;


	@Override
	public List<Role> queryRootAndTopRole() throws Exception {
		return roleDao.queryRootAndTopRole();
	}

	@Override
	public boolean addRole(Role role) throws Exception {
		Date date = new Date();
		role.setCreateDate(date);
		role.setUpdateDate(date);
		int ID = roleDao.add(role);
		return ID > 0;
	}


	@Override
	public Role queryRoleById(int roleId) throws Exception {
		Role role = roleDao.queryById(Role.class, roleId);
		return role;
	}

	@Override
	public boolean updateRole(Role role) throws Exception {

		int roleId = role.getRoleId();
		Role queryRole = this.queryRoleById(roleId);

		Date date = new Date();
		role.setUpdateDate(date);
		int rows = roleDao.update(role);
		//TODO 修改于角色相关的信息，角色与站点对应关系
		if (rows > 0) {
			//构造条件list
			List<SQLCondition> conditionList = new ArrayList<SQLCondition>();
			SQLCondition condition = new SQLCondition("roleId", "");
			conditionList.add(condition);
			List<Role> childRoleList = null;
			//查询角色是否更改了父角色
			int parentId = role.getParentId();
			if (parentId != queryRole.getParentId()) {

				//如果更改了父角色，则删除与角色相关的所有信息(权限、站点)
				condition.setValue(roleId);
				roleDao.delByCondition(RoleAuth.class, conditionList);

				//如果还有子角色，则删除与子角色相关的所有信息
				if (this.roleHasChild(roleId)) {
					childRoleList = this.queryChildRole(roleId);
					for (Role childRole : childRoleList) {
						int childRoleId = childRole.getRoleId();
						condition.setValue(childRoleId);
						roleDao.delByCondition(RoleAuth.class, conditionList);
					}
				}

			}
			logger.info("更新角色时，更新角色与站点的对象关系完毕");
		}
		return rows > 0;
	}

	@Override
	public boolean delRole(int roleId) throws Exception {
		int delRole = roleDao.del(Role.class, roleId);
		boolean result = false;

		if (delRole > 0) {
			logger.info("删除角色数为:" + delRole);
			//构造查询条件list
			List<SQLCondition> conditionList = new ArrayList<SQLCondition>();
			SQLCondition condition = new SQLCondition("roleId", roleId);
			conditionList.add(condition);

			//删除角色和权限的对应关系
			int authRows = roleDao.delByCondition(RoleAuth.class, conditionList);
			//int authRows = service.delByRoleId(RoleAuth.class, roleId);
			logger.info("删除角色和权限对应数为:" + authRows);


			//删除用户和角色的对应关系
			int userRoleRows = roleDao.delByCondition(UserRole.class, conditionList);
			//int userRoleRows = service.delByRoleId(UserRole.class, roleId);
			logger.info("删除用户和角色对应数为:" + userRoleRows);

			//如果有子角色，则删除与子角色对应的所有关系
			if (this.roleHasChild(roleId)) {
				List<Role> childRoleList = this.queryChildRole(roleId);
				for (Role childRole : childRoleList) {
					int childRoleId = childRole.getRoleId();
					condition.setValue(childRoleId);
					roleDao.delByCondition(RoleAuth.class, conditionList);
					roleDao.delByCondition(UserRole.class, conditionList);

					//service.delByRoleId(RoleSite.class, childRoleId);
					//service.delByRoleId(RoleAuth.class, childRoleId);
					//service.delByRoleId(UserRole.class, childRoleId);
				}
				logger.error("删除子角色对应信息完毕");
			}

			result = true;
		}
		return result;
	}

	@Override
	public PaginationResult<Role> queryChildRole(PageInfo pageInfo, int roleId) throws Exception {
//		int total = roleDao.queryChildeRoleRecordByRoleId(roleId);
//		List<Role> authList = roleDao.queryChildeRoleList(roleId, pageInfo);
//		PaginationResult<Role> paginationResult = new PaginationResult<Role>(total, authList);
		List<SQLCondition> conditionList = new ArrayList<SQLCondition>();
		SQLCondition condition = new SQLCondition("parentId", roleId);
		conditionList.add(condition);
		PaginationResult<Role> paginationResult = roleDao.query(Role.class, conditionList, pageInfo, "");
		return paginationResult;
	}

	@Override
	public Role queryRoleAllInfoByRoleId(int roleId) throws Exception {
		Role role = this.queryRoleById(roleId);
		if (null != role) {
			this.setChildRoleList(role);
			this.setRoleAuthList(role);
		}
		return role;
	}

	@Override
	public List<Role> queryChildRole(int roleId) throws Exception {
		List<SQLCondition> conditionList = new ArrayList<SQLCondition>();
		SQLCondition condition = new SQLCondition("parentId", roleId);
		conditionList.add(condition);
		List<Role> roleList = roleDao.queryList(Role.class, conditionList, "");
		return roleList;
	}

	@Override
	public List<Role> queryAllRole() throws Exception {
		List<Role> roleList = roleDao.queryList(Role.class, null, "");
		List<SQLCondition> conditionList = new ArrayList<SQLCondition>();
		SQLCondition condition = new SQLCondition("parentId", "");
		conditionList.add(condition);
		for (Role role : roleList) {
			int roleId = role.getRoleId();
			boolean hasChild = this.roleHasChild(roleId);
			if (hasChild) {
				condition.setValue(roleId);
				List<Role> childRoleList = roleDao.queryList(Role.class, conditionList, "");
				role.setChildRoleList(childRoleList);
			}
		}
		return roleList;
	}

	@Override
	public List<Role> queryTopRole() throws Exception {
		List<SQLCondition> conditionList = new ArrayList<SQLCondition>();
		SQLCondition condition = new SQLCondition("parentId", AppConstant.TOP_ROLE_PARENT_ID);
		conditionList.add(condition);
		List<Role> roleList = roleDao.queryList(Role.class, conditionList, "");
		for (Role role : roleList) {
			int roleId = role.getRoleId();
			boolean hasChild = this.roleHasChild(roleId);
			if (hasChild) {
				condition.setValue(roleId);
				List<Role> childRoleList = roleDao.queryList(Role.class, conditionList, "");
				role.setChildRoleList(childRoleList);
			}
		}
		return roleList;
	}

	@Override
	public Role queryParentRole(int roleId) {
		List<Role> roleList = roleDao.queryParentRole(roleId);
		if (null != roleList && roleList.size() > 0) {
			return roleList.get(0);
		}
		return null;
	}

	/**
	 * 查询角色是否含有子角色
	 * @param roleId 角色ID
	 * @return
	 */
	private boolean roleHasChild(int roleId) throws Exception {
		List<SQLCondition> conditionList = new ArrayList<SQLCondition>();
		SQLCondition condition = new SQLCondition("parentId", roleId);
		conditionList.add(condition);
		List<Role> roleList = roleDao.queryList(Role.class, conditionList, "");
		if (null == roleList || roleList.size() == 0) {
			return false;
		}
		return true;
	}

	private void setRoleAuthList(Role role) throws Exception {
		List<Auth> authList = service.queryAuthByRoleId(role.getRoleId());
		if (null != authList && authList.size() > 0) {
			role.setAuthList(authList);
			role.setAuthIds(this.getAuthIds(authList));
		}
	}

	/**
	 * 设置用户的权限Id列表
	 * @param authList
	 * @return
	 */
	private List<Integer> getAuthIds(List<Auth> authList) {
		Assert.notNull(authList, "权限集合不能为null");
		Assert.notEmpty(authList, "权限集合不能为空");
		List<Integer> authIdList = new ArrayList<Integer>();
		for (Auth auth : authList) {
			authIdList.add(auth.getAuthId());
		}
		return authIdList;
	}

	public void setChildRoleList(Role role) throws Exception {
		Assert.notNull(role, "角色对象不能为空");
		List<Role> roleList = this.queryChildRole(role.getRoleId());
		if (null != roleList && roleList.size() > 0) {
			role.setChildRoleList(roleList);
			role.setChildRoleIds(this.getRoleIds(roleList));
		}
	}

	private List<Integer> getRoleIds(List<Role> roleList) {
		Assert.notNull(roleList, "站点集合不能为null");
		Assert.notEmpty(roleList, "站点集合不能为空");
		List<Integer> roleIdList = new ArrayList<Integer>();
		for (Role role : roleList) {
			roleIdList.add(role.getRoleId());
		}
		return roleIdList;
	}
}
