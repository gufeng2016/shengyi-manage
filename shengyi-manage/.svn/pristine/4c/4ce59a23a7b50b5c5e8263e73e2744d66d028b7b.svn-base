
package com.sz.pojo.manage.user;

import com.sz.common.annotation.ForbidUpdate;
import com.sz.common.annotation.PrimaryKey;

import java.util.Date;

/***
 *类描述: UserRole
 *
 */
public class UserRole {

	/** 主键ID */
	@PrimaryKey
	private Integer ID;
	/** userId*/
	private Integer userId;
	/** roleId*/
	private Integer roleId;
	/** 创建时间 */
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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