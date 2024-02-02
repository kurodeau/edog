package com.sellchat.model;
import java.sql.Date;

public class SellChatVO implements java.io.Serializable{
	private Integer sellChatId;
	private Integer sellChatRoomId;
	private Integer sendPart;
	private String message;
	private byte[] image;
	private Date createTime;
	
	public Integer getSellChatId() {
		return sellChatId;
	}
	public void setSellChatId(Integer sellChatId) {
		this.sellChatId = sellChatId;
	}
	public Integer getSellChatRoomId() {
		return sellChatRoomId;
	}
	public void setSellChatRoomId(Integer sellChatRoomId) {
		this.sellChatRoomId = sellChatRoomId;
	}
	public Integer getSendPart() {
		return sendPart;
	}
	public void setSendPart(Integer sendPart) {
		this.sendPart = sendPart;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
		
}
