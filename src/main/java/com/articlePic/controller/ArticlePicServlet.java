package com.articlePic.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.articlePic.model.ArticlePicService;
import com.articlePic.model.ArticlePicVO;


@WebServlet("/articlePic/articlePic.do")
@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)
public class ArticlePicServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("get_All".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 2.
			 *****************************************/
			ArticlePicService articlePicSvc = new ArticlePicService();
			List<ArticlePicVO> list = articlePicSvc.getAll();
			req.setAttribute("list", list);
			
			/***************************
			 * 2.
			 *****************************************/
			
			// Method 1
			// 一次取得大量圖片，回傳Base64
			Map<Integer, List<String>> articlePicMap = new HashMap<>();
			
			System.out.println( getServletContext().getResourceAsStream("/nodata/none03.jpg"));
			InputStream in = getServletContext().getResourceAsStream("/nodata/none03.jpg");			
			String noImageBase64 = Base64.getEncoder().encodeToString(in.readAllBytes());
			
			for (ArticlePicVO item:list) {
				Integer articlePicId = item.getArticlePicId();
				List<String> picList = new ArrayList<String>();
				if(!articlePicMap.containsKey(articlePicId)) {
					articlePicMap.put(articlePicId,new ArrayList<String>());
				}
				
				if(item.getArticlePicBlob()!=null) {
					picList = articlePicMap.get(articlePicId);
					String articlePicBase64 = Base64.getEncoder().encodeToString(item.getArticlePicBlob());
					System.out.println(articlePicBase64.substring(0, 10));
					picList.add(articlePicBase64);
					articlePicMap.put(articlePicId,picList);
				}
				
			}
			
			// Check for keys with an ArrayList size of 0
			articlePicMap.forEach((key, value) -> {
			    if (value.size() == 0) {
			    	value.add(noImageBase64);
			    	articlePicMap.put(key,value);
			    }
			});
			

			
			// Method 2
			// 一次取得大量圖片，回傳Base64
//			Map<Integer, List<String>> articlePicMap = new HashMap<>();
//			for (ArticlePicVO item:list) {
//			Integer articlePicId = item.getArticlePicId();
//			List<String> picList = new ArrayList<String>();
//			System.out.println(articlePicId);
//			if(!articlePicMap.containsKey(articlePicId)) {
//				articlePicMap.put(articlePicId,new ArrayList<String>());
//			}
//			
//			if(item.getArticlePicBlob()!=null) {
//				picList = articlePicMap.get(articlePicId);
//				String articlePicBase64 = Base64.getEncoder().encodeToString(item.getArticlePicBlob());
//				picList.add(articlePicBase64);
//				articlePicMap.put(articlePicId,picList);
//			}
//			
//		}
			
			
			/***************************
			 * 3.
			 *************/
			req.setAttribute("list", list); 
			
			
			// Method 1
			// 一次取得大量圖片，回傳Base64
			 req.setAttribute("articlePicMap", articlePicMap); 
			
			String url = "/articlePic/listAllArticlePic2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 
			successView.forward(req, res);

		}

		if ("getOne_For_Display".equals(action)) { // 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.
			 **********************/
			String str = req.getParameter("articlePicId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("嚙請選蕭撜對蕭s嚙踝蕭");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/articlePic/select_page.jsp");
				failureView.forward(req, res);
				return;// 嚙緹嚙踝蕭嚙踝蕭嚙稻
			}

			Integer articlePicId = null;
			try {
				articlePicId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("嚙賢章嚙編嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/articlePic/select_page.jsp");
				failureView.forward(req, res);
				return;// 嚙緹嚙踝蕭嚙踝蕭嚙稻
			}

			/***************************
			 * 2.嚙罷嚙締嚙範嚙賠賂蕭嚙�
			 *****************************************/
			ArticlePicService articlePicSvc = new ArticlePicService();
			ArticlePicVO articlePicVO = articlePicSvc.getOneArticlePic(articlePicId);
			if (articlePicVO == null) {
				errorMsgs.add("嚙範嚙盤嚙踝蕭嚙�");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/articlePic/select_page.jsp");
				failureView.forward(req, res);
				return;// 嚙緹嚙踝蕭嚙踝蕭嚙稻
			}
			/***************************
			 * 嚙緩嚙畿嚙緲嚙踝蕭嚙�
			 *****************************************/
			String articlePicBase64 = Base64.getEncoder().encodeToString(articlePicVO.getArticlePicBlob());
			req.setAttribute("articlePic64str", articlePicBase64);
			/***************************
			 * 3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)
			 *************/
			req.setAttribute("articlePicVO", articlePicVO); // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭articlePicVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
			String url = "/articlePic/listOneArticlePic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙� listOneArticlePic.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 嚙諉佗蕭listAllArticlePic.jsp嚙踝蕭嚙請求
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.
			 ****************************************/
			Integer articlePicId = Integer.valueOf(req.getParameter("articlePicId"));

			/***************************
			 * 2.
			 ****************************************/
			ArticlePicService articlePicSvc = new ArticlePicService();
			ArticlePicVO articlePicVO = articlePicSvc.getOneArticlePic(articlePicId);
			/***************************
			 * 3.
			 ************/
			req.setAttribute("articlePicVO", articlePicVO); 
			System.out.println("articlePicVO " +articlePicVO);

			String url = "/articlePic/update_articlePic_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.
			 **********************/
			Integer articlePicId = Integer.valueOf(req.getParameter("articlePicId").trim());

			Integer articleId = Integer.valueOf(req.getParameter("articleId").trim());

			byte[] articlePicBlob = null;

			java.sql.Timestamp articlePicTime = null;
			try {
				articlePicTime = java.sql.Timestamp.valueOf(req.getParameter("articlePicTime").trim());
			} catch (IllegalArgumentException e) {
				articlePicTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("嚙請選蕭J嚙踝蕭嚙�!");
			}

			ArticlePicVO articlePicVO = new ArticlePicVO();
			articlePicVO.setArticlePicId(articlePicId);
			articlePicVO.setArticleId(articleId);
			articlePicVO.setArticlePicBlob(articlePicBlob);
			articlePicVO.setArticlePicTime(articlePicTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ArticlePicVO", articlePicVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭ArticlePicVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_articlePic_input.jsp");
				failureView.forward(req, res);
				return; // 嚙緹嚙踝蕭嚙踝蕭嚙稻
			}

			/***************************
			 * 2.嚙罷嚙締嚙論改蕭嚙踝蕭
			 *****************************************/
			ArticlePicService articlePicSvc = new ArticlePicService();
			articlePicVO = articlePicSvc.updateArticlePic(articlePicId, articleId, articlePicBlob, articlePicTime);

			/***************************
			 * 3.嚙論改完嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)
			 *************/
			req.setAttribute("ArticlePicVO", articlePicVO); // 嚙踝蕭wupdate嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙確嚙踝蕭嚙踝蕭ArticlePicVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
			String url = "/articlePic/listOneArticlePic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙論改成嚙穀嚙踝蕭,嚙踝蕭嚙締istOneEmp.jsp
			successView.forward(req, res);
		}

		System.out.println(action);
		if ("insert".equals(action)) { // 嚙諉佗蕭addEmp.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************
			 * 1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲
			 *************************/

			Integer articleId = null;
			try {
				articleId = Integer.valueOf(req.getParameter("articleId").trim());
				if (articleId == 0) {
					errorMsgs.add("嚙罵嚙踝蕭ID嚙踝蕭嚙緻嚙踝蕭0");
				}
			} catch (NumberFormatException e) {
				articleId = 0;
				errorMsgs.add("嚙罵嚙踝蕭ID嚙請選蕭J嚙複字");
			}
			System.out.println(articleId);
			Part filePart = req.getPart("articlePicBlob");
			InputStream fileContent = filePart.getInputStream();
			byte[] articlePicBlob = fileContent.readAllBytes();
			fileContent.close();
			System.out.println(articlePicBlob);
			String articlePicTimeString = req.getParameter("articlePicTime").trim();

			// 嚙瞇嚙踝蕭嚙賞換嚙踝蕭 "yyyy-mm-dd hh:mm:ss" 嚙賣式嚙踝蕭嚙緝嚙褐佗蕭
			String formattedArticlePicTime = articlePicTimeString.replace("T", " ") + ":00";

			// 嚙璀嚙賞換嚙踝蕭 Timestamp
			Timestamp articlePicTime = Timestamp.valueOf(formattedArticlePicTime);
			System.out.println(articlePicTime);

			ArticlePicVO articlePicVO = new ArticlePicVO();

			articlePicVO.setArticleId(articleId);
			articlePicVO.setArticlePicBlob(articlePicBlob);
			articlePicVO.setArticlePicTime(articlePicTime);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ArticlePicVO", articlePicVO); //
				RequestDispatcher failureView = req.getRequestDispatcher("/articlePic/addArticlePic.jsp");
				failureView.forward(req, res);

				return;

			}

			/***************************
			 ***************************************/
			ArticlePicService articlePicSvc = new ArticlePicService();
			articlePicVO = articlePicSvc.addArticlePic(articleId, articlePicBlob, articlePicTime);
			/***************************
			 *****************************************/
			String articlePicBase64 = Base64.getEncoder().encodeToString(articlePicVO.getArticlePicBlob());
			req.setAttribute("articlePic64str", articlePicBase64);
			/***************************
			 ***********/
			String url = "/articlePic/listAllArticlePic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
			successView.forward(req, res);

		}

//		if ("delete".equals(action)) { // 嚙諉佗蕭listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 ***************************************/
//			Integer articlePicId = Integer.valueOf(req.getParameter("articlePicId"));
//
//			/*************************** 2.嚙罷嚙締嚙磋嚙踝蕭嚙踝蕭嚙� ***************************************/
//			ArticlePicService articlePicSvc = new ArticlePicService();
//			articlePicSvc.deleteEmp(articlePicId);
//
//			/*************************** 3.嚙磋嚙踝蕭嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view) ***********/
//			String url = "/emp/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙磋嚙踝蕭嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙稷嚙箴嚙碼嚙磋嚙踝蕭嚙踝蕭嚙諉瘀蕭嚙踝蕭嚙踝蕭
//			successView.forward(req, res);
//		}

	}
}
