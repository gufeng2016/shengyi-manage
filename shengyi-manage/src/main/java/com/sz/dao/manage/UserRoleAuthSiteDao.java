package com.sz.dao.manage;

import java.util.List;
import java.util.Map;

import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.role.Role;

/**
 * User: xin.fang
 * Date: 14-3-28
 * Time: 上午10:29
 * .
 */
public interface UserRoleAuthSiteDao {

	/**
	 * 根据角色ID删除相应的对应关系
	 * @param clazz 与角色的对应关系
	 * @param roleId 角色ID
	 * @return
	 */
	int delByRoleId(Class clazz, int roleId) throws Exception;

	/**
	 * 根据角色ID查询相应的对应关系
	 * @param clazz 与角色的对应关系类
	 * @param roleId 角色ID
	 * @param <T>
	 * @return
	 */
	<T> List<T> queryByRoleId(Class<T> clazz, int roleId) throws Exception;

	/**
	 * 根据角色Id查询角色的所有权限
	 * @param roleId
	 * @return
	 */
	List<Auth> queryRoleAuth(int roleId) throws Exception;

	/**
	 * 根据角色ID查询角色拥有的顶级权限
	 * @param roleId 角色ID
	 * @return
	 */
	List<Auth> queryRoleTopAuth(int roleId);


	/**
	 * 批量增加
	 *
	 * @param list
	 * @return
	 */
	<T> int[] addBatch(List<T> list);

	/**
	 * 删除用户和角色的对应关系
	 *
	 * @param userId
	 * @return
	 */
	int delUserRoleByUserId(int userId);

	/**
	 * 根据条件查询信息
	 * @param clazz
	 * @param map
	 * @param <T>
	 * @return
	 */
	<T> List<T> queryByProperty(Class<T> clazz, Map<String, Object> map);

	/**
	 * 根据站点ID查询角色
	 * @param siteId
	 * @return
	 */
	List<Role> queryRoleBySite(int siteId) throws Exception;
}
