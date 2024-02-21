package com.articleType.dao;

import java.util.List;
import java.util.Map;

import com.articleType.entity.ArticleTypeVO;
import com.sellerLv.entity.SellerLvVO;

public interface ArticleTypeDAO {

	public Integer insert(ArticleTypeVO articleType);
	public Integer update(ArticleTypeVO articleType);
	public Integer delete(Integer articleTypeId);
	public ArticleTypeVO findByPrimaryKey(Integer articleTypeId);
    public List<ArticleTypeVO> getAll();
    public Integer getTotal();

}
