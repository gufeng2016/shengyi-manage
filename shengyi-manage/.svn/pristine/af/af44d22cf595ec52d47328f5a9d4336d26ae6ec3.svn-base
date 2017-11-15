package com.sz.pojo.manage.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.sz.common.annotation.ForbidUpdate;
import com.sz.common.annotation.PrimaryKey;
import com.sz.common.annotation.Transient;

/**
 * 类描述: Auth
 */
public class Auth {

	/**
	 * 权限ID
	 */
	@PrimaryKey
	private Integer authId;
	/**
	 * 权限父ID
	 */
	@Min(value = 1, message = "{auth.parent.null}")
	private Integer parentAuthId;
	/**
	 * 权限名称
	 */
	@Pattern(regexp = "[\u4e00-\u9fa5]{2,20}", message = "{auth.name.format.error}")
	private String authName;
	/**
	 * 权限url
	 */
	@Pattern(regexp = "^/[A-Za-z]+[\\w]*[/\\w]*[.\\w]*", message = "{auth.url.format.error}")
	private String url;
	/**
	 * 权限描述
	 */
	@Pattern(regexp = "[\u4e00-\u9fa5]{2,20}", message = "{auth.desc.format.error}")
	private String authDesc;
	/**
	 * 是否启用(1为启用，-1为不可用)
	 */
	@Max(value = 1, message = "{auth.available.error}")
	@Min(value = -1, message = "{auth.available.error}")
	private Integer available = 1;
	/**
	 * 是否菜单显示(1为显示，-1为不显示)
	 */
	@Max(value = 1, message = "{auth.menuDisplay.error}")
	@Min(value = -1, message = "{auth.menuDisplay.error}")
	private Integer menuDisplay = -1;
	
	/**
	 * 手机端是否菜单显示(1为显示，-1为不显示)
	 */
	@Max(value = 1, message = "{auth.mobile.menuDisplay.error}")
	@Min(value = -1, message = "{auth.mobile.menuDisplay.error}")
	private Integer mobileMenuDisplay = -1;

	/**
	 * 排序ID
	 */
	// @NotEmpty(message = "{auth.order.error}")
	//@Min(value = 0, message = "{auth.order.error}")
	private Integer orderId;
	/**
	 * 创建时间
	 */
	@ForbidUpdate
	private Date createDate;
	/**
	 * 权限开放范围(0对所有人开放，1对部分开放)
	 */
	@Transient
	private Integer authRange;

	/**
	 * 子权限ID
	 */
	@Transient
	private List<Integer> childAuthIds;

	/**
	 * 子权限
	 */
	@Transient
	private List<Auth> childAuth;

	/**
	 * 是否删除（1为未删除， -1为已删除）
	 */
	@ForbidUpdate
	private Integer deleteState = 1;


	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Integer getParentAuthId() {
		return parentAuthId;
	}

	public void setParentAuthId(Integer parentAuthId) {
		this.parentAuthId = parentAuthId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getMenuDisplay() {
		return menuDisplay;
	}

	public void setMenuDisplay(Integer menuDisplay) {
		this.menuDisplay = menuDisplay;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getAuthRange() {
		return authRange;
	}

	public void setAuthRange(Integer authRange) {
		this.authRange = authRange;
	}

	public List<Integer> getChildAuthIds() {
		return childAuthIds;
	}

	public void setChildAuthIds(List<Integer> childAuthIds) {
		this.childAuthIds = childAuthIds;
	}

	public List<Auth> getChildAuth() {
		return childAuth;
	}

	public void setChildAuth(List<Auth> childAuth) {
		this.childAuth = childAuth;
		if (null != childAuth && childAuth.size() > 0) {
			List<Integer> childAuthIdList = new ArrayList<Integer>();
			for (Auth auth : childAuth) {
				childAuthIdList.add(auth.getAuthId());
			}
			this.setChildAuthIds(childAuthIdList);
		}
	}

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}

	public Integer getMobileMenuDisplay() {
		return mobileMenuDisplay;
	}

	public void setMobileMenuDisplay(Integer mobileMenuDisplay) {
		this.mobileMenuDisplay = mobileMenuDisplay;
	}
	
}