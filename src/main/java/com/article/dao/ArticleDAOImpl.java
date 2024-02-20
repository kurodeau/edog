package com.article.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.article.entity.ArticleVO;

import util.HibernateUtil;

public class ArticleDAOImpl implements ArticleDAO {
	private SessionFactory factory;

	public ArticleDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(ArticleVO entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public Integer update(ArticleVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer delete(Integer id) {
		ArticleVO article = getSession().get(ArticleVO.class, id);
		if (article != null) {
			getSession().delete(article);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public ArticleVO findByPrimaryKey(Integer articleId) {
		return getSession().get(ArticleVO.class, articleId);
	}

	@Override
	public List<ArticleVO> getAll() {
		return getSession().createQuery("from ArticleVO", ArticleVO.class).list();
	}

	@Override
	public List<ArticleVO> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ArticleVO> criteria = builder.createQuery(ArticleVO.class);
		Root<ArticleVO> root = criteria.from(ArticleVO.class);

		List<Predicate> predicates = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		java.util.Date startdate = null;
		java.util.Date enddate = null;
		try {
			startdate = map.containsKey("startdate") ? dateFormat.parse(map.get("startdate")) : null;
			enddate = map.containsKey("enddate") ? dateFormat.parse(map.get("enddate")) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 注意以下
		if (map.containsKey("startdate") && map.containsKey("enddate")) {

			predicates.add(builder.between(root.get("artUpdateTime"), startdate, enddate));
		}

		if (map.containsKey("articleSort")) {
			predicates.add(builder.equal(root.get("articleSort"), Integer.valueOf(map.get("articleSort"))));
		}

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("articleTitle".equals(row.getKey())) {
				predicates.add(builder.like(root.get("articleTitle"), "%" + row.getValue() + "%"));
			}

			if ("startdate".equals(row.getKey())) {
				if (!map.containsKey("enddate")) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("artUpdateTime"), startdate));
				}
			}

			if ("enddate".equals(row.getKey())) {
				if (!map.containsKey("startdate")) {
					predicates.add(builder.lessThanOrEqualTo(root.get("artUpdateTime"), enddate));
				}
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("articleId")));
		TypedQuery<ArticleVO> query = getSession().createQuery(criteria);
		System.out.println("===============");
		System.out.println(query.getResultList());
		return query.getResultList();

	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from ArticleVO", Long.class).uniqueResult();
		return total.intValue();
	}


}
