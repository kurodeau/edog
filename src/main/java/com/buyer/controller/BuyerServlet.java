package com.buyer.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.buyer.entity.BuyerVO;
import com.buyer.service.BuyerService;


@WebServlet("/buyer/buyer.do")
public class BuyerServlet extends HttpServlet {

	private List<String> errorMsgs = null;
	private BuyerService buyerSvc;

	@Override
	public void init() throws ServletException {
		buyerSvc = new BuyerService();
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
			forwardPath = getCompositeBuyersQuery(req, res);
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
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		BuyerVO buyerVO = buyerSvc.getOneBuyer(memberId);
		req.setAttribute("buyerVO", buyerVO);

		return "/buyer/listOneBuyer.jsp";

	}

	private String getCompositeBuyersQuery(HttpServletRequest req, HttpServletResponse res) {
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
			List<BuyerVO> list = buyerSvc.getBuyersByCompositeQuery(map);
			req.setAttribute("list", list);
		} else {
			return "buyer/index.jsp";
		}
		return "/buyer/listCompositeQueryBuyers.jsp";
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<BuyerVO> list = buyerSvc.getAll();

		if (req.getSession().getAttribute("buyerPageQty") == null) {
			int buyerPageQty = buyerSvc.getTotal();
			req.getSession().setAttribute("buyerPageQty", buyerPageQty);
		}

		req.setAttribute("list", list);
		req.setAttribute("currentPage", currentPage);

		return "/buyer/listAllBuyer.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer memberId = Integer.valueOf(req.getParameter("memberId"));
		BuyerVO buyerVO = buyerSvc.getOneBuyer(memberId);

		req.setAttribute("buyerVO", buyerVO);
		return "/buyer/update_buyer_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		/*************************** 1.接收請求參數 ****************************************/
		Integer memberId = Integer.valueOf(req.getParameter("memberId"));
		
		String memberEmail = String.valueOf(req.getParameter("memberEmail"));
		String emailPat = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (memberEmail == null || memberEmail.trim().length() == 0) {
			errorMsgs.add("用戶信箱不可以為空白");
		}
		else if ( !memberEmail.trim().matches(emailPat) ) {
			errorMsgs.add("錯誤的信箱格式");			
		}
		
		String thirdFrom = String.valueOf(req.getParameter("thirdFrom"));
		
		String memberName = req.getParameter("memberName");
		if (memberName == null || memberName.trim().length() == 0) {
			errorMsgs.add("用戶名稱不可以為空白");
		}
		else if ( memberName.trim().length()>10 ) {
			errorMsgs.add("用戶名稱不可超過10個字");			
		}
				
		String memberPhone = String.valueOf(req.getParameter("memberPhone"));
		String phonePat = "^[0-9][0-9]*$";
		if ( !(memberPhone == null) && !memberPhone.trim().matches(phonePat) ) {
			errorMsgs.add("錯誤的電話格式");			
		}
		
		String memberMobile = String.valueOf(req.getParameter("memberMobile"));
		String mobilePat = "^[0-9][0-9]*$";
		if ( !(memberMobile == null) && !memberMobile.trim().matches(mobilePat) ) {
			errorMsgs.add("錯誤的行動電話格式");			
		}
		
		//(待辦)日期怎搞?
//		Date memberBirthday = Date.valueOf(req.getParameter("memberBirthday"));
		Date memberBirthday = null;

		
		String memberPassword = req.getParameter("memberPassword");
		if (memberPassword == null || memberPassword.trim().length() == 0) {
			errorMsgs.add("用戶密碼不可以為空白");
		}
		else if ( memberPassword.trim().length()>10 || memberPassword.trim().length()<4 ) {
			errorMsgs.add("用戶密碼必須介於4~10個字");			
		}		
	
		String memberAddress = String.valueOf(req.getParameter("memberAddress"));

		Boolean isMemberEmail = Boolean.valueOf(req.getParameter("isMemberEmail"));

		//(待辦)日期怎搞, 但邏輯上用戶註冊時間不用(能)讓使用者自訂, 該自動更新
//		Date memberRegistrationTime = Date.valueOf(req.getParameter("memberRegistrationTime"));
		Date memberRegistrationTime = null;
		
		String petName = String.valueOf(req.getParameter("petName"));
		if ( petName.trim().length()>10 ) {
			errorMsgs.add("寵物名稱不可超過10個字");			
		}
		//(待辦)圖片怎搞
//		byte[] petImg = byte[].valueOf(req.getParameter("petImg"));
		byte[] petImg = null;
		
		//(待辦)日期怎搞, 但邏輯上更新圖片時間不用(能)讓使用者自訂, 該自動更新
//		Date petImgUploadTime = Date.valueOf(req.getParameter("petImgUploadTime"));
		Date petImgUploadTime = null;
		
		String petVaccName1 = String.valueOf(req.getParameter("petVaccName1"));
		if ( petVaccName1.trim().length()>10 ) {
			errorMsgs.add("寵物疫苗名稱不可超過10個字");			
		}
		
		//(待辦)日期怎搞?
//		Date petVaccTime1 = Date.valueOf(req.getParameter("petVaccTime1"));
		Date petVaccTime1 = null;
		
		String petVaccName2 = String.valueOf(req.getParameter("petVaccName2"));
		if ( petVaccName2.trim().length()>10 ) {
			errorMsgs.add("寵物疫苗名稱不可超過10個字");			
		}
		
		//(待辦)日期怎搞?
//		Date petVaccTime2 = Date.valueOf(req.getParameter("petVaccTime2"));
		Date petVaccTime2 = null;
		
		Boolean isConfirm = Boolean.valueOf(req.getParameter("isConfirm"));
		
		BuyerVO buyerVO = new BuyerVO();
		buyerVO.setMemberId(memberId);
		buyerVO.setMemberEmail(memberEmail);
		buyerVO.setThirdFrom(thirdFrom);
		buyerVO.setMemberName(memberName);
		buyerVO.setMemberPhone(memberPhone);
		buyerVO.setMemberMobile(memberMobile);
		buyerVO.setMemberBirthday(memberBirthday);
		buyerVO.setMemberPassword(memberPassword);
		buyerVO.setMemberAddress(memberAddress);
		buyerVO.setIsMemberEmail(isMemberEmail);
		buyerVO.setPetName(petName);
		buyerVO.setPetImg(petImg); //不知道怎麼搞圖片, 先註解
		buyerVO.setPetImgUploadTime(petImgUploadTime);
		buyerVO.setPetVaccName1(petVaccName1);
		buyerVO.setPetVaccTime1(petVaccTime1);
		buyerVO.setPetVaccName2(petVaccName2);
		buyerVO.setPetVaccTime2(petVaccTime2);
		buyerVO.setIsConfirm(isConfirm);

		req.setAttribute("buyerVO", buyerVO);

		if (!errorMsgs.isEmpty()) {
			return ("/buyer/update_buyer_input.jsp");
		}

		// Update 使用值做更新(因為Service內部轉換)
		buyerVO = buyerSvc.updateBuyer(
//				memberId, //不允許更新Id
				memberEmail, 
				thirdFrom, 
				memberName, 
				memberPhone, 
				memberMobile,
				memberBirthday, 
				memberPassword, 
				memberAddress,
				isMemberEmail,
//				memberRegistrationTime, //不允許更新創帳號時間
				petName,
				petImg, //不知道怎麼搞圖片, 先註解
				petImgUploadTime,
				petVaccName1,
				petVaccTime1,
				petVaccName2,
				petVaccTime2,
				isConfirm
				);

		return "/buyer/listOneBuyer.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		
		String memberEmail = String.valueOf(req.getParameter("memberEmail"));
		String emailPat = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (memberEmail == null || memberEmail.trim().length() == 0) {
			errorMsgs.add("用戶信箱不可以為空白");
		}
		else if ( !memberEmail.trim().matches(emailPat) ) {
			errorMsgs.add("錯誤的信箱格式");			
		}
		
		String thirdFrom = String.valueOf(req.getParameter("thirdFrom"));
		
		String memberName = req.getParameter("memberName");
		if (memberName == null || memberName.trim().length() == 0) {
			errorMsgs.add("用戶名稱不可以為空白");
		}
		else if ( memberName.trim().length()>10 ) {
			errorMsgs.add("用戶名稱不可超過10個字");			
		}
				
		String memberPhone = String.valueOf(req.getParameter("memberPhone"));
		String phonePat = "^[0-9][0-9]*$";
		if ( !(memberPhone == null) && !memberPhone.trim().matches(phonePat) ) {
			errorMsgs.add("錯誤的電話格式");			
		}
		
		String memberMobile = String.valueOf(req.getParameter("memberMobile"));
		String mobilePat = "^[0-9][0-9]*$";
		if ( !(memberMobile == null) && !memberMobile.trim().matches(mobilePat) ) {
			errorMsgs.add("錯誤的行動電話格式");			
		}
		
		//(待辦)日期怎搞?
//		Date memberBirthday = Date.valueOf(req.getParameter("memberBirthday"));
		Date memberBirthday = null;

		
		String memberPassword = req.getParameter("memberPassword");
		if (memberPassword == null || memberPassword.trim().length() == 0) {
			errorMsgs.add("用戶密碼不可以為空白");
		}
		else if ( memberPassword.trim().length()>10 || memberPassword.trim().length()<4 ) {
			errorMsgs.add("用戶密碼必須介於4~10個字");			
		}		
	
		String memberAddress = String.valueOf(req.getParameter("memberAddress"));

		Boolean isMemberEmail = Boolean.valueOf(req.getParameter("isMemberEmail"));

		//(待辦)日期怎搞, 但邏輯上用戶註冊時間不用(能)讓使用者自訂, 該自動更新
//		Date memberRegistrationTime = Date.valueOf(req.getParameter("memberRegistrationTime"));
		Date memberRegistrationTime = null;
		
		String petName = String.valueOf(req.getParameter("petName"));
		if ( petName.trim().length()>10 ) {
			errorMsgs.add("寵物名稱不可超過10個字");			
		}
		//(待辦)圖片怎搞
//		byte[] petImg = byte[].valueOf(req.getParameter("petImg"));
		byte[] petImg = null;
		
		//(待辦)日期怎搞, 但邏輯上更新圖片時間不用(能)讓使用者自訂, 該自動更新
//		Date petImgUploadTime = Date.valueOf(req.getParameter("petImgUploadTime"));
		Date petImgUploadTime = null;
		
		String petVaccName1 = String.valueOf(req.getParameter("petVaccName1"));
		if ( petVaccName1.trim().length()>10 ) {
			errorMsgs.add("寵物疫苗名稱不可超過10個字");			
		}
		
		//(待辦)日期怎搞?
//		Date petVaccTime1 = Date.valueOf(req.getParameter("petVaccTime1"));
		Date petVaccTime1 = null;
		
		String petVaccName2 = String.valueOf(req.getParameter("petVaccName2"));
		if ( petVaccName2.trim().length()>10 ) {
			errorMsgs.add("寵物疫苗名稱不可超過10個字");			
		}
		
		//(待辦)日期怎搞?
//		Date petVaccTime2 = Date.valueOf(req.getParameter("petVaccTime2"));
		Date petVaccTime2 = null;
		
		Boolean isConfirm = Boolean.valueOf(req.getParameter("isConfirm"));

		BuyerVO buyerVO = new BuyerVO();
//		buyerVO.setMemberId(memberId);
		buyerVO.setMemberEmail(memberEmail);
		buyerVO.setThirdFrom(thirdFrom);
		buyerVO.setMemberName(memberName);
		buyerVO.setMemberPhone(memberPhone);
		buyerVO.setMemberMobile(memberMobile);
		buyerVO.setMemberBirthday(memberBirthday);
		buyerVO.setMemberPassword(memberPassword);
		buyerVO.setMemberAddress(memberAddress);
		buyerVO.setIsMemberEmail(isMemberEmail);
//		buyerVO.setMemberRegistrationTime(memberRegistrationTime);
		buyerVO.setPetName(petName);
		buyerVO.setPetImg(petImg); //不知道怎麼搞圖片, 先註解
		buyerVO.setPetImgUploadTime(petImgUploadTime);
		buyerVO.setPetVaccName1(petVaccName1);
		buyerVO.setPetVaccTime1(petVaccTime1);
		buyerVO.setPetVaccName2(petVaccName2);
		buyerVO.setPetVaccTime2(petVaccTime2);
		buyerVO.setIsConfirm(isConfirm);

		req.setAttribute("buyerVO", buyerVO);
		System.out.println(buyerVO);
		// couponCreateTime is automatically set to the current date in the database,9
		// isConfirm is automatically set to the current date in the database

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("buyerVO", buyerVO); // 含有輸入格式錯誤的buyerVO物件,也存入req
			return ("/buyer/addBuyer.jsp");
		}

		buyerVO = buyerSvc.addBuyer(
//				memberId, //自增鍵不允許手動新增
				memberEmail,
				thirdFrom,
				memberName,
				memberPhone,
				memberMobile,
				memberBirthday,
				memberPassword,
				memberAddress,
				isMemberEmail,
//				memberRegi	ionTime, //自增鍵不允許手動新增
				petName,
				petImg, //不知道怎麼搞圖片, 先註解
				petImgUploadTime,
				petVaccName1,
				petVaccTime1,
				petVaccName2,
				petVaccTime2,
				isConfirm
				);
		req.setAttribute("buyerVO", buyerVO);

		return "/buyer/listOneBuyer.jsp";
	}

}

