package com.sz.dao.manage;

import com.sz.dao.BaseDao;
import com.sz.pojo.manage.log.LoginLog;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.user.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xin.fang
 * Date: 14-2-25
 * Time: 下午3:51
 * 用户管理dao
 */
public interface UserDao extends BaseDao{

	/**
	 * 根据用户名查询用户信息
	 * @param name 用户名
	 * @return
	 */
	User queryUserByName(String name) throws Exception;


	/**
	 * 修改用户密码
	 *
	 * @param password 新密码
	 * @param oldPassword 旧密码
	 * @param userId 用户ID
	 * @return
	 */
	int updatePassword(String password, String oldPassword, Integer userId);

	/**
	 * 重置用户密码
	 * @param userId 用户ID
	 * @return
	 */
	int resetPassword(int userId);

	/**
	 * 查询ip最后一次登录记录
	 * @param ip
	 * @return
	 */
	LoginLog queryIpLastLog(String ip) throws Exception;

	/**
	 * 查询ip最后几次的登录日志
	 * @param ip
	 * @param count
	 * @return
	 */
	List<LoginLog> queryIpLoginLogLastCount(String ip, int count);

	/**
	 * 增加登录日志
	 *
	 * @param log
	 * @return
	 */
	Integer addLoginLog(LoginLog log);
}
