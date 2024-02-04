package com.seller.dao;


import java.util.List;

import com.seller.entity.SellerVO;


public interface SellerDAO {
          public void insert(SellerVO sellerVO);
          public void update(SellerVO sellerVO);
          public void delete(Integer sellerId);
          public SellerVO findByPrimaryKey(Integer sellerId);
          public List<SellerVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 

}