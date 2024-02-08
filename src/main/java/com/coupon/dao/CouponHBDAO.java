package com.coupon.dao;

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

import com.coupon.entity.CouponVO;

import util.HibernateUtil;

public class CouponHBDAO implements CouponDAO {

	private SessionFactory factory;

	public CouponHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(CouponVO coupon) {
		return (Integer) getSession().save(coupon);
	}

	@Override
	public Integer update(CouponVO coupon) {
		try {
			getSession().update(coupon);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer couponId) {
		CouponVO coupon = getSession().get(CouponVO.class, couponId);
		try {
			if (coupon != null) {
				getSession().delete(couponId);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public CouponVO findByPrimaryKey(Integer couponId) {
		return getSession().get(CouponVO.class, couponId);
	}

	@Override
	public List<CouponVO> getAll() {
		return getSession().createQuery("from CouponVO", CouponVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from CouponVO", Long.class).uniqueResult();
		return total.intValue();
	}

	@Override
	public List<CouponVO> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<CouponVO> criteria = builder.createQuery(CouponVO.class);
		Root<CouponVO> root = criteria.from(CouponVO.class);

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


			predicates.add(builder.between(root.get("couponCreateTime"), startdate, enddate));
		}

		if (map.containsKey("couponLvId")) {
			predicates.add(builder.equal(root.get("couponLvId"), Integer.valueOf(map.get("couponLvId"))));
		}

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("couponCompany".equals(row.getKey())) {
				predicates.add(builder.like(root.get("couponCompany"), "%" + row.getValue() + "%"));
			}

			if ("startdate".equals(row.getKey())) {
				if (!map.containsKey("enddate")) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("couponCreateTime"),startdate));
				}
			}

			if ("enddate".equals(row.getKey())) {
				if (!map.containsKey("startdate")) {
					predicates.add(
							builder.lessThanOrEqualTo(root.get("couponCreateTime"), enddate));
				}
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("couponId")));
		TypedQuery<CouponVO> query = getSession().createQuery(criteria);
		System.out.println("===============");
		System.out.println(query.getResultList());
		return query.getResultList();

	}

	@Override
	public boolean isCouponCodeUnique(String inputCouponCode, Integer couponMyId) {

		// Method 1
	    // Use Query to fetch only the needed data (couponId, couponCode)
//	    List<Object[]> couponCodes = getSession().createQuery("select couponId, couponCode from CouponVO", Object[].class).list();
//
//	    for (Object[] coupon : couponCodes) {
//	        Integer couponId = (Integer) coupon[0];
//	        String code = (String) coupon[1];
//
//	        // Check if the input coupon code already exists (excluding the current coupon by ID)
//	        if (inputCouponCode.equals(code) && !couponId.equals(couponMyId)) {
//	            return false;
//	        }
//	    }
				
	    Long count = (Long) getSession()
	            .createQuery("select count(couponId) from CouponVO where couponCode = :code and couponId <> :id", Long.class)
	            .setParameter("code", inputCouponCode)
	            .setParameter("id", couponMyId)
	            .uniqueResult();
		return count==0;
	}


}


