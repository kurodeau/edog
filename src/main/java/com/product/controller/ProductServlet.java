package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.allenum.FieldErrorEnum;
import com.allenum.ProductStatusEnum;
import com.google.gson.Gson;
import com.product.entity.ProductVO;
import com.product.service.ProductService;

@WebServlet("/product/product.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 限制上傳檔案的最大大小 (此例為 5 MB)
public class ProductServlet extends HttpServlet {

	private HashMap<String, String> errorMsgs = null;

	private ProductService productSvc;

	@Override
	public void init() throws ServletException {
//		productSvc = new ProductService();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		errorMsgs = new HashMap<String, String>();

		req.setCharacterEncoding("UTF-8");
		req.setAttribute("errorMsgs", errorMsgs);

		String action = part2String(req.getPart("action"));
		String forwardPath = "";

		switch (action) {

		case "check":

			Boolean isValid = checkFormFields(req, res);
			System.out.println(isValid);

			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			Gson gson = new Gson();

			if (!isValid) {
				FieldResponse errResp = new FieldResponse("fail", errorMsgs);
				String errorJson = gson.toJson(errResp);

				System.out.println(errorJson);
				res.getWriter().write(errorJson);
				return;
			} else {
				FieldResponse sucResp = new FieldResponse("success", null);
				String successJson = gson.toJson(sucResp);
				System.out.println(successJson);

				res.getWriter().write(successJson);
				return;

			}
		case "compositeQuery":
			forwardPath = getCompositeProductsQuery(req, res);
			break;

//		case "update":
//			forwardPath = update(req, res);
//			break;
//
//		case "getOne_For_Update":
//			forwardPath = getOne_For_Update(req, res);
//			break;
//
//		case "insert":
//			forwardPath = insert(req, res);
//			break;

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

	private Boolean checkFormFields(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		Part mainImagePart = req.getPart("mainImage");
		
		if (mainImagePart == null || mainImagePart.getSize() == 0) {
			errorMsgs.put("mainImage", FieldErrorEnum.IMAGE_MISSING.getMessage());
		} else if (mainImagePart.getSize() > ProductVO.MAX_IMAGE_SIZE) {
			errorMsgs.put("mainImage", FieldErrorEnum.IMAGE_SIZE_EXCEEDED.getMessage() + "10MB");
		}

		
		List<Part> subImageParts = req.getParts().stream()
		        .filter(part -> part.getName().startsWith("subImages"))
		        .collect(Collectors.toList());

		if (subImageParts.isEmpty()) {
		    errorMsgs.put("subImages", FieldErrorEnum.IMAGE_MISSING.getMessage());
		} else {
		    for (Part subImagePart : subImageParts) {
		        String subImageName = subImagePart.getSubmittedFileName();
		        if (subImagePart.getSize() > ProductVO.MAX_IMAGE_SIZE) {
		            errorMsgs.put("subimages", FieldErrorEnum.IMAGE_SIZE_EXCEEDED.getMessage() + "10MB");
		        }
		    }
		}

		String productName = part2String(req.getPart("productName"));
		if (productName == null || productName.trim().isEmpty()) {
			errorMsgs.put("productName", FieldErrorEnum.MISSING_REQUIRED_FIELD.getMessage());
		} else if (productName.length() > 100) {
			errorMsgs.put("productName", FieldErrorEnum.OUT_OF_RANGE.getMessage());
		}

		String productSortStr = part2String(req.getPart("productSort"));
		Integer productSort = null;
		if (productSortStr == null || productSortStr.trim().isEmpty()) {
			errorMsgs.put("productSort", FieldErrorEnum.MISSING_REQUIRED_FIELD.getMessage());
		} else {
			try {

				productSort = Integer.parseInt(productSortStr);
				if (productSort < 0 || productSort > ProductVO.MAX_PRODUCT_SORT) {
					errorMsgs.put("productSort", FieldErrorEnum.OUT_OF_RANGE.getMessage());
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("productSort", FieldErrorEnum.INVALID_FORMAT.getMessage());
			}
		}

		String productStockQuantityStr = part2String(req.getPart("productStockQuantity"));
		Integer productStockQuantity = null;
		try {
			if (productStockQuantityStr != null && !productStockQuantityStr.trim().isEmpty()) {
				productStockQuantity = Integer.valueOf(productStockQuantityStr);

				// 驗證 productStockQuantity 的範圍
				if (productStockQuantity < 0) {
					errorMsgs.put("productStockQuantity", FieldErrorEnum.OUT_OF_RANGE.getMessage());
				}
			} else {
				errorMsgs.put("productStockQuantity", FieldErrorEnum.MISSING_REQUIRED_FIELD.getMessage());
			}
		} catch (NumberFormatException e) {
			errorMsgs.put("productStockQuantity", FieldErrorEnum.INVALID_FORMAT.getMessage());
		}

		String productPriceStr = part2String(req.getPart("productPrice"));
		BigDecimal productPrice = null;
		try {
			if (productPriceStr != null && !productPriceStr.trim().isEmpty()) {
				productPrice = new BigDecimal(productPriceStr);

				// 驗證 productPrice 的範圍
				if (productPrice.compareTo(BigDecimal.ZERO) < 0) {
					errorMsgs.put("productPrice", FieldErrorEnum.OUT_OF_RANGE.getMessage());
				}
			} else {
				errorMsgs.put("productPrice", FieldErrorEnum.MISSING_REQUIRED_FIELD.getMessage());
			}
		} catch (NumberFormatException e) {
			errorMsgs.put("productPrice", FieldErrorEnum.INVALID_FORMAT.getMessage());
		}

		String productDetails = part2String(req.getPart("productDetails"));
		if (productDetails == null || productDetails.trim().isEmpty()) {
			errorMsgs.put("productDetails", FieldErrorEnum.MISSING_REQUIRED_FIELD.getMessage());

		} else if (productDetails.length() > 100) {
			errorMsgs.put("productDetails", FieldErrorEnum.OUT_OF_RANGE.getMessage());

		}

		errorMsgs.forEach((field, error) -> System.out.println("Field: " + field + ", Error: " + error));

		return errorMsgs.size() > 0 ? false : true;
	}

	private String getOne_For_Display(HttpServletRequest req, HttpServletResponse res) {
		Integer productId = Integer.parseInt(req.getParameter("productId"));
		ProductVO productVO = productSvc.getOneProduct(productId);
		req.setAttribute("productVO", productVO);

		return "/product/listOneProduct.jsp";

	}

	private String getCompositeProductsQuery(HttpServletRequest req, HttpServletResponse res) {
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
			List<ProductVO> list = productSvc.getProductsByCompositeQuery(map);
			req.setAttribute("list", list);
		} else {
			return "product/index.jsp";
		}
		return "/product/listCompositeQueryProducts.jsp";
	}

	private String getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<ProductVO> list = productSvc.getAll();

		if (req.getSession().getAttribute("productPageQty") == null) {
			int productPageQty = productSvc.getTotal();
			req.getSession().setAttribute("productPageQty", productPageQty);
		}

		req.setAttribute("list", list);
		req.setAttribute("currentPage", currentPage);

		return "/product/listAllProduct.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer productId = Integer.valueOf(req.getParameter("productId"));
		ProductVO productVO = productSvc.getOneProduct(productId);

		req.setAttribute("productVO", productVO);
		return "/product/update_product_input.jsp";
	}

//	private String update(HttpServletRequest req, HttpServletResponse res) {
//		/*************************** 1.接收請求參數 ****************************************/
//		Integer productId = Integer.valueOf(req.getParameter("productId"));
//
//		String productCode = req.getParameter("productCode");
//		if (productCode == null) {
//			errorMsgs.add("優惠券代碼不能為空");
//		} else {
//			productCode = productCode.trim();
//			if (productCode.isEmpty()) {
//				errorMsgs.add("優惠券代碼請勿空白");
//			} else if (productCode.length() > 20) {
//				errorMsgs.add("優惠券代碼長度不能超過20");
//			} else if (!productSvc.isProductCodeUnique(productCode, productId)) {
//				errorMsgs.add("優惠券代碼重複");
//			}
//		}
//
//		String productName = req.getParameter("productName");
//		if (productName == null || productName.trim().length() == 0) {
//			errorMsgs.add("優惠券名稱請勿空白");
//		}
//
//		Date startTime = null;
//		try {
//			String startTimeStr = req.getParameter("startTime");
//			if (startTimeStr != null && !startTimeStr.trim().isEmpty()) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//				startTime = dateFormat.parse(startTimeStr);
//				System.out.println("startTimeStr" + startTimeStr);
//				System.out.println("startTime" + startTime);
//
//			} else {
//				errorMsgs.add("請輸入開始時間");
//			}
//		} catch (ParseException e) {
//			errorMsgs.add("請選擇正確的開始時間");
//		}
//
//		Date endTime = null;
//		try {
//			String endTimeStr = req.getParameter("endTime");
//			if (endTimeStr != null && !endTimeStr.trim().isEmpty()) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//				endTime = dateFormat.parse(endTimeStr);
//				System.out.println("endTimeStr" + endTimeStr);
//				System.out.println("endTime" + endTime);
//			} else {
//				errorMsgs.add("請輸入結束時間");
//			}
//		} catch (ParseException e) {
//			errorMsgs.add("請選擇正確的結束時間");
//		}
//
//		Integer minSpendingAmount = null;
//		try {
//			String minSpendingAmountStr = req.getParameter("minSpendingAmount");
//			if (minSpendingAmountStr != null && !minSpendingAmountStr.trim().isEmpty()) {
//				minSpendingAmount = Integer.valueOf(minSpendingAmountStr);
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("最低消費金額請輸入數字");
//		}
//
//		Integer productQuantity = null;
//		try {
//			String productQuantityStr = req.getParameter("productQuantity");
//			if (productQuantityStr != null && !productQuantityStr.trim().isEmpty()) {
//				productQuantity = Integer.valueOf(productQuantityStr);
//
//				// 驗證 productQuantity
//				if (productQuantity <= 0) {
//					errorMsgs.add("優惠券數量應該大於0");
//				}
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("優惠券數量請輸入數字");
//		}
//
//		Integer memberAllowQuantity = null;
//		try {
//			String memberAllowQuantityStr = req.getParameter("memberAllowQuantity");
//			if (memberAllowQuantityStr != null && !memberAllowQuantityStr.trim().isEmpty()) {
//				memberAllowQuantity = Integer.valueOf(memberAllowQuantityStr);
//
//				// 驗證 memberAllowQuantity
//				if (memberAllowQuantity <= 0) {
//					errorMsgs.add("會員可使用數量應該大於0");
//				}
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("會員可使用數量請輸入數字");
//		}
//
//		Integer productDiscount = null;
//		try {
//			String productDiscountStr = req.getParameter("productDiscount");
//			if (productDiscountStr != null && !productDiscountStr.trim().isEmpty()) {
//				productDiscount = Integer.valueOf(productDiscountStr);
//
//				// 驗證 productDiscount
//				if (productDiscount < 0) {
//					errorMsgs.add("優惠折扣應該大於等於0");
//				}
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("優惠折扣請輸入數字");
//		}
//
//		Date productCreateTime = null;
//		try {
//			String productCreateTimeStr = req.getParameter("productCreateTime");
//			if (productCreateTimeStr != null && !productCreateTimeStr.trim().isEmpty()) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//				productCreateTime = dateFormat.parse(productCreateTimeStr);
//				System.out.println("productCreateTimeStr" + productCreateTimeStr);
//				System.out.println("productCreateTime" + productCreateTime);
//
//			} else {
//				errorMsgs.add("請輸入創建時間");
//			}
//		} catch (ParseException e) {
//			errorMsgs.add("請選擇正確的創建時間");
//		}
//
//		if (endTime.before(startTime) && startTime.before(productCreateTime)) {
//			errorMsgs.add("請確認時間順序");
//		}
//
//		ProductVO productVO = new ProductVO();
//		productVO.setProductId(productId);
//		productVO.setProductName(productName);
//		productVO.setProductCode(productCode);
//		productVO.setStartTime(startTime);
//		productVO.setEndTime(endTime);
//		productVO.setMinSpendingAmount(minSpendingAmount);
//		productVO.setProductQuantity(productQuantity);
//		productVO.setMemberAllowQuantity(memberAllowQuantity);
//		productVO.setProductDiscount(productDiscount);
//		productVO.setProductCreateTime(productCreateTime);
//
//		req.setAttribute("productVO", productVO);
//
//		if (!errorMsgs.isEmpty()) {
//			return ("/product/update_product_input.jsp");
//		}
//
//		// Update 使用值做更新(因為Service內部轉換)
//		productVO = productSvc.updateProduct(productId, productName, productCode, startTime, endTime, minSpendingAmount,
//				productQuantity, memberAllowQuantity, productDiscount, productCreateTime);
//
//		return "/product/listOneProduct.jsp";
//	}
//
//	private String insert(HttpServletRequest req, HttpServletResponse res) {
//		String productCode = req.getParameter("productCode");
//		if (productCode == null) {
//			errorMsgs.add("優惠券代碼不能為空");
//		} else {
//			productCode = productCode.trim();
//			if (productCode.isEmpty()) {
//				errorMsgs.add("優惠券代碼請勿空白");
//			} else if (productCode.length() > 20) {
//				errorMsgs.add("優惠券代碼長度不能超過20");
//			} else if (!productSvc.isProductCodeUnique(productCode, 0)) {
//				errorMsgs.add("優惠券代碼重複");
//			}
//		}
//
//		String productName = req.getParameter("productName");
//		if (productName == null || productName.trim().length() == 0) {
//			errorMsgs.add("優惠券名稱請勿空白");
//		}
//
//		Date startTime = null;
//		try {
//			String startTimeStr = req.getParameter("startTime");
//			if (startTimeStr != null && !startTimeStr.trim().isEmpty()) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//				startTime = dateFormat.parse(startTimeStr);
//				System.out.println("startTimeStr" + startTimeStr);
//				System.out.println("startTime" + startTime);
//
//			} else {
//				errorMsgs.add("請輸入開始時間");
//			}
//		} catch (ParseException e) {
//			errorMsgs.add("請選擇正確的開始時間");
//		}
//
//		Date endTime = null;
//		try {
//			String endTimeStr = req.getParameter("endTime");
//			if (endTimeStr != null && !endTimeStr.trim().isEmpty()) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//				endTime = dateFormat.parse(endTimeStr);
//				System.out.println("endTimeStr" + endTimeStr);
//				System.out.println("endTime" + endTime);
//			} else {
//				errorMsgs.add("請輸入結束時間");
//			}
//		} catch (ParseException e) {
//			errorMsgs.add("請選擇正確的結束時間");
//		}
//
//		Integer minSpendingAmount = null;
//		try {
//			String minSpendingAmountStr = req.getParameter("minSpendingAmount");
//			if (minSpendingAmountStr != null && !minSpendingAmountStr.trim().isEmpty()) {
//				minSpendingAmount = Integer.valueOf(minSpendingAmountStr);
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("最低消費金額請輸入數字");
//		}
//
//		Integer productQuantity = null;
//		try {
//			String productQuantityStr = req.getParameter("productQuantity");
//			if (productQuantityStr != null && !productQuantityStr.trim().isEmpty()) {
//				productQuantity = Integer.valueOf(productQuantityStr);
//
//				// 驗證 productQuantity
//				if (productQuantity <= 0) {
//					errorMsgs.add("優惠券數量應該大於0");
//				}
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("優惠券數量請輸入數字");
//		}

//
//		Integer memberAllowQuantity = null;
//		try {
//			String memberAllowQuantityStr = req.getParameter("memberAllowQuantity");
//			if (memberAllowQuantityStr != null && !memberAllowQuantityStr.trim().isEmpty()) {
//				memberAllowQuantity = Integer.valueOf(memberAllowQuantityStr);
//
//				// 驗證 memberAllowQuantity
//				if (memberAllowQuantity <= 0) {
//					errorMsgs.add("會員可使用數量應該大於0");
//				}
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("會員可使用數量請輸入數字");
//		}
//
//		Integer productDiscount = null;
//		try {
//			String productDiscountStr = req.getParameter("productDiscount");
//			if (productDiscountStr != null && !productDiscountStr.trim().isEmpty()) {
//				productDiscount = Integer.valueOf(productDiscountStr);
//
//				// 驗證 productDiscount
//				if (productDiscount < 0) {
//					errorMsgs.add("優惠折扣應該大於等於0");
//				}
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("優惠折扣請輸入數字");
//		}
//
//		if (endTime != null && startTime != null && endTime.before(startTime)) {
//			errorMsgs.add("請確認時間順序");
//		}
//
//		ProductVO productVO = new ProductVO();
//		productVO.setProductName(productName);
//		productVO.setProductCode(productCode);
//		productVO.setStartTime(startTime);
//		productVO.setEndTime(endTime);
//		productVO.setMinSpendingAmount(minSpendingAmount);
//		productVO.setProductQuantity(productQuantity);
//		productVO.setMemberAllowQuantity(memberAllowQuantity);
//		productVO.setProductDiscount(productDiscount);
//
//		req.setAttribute("productVO", productVO);
//		System.out.println(productVO);
//		// productCreateTime is automatically set to the current date in the database,9
//		// isConfirm is automatically set to the current date in the database
//
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
//			return ("/product/addProduct.jsp");
//		}
//
//		productVO = productSvc.addProduct(productName, productCode, startTime, endTime, minSpendingAmount,
//				productQuantity, memberAllowQuantity, productDiscount);
//		req.setAttribute("productVO", productVO);
//
//		return "/product/listOneProduct.jsp";
//	}

	public static String part2String(Part actionPart) throws IOException {

		if (actionPart == null) {
			System.out.println("isNull : MaybeYou Type in wrong name");
			return "";
		}

		InputStream in = null;
		try {

			in = actionPart.getInputStream();
			if (in.available() == 0) {
				return "";
			}

			byte[] textBytes = in.readAllBytes();
			return new String(textBytes, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	private ProductVO inputDefaultFields(ProductVO productVO) {
		productVO.setIsEnabled(false);
		productVO.setTotalStars(0);
		productVO.setTotalReviews(0);
		productVO.setProductStatus(ProductStatusEnum.ENABLED.getStatus());
		return productVO;
	}

	class FieldResponse {
		private String status;
		private Map<String, String> desp;

		public FieldResponse(String status, Map<String, String> desp) {
			super();
			this.status = status;
			if (desp == null) {
				Map<String, String> success = new HashMap<>();
				success.put("noerror", "ok");
				this.desp = success;
			} else {
				this.desp = desp;
			}

		}
	}

}
