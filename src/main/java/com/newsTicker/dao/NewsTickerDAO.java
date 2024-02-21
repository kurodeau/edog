package com.newsTicker.dao;


import java.util.List;
import java.util.Map;

import com.newsTicker.entity.*;


public interface NewsTickerDAO {
          public Integer insert(NewsTickerVO newsTicker);
          public Integer update(NewsTickerVO newsTicker);
          public Integer delete(Integer newsTickerId);
          public NewsTickerVO findByPrimaryKey(Integer newsTickerId);
          public List<NewsTickerVO> getAll();
          public Integer getTotal();
//        public List<NewsTickerVO> getByCompositeQuery(Map<String,String> map);

          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<NewsTickerVO> getAll(Map<String, String[]> map); 
}




