package com.sz.dao.manage.impl;

import com.sz.common.db.JDBCBaseDao;
import com.sz.common.util.AppConstant;
import com.sz.common.util.DateUtil;
import com.sz.dao.manage.UserDao;
import com.sz.pojo.manage.log.LoginLog;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xin.fang
 * Date: 14-2-25
 * Time: 下午3:52
 * 用户管理的dao
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/**
	 * 更新用户密码
	 */
	private static final String UPDATE_USER_PASSWORD = "UPDATE t_user SET password = ?, "
			+ " last_password = ?, effective_time = ?, update_date = ? WHERE user_id = ? ";

	/**
	 * 充值用户密码
	 */
	private static final String RESET_USER_PASSWORD = "UPDATE t_user SET password = ?, "
			+ " last_password = ?, effective_time = ?, update_date = ? WHERE user_id = ?";

	/**
	 * 查询登录日志
	 */
	private static final String QUERY_USER_LOGIN_LOG = "SELECT * FROM t_login_log ";


	@Autowired
	private JDBCBaseDao baseDao;

	@Override
	public User queryUserByName(String name) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", name);
		return baseDao.queryForObject(User.class, map);
	}


	@Override
	public int updatePassword(String password, String oldPassword, Integer userId) {
		Date effectTime = DateUtil.addMonth(AppConstant.PASSWORD_EFFECTIVE_TIME);
		return baseDao.update(UPDATE_USER_PASSWORD, password, oldPassword, effectTime, new Date(), userId);
	}

	@Override
	public int resetPassword(int userId) {
		Date effectTime = DateUtil.addMonth(AppConstant.PASSWORD_EFFECTIVE_TIME);
		String password = AppConstant.INIT_USER_PASSWORD;
		return baseDao.update(RESET_USER_PASSWORD, password, password, effectTime, new Date(), userId);
	}

	@Override
	public LoginLog queryIpLastLog(String ip) throws Exception {
		StringBuilder sql = new StringBuilder(QUERY_USER_LOGIN_LOG);
		sql.append(" where ip = ? order by login_time desc limit 1");
		List<LoginLog> loginLogList = baseDao.query(sql.toString(), new Object[]{ip}, LoginLog.class);
		if (null != loginLogList && loginLogList.size() > 0) {
			return loginLogList.get(0);
		}
		return null;
	}

	@Override
	public List<LoginLog> queryIpLoginLogLastCount(String ip, int count) {
		StringBuilder sql = new StringBuilder(QUERY_USER_LOGIN_LOG);
		sql.append(" where ip = ? order by login_time desc limit ").append(count);
		return baseDao.query(sql.toString(), new Object[]{ip}, LoginLog.class);
	}

	@Override
	public Integer addLoginLog(LoginLog log) {
		log.setLoginTime(new Date());
		return baseDao.save(log);
	}
}
