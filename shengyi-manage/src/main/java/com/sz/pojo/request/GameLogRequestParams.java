package com.sz.pojo.request;

import java.util.Date;

/**
 * @author : yunxing.li
 * @date   : 2017年7月19日 下午5:50:06
 * @version: v1.0
 */
public class GameLogRequestParams {

	private Integer playerId;
	private Date beginTime;
	private Date endTime;
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
