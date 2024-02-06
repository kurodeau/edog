package com.articlePic.model;
import java.sql.Timestamp;
import java.util.Arrays;

public class ArticlePicVO implements java.io.Serializable{
	private Integer articlePicId;
	private Integer articleId;
	private byte[] articlePicBlob;
	private Timestamp articlePicTime;
	
	public Integer getArticlePicId() {
		return articlePicId;
	}
	public void setArticlePicId(Integer articlePicId) {
		this.articlePicId = articlePicId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public byte[] getArticlePicBlob() {
		return articlePicBlob;
	}
	public void setArticlePicBlob(byte[] articlePicBlob) {
		this.articlePicBlob = articlePicBlob;
	}
	public Timestamp getArticlePicTime() {
		return articlePicTime;
	}
	public void setArticlePicTime(Timestamp articlePicTime) {
		this.articlePicTime = articlePicTime;
	}
	@Override
	public String toString() {
		return "ArticlePicVO [articlePicId=" + articlePicId + ", articleId=" + articleId + ", articlePicBlob="
				+ ", articlePicTime=" + articlePicTime + "]";
	}
}
