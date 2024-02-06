package com.seller.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.seller.dao.SellerDAO;
import com.seller.dao.SellerHBDAO;
import com.seller.entity.SellerVO;
import com.sellerLv.dao.SellerLvDAO;
import com.sellerLv.dao.SellerLvHBDAO;
import com.sellerLv.entity.SellerLvVO;




public class SellerService {


	private SellerDAO dao;
	private SellerLvDAO sellerLvdao;

	public SellerService() {
		
		dao = new SellerHBDAO();
		sellerLvdao = new SellerLvHBDAO();

	}

	public SellerVO addSeller(String sellerEmail, String sellerCompany, String sellerTaxId,
	        Integer sellerCapital, String sellerContact, String sellerCompanyPhone,
	        String sellerCompanyExtension, String sellerMobile, String sellerAddress,
	        String sellerPassword, String sellerBankAccount, String sellerBankCode,
	        String sellerBankAccountNumber) {

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
	    // sellerCreateTime 使用預設值，不需要在此設定
	    // 預設 isConfirm 為 FALSE，不需在此設定

	    dao.insert(sellerVO);

	    return sellerVO;
	}


	public SellerVO updateSeller(Integer sellerId, Integer sellerLvId, String sellerEmail, String sellerCompany,
	        String sellerTaxId, Integer sellerCapital, String sellerContact,
	        String sellerCompanyPhone, String sellerCompanyExtension, String sellerMobile,
	        String sellerAddress, String sellerPassword, String sellerBankAccount,
	        String sellerBankCode, String sellerBankAccountNumber, Date sellerCreateTime,
	        Boolean isConfirm) {

	    SellerVO sellerVO = new SellerVO();
	
	    sellerVO.setSellerId(sellerId);
	    

	    SellerLvVO sellerLvVO = sellerLvdao.findByPrimaryKey(sellerLvId);
	    sellerVO.setSellerLvId(sellerLvVO);
	    
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

	    dao.update(sellerVO);

	    return sellerVO;
	}


	public void deleteSeller(Integer sellerId) {
		dao.delete(sellerId);
	}

	public SellerVO getOneSeller(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<SellerVO> getAll() {
		return dao.getAll();
	}
	
	public int getTotal() {
		return dao.getTotal();
	}
	
	public List<SellerVO> getSellersByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
			
		
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}
		
		
		return dao.getByCompositeQuery(query);
	}

}


