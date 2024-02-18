package com.sellchatroom.entity;
import java.sql.Date;

public class SellChatRoomVO implements java.io.Serializable{
	private Integer sellChatRoomId;
	private Integer memberId;
	private Integer sellerId;
	private Date createTime;
	
	public Integer getSellChatRoomId() {
		return sellChatRoomId;
	}
	public void setSellChatRoomId(Integer sellChatRoomId) {
		this.sellChatRoomId = sellChatRoomId;
	}
	public Integer getUserId() {
		return memberId;
	}
	public void setUserId(Integer userId) {
		this.memberId = userId;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
