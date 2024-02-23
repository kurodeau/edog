package com.buyer.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.buyer.dao.BuyerDAO;
import com.buyer.dao.BuyerHBDAO;
import com.buyer.entity.BuyerVO;

import util.HibernateUtil;

public class BuyerService {
	
	private BuyerDAO dao;

	public BuyerService() {
		dao = new BuyerHBDAO();
	}
	
	public BuyerVO addBuyer(
//			Integer memberId, //自增鍵不用手動新增
			String memberEmail,
			String thirdFrom,
			String memberName,
			String memberPhone,
			String memberMobile,
			Date memberBirthday,
			String memberPassword,
			String memberAddress,
			Boolean isMemberEmail,
//			Date memberRegistrationTime, //自動新增日期不用手動新增
			String petName,
			byte[] petImg,
			Date petImgUploadTime,
			String petVaccName1,
			Date petVaccTime1,
			String petVaccName2,
			Date petVaccTime2,
			Boolean isConfirm
			) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		BuyerVO buyerVO = null;
		try {
			session.beginTransaction();

			buyerVO = new BuyerVO();
			
//			buyerVO.setMemberId(memberId); //自增建不允許手動新增
			buyerVO.setMemberEmail(memberEmail);
			buyerVO.setThirdFrom(thirdFrom);
			buyerVO.setMemberName(memberName);
			buyerVO.setMemberPhone(memberPhone);
			buyerVO.setMemberMobile(memberMobile);
			buyerVO.setMemberBirthday(memberBirthday);
			buyerVO.setMemberPassword(memberPassword);
			buyerVO.setMemberAddress(memberAddress);
			buyerVO.setIsMemberEmail(isMemberEmail);
//			buyerVO.setMemberRegistrationTime(memberRegistrationTime); //自動新增日期不用手動新增
			buyerVO.setPetName(petName);
			buyerVO.setPetImg(petImg); //有問題, 不知道怎麼處理
			buyerVO.setPetImgUploadTime(petImgUploadTime);
			buyerVO.setPetVaccName1(petVaccName1);
			buyerVO.setPetVaccTime1(petVaccTime1);
			buyerVO.setPetVaccName2(petVaccName2);
			buyerVO.setPetVaccTime2(petVaccTime2);
			buyerVO.setIsConfirm(isConfirm);
			
			dao.insert(buyerVO);
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
		return buyerVO;
	}

	public BuyerVO updateBuyer(
//			Integer memberId, //不允許更新Id
			String memberEmail,
			String thirdFrom,
			String memberName,
			String memberPhone,
			String memberMobile,
			Date memberBirthday,
			String memberPassword,
			String memberAddress,
			Boolean isMemberEmail,
//			Date memberRegistrationTime, //不允許更新創建用戶時間
			String petName,
			byte[] petImg,
			Date petImgUploadTime,
			String petVaccName1,
			Date petVaccTime1,
			String petVaccName2,
			Date petVaccTime2,
			Boolean isConfirm
			) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		BuyerVO buyerVO = null;

		try {
			session.beginTransaction();
			buyerVO = new BuyerVO();

//			buyerVO.setMemberId(memberId); //自增建不允許手動新增
			buyerVO.setMemberEmail(memberEmail);
			buyerVO.setThirdFrom(thirdFrom);
			buyerVO.setMemberName(memberName);
			buyerVO.setMemberPhone(memberPhone);
			buyerVO.setMemberMobile(memberMobile);
			buyerVO.setMemberBirthday(memberBirthday);
			buyerVO.setMemberPassword(memberPassword);
			buyerVO.setMemberAddress(memberAddress);
			buyerVO.setIsMemberEmail(isMemberEmail);
//			buyerVO.setMemberRegistrationTime(memberRegistrationTime); //自動新增日期不用手動新增
			buyerVO.setPetName(petName);
			buyerVO.setPetImg(petImg); //有問題, 不知道怎麼處理
			buyerVO.setPetImgUploadTime(petImgUploadTime);
			buyerVO.setPetVaccName1(petVaccName1);
			buyerVO.setPetVaccTime1(petVaccTime1);
			buyerVO.setPetVaccName2(petVaccName2);
			buyerVO.setPetVaccTime2(petVaccTime2);
			buyerVO.setIsConfirm(isConfirm);

			dao.update(buyerVO);
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
		return buyerVO;
	}

	public void deleteBuyer(Integer memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			dao.delete(memberId);
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

	public BuyerVO getOneBuyer(Integer memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		BuyerVO buyerVO = null;

		try {
			session.beginTransaction();
			buyerVO=dao.findByPrimaryKey(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return buyerVO;
	}

	public List<BuyerVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<BuyerVO> list = Collections.emptyList() ;
		
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

	public List<BuyerVO> getBuyersByCompositeQuery(Map<String, String[]> map) {
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
		   List<BuyerVO> result = Collections.emptyList();
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
