package com.chat.model;
import java.sql.Date;

public class ChatRoomVO implements java.io.Serializable{
	private Integer chatRoomId;
	private Integer member1Id;
	private Integer member2Id;
	private Date createTime;
	
	public Integer getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public Integer getUser1Id() {
		return member1Id;
	}
	public void setUser1Id(Integer user1Id) {
		this.member1Id = user1Id;
	}
	public Integer getUser2Id() {
		return member2Id;
	}
	public void setUser2Id(Integer user2Id) {
		this.member2Id = user2Id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
