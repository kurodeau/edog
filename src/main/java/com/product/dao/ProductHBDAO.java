package com.product.dao;

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

import com.product.entity.ProductVO;

import util.HibernateUtil;

public class ProductHBDAO implements ProductDAO {

	private SessionFactory factory;

	public ProductHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(ProductVO product) {
		return (Integer) getSession().save(product);
	}

	@Override
	public Integer update(ProductVO product) {
		try {
			getSession().update(product);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer productId) {
		ProductVO product = getSession().get(ProductVO.class, productId);
		try {
			if (product != null) {
				getSession().delete(productId);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public ProductVO findByPrimaryKey(Integer productId) {
		return getSession().get(ProductVO.class, productId);
	}

	@Override
	public List<ProductVO> getAll() {
		return getSession().createQuery("from ProductVO", ProductVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from ProductVO", Long.class).uniqueResult();
		return total.intValue();
	}

	@Override
	public List<ProductVO> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ProductVO> criteria = builder.createQuery(ProductVO.class);
		Root<ProductVO> root = criteria.from(ProductVO.class);

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


			predicates.add(builder.between(root.get("productCreateTime"), startdate, enddate));
		}

		if (map.containsKey("productLvId")) {
			predicates.add(builder.equal(root.get("productLvId"), Integer.valueOf(map.get("productLvId"))));
		}

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("productCompany".equals(row.getKey())) {
				predicates.add(builder.like(root.get("productCompany"), "%" + row.getValue() + "%"));
			}

			if ("startdate".equals(row.getKey())) {
				if (!map.containsKey("enddate")) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("productCreateTime"),startdate));
				}
			}

			if ("enddate".equals(row.getKey())) {
				if (!map.containsKey("startdate")) {
					predicates.add(
							builder.lessThanOrEqualTo(root.get("productCreateTime"), enddate));
				}
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("productId")));
		TypedQuery<ProductVO> query = getSession().createQuery(criteria);
		System.out.println("===============");
		System.out.println(query.getResultList());
		return query.getResultList();

	}

	@Override
	public boolean isProductCodeUnique(String inputProductCode, Integer productMyId) {

		// Method 1
	    // Use Query to fetch only the needed data (productId, productCode)
//	    List<Object[]> productCodes = getSession().createQuery("select productId, productCode from ProductVO", Object[].class).list();
//
//	    for (Object[] product : productCodes) {
//	        Integer productId = (Integer) product[0];
//	        String code = (String) product[1];
//
//	        // Check if the input product code already exists (excluding the current product by ID)
//	        if (inputProductCode.equals(code) && !productId.equals(productMyId)) {
//	            return false;
//	        }
//	    }
				
	    Long count = (Long) getSession()
	            .createQuery("select count(productId) from ProductVO where productCode = :code and productId <> :id", Long.class)
	            .setParameter("code", inputProductCode)
	            .setParameter("id", productMyId)
	            .uniqueResult();
		return count==0;
	}


}


