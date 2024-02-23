package com.newsTicker.dao;

import java.io.Serializable;
import java.sql.Timestamp;
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
import org.hibernate.query.Query;

import com.newsTicker.entity.*;

import util.HibernateUtil;

public class NewsTickerHBDAO implements NewsTickerDAO {

	private SessionFactory factory;

	public NewsTickerHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(NewsTickerVO newsTicker) {
		return (Integer) getSession().save(newsTicker);
	}

	@Override
	public Integer update(NewsTickerVO newsTicker) {
		try {
			getSession().update(newsTicker);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer newsTickerId) {
		NewsTickerVO newsTicker = getSession().get(NewsTickerVO.class, newsTickerId);
		try {
			if (newsTicker != null) {
				getSession().delete(newsTickerId);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public NewsTickerVO findByPrimaryKey(Integer newsTickerId) {
		return getSession().get(NewsTickerVO.class, newsTickerId);
	}

	@Override
	public List<NewsTickerVO> getAll() {
		return getSession().createQuery("from NewsTickerVO", NewsTickerVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from NewsTickerVO", Long.class).uniqueResult();
		return total.intValue();
	}

//	@Override
//	public List<NewsTickerVO> getByCompositeQuery(Map<String, String> map) {
//		if (map.size() == 0) {
//			return getAll();
//		}
//
//		CriteriaBuilder builder = getSession().getCriteriaBuilder();
//		CriteriaQuery<NewsTickerVO> criteria = builder.createQuery(NewsTickerVO.class);
//		Root<NewsTickerVO> root = criteria.from(NewsTickerVO.class);
//
//		List<Predicate> predicates = new ArrayList<>();
//
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//
//		java.util.Date startdate =null;
//		java.util.Date enddate = null;
//		try {
//			startdate = map.containsKey("startdate") ? dateFormat.parse(map.get("startdate")) : null;
//			enddate= map.containsKey("enddate") ? dateFormat.parse(map.get("enddate")) : null;
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		// 注意以下
//		if (map.containsKey("startdate") && map.containsKey("enddate")) {
//			predicates.add(builder.between(root.get("newsTickerCreateTime"), startdate, enddate));
//		}
//
//		for (Map.Entry<String, String> row : map.entrySet()) {
//			if ("startdate".equals(row.getKey())) {
//				if (!map.containsKey("enddate")) {
//					predicates.add(builder.greaterThanOrEqualTo(root.get("newsTickerCreateTime"),startdate));
//				}
//			}
//
//			if ("enddate".equals(row.getKey())) {
//				if (!map.containsKey("startdate")) {
//					predicates.add(
//							builder.lessThanOrEqualTo(root.get("newsTickerCreateTime"), enddate));
//				}
//			}
//
//		}
//
//		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		criteria.orderBy(builder.asc(root.get("newsTickerId")));
//		TypedQuery<NewsTickerVO> query = getSession().createQuery(criteria);
//		System.out.println("===============");
//		System.out.println(query.getResultList());
//		return query.getResultList();
//
//	}
	
//	public static void main(String[] args) {
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		Session session = factory.getCurrentSession();
//		session.beginTransaction();
//
//		// ================Insert==============
//
//		Query query = session.createQuery("FROM newsTickerLvVO WHERE newsTickerLvId = :id");
//		query.setParameter("id", 1);
//		newsTickerLvVO newsTickerLv1 = (newsTickerLvVO) query.uniqueResult();
//
//		NewsTickerVO newsTicker1 = new NewsTickerVO();
//		newsTicker1.setnewsTickerBankAccountNumber("002");
//		newsTicker1.setnewsTickerEmail("newsTicker2@example.com");
//		newsTicker1.setnewsTickerCompany("Test Company");
//		newsTicker1.setnewsTickerTaxId("123S");
//		newsTicker1.setnewsTickerCapital(500000);
//		newsTicker1.setnewsTickerContact("John Doe");
//		newsTicker1.setnewsTickerCompanyPhone("0223456789");
//		newsTicker1.setnewsTickerCompanyExtension("123");
//		newsTicker1.setnewsTickerMobile("0912345678");
//		newsTicker1.setnewsTickerAddress("台北市中正區1號");
//		newsTicker1.setnewsTickerPassword("password1");
//		newsTicker1.setnewsTickerBankAccount("123-456-789");
//		newsTicker1.setnewsTickerBankCode("012");
//		newsTicker1.setnewsTickerBankAccountNumber("98765432");
//		// isConfirm 跟 createTime 有預設
//		newsTicker1.setIsConfirm(false);
//		Timestamp t1 = new Timestamp(System.currentTimeMillis());
//		newsTicker1.setnewsTickerCreateTime(t1);
//
//		newsTicker1.setnewsTickerLvId(newsTickerLv1);
//
//		;
//
//		Serializable newsTickerId = session.save(newsTicker1);
//
//		// ================Update==============
//
//		NewsTickerVO newsTickerToUpdate = session.get(NewsTickerVO.class, (Integer) newsTickerId);
//		newsTickerToUpdate.setnewsTickerCompany("New Company Name");
//		session.update(newsTickerToUpdate);
//
//		// ================Query==============
//		String newsTickerLvName = newsTicker1.getnewsTickerLvId().getLvName();
//		System.out.println("newsTickerLvName: " + newsTickerLvName);
//
//		// ================Delete==============
//
//		NewsTickerVO newsTickerToDelete = session.get(NewsTickerVO.class, (Integer) newsTickerId);
//		session.delete(newsTickerToDelete);
//
//		session.getTransaction().commit();
//
//	}

}


