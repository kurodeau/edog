package com.sellchat.dao;

import java.util.List;

import com.sellchat.entity.*;

public interface SellChatDAO_interface {
          public void insert(SellChatVO sellChatVO);
          public void update(SellChatVO sellChatVO);
          public void delete(Integer sellChatRoomId);
          public SellChatVO findByPrimaryKey(Integer sellChatRoomId);
          public List<SellChatVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<SellChatChatVO> getAll(Map<String, String[]> map); 
}

//private Integer sellChatId;
//private Integer sellChatRoomId;
//private Integer sendPart;
//private String message;
//private byte[] image;
//private Date createTime;