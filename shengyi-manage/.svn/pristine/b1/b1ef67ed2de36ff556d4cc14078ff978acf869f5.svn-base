package com.sz.dao.manage;

import java.util.List;

import com.sz.dao.BaseDao;
import com.sz.pojo.manage.auth.Auth;

/**
 * User: xin.fang
 * Date: 14-3-6
 * Time: 下午3:51
 * .
 */
public interface AuthDao extends BaseDao {


	/**
	 * 查询用户拥有的权限
	 *
	 * @param userId 用户ID
	 * @return
	 */
	List<Auth> queryAuthByUserId(int userId) throws Exception;

	/**
	 * 根据用户ID和权限ID查询相关子权限信息
	 * @param userId 用户ID
	 * @param authId 权限ID
	 * @return
	 */
	List<Auth> queryChildAuthByUserIdAndAuthId(int userId, int authId) throws Exception;

	/**
	 * 查询根权限和顶级权限
	 * @return
	 */
	List<Auth> queryRootAndTopAuth() throws Exception;


	/**
	 * 根据用户ID和站点ID查询权限
	 * @param userId
	 * @param siteId
	 * @return
	 */
	List<Auth> queryAuthByUserIdAndSiteId(int userId) throws Exception;
	
	/**
	 * 根据角色查询权限
	 * @throws Exception
	 */
	List<Auth> queryAuthByRole(int roleId) throws Exception;
	
	/**
	 * 根据Url查询
	 * @param url
	 * @return
	 * @throws Exception
	 */
	Auth queryAuthByUrl(String url) throws Exception;
	
}
