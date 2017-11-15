package com.sz.pojo.manage.user;

import com.sz.common.annotation.ForbidUpdate;
import com.sz.common.annotation.PrimaryKey;
import com.sz.common.annotation.Transient;
import com.sz.pojo.manage.role.Role;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

/***
 *类描述: User
 *
 */
public class User {

	/** 用户ID */
	@PrimaryKey
	private Integer userId;
	/** 用户名 */
	@NotEmpty(message = "{user.name.format.error}")
	@Pattern(regexp = "\\w+[a-zA-Z1-9_.]{5,19}", message = "{user.name.format.error}")
	private String userName;
	/** 密码 */
	private String password;
	/** 否是启用 */
	// @NotEmpty(message = "{user.available.null}")
	@Min(value = 0, message = "{user.available.null}")
	@Max(value = 1, message = "{user.available.null}")
	private Integer available = 1;
	/** 用户中文名 */
	@Pattern(regexp = "[\u4e00-\u9fa5]{2,10}", message = "{user.name.cn.format.error}")
	private String userNameCn;
	/** 邮箱地址 */
	@NotEmpty(message = "{user.email.null}")
	@Email(message = "{user.email.format.error}")
	private String email;
	/** 密码有效时间 */
	private Date effectiveTime;
	/** 上一次密码 */
	private String lastPassword;
	/** 创建日期 */
	@ForbidUpdate
	private Date createDate;
	/** 修改时间 */
	private Date updateDate;

	/**
	 * 是否删除（1为未删除， -1为已删除）
	 */
	@ForbidUpdate
	private Integer deleteState = 1;


	@Transient
	@Valid
	@NotEmpty(message = "{user.roleIds.null}")
	private List<Integer> roleIds;

	@Transient
	private List<Role> roleList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getUserNameCn() {
		return userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getLastPassword() {
		return lastPassword;
	}

	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}
}