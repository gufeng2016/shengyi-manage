package com.sz.control.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.common.util.StringUtil;
import com.sz.exception.MessageException;
import com.sz.params.material.MaterialClassParam;
import com.sz.params.material.MaterialQueryParam;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.request.ResultMessage;
import com.sz.pojo.web.Material;
import com.sz.service.web.MaterialService;

@Controller
@RequestMapping("/material")
public class MaterialController {
	
	private Logger logger = LoggerFactory.getLogger(MaterialController.class);
	
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value = "/query")
	public ModelAndView query(MaterialQueryParam param,HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		try {
			view.setViewName("material.list");
			PageInfo info = PageInfo.getPageInfo(request);
			PaginationResult<Material> paginationResult = materialService.queryMaterialListByPage(param, info);
			
			view.addObject("paginationResult", paginationResult);
			
			return view;
		} catch (Exception e) {
			logger.error("转向增加用户页面时出现异常", e);
		}
		return view;
	}
	
	@RequestMapping(value = "/addView")
	public ModelAndView addView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		try {
			view.setViewName("material.add");
			return view;
		} catch (Exception e) {
			logger.error("转向增加用户页面时出现异常", e);
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update")
	public Map<String, Object> update(MaterialClassParam params,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(params.getMaId() == null){
				throw new MessageException("缺少maId");
			}
			ResultMessage result = materialService.updateMaterial(params);
			if(!result.success()){
				throw new MessageException(result.getMessage());
			}
			resultMap.put("code", "0000");
			resultMap.put("msg", "操作成功");
		}catch (MessageException e) {
			logger.info("修改板材异常:", e.getMessage());
			resultMap.put("code", "9999");
			resultMap.put("msg", e.getMessage());
		} catch (Exception e) {
			logger.error("修改板材异常", e);
			resultMap.put("code", "9999");
			resultMap.put("msg", "操作失败");
		}
		return resultMap;
		
	}
	@ResponseBody
	@RequestMapping(value = "/add")
	public Map<String, Object> add(MaterialClassParam params,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ResultMessage result = materialService.addMaterial(params);
			if(!result.success()){
				throw new MessageException(result.getMessage());
			}
			resultMap.put("code", "0000");
			resultMap.put("msg", "操作成功");
		}catch (MessageException e) {
			logger.info("添加板材异常:", e.getMessage());
			resultMap.put("code", "9999");
			resultMap.put("msg", e.getMessage());
		} catch (Exception e) {
			logger.error("添加板材异常", e);
			resultMap.put("code", "9999");
			resultMap.put("msg", "操作失败");
		}
		return resultMap;
		
	}
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(MaterialClassParam params,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(params.getMaId() == null && StringUtil.isNullOrEmpty(params.getMaIds())){
				throw new MessageException("缺少maId");
			}
			boolean result = materialService.deleteMaterial(params);
			if(!result){
				throw new MessageException("删除失败");
			}
			resultMap.put("code", "0000");
			resultMap.put("msg", "操作成功");
		}catch (MessageException e) {
			logger.info("删除板材异常:", e.getMessage());
			resultMap.put("code", "9999");
			resultMap.put("msg", e.getMessage());
		} catch (Exception e) {
			logger.error("删除板材异常", e);
			resultMap.put("code", "9999");
			resultMap.put("msg", "操作失败");
		}
		return resultMap;
		
	}
}
