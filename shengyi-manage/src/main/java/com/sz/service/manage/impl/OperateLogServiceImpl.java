package com.sz.service.manage.impl;

import com.sz.common.db.SQLUtils;
import com.sz.common.util.AppConstant;
import com.sz.dao.manage.OperateDao;
import com.sz.pojo.manage.auth.AuthIp;
import com.sz.pojo.manage.log.OperateLog;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.sqlEntry.SQLCondition;
import com.sz.service.manage.OperateLogService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: xin.fang
 * Date: 14-3-5
 * Time: 下午5:43
 * 管理ip权限的service的实现类
 */
@Service(value = "operateLogService")
public class OperateLogServiceImpl implements OperateLogService {

	@Autowired
	private OperateDao operatorDao;

	@Override
	public boolean isAuthIp(String ip) {
		Assert.notNull(ip, "ip is null");
		List<AuthIp> authIpList = operatorDao.queryAllAuthIp();
		boolean result = false;
		if (null != authIpList) {
			for (AuthIp authIp : authIpList) {
				String ipadd = authIp.getIp();
				if (ipadd.equals(AppConstant.ALLOW_ALL_IP) || ipadd.equals(ip)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public Integer addOperatorLog(OperateLog log) {
		return operatorDao.addOperatorLog(log);
	}

    @Override
    public PaginationResult queryAllOperateLog(PageInfo pageInfo,final OperateLog log) throws Exception{
        final List<SQLCondition> conditions = new ArrayList<SQLCondition>();
        ReflectionUtils.doWithFields(log.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                field.setAccessible(true);
                Object obj = field.get(log);
                if (obj == null || StringUtils.isBlank(obj.toString())) return;
                SQLCondition condition;
                if(obj instanceof Date) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String formarOpertateTime = format.format(obj);
                    condition = new SQLCondition(SQLUtils.convertStrToDBFormat(field.getName()), formarOpertateTime, true);
                }else{

                    condition = new SQLCondition(SQLUtils.convertStrToDBFormat(field.getName()), obj, true);
                }
                conditions.add(condition);
            }
        });
        PaginationResult result = operatorDao.query(log.getClass(),conditions,pageInfo," order by operate_id desc");
        return result;
    }
}
