/**
 */
package com.sz.pojo.request.coupon;

/**
 * 
 * @author goff.yin 
 * @date 2017-8-18   上午11:58:03 
 * @version 1.0.0 
 */
public class CouponQueyParams {
	
	private Long userId;
	/**是否已使用 0-否 1-是*/
	private Integer used;
	/**过期 0-未过期过期 1-过期*/
	private Integer overdu;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getOverdu() {
		return overdu;
	}

	public void setOverdu(Integer overdu) {
		this.overdu = overdu;
	}
	
	
}
