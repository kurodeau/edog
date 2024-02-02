package com.remittance.model;

import java.sql.Timestamp;
import java.util.List;

public class RemittanceService {
	
	private RemittanceDAO_interface dao;
	
	public RemittanceService() {
		dao = new RemittanceDAO(); 
	}
	
	public RemittanceVO addRemittance(Integer sellerId, Timestamp remittanceEstimatedTime,
			Timestamp remittanceTime, Timestamp settlementTime, Integer turnover, Integer handlingFee,
			Integer remittanceAmount, Integer remittanceStatus, String explanation) {
		
		RemittanceVO remittanceVO = new RemittanceVO();
		

		remittanceVO.setSellerId(sellerId);
		remittanceVO.setRemittanceEstimatedTime(remittanceEstimatedTime);
		remittanceVO.setRemittanceTime(remittanceTime);
		remittanceVO.setSettlementTime(settlementTime);
		remittanceVO.setTurnover(turnover);
		remittanceVO.setHandlingFee(handlingFee);
		remittanceVO.setRemittanceAmount(remittanceAmount);
		remittanceVO.setRemittanceStatus(remittanceStatus);
		remittanceVO.setExplanation(explanation);
		dao.insert(remittanceVO);
		
		return remittanceVO;	
	}
	
	
	public RemittanceVO updateRemittance(Integer remittanceId, Integer sellerId, Timestamp remittanceEstimatedTime,
		Timestamp remittanceTime, Timestamp settlementTime, Integer turnover, Integer handlingFee,
		Integer remittanceAmount, Integer remittanceStatus, String explanation) {
		
		RemittanceVO remittanceVO = new RemittanceVO();
		
		remittanceVO.setRemittanceId(remittanceId);
		remittanceVO.setSellerId(sellerId);
		remittanceVO.setRemittanceEstimatedTime(remittanceEstimatedTime);
		remittanceVO.setRemittanceTime(remittanceTime);
		remittanceVO.setSettlementTime(settlementTime);
		remittanceVO.setTurnover(turnover);
		remittanceVO.setHandlingFee(handlingFee);
		remittanceVO.setRemittanceAmount(remittanceAmount);
		remittanceVO.setRemittanceStatus(remittanceStatus);
		remittanceVO.setExplanation(explanation);
		dao.update(remittanceVO);
		
		return remittanceVO;
	}
	
	public void deleteRemittance(Integer remittanceId) {
		dao.delete(remittanceId);
	}
	
    public RemittanceVO getOneRemittance(Integer remittanceId) {
    	return dao.findByPrimaryKey(remittanceId);
    }
    
    public List<RemittanceVO> getAll(){
    	return dao.getAll();
    }

}
