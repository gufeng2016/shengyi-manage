package com.sz.dao;

import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.sqlEntry.SQLCondition;

import java.util.List;

/**
 * User： xin.fang
 * Date： 14-6-9 
 * Time： 下午5:23
 *
 */
public interface BaseDao {

	/**
	 * 保存对象
	 * @param t 待保存的对象
	 * @return
	 */
	<T> Integer add(T t) throws Exception;

	/**
	 * 批量增加对象
	 * @param list
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	<T> int [] addBatch(List<T> list)  throws Exception;

	/**
	 * 根据ID查询对象
	 * @param ID 主键ID
	 * @return
	 */
	<T> T queryById(Class<T> clazz, int id) throws Exception;

	/**
	 * 修改对象
	 * @param t 待修改的对象
	 * @return
	 */
	<T> int update(T t) throws Exception;

	/**
	 * 根据ID删除对象
	 * @param clazz 待删除对象的class
	 * @param ID 主键ID
	 * @return
	 */
	<T> Integer del(Class<T> clazz, int id) throws Exception;

	/**
	 * 批量删除对象
	 *
	 * @param clazz 待删除对象的class
	 * @param IDS 对象ID的集合
	 * @return
	 */
	<T> int[] delBatch(Class<T> clazz, List<Integer> IDS) throws Exception;

	/**
	 * 根据条件删除
	 *
	 * @param clazz
	 * @param list 条件列表
	 * @return
	 */
	<T> int delByCondition(Class<T> clazz, List<SQLCondition> list) throws Exception;

    /**
     * 批量删除对象
     *
     * @param clazz 待删除对象的class
     * @param IDS 对象ID的集合
     * @return
     */
    public <T> int logicaDel(Class<T> clazz, String[] IDS) throws Exception;

	/**
	 * 分页查询对象
	 * @param clazz 待查询对象的class
	 * @param list 查询条件
	 * @param pageInfo 分页对象
	 * @param order 排序方式
	 * @return
	 */
	<T> PaginationResult<T> query(Class<T> clazz, List<SQLCondition> list, PageInfo pageInfo, String order) throws Exception;

	/**
	 * 按条件分页查询文章类型
	 * @param clazz 待查询对象的class
	 * @param list 查询条件
	 * @param pageInfo 分页对象
	 *  @param order 排序方式
	 * @return
	 */
	<T> List<T> queryList(Class<T> clazz, List<SQLCondition> list, PageInfo pageInfo, String order) throws Exception;

	/**
	 * 按条件查询文章类型
	 * @param clazz 待查询对象的class
	 * @param list 查询条件
	 *  @param order 排序方式
	 * @return
	 */
	<T> List<T> queryList(Class<T> clazz, List<SQLCondition> list, String order) throws Exception;


	/**
	 * 按条件查询文章类型数
	 * @param clazz 待查询对象的class
	 * @param list 查询条件
	 * @return
	 */
	<T> int queryRecord(Class<T> clazz, List<SQLCondition> list) throws Exception;

}
