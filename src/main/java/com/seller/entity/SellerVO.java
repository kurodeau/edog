package com.seller.entity;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ad.model.AdVO;
import com.sellerLv.entity.SellerLvVO;


@Entity
@Table(name="seller")
public class SellerVO implements java.io.Serializable{
	public SellerVO() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sellerId", updatable = false)
	  private Integer sellerId;
	
	@OneToMany(mappedBy = "sellerVO", cascade = CascadeType.ALL) 
	private Set<AdVO> adVO;
	
	
	
	@ManyToOne
	@JoinColumn(name = "sellerLvId", referencedColumnName = "sellerLvId")
	private SellerLvVO sellerLvId;
	
	public SellerLvVO getSellerLvId() {
		return sellerLvId;
	}
	public void setSellerLvId(SellerLvVO sellerLvId) {
		this.sellerLvId = sellerLvId;
	}

	@Column(name = "sellerEmail")
    private String sellerEmail;

    public Set<AdVO> getAdVO() {
		return adVO;
	}
	public void setAdVO(Set<AdVO> adVO) {
		this.adVO = adVO;
	}

	@Column(name = "sellerCompany")
    private String sellerCompany;

    @Column(name = "sellerTaxId",  columnDefinition = "char(8)")
    private String sellerTaxId;

    @Column(name = "sellerCapital")
    private Integer sellerCapital;

    @Column(name = "sellerContact")
    private String sellerContact;

    @Column(name = "sellerCompanyPhone")
    private String sellerCompanyPhone;

    @Column(name = "sellerCompanyExtension")
    private String sellerCompanyExtension;

    @Column(name = "sellerMobile")
    private String sellerMobile;

    @Column(name = "sellerAddress")
    private String sellerAddress;

    @Column(name = "sellerPassword")
    private String sellerPassword;

    @Column(name = "sellerBankAccount")
    private String sellerBankAccount;

    @Column(name = "sellerBankCode")
    private String sellerBankCode;

    @Column(name = "sellerBankAccountNumber")
    private String sellerBankAccountNumber;

    @Column(name = "sellerCreateTime")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date sellerCreateTime= new Date();

    @Column(name = "isConfirm")
    private Boolean isConfirm =false;
    
	    
	    public Integer getSellerId() {
			return sellerId;
		}
		public void setSellerId(Integer sellerId) {
			this.sellerId = sellerId;
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
	
}
