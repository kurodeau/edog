package com.remittance.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class RemittanceVO implements Serializable{
    private Integer remittanceId;
    private Integer sellerId;
    private Timestamp remittanceEstimatedTime;
    private Timestamp remittanceTime;
    private Timestamp settlementTime;
    private Integer turnover;
    private Integer handlingFee;
    private Integer remittanceAmount;
    private Integer remittanceStatus;
    private String explanation;
    
    public RemittanceVO() {
    	
    }
    
    
	public RemittanceVO(Integer remittanceId, Integer sellerId, Timestamp remittanceEstimatedTime,
			Timestamp remittanceTime, Timestamp settlementTime, Integer turnover, Integer handlingFee,
			Integer remittanceAmount, Integer remittanceStatus, String explanation) {
		super();
		this.remittanceId = remittanceId;
		this.sellerId = sellerId;
		this.remittanceEstimatedTime = remittanceEstimatedTime;
		this.remittanceTime = remittanceTime;
		this.settlementTime = settlementTime;
		this.turnover = turnover;
		this.handlingFee = handlingFee;
		this.remittanceAmount = remittanceAmount;
		this.remittanceStatus = remittanceStatus;
		this.explanation = explanation;
	}


	public Integer getRemittanceId() {
		return remittanceId;
	}
	public void setRemittanceId(Integer remittanceId) {
		this.remittanceId = remittanceId;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Timestamp getRemittanceEstimatedTime() {
		return remittanceEstimatedTime;
	}
	public void setRemittanceEstimatedTime(Timestamp remittanceEstimatedTime) {
		this.remittanceEstimatedTime = remittanceEstimatedTime;
	}
	public Timestamp getRemittanceTime() {
		return remittanceTime;
	}
	public void setRemittanceTime(Timestamp remittanceTime) {
		this.remittanceTime = remittanceTime;
	}
	public Timestamp getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(Timestamp settlementTime) {
		this.settlementTime = settlementTime;
	}
	public Integer getTurnover() {
		return turnover;
	}
	public void setTurnover(Integer turnover) {
		this.turnover = turnover;
	}
	public Integer getHandlingFee() {
		return handlingFee;
	}
	public void setHandlingFee(Integer handlingFee) {
		this.handlingFee = handlingFee;
	}
	public Integer getRemittanceAmount() {
		return remittanceAmount;
	}
	public void setRemittanceAmount(Integer remittanceAmount) {
		this.remittanceAmount = remittanceAmount;
	}
	public Integer getRemittanceStatus() {
		return remittanceStatus;
	}
	public void setRemittanceStatus(Integer remittanceStatus) {
		this.remittanceStatus = remittanceStatus;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	
	
	
	
	

}
