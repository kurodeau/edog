package com.allenum;

public enum FieldErrorEnum {
	MISSING_REQUIRED_FIELD("請輸入此欄位", 0), INVALID_FORMAT("欄位格式不正確", 1), OUT_OF_RANGE("欄位值超出範圍", 2),
	DUPLICATE_VALUE("此名稱已被使用，請更換名稱", 3), UNKNOWN_ERROR("未知錯誤", 4),IMAGE_SIZE_EXCEEDED("圖片超過大小",5),IMAGE_MISSING("請上傳圖片",6);

	private String message;
	private Integer index;

	FieldErrorEnum(String message, Integer index) {
		this.message = message;
		this.index = index;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
