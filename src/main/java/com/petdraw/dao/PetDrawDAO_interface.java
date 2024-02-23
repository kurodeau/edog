package com.petdraw.dao;


import java.util.List;

import com.petdraw.entity.PetDrawVO;

public interface PetDrawDAO_interface {
          public void insert(PetDrawVO PetDrawVO);
          public void update(PetDrawVO PetDrawVO);
          public void delete(Integer PetDrawno);
          public PetDrawVO findByPrimaryKey(Integer CouponId);
          public List<PetDrawVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<PetDrawVO> getAll(Map<String, String[]> map); 
}
