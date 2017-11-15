package com.sz.dao.manage;

import java.util.List;

import com.sz.pojo.manage.config.Config;

/**
 * 配置Dao
 * @author jiangfan.zhou
 * date Jun 4, 2014
 */
public interface ConfigDao {


	/**
	 * 查询根配置和顶级配置
	 * @return
	 */
	List<Config> queryAllConfig() throws Exception;
	
	/**
	 * 模糊查询
	 * @throws Exception
	 */
	List<Config> queryByKeyFuzzy(String key) throws Exception;

	/**
	 * 增加配置,并返回其ID
	 * @param entity
	 * @return
	 */
	int addConfig(Config entity) throws Exception;

	/**
	 * 根据ID查询配置
	 * @param id
	 * @return
	 */
	Config queryConfigById(int id) throws Exception;
	
	/**
     * 根据key查询配置
     * @param key
	 * @param siteId
     * @return
     */
	Config queryConfigByKey(String key) throws Exception;

	/**
	 * 更新配置并返回更新的行数
	 * @param entity
	 * @return
	 */
	int updateConfig(Config entity) throws Exception;

	/**
	 * 删除配置并返回删除的行数
	 * @param id
	 * @return
	 */
	int delConfig(int id) throws Exception;

	/**
	 * 查询配置信息数
	 * @return
	 */
	int queryConfigCount() throws Exception;

}
