/**
 * Copyright  2013-6-17 第七大道-技术支持部-网站开发组
 * 自主运营平台WEB 下午5:58:33
 * 版本号： v1.0
*/

package com.sz.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.sz.common.annotation.Transient;
import com.sz.pojo.manage.sqlEntry.SQLCondition;

/**
 * <li>类描述：</li>
 * <li>创建者： amos.zhou</li>
 * <li>项目名称： platform-manage</li>
 * <li>创建时间： 2013-6-17 下午5:58:33</li>
 * <li>版本号： v1.0 </li>
 */
public class ReflectUtil {

	/**
	 * 
	 * <li>创建时间： 2013-6-17 下午6:01:41</li>
	 * <li>创建人：amos.zhou </li>
	 * <li>方法描述 : 寻找方法名唯 一的方法</li>
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Method findUniqueMethod(Class<?> clazz, String name) {
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(name, "Method name must not be null");
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (Method method : methods) {
				if (name.equals(method.getName())) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	/**
	 * 组装要查询的sql
	 * @author goff.yin 
	 * @date 2017-8-16 下午6:07:48  
	 * @version 1.0.0 
	 * @param class1
	 * @param nickName 
	 * @return
	 */
	public  static <T> String createSqlParams(Class<T> clazz, String nickName) {
		if(!StringUtil.isNullOrEmpty(nickName)){
			nickName = (nickName+".").toUpperCase();
		}
		StringBuffer sb = new StringBuffer(" ");
		Field[] fields = clazz.getDeclaredFields();
		int con = 0;
		for (Field field : fields) {
			Transient tr = field.getAnnotation(Transient.class);
			if(tr != null){
				continue;
			}
			if(con >0  ){
				sb.append(", ");
			}
			sb.append(" ").append(nickName).append(convertStrToDBFormat(field.getName()));
			con ++;
		}
		return sb.toString();
	}
	
	
	public static String convertStrToDBFormat(String str){
		return str.replaceAll("[a-z](?=[A-Z]+)","$0_").toUpperCase();
	}

	/**
	 * @author goff.yin 
	 * @date 2017-8-18 下午12:10:02  
	 * @version 1.0.0 
	 * @param params
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public  static <T> List<SQLCondition> parseCondition(T t) throws  Exception {
		if(t!= null){
			Class clazz = t.getClass();
			List<SQLCondition> lists = new ArrayList<SQLCondition>();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				lists.add(new SQLCondition(field.getName(), field.get(t)));
			}
			return lists;
		}
		return null;
	}
}
