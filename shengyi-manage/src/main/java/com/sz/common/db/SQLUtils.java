package com.sz.common.db;

import com.sz.common.annotation.PrimaryKey;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;


/**
 * 
 * <li>类描述：数据库操作的工具类</li>
 * <li>创建者： amos.zhou</li>
 * <li>项目名称： 7road-common</li>
 * <li>创建时间： 2013-7-17 下午2:57:34</li>
 * <li>版本号： v1.0 </li>
 */
public final class SQLUtils {
	private SQLUtils(){}
	
	
	
	/**默认表前缀以t_开头*/
	public static String tablePrefix ="T_" ;
	/**默认的数据库主键 ID*/
	private static final String DEFAULT_PRIMARY_KEY_NAME = "ID";
	
	public static final String DEFAULT_ALL_COLUMNS = " *  ";
	
	
//	/**
//	 * 
//	 * <p>创建时间： 2013-7-16 上午10:43:01</p>
//	 * <p>获取总记录数的SQL</p>
//	 * @param sql
//	 * @return
//	 */
//	public static String getCountSQL(String sql){
//		StringBuilder buf = new StringBuilder("select count(1) as total from ( ");
//		buf.append(sql).append("  ) aa ");
//		return buf.toString();
//	}
	
	
	
	/**
	 * 
	 * <p>创建时间： 2013-7-16 上午10:49:10</p>
	 * <p>转aaBbCc转为AA_BB_CC<p>
	 * @param str
	 * @return
	 */
	public static String convertStrToDBFormat(String str){
		return str.replaceAll("[a-z](?=[A-Z]+)","$0_").toUpperCase();
	}
	
	
	/**
	 * 
	 * <p>创建时间： 2013-7-16 上午11:30:57</p>
	 * <p>获取表名</p>
	 * @param classz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getTableName(Class classz){
		return  StringUtils.trimToEmpty(tablePrefix) + convertStrToDBFormat(classz.getSimpleName());
	}

    /**
     * 获取主键
     * @param clazz
     * @return
     */
    public static String getPrimaryKeyName (Class clazz) {
        String primaryKeyName = null;
        Field[] fieldArray = clazz.getDeclaredFields();
        for (int i = 0; i < fieldArray.length; i++) {
            Field f = fieldArray[i];
            PrimaryKey primaryKey = f.getAnnotation(PrimaryKey.class);
            if(primaryKey != null){
                primaryKeyName = f.getName();
            }
        }
        if(StringUtils.isBlank(primaryKeyName)) {
            primaryKeyName = DEFAULT_PRIMARY_KEY_NAME;
        }
        return primaryKeyName;
    }

    public static void main(String[] args) {

    }
}
