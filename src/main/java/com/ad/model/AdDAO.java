package com.ad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class AdDAO implements AdDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/edog");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO ad (sellerId,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adid,sellerId,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled FROM ad order by adid";
	private static final String GET_ONE_STMT = 
			"SELECT adid,sellerId,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled FROM ad where adid = ?";
	private static final String DELETE = 
			"DELETE FROM ad where adid = ?";
	private static final String UPDATE = 
			"UPDATE ad set sellerId=?,adImg=?,adImgUploadTime=?,adName=?,adUrl=?,adStartTime=?,adEndTime=?,adLv=?,adMemo=?,isAdConfirm=?,adCreateTime=?,isEnabled=? where adid = ?";

	@Override
	public void insert(AdVO adVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
				adVO.setAdUrl(rs.getString("adUrl"));
				adVO.setAdStartTime(rs.getTimestamp("adStartTime"));
				adVO.setAdEndTime(rs.getTimestamp("adEndTime"));
				adVO.setAdLv(rs.getInt("adLv"));
				adVO.setAdMemo(rs.getString("adMemo"));
				adVO.setIsAdConfirm(rs.getBoolean("isAdConfirm"));
				adVO.setAdCreateTime(rs.getTimestamp("adCreateTime"));
				adVO.setIsEnabled(rs.getBoolean("isEnabled"));
			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				adVO = new AdVO();
				adVO.setAdId(rs.getInt("adId"));
				adVO.setSellerId(rs.getInt("sellerId"));
				adVO.setAdImg(rs.getBytes("adImg"));
				adVO.setAdImgUploadTime(rs.getTimestamp("adImgUploadTime"));
				adVO.setAdName(rs.getString("adName"));
				adVO.setAdUrl(rs.getString("adUrl"));
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adid);

			pstmt.executeUpdate();

			// Handle any driver errors
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
}
