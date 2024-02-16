package com.ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class AdJDBCDAO implements AdDAO_interface {
	
	
	String driver =  "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/edog?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
//	private static final String INSERT_STMT = 
//			"INSERT INTO ad (sellerId,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT = 
			"INSERT INTO ad (sellerId,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adid,sellerId,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled FROM ad order by adid";
	private static final String GET_ONE_STMT = 
			"SELECT adid,sellerId,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled FROM ad where adid = ?";
	private static final String DELETE = 
			"DELETE FROM ad where adid = ?";
	private static final String UPDATE = 
			"UPDATE ad set sellerId=?,adImg=?,adImgUploadTime=?,adName=?,adUrl=?,adStartTime=?,adEndTime=?,adLv,adMemo=?,isAdConfirm=?,adCreateTime=?,isEnabled=? where adid = ?";
	
	@Override
	public void insert(AdVO adVO) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, adVO.getSellerId());
//			pstmt.setTimestamp(2, adVO.getAdImgUploadTime());
//			pstmt.setString(3, adVO.getAdName());
//			pstmt.setString(4, adVO.getAdUrl());
//			pstmt.setTimestamp(5, adVO.getAdStartTime());
//			pstmt.setTimestamp(6, adVO.getAdEndTime());
//			pstmt.setInt(7, adVO.getAdLv());
//			pstmt.setString(8, adVO.getAdMemo());
//			pstmt.setBoolean(9, adVO.getIsAdConfirm());
//			pstmt.setTimestamp(10, adVO.getAdCreateTime());
//			pstmt.setBoolean(11, adVO.getIsEnabled());
//			
			pstmt.setInt(1, adVO.getSellerId());
			pstmt.setBytes(2, adVO.getAdImg());
			pstmt.setTimestamp(3, adVO.getAdImgUploadTime());
			pstmt.setString(4, adVO.getAdName());
			pstmt.setString(5, adVO.getAdUrl());
			pstmt.setTimestamp(6, adVO.getAdStartTime());
			pstmt.setTimestamp(7, adVO.getAdEndTime());
			pstmt.setInt(8, adVO.getAdLv());
			pstmt.setString(9, adVO.getAdMemo());
			pstmt.setBoolean(10, adVO.getIsAdConfirm());
			pstmt.setTimestamp(11, adVO.getAdCreateTime());
			pstmt.setBoolean(12, adVO.getIsEnabled());
			
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
	public void update(AdVO adVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
		
			pstmt.setInt(1, adVO.getSellerId());
			pstmt.setBytes(2, adVO.getAdImg());
			pstmt.setTimestamp(3, adVO.getAdImgUploadTime());
			pstmt.setString(4, adVO.getAdName());
			pstmt.setString(5, adVO.getAdUrl());
			pstmt.setTimestamp(6, adVO.getAdStartTime());
			pstmt.setTimestamp(7, adVO.getAdEndTime());
			pstmt.setInt(8, adVO.getAdLv());
			pstmt.setString(9, adVO.getAdMemo());
			pstmt.setBoolean(10, adVO.getIsAdConfirm());
			pstmt.setTimestamp(11, adVO.getAdCreateTime());
			pstmt.setBoolean(12, adVO.getIsEnabled());
			pstmt.setInt(13, adVO.getAdId());
			
			
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
	public AdVO findByPrimaryKey(Integer adId) {
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adId);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				adVO  = new AdVO();
				adVO.setAdId(rs.getInt("adId"));
				adVO.setSellerId(rs.getInt("sellerId"));
				adVO.setAdImg(rs.getBytes("adImg"));
				adVO.setAdImgUploadTime(rs.getTimestamp("adImgUploadTime"));
				adVO.setAdName(rs.getString("adName"));
				adVO.setAdUrl(rs.getString("String"));
				adVO.setAdStartTime(rs.getTimestamp("adStartTime"));
				adVO.setAdEndTime(rs.getTimestamp("adEndTime"));
				adVO.setAdLv(rs.getInt("adLv"));
				adVO.setAdMemo(rs.getString("adMemo"));
				adVO.setIsAdConfirm(rs.getBoolean("isAdConfirm"));
				adVO.setAdCreateTime(rs.getTimestamp("adCreateTime"));
				adVO.setIsEnabled(rs.getBoolean("isEnabled"));
				
				
				
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
		return adVO;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AdVO �]�٬� Domain objects
				adVO = new AdVO();
				adVO.setAdId(rs.getInt("adId"));
				adVO.setSellerId(rs.getInt("sellerId"));
				adVO.setAdImg(rs.getBytes("adImg"));
				adVO.setAdImgUploadTime(rs.getTimestamp("adImgUploadTime"));
				adVO.setAdName(rs.getString("adName"));
				adVO.setAdUrl(rs.getString("AdUrl"));
				adVO.setAdStartTime(rs.getTimestamp("adStartTime"));
				adVO.setAdEndTime(rs.getTimestamp("adEndTime"));
				adVO.setAdLv(rs.getInt("adLv"));
				adVO.setAdMemo(rs.getString("adMemo"));
				adVO.setIsAdConfirm(rs.getBoolean("isAdConfirm"));
				adVO.setAdCreateTime(rs.getTimestamp("adCreateTime"));
				adVO.setIsEnabled(rs.getBoolean("isEnabled"));
				list.add(adVO); // Store the row in the list
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
	
	@Override
	public void delete(Integer adid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adid);

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
	
	public static void main(String[] args) {
	     AdJDBCDAO dao = new AdJDBCDAO();

		// �s�W
		AdVO adVO1 = new AdVO();
		LocalDateTime currentDateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(currentDateTime);
				
        adVO1.setSellerId(123);
//		adVO1.setAdImg();		
		adVO1.setAdImgUploadTime(timestamp);
		adVO1.setAdName("123");
		adVO1.setAdUrl("123");
		adVO1.setAdStartTime(timestamp);
		adVO1.setAdEndTime(timestamp);
		adVO1.setAdLv(1);
		adVO1.setAdMemo("123");
		adVO1.setIsAdConfirm(true);
		adVO1.setAdCreateTime(timestamp);
		adVO1.setIsEnabled(true);
		dao.insert(adVO1);
		
	
	
	// �d��
//	List<AdVO> list = dao.getAll();
//	for (AdVO ad : list) {
//		System.out.print(ad.getAdId() + ",");
//		System.out.print(ad.getAdMemo() + ",");
		
		
//	}
	
	}


}
	

