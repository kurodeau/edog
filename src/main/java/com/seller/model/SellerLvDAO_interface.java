package com.seller.model;

import java.util.*;

public interface SellerLvDAO_interface {
          public void insert(SellerVO sellerVO);
          public void update(SellerVO sellerVO);
          public void delete(Integer sellerId);
          public SellerVO findByPrimaryKey(Integer sellerId);
          public List<SellerVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
