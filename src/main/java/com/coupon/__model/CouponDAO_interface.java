package com.coupon.model;

import java.util.*;

public interface CouponDAO_interface {
          public void insert(CouponVO CouponVO);
          public void update(CouponVO CouponVO);
          public void delete(Integer Couponno);
          public CouponVO findByPrimaryKey(Integer CouponId);
          public List<CouponVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<CouponVO> getAll(Map<String, String[]> map); 
}
