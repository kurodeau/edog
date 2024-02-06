package com.seller.controller;

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

import com.seller.entity.SellerVO;
import com.seller.service.SellerService;
import com.sellerLv.dao.SellerLvDAO;
import com.sellerLv.dao.SellerLvHBDAO;
import com.sellerLv.entity.SellerLvVO;

import util.Util;

@WebServlet("/seller/seller.do")
public class SellerServlet extends HttpServlet {

	private List<String> errorMsgs = null;
	private SellerService sellerSvc;

	@Override
	public void init() throws ServletException {
		sellerSvc = new SellerService();
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
			forwardPath = getCompositeSellersQuery(req, res);
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
		Integer sellerId =Integer.parseInt(req.getParameter("sellerId")) ;
		SellerVO sellerVO= sellerSvc.getOneSeller(sellerId);
		req.setAttribute("sellerVO", sellerVO);

		return "/seller/listOneSeller.jsp";
		
	}

	private String getCompositeSellersQuery(HttpServletRequest req, HttpServletResponse res) {
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
			List<SellerVO> list = sellerSvc.getSellersByCompositeQuery(map);
			req.setAttribute("list", list);
		} else {
			return "/index.jsp";
		}
		return "/seller/listCompositeQuerySellers.jsp";
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<SellerVO> list = sellerSvc.getAll();

		if (req.getSession().getAttribute("sellerPageQty") == null) {
			int sellerPageQty = sellerSvc.getTotal();
			req.getSession().setAttribute("sellerPageQty", sellerPageQty);
		}

		req.setAttribute("list", list);
		req.setAttribute("currentPage", currentPage);

		return "/seller/listAllSeller.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer sellerId = Integer.valueOf(req.getParameter("sellerId"));
		SellerVO sellerVO = sellerSvc.getOneSeller(sellerId);

		req.setAttribute("sellerVO", sellerVO);
		return "/seller/update_seller_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		/*************************** 1.接收請求參數 ****************************************/
		Integer sellerId = Integer.valueOf(req.getParameter("sellerId"));

		SellerLvDAO sellerDAOlv1 = new SellerLvHBDAO();
		SellerLvVO sellerlv1 = new SellerLvVO();

		Integer sellerLvId = null;
		try {
			String sellerLvIdstr = req.getParameter("sellerLvId");
			if (sellerLvIdstr == null || sellerLvIdstr.trim().length() == 0) {
				errorMsgs.add("請填寫等級");
			}
			sellerLvId = Integer.valueOf(sellerLvIdstr);
			if (sellerLvId > Util.SELLERLV_LIMIT) {
				errorMsgs.add("等級最大只到" + Util.SELLERLV_LIMIT);
				sellerlv1 = sellerDAOlv1.findByPrimaryKey(1);
			} else {
				sellerlv1 = sellerDAOlv1.findByPrimaryKey(sellerLvId);
			}

		} catch (NumberFormatException e) {
			errorMsgs.add("等級請填寫數字");
		}

		String sellerEmail = req.getParameter("sellerEmail");
		String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

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
		} else if (sellerCompanyPhone.trim().length() > 10) {
			errorMsgs.add("公司電話請勿超過十碼");
		}

		String sellerCompanyExtension = req.getParameter("sellerCompanyExtension");
		if (sellerCompanyExtension == null) {
			errorMsgs.add("JSP有人打錯");
		}
//		else if (!sellerCompanyExtension.trim().matches("\\d+")) {
//			errorMsgs.add("分機格式錯誤");
//		}

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
		java.util.Date sellerCreateTime = null;
		try {

			String dateformatStr = req.getParameter("sellerCreateTime");
			System.out.println("dateformatStr" + dateformatStr);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			sellerCreateTime = dateFormat.parse(dateformatStr);

		} catch (ParseException e) {
			sellerCreateTime = new java.util.Date(System.currentTimeMillis());
			errorMsgs.add("請選擇日期");
		}

		Boolean isConfirm = null;
		try {

			isConfirm = req.getParameter("isConfirm") == null ? false : true;

		} catch (IllegalArgumentException e) {
			System.out.println("賣家帳號授權有誤");
		}

		//
		SellerVO sellerVO = new SellerVO();
		sellerVO.setSellerId(sellerId);
		// 因為屬性是 FK 所以這邊放對象
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

		req.setAttribute("sellerVO", sellerVO);

		if (!errorMsgs.isEmpty()) {
			return ("/seller/update_seller_input.jsp");
		}

		// Update 使用值做更新(因為Service內部轉換)
		sellerVO = sellerSvc.updateSeller(sellerId, sellerLvId, sellerEmail, sellerCompany, sellerTaxId, sellerCapital,
				sellerContact, sellerCompanyPhone, sellerCompanyExtension, sellerMobile, sellerAddress, sellerPassword,
				sellerBankAccount, sellerBankCode, sellerBankAccountNumber, sellerCreateTime, isConfirm);

		return "/seller/listOneSeller.jsp";
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
			return ("/seller/addSeller.jsp");
		}

		sellerVO = sellerSvc.addSeller(sellerEmail, sellerCompany, sellerTaxId, sellerCapital, sellerContact,
				sellerCompanyPhone, sellerCompanyExtension, sellerMobile, sellerAddress, sellerPassword,
				sellerBankAccount, sellerBankCode, sellerBankAccountNumber);

		return "/seller/listAllSeller.jsp";
	}

}
