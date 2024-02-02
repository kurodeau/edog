package com.articleType.model;

import java.util.List;

public interface ArticleTypeDAO_interface {
	public void insert(ArticleTypeVO articleTypeVO);
    public void update(ArticleTypeVO articleTypeVO);
    public void delete(Integer articleTypeId);
    public ArticleTypeVO findByPrimaryKey(Integer articleTypeId);
    public List<ArticleTypeVO> getAll();
}
