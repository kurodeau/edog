package com.articleType.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.articleType.entity.ArticleTypeVO;

import util.HibernateUtil;

public class ArticleTypeDAOImpl implements ArticleTypeDAO {
	private SessionFactory factory;

	public ArticleTypeDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(ArticleTypeVO entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public Integer update(ArticleTypeVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer delete(Integer id) {
		ArticleTypeVO articleType = getSession().get(ArticleTypeVO.class, id);
		if (articleType != null) {
			getSession().delete(articleType);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public ArticleTypeVO findByPrimaryKey(Integer id) {
		return getSession().get(ArticleTypeVO.class, id);
	}

	@Override
	public List<ArticleTypeVO> getAll() {
		return getSession().createQuery("from ArticleType", ArticleTypeVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from ArticleTypeVO",Long.class).uniqueResult();
		return total.intValue();
	}

}
