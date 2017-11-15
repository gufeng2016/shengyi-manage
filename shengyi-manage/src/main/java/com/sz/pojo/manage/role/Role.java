package com.sz.pojo.manage.role;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.sz.common.annotation.ForbidUpdate;
import com.sz.common.annotation.PrimaryKey;
import com.sz.common.annotation.Transient;
import com.sz.pojo.manage.auth.Auth;

/**
 * 类描述: Role
 */
public class Role {

	/**
	 * 角色ID
	 */
	@PrimaryKey
	private Integer roleId;
	/**
	 * 角色父ID
	 */
	@Min(value = 1, message = "{role.parent.null}")
	private Integer parentId;
	/**
	 * 角色名称
	 */
	@Pattern(regexp = "[\u4e00-\u9fa5]{2,20}", message = "{role.name.format.error}")
	private String roleName;
	/**
	 * 角色描述
	 */
	@Pattern(regexp = "[\u4e00-\u9fa5]{2,50}", message = "{role.desc.format.error}")
	private String roleDesc;
	/**
	 * 创建时间
	 */
	@ForbidUpdate
	private Date createDate;
	/**
	 * 修改日期
	 */
	private Date updateDate;

	/**
	 * 子角色ID
	 */
	@Transient
	private List<Integer> childRoleIds;

	/**
	 * 子角色
	 */
	@Transient
	private List<Role> childRoleList;

	/**
	 * 权限列表
	 */
	@Transient
	private List<Auth> authList;

	/**
	 * 权限列表ID
	 */
	@Transient
	private List<Integer> authIds;

	/**
	 * 是否删除（1为未删除， -1为已删除）
	 */
	@ForbidUpdate
	private Integer deleteState = 1;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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

	public List<Integer> getChildRoleIds() {
		return childRoleIds;
	}

	public void setChildRoleIds(List<Integer> childRoleIds) {
		this.childRoleIds = childRoleIds;
	}

	public List<Role> getChildRoleList() {
		return childRoleList;
	}

	public void setChildRoleList(List<Role> childRoleList) {
		this.childRoleList = childRoleList;
	}

	public List<Auth> getAuthList() {
		return authList;
	}

	public void setAuthList(List<Auth> authList) {
		this.authList = authList;
	}

	public List<Integer> getAuthIds() {
		return authIds;
	}

	public void setAuthIds(List<Integer> authIds) {
		this.authIds = authIds;
	}

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}
}