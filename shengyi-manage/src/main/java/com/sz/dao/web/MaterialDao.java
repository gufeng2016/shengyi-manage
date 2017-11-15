package com.sz.dao.web;

import com.sz.params.material.MaterialQueryParam;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.web.Material;

public interface MaterialDao {
	
	PaginationResult<Material> queryMaterialListByPage(MaterialQueryParam param, PageInfo info);

	Material findMaterialById(Integer maId);

	boolean deleteMaterial(Integer maId);

	int updateMaterial(Material ma);

}
