package com.newsTicker.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newsTicker.service.NewsTickerService;
import com.newsTicker.entity.*;


import util.Util;

@WebServlet("/newsTicker/newsTicker.do")
public class NewsTickerServlet extends HttpServlet {

	private List<String> errorMsgs = null;
	private NewsTickerService newsTickerSvc;

	@Override
	public void init() throws ServletException {
		newsTickerSvc = new NewsTickerService();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		errorMsgs = new LinkedList<String>();

		req.setCharacterEncoding("UTF-8");
		req.setAttribute("errorMsgs", errorMsgs);

		String action = req.getParameter("action");
		String forwardPath = "";

		switch (action) {

		case "getAll":
			forwardPath = getAll(req, res);
			break;
			
	    // 這應該是不需要複合查詢
//		case "compositeQuery":
//			forwardPath = getCompositeSellersQuery(req, res);
//			break;

		case "update":
			forwardPath = update(req, res);
			break;

		case "getOne_For_Update":
			forwardPath = getOne_For_Update(req, res);
			break;

		case "insert":
			forwardPath = insert(req, res);
			break;

		case "getOne_For_Display":
			forwardPath = getOne_For_Display(req, res);
			break;

		default:
			forwardPath = "/index.jsp";
		}
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	// ***************************
	// Method Area
	// ***************************
	private String getOne_For_Display(HttpServletRequest req, HttpServletResponse res) {
		Integer newsTickerId =Integer.parseInt(req.getParameter("newsTickerId")) ;
		NewsTickerVO newsTickerVO= newsTickerSvc.getOneNewsTicker(newsTickerId);
		req.setAttribute("newsTickerVO", newsTickerVO);

		return "/seller/listOneNewsTicker.jsp";
		
	}

//	private String getCompositeNewsTickersQuery(HttpServletRequest req, HttpServletResponse res) {
//		Map<String, String[]> map = req.getParameterMap();
//
//		System.out.println("=====================");
//		for(String key : map.keySet()) {
//			for(String value: map.get(key)) {
//				System.out.println("key" + key +" value" +value);
//			}
//		}
//
//		System.out.println("=====================");
//
//		if (map != null) {
//			List<NewsTickerVO> list = newsTickerSvc.getNewsTickersByCompositeQuery(map);
//			req.setAttribute("list", list);
//		} else {
//			return "/index.jsp";
//		}
//		return "/newsTicker/listCompositeQueryNewsTickers.jsp";
//	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<NewsTickerVO> list = newsTickerSvc.getAll();

		if (req.getSession().getAttribute("newsTickerPageQty") == null) {
			int newsTickerPageQty = newsTickerSvc.getTotal();
			req.getSession().setAttribute("newsTickerPageQty", newsTickerPageQty);
		}

		req.setAttribute("list", list);
		req.setAttribute("currentPage", currentPage);

		return "/newsTicker/listAllNewsTickerPageQty.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer newsTickerId = Integer.valueOf(req.getParameter("newsTickerId"));
		NewsTickerVO newsTickerVO = newsTickerSvc.getOneNewsTicker(newsTickerId);

		req.setAttribute("newsTickerVO", newsTickerVO);
		return "/newsTicker/update_newsTicker_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		/*************************** 1.接收請求參數 ****************************************/
		Integer newsTickerId = Integer.valueOf(req.getParameter("newsTickerId"));

		String newsTickerContent = req.getParameter("newsTickerContent");
		
		Integer sort = Integer.valueOf(req.getParameter("sort"));
		
		// 以下內容為預設值，且只能由Admin操控
		java.util.Date startTime = null;
		try {
			String dateformatStr = req.getParameter("startTime");
			System.out.println("dateformatStr" + dateformatStr);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			startTime = dateFormat.parse(dateformatStr);
		} catch (ParseException e) {
			startTime = new java.util.Date(System.currentTimeMillis());
			errorMsgs.add("請選擇開始日期");
		}
		
		java.util.Date endTime = null;
		try {
			String dateformatStr = req.getParameter("endTime");
			System.out.println("dateformatStr" + dateformatStr);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			endTime = dateFormat.parse(dateformatStr);
		} catch (ParseException e) {
			endTime = new java.util.Date(System.currentTimeMillis());
			errorMsgs.add("請選擇結束日期");
		}
		
		Boolean isDisplay = null;
		try {
			isDisplay = req.getParameter("isDisplay") == null ? false : true;
		} catch (IllegalArgumentException e) {
			System.out.println("走馬燈顯示設定有錯");
		}

		//
		NewsTickerVO newsTickerVO = new NewsTickerVO();
		newsTickerVO.setNewsTickerContent(newsTickerContent);
		newsTickerVO.setSort(sort);
		newsTickerVO.setStartTime(startTime);
		newsTickerVO.setEndTime(endTime);
		newsTickerVO.setIsDisplay(isDisplay);

		req.setAttribute("newsTickerVO", newsTickerVO);

		if (!errorMsgs.isEmpty()) {
			return ("/newsTicker/update_newsTicker_input.jsp");
		}

		// Update 使用值做更新(因為Service內部轉換)
		newsTickerVO = newsTickerSvc.updateNewsTicker(
				newsTickerId,
				newsTickerContent, 
				sort, 
				startTime, 
				endTime, 
				isDisplay
				);

		return "/newsTicker/listOneNewsTicker.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		String newsTickerContent = req.getParameter("newsTickerContent");
		
		Integer sort = Integer.valueOf(req.getParameter("sort"));
		
		// 以下內容為預設值，且只能由Admin操控
		java.util.Date startTime = null;
		try {
			String dateformatStr = req.getParameter("startTime");
			System.out.println("dateformatStr" + dateformatStr);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			startTime = dateFormat.parse(dateformatStr);
		} catch (ParseException e) {
			startTime = new java.util.Date(System.currentTimeMillis());
			errorMsgs.add("請選擇開始日期");
		}
		
		java.util.Date endTime = null;
		try {
			String dateformatStr = req.getParameter("endTime");
			System.out.println("dateformatStr" + dateformatStr);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			endTime = dateFormat.parse(dateformatStr);
		} catch (ParseException e) {
			endTime = new java.util.Date(System.currentTimeMillis());
			errorMsgs.add("請選擇結束日期");
		}
		
		Boolean isDisplay = null;
		try {
			isDisplay = req.getParameter("isDisplay") == null ? false : true;
		} catch (IllegalArgumentException e) {
			System.out.println("走馬燈顯示設定有錯");
		}

		//
		NewsTickerVO newsTickerVO = new NewsTickerVO();
		newsTickerVO.setNewsTickerContent(newsTickerContent);
		newsTickerVO.setSort(sort);
		newsTickerVO.setStartTime(startTime);
		newsTickerVO.setEndTime(endTime);
		newsTickerVO.setIsDisplay(isDisplay);
		// sellerCreateTime is automatically set to the current date in the database,
		// isConfirm is automatically set to the current date in the database

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("newsTickerVO", newsTickerVO); // 含有輸入格式錯誤的empVO物件,也存入req
			return ("/newsTicker/addNewsTicker.jsp");
		}

		newsTickerVO = newsTickerSvc.addNewsTicker(
				newsTickerContent, 
				sort, 
				startTime, 
				endTime, 
				isDisplay
				);

		
		req.setAttribute("newsTickerVO", newsTickerVO);

		return "/newsTicker/listOneNewsTicker.jsp";
	}

}
