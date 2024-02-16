package com.ad.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class AdVO implements Serializable {
	
	private Integer adId;
	private Integer sellerId;
	private byte[] adImg;
	private Timestamp adImgUploadTime;
	private String adName;
	private String adUrl;
	private Timestamp adStartTime;
	private Timestamp adEndTime;
	private Integer adLv;
	private String adMemo;
	private Boolean isAdConfirm;
	@Override
	public String toString() {
		return "AdVO [adId=" + adId + ", sellerId=" + sellerId + ", adImg=" + Arrays.toString(adImg)
				+ ", adImgUploadTime=" + adImgUploadTime + ", adName=" + adName + ", adUrl=" + adUrl + ", adStartTime="
				+ adStartTime + ", adEndTime=" + adEndTime + ", adLv=" + adLv + ", adMemo=" + adMemo + ", isAdConfirm="
				+ isAdConfirm + ", adCreateTime=" + adCreateTime + ", isEnabled=" + isEnabled + "]";
	}
	private Timestamp adCreateTime;
	private Boolean isEnabled;
	
	
	
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public byte[] getAdImg() {
		return adImg;
	}
	public void setAdImg(byte[] adImg) {
		this.adImg = adImg;
	}
	public Timestamp getAdImgUploadTime() {
		return adImgUploadTime;
	}
	public void setAdImgUploadTime(Timestamp adImgUploadTime) {
		this.adImgUploadTime = adImgUploadTime;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public Timestamp getAdStartTime() {
		return adStartTime;
	}
	public void setAdStartTime(Timestamp adStartTime) {
		this.adStartTime = adStartTime;
	}
	public Timestamp getAdEndTime() {
		return adEndTime;
	}
	public void setAdEndTime(Timestamp adEndTime) {
		this.adEndTime = adEndTime;
	}
	public Integer getAdLv() {
		return adLv;
	}
	public void setAdLv(Integer adLv) {
		this.adLv = adLv;
	}
	public String getAdMemo() {
		return adMemo;
	}
	public void setAdMemo(String adMemo) {
		this.adMemo = adMemo;
	}
	public Boolean getIsAdConfirm() {
		return isAdConfirm;
	}
	public void setIsAdConfirm(Boolean isAdConfirm) {
		this.isAdConfirm = isAdConfirm;
	}
	public Timestamp getAdCreateTime() {
		return adCreateTime;
	}
	public void setAdCreateTime(Timestamp adCreateTime) {
		this.adCreateTime = adCreateTime;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	
 	

}
