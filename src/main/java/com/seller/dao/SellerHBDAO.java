package com.seller.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.seller.entity.SellerVO;
import com.sellerLv.entity.SellerLvVO;

import util.HibernateUtil;

public class SellerHBDAO implements SellerDAO {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		// ================Insert==============

		Query query = session.createQuery("FROM SellerLvVO WHERE sellerLvId = :id");
		query.setParameter("id", 1);
		SellerLvVO sellerLv1 = (SellerLvVO) query.uniqueResult();

		SellerVO seller1 = new SellerVO();
		seller1.setSellerBankAccountNumber("002");
		seller1.setSellerEmail("seller2@example.com");
		seller1.setSellerCompany("Test Company");
		seller1.setSellerTaxId("123S");
		seller1.setSellerCapital(500000);
		seller1.setSellerContact("John Doe");
		seller1.setSellerCompanyPhone("0223456789");
		seller1.setSellerCompanyExtension("123");
		seller1.setSellerMobile("0912345678");
		seller1.setSellerAddress("台北市中正區1號");
		seller1.setSellerPassword("password1");
		seller1.setSellerBankAccount("123-456-789");
		seller1.setSellerBankCode("012");
		seller1.setSellerBankAccountNumber("98765432");
		// isConfirm 跟 createTime 有預設
		seller1.setIsConfirm(false);
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		seller1.setSellerCreateTime(t1);

		seller1.setSellerLvId(sellerLv1);

		;

		Serializable sellerId = session.save(seller1);

		// ================Update==============

		SellerVO sellerToUpdate = session.get(SellerVO.class, (Integer) sellerId);
		sellerToUpdate.setSellerCompany("New Company Name");
		session.update(sellerToUpdate);

		// ================Query==============
		String sellerLvName = seller1.getSellerLvId().getLvName();
		System.out.println("sellerLvName: " + sellerLvName);

		// ================Delete==============

		SellerVO sellerToDelete = session.get(SellerVO.class, (Integer) sellerId);
		session.delete(sellerToDelete);

		session.getTransaction().commit();

	}

	private SessionFactory factory;

	public SellerHBDAO() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	public Integer insert(SellerVO seller) {
		return (Integer) getSession().save(seller);
	}

	public Integer update(SellerVO seller) {
		try {
			getSession().update(seller);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

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

	public SellerVO findByPrimaryKey(Integer sellerId) {
		return getSession().get(SellerVO.class, sellerId);
	}

	public List<SellerVO> getAll() {
		return getSession().createQuery("from Seller", SellerVO.class).list();
	}

	public long getTotal() {
		return getSession().createQuery("select count(*) from Seller", Long.class).uniqueResult();
	}

}