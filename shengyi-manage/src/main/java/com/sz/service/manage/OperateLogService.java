package com.sz.service.manage;

import com.sz.pojo.manage.log.OperateLog;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;

/**
 * User: xin.fang
 * Date: 14-3-5
 * Time: 下午5:40
 * 管理ip权限的service
 */
public interface OperateLogService {
	/**
	 * 是否是权限允许的ip地址
	 * @param ip
	 * @return
	 */
	boolean isAuthIp(String ip);

	/**
	 * 增加操作日志
	 *
	 * @param log
	 * @return
	 */
	Integer addOperatorLog(OperateLog log);

    /**
     * 日志查询
     * @param pageInfo
     * @param log
     * @return
     * @throws Exception
     */
    public PaginationResult queryAllOperateLog(PageInfo pageInfo,final OperateLog log) throws Exception;
}
