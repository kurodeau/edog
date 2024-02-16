package com.buyer.dao;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.buyer.entity.BuyerVO;

import util.HibernateUtil;

public class BuyerHBDAO implements BuyerDAO_interface {

	private SessionFactory factory;

	public BuyerHBDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(BuyerVO buyer) {
		return (Integer) getSession().save(buyer);
	}

	@Override
	public Integer update(BuyerVO buyer) {
		try {
			getSession().update(buyer);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer delete(Integer buyerId) {
		BuyerVO buyer = getSession().get(BuyerVO.class, buyerId);
		try {
			if (buyer != null) {
				getSession().delete(buyerId);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public BuyerVO findByPrimaryKey(Integer buyerId) {
		return getSession().get(BuyerVO.class, buyerId);
	}

	@Override
	public List<BuyerVO> getAll() {
		return getSession().createQuery("from BuyerVO", BuyerVO.class).list();
	}

	@Override
	public Integer getTotal() {
		Long total = getSession().createQuery("select count(*) from BuyerVO", Long.class).uniqueResult();
		return total.intValue();
	}

//	@Override
//	public List<BuyerVO> getByCompositeQuery(Map<String, String> map) {
//		if (map.size() == 0) {
//			return getAll();
//		}
//
//		CriteriaBuilder builder = getSession().getCriteriaBuilder();
//		CriteriaQuery<BuyerVO> criteria = builder.createQuery(BuyerVO.class);
//		Root<BuyerVO> root = criteria.from(BuyerVO.class);
//
//		List<Predicate> predicates = new ArrayList<>();
//
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//
//		java.util.Date startdate =null;
//		java.util.Date enddate = null;
//		try {
//			startdate = map.containsKey("startdate") ? dateFormat.parse(map.get("startdate")) : null;
//			enddate= map.containsKey("enddate") ? dateFormat.parse(map.get("enddate")) : null;
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		// 注意以下
//		if (map.containsKey("startdate") && map.containsKey("enddate")) {
//
//
//			predicates.add(builder.between(root.get("buyerCreateTime"), startdate, enddate));
//		}
//
//		if (map.containsKey("buyerLvId")) {
//			predicates.add(builder.equal(root.get("buyerLvId"), Integer.valueOf(map.get("buyerLvId"))));
//		}
//
//		for (Map.Entry<String, String> row : map.entrySet()) {
//			if ("buyerCompany".equals(row.getKey())) {
//				predicates.add(builder.like(root.get("buyerCompany"), "%" + row.getValue() + "%"));
//			}
//
//			if ("startdate".equals(row.getKey())) {
//				if (!map.containsKey("enddate")) {
//					predicates.add(builder.greaterThanOrEqualTo(root.get("buyerCreateTime"),startdate));
//				}
//			}
//
//			if ("enddate".equals(row.getKey())) {
//				if (!map.containsKey("startdate")) {
//					predicates.add(
//							builder.lessThanOrEqualTo(root.get("buyerCreateTime"), enddate));
//				}
//			}
//
//		}
//
//		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		criteria.orderBy(builder.asc(root.get("buyerId")));
//		TypedQuery<BuyerVO> query = getSession().createQuery(criteria);
//		System.out.println("===============");
//		System.out.println(query.getResultList());
//		return query.getResultList();
//
//	}

//============ main方法測試 ============
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		// ================Insert==============

		Query query = session.createQuery("FROM buyerLvVO WHERE buyerLvId = :id");
		query.setParameter("id", 1);
		
		BuyerVO buyer1 = new BuyerVO();
		
		// private Integer memberId; 自增鍵不能改
		
		// private String memberEmail;
		buyer1.setMemberEmail("buyer2@example.com");
		
		// private String thirdFrom; 之後串第三方再回頭弄
		
		// private String memberName;
		buyer1.setMemberName("測試DAO名稱");
		
		// private String memberPhone;
		buyer1.setMemberPhone("22345678");
		
		// private String mobile;
		buyer1.setMobile("0912345678");

		// private Date memberBirthday;
//		LocalDateTime currentDateTime = LocalDateTime.now();
//		String currentDate2 = currentDateTime.toString();
//		Date currentDate3 = Date.valueOf( currentDate2 );
		// 下二等同上三
		LocalDateTime currentDateTime = LocalDateTime.now();
		Date currentDate = Date.valueOf( currentDateTime.toString() );
		buyer1.setMemberBirthday( currentDate );
		
		// private String memberPassword;
		buyer1.setMemberPassword("ps1234");
		
		// private String memberAddress;
		buyer1.setMemberAddress("台北市測試一區");

		// private Boolean isMemberEmail;
		buyer1.setIsMemberEmail(true);
		
		// private Date memberRegistrationTime; 創建時間沒道理改
		
		// private String petName;
		buyer1.setPetName("小狗勾");

		// private String petVaccName1;
		buyer1.setPetVaccName1("心絲蟲疫苗");
		
		// private Date petVaccTime1;
		buyer1.setPetVaccTime1( Date.valueOf( "2025-01-30" ) );
		
		// private String petVaccName2;
		buyer1.setPetVaccName2("狂犬病疫苗");
		
		// private Date petVaccTime2;
		buyer1.setPetVaccTime2( Date.valueOf( "2025-12-30" ) );
		
		// private byte[] petImg; //圖片還沒做, 我就先測試
		
		// private Date petImgUploadTime;
		LocalDateTime currentDateTimePet = LocalDateTime.now();
		Date currentDatePet = Date.valueOf( currentDateTime.toString() );
		buyer1.setPetImgUploadTime( currentDatePet );
		
		// private Boolean isConfirm;
		buyer1.setIsConfirm( false );

		Serializable buyerId = session.save(buyer1);

		// ================Update==============

//	BuyerVO buyerToUpdate = session.get(BuyerVO.class, (Integer) buyerId);
//	buyerToUpdate.setbuyerCompany("New Company Name");
//	session.update(buyerToUpdate);

		// ================Query==============
//	String buyerLvName = buyer1.getbuyerId().getName();
//	System.out.println("buyerLvName: " + buyerLvName);

		// ================Delete==============

//		BuyerVO buyerToDelete = session.get(BuyerVO.class, (Integer) buyerId);
//		session.delete(buyerToDelete);

		session.getTransaction().commit();

	}

}
