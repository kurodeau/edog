package com.ad.model;

import java.util.*;



public interface AdDAO_interface {
	
	  public Integer insert(AdVO adVO);
      public Integer update(AdVO adVO);
      public Integer delete(Integer adId);
      public AdVO findByPrimaryKey(Integer adId);
      public List<AdVO> getAll();
	

}
