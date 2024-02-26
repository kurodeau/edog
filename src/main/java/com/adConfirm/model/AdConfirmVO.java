package com.adConfirm.model;

import java.sql.Timestamp;

public class AdConfirmVO {
	
	private Integer adConfirmId;
	private Integer adId;
	private String failReason;
	private Timestamp confirmTime;
	private boolean reviewStatus;
	
	public Integer getAdConfirmId() {
		return adConfirmId;
	}
	public void setAdConfirmId(Integer adConfirmId) {
		this.adConfirmId = adConfirmId;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public Timestamp getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}
	public boolean isReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(boolean reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	
	
}
