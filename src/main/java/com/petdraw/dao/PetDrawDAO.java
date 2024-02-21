package com.petdraw.dao;

import java.util.List;
import java.util.Map;

import com.petdraw.entity.PetDrawVO;

public interface PetDrawDAO {
          public Integer insert(PetDrawVO petDraw);
          public Integer update(PetDrawVO petDraw);
          public Integer delete(Integer petDrawId);
          public PetDrawVO findByPrimaryKey(Integer petDrawId);
          public List<PetDrawVO> getAll();
          public Integer getTotal();
          public List<PetDrawVO> getByCompositeQuery(Map<String,String> map); 
          
          //FOR UK
          boolean isPetDrawCodeUnique(String petDrawCode,Integer petDrawMyId) ;

}


