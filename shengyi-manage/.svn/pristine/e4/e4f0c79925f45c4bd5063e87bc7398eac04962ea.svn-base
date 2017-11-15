package com.sz.pojo.manage.auth;


import com.sz.common.annotation.ForbidUpdate;
import com.sz.common.annotation.PrimaryKey;

/***
 *类描述: AuthIp
 *
 */
public class AuthIp {

	/**
	* 主键ID
	*/
	@PrimaryKey
	private Integer id;

	/**
	 * ip地址(兼容ipv6)
	 */
	private String ip;

	/**
	 * 允许类型
	 */
	private Integer type;

	/**
	 * 是否启用(1为启用，-1为不启用)
	 */
	private Integer available;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public int getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(int deleteState) {
		this.deleteState = deleteState;
	}
}