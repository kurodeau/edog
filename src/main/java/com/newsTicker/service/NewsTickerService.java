package com.newsTicker.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.newsTicker.dao.*;
import com.newsTicker.entity.*;

public class NewsTickerService {

	private NewsTickerDAO dao;

	public NewsTickerService() {
		dao = new NewsTickerHBDAO();
	}

	public NewsTickerVO addNewsTicker(
//			Integer newsTickerId, //自增ID不可手動新增
			String newsTickerContent, 
			Integer sort, 
			Date startTime, 
			Date endTime, 
			Boolean isDisplay
			) {

		NewsTickerVO newsTickerVO = new NewsTickerVO();

//		newsTickerVO.setNewsTickerId(newsTickerId); //自增ID不可手動新增
	    newsTickerVO.setNewsTickerContent(newsTickerContent);
	    newsTickerVO.setSort(sort);
	    newsTickerVO.setStartTime(startTime);
	    newsTickerVO.setEndTime(endTime);
	    newsTickerVO.setIsDisplay(isDisplay);
	    
	    // Hibernate要指定預設值
	    newsTickerVO.setIsDisplay(true);

	    dao.insert(newsTickerVO);

	    return newsTickerVO;
	}


	public NewsTickerVO updateNewsTicker(
			Integer newsTickerId,
			String newsTickerContent, 
			Integer sort, 
			Date startTime, 
			Date endTime, 
			Boolean isDisplay
			) {

	    NewsTickerVO newsTickerVO = new NewsTickerVO();
	
	    newsTickerVO.setNewsTickerId(newsTickerId);
	    newsTickerVO.setNewsTickerContent(newsTickerContent);
	    newsTickerVO.setSort(sort);
	    newsTickerVO.setStartTime(startTime);
	    newsTickerVO.setEndTime(endTime);
	    newsTickerVO.setIsDisplay(isDisplay);

	    dao.update(newsTickerVO);

	    return newsTickerVO;
	}


	public void deleteNewsTicker(Integer newsTickerId) {
		dao.delete(newsTickerId);
	}

	public NewsTickerVO getOneNewsTicker(Integer newsTickerId) {
		return dao.findByPrimaryKey(newsTickerId);
	}

	public List<NewsTickerVO> getAll() {
		return dao.getAll();
	}
	
	public int getTotal() {
		return dao.getTotal();
	}
	
	//走馬燈應該用不到複合查詢?
//	public List<NewsTickerVO> getSellersByCompositeQuery(Map<String, String[]> map) {
//		Map<String, String> query = new HashMap<>();
//			
//		
//		// Map.Entry即代表一組key-value
//		Set<Map.Entry<String, String[]>> entry = map.entrySet();
//		
//		for (Map.Entry<String, String[]> row : entry) {
//			String key = row.getKey();
//			// 因為請求參數裡包含了action，做個去除動作
//			if ("action".equals(key)) {
//				continue;
//			}
//			// 若是value為空即代表沒有查詢條件，做個去除動作
//			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
//			if (value == null || value.isEmpty()) {
//				continue;
//			}
//			query.put(key, value);
//		}
//		
//		
//		return dao.getByCompositeQuery(query);
//	}

}


