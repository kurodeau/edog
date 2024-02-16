package com.coupon.__model;

import java.sql.Date;
import java.sql.Timestamp;

public class CouponVO implements java.io.Serializable{
	private Integer couponId;
	private String couponName;
	private String couponCode;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer minSpendingAmount;
	private Integer couponQuantity;
	private Integer memberAllowQuantity;
	private Integer couponDiscount;
	private Timestamp couponCreateTime;
	
	
	public CouponVO() {
		super();
	}
	
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Integer getMinSpendingAmount() {
		return minSpendingAmount;
	}
	public void setMinSpendingAmount(Integer minSpendingAmount) {
		this.minSpendingAmount = minSpendingAmount;
	}
	public Integer getCouponQuantity() {
		return couponQuantity;
	}
	public void setCouponQuantity(Integer couponQuantity) {
		this.couponQuantity = couponQuantity;
	}
	public Integer getMemberAllowQuantity() {
		return memberAllowQuantity;
	}
	public void setMemberAllowQuantity(Integer memberAllowQuantity) {
		this.memberAllowQuantity = memberAllowQuantity;
	}
	public Integer getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(Integer couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public Timestamp getCouponCreateTime() {
		return couponCreateTime;
	}
	public void setCouponCreateTime(Timestamp couponCreateTime) {
		this.couponCreateTime = couponCreateTime;
	}

}
