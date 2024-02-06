package com.seller.dao;

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

import com.seller.entity.SellerVO;

import util.HibernateUtil;

public class SellerHBDAO implements SellerDAO {

	private SessionFactory factory;

	public SellerHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(SellerVO seller) {
		return (Integer) getSession().save(seller);
	}

	@Override
	public Integer update(SellerVO seller) {
		try {
			getSession().update(seller);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer sellerId) {
		SellerVO seller = getSession().get(SellerVO.class, sellerId);
		try {
			if (seller != null) {
				getSession().delete(sellerId);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public SellerVO findByPrimaryKey(Integer sellerId) {
		return getSession().get(SellerVO.class, sellerId);
	}

	@Override
	public List<SellerVO> getAll() {
		return getSession().createQuery("from SellerVO", SellerVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from SellerVO", Long.class).uniqueResult();
		return total.intValue();
	}

	@Override
	public List<SellerVO> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<SellerVO> criteria = builder.createQuery(SellerVO.class);
		Root<SellerVO> root = criteria.from(SellerVO.class);

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


			predicates.add(builder.between(root.get("sellerCreateTime"), startdate, enddate));
		}

		if (map.containsKey("sellerLvId")) {
			predicates.add(builder.equal(root.get("sellerLvId"), Integer.valueOf(map.get("sellerLvId"))));
		}

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("sellerCompany".equals(row.getKey())) {
				predicates.add(builder.like(root.get("sellerCompany"), "%" + row.getValue() + "%"));
			}

			if ("startdate".equals(row.getKey())) {
				if (!map.containsKey("enddate")) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("sellerCreateTime"),startdate));
				}
			}

			if ("enddate".equals(row.getKey())) {
				if (!map.containsKey("startdate")) {
					predicates.add(
							builder.lessThanOrEqualTo(root.get("sellerCreateTime"), enddate));
				}
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("sellerId")));
		TypedQuery<SellerVO> query = getSession().createQuery(criteria);
		System.out.println("===============");
		System.out.println(query.getResultList());
		return query.getResultList();

	}

}

//public static void main(String[] args) {
//	SessionFactory factory = HibernateUtil.getSessionFactory();
//	Session session = factory.getCurrentSession();
//	session.beginTransaction();
//
//	// ================Insert==============
//
//	Query query = session.createQuery("FROM SellerLvVO WHERE sellerLvId = :id");
//	query.setParameter("id", 1);
//	SellerLvVO sellerLv1 = (SellerLvVO) query.uniqueResult();
//
//	SellerVO seller1 = new SellerVO();
//	seller1.setSellerBankAccountNumber("002");
//	seller1.setSellerEmail("seller2@example.com");
//	seller1.setSellerCompany("Test Company");
//	seller1.setSellerTaxId("123S");
//	seller1.setSellerCapital(500000);
//	seller1.setSellerContact("John Doe");
//	seller1.setSellerCompanyPhone("0223456789");
//	seller1.setSellerCompanyExtension("123");
//	seller1.setSellerMobile("0912345678");
//	seller1.setSellerAddress("台北市中正區1號");
//	seller1.setSellerPassword("password1");
//	seller1.setSellerBankAccount("123-456-789");
//	seller1.setSellerBankCode("012");
//	seller1.setSellerBankAccountNumber("98765432");
//	// isConfirm 跟 createTime 有預設
//	seller1.setIsConfirm(false);
//	Timestamp t1 = new Timestamp(System.currentTimeMillis());
//	seller1.setSellerCreateTime(t1);
//
//	seller1.setSellerLvId(sellerLv1);
//
//	;
//
//	Serializable sellerId = session.save(seller1);
//
//	// ================Update==============
//
//	SellerVO sellerToUpdate = session.get(SellerVO.class, (Integer) sellerId);
//	sellerToUpdate.setSellerCompany("New Company Name");
//	session.update(sellerToUpdate);
//
//	// ================Query==============
//	String sellerLvName = seller1.getSellerLvId().getLvName();
//	System.out.println("sellerLvName: " + sellerLvName);
//
//	// ================Delete==============
//
//	SellerVO sellerToDelete = session.get(SellerVO.class, (Integer) sellerId);
//	session.delete(sellerToDelete);
//
//	session.getTransaction().commit();
//
//}
