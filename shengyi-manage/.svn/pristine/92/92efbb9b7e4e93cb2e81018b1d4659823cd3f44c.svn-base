package com.sz.pojo.manage.auth;


import com.sz.common.annotation.ForbidUpdate;
import com.sz.common.annotation.PrimaryKey;

/***
 *类描述: AuthIpType
 *
 */
public class AuthIpType {

	/** 主键ID */
	@PrimaryKey
	private Integer id;
	/** 类型ID */
	private Integer typeId;
	/** 类型名称 */
	private String typeName;
	/** 类型简称 */
	private String typeShortName;
	/**
	 * 是否删除（1为未删除，-1为已删除）
	 */
	@ForbidUpdate
	private int deleteState = 1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeShortName() {
		return typeShortName;
	}

	public void setTypeShortName(String typeShortName) {
		this.typeShortName = typeShortName;
	}

	public int getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(int deleteState) {
		this.deleteState = deleteState;
	}
}