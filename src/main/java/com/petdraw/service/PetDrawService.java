package com.petdraw.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.buyer.entity.BuyerVO;
import com.petdraw.dao.PetDrawDAO;
import com.petdraw.dao.PetDrawHBDAO;
import com.petdraw.entity.PetDrawVO;

import util.HibernateUtil;

public class PetDrawService {

    private PetDrawDAO dao;

    public PetDrawService() {
        dao = new PetDrawHBDAO();
    }

    public boolean isPetDrawCodeUnique(String petDrawCode, Integer petDrawMyId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Boolean isUnique = false;

        try {
            tx = session.beginTransaction();
            isUnique = dao.isPetDrawCodeUnique(petDrawCode, petDrawMyId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return isUnique;
    }

    public PetDrawVO addPetDraw(BuyerVO memberMain, BuyerVO memberPair, Boolean isMemberLike, Date memberResTime,
            Date memberPairResTime, Boolean isMemberPairLike, Date petDrawTime, Double petDrawLog, Double petDrawLat) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        PetDrawVO petDrawVO = null;

        try {
            tx = session.beginTransaction();

            petDrawVO = new PetDrawVO();
            petDrawVO.setMemberMain(memberMain);
            petDrawVO.setMemberPair(memberPair);
            petDrawVO.setIsMemberLike(isMemberLike);
            petDrawVO.setMemberResTime(memberResTime);
            petDrawVO.setMemberPairResTime(memberPairResTime);
            petDrawVO.setIsMemberPairLike(isMemberPairLike);
            petDrawVO.setPetDrawTime(petDrawTime);
            petDrawVO.setPetDrawLog(petDrawLog);
            petDrawVO.setPetDrawLat(petDrawLat);

            dao.insert(petDrawVO);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return petDrawVO;
    }

    public PetDrawVO getOnePetDraw(Integer petDrawId) {
        return dao.findByPrimaryKey(petDrawId);
    }

    public PetDrawVO updatePetDraw(Integer petDrawId, BuyerVO memberMain, BuyerVO memberPair, Boolean isMemberLike,
            Date memberResTime, Date memberPairResTime, Boolean isMemberPairLike, Date petDrawTime, Double petDrawLog,
            Double petDrawLat, Date petDrawCreateTime) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        PetDrawVO petDrawVO = null;

        try {
            tx = session.beginTransaction();

            petDrawVO = dao.findByPrimaryKey(petDrawId);

            if (petDrawVO != null) {
                petDrawVO.setMemberMain(memberMain);
                petDrawVO.setMemberPair(memberPair);
                petDrawVO.setIsMemberLike(isMemberLike);
                petDrawVO.setMemberResTime(memberResTime);
                petDrawVO.setMemberPairResTime(memberPairResTime);
                petDrawVO.setIsMemberPairLike(isMemberPairLike);
                petDrawVO.setPetDrawTime(petDrawTime);
                petDrawVO.setPetDrawLog(petDrawLog);
                petDrawVO.setPetDrawLat(petDrawLat);

                dao.update(petDrawVO);
            }

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return petDrawVO;
    }

    public void updateDrawDate(Integer memberid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PetDrawVO petDrawVO = dao.findByPrimaryKey(memberid);

            if (petDrawVO != null) {
                java.util.Date today = new java.sql.Date(System.currentTimeMillis());
                petDrawVO.setPetDrawTime(today);
                dao.update(petDrawVO);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<PetDrawVO> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PetDrawVO> list = null;

        try {
            tx = session.beginTransaction();
            list = dao.getAll();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return list;
    }
}
