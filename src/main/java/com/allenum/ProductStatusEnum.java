package com.allenum;

public enum ProductStatusEnum {
    ENABLED("已上架"),
    DISABLED("未上架"),
    OUT_OF_STOCK("庫存不足");

	  private final String status;

	    ProductStatusEnum(String status) {
	        this.status = status;
	    }

	    public String getStatus() {
	        return status;
	    }

}
