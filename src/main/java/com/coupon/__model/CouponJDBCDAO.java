package com.coupon.model;

import java.util.*;
import java.sql.*;

public class CouponJDBCDAO implements CouponDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/edog?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

//	ename,job,hiredate,sal,comm,deptno
//	couponId,couponName,couponCode,startTime,endTime,minSpendingAmount,couponQuantity,memberAllowQuantity,couponDiscount,couponCreateTime
	
	private static final String INSERT_STMT = 
		"INSERT INTO coupon (couponName,couponCode,startTime,endTime,minSpendingAmount,couponQuantity,memberAllowQuantity,couponDiscount,couponCreateTime"
		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT couponId,couponName,couponCode,startTime,endTime,minSpendingAmount,couponQuantity,memberAllowQuantity,couponDiscount,couponCreateTime FROM coupon order by couponId";
	private static final String GET_ONE_STMT = 
		"SELECT couponId,couponName,couponCode,startTime,endTime,minSpendingAmount,couponQuantity,memberAllowQuantity,couponDiscount,couponCreateTime FROM coupon where couponId = ?";
	private static final String DELETE = 
		"DELETE FROM coupon where couponId = ?";
	
	private static final String UPDATE = 
		    "UPDATE coupon " +
		    "SET couponName=?, " +
		    "    couponCode=?, " +
		    "    startTime=?, " +
		    "    endTime=?, " +
		    "    minSpendingAmount=?, " +
		    "    couponQuantity=?, " +
		    "    memberAllowQuantity=?, " +
		    "    couponDiscount=?, " +
		    "    couponCreateTime=? " +
		    "WHERE couponId = ?";


	@Override
	public void insert(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

		    pstmt.setString(1, couponVO.getCouponName());
		    pstmt.setString(2, couponVO.getCouponCode());
		    pstmt.setTimestamp(3, couponVO.getStartTime());
		    pstmt.setTimestamp(4, couponVO.getEndTime());
		    pstmt.setInt(5, couponVO.getMinSpendingAmount());
		    pstmt.setInt(6, couponVO.getCouponQuantity());
		    pstmt.setInt(7, couponVO.getMemberAllowQuantity());
		    pstmt.setInt(8, couponVO.getCouponDiscount());
		    pstmt.setTimestamp(9, couponVO.getCouponCreateTime());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

		    pstmt.setString(1, couponVO.getCouponName());
		    pstmt.setString(2, couponVO.getCouponCode());
		    pstmt.setTimestamp(3, couponVO.getStartTime());
		    pstmt.setTimestamp(4, couponVO.getEndTime());
		    pstmt.setInt(5, couponVO.getMinSpendingAmount());
		    pstmt.setInt(6, couponVO.getCouponQuantity());
		    pstmt.setInt(7, couponVO.getMemberAllowQuantity());
		    pstmt.setInt(8, couponVO.getCouponDiscount());
		    pstmt.setTimestamp(9, couponVO.getCouponCreateTime());
		    pstmt.setInt(10, couponVO.getCouponId());
		    
		    int rowsUpdated = pstmt.executeUpdate();
		    System.out.println(rowsUpdated == 0 ? "沒有更改" : "更改" + rowsUpdated + "筆資料");

			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer couponId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, couponId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CouponVO findByPrimaryKey(Integer couponId) {

		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, couponId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				couponVO = new CouponVO();
				
				
				couponVO.setCouponId(rs.getInt("couponId"));
				couponVO.setCouponName(rs.getString("couponName"));
				couponVO.setCouponCode(rs.getString("couponCode"));
				couponVO.setStartTime(rs.getTimestamp("startTime"));
				couponVO.setEndTime(rs.getTimestamp("endTime"));
				couponVO.setMinSpendingAmount(rs.getInt("minSpendingAmount"));
				couponVO.setCouponQuantity(rs.getInt("couponQuantity"));
				couponVO.setMemberAllowQuantity(rs.getInt("memberAllowQuantity"));
				couponVO.setCouponDiscount(rs.getInt("couponDiscount"));
				couponVO.setCouponCreateTime(rs.getTimestamp("couponCreateTime"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// couponVO 也稱為 Domain objects
				couponVO = new CouponVO();
				couponVO.setCouponId(rs.getInt("couponId"));
				couponVO.setCouponName(rs.getString("couponName"));
				couponVO.setCouponCode(rs.getString("couponCode"));
				couponVO.setStartTime(rs.getTimestamp("startTime"));
				couponVO.setEndTime(rs.getTimestamp("endTime"));
				couponVO.setMinSpendingAmount(rs.getInt("minSpendingAmount"));
				couponVO.setCouponQuantity(rs.getInt("couponQuantity"));
				couponVO.setMemberAllowQuantity(rs.getInt("memberAllowQuantity"));
				couponVO.setCouponDiscount(rs.getInt("couponDiscount"));
				couponVO.setCouponCreateTime(rs.getTimestamp("couponCreateTime"));
				
				list.add(couponVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		CouponJDBCDAO dao = new CouponJDBCDAO();

		// 新增
//		CouponVO couponVO1 = new CouponVO();
//		couponVO1.setCouponName("Discount Coupon 1");
//		couponVO1.setCouponCode("FAKECODE001");
//		couponVO1.setStartTime(Timestamp.valueOf("2024-01-22 12:00:00"));
//		couponVO1.setEndTime(Timestamp.valueOf("2024-02-22 23:59:59"));
//		couponVO1.setMinSpendingAmount(50);
//		couponVO1.setCouponQuantity(100);
//		couponVO1.setMemberAllowQuantity(1);
//		couponVO1.setCouponDiscount(10);
//		couponVO1.setCouponCreateTime(Timestamp.valueOf("2024-01-22 08:00:00"));
//		dao.insert(couponVO1);

		// 修改
//		CouponVO couponVO2 = new CouponVO();
//		couponVO2.setCouponId(1);
//		couponVO2.setCouponName("吳永志2");
//		couponVO2.setCouponCode("MANAGER2");
//		couponVO2.setStartTime(java.sql.Timestamp.valueOf("2002-01-01 00:00:00"));
//		couponVO2.setEndTime(java.sql.Timestamp.valueOf("2002-01-01 00:00:00"));
//		couponVO2.setMinSpendingAmount(20000);
//		couponVO2.setCouponQuantity(200);
//		couponVO2.setMemberAllowQuantity(20);
//		couponVO2.setCouponDiscount(200);
//		couponVO2.setCouponCreateTime(java.sql.Timestamp.valueOf("2002-01-01 00:00:00"));
//
//		dao.update(couponVO2);
		

		// 刪除
//		dao.delete(2);

		// 查詢
//		CouponVO couponVO3 = dao.findByPrimaryKey(1);
//		System.out.println(couponVO3.getCouponId() + ",");
//		System.out.println(couponVO3.getCouponName() + ",");
//		System.out.println(couponVO3.getCouponCode() + ",");
//		System.out.println(couponVO3.getStartTime() + ",");
//		System.out.println(couponVO3.getEndTime() + ",");
//		System.out.println(couponVO3.getMinSpendingAmount() + ",");
//		System.out.println(couponVO3.getCouponQuantity() + ",");
//		System.out.println(couponVO3.getMemberAllowQuantity() + ",");
//		System.out.println(couponVO3.getCouponDiscount() + ",");
//		System.out.println(couponVO3.getCouponCreateTime());

//		System.out.println("---------------------");

		// 查詢
	
//		List<CouponVO> list = dao.getAll();
//		for (CouponVO couponVO : list) {
//		    System.out.print(couponVO.getCouponId() + ",");
//		    System.out.print(couponVO.getCouponName() + ",");
//		    System.out.print(couponVO.getCouponCode() + ",");
//		    System.out.print(couponVO.getStartTime() + ",");
//		    System.out.print(couponVO.getEndTime() + ",");
//		    System.out.print(couponVO.getMinSpendingAmount() + ",");
//		    System.out.print(couponVO.getCouponQuantity() + ",");
//		    System.out.print(couponVO.getMemberAllowQuantity() + ",");
//		    System.out.print(couponVO.getCouponDiscount() + ",");
//		    System.out.println(couponVO.getCouponCreateTime());
//		}

	}
}