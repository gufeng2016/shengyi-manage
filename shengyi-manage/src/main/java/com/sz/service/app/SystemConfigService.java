package com.sz.service.app;

import java.util.List;

import com.sz.pojo.app.SystemConfig;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;

/**
 * @author : yunxing.li
 * @date   : 2017年6月26日 上午11:23:22
 * @version: v1.0
 */
public interface SystemConfigService {
	
	/**
	 * 根据Id查询 
	 * @param id
	 * @throws Exception
	 */
	public SystemConfig queryById(Integer id) throws Exception;
	
	/**
	 * 根据key查询 
	 * @param key
	 * @throws Exception
	 */
	public SystemConfig queryByKey(String key) throws Exception;
	
	/**
	 * 检查key是否存在 
	 * @param key
	 * @throws Exception
	 */
	public boolean checkKey(String key) throws Exception;
	
	/**
	 * 分页查询 
	 * @param page
	 * @throws Exception
	 */
	public PaginationResult<SystemConfig> queryByPage(String key, PageInfo page) throws Exception;
	
	/**
	 * 新增配置
	 * @param config
	 * @throws Exception
	 */
	public boolean add(SystemConfig config) throws Exception;
	
	/**
	 * 更新
	 * @param config
	 * @throws Exception
	 */
	public boolean update(SystemConfig config) throws Exception;
	
	/**
	 * 删除 
	 * @param id
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws Exception;
	
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public boolean batchDelete(List<Integer> ids) throws Exception;

}
