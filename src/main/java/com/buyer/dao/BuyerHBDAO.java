package com.buyer.dao;

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

import com.buyer.entity.BuyerVO;
import com.coupon.entity.CouponVO;

import util.HibernateUtil;

public class BuyerHBDAO implements BuyerDAO{
	
	private SessionFactory factory;

	public BuyerHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(BuyerVO buyer) {
		return (Integer) getSession().save(buyer);
	}

	@Override
	public Integer update(BuyerVO buyer) {
		try {
			getSession().update(buyer);
			return 1;
		} catch (Exception e) {
			return -1;
		}
		
	}

	@Override
	public Integer delete(Integer memberId) {
		BuyerVO buyer = getSession().get(BuyerVO.class, memberId);
		try {
			if (buyer != null) {
				getSession().delete(memberId);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public BuyerVO findByPrimaryKey(Integer memberId) {
		return getSession().get(BuyerVO.class, memberId);
	}

	@Override
	public List<BuyerVO> getAll() {
		return getSession().createQuery("from BuyerVO", BuyerVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from BuyerVO", Long.class).uniqueResult();
		return total.intValue();
	}

	@Override
	public List<BuyerVO> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<BuyerVO> criteria = builder.createQuery(BuyerVO.class);
		Root<BuyerVO> root = criteria.from(BuyerVO.class);

		List<Predicate> predicates = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		java.util.Date startdate =null;
		java.util.Date enddate = null;
		try {
			startdate = map.containsKey("startdate") ? dateFormat.parse(map.get("startdate")) : null;
			enddate= map.containsKey("enddate") ? dateFormat.parse(map.get("enddate")) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 注意以下
		if (map.containsKey("startdate") && map.containsKey("enddate")) {
			predicates.add(builder.between(root.get("memberRegistrationTime"), startdate, enddate));
		}

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("membername".equals(row.getKey())) {
				predicates.add(builder.like(root.get("membername"), "%" + row.getValue() + "%"));
			}

			if ("startdate".equals(row.getKey())) {
				if (!map.containsKey("enddate")) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("memberRegistrationTime"),startdate));
				}
			}

			if ("enddate".equals(row.getKey())) {
				if (!map.containsKey("startdate")) {
					predicates.add(
							builder.lessThanOrEqualTo(root.get("memberRegistrationTime"), enddate));
				}
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("memberId")));
		TypedQuery<BuyerVO> query = getSession().createQuery(criteria);
		System.out.println("===============");
		System.out.println(query.getResultList());
		return query.getResultList();

	}
	

}
