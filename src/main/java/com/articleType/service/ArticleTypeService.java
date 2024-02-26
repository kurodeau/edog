package com.articleType.service;


import java.util.List;

import com.article.entity.ArticleVO;
import com.articleType.dao.ArticleTypeDAO;
import com.articleType.dao.ArticleTypeDAOImpl;
import com.articleType.entity.ArticleTypeVO;


public class ArticleTypeService {
	private ArticleTypeDAO dao;

	public ArticleTypeService() {

		dao = new ArticleTypeDAOImpl();
	}


	public ArticleTypeVO addArticleType(String articleTypeName) {

		ArticleTypeVO articleTypeVO = new ArticleTypeVO();

		articleTypeVO.setArticleTypeName(articleTypeName);

		dao.insert(articleTypeVO);

		return articleTypeVO;
	}

	public ArticleTypeVO updateArticleType(Integer articleTypeId, String articleTypeName) {

		ArticleTypeVO articleTypeVO = new ArticleTypeVO();
		articleTypeVO.setArticleTypeId(articleTypeId);
		articleTypeVO.setArticleTypeName(articleTypeName);

		dao.update(articleTypeVO);

		return articleTypeVO;
	}

	public void deleteArticleType(Integer id) {
		dao.delete(id);
	}

	public ArticleTypeVO getOneArticleType(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public List<ArticleTypeVO> getAll() {
		return dao.getAll();
	}

	public Integer getTotal() {
		return dao.getTotal();
	}

}
