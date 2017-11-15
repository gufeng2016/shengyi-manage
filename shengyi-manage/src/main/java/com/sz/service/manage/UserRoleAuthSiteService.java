package com.sz.service.manage;

import java.util.List;
import java.util.Map;

import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.role.Role;
import com.sz.pojo.manage.user.UserRole;

/**
 * User: xin.fang
 * Date: 14-3-27
 * Time: 下午6:07
 * 用户、角色、权限、站点之间的关系的service
 */
public interface UserRoleAuthSiteService {

	/**
	 * 根据角色ID删除相应的对应关系
	 * @param clazz 与角色对应关系的实体
	 * @param roleId 角色ID
	 * @return
	 */
	int delByRoleId(Class clazz, int roleId) throws Exception;

	/**
	 * 根据角色ID与角色对应关系
	 * @param roleId 角色ID
	 * @return
	 */
	<T> List<T> queryByRoleId(Class<T> clazz, int roleId) throws Exception;

	/**
	 * 根据角色ID查询权限信息
	 * @param roleId
	 * @return
	 */
	List<Auth> queryAuthByRoleId(int roleId) throws Exception;

	/**
	 * 根据角色ID查询其父角色的权限
	 * @param roleId 角色Id
	 * @return
	 */
	List<Auth> queryParentRoleAuthAndChildAuth(int roleId) throws Exception;

	/**
	 * 为角色分配权限
	 * @param role
	 * @return
	 */
	boolean allocationAuth(Role role) throws Exception;

	/**
	 * 批量增加
	 * @param list
	 * @return
	 */
	<T> boolean addBatch(List<T> list);

	/**
	 * 根据用户名删除用户角色对应关系
	 * @param userId
	 * @return
	 */
	boolean delUserRoleByUserId(int userId);

	/**
	 * 根据条件查询用户角色对应关系
	 * @param map
	 * @return
	 */
	List<UserRole> queryUserRoleByProperty(Map<String, Object> map);

	/**
	 * 根据站点Id查询角色
	 * @param siteId
	 * @return
	 */
	List<Role> queryRoleBySite(int siteId) throws Exception;
}
