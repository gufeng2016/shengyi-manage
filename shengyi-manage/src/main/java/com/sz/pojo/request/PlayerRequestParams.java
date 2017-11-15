package com.sz.pojo.request;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sz.common.util.DateUtil;

/**
 * @author : yunxing.li
 * @date   : 2017年7月11日 下午2:52:38
 * @version: v1.0
 */
public class PlayerRequestParams {
	
	private static final Logger logger = LoggerFactory.getLogger(PlayerRequestParams.class);
	
	private Integer playerId;
	private String beginTimeStr;
	private String endTimeStr;
	private Date beginTime;
	private Date endTime;
	
	//上下分
	private Integer score;
	
	public boolean checkUpdateScoreParams(){
		if(playerId == null || playerId.intValue() <= 0){
			logger.error("参数playerId:{}异常", playerId);
			return false;
		}
		if(score == null){
			logger.error("参数score:{}异常", score);
			return false;
		}
		return true;
	}
	
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
	public String getBeginTimeStr() {
		return beginTimeStr;
	}
	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
		setBeginTime(DateUtil.parse(beginTimeStr, "yyyy-MM-dd HH:mm"));
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		setEndTime(DateUtil.parse(endTimeStr, "yyyy-MM-dd HH:mm"));
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

}
