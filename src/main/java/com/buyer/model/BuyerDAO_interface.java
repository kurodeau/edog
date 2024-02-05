package com.buyer.model;

import java.sql.Date;
import java.util.*;

public interface BuyerDAO_interface {
          public void insert(BuyerVO buyerVO);
          public void update(BuyerVO buyerVO);
          public void delete(Integer managerId);
          public BuyerVO findByPrimaryKey(Integer memberId);
          public List<BuyerVO> getAll();
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