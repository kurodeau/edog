package com.article.dao;

import java.util.List;
import java.util.Map;

import com.article.entity.ArticleVO;

public interface ArticleDAO {

	public Integer insert(ArticleVO article);
    public Integer update(ArticleVO article);
    public Integer delete(Integer articleId);
    public ArticleVO findByPrimaryKey(Integer articleId);
    public List<ArticleVO> getAll();
    public Integer getTotal();
    public List<ArticleVO> getByCompositeQuery(Map<String,String> map);
    
}
