package com.coupon.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.coupon.dao.CouponDAO;
import com.coupon.dao.CouponHBDAO;
import com.coupon.entity.CouponVO;

import util.HibernateUtil;

public class CouponService {

	private CouponDAO dao;

	public CouponService() {
		dao = new CouponHBDAO();
	}
	public boolean isCouponCodeUnique(String couponCode,Integer couponMyId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Boolean isUnique = false;
		try {
			
		session.beginTransaction();
		 isUnique =  dao.isCouponCodeUnique(couponCode,couponMyId);
		session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return isUnique;

	}

	public CouponVO addCoupon(String couponName, String couponCode, Date startTime, Date endTime,
			Integer minSpendingAmount, Integer couponQuantity, Integer memberAllowQuantity, Integer couponDiscount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		CouponVO couponVO = null;
		try {
			session.beginTransaction();

			couponVO = new CouponVO();

			couponVO.setCouponName(couponName);
			couponVO.setCouponCode(couponCode);
			couponVO.setStartTime(startTime);
			couponVO.setEndTime(endTime);
			couponVO.setMinSpendingAmount(minSpendingAmount);
			couponVO.setCouponQuantity(couponQuantity);
			couponVO.setMemberAllowQuantity(memberAllowQuantity);
			couponVO.setCouponDiscount(couponDiscount);
			
			dao.insert(couponVO);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return couponVO;
	}

	public CouponVO updateCoupon(Integer couponId, String couponName, String couponCode, Date startTime, Date endTime,
			Integer minSpendingAmount, Integer couponQuantity, Integer memberAllowQuantity, Integer couponDiscount, Date couponCreateTime) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		CouponVO couponVO = null;

		try {
			session.beginTransaction();
			couponVO = new CouponVO();

			couponVO.setCouponId(couponId);
			couponVO.setCouponName(couponName);
			couponVO.setCouponCode(couponCode);
			couponVO.setStartTime(startTime);
			couponVO.setEndTime(endTime);
			couponVO.setMinSpendingAmount(minSpendingAmount);
			couponVO.setCouponQuantity(couponQuantity);
			couponVO.setMemberAllowQuantity(memberAllowQuantity);
			couponVO.setCouponDiscount(couponDiscount);
			couponVO.setCouponCreateTime(couponCreateTime);

			dao.update(couponVO);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return couponVO;
	}

	public void deleteCoupon(Integer couponId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			dao.delete(couponId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public CouponVO getOneCoupon(Integer couponId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		CouponVO couponVO = null;

		try {
			session.beginTransaction();
			 couponVO=dao.findByPrimaryKey(couponId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return couponVO;
	}

	public List<CouponVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CouponVO> list = Collections.emptyList() ;
		
		try {
			session.beginTransaction();
			 list =dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public int getTotal() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			return dao.getTotal();
		} catch (Exception e) {
			e.printStackTrace();
			return 0; // or handle as needed
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public List<CouponVO> getCouponsByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();

		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		   List<CouponVO> result = Collections.emptyList();
		try {
			session.beginTransaction();
			result = dao.getByCompositeQuery(query);
	        session.getTransaction().commit();
			return (result);
		}
		catch (Exception e) {
			e.printStackTrace();
			 
		} finally {
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}
	
	
//	public static void main(String[] args) {
//        // 添加Coupon
//        addCouponTest();
//
//        // 更新Coupon
//        updateCouponTest();
//
//        // 获取所有Coupons
//        getAllCouponsTest();
//
//        // 根据条件查询Coupons
//        getCouponsByCompositeQueryTest();
//
//        // 删除Coupon
//        deleteCouponTest();
//    }
//
//    private static void addCouponTest() {
//        CouponService couponService = new CouponService();
//
//        Date startTime = new Date();
//        Date endTime = new Date();
//
//        CouponVO coupon = couponService.addCoupon("TestCoupon", "TEST12SSS3", startTime, endTime, 100, 10, 5, 20);
//
//        if (coupon != null) {
//            System.out.println("Coupon added successfully: " + coupon);
//        } else {
//            System.out.println("Failed to add Coupon.");
//        }
//    }
//
//    private static void updateCouponTest() {
//        CouponService couponService = new CouponService();
//
//        Date startTime = new Date();
//        Date endTime = new Date();
//
//        // Assuming you have a couponId available for updating
//        Integer couponIdToUpdate = 6;
//
//        CouponVO updatedCoupon = couponService.updateCoupon(couponIdToUpdate, "UpdatedCoupon", "UPDATED123",
//                startTime, endTime, 150, 15, 8, 25);
//
//        if (updatedCoupon != null) {
//            System.out.println("Coupon updated successfully: " + updatedCoupon);
//        } else {
//            System.out.println("Failed to update Coupon.");
//        }
//    }
//
//    private static void getAllCouponsTest() {
//        CouponService couponService = new CouponService();
//
//        List<CouponVO> allCoupons = couponService.getAll();
//
//        System.out.println("All Coupons:");
//        for (CouponVO coupon : allCoupons) {
//            System.out.println(coupon);
//        }
//    }
//
//    private static void getCouponsByCompositeQueryTest() {
//        CouponService couponService = new CouponService();
//
//        // Assuming you have a map with query parameters
//        Map<String, String[]> queryParams = new HashMap<>();
//        queryParams.put("couponName", new String[]{"TestCoupon"});
//
//        List<CouponVO> filteredCoupons = couponService.getCouponsByCompositeQuery(queryParams);
//
//        System.out.println("Filtered Coupons:");
//        for (CouponVO coupon : filteredCoupons) {
//            System.out.println(coupon);
//        }
//    }
//
//    private static void deleteCouponTest() {
//        CouponService couponService = new CouponService();
//
//        // Assuming you have a couponId available for deletion
//        Integer couponIdToDelete = 8;
//
//        couponService.deleteCoupon(couponIdToDelete);
//
//        System.out.println("Coupon deleted successfully.");
//    }

}
