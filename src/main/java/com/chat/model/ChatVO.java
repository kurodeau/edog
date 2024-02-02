package com.chat.model;
import java.sql.Date;

public class ChatVO implements java.io.Serializable{
	private Integer chatId;
	private Integer chatRoomId;
	private Integer sendPart;
	private String message;
	private byte[] image;
	private Date createTime;
	
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	public Integer getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
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
