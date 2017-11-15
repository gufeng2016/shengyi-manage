/**
 */
package com.sz.pojo.request.coupon;

import java.util.Date;

/**
 * 
 * @author goff.yin 
 * @date 2017-8-18   下午3:49:08 
 * @version 1.0.0 
 */
public class CouponUpdateParams {
		private Long userId;
		
		private Byte isRide;
		
		private Date overduTime;
		
		private Integer amount;
		
		private Integer couponId;
		
		private String couponIds;
		
		private String overduDate;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Byte getIsRide() {
			return isRide;
		}

		public void setIsRide(Byte isRide) {
			this.isRide = isRide;
		}

		public Date getOverduTime() {
			return overduTime;
		}

		public void setOverduTime(Date overduTime) {
			this.overduTime = overduTime;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public Integer getCouponId() {
			return couponId;
		}

		public void setCouponId(Integer couponId) {
			this.couponId = couponId;
		}

		public String getCouponIds() {
			return couponIds;
		}

		public void setCouponIds(String couponIds) {
			this.couponIds = couponIds;
		}

		public String getOverduDate() {
			return overduDate;
		}

		public void setOverduDate(String overduDate) {
			this.overduDate = overduDate;
		}
		
}
