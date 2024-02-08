package com.coupon.dao;


import java.util.List;
import java.util.Map;

import com.coupon.entity.CouponVO;


public interface CouponDAO {
          public Integer insert(CouponVO coupon);
          public Integer update(CouponVO coupon);
          public Integer delete(Integer couponId);
          public CouponVO findByPrimaryKey(Integer couponId);
          public List<CouponVO> getAll();
          public Integer getTotal();
          public List<CouponVO> getByCompositeQuery(Map<String,String> map); 
          
          //FOR UK
          boolean isCouponCodeUnique(String couponCode,Integer couponMyId) ;

}




