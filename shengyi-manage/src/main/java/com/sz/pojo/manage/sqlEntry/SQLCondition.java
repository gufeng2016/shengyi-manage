package com.sz.pojo.manage.sqlEntry;

import org.apache.commons.lang.StringUtils;

/**
 * 用户SQL查询条件的实体类
 */
public class SQLCondition {

	private String key;

	private Object value;

    private String operator;//add by vic ,used to control the assembly of 'where' condition

	private boolean isLike;


	public SQLCondition() {
	}

	public SQLCondition(String key, Object value, boolean isLike) {
		if (StringUtils.isBlank(key)) {
			this.key = "";
		} else {
			this.key = key;
		}

		if (null == value) {
			this.value = "";
		} else {
			this.value = value;
		}
		this.isLike = isLike;
	}

	public SQLCondition(String key, Object value) {
		if (StringUtils.isBlank(key)) {
			this.key = "";
		} else {
			this.key = key;
		}

		if (null == value) {
			this.value = "";
		} else {
			this.value = value;
		}
	}

    public SQLCondition(String key, String operator, Object value) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
