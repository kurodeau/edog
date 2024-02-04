package com.seller.dao;

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

import com.seller.dao.SellerDAO;
import com.seller.entity.SellerVO;

import util.Util;
public class SellerDSDAO implements SellerDAO {
	public SellerDSDAO() {
		super();
	}
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Util.DS_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// ename,job,hiredate,sal,comm,deptno
	private static final String INSERT_STMT = "INSERT INTO edog.seller (sellerLvId, sellerEmail, sellerCompany, sellerTaxId, sellerCapital, sellerContact, sellerCompanyPhone, sellerCompanyExtension, sellerMobile, sellerAddress, sellerPassword, sellerBankAccount, sellerBankCode, sellerBankAccountNumber,  isConfirm) VALUES (1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, FALSE)";
	private static final String GET_ALL_STMT = "SELECT * FROM edog.seller ORDER BY sellerId";
	private static final String GET_ONE_STMT = "SELECT * FROM edog.seller where sellerId = ?";
	private static final String DELETE = "DELETE FROM edog.seller where sellerId = ?";
	private static final String UPDATE = "UPDATE edog.seller SET sellerLvId=?, sellerEmail=?, sellerCompany=?, sellerTaxId=?, sellerCapital=?, sellerContact=?, "
	        + "sellerCompanyPhone=?, sellerCompanyExtension=?, sellerMobile=?, sellerAddress=?, sellerPassword=?, sellerBankAccount=?, "
	        + "sellerBankCode=?, sellerBankAccountNumber=?, sellerCreateTime=?, isConfirm=? WHERE sellerLvId = ?";
	
	private static final String UPDATE_SELLER = "UPDATE edog.seller SET sellerLvId=?, sellerEmail=?, sellerCompany=?, sellerTaxId=?, sellerCapital=?, sellerContact=?, "
	        + "sellerCompanyPhone=?, sellerCompanyExtension=?, sellerMobile=?, sellerAddress=?, sellerPassword=?, sellerBankAccount=?, "
	        + "sellerBankCode=?, sellerBankAccountNumber=?, sellerCreateTime=?, isConfirm=? WHERE sellerLvId = ?";

	@Override
	public void insert(SellerVO sellerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, sellerVO.getSellerEmail());
			pstmt.setString(2, sellerVO.getSellerCompany());
			pstmt.setString(3, sellerVO.getSellerTaxId());
			pstmt.setInt(4, sellerVO.getSellerCapital());
			pstmt.setString(5, sellerVO.getSellerContact());
			pstmt.setString(6, sellerVO.getSellerCompanyPhone());
			pstmt.setString(7, sellerVO.getSellerCompanyExtension());
			pstmt.setString(8, sellerVO.getSellerMobile());
			pstmt.setString(9, sellerVO.getSellerAddress());
			pstmt.setString(10, sellerVO.getSellerPassword());
			pstmt.setString(11, sellerVO.getSellerBankAccount());
			pstmt.setString(12, sellerVO.getSellerBankCode());
			pstmt.setString(13, sellerVO.getSellerBankAccountNumber());

			int success = pstmt.executeUpdate();

			System.out.println("success " + success);


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
	public void update(SellerVO sellerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			System.out.println(sellerVO);

			pstmt.setInt(1, sellerVO.getSellerLvId());
			pstmt.setString(2, sellerVO.getSellerEmail());
			pstmt.setString(3, sellerVO.getSellerCompany());
			pstmt.setString(4, sellerVO.getSellerTaxId());
			pstmt.setInt(5, sellerVO.getSellerCapital());
			pstmt.setString(6, sellerVO.getSellerContact());
			pstmt.setString(7, sellerVO.getSellerCompanyPhone());
			pstmt.setString(8, sellerVO.getSellerCompanyExtension());
			pstmt.setString(9, sellerVO.getSellerMobile());
			pstmt.setString(10, sellerVO.getSellerAddress());
			pstmt.setString(11, sellerVO.getSellerPassword());
			pstmt.setString(12, sellerVO.getSellerBankAccount());
			pstmt.setString(13, sellerVO.getSellerBankCode());
			pstmt.setString(14, sellerVO.getSellerBankAccountNumber());
			pstmt.setDate(15, sellerVO.getSellerCreateTime());
			pstmt.setBoolean(16, sellerVO.getIsConfirm());
			
			pstmt.setInt(17, sellerVO.getSellerId());

			int success = pstmt.executeUpdate();

			System.out.println("success " + success);

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
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

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
	public SellerVO findByPrimaryKey(Integer sellerId) {

		SellerVO sellerVO  = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sellerId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sellerVO 也稱為 Domain objects
				sellerVO = new SellerVO();
				sellerVO.setSellerId(rs.getInt("sellerId"));
				sellerVO.setSellerLvId(rs.getInt("sellerLvId"));
				sellerVO.setSellerEmail(rs.getString("sellerEmail"));
				sellerVO.setSellerCompany(rs.getString("sellerCompany"));
				sellerVO.setSellerTaxId(rs.getString("sellerTaxId"));
				sellerVO.setSellerCapital(rs.getInt("sellerCapital"));
				sellerVO.setSellerContact(rs.getString("sellerContact"));
				sellerVO.setSellerCompanyPhone(rs.getString("sellerCompanyPhone"));
				sellerVO.setSellerCompanyExtension(rs.getString("sellerCompanyExtension"));
				sellerVO.setSellerMobile(rs.getString("sellerMobile"));
				sellerVO.setSellerAddress(rs.getString("sellerAddress"));
				sellerVO.setSellerPassword(rs.getString("sellerPassword"));
				sellerVO.setSellerBankAccount(rs.getString("sellerBankAccount"));
				sellerVO.setSellerBankCode(rs.getString("sellerBankCode"));
				sellerVO.setSellerBankAccountNumber(rs.getString("sellerBankAccountNumber"));
				sellerVO.setSellerCreateTime(rs.getDate("sellerCreateTime"));
				sellerVO.setIsConfirm(rs.getBoolean("isConfirm"));
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
		return sellerVO;
	}

	@Override
	public List<SellerVO> getAll() {
		
		
		
		List<SellerVO> list = new ArrayList<SellerVO>();
		SellerVO sellerVO = null;

		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// sellerVO 也稱為 Domain objects
				sellerVO = new SellerVO();
				sellerVO.setSellerId(rs.getInt("sellerId"));
				sellerVO.setSellerLvId(rs.getInt("sellerLvId"));
				sellerVO.setSellerEmail(rs.getString("sellerEmail"));
				sellerVO.setSellerCompany(rs.getString("sellerCompany"));
				sellerVO.setSellerTaxId(rs.getString("sellerTaxId"));
				sellerVO.setSellerCapital(rs.getInt("sellerCapital"));

				sellerVO.setSellerContact(rs.getString("sellerContact"));
				sellerVO.setSellerCompanyPhone(rs.getString("sellerCompanyPhone"));
				sellerVO.setSellerCompanyExtension(rs.getString("sellerCompanyExtension"));
				sellerVO.setSellerMobile(rs.getString("sellerMobile"));

				sellerVO.setSellerAddress(rs.getString("sellerAddress"));
				sellerVO.setSellerPassword(rs.getString("sellerPassword"));
				sellerVO.setSellerBankAccount(rs.getString("sellerBankAccount"));
				sellerVO.setSellerBankCode(rs.getString("sellerBankCode"));
				sellerVO.setSellerBankAccountNumber(rs.getString("sellerBankAccountNumber"));
				sellerVO.setSellerCreateTime(rs.getDate("sellerCreateTime"));
				sellerVO.setIsConfirm(rs.getBoolean("isConfirm"));

				list.add(sellerVO); // Store the row in the list
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
}