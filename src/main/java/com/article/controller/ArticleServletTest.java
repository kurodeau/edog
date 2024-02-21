package com.article.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.*;

public class ArticleServletTest extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleId");

			if (str == null || str.trim().isEmpty()) {
			    errorMsgs.add("請輸入文章編號");
			} else {
			    if (!str.matches("\\d+")) {
			        errorMsgs.add("文章編號格式不正確，請輸入數字");
			    }
			}
			if (!errorMsgs.isEmpty()) {
			    RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
			    failureView.forward(req, res);
			    return; // 程式中斷
			}
			Integer articleId = Integer.valueOf(str);

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(articleId);
			if (articleVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
			String url = "/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer articleId = Integer.valueOf(req.getParameter("articleId"));
			System.out.println("Article ID: " + articleId);
			/*************************** 2.開始查詢資料 ****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(articleId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
			String url = "/article/update_article_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer articleId = Integer.valueOf(req.getParameter("articleId").trim());

			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());

			String articleTitle = req.getParameter("articleTitle").trim();
			if (articleTitle == null || articleTitle.trim().length() == 0) {
				errorMsgs.add("文章標題請勿空白");
			}
			String articleContent = req.getParameter("articleContent").trim();
			if (articleContent == null || articleContent.trim().length() == 0) {
				errorMsgs.add("文章內容請勿空白");
			}
			String artUpdateTimeString = req.getParameter("artUpdateTime").trim();

			String formattedArtUpdateTime = artUpdateTimeString.replace("T", " ") + ":00";

			Timestamp artUpdateTime = Timestamp.valueOf(formattedArtUpdateTime);
			Integer articleLike = Integer.valueOf(req.getParameter("articleLike").trim());
			Integer articleComment = Integer.valueOf(req.getParameter("articleComment").trim());
			Integer articleShare = Integer.valueOf(req.getParameter("articleShare").trim());
			Integer articleSort = Integer.valueOf(req.getParameter("articleSort").trim());

			String isEnabledStr = req.getParameter("isEnabled").trim();
			Boolean isEnabled = "1".equals(isEnabledStr);
			ArticleVO articleVO = new ArticleVO();

			articleVO.setArticleId(articleId);
			articleVO.setMemberId(memberId);
			articleVO.setArticleTitle(articleTitle);
			articleVO.setArticleContent(articleContent);
			articleVO.setArtUpdateTime(artUpdateTime);
			articleVO.setArticleLike(articleLike);
			articleVO.setArticleComment(articleComment);
			articleVO.setArticleShare(articleShare);
			articleVO.setArticleSort(articleSort);
			articleVO.setIsEnabled(isEnabled);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/article/update_article_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			articleVO = articleSvc.updateArticle(articleId, memberId, articleTitle, articleContent, artUpdateTime,
					articleLike, articleComment, articleShare, articleSort, isEnabled);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer memberId = null;
			try {
				memberId = Integer.valueOf(req.getParameter("memberId").trim());
			if(memberId == 0) {
				errorMsgs.add("會員ID不得為0");
			}
			} catch (NumberFormatException e) {
				memberId = 0;
				errorMsgs.add("會員ID請輸入數字");
			}
			
			String articleTitle = req.getParameter("articleTitle").trim();
			if (articleTitle == null || articleTitle.trim().length() == 0) {
				errorMsgs.add("文章標題請勿空白");
			}
			String articleContent = req.getParameter("articleContent").trim();
			if (articleContent == null || articleContent.trim().length() == 0) {
				errorMsgs.add("文章內容請勿空白");
			}
			String artUpdateTimeString = req.getParameter("artUpdateTime").trim();

			String formattedArtUpdateTime = artUpdateTimeString.replace("T", " ") + ":00";

			Timestamp artUpdateTime = Timestamp.valueOf(formattedArtUpdateTime);

			Integer articleLike = 0;
			Integer articleComment = 0;
			Integer articleShare = 0;
			Integer articleSort = Integer.valueOf(req.getParameter("articleSort").trim());
			// 使用字符串比較而不是布林轉換
			String isEnabledStr = req.getParameter("isEnabled").trim();
			Boolean isEnabled = "1".equals(isEnabledStr);

			ArticleVO articleVO = new ArticleVO();

			articleVO.setMemberId(memberId);
			articleVO.setArticleTitle(articleTitle);
			articleVO.setArticleContent(articleContent);
			articleVO.setArtUpdateTime(artUpdateTime);
			articleVO.setArticleLike(articleLike);
			articleVO.setArticleComment(articleComment);
			articleVO.setArticleShare(articleShare);
			articleVO.setArticleSort(articleSort);
			articleVO.setIsEnabled(isEnabled);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/article/addArticle.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ArticleService articleSvc = new ArticleService();
			articleVO = articleSvc.addArticle(memberId, articleTitle, articleContent, artUpdateTime, articleLike,
					articleComment, articleShare, articleSort, isEnabled);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//		}
	}
}
