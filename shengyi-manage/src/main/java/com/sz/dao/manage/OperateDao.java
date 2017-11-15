package com.sz.dao.manage;

import com.sz.dao.BaseDao;
import com.sz.pojo.manage.auth.AuthIp;
import com.sz.pojo.manage.log.OperateLog;

import java.util.List;

/**
 * User: xin.fang
 * Date: 14-3-5
 * Time: 下午5:45
 * 管理ip权限的Dao
 */
public interface OperateDao extends BaseDao {

	/**
	 * 查询所有的权限ip地址
	 * @return
	 */
	List<AuthIp> queryAllAuthIp();

	/**
	 * 增加操作日志
	 *
	 * @param log
	 * @return
	 */
	Integer addOperatorLog(OperateLog log);
}
