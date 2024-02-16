package com.buyer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.buyer.dao.BuyerDAO_interface;
import com.buyer.dao.BuyerHBDAO;
import com.buyer.entity.BuyerVO;

public class BuyerService {

	private BuyerDAO_interface dao;

	public BuyerService() {
		dao = new BuyerHBDAO();
	}

//	memberId int AI PK //這個不給改
//	memberEmail varchar(200) 
//	thirdFrom varchar(100) 
//	memberName varchar(100) 
//	memberPhone varchar(10) 
//	memberMobile varchar(20) 
//	memberBirthday date 
//	memberPassword varchar(100) 
//	memberAddress varchar(100) 
//	isMemberEmail tinyint(1) 
//	memberRegistrationTime datetime  //這個不給改 
//	petName varchar(100) 
//	petImg longblob 
//	petImgUploadTime datetime //這個要根據上圖時間自動改
//	petVaccName1 varchar(100) 
//	petVaccTime1 datetime 
//	petVaccName2 varchar(100) 
//	petVaccTime2 datetime 
//	isConfirm tinyint(1)
	
	public BuyerVO addBuyer(
			String memberEmail, 
			String thirdFrom, 
			String memberName,
	        String memberPhone, 
	        String memberMobile,
	        Date memberBirthday,
	        String memberPassword,
	        String memberAddress,
	        boolean isMemberEmail,
	        String petName,
	        byte[] petImg,
	        Date petImgUploadTime, //這個要根據上圖時間自動改
	        String petVaccName1,
	        Date petVaccTime1,
	        String petVaccName2,
	        Date petVaccTime2,
	        boolean isConfirm
			) {

		BuyerVO buyerVO = new BuyerVO();

		buyerVO.setMemberEmail(memberEmail);
		buyerVO.setThirdFrom(thirdFrom);
		buyerVO.setMemberName(memberName);
		buyerVO.setMemberPhone(memberPhone);
	    buyerVO.setSellerContact(sellerContact);
	    buyerVO.setSellerCompanyPhone(sellerCompanyPhone);
	    buyerVO.setSellerCompanyExtension(sellerCompanyExtension);
	    buyerVO.setSellerMobile(sellerMobile);
	    buyerVO.setSellerAddress(sellerAddress);
	    buyerVO.setSellerPassword(sellerPassword);
	    buyerVO.setSellerBankAccount(sellerBankAccount);
	    buyerVO.setSellerBankCode(sellerBankCode);
	    buyerVO.setSellerBankAccountNumber(sellerBankAccountNumber);
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


