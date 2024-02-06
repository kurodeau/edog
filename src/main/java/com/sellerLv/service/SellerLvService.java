package com.sellerLv.service;


import java.sql.Date;
import java.util.List;

import com.seller.entity.__SellerVO;
import com.sellerLv.dao.SellerLvDAO;
import com.sellerLv.dao.SellerLvHBDAO;

public class SellerLvService {


	private SellerLvDAO dao;
	
	public SellerLvService() {
		
		dao = new SellerLvHBDAO();
	}

	public SellerLvVO addSeller(String sellerEmail, String sellerCompany, String sellerTaxId,
	        Integer sellerCapital, String sellerContact, String sellerCompanyPhone,
	        String sellerCompanyExtension, String sellerMobile, String sellerAddress,
	        String sellerPassword, String sellerBankAccount, String sellerBankCode,
	        String sellerBankAccountNumber) {

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
	    // sellerCreateTime 使用預設值，不需要在此設定
	    // 預設 isConfirm 為 FALSE，不需在此設定

	    dao.insert(sellerVO);

	    return sellerVO;
	}


	public SellerLvVO updateSeller(Integer sellerId, Integer sellerLvId, String sellerEmail, String sellerCompany,
	        String sellerTaxId, Integer sellerCapital, String sellerContact,
	        String sellerCompanyPhone, String sellerCompanyExtension, String sellerMobile,
	        String sellerAddress, String sellerPassword, String sellerBankAccount,
	        String sellerBankCode, String sellerBankAccountNumber, Date sellerCreateTime,
	        Boolean isConfirm) {

	    SellerLvVO sellerVO = new __SellerVO();
	
	    sellerVO.setSellerId(sellerId);
	    sellerVO.setSellerLvId(sellerLvId);
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


	public void deleteSeller(Integer empno) {
		dao.delete(empno);
	}

	public __SellerVO getOneSeller(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<__SellerVO> getAll() {
		return dao.getAll();
	}
}


