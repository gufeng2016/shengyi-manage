package com.sz.service.manage;

import com.sz.pojo.manage.log.LoginLog;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: xin.fang
 * Date: 14-2-25
 * Time: 下午3:50
 * 用户管理的service
 */
public interface UserService {

	/**
	 * 根据用户名查询用户信息
	 * @param name
	 * @return
	 */
	User queryUserByName(String name) throws Exception;

	/**
	 * 根据用户ID删除用户信息
	 * @param userId 用户ID
	 * @return
	 */
	boolean delUserById(int userId) throws Exception;

	/**
	 * 增加用户信息
	 * @param user
	 * @return
	 */
	boolean addUser(User user) throws Exception;

	/**
	 * 根据用户ID查询用户信息
	 * @param userId
	 * @return
	 */
	User queryUserById(int userId) throws Exception;

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	boolean updateUser(User user) throws Exception;

	/**
	 * 分页查询用户信息
	 * @param pageInfo
	 * @return
	 */
	PaginationResult<User> queryUsers(PageInfo pageInfo) throws Exception;

	/**
	 * 修改用户密码信息
	 * @param newPassword 用户新密码
	 * @param oldPassword 用户老密码
	 * @param userId 用户Id
	 * @return
	 */
	boolean updateUserPassword(String newPassword, String oldPassword, Integer userId);

	/**
	 * 重置用户密码
	 * @param userId 用户ID
	 * @return
	 */
	boolean resetPassword(int userId);

	/**
	 * 查询ip是否被锁定
	 * @param ip ip地址
	 * @return true表示ip已锁定无法登录、false表示用户可以登录
	 */
	boolean ipIsLock(String ip) throws Exception;

	/**
	 * 增加登录日志
	 *
	 * @param log
	 * @return
	 */
	void addLoginLog(LoginLog log) throws Exception;
}
