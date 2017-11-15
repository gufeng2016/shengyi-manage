/**
 */
package com.sz.pojo.request.recharge;

/**
 * 
 * @author goff.yin 
 * @date 2017-8-21   上午10:24:18 
 * @version 1.0.0 
 */
public class RechargeRewardParams {
	
	private Integer rruId;
	private Double chargeMoney;
	/**是否有效  0-否 1-是*/
	private Integer state;
	private String rruIds;
	
	private Double rewardMoney;
	/**奖励骑行券*/
	private Integer rewardTime;
	
	public Double getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(Double chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getRruId() {
		return rruId;
	}
	public void setRruId(Integer rruId) {
		this.rruId = rruId;
	}
	public String getRruIds() {
		return rruIds;
	}
	public void setRruIds(String rruIds) {
		this.rruIds = rruIds;
	}
	public Double getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(Double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public Integer getRewardTime() {
		return rewardTime;
	}
	public void setRewardTime(Integer rewardTime) {
		this.rewardTime = rewardTime;
	}
	
}
