package com.sellchatroom.dao;

import java.util.List;

import com.sellchatroom.entity.SellChatRoomVO;

public interface SellChatRoomDAO_interface {
          public void insert(SellChatRoomVO sellChatRoomVO);
          public void update(SellChatRoomVO sellChatRoomVO);
          public void delete(Integer sellChatRoomId);
          public SellChatRoomVO findByPrimaryKey(Integer sellChatRoomId);
          public List<SellChatRoomVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<SellChatRoomVO> getAll(Map<String, String[]> map); 
}

//private Integer sellChatRoomId;
//private Integer memberId;
//private Integer sellerId;
//private Date createTime;