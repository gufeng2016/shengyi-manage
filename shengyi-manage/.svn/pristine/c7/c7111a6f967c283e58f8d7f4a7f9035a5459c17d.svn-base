package com.sz.service.manage;

import java.util.List;

import com.sz.pojo.manage.config.Config;

/**
 * 配置service接口
 * @author jiangfan.zhou
 * date Jun 4, 2014
 */
public interface ConfigService {


	/**
	 * 查询根配置和顶级配置
	 * @return
	 */
	List<Config> queryAllConfig() throws Exception;

	/**
	 * 创建配置
	 * @param entity
	 * @return
	 */
	boolean addConfig(Config entity) throws Exception;

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
	 * 模糊查询
	 * @param key
	 * @throws Exception
	 */
	List<Config> queryConfigByKeyFuzzy(String key) throws Exception;

	/**
	 * 更新配置信息
	 * @param entity
	 * @return
	 */
	boolean updateConfig(Config entity) throws Exception;

	/**
	 * 删除配置信息
	 * @param id
	 * @return
	 */
	boolean delConfig(int id) throws Exception;

    /**
     * 从缓存中获取值
     * @param key
	 * @param siteId
     * @return
     */
    String getCacheConfigByKey(String key);

    /**
     * 重新加载配置缓存
     */
    void reload();

}
