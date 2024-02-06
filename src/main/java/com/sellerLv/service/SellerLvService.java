package com.sellerLv.service;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.seller.entity.SellerVO;
import com.sellerLv.dao.SellerLvDAO;
import com.sellerLv.dao.SellerLvHBDAO;
import com.sellerLv.entity.SellerLvVO;

public class SellerLvService {


	private SellerLvDAO dao;
	
	public SellerLvService() {
		
		dao = new SellerLvHBDAO();
	}

	public SellerLvVO addSellerLv(String lvName, BigDecimal platformCommission, Integer adAllowType,
	        Boolean isExportGoldflow, Integer freightSub, Integer returnSubPerMonth,
	        Boolean isShowPriority, Integer shelvesNumber) {

	    SellerLvVO sellerLvVO = new SellerLvVO();

	    sellerLvVO.setLvName(lvName);
	    sellerLvVO.setPlatformCommission(platformCommission);
	    sellerLvVO.setAdAllowType(adAllowType);
	    sellerLvVO.setIsExportGoldflow(isExportGoldflow);
	    sellerLvVO.setFreightSub(freightSub);
	    sellerLvVO.setReturnSubPerMonth(returnSubPerMonth);
	    sellerLvVO.setIsShowPriority(isShowPriority);
	    sellerLvVO.setShelvesNumber(shelvesNumber);

	    // 不需要以下內容 (除非你需要初始值)
//	    Set<SellerVO> sellers = new HashSet<>();
//	    sellerLvVO.setSellers(sellers);
	   

	    dao.insert(sellerLvVO);

	    return sellerLvVO;
	}



	public SellerLvVO updateSellerLv(Integer sellerLvId, String lvName, BigDecimal platformCommission, Integer adAllowType,
	        Boolean isExportGoldflow, Integer freightSub, Integer returnSubPerMonth,
	        Boolean isShowPriority, Integer shelvesNumber) {

	    SellerLvVO sellerLvVO = new SellerLvVO();
	    sellerLvVO.setSellerLvId(sellerLvId);
	    sellerLvVO.setLvName(lvName);
	    sellerLvVO.setPlatformCommission(platformCommission);
	    sellerLvVO.setAdAllowType(adAllowType);
	    sellerLvVO.setIsExportGoldflow(isExportGoldflow);
	    sellerLvVO.setFreightSub(freightSub);
	    sellerLvVO.setReturnSubPerMonth(returnSubPerMonth);
	    sellerLvVO.setIsShowPriority(isShowPriority);
	    sellerLvVO.setShelvesNumber(shelvesNumber);

	    dao.update(sellerLvVO);

	    return sellerLvVO;
	}


	public void deleteSellerLv(Integer id) {
		dao.delete(id);
	}

	public SellerLvVO getOneSellerLv(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public List<SellerLvVO> getAll() {
		return dao.getAll();
	}
	
	public Integer getTotal() {
		return dao.getTotal();
	}
	

}


