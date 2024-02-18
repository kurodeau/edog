package com.sellchat.model;

import java.sql.Date;
import java.util.*;

public interface SellChatDAO_interface {
          public void insert(SellChatVO sellChatVO);
          public void update(SellChatVO sellChatVO);
          public void delete(Integer sellChatRoomId);
          public SellChatVO findByPrimaryKey(Integer sellChatRoomId);
          public List<SellChatVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<SellChatChatVO> getAll(Map<String, String[]> map); 
}

//private Integer sellChatId;
//private Integer sellChatRoomId;
//private Integer sendPart;
//private String message;
//private byte[] image;
//private Date createTime;