package com.article.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.articleType.entity.ArticleTypeVO;

@Entity
@Table(name = "article")
public class ArticleVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleId", updatable = false)
	private Integer articleId;
	
	@Column(name = "memberId")
	private Integer memberId;
	
	@Column(name = "articleTitle")
	private String articleTitle;
	
	@Column(name = "articleContent")
	private String articleContent;
	
	@Column(name = "artUpdateTime")
	private Date artUpdateTime;
	
	@Column(name = "articleLike")
	private Integer articleLike;
	
	@Column(name = "articleComment")
	private Integer articleComment;
	
	@Column(name = "articleShare")
	private Integer articleShare;
	
//	@ManyToOne
//	@JoinColumn(name = "articleSort", referencedColumnName = "articleTypeId")
	@Column(name = "articleSort")
	private ArticleTypeVO articleSort;
	
	public ArticleTypeVO getArticleTypeId() {
		return articleSort;
	}
	public void setArticleTypeId(ArticleTypeVO articleSort) {
		this.articleSort = articleSort;
	}
	
	@Column(name = "isEnabled")
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

	public Date getArtUpdateTime() {
		return artUpdateTime;
	}

	public void setArtUpdateTime(Date artUpdateTime) {
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

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", memberId=" + memberId + ", articleTitle=" + articleTitle
				+ ", articleContent=" + articleContent + ", artUpdateTime=" + artUpdateTime + ", articleLike="
				+ articleLike + ", articleComment=" + articleComment + ", articleShare=" + articleShare
				+ ", articleSort=" + articleSort + ", isEnabled=" + isEnabled + "]";
	}
	
}
