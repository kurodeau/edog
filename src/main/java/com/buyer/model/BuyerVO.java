package com.buyer.model;
import java.sql.Date;

public class BuyerVO implements java.io.Serializable{
	private Integer memberId;
	private String memberEmail;
	private String thirdFrom;
	private String memberName;
	private String memberPhone;
	private String mobile;
	private Date memberBirthday;
	private String memberPassword;
	private String memberAddress;
	private Boolean isMemberEmail;
	private Date memberRegistrationTime;
	private String petName;
	private String petVaccName1;
	private Date petVaccTime1;
	private String petVaccName2;
	private Date petVaccTime2;
	private byte[] petImg;
	private Date petImgUploadTime;
	
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
	

}
