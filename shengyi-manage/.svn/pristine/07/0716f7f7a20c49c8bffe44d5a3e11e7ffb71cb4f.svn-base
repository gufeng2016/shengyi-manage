package com.sz.dao.app;

import java.util.List;

import com.sz.pojo.app.SystemConfig;
import com.sz.pojo.manage.page.PageInfo;

/**
 * @author : yunxing.li
 * @date   : 2017年6月26日 上午10:57:15
 * @version: v1.0
 */
public interface SystemConfigDao {
	
	public static final String SQL_COUNT = "SELECT COUNT(*) FROM t_system_config WHERE 1=1";
	
	public static final String SQL_QUERY = "SELECT * FROM t_system_config WHERE 1=1";
	
	public static final String SQL_DELETE = "DELETE FROM t_system_config WHERE 1=1";
	
	public static final String SQL_INSERT = "INSERT INTO t_system_config (`key`,`value`,`comment`) VALUES (?,?,?)";
	
	public static final String SQL_UPDATE = "UPDATE t_system_config SET `key`=?, `value`=?, `comment`=? WHERE id=?";
	
	/**
	 * 根据key查询
	 * @param key
	 * @throws Exception
	 */
	public SystemConfig queryByKey(String key) throws Exception;
	
	/**
	 * 根据Id查询
	 * @param id
	 * @throws Exception
	 */
	public SystemConfig queryById(Integer id) throws Exception;
	
	/**
	 * 查询记录数
	 * @param key
	 * @throws Exception
	 */
	public int count(String key) throws Exception;
	
	/**
	 * 分页查询
	 * @param key
	 * @param page
	 * @throws Exception
	 */
	public List<SystemConfig> queryByPage(String key, PageInfo page) throws Exception;
	
	/**
	 * 新增配置
	 * @param config
	 * @throws Exceptin
	 */
	public boolean add(SystemConfig config) throws Exception;
	
	/**
	 * 更新配置
	 * @param config
	 * @throws Exception
	 */
	public boolean update(SystemConfig config) throws Exception;
	
	/**
	 * 根据Id删除
	 * @param id
	 * @throws Exception
	 */
	public boolean deleteById(Integer id) throws Exception;
	
	/**
	 * 根据Id批量删除
	 * @param ids
	 * @return 失败的Id集合
	 * @throws Exception
	 */
	public boolean batchDeleteByIds(List<Integer> ids) throws Exception;
	
} 
