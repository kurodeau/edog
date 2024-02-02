package com.chat.model;

import java.sql.Date;
import java.util.*;

public interface ChatDAO_interface {
          public void insert(ChatVO chatVO);
          public void update(ChatVO chatVO);
          public void delete(Integer chatId);
          public ChatVO findByPrimaryKey(Integer chatId);
          public List<ChatVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<ChatVO> getAll(Map<String, String[]> map); 
}

//private Integer chatId;
//private Integer chatRoomId;
//private Integer sendPart;
//private String message;
//private byte[] image;
//private Date createTime;