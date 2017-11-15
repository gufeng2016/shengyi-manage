package com.sz.pojo.manage.config;

import com.sz.common.annotation.PrimaryKey;
import com.sz.common.annotation.Transient;

/***
 *类描述: Config
 *
 */
public class Config {

	/** 主键ID */
	@PrimaryKey
	private Integer id;
	/** 关键码  */
	private String key;
	/** 值 */
	private String value;
	/** 备注 */
	private String remark;
	/**
	 * 是否删除（1为未删除， -1为已删除）
	 */
	@Transient
	private int deleteState;
	
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	public int getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(int deleteState) {
		this.deleteState = deleteState;
	}
}