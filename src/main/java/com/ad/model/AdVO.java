package com.ad.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.seller.entity.SellerVO;


@Entity
@Table(name="ad")
public class AdVO implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="adId" , updatable = false)
	private Integer adId;

	
	@ManyToOne
	@JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
	private SellerVO sellerVO;
	
	@Column(name="adImg" ,columnDefinition = "longblob")
	private byte[] adImg;
	
	@Column(name= "adImgUploadTime")
	private Timestamp adImgUploadTime;
	
	@Column(name = "adName")
	private String adName;
	
	@Column(name = "adUrl")
	private String adUrl;
	
	@Column(name = "adStartTime")
	private Date adStartTime;
	
	@Column(name = "adEndTime")
	private Date adEndTime;
	
	@Column(name ="adLv")
	private Integer adLv;
	
	@Column(name="adMemo")
	private String adMemo;
	
	@Column(name = "adStatus")
	private String adStatus;
	@Override
	public String toString() {
		return "AdVO [adId=" + adId +" adImg=" + Arrays.toString(adImg)
				+ ", adImgUploadTime=" + adImgUploadTime + ", adName=" + adName + ", adUrl=" + adUrl + ", adStartTime="
				+ adStartTime + ", adEndTime=" + adEndTime + ", adLv=" + adLv + ", adMemo=" + adMemo + ", adStatus="
				+ adStatus + ", adCreateTime=" + adCreateTime + ", isEnabled=" + isEnabled + "]";
	}
	
	@Column(name = "adCreateTime")
	private Timestamp adCreateTime;
	
	public AdVO() {
		
	}
	
	
	
	
	public AdVO(Integer adId, byte[] adImg, String adName, Date adStartTime, Date adEndTime, String adStatus) {
		super();
		this.adId = adId;
		this.adImg = adImg;
		this.adName = adName;
		this.adStartTime = adStartTime;
		this.adEndTime = adEndTime;
		this.adStatus = adStatus;
	}




	public AdVO( SellerVO sellerVO, byte[] adImg, Timestamp adImgUploadTime, String adName, String adUrl,
			Date adStartTime, Date adEndTime, Integer adLv, String adMemo, String adStatusd,
			Timestamp adCreateTime, Boolean isEnabled) {
		super();
	
		this.sellerVO = sellerVO;
		this.adImg = adImg;
		this.adImgUploadTime = adImgUploadTime;
		this.adName = adName;
		this.adUrl = adUrl;
		this.adStartTime = adStartTime;
		this.adEndTime = adEndTime;
		this.adLv = adLv;
		this.adMemo = adMemo;
		this.adStatus = adStatus;
		this.adCreateTime = adCreateTime;
		this.isEnabled = isEnabled;
	}

	@Column(name ="isEnabled" )
	private Boolean isEnabled;
	public Integer getAdId() {
		return adId;
	}




	public SellerVO getSellerVO() {
		return sellerVO;
	}




	public void setSellerVO(SellerVO sellerVO) {
		this.sellerVO = sellerVO;
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




	public Date getAdStartTime() {
		return adStartTime;
	}




	public void setAdStartTime(Date adStartTime) {
		this.adStartTime = adStartTime;
	}




	public Date getAdEndTime() {
		return adEndTime;
	}




	public void setAdEndTime(Date adEndTime) {
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








	public String getAdStatus() {
		return adStatus;
	}




	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
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




	public void setAdId(Integer adId) {
		this.adId = adId;
	}


	
	
	

}
