package com.manager.model;

import java.sql.Date;
import java.util.*;

import com.manager.entity.ManagerUserVO;

public interface ManagerUserDAO_interface {
          public void insert(ManagerUserVO managerUserVO);
          public void update(ManagerUserVO managerUserVO);
          public void delete(Integer managerId);
          public ManagerUserVO findByPrimaryKey(Integer managerId);
          public List<ManagerUserVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<ManagerUserVO> getAll(Map<String, String[]> map); 
}

//private Integer managerId;
//private String managername;
//private String managerPassword;
//private Integer managerPer;
//private Date createtime;