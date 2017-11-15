package com.sz.service.manage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.common.util.AppConstant;
import com.sz.dao.manage.AuthDao;
import com.sz.dao.manage.UserRoleAuthSiteDao;
import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.role.Role;
import com.sz.pojo.manage.role.RoleAuth;
import com.sz.pojo.manage.user.UserRole;
import com.sz.service.manage.AuthService;
import com.sz.service.manage.RoleService;
import com.sz.service.manage.UserRoleAuthSiteService;


@Service
public class UserRoleAuthSiteServiceImpl implements UserRoleAuthSiteService {

	@Autowired
	private UserRoleAuthSiteDao userRoleAuthSiteDao;

	@Autowired
	private AuthService authService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthDao authDao;

	@Override
	public int delByRoleId(Class clazz, int roleId) throws Exception {
		return userRoleAuthSiteDao.delByRoleId(clazz, roleId);
	}

	@Override
	public <T> List<T> queryByRoleId(Class<T> clazz, int roleId) throws Exception {
		return userRoleAuthSiteDao.queryByRoleId(clazz, roleId);
	}

	@Override
	public List<Auth> queryAuthByRoleId(int roleId) throws Exception {
		return authDao.queryAuthByRole(roleId);
	}

	@Override
	public List<Auth> queryParentRoleAuthAndChildAuth(int roleId) throws Exception {
		Role role = roleService.queryRoleById(roleId);
		List<Auth> authList = null;
		if (null != role) {
			authList = new ArrayList<Auth>();
			int parentId = role.getParentId();
			//如果角色时根角色或者顶级角色，则拥有所有的顶级权限其子权限
			if (parentId == AppConstant.ROOT_ROLE_PARENT_ID || parentId == AppConstant.TOP_ROLE_PARENT_ID) {
				List<Auth> rootAndTopAuthList = authService.queryTopAuth();
				for (Auth auth : rootAndTopAuthList) {
					auth.setChildAuth(authService.queryChildAuthByAuthId(auth.getAuthId()));
					authList.add(auth);
				}
			} else {
				//如果不是则查询该角色拥有的顶级权限及相应的子权限
				List<Auth> topAuth = userRoleAuthSiteDao.queryRoleTopAuth(parentId);
				for (Auth auth : topAuth) {
					auth.setChildAuth(authService.queryChildAuthByAuthId(auth.getAuthId()));
					authList.add(auth);
				}
			}
		}
		return authList;
	}

	@Override
	public boolean allocationAuth(Role role) throws Exception {
		List<Integer> authIds = role.getAuthIds();
		List<RoleAuth> roleAuthList = null;
		int roleId = role.getRoleId();
		//删除旧的角色权限对应关系
		userRoleAuthSiteDao.delByRoleId(RoleAuth.class, roleId);


		if (null != authIds && authIds.size() > 0) {
			roleAuthList = new ArrayList<RoleAuth>();

			for (int authId : authIds) {
				RoleAuth roleAuth = new RoleAuth();
				roleAuth.setAuthId(authId);
				roleAuth.setRoleId(roleId);
				roleAuth.setCreateDate(new Date());
				roleAuthList.add(roleAuth);
			}
			int[] result = userRoleAuthSiteDao.addBatch(roleAuthList);
			if (result.length > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public <T> boolean addBatch(List<T> list) {
		int[] result = userRoleAuthSiteDao.addBatch(list);
		return result.length > 0;
	}

	@Override
	public boolean delUserRoleByUserId(int userId) {
		int result = userRoleAuthSiteDao.delUserRoleByUserId(userId);
		return result > 0;
	}

	@Override
	public List<UserRole> queryUserRoleByProperty(Map<String, Object> map) {
		return userRoleAuthSiteDao.queryByProperty(UserRole.class, map);
	}

	@Override
	public List<Role> queryRoleBySite(int siteId) throws Exception {
		return userRoleAuthSiteDao.queryRoleBySite(siteId);
	}


}
