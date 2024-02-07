package com.articlePic.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticlePicService {
	private ArticlePicDAO_interface dao;

	public ArticlePicService() {
		dao = new ArticlePicDAO();
	}

	public ArticlePicVO addArticlePic(Integer articleId,byte[] articlePicBlob,Timestamp articlePicTime) {

		ArticlePicVO articlePicVO = new ArticlePicVO();

		articlePicVO.setArticleId(articleId);
		articlePicVO.setArticlePicBlob(articlePicBlob);
		articlePicVO.setArticlePicTime(articlePicTime);
		dao.insert(articlePicVO);

		return articlePicVO;
	}

	public ArticlePicVO updateArticlePic(Integer articlePicId,Integer articleId,byte[] articlePicBlob,Timestamp articlePicTime) {

		ArticlePicVO articlePicVO = new ArticlePicVO();

		articlePicVO.setArticlePicId(articlePicId);
		articlePicVO.setArticleId(articleId);
		articlePicVO.setArticlePicBlob(articlePicBlob);
		articlePicVO.setArticlePicTime(articlePicTime);
		dao.update(articlePicVO);

		return articlePicVO;
	}

//	public void deleteArticlePic(Integer articlePicId) {
//		dao.delete(articlePicId);
//	}

	public ArticlePicVO getOneArticlePic(Integer articlePicId) {
		return dao.findByPrimaryKey(articlePicId);
	}

	public List<ArticlePicVO> getAll() {
		return dao.getAll();
	}
}
