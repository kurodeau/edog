package com.allenum;

public enum ProductSortEnum {
	FOOD("食品", 0), TOYS("玩具", 1), BEDDING("寢具", 2), GROOMING("美容清潔", 3), CARRIERS("攜帶袋", 4), APPAREL("服飾", 5),
	HEALTH_CARE("健康保健", 6), ACCESSORIES("配件", 7);

	private String typename;
	private Integer index;
	
	public String getTypename() {
		return typename;
	}

	private ProductSortEnum(String typename, Integer index) {
		this.typename = typename;
		this.index = index;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}



}
