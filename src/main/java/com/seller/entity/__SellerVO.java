package com.seller.entity;
import java.sql.Date;

public class __SellerVO implements java.io.Serializable{
	public __SellerVO() {
		super();
	}
	
	  private Integer sellerId;
	    private Integer sellerLvId;
	    private String sellerEmail;
	    private String sellerCompany;
	    private String sellerTaxId;
	    private Integer sellerCapital;
	    private String sellerContact;
	    private String sellerCompanyPhone;
	    private String sellerCompanyExtension;
	    private String sellerMobile;
	    private String sellerAddress;
	    private String sellerPassword;
	    private String sellerBankAccount;
		private String sellerBankCode;
	    private String sellerBankAccountNumber;
	    private Date sellerCreateTime;
	    private Boolean  isConfirm;
	    
	    
	    public Integer getSellerId() {
			return sellerId;
		}
		public void setSellerId(Integer sellerId) {
			this.sellerId = sellerId;
		}
		public Integer getSellerLvId() {
			return sellerLvId;
		}
		@Override
		public String toString() {
			return "SellerVO [sellerId=" + sellerId + ", sellerLvId=" + sellerLvId + ", sellerEmail=" + sellerEmail
					+ ", sellerCompany=" + sellerCompany + ", sellerTaxId=" + sellerTaxId + ", sellerCapital="
					+ sellerCapital + ", sellerContact=" + sellerContact + ", sellerCompanyPhone=" + sellerCompanyPhone
					+ ", sellerCompanyExtension=" + sellerCompanyExtension + ", sellerMobile=" + sellerMobile
					+ ", sellerAddress=" + sellerAddress + ", sellerPassword=" + sellerPassword + ", sellerBankAccount="
					+ sellerBankAccount + ", sellerBankCode=" + sellerBankCode + ", sellerBankAccountNumber="
					+ sellerBankAccountNumber + ", sellerCreateTime=" + sellerCreateTime + ", isConfirm=" + isConfirm
					+ "]";
		}
		public void setSellerLvId(Integer sellerLvId) {
			this.sellerLvId = sellerLvId;
		}
		public String getSellerEmail() {
			return sellerEmail;
		}
		public void setSellerEmail(String sellerEmail) {
			this.sellerEmail = sellerEmail;
		}
		public String getSellerCompany() {
			return sellerCompany;
		}
		public void setSellerCompany(String sellerCompany) {
			this.sellerCompany = sellerCompany;
		}
		public String getSellerTaxId() {
			return sellerTaxId;
		}
		public void setSellerTaxId(String sellerTaxId) {
			this.sellerTaxId = sellerTaxId;
		}
		public Integer getSellerCapital() {
			return sellerCapital;
		}
		public void setSellerCapital(Integer sellerCapital) {
			this.sellerCapital = sellerCapital;
		}
		public String getSellerContact() {
			return sellerContact;
		}
		public void setSellerContact(String sellerContact) {
			this.sellerContact = sellerContact;
		}
		public String getSellerCompanyPhone() {
			return sellerCompanyPhone;
		}
		public void setSellerCompanyPhone(String sellerCompanyPhone) {
			this.sellerCompanyPhone = sellerCompanyPhone;
		}
		public String getSellerCompanyExtension() {
			return sellerCompanyExtension;
		}
		public void setSellerCompanyExtension(String sellerCompanyExtension) {
			this.sellerCompanyExtension = sellerCompanyExtension;
		}
		public String getSellerMobile() {
			return sellerMobile;
		}
		public void setSellerMobile(String sellerMobile) {
			this.sellerMobile = sellerMobile;
		}
		public String getSellerAddress() {
			return sellerAddress;
		}
		public void setSellerAddress(String sellerAddress) {
			this.sellerAddress = sellerAddress;
		}
		public String getSellerPassword() {
			return sellerPassword;
		}
		public void setSellerPassword(String sellerPassword) {
			this.sellerPassword = sellerPassword;
		}
		public String getSellerBankAccount() {
			return sellerBankAccount;
		}
		public void setSellerBankAccount(String sellerBankAccount) {
			this.sellerBankAccount = sellerBankAccount;
		}
		public String getSellerBankCode() {
			return sellerBankCode;
		}
		public void setSellerBankCode(String sellerBankCode) {
			this.sellerBankCode = sellerBankCode;
		}
		public String getSellerBankAccountNumber() {
			return sellerBankAccountNumber;
		}
		public void setSellerBankAccountNumber(String sellerBankAccountNumber) {
			this.sellerBankAccountNumber = sellerBankAccountNumber;
		}
		public Date getSellerCreateTime() {
			return sellerCreateTime;
		}
		public void setSellerCreateTime(Date sellerCreateTime) {
			this.sellerCreateTime = sellerCreateTime;
		}
		public Boolean getIsConfirm() {
			return isConfirm;
		}
		public void setIsConfirm(Boolean isConfirm) {
			this.isConfirm = isConfirm;
		}

	
}
