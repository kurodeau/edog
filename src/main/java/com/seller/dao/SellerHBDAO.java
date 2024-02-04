package com.seller.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.seller.entity.Seller;

import util.HibernateUtil;

public class SellerHBDAO {

	private SessionFactory factory;

	public SellerHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	public Integer insert(Seller seller) {
		return (Integer) getSession().save(seller);
	}

	public Integer update(Seller seller) {
		try {
			getSession().update(seller);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	public Integer delete(Integer sellerId) {
		Seller seller = getSession().get(Seller.class, sellerId);
		try {
			if(seller!=null) {	getSession().delete(sellerId);
			return 1;
			}else {
			return 0;
			}
		} catch (Exception e) {
			System.out.println("=====SellerHBDAO==delete====");
			return -1;
		}
	}

	public Seller findByPrimaryKey(Integer sellerId) {
		return getSession().get(Seller.class,sellerId);
	}

	public List<Seller> getAll() {
		return getSession().createQuery("from Seller",Seller.class).list();
	}
	
	public long getTotal() {
		return getSession().createQuery("select count(*) from Seller", Long.class).uniqueResult();
	}

}