package com.ad.model;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class AdDAO implements AdDAO_interface {

	private SessionFactory factory;

	public AdDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(AdVO adVOHB) {

		Session session = getSession();
		try {
			session.beginTransaction();
			Integer adId = (Integer) session.save(adVOHB);
			session.getTransaction().commit();
			return adId;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		// 操作失敗 , 可以在Service判斷
	}

	@Override
	public Integer update(AdVO adVO) {
		try {
			getSession().update(adVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer delete(Integer adId) {
		AdVO adVO = getSession().get(AdVO.class, adId);
		try {
			if (adId != null) {
				getSession().delete(adId);
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public AdVO findByPrimaryKey(Integer adId) {

		return getSession().get(AdVO.class, adId);
	}

	@Override
	public List<AdVO> getAll() {
	    Session session = getSession();
	    try {
	    		    	
	        session.beginTransaction();
	        String hql = "SELECT new AdVO(adId, adImg, adName, adStartTime, adEndTime, adStatus) FROM AdVO";
	        List<AdVO> advertisements = session.createQuery(hql ,AdVO.class).list();
	        System.out.println("Retrieved advertisements: " + advertisements);
	        
	        
	        // 移动事务提交到方法末尾
	        session.getTransaction().commit();
	        
	        return advertisements;
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    return Collections.emptyList();
		}
}
