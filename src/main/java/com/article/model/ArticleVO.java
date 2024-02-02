package com.article.model;
import java.sql.Timestamp;

public class ArticleVO implements java.io.Serializable{
	private Integer articleId;
	private Integer memberId;
	private String articleTitle;
	private String articleContent;
	private Timestamp artUpdateTime;
	private Integer articleLike;
	private Integer articleComment;
	private Integer articleShare;
	private Integer articleSort;
	private Boolean isEnabled;
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public Timestamp getArtUpdateTime() {
		return artUpdateTime;
	}
	public void setArtUpdateTime(Timestamp artUpdateTime) {
		this.artUpdateTime = artUpdateTime;
	}
	public Integer getArticleLike() {
		return articleLike;
	}
	public void setArticleLike(Integer articleLike) {
		this.articleLike = articleLike;
	}
	public Integer getArticleComment() {
		return articleComment;
	}
	public void setArticleComment(Integer articleComment) {
		this.articleComment = articleComment;
	}
	public Integer getArticleShare() {
		return articleShare;
	}
	public void setArticleShare(Integer articleShare) {
		this.articleShare = articleShare;
	}
	public Integer getArticleSort() {
		return articleSort;
	}
	public void setArticleSort(Integer articleSort) {
		this.articleSort = articleSort;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
