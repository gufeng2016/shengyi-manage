package com.sz.pojo.manage.json;


/**
 * User: xin.fang
 * Date: 14-3-10
 * Time: 下午4:02
 * 用户登录成功后在session存放的json对象
 */
public class LoginSessionObject {

	private int userId;

	private String userName;

	private String userNameCn;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserNameCn() {
		return userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}
	
}
