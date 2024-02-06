package com.sellerLv.controller;

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

import com.sellerLv.entity.SellerLvVO;
import com.sellerLv.service.SellerLvService;
import com.seller.entity.SellerVO;
import com.sellerLv.dao.SellerLvDAO;
import com.sellerLv.dao.SellerLvHBDAO;
import com.sellerLv.entity.SellerLvVO;

import util.Util;

@WebServlet("/sellerLv/sellerLv.do")
public class SellerLvServlet extends HttpServlet {

	private List<String> errorMsgs = null;
	private SellerLvService sellerLvSvc;

	@Override
	public void init() throws ServletException {
		sellerLvSvc = new SellerLvService();
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
		Integer sellerLvId =Integer.parseInt(req.getParameter("sellerLvId")) ;
		SellerLvVO sellerLvVO= sellerLvSvc.getOneSellerLv(sellerLvId);
		req.setAttribute("sellerLvVO", sellerLvVO);

		return "/seller/listOneSellerLv.jsp";
		
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<SellerLvVO> list = sellerLvSvc.getAll();

		if (req.getSession().getAttribute("sellerPageQty") == null) {
			int sellerPageQty = sellerLvSvc.getTotal();
			req.getSession().setAttribute("sellerPageQty", sellerPageQty);
		}

		req.setAttribute("list", list);
		req.setAttribute("currentPage", currentPage);

		return "/sellerLv/listAllSellerLv.jsp";
	}



	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer sellerLvId = Integer.valueOf(req.getParameter("sellerLvId"));
		SellerLvVO sellerVO = sellerLvSvc.getOneSellerLv(sellerLvId);

		req.setAttribute("sellerVO", sellerVO);
		return "/sellerLv/update_seller_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {


		return "/sellerLv/listOneSellerLv.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
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

		SellerLvVO sellerVO = new SellerLvVO();
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
			return ("/sellerLv/addSellerLv.jsp");
		}

		sellerVO = sellerLvSvc.addSeller(sellerEmail, sellerCompany, sellerTaxId, sellerCapital, sellerContact,
				sellerCompanyPhone, sellerCompanyExtension, sellerMobile, sellerAddress, sellerPassword,
				sellerBankAccount, sellerBankCode, sellerBankAccountNumber);

		return "/sellerLv/listAllSellerLv.jsp";
	}

}
