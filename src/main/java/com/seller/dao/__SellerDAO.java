package com.seller.dao;


import java.util.List;

import com.seller.entity.*;


public interface __SellerDAO {
          public Integer insert(__SellerVO sellerVO);
          public Integer update(__SellerVO sellerVO);
          public Integer delete(Integer sellerId);
          public __SellerVO findByPrimaryKey(Integer sellerId);
          public List<__SellerVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 

}




