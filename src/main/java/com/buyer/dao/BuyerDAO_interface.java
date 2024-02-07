package com.buyer.dao;

import java.sql.Date;
import java.util.*;

import com.buyer.entity.BuyerVO;

public interface BuyerDAO_interface {
          public Integer insert(BuyerVO buyerVO);
          public Integer update(BuyerVO buyerVO);
          public Integer delete(Integer managerId);
          public BuyerVO findByPrimaryKey(Integer memberId);
          public List<BuyerVO> getAll();
          public Integer getTotal();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<BuyerVO> getAll(Map<String, String[]> map); 
}

//private Integer memberId;
//private String memberEmail;
//private String thirdFrom;
//private String memberName;
//private String memberPhone;
//private String mobile;
//private Date memberBirthday;
//private String memberPassword;
//private String memberAddress;
//private Boolean isMemberEmail;
//private Date memberRegistrationTime;
//private String petName;
//private String petVaccName1;
//private Date petVaccTime1;
//private String petVaccName2;
//private Date petVaccTime2;
//private byte[] petImg;
//private Date petImgUploadTime;
// private Boolean isConfirm;