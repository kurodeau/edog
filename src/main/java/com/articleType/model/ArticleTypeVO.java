package com.articleType.model;

public class ArticleTypeVO implements java.io.Serializable{
	private Integer articleTypeId;
	private String articleTypeName;
	public Integer getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

}
