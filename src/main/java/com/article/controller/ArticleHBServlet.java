package com.article.controller;

import java.io.IOException;
import java.sql.Timestamp;
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

import com.article.entity.ArticleVO;
import com.article.service.ArticleService;
import com.articleType.dao.ArticleTypeDAO;
import com.articleType.dao.ArticleTypeDAOImpl;
import com.articleType.entity.ArticleTypeVO;

import util.Util;

@WebServlet("/article/article.do")
public class ArticleHBServlet extends HttpServlet {

	private List<String> errorMsgs = null;
	private ArticleService articleSvc;

	@Override
	public void init() throws ServletException {
		articleSvc = new ArticleService();
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
		case "compositeQuery":
			forwardPath = getCompositeArticlesQuery(req, res);
			break;

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
		Integer articleId =Integer.parseInt(req.getParameter("articleId")) ;
		ArticleVO articleVO= articleSvc.getOneArticle(articleId);
		req.setAttribute("articleVO", articleVO);

		return "/Article/listOneArticle.jsp";
		
	}

	private String getCompositeArticlesQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

//		System.out.println("=====================");
//		for(String key : map.keySet()) {
//			for(String value: map.get(key)) {
//				System.out.println("key" + key +" value" +value);
//			}
//		}
//
//		System.out.println("=====================");

		if (map != null) {
			List<ArticleVO> list = articleSvc.getArticlesByCompositeQuery(map);
			req.setAttribute("list", list);
		} else {
			return "/index.jsp";
		}
		return "/article/listCompositeArticlesQuery.jsp";
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<ArticleVO> list = articleSvc.getAll();

		if (req.getSession().getAttribute("articlePageQty") == null) {
			int articlePageQty = articleSvc.getTotal();
			req.getSession().setAttribute("articlePageQty", articlePageQty);
		}

		req.setAttribute("list", list);
		req.setAttribute("currentPage", currentPage);

		return "/article/listAllArticle.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));
		ArticleVO articleVO = articleSvc.getOneArticle(articleId);

		req.setAttribute("articleVO", articleVO);
		return "/article/update_article_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		/*************************** 1.接收請求參數 ****************************************/
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));
		Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
		try {
			String memberIdStr = req.getParameter("memberId");
			if (memberIdStr != null && !memberIdStr.trim().isEmpty()) {
				memberId = Integer.valueOf(memberIdStr);
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("會員編號請輸入數字");
		}
		
		String articleTitle = req.getParameter("articleTitle").trim();
		if (articleTitle == null || articleTitle.trim().length() == 0) {
			errorMsgs.add("文章標題請勿空白");
		}
		String articleContent = req.getParameter("articleContent").trim();
		if (articleContent == null || articleContent.trim().length() == 0) {
			errorMsgs.add("文章內容請勿空白");
		}
		java.util.Date artUpdateTime = null;
		try {

			String dateformatStr = req.getParameter("artUpdateTime");
			System.out.println("dateformatStr" + dateformatStr);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			artUpdateTime = dateFormat.parse(dateformatStr);

		} catch (ParseException e) {
			artUpdateTime = new java.util.Date(System.currentTimeMillis());
			errorMsgs.add("請選擇日期");
		}
		Integer articleLike = Integer.valueOf(req.getParameter("articleLike").trim());
		Integer articleComment = Integer.valueOf(req.getParameter("articleComment").trim());
		Integer articleShare = Integer.valueOf(req.getParameter("articleShare").trim());
		
		ArticleTypeDAO ArticleTypeDAO1 = new ArticleTypeDAOImpl();
		ArticleTypeVO articleType1 = new ArticleTypeVO();
		
		Integer articleSort = null;
		try {
			String articleSortstr = req.getParameter("articleSort");
			if (articleSortstr == null || articleSortstr.trim().length() == 0) {
				errorMsgs.add("選擇文章分類");
			}else {
				articleType1 = ArticleTypeDAO1.findByPrimaryKey(articleSort);
			}

		} catch (NumberFormatException e) {
			errorMsgs.add("等級請填寫數字");
		}
		
		String isEnabledStr = req.getParameter("isEnabled").trim();
		Boolean isEnabled = "1".equals(isEnabledStr);

		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticleId(articleId);
		// 因為屬性是 FK 所以這邊放對象
		articleVO.setArticleId(articleId);
		articleVO.setMemberId(memberId);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArtUpdateTime(artUpdateTime);
		articleVO.setArticleLike(articleLike);
		articleVO.setArticleComment(articleComment);
		articleVO.setArticleShare(articleShare);
		articleVO.setArticleTypeId(articleType1);
		articleVO.setIsEnabled(isEnabled);

		req.setAttribute("articleVO", articleVO);

		if (!errorMsgs.isEmpty()) {
			return ("/article/update_article_input.jsp");
		}

		// Update 使用值做更新(因為Service內部轉換)
		articleVO = articleSvc.updateArticle(articleId, memberId, articleTitle, articleContent, artUpdateTime,
				articleLike, articleComment, articleShare, articleSort, isEnabled);

		return "/article/listOneArticle.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
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
		java.util.Date artUpdateTime = null;
		try {

			String dateformatStr = req.getParameter("artUpdateTime");
			System.out.println("dateformatStr" + dateformatStr);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			artUpdateTime = dateFormat.parse(dateformatStr);

		} catch (ParseException e) {
			artUpdateTime = new java.util.Date(System.currentTimeMillis());
			errorMsgs.add("請選擇日期");
		}
		Integer articleLike = 0;
		Integer articleComment = 0;
		Integer articleShare = 0;
		ArticleTypeDAO ArticleTypeDAO1 = new ArticleTypeDAOImpl();
		ArticleTypeVO articleType1 = new ArticleTypeVO();
		
		Integer articleSort = null;
		try {
			String articleSortstr = req.getParameter("articleSort");
			if (articleSortstr == null || articleSortstr.trim().length() == 0) {
				errorMsgs.add("選擇文章分類");
			}else {
				articleType1 = ArticleTypeDAO1.findByPrimaryKey(articleSort);
			}

		} catch (NumberFormatException e) {
			errorMsgs.add("等級請填寫數字");
		}
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
		articleVO.setArticleTypeId(articleType1);
		articleVO.setIsEnabled(isEnabled);
		// sellerCreateTime is automatically set to the current date in the database,
		// isConfirm is automatically set to the current date in the database

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
			return ("/article/addArticle.jsp");
		}

		articleVO = articleSvc.addArticle(
				memberId, articleTitle, articleContent, artUpdateTime, articleLike,
				articleComment, articleShare, articleSort, isEnabled);

		
		req.setAttribute("articleVO", articleVO);

		return "/article/listOneArticle.jsp";
	}

}
