/**
 * Copyright  2013-7-16 第七大道-技术支持部-网站开发组
 * 自主运营平台WEB 下午5:41:01
 * 版本号： v1.0
*/

package com.sz.common.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.sqlEntry.SQLCondition;

/**
 * <li>类描述：查询SQL的构建工厂</li>
 * <li>创建者： amos.zhou</li>
 * <li>项目名称： 7road-common</li>
 * <li>创建时间： 2013-7-16 下午5:41:01</li>
 * <li>版本号： v1.0 </li>
 */
public class SelectSQLFactory extends AbstractSQLFactory{

	private SelectSQLFactory(){}

	private static volatile  SelectSQLFactory instance  = new SelectSQLFactory();
	
	public static SelectSQLFactory getInstance(){
		return instance;
	}
	

	@Override
	public String buildSQLMainBody(Class entityClass,List<String> resultProperties ) {
		StringBuilder  selectSQL =  new StringBuilder(" select ").append(getQueryColumns(resultProperties)); 
		selectSQL.append(" from ").append(SQLUtils.getTableName(entityClass));
		return selectSQL.toString();
	}

    /**
     * 查询sql构建，支持模糊匹配
     * @param list
     * @return
     */
    private SQLEntry buildSQLCondition(List<SQLCondition> list) {
        StringBuilder buf = new StringBuilder();
        List<Object> params = new ArrayList<Object>();

        buf.append(" where  delete_state = 1 ");

       if (null != list) {
          for (SQLCondition condition : list) {
              if (null != condition) {
                  buf.append("  and ").append(SQLUtils.convertStrToDBFormat(condition.getKey()));
                  Object value =  condition.getValue();
                  if (condition.isLike()) {
                      buf.append(" LIKE '%" + value + "%' ");
                  } else{
                      buf.append(" = ? ");
                      params.add(value);
                  }
              }
          }
       }
        return new SQLEntry(buf.toString(),params.toArray());
    }
	
	
	
	/**
	 * 
	 * <p>创建时间： 2013-7-17 下午4:29:33</p>
	 * <p>完成SQL的构建</p>
	 * @param entityClass 操作类型 
	 * @param paramMap  参数列表
	 * @param resultProperties 要操作的列
	 * @return SQLEntry
	 */
	public  SQLEntry getSQLEntry(Class entityClass,Map<String, Object> paramMap,List<String> resultProperties) {
		SQLEntry entry = buildSQLCondition(paramMap);
		entry.setSql(buildSQLMainBody(entityClass, resultProperties) + entry.getSql());
		return entry;
	}

	
	/**
	 * 
	 * <p>创建时间： 2013-7-16 下午5:54:35</p>
	 * <p>处理查询列</p>
	 * @param resultProperties
	 * @return
	 */
	private  String getQueryColumns(List<String> resultProperties){
		if(CollectionUtils.isEmpty(resultProperties)){
			return SQLUtils.DEFAULT_ALL_COLUMNS ;
		}
		for(int i = 0; i< resultProperties.size() ;  i++){
			resultProperties.set(i, SQLUtils.convertStrToDBFormat(resultProperties.get(i)));
		}
		String str = resultProperties.toString();
		return str.substring(1, str.length()-1);
	}


    /**
     * 获取记录数的查询语句(如果map==null则查询总的记录数, 不支持模糊查询)
     * @return
     */
    public SQLEntry getQueryRecodeEntry(Class<?> clazz, Map<String, Object> map){
        StringBuilder  selectSQL =  new StringBuilder(" select count(1) from ");
        selectSQL.append(SQLUtils.getTableName(clazz));
        selectSQL.append(" where  delete_state = 1 ");
        List<Object> paramList = new ArrayList<Object>();
        if (null != map && map.size() > 0 ) {
             Set<Map.Entry<String, Object>> entrySet = map.entrySet();
             int i = 0;
            for (Map.Entry<String, Object> entry : entrySet) {
                 selectSQL.append(" and ").append(SQLUtils.convertStrToDBFormat(entry.getKey())).append(" = ? ");
                paramList.add(entry.getValue());
             }
        }
        SQLEntry entry = new SQLEntry();
        entry.setSql(selectSQL.toString());
        entry.setParamValues(paramList.toArray());
        return entry;
    }


    /**
     *  获取记录数的查询语句(如果list==null或为空则查询总的记录数, 支持模糊查询)
     * @param clazz
     * @param list
     * @param <T>
     * @return
     */
    public <T> SQLEntry getQueryRecodeEntry(Class<T> clazz, List<SQLCondition> list) {
        StringBuilder  selectSQL =  new StringBuilder(" select count(1) from ");
        selectSQL.append(SQLUtils.getTableName(clazz));
        selectSQL.append(" where delete_state = 1  ");
        List<Object> paramList = new ArrayList<Object>();
        if (null != list && list.size() > 0 ) {
           for (SQLCondition condition : list) {
               if (null != condition) {
                   selectSQL.append(" and ").append(SQLUtils.convertStrToDBFormat(condition.getKey()));
                   if (condition.isLike()) {
                       selectSQL.append( " LIKE '%" + condition.getValue() + "%' ");
                   } else {
                       selectSQL.append(" = ? ");
                       paramList.add(condition.getValue());
                   }
               }
           }
        }
        SQLEntry entry = new SQLEntry();
        entry.setSql(selectSQL.toString());
        entry.setParamValues(paramList.toArray());
        return entry;
    }


    /**
     *
     * <p>创建时间： 2013-7-17 下午4:29:33</p>
     * <p>完成SQL的构建</p>
     * @param entityClass 操作类型
     * @param paramMap  参数列表(为null或空列表表示查询所有)
     * @return SQLEntry
     */
    public  SQLEntry getPageSQLEntry(Class entityClass,Map<String, Object> paramMap, PageInfo pageInfo) {
        return this.getPageOrderSQLEntry(entityClass, paramMap, pageInfo, null);
    }


    /**
     * 分页排序查询的 SQLEntry实体构建
     * @param entityClass 待查询的实体
     * @param paramMap 参数map
     * @param pageInfo 分页对象
     * @param order 排序语句（必须以order by开头）
     * @param <T>
     * @return
     */
    public <T> SQLEntry getPageOrderSQLEntry(Class<T> entityClass, Map<String, Object> paramMap, PageInfo pageInfo, String order){
        Assert.notNull(entityClass, "传入的类型不能为null");
        Assert.notNull(pageInfo, "传入的分页队形不能为null");
        SQLEntry entry = buildSQLCondition(paramMap);
        StringBuilder builder = new StringBuilder();
        builder.append(buildSQLMainBody(entityClass, null)).append(entry.getSql());
        if(StringUtils.isNotBlank(order)){
            Assert.state(order.trim().toLowerCase().startsWith("order by"), "排序语句必须以order by开始");
            builder.append(" ").append(order).append(" ");
        }
        builder.append(" limit ").append(pageInfo.getStartRow()).append(" , ").append(pageInfo.getPageSize());
        entry.setSql(builder.toString());
        return entry;
    }

    /**
     * 分页排序查询的 SQLEntry实体构建, 支持模糊匹配
     * @param clazz 待查询的实体
     * @param list 查询条件list(SQLQueryCondition)
     * @param pageInfo 分页队形
     * @param order 啊皮鞋语句
     * @param <T>
     * @return
     */
    public <T> SQLEntry getPageOrderSQLEntry(Class<T> clazz, List<SQLCondition> list, PageInfo pageInfo, String order) {
        Assert.notNull(clazz, "传入的类型不能为null");
        Assert.notNull(pageInfo, "传入的分页队形不能为null");
        SQLEntry entry = buildSQLCondition(list);
        StringBuilder builder = new StringBuilder();
        builder.append(buildSQLMainBody(clazz, null)).append(entry.getSql());
        if(StringUtils.isNotBlank(order)){
            Assert.state(order.trim().toLowerCase().startsWith("order by"), "排序语句必须以order by开始");
            builder.append(" ").append(order).append(" ");
        }
        builder.append(" limit ").append(pageInfo.getStartRow()).append(" , ").append(pageInfo.getPageSize());
        entry.setSql(builder.toString());
        return entry;
    }

	/**
	 * 排序查询的 SQLEntry实体构建, 支持模糊匹配
	 * @param clazz 待查询的实体
	 * @param list 查询条件list({@link com.sz.pojo.manage.sqlEntry.SQLCondition})
	 * @param order 排序方式
	 * @param <T>
	 * @return
	 */
	public <T> SQLEntry getOrderSQLEntry(Class<T> clazz, List<SQLCondition> list, String order) {
		Assert.notNull(clazz, "传入的类型不能为null");
		SQLEntry entry = buildSQLCondition(list);
		StringBuilder builder = new StringBuilder();
		builder.append(buildSQLMainBody(clazz, null)).append(entry.getSql());
		if(StringUtils.isNotBlank(order)){
			Assert.state(order.trim().toLowerCase().startsWith("order by"), "排序语句必须以order by开始");
			builder.append(" ").append(order).append(" ");
		}
		entry.setSql(builder.toString());
		return entry;
	}
}
