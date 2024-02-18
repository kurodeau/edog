package com.coupon.controller;

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

import com.coupon.entity.CouponVO;
import com.coupon.service.CouponService;

@WebServlet("/coupon/coupon.do")
public class CouponServlet extends HttpServlet {

	private List<String> errorMsgs = null;
	private CouponService couponSvc;

	@Override
	public void init() throws ServletException {
		couponSvc = new CouponService();
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
				forwardPath = getCompositeCouponsQuery(req, res);
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
		Integer couponId = Integer.parseInt(req.getParameter("couponId"));
		CouponVO couponVO = couponSvc.getOneCoupon(couponId);
		req.setAttribute("couponVO", couponVO);

		return "/coupon/listOneCoupon.jsp";

	}

	private String getCompositeCouponsQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		// System.out.println("=====================");
		// for(String key : map.keySet()) {
		// for(String value: map.get(key)) {
		// System.out.println("key" + key +" value" +value);
		// }
		// }
		//
		// System.out.println("=====================");

		if (map != null) {
			List<CouponVO> list = couponSvc.getCouponsByCompositeQuery(map);
			req.setAttribute("list", list);
		} else {
			return "coupon/index.jsp";
		}
		return "/coupon/listCompositeQueryCoupons.jsp";
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<CouponVO> list = couponSvc.getAll();

		if (req.getSession().getAttribute("couponPageQty") == null) {
			int couponPageQty = couponSvc.getTotal();
			req.getSession().setAttribute("couponPageQty", couponPageQty);
		}

		req.setAttribute("list", list);
		req.setAttribute("currentPage", currentPage);

		return "/coupon/listAllCoupon.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer couponId = Integer.valueOf(req.getParameter("couponId"));
		CouponVO couponVO = couponSvc.getOneCoupon(couponId);

		req.setAttribute("couponVO", couponVO);
		return "/coupon/update_coupon_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		/*************************** 1.接收請求參數 ****************************************/
		Integer couponId = Integer.valueOf(req.getParameter("couponId"));

		String couponCode = req.getParameter("couponCode");
		if (couponCode == null) {
			errorMsgs.add("優惠券代碼不能為空");
		} else {
			couponCode = couponCode.trim();
			if (couponCode.isEmpty()) {
				errorMsgs.add("優惠券代碼請勿空白");
			} else if (couponCode.length() > 20) {
				errorMsgs.add("優惠券代碼長度不能超過20");
			} else if (!couponSvc.isCouponCodeUnique(couponCode, couponId)) {
				errorMsgs.add("優惠券代碼重複");
			}
		}

		String couponName = req.getParameter("couponName");
		if (couponName == null || couponName.trim().length() == 0) {
			errorMsgs.add("優惠券名稱請勿空白");
		}

		Date startTime = null;
		try {
			String startTimeStr = req.getParameter("startTime");
			if (startTimeStr != null && !startTimeStr.trim().isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				startTime = dateFormat.parse(startTimeStr);
				System.out.println("startTimeStr" + startTimeStr);
				System.out.println("startTime" + startTime);

			} else {
				errorMsgs.add("請輸入開始時間");
			}
		} catch (ParseException e) {
			errorMsgs.add("請選擇正確的開始時間");
		}

		Date endTime = null;
		try {
			String endTimeStr = req.getParameter("endTime");
			if (endTimeStr != null && !endTimeStr.trim().isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				endTime = dateFormat.parse(endTimeStr);
				System.out.println("endTimeStr" + endTimeStr);
				System.out.println("endTime" + endTime);
			} else {
				errorMsgs.add("請輸入結束時間");
			}
		} catch (ParseException e) {
			errorMsgs.add("請選擇正確的結束時間");
		}

		Integer minSpendingAmount = null;
		try {
			String minSpendingAmountStr = req.getParameter("minSpendingAmount");
			if (minSpendingAmountStr != null && !minSpendingAmountStr.trim().isEmpty()) {
				minSpendingAmount = Integer.valueOf(minSpendingAmountStr);
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("最低消費金額請輸入數字");
		}

		Integer couponQuantity = null;
		try {
			String couponQuantityStr = req.getParameter("couponQuantity");
			if (couponQuantityStr != null && !couponQuantityStr.trim().isEmpty()) {
				couponQuantity = Integer.valueOf(couponQuantityStr);

				// 驗證 couponQuantity
				if (couponQuantity <= 0) {
					errorMsgs.add("優惠券數量應該大於0");
				}
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("優惠券數量請輸入數字");
		}

		Integer memberAllowQuantity = null;
		try {
			String memberAllowQuantityStr = req.getParameter("memberAllowQuantity");
			if (memberAllowQuantityStr != null && !memberAllowQuantityStr.trim().isEmpty()) {
				memberAllowQuantity = Integer.valueOf(memberAllowQuantityStr);

				// 驗證 memberAllowQuantity
				if (memberAllowQuantity <= 0) {
					errorMsgs.add("會員可使用數量應該大於0");
				}
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("會員可使用數量請輸入數字");
		}

		Integer couponDiscount = null;
		try {
			String couponDiscountStr = req.getParameter("couponDiscount");
			if (couponDiscountStr != null && !couponDiscountStr.trim().isEmpty()) {
				couponDiscount = Integer.valueOf(couponDiscountStr);

				// 驗證 couponDiscount
				if (couponDiscount < 0) {
					errorMsgs.add("優惠折扣應該大於等於0");
				}
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("優惠折扣請輸入數字");
		}

		Date couponCreateTime = null;
		try {
			String couponCreateTimeStr = req.getParameter("couponCreateTime");
			if (couponCreateTimeStr != null && !couponCreateTimeStr.trim().isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				couponCreateTime = dateFormat.parse(couponCreateTimeStr);
				System.out.println("couponCreateTimeStr" + couponCreateTimeStr);
				System.out.println("couponCreateTime" + couponCreateTime);

			} else {
				errorMsgs.add("請輸入創建時間");
			}
		} catch (ParseException e) {
			errorMsgs.add("請選擇正確的創建時間");
		}

		if (endTime != null && startTime != null && endTime.before(startTime) && startTime.before(couponCreateTime)) {
			errorMsgs.add("請確認時間順序");
		}

		CouponVO couponVO = new CouponVO();
		couponVO.setCouponId(couponId);
		couponVO.setCouponName(couponName);
		couponVO.setCouponCode(couponCode);
		couponVO.setStartTime(startTime);
		couponVO.setEndTime(endTime);
		couponVO.setMinSpendingAmount(minSpendingAmount);
		couponVO.setCouponQuantity(couponQuantity);
		couponVO.setMemberAllowQuantity(memberAllowQuantity);
		couponVO.setCouponDiscount(couponDiscount);
		couponVO.setCouponCreateTime(couponCreateTime);

		req.setAttribute("couponVO", couponVO);

		if (!errorMsgs.isEmpty()) {
			return ("/coupon/update_coupon_input.jsp");
		}

		// Update 使用值做更新(因為Service內部轉換)
		couponVO = couponSvc.updateCoupon(couponId, couponName, couponCode, startTime, endTime, minSpendingAmount,
				couponQuantity, memberAllowQuantity, couponDiscount, couponCreateTime);

		return "/coupon/listOneCoupon.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		String couponCode = req.getParameter("couponCode");
		if (couponCode == null) {
			errorMsgs.add("優惠券代碼不能為空");
		} else {
			couponCode = couponCode.trim();
			if (couponCode.isEmpty()) {
				errorMsgs.add("優惠券代碼請勿空白");
			} else if (couponCode.length() > 20) {
				errorMsgs.add("優惠券代碼長度不能超過20");
			} else if (!couponSvc.isCouponCodeUnique(couponCode, 0)) {
				errorMsgs.add("優惠券代碼重複");
			}
		}

		String couponName = req.getParameter("couponName");
		if (couponName == null || couponName.trim().length() == 0) {
			errorMsgs.add("優惠券名稱請勿空白");
		}

		Date startTime = null;
		try {
			String startTimeStr = req.getParameter("startTime");
			if (startTimeStr != null && !startTimeStr.trim().isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				startTime = dateFormat.parse(startTimeStr);
				System.out.println("startTimeStr" + startTimeStr);
				System.out.println("startTime" + startTime);

			} else {
				errorMsgs.add("請輸入開始時間");
			}
		} catch (ParseException e) {
			errorMsgs.add("請選擇正確的開始時間");
		}

		Date endTime = null;
		try {
			String endTimeStr = req.getParameter("endTime");
			if (endTimeStr != null && !endTimeStr.trim().isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				endTime = dateFormat.parse(endTimeStr);
				System.out.println("endTimeStr" + endTimeStr);
				System.out.println("endTime" + endTime);
			} else {
				errorMsgs.add("請輸入結束時間");
			}
		} catch (ParseException e) {
			errorMsgs.add("請選擇正確的結束時間");
		}

		Integer minSpendingAmount = null;
		try {
			String minSpendingAmountStr = req.getParameter("minSpendingAmount");
			if (minSpendingAmountStr != null && !minSpendingAmountStr.trim().isEmpty()) {
				minSpendingAmount = Integer.valueOf(minSpendingAmountStr);
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("最低消費金額請輸入數字");
		}

		Integer couponQuantity = null;
		try {
			String couponQuantityStr = req.getParameter("couponQuantity");
			if (couponQuantityStr != null && !couponQuantityStr.trim().isEmpty()) {
				couponQuantity = Integer.valueOf(couponQuantityStr);

				// 驗證 couponQuantity
				if (couponQuantity <= 0) {
					errorMsgs.add("優惠券數量應該大於0");
				}
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("優惠券數量請輸入數字");
		}

		Integer memberAllowQuantity = null;
		try {
			String memberAllowQuantityStr = req.getParameter("memberAllowQuantity");
			if (memberAllowQuantityStr != null && !memberAllowQuantityStr.trim().isEmpty()) {
				memberAllowQuantity = Integer.valueOf(memberAllowQuantityStr);

				// 驗證 memberAllowQuantity
				if (memberAllowQuantity <= 0) {
					errorMsgs.add("會員可使用數量應該大於0");
				}
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("會員可使用數量請輸入數字");
		}

		Integer couponDiscount = null;
		try {
			String couponDiscountStr = req.getParameter("couponDiscount");
			if (couponDiscountStr != null && !couponDiscountStr.trim().isEmpty()) {
				couponDiscount = Integer.valueOf(couponDiscountStr);

				// 驗證 couponDiscount
				if (couponDiscount < 0) {
					errorMsgs.add("優惠折扣應該大於等於0");
				}
			}
		} catch (NumberFormatException e) {
			errorMsgs.add("優惠折扣請輸入數字");
		}

		if (endTime != null && startTime != null && endTime.before(startTime)) {
			errorMsgs.add("請確認時間順序");
		}

		CouponVO couponVO = new CouponVO();
		couponVO.setCouponName(couponName);
		couponVO.setCouponCode(couponCode);
		couponVO.setStartTime(startTime);
		couponVO.setEndTime(endTime);
		couponVO.setMinSpendingAmount(minSpendingAmount);
		couponVO.setCouponQuantity(couponQuantity);
		couponVO.setMemberAllowQuantity(memberAllowQuantity);
		couponVO.setCouponDiscount(couponDiscount);

		req.setAttribute("couponVO", couponVO);
		System.out.println(couponVO);
		// couponCreateTime is automatically set to the current date in the database,9
		// isConfirm is automatically set to the current date in the database

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的empVO物件,也存入req
			return ("/coupon/addCoupon.jsp");
		}

		couponVO = couponSvc.addCoupon(couponName, couponCode, startTime, endTime, minSpendingAmount, couponQuantity,
				memberAllowQuantity, couponDiscount);
		req.setAttribute("couponVO", couponVO);

		return "/coupon/listOneCoupon.jsp";
	}

}
