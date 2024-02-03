package com.article.model;
import java.sql.Timestamp;
import java.util.List;

public class ArticleService {
	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleDAO();
	}
	public ArticleVO addArticle(Integer memberId,String articleTitle,String articleContent,Timestamp artUpdateTime,Integer articleLike,Integer articleComment,Integer articleShare,Integer articleSort,Boolean isEnabled) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setMemberId(memberId);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArtUpdateTime(artUpdateTime);
		articleVO.setArticleLike(articleLike);
		articleVO.setArticleComment(articleComment);
		articleVO.setArticleShare(articleShare);
		articleVO.setArticleSort(articleSort);
		articleVO.setIsEnabled(isEnabled);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(Integer ArticleId,Integer memberId,String articleTitle,String articleContent,Timestamp artUpdateTime,Integer articleLike,Integer articleComment,Integer articleShare,Integer articleSort,Boolean isEnabled) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticleId(ArticleId);
		articleVO.setMemberId(memberId);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArtUpdateTime(artUpdateTime);
		articleVO.setArticleLike(articleLike);
		articleVO.setArticleComment(articleComment);
		articleVO.setArticleShare(articleShare);
		articleVO.setArticleSort(articleSort);
		articleVO.setIsEnabled(isEnabled);		
		dao.update(articleVO);

		return articleVO;
	}

//	public void deleteArticle(Integer articleId) {
//		dao.delete(articleId);
//	}

	public ArticleVO getOneArticle(Integer articleId) {
		return dao.findByPrimaryKey(articleId);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
}
