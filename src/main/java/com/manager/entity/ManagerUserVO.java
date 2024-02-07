package com.manager.entity;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="managerUser")
public class ManagerUserVO implements java.io.Serializable{
	@Id
//	@Column(name="")
	private Integer managerId;
	private String managername;
	private String managerPassword;
	private Integer managerPer;
	private Date createtime;
	
	public ManagerUserVO() {};
	
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public Integer getManagerPer() {
		return managerPer;
	}
	public void setManagerPer(Integer managerPer) {
		this.managerPer = managerPer;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
