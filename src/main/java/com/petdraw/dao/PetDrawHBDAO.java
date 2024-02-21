package com.petdraw.dao;


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

import com.petdraw.entity.PetDrawVO;

import util.HibernateUtil;

public class PetDrawHBDAO implements PetDrawDAO {

	private SessionFactory factory;

	public PetDrawHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(PetDrawVO petDraw) {
		return (Integer) getSession().save(petDraw);
	}

	@Override
	public Integer update(PetDrawVO petDraw) {
		try {
			getSession().update(petDraw);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer petDrawId) {
		PetDrawVO petDraw = getSession().get(PetDrawVO.class, petDrawId);
		try {
			if (petDraw != null) {
				getSession().delete(petDrawId);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public PetDrawVO findByPrimaryKey(Integer petDrawId) {
		return getSession().get(PetDrawVO.class, petDrawId);
	}

	@Override
	public List<PetDrawVO> getAll() {
		return getSession().createQuery("from PetDrawVO", PetDrawVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from PetDrawVO", Long.class).uniqueResult();
		return total.intValue();
	}

	@Override
	public List<PetDrawVO> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<PetDrawVO> criteria = builder.createQuery(PetDrawVO.class);
		Root<PetDrawVO> root = criteria.from(PetDrawVO.class);

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


			predicates.add(builder.between(root.get("petDrawCreateTime"), startdate, enddate));
		}

		if (map.containsKey("petDrawLvId")) {
			predicates.add(builder.equal(root.get("petDrawLvId"), Integer.valueOf(map.get("petDrawLvId"))));
		}

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("petDrawCompany".equals(row.getKey())) {
				predicates.add(builder.like(root.get("petDrawCompany"), "%" + row.getValue() + "%"));
			}

			if ("startdate".equals(row.getKey())) {
				if (!map.containsKey("enddate")) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("petDrawCreateTime"),startdate));
				}
			}

			if ("enddate".equals(row.getKey())) {
				if (!map.containsKey("startdate")) {
					predicates.add(
							builder.lessThanOrEqualTo(root.get("petDrawCreateTime"), enddate));
				}
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("petDrawId")));
		TypedQuery<PetDrawVO> query = getSession().createQuery(criteria);
		System.out.println("===============");
		System.out.println(query.getResultList());
		return query.getResultList();

	}

	@Override
	public boolean isPetDrawCodeUnique(String inputPetDrawCode, Integer petDrawMyId) {

		// Method 1
	    // Use Query to fetch only the needed data (petDrawId, petDrawCode)
//	    List<Object[]> petDrawCodes = getSession().createQuery("select petDrawId, petDrawCode from PetDrawVO", Object[].class).list();
//
//	    for (Object[] petDraw : petDrawCodes) {
//	        Integer petDrawId = (Integer) petDraw[0];
//	        String code = (String) petDraw[1];
//
//	        // Check if the input petDraw code already exists (excluding the current petDraw by ID)
//	        if (inputPetDrawCode.equals(code) && !petDrawId.equals(petDrawMyId)) {
//	            return false;
//	        }
//	    }
				
	    Long count = (Long) getSession()
	            .createQuery("select count(petDrawId) from PetDrawVO where petDrawCode = :code and petDrawId <> :id", Long.class)
	            .setParameter("code", inputPetDrawCode)
	            .setParameter("id", petDrawMyId)
	            .uniqueResult();
		return count==0;
	}


}


