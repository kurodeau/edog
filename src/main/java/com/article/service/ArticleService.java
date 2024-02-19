package com.article.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.article.dao.ArticleDAO;
import com.article.dao.ArticleDAOImpl;
import com.article.entity.ArticleVO;
import com.articleType.dao.ArticleTypeDAO;
import com.articleType.dao.ArticleTypeDAOImpl;
import com.articleType.entity.ArticleTypeVO;

public class ArticleService {
	private ArticleDAO dao;
	private ArticleTypeDAO articleTypedao;

	public ArticleService() {

		dao = new ArticleDAOImpl();
		articleTypedao = new ArticleTypeDAOImpl();
	}

	public ArticleVO addArticle(Integer memberId, String articleTitle, String articleContent, Date artUpdateTime,
			Integer articleLike, Integer articleComment, Integer articleShare, Integer articleSort, Boolean isEnabled) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setMemberId(memberId);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArtUpdateTime(artUpdateTime);
		articleVO.setArticleLike(articleLike);
		articleVO.setArticleComment(articleComment);
		articleVO.setArticleShare(articleShare);
		articleVO.setIsEnabled(isEnabled);
		
		articleVO.setArticleTypeId(articleTypedao.findByPrimaryKey(1));

		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(Integer articleId, Integer memberId, String articleTitle, String articleContent, Date artUpdateTime,
			Integer articleLike, Integer articleComment, Integer articleShare, Integer articleSort, Boolean isEnabled) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticleId(articleId);

		ArticleTypeVO articleTypeVO = articleTypedao.findByPrimaryKey(articleSort);
		articleVO.setArticleTypeId(articleTypeVO);

		articleVO.setMemberId(memberId);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArtUpdateTime(artUpdateTime);
		articleVO.setArticleLike(articleLike);
		articleVO.setArticleComment(articleComment);
		articleVO.setArticleShare(articleShare);
		articleVO.setIsEnabled(isEnabled);

		dao.update(articleVO);

		return articleVO;
	}

	public void deleteArticle(Integer articleId) {
		dao.delete(articleId);
	}

	public ArticleVO getOneArticle(Integer articleId) {
		return dao.findByPrimaryKey(articleId);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}

	public int getTotal() {
		return dao.getTotal();
	}

	public List<ArticleVO> getArticlesByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();

		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}

		return dao.getByCompositeQuery(query);
	}

}
