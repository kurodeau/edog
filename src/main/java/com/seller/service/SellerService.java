package com.seller.service;

import java.sql.Date;
import java.util.List;

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


	public void deleteSeller(Integer empno) {
		dao.delete(empno);
	}

	public SellerVO getOneSeller(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<SellerVO> getAll() {
		return dao.getAll();
	}
}


