package com.coupon.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "coupon")
public class CouponVO implements java.io.Serializable {
	public CouponVO() {
		super();
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couponId")
    private Integer couponId;

    @Column(name = "couponName", length = 100)
    private String couponName;

    @Column(name = "couponCode", length = 20,unique = true)
    private String couponCode;

    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "minSpendingAmount")
    private Integer minSpendingAmount;

    @Column(name = "couponQuantity")
    private Integer couponQuantity;

    @Column(name = "memberAllowQuantity")
    private Integer memberAllowQuantity;

    @Column(name = "couponDiscount")
    private Integer couponDiscount;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "couponCreateTime")
    private Date couponCreateTime;

    
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



	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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



	public Date getCouponCreateTime() {
		return couponCreateTime;
	}

	public void setCouponCreateTime(Date couponCreateTime) {
		this.couponCreateTime = couponCreateTime;
	}

	@Override
	public String toString() {
		return "CouponVO [couponId=" + couponId + ", couponName=" + couponName + ", couponCode=" + couponCode
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", minSpendingAmount=" + minSpendingAmount
				+ ", couponQuantity=" + couponQuantity + ", memberAllowQuantity=" + memberAllowQuantity
				+ ", couponDiscount=" + couponDiscount + ", couponCreateTime=" + couponCreateTime + "]";
	}

}
