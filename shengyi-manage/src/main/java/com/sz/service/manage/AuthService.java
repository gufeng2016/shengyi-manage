package com.sz.service.manage;

import java.util.List;

import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;

/**
 * User: xin.fang
 * Date: 14-3-6
 * Time: 下午2:52
 * .
 */
public interface AuthService {


	/**
	 * 增加权限
	 * @param auth
	 * @return
	 */
	boolean addAuth(Auth auth) throws Exception;

	/**
	 * 更新权限
	 * @param auth
	 * @return
	 */
	boolean updateAuth(Auth auth) throws Exception;

	/**
	 * 根据ID删除权限
	 * @param authId
	 * @return
	 */
	boolean delAuth(int authId) throws Exception;

	/**
	 * 根据Id查询权限
	 * @param authId
	 * @return
	 */
	Auth queryAuthById(int authId) throws Exception;

	/**
	 * 查询当前用户拥有的权限
	 * @param userId 用户ID
	 * @return
	 */
	List<Auth> queryAuthByUserId(int userId) throws Exception;

	/**
	 * 查询根权限和顶级权限
	 * @return
	 */
	List<Auth> queryRootAndTopAuth() throws Exception;

	/**
	 * 根据权限ID分页查询权限
	 *
	 * @param authId 权限ID
	 * @param info  权限名称
	 * @param authName 权限名
	 * @param url  权限url
	 * @return
	 */
	PaginationResult<Auth> queryAuthList(int authId, PageInfo info, String authName, String url) throws Exception;

	/**
	 * 根据权限ID查询权限
	 * @param authId 权限ID
	 * @return
	 */
	List<Auth> queryChildAuthByAuthId(int authId) throws Exception;

	/**
	 * 查询顶级权限
	 * @return
	 */
	List<Auth> queryTopAuth() throws Exception;

	/**
	 * 根据权限ID分页查询
	 * @param authId
	 * @param pageInfo
	 * @return
	 */
	PaginationResult<Auth> queryAuthList(int authId, PageInfo pageInfo) throws Exception;
	
	/**
	 * 查询用户所有权限
	 * @param userId
	 * @param userType
	 * @throws Exception
	 */
	List<Auth> queryAuthByUser(int userId) throws Exception;
	
	/**
	 * 根据Url查询
	 * @param url
	 * @return
	 * @throws Exception
	 */
	Auth queryAuthByUrl(String url) throws Exception;
}
