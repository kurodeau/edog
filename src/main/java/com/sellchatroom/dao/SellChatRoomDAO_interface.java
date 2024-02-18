package com.sellchat.model;

import java.sql.Date;
import java.util.*;

public interface SellChatRoomDAO_interface {
          public void insert(SellChatRoomVO sellChatRoomVO);
          public void update(SellChatRoomVO sellChatRoomVO);
          public void delete(Integer sellChatRoomId);
          public SellChatRoomVO findByPrimaryKey(Integer sellChatRoomId);
          public List<SellChatRoomVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<SellChatRoomVO> getAll(Map<String, String[]> map); 
}

//private Integer sellChatRoomId;
//private Integer memberId;
//private Integer sellerId;
//private Date createTime;