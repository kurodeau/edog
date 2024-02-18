package com.chatroom.dao;

import java.sql.Date;
import java.util.*;

import com.chatroom.entity.ChatRoomVO;

public interface ChatRoomDAO_interface {
          public void insert(ChatRoomVO ChatRoomVO);
          public void update(ChatRoomVO ChatRoomVO);
          public void delete(Integer chatRoomId);
          public ChatRoomVO findByPrimaryKey(Integer chatRoomId);
          public List<ChatRoomVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<ChatRoomVO> getAll(Map<String, String[]> map); 
}

