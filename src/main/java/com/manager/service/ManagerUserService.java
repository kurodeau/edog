package com.manager.service;

import java.util.List;

import com.manager.entity.ManagerUserVO;
import com.manager.model.ManagerUserDAO_interface;
import com.manager.model.ManagerUserJDBCDAO;

public class ManagerUserService {

	private ManagerUserDAO_interface dao;

	public ManagerUserService() {
		dao = new ManagerUserJDBCDAO();
	}

	public ManagerUserVO addManagerUser(
			String managername, 
			String managerPaassword, 
			Integer managerPer 
			) 
	{
		ManagerUserVO managerUserVO = new ManagerUserVO();

		managerUserVO.setManagername(managername);
		managerUserVO.setManagerPassword(managerPaassword);
		managerUserVO.setManagerPer(managerPer);
		dao.insert(managerUserVO);

		return managerUserVO;
	}

	public ManagerUserVO updateManagerUser(
			String managername, 
			String managerPaassword, 
			Integer managerPer,
			java.sql.Date createtime,
			Integer managerId
			)
	{

		ManagerUserVO managerUserVO = new ManagerUserVO();

		managerUserVO.setManagername(managername);
		managerUserVO.setManagerPassword(managerPaassword);
		managerUserVO.setManagerPer(managerPer);
		managerUserVO.setCreatetime(createtime);
		managerUserVO.setManagerId(managerId);
		dao.update(managerUserVO);

		return managerUserVO;
	}

	public void deleteManagerUser(Integer managerId) {
		dao.delete(managerId);
	}

	public ManagerUserVO getOneManagerUser(Integer managerId) {
		return dao.findByPrimaryKey(managerId);
	}

	public List<ManagerUserVO> getAll() {
		return dao.getAll();
	}
}
