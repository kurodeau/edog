package com.buyer.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "buyer")
public class BuyerVO implements java.io.Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberId")
	private Integer memberId;
	
	@Column(name = "memberEmail")
	private String memberEmail; 
	
	@Column(name = "thirdFrom")
	private String thirdFrom;
	
	@Column(name = "memberName")
	private String memberName;
	
	@Column(name = "memberPhone")
	private String memberPhone;
	
	@Column(name = "memberMobile")
	private String memberMobile; 
	
	@Column(name = "memberBirthday")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberBirthday; 
	
	@Column(name = "memberPassword")
	private String memberPassword; 
	
	@Column(name = "memberAddress")
	private String memberAddress; 
	
	@Column(name = "isMemberEmail")
	private Boolean isMemberEmail; 
	
	@Column(name = "memberRegistrationTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberRegistrationTime; 
	
	@Column(name = "petName")
	private String petName; 
	
	@Column(name = "petImg", columnDefinition = "LONGBLOB" )
	private byte[] petImg; 
	
	@Column(name = "petImgUploadTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date petImgUploadTime; 
	
	@Column(name = "petVaccName1")
	private String petVaccName1; 
	
	@Column(name = "petVaccTime1")
	@Temporal(TemporalType.TIMESTAMP)
	private Date petVaccTime1; 
	
	@Column(name = "petVaccName2")
	private String petVaccName2; 
	
	@Column(name = "petVaccTime2")
	@Temporal(TemporalType.TIMESTAMP)
	private Date petVaccTime2; 
	
	@Column(name = "isConfirm")
	private Boolean isConfirm;
	
	public BuyerVO() {
		super();
	};
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getThirdFrom() {
		return thirdFrom;
	}

	public void setThirdFrom(String thirdFrom) {
		this.thirdFrom = thirdFrom;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public Date getMemberBirthday() {
		return memberBirthday;
	}

	public void setMemberBirthday(Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public Boolean getIsMemberEmail() {
		return isMemberEmail;
	}

	public void setIsMemberEmail(Boolean isMemberEmail) {
		this.isMemberEmail = isMemberEmail;
	}

	public Date getMemberRegistrationTime() {
		return memberRegistrationTime;
	}

	public void setMemberRegistrationTime(Date memberRegistrationTime) {
		this.memberRegistrationTime = memberRegistrationTime;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public byte[] getPetImg() {
		return petImg;
	}

	public void setPetImg(byte[] petImg) {
		this.petImg = petImg;
	}

	public Date getPetImgUploadTime() {
		return petImgUploadTime;
	}

	public void setPetImgUploadTime(Date petImgUploadTime) {
		this.petImgUploadTime = petImgUploadTime;
	}

	public String getPetVaccName1() {
		return petVaccName1;
	}

	public void setPetVaccName1(String petVaccName1) {
		this.petVaccName1 = petVaccName1;
	}

	public Date getPetVaccTime1() {
		return petVaccTime1;
	}

	public void setPetVaccTime1(Date petVaccTime1) {
		this.petVaccTime1 = petVaccTime1;
	}

	public String getPetVaccName2() {
		return petVaccName2;
	}

	public void setPetVaccName2(String petVaccName2) {
		this.petVaccName2 = petVaccName2;
	}

	public Date getPetVaccTime2() {
		return petVaccTime2;
	}

	public void setPetVaccTime2(Date petVaccTime2) {
		this.petVaccTime2 = petVaccTime2;
	}

	public boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}
		
	@Override
	public String toString() {
		return "BuyerVO [memberId=" + memberId + ", memberEmail=" + memberEmail + ", thirdFrom=" + thirdFrom
				+ ", memberName=" + memberName + ", memberPhone=" + memberPhone + ", memberMobile=" + memberMobile
				+ ", memberBirthday=" + memberBirthday + ", memberPassword=" + memberPassword + ", memberAddress="
				+ memberAddress + ", isMemberEmail=" + isMemberEmail + ", memberRegistrationTime="
				+ memberRegistrationTime + ", petName=" + petName + ", petImg=" + Arrays.toString(petImg)
				+ ", petImgUploadTime=" + petImgUploadTime + ", petVaccName1=" + petVaccName1 + ", petVaccTime1="
				+ petVaccTime1 + ", petVaccName2=" + petVaccName2 + ", petVaccTime2=" + petVaccTime2 + ", isConfirm="
				+ isConfirm + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

//memberId int AI PK 
//memberEmail varchar(200) 
//thirdFrom varchar(100) 
//memberName varchar(100) 
//memberPhone varchar(10) 
//memberMobile varchar(20) 
//memberBirthday date 
//memberPassword varchar(100) 
//memberAddress varchar(100) 
//isMemberEmail tinyint(1) 
//memberRegistrationTime datetime 
//petName varchar(100) 
//petImg longblob 
//petImgUploadTime datetime 
//petVaccName1 varchar(100) 
//petVaccTime1 datetime 
//petVaccName2 varchar(100) 
//petVaccTime2 datetime 
//isConfirm