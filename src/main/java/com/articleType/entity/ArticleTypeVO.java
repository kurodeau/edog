package com.articleType.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "articleType")
public class ArticleTypeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articleTypeId", updatable = false)
    private Integer articleTypeId;
    
    @Column(name = "articleTypeName")
    private String articleTypeName;
    
  @OneToMany(mappedBy = "articleType", cascade = CascadeType.ALL)
  @OrderBy("articleTypeId asc")
//  @Column(name = "articles")
//  private Set<ArticleVO> articles;
    
    // Getters and setters for articleTypeId and articleTypeName

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
    
    // Override toString() method for better object representation
    
    @Override
    public String toString() {
        return "ArticleType [articleTypeId=" + articleTypeId + ", articleTypeName=" + articleTypeName + "]";
    }
}
