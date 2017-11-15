package com.sz.service.manage.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.sz.common.util.AppConstant;
import com.sz.dao.manage.AuthDao;
import com.sz.dao.manage.RoleDao;
import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.role.Role;
import com.sz.pojo.manage.sqlEntry.SQLCondition;
import com.sz.service.manage.AuthService;
import com.sz.service.manage.ConfigService;

/**
 * User: xin.fang
 * Date: 14-3-6
 * Time: 下午2:54
 * .
 */
@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private ConfigService configService;


	@Override
	public boolean addAuth(Auth auth) throws Exception {
		Date date = new Date();
		auth.setCreateDate(date);
		int ID = authDao.add(auth);
		return ID > 0;
	}

	@Override
	public boolean updateAuth(Auth auth) throws Exception {
		int rows = authDao.update(auth);
		return rows > 0;
	}

	@Override
	public boolean delAuth(int authId) throws Exception {
		int rows = authDao.del(Auth.class, authId);
		return rows > 0;
	}


	@Override
	public Auth queryAuthById(int authId) throws Exception {
		return authDao.queryById(Auth.class, authId);
	}

	@Override
	public List<Auth> queryAuthByUserId(int userId) throws Exception {
		List<Auth> authList = authDao.queryAuthByUserId(userId);
		List<Auth> list = initAuth(authList, AppConstant.TOP_AUTH_PARENT_ID);
		list.addAll(authList);
		return list;
	}
	
	private List<Auth> initAuth(List<Auth> authList, int parentId) throws Exception{
		if(CollectionUtils.isEmpty(authList)) return null;
		List<Auth> list = Lists.newArrayList();
		for(int i = 0; i < authList.size(); i++){
			Auth auth = authList.get(i);
			if(auth.getParentAuthId() != parentId) continue;
			authList.remove(i);
			auth.setChildAuth(initAuth(authList, auth.getAuthId()));
			list.add(auth);
			i--;
		}
		Collections.sort(list, new Comparator<Auth>() {
			@Override
			public int compare(Auth o1, Auth o2) {
				return o1.getOrderId() - o2.getOrderId();
			}
		});
		return list;
	}

	@Override
	public List<Auth> queryRootAndTopAuth() throws Exception {
		return authDao.queryRootAndTopAuth();
	}

	@Override
	public PaginationResult<Auth> queryAuthList(int authId, PageInfo pageInfo, String authName, String url) throws Exception {
		List<SQLCondition> list = new ArrayList<SQLCondition>();
		if (StringUtils.isNotBlank(authName)) {
			list.add(new SQLCondition("authName", authName, true));
		}

		if (StringUtils.isNotBlank(url)) {
			list.add(new SQLCondition("url", url, true));
		}

		if (authId != 0) {
			list.add(new SQLCondition("authId", authId));
		}

//		int total = authDao.queryChildeAuthRecord(list);
//		List<Auth> authList = authDao.queryChildeAuthList(list, pageInfo);
		PaginationResult<Auth> paginationResult = authDao.query(Auth.class, list, pageInfo, "");
		return paginationResult;
	}

	@Override
	public List<Auth> queryChildAuthByAuthId(int authId) throws Exception {
		List<SQLCondition> list = new ArrayList<SQLCondition>();
		list.add(new SQLCondition("parentAuthId", authId));
		return authDao.queryList(Auth.class, list, "");
	}

	@Override
	public List<Auth> queryTopAuth() throws Exception {
		List<SQLCondition> list = new ArrayList<SQLCondition>();
		list.add(new SQLCondition("parentAuthId", AppConstant.TOP_AUTH_PARENT_ID));
		return authDao.queryList(Auth.class, list, "");
	}

	@Override
	public PaginationResult<Auth> queryAuthList(int authId, PageInfo pageInfo) throws Exception {
//		int total = authDao.queryChildeAuthRecordByAuthId(authId);
//		List<Auth> authList = authDao.queryChildeAuthList(authId, pageInfo);
		List<SQLCondition> list = new ArrayList<SQLCondition>();
		list.add(new SQLCondition("parentAuthId", authId));
		PaginationResult<Auth> paginationResult = authDao.query(Auth.class, list, pageInfo, "");
		return paginationResult;
	}
	
	@Override
	public List<Auth> queryAuthByUser(int userId) throws Exception {
		List<Auth> authList = Lists.newArrayList();
		Role role = null;
		String roleIdStr = null;
		authList = authDao.queryAuthByUserId(userId);
		if(!CollectionUtils.isEmpty(authList)){
			List<Auth> list = initAuth(authList, AppConstant.TOP_AUTH_PARENT_ID);
			list.addAll(authList);
			return list;
		}
		return null;
	}
	
	@Override
	public Auth queryAuthByUrl(String url) throws Exception {
		return authDao.queryAuthByUrl(url);
	}
	
}
