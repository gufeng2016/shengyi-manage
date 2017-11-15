package com.sz.service.manage;

import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.role.Role;

import java.util.List;

/**
 * User: xin.fang
 * Date: 14-3-12
 * Time: 上午11:44
 * .
 */
public interface RoleService {


	/**
	 * 查询根角色和顶级角色
	 * @return
	 */
	List<Role> queryRootAndTopRole() throws Exception;

	/**
	 * 创建角色
	 * @param role
	 * @return
	 */
	boolean addRole(Role role) throws Exception;

	/**
	 * 根据ID查询角色
	 * @param roleId
	 * @return
	 */
	Role queryRoleById(int roleId) throws Exception;

	/**
	 * 更新角色信息
	 * @param role
	 * @return
	 */
	boolean updateRole(Role role) throws Exception;

	/**
	 * 删除角色信息
	 * @param roleId
	 * @return
	 */
	boolean delRole(int roleId) throws Exception;

	/**
	 * 根据角色ID分页查询其子角色
	 * @param pageInfo
	 * @param roleId 角色ID
	 * @return
	 */
	PaginationResult<Role> queryChildRole(PageInfo pageInfo, int roleId) throws Exception;

	/**
	 * 根据角色Id查询角色并填充角色权限
	 * @param roleId
	 * @return
	 */
	Role queryRoleAllInfoByRoleId(int roleId) throws Exception;

	/**
	 * 根据角色Id查询其所有的子角色
	 * @param roleId
	 * @return
	 */
	List<Role> queryChildRole(int roleId) throws Exception;

	/**
	 *
	 * 查询所有角色信息
	 * @return
	 */
	List<Role> queryAllRole() throws Exception;

	/**
	 * 查询顶级角色
	 * @return
	 */
	List<Role> queryTopRole() throws Exception;

	/**
	 * 根据角色ID查询父角色
	 * @param roleId
	 * @return
	 */
	Role queryParentRole(int roleId);
}
