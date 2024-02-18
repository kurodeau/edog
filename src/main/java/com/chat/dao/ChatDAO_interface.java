package com.chat.dao;

import java.sql.Date;
import java.util.*;

import com.chat.entity.ChatVO;

public interface ChatDAO_interface {
          public void insert(ChatVO chatVO);
          public void update(ChatVO chatVO);
          public void delete(Integer chatId);
          public ChatVO findByPrimaryKey(Integer chatId);
          public List<ChatVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<ChatVO> getAll(Map<String, String[]> map); 
}

//private Integer chatId;
//private Integer chatRoomId;
//private Integer sendPart;
//private String message;
//private byte[] image;
//private Date createTime;