package com.petdraw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.buyer.entity.BuyerVO;

@Entity
@Table(name = "petdraw") // 指定對應的資料庫表格名稱
public class PetDrawVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petDrawId")
	private Integer petDrawId;

	@ManyToOne
	@JoinColumn(name = "memberId", referencedColumnName = "memberId")
	private BuyerVO memberMain;

	@ManyToOne
	@JoinColumn(name = "memberpairId", referencedColumnName = "memberId")
	private BuyerVO memberPair;

	public BuyerVO getMemberMain() {
		return memberMain;
	}

	public void setMemberMain(BuyerVO memberMain) {
		this.memberMain = memberMain;
	}

	@Column(name = "isMemberLike")
	private Boolean isMemberLike;

	@Column(name = "memberResTime")
	private Date memberResTime;

	@Column(name = "memberPairResTime")
	private Date memberPairResTime;

	@Column(name = "isMemberPairLike")
	private Boolean isMemberPairLike;

	@Column(name = "petDrawTime")
	private Date petDrawTime;

	@Column(name = "petDrawLog")
	private Double petDrawLog;

	@Column(name = "petDrawLat")
	private Double petDrawLat;

	public Integer getPetDrawId() {
		return petDrawId;
	}

	public void setPetDrawId(Integer petDrawId) {
		this.petDrawId = petDrawId;
	}


	public BuyerVO getMemberPair() {
		return memberPair;
	}

	public void setMemberPair(BuyerVO memberPair) {
		this.memberPair = memberPair;
	}

	public Boolean getIsMemberLike() {
		return isMemberLike;
	}

	public void setIsMemberLike(Boolean isMemberLike) {
		this.isMemberLike = isMemberLike;
	}

	public Date getMemberResTime() {
		return memberResTime;
	}

	public void setMemberResTime(Date memberResTime) {
		this.memberResTime = memberResTime;
	}

	public Date getMemberPairResTime() {
		return memberPairResTime;
	}

	public void setMemberPairResTime(Date memberPairResTime) {
		this.memberPairResTime = memberPairResTime;
	}

	public Boolean getIsMemberPairLike() {
		return isMemberPairLike;
	}

	public void setIsMemberPairLike(Boolean isMemberPairLike) {
		this.isMemberPairLike = isMemberPairLike;
	}

	public Date getPetDrawTime() {
		return petDrawTime;
	}

	public void setPetDrawTime(Date petDrawTime) {
		this.petDrawTime = petDrawTime;
	}

	public Double getPetDrawLog() {
		return petDrawLog;
	}

	public void setPetDrawLog(Double petDrawLog) {
		this.petDrawLog = petDrawLog;
	}

	public Double getPetDrawLat() {
		return petDrawLat;
	}

	public void setPetDrawLat(Double petDrawLat) {
		this.petDrawLat = petDrawLat;
	}

}
