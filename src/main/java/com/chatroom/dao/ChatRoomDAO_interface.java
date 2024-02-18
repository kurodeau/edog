package com.chat.model;

import java.sql.Date;
import java.util.*;

public interface ChatRoomDAO_interface {
          public void insert(ChatRoomVO ChatRoomVO);
          public void update(ChatRoomVO ChatRoomVO);
          public void delete(Integer chatRoomId);
          public ChatRoomVO findByPrimaryKey(Integer chatRoomId);
          public List<ChatRoomVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<ChatRoomVO> getAll(Map<String, String[]> map); 
}

