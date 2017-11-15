package com.sz.service.web;

import com.sz.params.material.MaterialClassParam;
import com.sz.params.material.MaterialQueryParam;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.request.ResultMessage;
import com.sz.pojo.web.Material;

public interface MaterialService {
	
	/**分页查询*/
	PaginationResult<Material> queryMaterialListByPage(MaterialQueryParam param, PageInfo info);

	ResultMessage updateMaterial(MaterialClassParam params);

	boolean deleteMaterial(MaterialClassParam params);

	ResultMessage addMaterial(MaterialClassParam params);

}
