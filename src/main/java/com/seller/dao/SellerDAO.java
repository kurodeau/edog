package com.seller.dao;


import java.util.List;
import java.util.Map;

import com.seller.entity.*;


public interface SellerDAO {
          public Integer insert(SellerVO seller);
          public Integer update(SellerVO seller);
          public Integer delete(Integer sellerId);
          public SellerVO findByPrimaryKey(Integer sellerId);
          public List<SellerVO> getAll();
          public Integer getTotal();
          public List<SellerVO> getByCompositeQuery(Map<String,String> map);

          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}




