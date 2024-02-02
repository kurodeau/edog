package com.remittance.model;

import java.util.*;


public interface RemittanceDAO_interface {
	   public void insert(RemittanceVO remittanceVO);
       public void update(RemittanceVO remittanceVO);
       public void delete(Integer remittanceId);
       public RemittanceVO findByPrimaryKey(Integer remittanceId);
       public List<RemittanceVO> getAll();
}
