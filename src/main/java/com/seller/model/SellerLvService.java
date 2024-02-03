package com.seller.model;

import java.util.List;

public class SellerLvService {

	private SellerDAO_interface dao;

	public SellerLvService() {
		
		dao = new SellerDAO();
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
	    // 預設 isConfirm 為 FALSE，不需要在此設定

	    dao.insert(sellerVO);

	    return sellerVO;
	}


	public SellerVO updateSeller(Integer sellerId, Integer sellerLvId, String sellerEmail, String sellerCompany,
	        String sellerTaxId, Integer sellerCapital, String sellerContact,
	        String sellerCompanyPhone, String sellerCompanyExtension, String sellerMobile,
	        String sellerAddress, String sellerPassword, String sellerBankAccount,
	        String sellerBankCode, String sellerBankAccountNumber, Boolean isConfirm) {

	    SellerVO sellerVO = new SellerVO();
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
