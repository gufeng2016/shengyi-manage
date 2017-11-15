package com.sz.dao.web.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sz.common.annotation.ChooseDataSource;
import com.sz.common.constant.DataSourceConstant;
import com.sz.common.db.JDBCBaseDao;
import com.sz.common.util.StringUtil;
import com.sz.dao.web.MaterialDao;
import com.sz.params.material.MaterialQueryParam;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.web.Material;

@Repository
public class MaterialDaoImpl implements MaterialDao{
		
	@Autowired
	private JDBCBaseDao jdbcDao;

	@Override
	@ChooseDataSource(mainType=DataSourceConstant.BIZ_TYPE_SLAVE,bizType=DataSourceConstant.PROJECT_SUFFIX)
	public PaginationResult<Material> queryMaterialListByPage(MaterialQueryParam param, PageInfo info) {
		StringBuffer sb = new StringBuffer("select %s from t_material where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtil.isNullOrEmpty(param.getName())){
			sb.append(" and name like ?");
			args.add("%"+param.getName()+"%");
		}
		int count = jdbcDao.queryForInt(String.format(sb.toString(), " count(1) ") ,args.toArray());
		sb.append(" order by create_time desc limit ?,? ");
		args.add(info.getStartRow());
		args.add(info.getPageSize());
		List<Material> lists = jdbcDao.queryList(Material.class, String.format(sb.toString(), "* "),args.toArray());
		return new PaginationResult<Material>(count, lists);
	}

	@Override
	@ChooseDataSource(mainType=DataSourceConstant.BIZ_TYPE_SLAVE,bizType=DataSourceConstant.PROJECT_SUFFIX)
	public Material findMaterialById(Integer maId) {
		String sql = "select * from t_material where id = ? ";
		List<Material> lists = jdbcDao.queryList(Material.class, sql,maId);
		if(lists != null && lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

	@Override
	@ChooseDataSource(mainType=DataSourceConstant.BIZ_TYPE_MASTER,bizType=DataSourceConstant.PROJECT_SUFFIX)
	public boolean deleteMaterial(Integer maId) {
		String sql = " delete from t_material where id = ? ";
		int con = jdbcDao.update(sql,maId);
		return con > 0 ;
	}

	@Override
	@ChooseDataSource(mainType=DataSourceConstant.BIZ_TYPE_MASTER,bizType=DataSourceConstant.PROJECT_SUFFIX)
	public int updateMaterial(Material ma) {
		int x = jdbcDao.update(ma);
		return x;
	}
	
	
	
}
