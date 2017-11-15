package com.sz.pojo.manage.log;

import com.sz.common.annotation.PrimaryKey;

import java.util.Date;

/***
 *类描述: LoginLog
 *
 */
public class LoginLog {

	/** 主键ID */
	@PrimaryKey
	private Integer id;
	/** 登录状态(1为成功，0为失败) */
	private Integer state;
	/** 登录IP */
	private String ip;
	/** 登录时间 */
	private Date loginTime;
	/** 登录描述 */
	private String message;
	/** 是否锁定 */
	private int loginLock;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLoginLock() {
		return loginLock;
	}

	public void setLoginLock(int loginLock) {
		this.loginLock = loginLock;
	}
}