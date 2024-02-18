package com.allenum;

public enum OrderStatusEnum {
	CANCELLED(0) , COMPLETED(1), CREATED(2) , PROCESSING(3), CLOSED(4);
	private Integer status;

	private OrderStatusEnum(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
