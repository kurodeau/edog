package com.seller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seller.entity.__SellerVO;

public class __SellerJDBCDAO implements __SellerDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/edog?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	// 預設sellerLvId == 1 && isConfirm == false && sellerCreateTime = DEAAULT (curDate)
	private static final String INSERT_STMT = "INSERT INTO seller (sellerLvId, sellerEmail, sellerCompany, sellerTaxId, sellerCapital, sellerContact, sellerCompanyPhone, sellerCompanyExtension, sellerMobile, sellerAddress, sellerPassword, sellerBankAccount, sellerBankCode, sellerBankAccountNumber,  isConfirm) VALUES (1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, FALSE)";
	private static final String GET_ALL_STMT = "SELECT * FROM seller ORDER BY sellerId";
	private static final String GET_ONE_STMT = "SELECT * FROM seller where sellerId = ?";
	private static final String DELETE = "DELETE FROM seller where sellerId = ?";
	private static final String UPDATE = "UPDATE seller SET sellerLvId=?, sellerEmail=?, sellerCompany=?, sellerTaxId=?, sellerCapital=?, sellerContact=?, "
			+ "sellerCompanyPhone=?, sellerCompanyExtension=?, sellerMobile=?, sellerAddress=?, sellerPassword=?, sellerBankAccount=?, "
			+ "sellerBankCode=?, sellerBankAccountNumber=?, sellerCreateTime=?, isConfirm=? WHERE sellerId = ?";

	
	public Integer insert(__SellerVO sellerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			// 預設sellerLvId == 1 && isConfirm == false && sellerCreateTime = DEAAULT (curDate)
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

			if (success == 1) {
	            // Retrieve the generated key
	            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    return generatedKeys.getInt(1); // Return the generated key (sellerId)
	                } else {
	                    throw new SQLException("Failed to get the generated key.");
	                }
	            }
	        } else {
	            throw new SQLException("Insert failed, no rows affected.");
	        }

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public Integer update(__SellerVO sellerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

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


	        if (success == 1) {
	            return success; // Return the number of affected rows
	        }
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
    	return -1;
	}

	public Integer delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

			 int success =pstmt.executeUpdate();
			 

		        if (success == 1) {
		            return success; // Return the number of affected rows
		        } 

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return -1;

	}

	@Override
	public __SellerVO findByPrimaryKey(Integer sellerId) {

		__SellerVO sellerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sellerId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sellerVO 也稱為 Domain objects
				sellerVO = new __SellerVO();
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<__SellerVO> getAll() {
		List<__SellerVO> list = new ArrayList<__SellerVO>();
		__SellerVO sellerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sellerVO 也稱為 Domain objects
				sellerVO = new __SellerVO();
				// 安全寫法，但GC會忙
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		__SellerJDBCDAO dao = new __SellerJDBCDAO();

		// 新增
//		sellerVO sellerVO1 = new sellerVO();
//		// setSellerLVID有預設		
//		//sellerVO1.setSellerLvId(1);
//		sellerVO1.setSellerEmail("seller2@example.com");
//		sellerVO1.setSellerCompany("Test Company");
//		sellerVO1.setSellerTaxId("123S");
//		sellerVO1.setSellerCapital(500000);
//		sellerVO1.setSellerContact("John Doe");
//		sellerVO1.setSellerCompanyPhone("0223456789");
//		sellerVO1.setSellerCompanyExtension("123");
//		sellerVO1.setSellerMobile("0912345678");
//		sellerVO1.setSellerAddress("台北市中正區1號");
//		sellerVO1.setSellerPassword("password1");
//		sellerVO1.setSellerBankAccount("123-456-789");
//		sellerVO1.setSellerBankCode("012");
//		sellerVO1.setSellerBankAccountNumber("98765432");
//		// isConfirm 跟 createTime 有預設
//		//sellerVO1.setIsConfirm(false);
//		//sellerVO1.setSellercreateTime();
//
//		
//		dao.insert(sellerVO1);

//		// 修改
//		sellerVO sellerVO2 = new sellerVO();
//		sellerVO2.setSellerLvId(1);
//		sellerVO2.setSellerEmail("updatedEmail@example.com");
//		sellerVO2.setSellerCompany("Updated Company");
//		sellerVO2.setSellerTaxId("U1234567");
//		sellerVO2.setSellerCapital(800000);
//		sellerVO2.setSellerContact("Updated Contact");
//		sellerVO2.setSellerCompanyPhone("0223456789");
//		sellerVO2.setSellerCompanyExtension("456");
//		sellerVO2.setSellerMobile("0987654321");
//		sellerVO2.setSellerAddress("Updated Address");
//		sellerVO2.setSellerPassword("updatedPassword");
//		sellerVO2.setSellerBankAccount("987-654-321");
//		sellerVO2.setSellerBankCode("789");
//		sellerVO2.setSellerBankAccountNumber("65432109");
//		sellerVO2.setSellerCreateTime(java.sql.Date.valueOf("2023-12-15"));
//		sellerVO2.setIsConfirm(true);
//		dao.update(sellerVO2);
//
//		// 刪除
//		dao.delete(1);
//
//		// 查詢
//		sellerVO sellerVO3 = dao.findByPrimaryKey(11);
//		System.out.print("SellerId: " + sellerVO3.getSellerId() + ", ");
//		System.out.print("SellerLvId: " + sellerVO3.getSellerLvId() + ", ");
//		System.out.print("SellerEmail: " + sellerVO3.getSellerEmail() + ", ");
//		System.out.print("SellerCompany: " + sellerVO3.getSellerCompany() + ", ");
//		// 新版本的SQL CONNECTOR 似乎已經針對 rs.getString優化
//		// SQL會自動對CHAR space padding
//		// 但先把字串TRIM()再回傳，而非像舊版本回傳有空格
//		System.out.print("SellerTaxId: " + sellerVO3.getSellerTaxId() + ", ");
//		System.out.print("SellerCapital: " + sellerVO3.getSellerCapital() + ", ");
//		System.out.print("SellerContact: " + sellerVO3.getSellerContact() + ", ");
//		System.out.print("SellerCompanyPhone: " + sellerVO3.getSellerCompanyPhone() + ", ");
//		System.out.print("SellerCompanyExtension: " + sellerVO3.getSellerCompanyExtension() + ", ");
//		System.out.print("SellerMobile: " + sellerVO3.getSellerMobile() + ", ");
//		System.out.print("SellerAddress: " + sellerVO3.getSellerAddress() + ", ");
//		System.out.print("SellerPassword: " + sellerVO3.getSellerPassword() + ", ");
//		System.out.print("SellerBankAccount: " + sellerVO3.getSellerBankAccount() + ", ");
//		System.out.print("SellerBankCode: " + sellerVO3.getSellerBankCode() + ", ");
//		System.out.print("SellerBankAccountNumber: " + sellerVO3.getSellerBankAccountNumber() + ", ");
//		System.out.print("SellerCreateTime: " + sellerVO3.getSellerCreateTime() + ", ");
//		System.out.println("IsConfirm: " + sellerVO3.getIsConfirm());
//		System.out.println("---------------------");
//
//		// 查詢
		List<__SellerVO> list = dao.getAll();
		for (__SellerVO seller : list) {
			System.out.print(seller.getSellerId() + ",");
			System.out.print(seller.getSellerLvId() + ",");
			System.out.print(seller.getSellerEmail() + ",");
			System.out.print(seller.getSellerCompany() + ",");
			System.out.print(seller.getSellerTaxId() + ",");
			System.out.print(seller.getSellerCapital() + ",");
			System.out.print(seller.getSellerContact() + ",");
			System.out.print(seller.getSellerCompanyPhone() + ",");
			System.out.print(seller.getSellerCompanyExtension() + ",");
			System.out.print(seller.getSellerMobile() + ",");
			System.out.print(seller.getSellerAddress() + ",");
			System.out.print(seller.getSellerPassword() + ",");
			System.out.print(seller.getSellerBankAccount() + ",");
			System.out.print(seller.getSellerBankCode() + ",");
			System.out.print(seller.getSellerBankAccountNumber() + ",");
			System.out.print(seller.getSellerCreateTime() + ",");
			System.out.println(seller.getIsConfirm());
		}
	}
}