package com.seller.service;

import java.sql.Date;
import java.util.List;

import com.seller.dao.*;
import com.seller.entity.*;

public class SellerService {


	private SellerDAO dao;

	public SellerService() {
		
		dao = new SellerDSDAO();
	}

	public __SellerVO addSeller(String sellerEmail, String sellerCompany, String sellerTaxId,
	        Integer sellerCapital, String sellerContact, String sellerCompanyPhone,
	        String sellerCompanyExtension, String sellerMobile, String sellerAddress,
	        String sellerPassword, String sellerBankAccount, String sellerBankCode,
	        String sellerBankAccountNumber) {

	    __SellerVO sellerVO = new __SellerVO();

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


	public __SellerVO updateSeller(Integer sellerId, Integer sellerLvId, String sellerEmail, String sellerCompany,
	        String sellerTaxId, Integer sellerCapital, String sellerContact,
	        String sellerCompanyPhone, String sellerCompanyExtension, String sellerMobile,
	        String sellerAddress, String sellerPassword, String sellerBankAccount,
	        String sellerBankCode, String sellerBankAccountNumber, Date sellerCreateTime,
	        Boolean isConfirm) {

	    __SellerVO sellerVO = new __SellerVO();
	
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


