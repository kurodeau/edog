package com.article.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.article.dao.ArticleDAO;
import com.article.dao.ArticleDAOImpl;
import com.article.entity.ArticleVO;
import com.articleType.dao.ArticleTypeDAO;
import com.articleType.dao.ArticleTypeDAOImpl;
import com.articleType.entity.ArticleTypeVO;

import util.HibernateUtil;

public class ArticleService {
	private ArticleDAO dao;
	private ArticleTypeDAO articleTypedao;

	public ArticleService() {

		dao = new ArticleDAOImpl();
		articleTypedao = new ArticleTypeDAOImpl();
	}

	
	public ArticleVO addArticle(Integer memberId, String articleTitle, String articleContent, Date artUpdateTime,
			Integer articleLike, Integer articleComment, Integer articleShare, Integer articleSort, Boolean isEnabled) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		ArticleVO articleVO = null;
		try {
			session.beginTransaction();

			articleVO = new ArticleVO();

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
			session.getTransaction().commit();

		} catch (

		Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return articleVO;
	}

	public ArticleVO updateArticle(Integer articleId, Integer memberId, String articleTitle, String articleContent,
			Date artUpdateTime, Integer articleLike, Integer articleComment, Integer articleShare, Integer articleSort,
			Boolean isEnabled) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArticleVO articleVO = null;
		try {
			session.beginTransaction();
			articleVO = new ArticleVO();

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
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return articleVO;
	}

	public void deleteArticle(Integer articleId) {
		dao.delete(articleId);
	}

	public ArticleVO getOneArticle(Integer articleId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArticleVO articleVO = null;

		try {
			session.beginTransaction();
			articleVO = dao.findByPrimaryKey(articleId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return articleVO;
	}

	public List<ArticleVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ArticleVO> list = Collections.emptyList();

		try {
			session.beginTransaction();
			list = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public int getTotal() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		try {
			return dao.getTotal();
		} catch (Exception e) {
			e.printStackTrace();
			return 0; // or handle as needed
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
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

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ArticleVO> result = Collections.emptyList();
		try {
			session.beginTransaction();
			result = dao.getByCompositeQuery(query);
			session.getTransaction().commit();
			return (result);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}
	// 在 ArticleService 中添加的方法
	public ArticleTypeVO getArticleTypeById(Integer articleTypeId) {
	    Session session = null;
	    try {
	        session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        ArticleTypeDAO articleTypeDAO = new ArticleTypeDAOImpl();
	        return articleTypeDAO.findByPrimaryKey(articleTypeId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}



}
