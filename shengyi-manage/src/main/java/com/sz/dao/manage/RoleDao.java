package com.sz.dao.manage;

import java.util.List;

import com.sz.dao.BaseDao;
import com.sz.pojo.manage.role.Role;

/**
 * User: xin.fang
 * Date: 14-3-20
 * Time: 下午2:43
 * .
 */
public interface RoleDao extends BaseDao {


	/**
	 * 查询根角色和顶级角色
	 * @return
	 */
	List<Role> queryRootAndTopRole() throws Exception;

	/**
	 * 根据角色ID查询父角色
	 *
	 * @param roleId
	 * @return
	 */
	List<Role> queryParentRole(int roleId);
	
	/**
	 * 根据角色名称查询 
	 * @param roleName
	 * @throws Exception
	 */
	Role queryByName(String roleName) throws Exception;
	
}
