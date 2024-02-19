package com.articleType.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.article.entity.ArticleVO;
@Entity
@Table(name = "articleType")
public class ArticleTypeVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleTypeId", updatable = false)
	private Integer articleTypeId;
	
	@Column(name = "articleTypeName")
	private String articleTypeName;
	
//	@OneToMany(mappedBy = "articleType", cascade = CascadeType.ALL)
//	@OrderBy("article asc")
	private Set<ArticleVO> articles;
	
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

	@Override
	public String toString() {
		return "ArticleType [articleTypeId=" + articleTypeId + ", articleTypeName=" + articleTypeName + "]";
	}
}
