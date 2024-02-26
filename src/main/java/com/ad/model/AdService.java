package com.ad.model;

import java.sql.Timestamp;
import java.util.List;

import com.seller.entity.SellerVO;

public class AdService {
	
	private AdDAO_interface dao;

	public AdService(){
		dao = new AdDAO(); //���p�G�ק��Ʈw , �u���ܧ�DAO
	}
	
	public AdVO addAd(AdVO adVOHB) {
				
		dao.insert(adVOHB);
		
		return adVOHB;
		
	}
	
	
//	public AdVO updateAd(Integer adId, Integer sellerId, byte[] adImg , Timestamp adImgUploadTime , String adName,
//			String adUrl , Timestamp adStartTime , Timestamp adEndTime , Integer adLv, String adMemo ,
//			Boolean isAdConfirm, Timestamp adCreateTime , Boolean isEnabled) {
//		
//		AdVO adVO = new AdVO();		
//		
//		adVO.setAdId(adId);
//		adVO.setSellerId(sellerId);
//		adVO.setAdImg(adImg);
//		adVO.setAdImgUploadTime(adImgUploadTime);
//		adVO.setAdName(adName);
//		adVO.setAdUrl(adUrl);
//		adVO.setAdStartTime(adStartTime);
//		adVO.setAdEndTime(adEndTime);
//		adVO.setAdLv(adLv);
//		adVO.setAdMemo(adMemo);
//		adVO.setIsAdConfirm(isAdConfirm);
//		adVO.setAdCreateTime(adCreateTime);
//		adVO.setIsEnabled(isEnabled);
//		dao.update(adVO);
//		
//		return adVO;
//		
//	}
//	
//	
//	public void deleteAd(Integer AdId) {
//		dao.delete(AdId);
//	}
//	
//	public AdVO getOneAd(Integer AdId) {
//	
//		return dao.findByPrimaryKey(AdId);
//		
//	}
//	
//	
	public List<AdVO> getAll(){
		return dao.getAll();
	}
	
	
}
	

