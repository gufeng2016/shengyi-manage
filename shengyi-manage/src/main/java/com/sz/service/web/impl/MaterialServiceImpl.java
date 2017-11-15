package com.sz.service.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.common.util.StringUtil;
import com.sz.dao.web.MaterialDao;
import com.sz.exception.MessageException;
import com.sz.params.material.MaterialClassParam;
import com.sz.params.material.MaterialQueryParam;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.request.ResultMessage;
import com.sz.pojo.web.Material;
import com.sz.service.web.MaterialService;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService{
	
	private Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);
	
	@Autowired
	private MaterialDao materialDao;

	@Override
	public PaginationResult<Material> queryMaterialListByPage(MaterialQueryParam param, PageInfo info) {
		return materialDao.queryMaterialListByPage(param,info);
	}

	@Override
	public ResultMessage updateMaterial(MaterialClassParam params) {
		ResultMessage result = new ResultMessage("9999","操作失败");
		try{
			Material ma = materialDao.findMaterialById(params.getMaId());
			if(ma == null){
				throw new MessageException("板材信息不存在或已删除");
			}
			if(StringUtil.isNullOrEmpty(params.getName())){
				throw new MessageException("板材名称不能为空！");
			}
			if(StringUtil.isNullOrEmpty(params.getGuiGe())){
				throw new MessageException("板材规格不能为空！");
			}
			if(params.getPrice1() == null || params.getPrice2() == null || params.getPrice3() == null || params.getPrice4() == null || params.getPrice5() == null || params.getPrice6() == null){
				throw new MessageException("6个价格不能全为空！");
			}
			if(params.getSalePrice() == null){
				throw new MessageException("销售价格不能为空！");
			}
			if(params.getProfit() == null){
				throw new MessageException("利润不能为空！");
			}
			if(params.getCount() == null){
				throw new MessageException("库存不能为空！");
			}
			
			int id = materialDao.updateMaterial(ma);
			if(id<0){
				throw new MessageException("保存板材失败");
			}
			result.setCode("0000");
			result.setMessage("操作成功");
		}catch (MessageException e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
			logger.info(e.getMessage());
		}catch (Exception e) {
			result.setCode("9999");
			result.setMessage("保存板材 失败！");
			logger.error(e.getMessage());
		}
		return result;
	}


	@Override
	public ResultMessage addMaterial(MaterialClassParam params) {
		ResultMessage result = new ResultMessage("9999","操作失败");
		try{
			if("".equals("")){
				throw new MessageException("库存不能为空！");
			}
			
			result.setCode("0000");
			result.setMessage("操作成功");
		}catch (MessageException e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
			logger.info(e.getMessage());
		}catch (Exception e) {
			result.setCode("9999");
			result.setMessage("保存充值返利失败！");
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean deleteMaterial(MaterialClassParam params) {
		if(params.getMaId() != null){
			return materialDao.deleteMaterial(params.getMaId());
		}
		String[] ids = params.getMaIds().split(",");
		int con = 0;
		for (String id : ids) {
			if(!StringUtil.isNullOrEmpty(id)){
				con += materialDao.deleteMaterial(Integer.valueOf(id)) ? 1:0;
			}
		}
		return con>0;
	}
	
}
