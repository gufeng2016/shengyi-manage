package com.sz.pojo.manage.role;

import com.sz.common.annotation.ForbidUpdate;
import com.sz.common.annotation.PrimaryKey;

import java.util.Date;

/***
 *类描述: RoleAuth
 *
 */
public class RoleAuth {
	@PrimaryKey
	private Integer ID;

	/** 角色ID */
	private Integer roleId;
	/** 权限ID */
	private Integer authId;
	/** 创建时间 */
	@ForbidUpdate
	private Date createDate;

	/**
	 * 是否删除（1为未删除， -1为已删除）
	 */
	@ForbidUpdate
	private Integer deleteState = 1;


	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}
}