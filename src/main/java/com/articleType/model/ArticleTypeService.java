package com.articleType.model;

import java.util.List;

public class ArticleTypeService {
	private ArticleTypeDAO_interface dao;

	public ArticleTypeService() {
		dao = new ArticleTypeDAO();
	}
	public ArticleTypeVO addArticleType(String articleTypeName) {

		ArticleTypeVO articleTypVO = new ArticleTypeVO();

		articleTypVO.setArticleTypeName(articleTypeName);
		dao.insert(articleTypVO);

		return articleTypVO;
	}

	public ArticleTypeVO updateArticleTypeVO(Integer articleTypeId,String articleTypeName) {

		ArticleTypeVO articleTypeVO = new ArticleTypeVO();

		articleTypeVO.setArticleTypeId(articleTypeId);
		articleTypeVO.setArticleTypeName(articleTypeName);	
		dao.update(articleTypeVO);

		return articleTypeVO;
	}

//	public void deleteArticleType(Integer articleTypeId) {
//		dao.delete(articleTypeId);
//	}

	public ArticleTypeVO getOneArticleTypeVO(Integer articleTypeId) {
		return dao.findByPrimaryKey(articleTypeId);
	}

	public List<ArticleTypeVO> getAll() {
		return dao.getAll();
	}
}
