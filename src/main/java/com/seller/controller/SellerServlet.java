package com.seller.controller;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seller.entity.SellerVO;
import com.seller.service.SellerService;
import com.sellerLv.dao.SellerLvDAO;
import com.sellerLv.dao.SellerLvHBDAO;
import com.sellerLv.entity.SellerLvVO;

import util.Util;

public class SellerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 如果useBodyEncoding=true，GET跟著POST編碼走
		// 如果useBodyEncoding=false，則跟請求轉發
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("sellerId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/seller/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer sellerId = null;
			try {
				sellerId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/seller/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			SellerService sellerSvc = new SellerService();
			SellerVO sellerVO = sellerSvc.getOneSeller(sellerId);
			if (sellerVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/seller/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("sellerVO", sellerVO); // 資料庫取出的empVO物件,存入req
			String url = "/seller/listOneSeller.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllSeller.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer sellerId = Integer.valueOf(req.getParameter("sellerId"));

			/*************************** 2.開始查詢資料 ****************************************/
			SellerService sellerSvc = new SellerService();
			SellerVO sellerVO = sellerSvc.getOneSeller(sellerId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("sellerVO", sellerVO); // 資料庫取出的empVO物件,存入req
			String url = "/seller/update_seller_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_seller_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_seller_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer sellerId = Integer.valueOf(req.getParameter("sellerId"));
			
			SellerLvDAO sellerDAOlv1 = new SellerLvHBDAO();
			SellerLvVO sellerlv1 = new SellerLvVO();

			
			Integer sellerLvId =null ;
			try {
				String sellerLvIdstr = req.getParameter("sellerLvId");
				if(sellerLvIdstr == null || sellerLvIdstr.trim().length()==0 ) {
					errorMsgs.add("請填寫等級");
				}
				sellerLvId = Integer.valueOf(sellerLvIdstr);
				if (sellerLvId>Util.SELLERLV_LIMIT) {
					errorMsgs.add("等級最大只到"+ Util.SELLERLV_LIMIT);
					sellerlv1=sellerDAOlv1.findByPrimaryKey(1);
				}else {
					sellerlv1=sellerDAOlv1.findByPrimaryKey(sellerLvId);
				}
				
			}
			catch(NumberFormatException e) {
				errorMsgs.add("等級請填寫數字");
			}
		
			String sellerEmail = req.getParameter("sellerEmail");
			String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
			// RFC 5322
			if (sellerEmail == null || sellerEmail.trim().length() == 0) {
				errorMsgs.add("信箱請勿留空白");
			} else if (!sellerEmail.trim().matches(emailReg)) {
				errorMsgs.add("信箱格式錯誤");
			}

			String sellerCompany = req.getParameter("sellerCompany");
			if (sellerCompany == null || sellerCompany.trim().length() == 0) {
				errorMsgs.add("公司姓名請勿空白");
			}

			String sellerTaxId = req.getParameter("sellerTaxId");
			if (sellerTaxId == null || sellerTaxId.trim().length() == 0) {
				errorMsgs.add("公司統編請勿空白");
			}

			
			Integer sellerCapital = 0; // Default value
			try {
			    String sellerCapitalStr = req.getParameter("sellerCapital");
			    if (sellerCapitalStr != null && !sellerCapitalStr.trim().isEmpty()) {
			        sellerCapital = Integer.valueOf(sellerCapitalStr);
			    }
			} catch (NumberFormatException e) {
				errorMsgs.add("資本額請輸入數字");
			}
			
			String sellerContact = req.getParameter("sellerContact");
			if (sellerContact == null || sellerContact.trim().length() == 0) {
				errorMsgs.add("公司負責人請勿空白");
			}

			String sellerCompanyPhone = req.getParameter("sellerCompanyPhone");
			if (sellerCompanyPhone == null || sellerCompanyPhone.trim().length() == 0) {
				errorMsgs.add("公司電話請勿空白");
			} else if (sellerCompanyPhone.trim().length()>10) {
				errorMsgs.add("公司電話請勿超過十碼");
			}

			String sellerCompanyExtension = req.getParameter("sellerCompanyExtension");
			if (sellerCompanyExtension == null ) {
				errorMsgs.add("JSP有人打錯");
			} 
//			else if (!sellerCompanyExtension.trim().matches("\\d+")) {
//				errorMsgs.add("分機格式錯誤");
//			}
			

			String sellerMobile = req.getParameter("sellerMobile");
			String mobileReg = "^09\\d\\d[0-9]{6}$";
			if (sellerMobile == null || sellerMobile.trim().length() == 0) {
				errorMsgs.add("手機請勿空白");
			} else if (!sellerMobile.trim().matches(mobileReg)) {
				errorMsgs.add("手機格式錯誤");
			}

			String sellerAddress = req.getParameter("sellerAddress");
			if (sellerAddress == null || sellerAddress.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}

			String sellerPassword = req.getParameter("sellerPassword");
			if (sellerPassword == null || sellerPassword.trim().length() == 0) {
				errorMsgs.add("密碼請勿留白");
			} else if (!sellerPassword.matches(".*[A-Z].*")) {
				errorMsgs.add("密碼需包含至少一個大寫字母");
			} else if (!sellerPassword.matches(".*[a-z].*")) {
				errorMsgs.add("密碼需包含至少一個小寫字母");
			} else if (!sellerPassword.matches(".*\\d.*")) {
				errorMsgs.add("密碼需包含至少一個數字");
			} else if (sellerPassword.length() < 8) {
				errorMsgs.add("密碼長度需至少為8位");
			}

			String sellerBankAccount = req.getParameter("sellerBankAccount");
			if (sellerBankAccount == null || sellerBankAccount.trim().length() == 0) {
				errorMsgs.add("銀行帳戶名稱請勿留白");
			}

			String sellerBankCode = req.getParameter("sellerBankCode");
			if (sellerBankCode == null || sellerBankCode.trim().length() == 0) {
				errorMsgs.add("密碼請勿留白");
			} else if (!sellerBankCode.matches("\\d{3}")) {
				errorMsgs.add("銀行代碼有誤");
			}

			String sellerBankAccountNumber = req.getParameter("sellerBankAccountNumber");
			if (sellerBankAccountNumber == null || sellerBankAccountNumber.trim().length() == 0) {
				errorMsgs.add("銀行帳號請勿留白");
			} else if (!sellerBankAccountNumber.matches("\\d{5,14}")) {
				errorMsgs.add("銀行帳號應為數字");
			}

			// 以下內容為預設值，且只能由Admin操控
			java.sql.Date sellerCreateTime = null;
			try {
				sellerCreateTime = java.sql.Date.valueOf(req.getParameter("sellerCreateTime"));
			} catch (DateTimeParseException e) {
				sellerCreateTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請選擇日期");
			}

			Boolean isConfirm = null;
			try {
				isConfirm = Boolean.valueOf(req.getParameter("isConfirm"));
			} catch (IllegalArgumentException e) {
				System.out.println("賣家帳號授權有誤");
			}

			
			// In case of error , user do not have to update again
			SellerVO sellerVO = new SellerVO();
			sellerVO.setSellerId(sellerId);
			

			
		
			sellerVO.setSellerLvId(sellerlv1);
			sellerVO.setSellerEmail(sellerEmail);
			sellerVO.setSellerCompany(sellerCompany);
			sellerVO.setSellerTaxId(sellerTaxId);
			sellerVO.setSellerCapital(sellerCapital);
			sellerVO.setSellerContact(sellerContact);
			sellerVO.setSellerCompanyPhone(sellerCompanyPhone);
			sellerVO.setSellerCompanyExtension(sellerCompanyExtension);
			sellerVO.setSellerMobile(sellerMobile);
			sellerVO.setSellerAddress(sellerAddress);
			sellerVO.setSellerPassword(sellerPassword);
			sellerVO.setSellerBankAccount(sellerBankAccount);
			sellerVO.setSellerBankCode(sellerBankCode);
			sellerVO.setSellerBankAccountNumber(sellerBankAccountNumber);
			sellerVO.setSellerCreateTime(sellerCreateTime);
			sellerVO.setIsConfirm(isConfirm);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sellerVO", sellerVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/seller/update_seller_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			SellerService sellerSvc = new SellerService();
			sellerVO = sellerSvc.updateSeller(sellerId, sellerLvId, sellerEmail, sellerCompany,
				    sellerTaxId, sellerCapital, sellerContact,
				    sellerCompanyPhone, sellerCompanyExtension, sellerMobile,
				    sellerAddress, sellerPassword, sellerBankAccount,
				    sellerBankCode, sellerBankAccountNumber,sellerCreateTime, isConfirm);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("sellerVO", sellerVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/seller/listOneSeller.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneSeller.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addSeller.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// Integer sellerId = Integer.valueOf(req.getParameter("sellerId"));
			// Integer sellerLvId =Integer.valueOf(req.getParameter("sellerLvId"));

			
//			FIX
			String sellerEmail = req.getParameter("sellerEmail");
			String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
			// RFC 5322
			if (sellerEmail == null || sellerEmail.trim().length() == 0) {
				errorMsgs.add("信箱請勿留空白");
			} else if (!sellerEmail.trim().matches(emailReg)) {
				errorMsgs.add("信箱格式錯誤");
			}

			String sellerCompany = req.getParameter("sellerCompany");
			if (sellerCompany == null || sellerCompany.trim().length() == 0) {
				errorMsgs.add("公司姓名請勿空白");
			}

			String sellerTaxId = req.getParameter("sellerTaxId");
			if (sellerCompany == null || sellerCompany.trim().length() == 0) {
				errorMsgs.add("公司統編請勿空白");
			}

			Integer sellerCapital = Integer.valueOf(req.getParameter("sellerCapital"));
			if (sellerCompany == null) {
				sellerCompany = sellerCompany.trim();
			}

			String sellerContact = req.getParameter("sellerContact");
			if (sellerContact == null || sellerContact.trim().length() == 0) {
				errorMsgs.add("公司負責人請勿空白");
			}

			String sellerCompanyPhone = req.getParameter("sellerCompanyPhone");
			if (sellerCompanyPhone == null || sellerCompanyPhone.trim().length() == 0) {
				errorMsgs.add("公司電話請勿空白");
			}

			String sellerCompanyExtension = req.getParameter("sellerCompanyExtension");
			sellerCompanyExtension = sellerCompanyExtension.trim();

			String sellerMobile = req.getParameter("sellerMobile");
			String mobileReg = "^09\\d\\d[0-9]{6}$";
			if (sellerMobile == null || sellerMobile.trim().length() == 0) {
				errorMsgs.add("手機請勿空白");
			} else if (!sellerMobile.trim().matches(mobileReg)) {
				errorMsgs.add("手機格式錯誤");
			}

			String sellerAddress = req.getParameter("sellerAddress");
			if (sellerAddress == null || sellerAddress.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}

			String sellerPassword = req.getParameter("sellerPassword");
			if (sellerPassword == null || sellerPassword.trim().length() == 0) {
				errorMsgs.add("密碼請勿留白");
			} else if (!sellerPassword.matches(".*[A-Z].*")) {
				errorMsgs.add("密碼需包含至少一個大寫字母");
			} else if (!sellerPassword.matches(".*[a-z].*")) {
				errorMsgs.add("密碼需包含至少一個小寫字母");
			} else if (!sellerPassword.matches(".*\\d.*")) {
				errorMsgs.add("密碼需包含至少一個數字");
			} else if (sellerPassword.length() < 8) {
				errorMsgs.add("密碼長度需至少為8位");
			}

			String sellerBankAccount = req.getParameter("sellerBankAccount");
			if (sellerBankAccount == null || sellerBankAccount.trim().length() == 0) {
				errorMsgs.add("銀行帳戶名稱請勿留白");
			} 
			
			String sellerBankCode = req.getParameter("sellerBankCode");
			if (sellerBankCode == null || sellerBankCode.trim().length() == 0) {
				errorMsgs.add("密碼請勿留白");
			} else if (!sellerBankCode.matches("^[0-9]{3}$")) {
				errorMsgs.add("銀行代碼有誤");
			}
			
			
			String sellerBankAccountNumber = req.getParameter("sellerBankAccountNumber");
			if (sellerBankAccountNumber == null || sellerBankAccountNumber.trim().length() == 0) {
				errorMsgs.add("銀行帳號請勿留白");
			} else if (!sellerBankAccountNumber.matches("\\d{5,14}")) {
				errorMsgs.add("銀行帳號應為數字");
			}

			SellerVO sellerVO = new SellerVO();
			sellerVO.setSellerEmail(sellerEmail);
			sellerVO.setSellerCompany(sellerCompany);
			sellerVO.setSellerTaxId(sellerTaxId);
			sellerVO.setSellerCapital(sellerCapital);
			sellerVO.setSellerContact(sellerContact);
			sellerVO.setSellerCompanyPhone(sellerCompanyPhone);
			sellerVO.setSellerCompanyExtension(sellerCompanyExtension);
			sellerVO.setSellerMobile(sellerMobile);
			sellerVO.setSellerAddress(sellerAddress);
			sellerVO.setSellerPassword(sellerPassword);
			sellerVO.setSellerBankAccount(sellerBankAccount);
			sellerVO.setSellerBankCode(sellerBankCode);
			sellerVO.setSellerBankAccountNumber(sellerBankAccountNumber);
			// sellerCreateTime is automatically set to the current date in the database,
			// isConfirm is automatically set to the current date in the database

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sellerVO", sellerVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/seller/addSeller.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			SellerService sellerSvc = new SellerService();
			sellerVO = sellerSvc.addSeller(sellerEmail, sellerCompany, sellerTaxId,
					sellerCapital, sellerContact, sellerCompanyPhone,
					sellerCompanyExtension, sellerMobile, sellerAddress,
					sellerPassword, sellerBankAccount, sellerBankCode,
					sellerBankAccountNumber);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/seller/listAllSeller.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllSeller.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllSeller.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer sellerId = Integer.valueOf(req.getParameter("sellerId"));

			/*************************** 2.開始刪除資料 ***************************************/
			SellerService sellerSvc = new SellerService();
			sellerSvc.deleteSeller(sellerId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/seller/listAllSeller.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
