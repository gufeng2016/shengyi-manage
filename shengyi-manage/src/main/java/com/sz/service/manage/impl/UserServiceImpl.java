package com.sz.service.manage.impl;

import com.sz.common.util.AppConstant;
import com.sz.common.util.DateUtil;
import com.sz.dao.manage.RoleDao;
import com.sz.dao.manage.UserDao;
import com.sz.pojo.manage.log.LoginLog;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.role.Role;
import com.sz.pojo.manage.sqlEntry.SQLCondition;
import com.sz.pojo.manage.user.User;
import com.sz.pojo.manage.user.UserRole;
import com.sz.service.manage.UserService;

import org.eclipse.core.runtime.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xin.fang
 * Date: 14-2-25
 * Time: 下午3:51
 * 用户管理的service的实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;


    @Override
    public User queryUserByName(String name) throws Exception {
        return userDao.queryUserByName(name);
    }

    @Override
    public boolean delUserById(int userId) throws Exception {
        int ID = userDao.del(User.class, userId);
        //删除用户和角色的对应关系
		deleteUserRoleByUserId(userId);
        return ID > 0;
    }

	/**
	 * 根据用户ID查询用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private boolean deleteUserRoleByUserId(int userId) throws Exception {
		List<SQLCondition> list = new ArrayList<SQLCondition>();
		list.add(new SQLCondition("userId", userId));
		int result = userDao.delByCondition(UserRole.class, list);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据用户ID查询用户角色对应关系
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	private List<UserRole> queryUserRoleByUserID(int userId) throws Exception {
		List<SQLCondition> list = new ArrayList<SQLCondition>();
		list.add(new SQLCondition("userId", userId));
		List<UserRole> userRoleList = userDao.queryList(UserRole.class, list, "");
		return userRoleList;
	}


    @Override
    public boolean addUser(User user) throws Exception {
        Assert.isNotNull(user, "增加的用户不能为null");
        user.setEffectiveTime(DateUtil.addDay(AppConstant.INIT_PASSWORD_EFFECTIVE_TIME));
        user.setUpdateDate(new Date());
        user.setCreateDate(new Date());
        user.setPassword(AppConstant.INIT_USER_PASSWORD);
        user.setLastPassword(AppConstant.INIT_USER_PASSWORD);
        int result = userDao.add(user);
        //增加用户和角色的对应关系

        List<Integer> roleIds = user.getRoleIds();
        if (null != roleIds && roleIds.size() > 0) {
            List<UserRole> userRoleList = new ArrayList<UserRole>();
            Date date = new Date();
            for (int roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(result);
                userRole.setRoleId(roleId);
                userRole.setCreateDate(date);
                userRoleList.add(userRole);
            }
            userDao.addBatch(userRoleList);
        }

        return result > 0;
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        Assert.isNotNull(user, "更新的用户不能为null");
        //保存上一次的密码
        User queryUser = this.queryUserById(user.getUserId());
        queryUser.setUserNameCn(user.getUserNameCn());
        queryUser.setEmail(user.getEmail());
        queryUser.setAvailable(user.getAvailable());
        int result = userDao.update(queryUser);
        //更新用户和角色的对应关系
        int userId = queryUser.getUserId();

        //查询是否存在用户和角色的对应关系，存在则删除
        List<UserRole> userRoles = queryUserRoleByUserID(userId);

		if (null != userRoles && userRoles.size() > 0) {
            //删除旧的用户和角色关系
			deleteUserRoleByUserId(userId);
        }
        //增加修改后的用户和角色的对应关系
        List<Integer> roleIds = user.getRoleIds();
        if (null != roleIds && roleIds.size() > 0) {
            List<UserRole> userRoleList = new ArrayList<UserRole>();
            Date date = new Date();
            for (int roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRole.setCreateDate(date);
                userRoleList.add(userRole);
            }
            userDao.addBatch(userRoleList);
        }
        return result > 0;
    }

    @Override
    public User queryUserById(int userId) throws Exception {
        User user = userDao.queryById(User.class, userId);
        //查询用户和角色的对应关系

		List<UserRole> userRoles = queryUserRoleByUserID(userId);
        if (null != userRoles && userRoles.size() > 0) {
            List<Role> roleList = new ArrayList<Role>();
            List<Integer> roleIds = new ArrayList<Integer>();
            for (UserRole userRole : userRoles) {
                int roleId = userRole.getRoleId();
                //Role role = roleService.queryRoleAllInfoByRoleId(roleId);
                Role role = roleDao.queryById(Role.class, roleId);
                roleList.add(role);
                roleIds.add(roleId);
            }
            user.setRoleList(roleList);
            user.setRoleIds(roleIds);
        }
        return user;
    }



    @Override
    public PaginationResult<User> queryUsers(PageInfo pageInfo) throws Exception {
//        int total = userDao.queryUserRecode();
//        List<User> userList = userDao.queryAllUser(pageInfo);
//        PaginationResult<User> paginationResult = new PaginationResult<User>(total, userList);
		PaginationResult<User> paginationResult =  userDao.query(User.class, null, pageInfo, " order by update_date desc ");
        return paginationResult;
    }

    @Override
    public boolean updateUserPassword(String newPassword, String oldPassword, Integer userId) {
        int result = userDao.updatePassword(newPassword,oldPassword, userId);
        return result > 0;
    }

    @Override
    public boolean resetPassword(int userId) {
        int result = userDao.resetPassword(userId);
        return result > 0;
    }

    @Override
    public boolean ipIsLock(String ip) throws Exception {
        LoginLog lastLog = userDao.queryIpLastLog(ip);
        //如果用户从未登录则直接返回false
        if (null == lastLog || lastLog.getId() == 0) {
            return false;
        }

        //如果用户最后一次登录未锁定，则直接返回false
        if (lastLog.getLoginLock() != AppConstant.IP_LONIN_LOCK) {
            return false;
        }

        Date loginDate = lastLog.getLoginTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -AppConstant.IP_LONIN_LOCK_TIME);
        //如果最后一次登录已经超过半小时，则直接返回false
        if (loginDate.getTime() <= calendar.getTimeInMillis()) {
            return false;
        }
        return true;
    }

    @Override
    public void addLoginLog(LoginLog log) throws Exception {
        String ip = log.getIp();
        //如果ip是锁定状态，则不增加日志记录
        if (this.ipIsLock(ip)) {
            return;
        }

        //判断登录ip是否应该被锁定
        int lockState = AppConstant.IP_LONIN_UNLOCK;
        //如果用户最后一次登录不是锁定状态,则查询用户登录的最后AppConstant.USER_LOGIN_FAIL_COUNT次记录
        int count = AppConstant.USER_LOGIN_FAIL_COUNT - 1;
        List<LoginLog> loginLogList = userDao.queryIpLoginLogLastCount(ip, count);
        if (null != loginLogList && loginLogList.size() >= count) {
            int state = AppConstant.IP_LONIN_FAIL_STATE;
            if (log.getState() ==  state) {
                //查看第一次登录和最后一次登录时间是否在半小时之内
                LoginLog firstLog = loginLogList.get(count - 1);
                LoginLog latestLog = loginLogList.get(0);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(firstLog.getLoginTime());
                calendar.add(Calendar.MINUTE, AppConstant.IP_LONIN_LOCK_TIME);

                //如果在半小时之内, 则判断ip是否应该被锁定
                if (calendar.getTimeInMillis() >= latestLog.getLoginTime().getTime()) {
                    int failCount = 0;
                    for (LoginLog loginLog : loginLogList) {
                        //只要登录日志中存在登录成功的,则failCount = 0
                        if (loginLog.getState() != state) {
                            failCount = 0;
                            break;
                        } else {
                            failCount++;
                        }
                    }
                    //如果所有登录均失败，则锁定该ip
                    if (failCount >= count) {
                        lockState = AppConstant.IP_LONIN_LOCK;
                        log.setMessage("半小时内多次连续登录失败，ip被锁定");
                    }
                }
            }
        }
        log.setLoginLock(lockState);
        //增加日志记录
        userDao.addLoginLog(log);
    }


}
