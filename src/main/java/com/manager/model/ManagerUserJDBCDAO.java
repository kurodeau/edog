package com.manager.model;

import java.util.*;
import java.sql.*;

public class ManagerUserJDBCDAO implements ManagerUserDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/edog?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

//  �o�O���Ҧ������W��
//	managerUserId int AUTO_INCREMENT primary key ,
//	managerUserName varchar(20) ,
//	managerPassword varchar(10) ,
//	managerPer int ,
//	createtime datetime 
	
	private static final String INSERT_STMT = 
		"INSERT INTO managerUser (managerUserName, managerPassword, managerPer) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT managerUserId, managerUserName, managerPassword, managerPer, createtime FROM managerUser order by managerUserId";
	private static final String GET_ONE_STMT = 
		"SELECT managerUserId, managerUserName, managerPassword, managerPer, createtime FROM managerUser where managerUserId = ?";
	private static final String DELETE = 
		"DELETE FROM managerUser where managerUserId = ?";
	private static final String UPDATE = 
		"UPDATE managerUser set managerUserName=?, managerPassword=?, managerPer=?, createtime=? where managerUserId = ?";

	@Override
	public void insert(ManagerUserVO managerUserVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, managerUserVO.getManagername());
			pstmt.setString(2, managerUserVO.getManagerPassword());
			pstmt.setInt(3, managerUserVO.getManagerPer());

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
	public void update(ManagerUserVO managerUserVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, managerUserVO.getManagername());
			pstmt.setString(2, managerUserVO.getManagerPassword());
			pstmt.setInt(3, managerUserVO.getManagerPer());
			pstmt.setDate(4, managerUserVO.getCreatetime());
			pstmt.setInt(5, managerUserVO.getManagerId());

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
	public void delete(Integer managerUserId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, managerUserId);

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
	public ManagerUserVO findByPrimaryKey(Integer managerUserId) {

		ManagerUserVO managerUserVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, managerUserId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects (???
				managerUserVO = new ManagerUserVO();
				managerUserVO.setManagerId(rs.getInt("managerUserId"));
				managerUserVO.setManagername(rs.getString("managerUserName"));
				managerUserVO.setManagerPassword(rs.getString("managerPassword"));
				managerUserVO.setManagerPer(rs.getInt("managerPer"));
				managerUserVO.setCreatetime(rs.getDate("createtime"));
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
		return managerUserVO;
	}

	@Override
	public List<ManagerUserVO> getAll() {
		List<ManagerUserVO> list = new ArrayList<ManagerUserVO>();
		ManagerUserVO managerUserVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				managerUserVO = new ManagerUserVO();
				managerUserVO.setManagerId(rs.getInt("managerUserId"));
				managerUserVO.setManagername(rs.getString("managerUserName"));
				managerUserVO.setManagerPassword(rs.getString("managerPassword"));
				managerUserVO.setManagerPer(rs.getInt("managerPer"));
				managerUserVO.setCreatetime(rs.getDate("createtime"));
				list.add(managerUserVO); // Store the row in the list
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

		ManagerUserJDBCDAO dao = new ManagerUserJDBCDAO();

		// �s�W
//		ManagerUserVO managerUserVO1 = new ManagerUserVO();
//		managerUserVO1.setManagername("���պ޲z��JDBCDAO_1");
//		managerUserVO1.setManagerPaassword("testPW_1");
//		managerUserVO1.setManagerPer(10);
//		dao.insert(managerUserVO1);

		// �ק�
//		ManagerUserVO managerUserVO2 = new ManagerUserVO();
//		managerUserVO2.setManagername("Sakiko");
//		managerUserVO2.setManagerPaassword("MyGO123");
//		managerUserVO2.setManagerPer(20);
//		managerUserVO2.setCreatetime(java.sql.Date.valueOf("2025-01-01"));
//		managerUserVO2.setManagerId(1);
//		dao.update(managerUserVO2);

		// �R��
//		dao.delete(1);

//		// �d��
//		ManagerUserVO managerUserVO3 = dao.findByPrimaryKey(1);
//		System.out.print(managerUserVO3.getManagerId() + ",");
//		System.out.print(managerUserVO3.getManagername() + ",");
//		System.out.print(managerUserVO3.getManagerPaassword() + ",");
//		System.out.print(managerUserVO3.getManagerPer() + ",");
//		System.out.print(managerUserVO3.getCreatetime() + ",");
//		System.out.println("---------------------");
	//  �o�O���Ҧ������W��
//		managerUserId int AUTO_INCREMENT primary key ,
//		managerUserName varchar(20) ,
//		managerPassword varchar(10) ,
//		managerPer int ,
//		createtime datetime 

//		// �d��
//		List<ManagerUserVO> list = dao.getAll();
//		for (ManagerUserVO aManagerUser : list) {
//			System.out.print(aManagerUser.getManagerId() + ",");
//			System.out.print(aManagerUser.getManagername() + ",");
//			System.out.print(aManagerUser.getManagerPaassword() + ",");
//			System.out.print(aManagerUser.getManagerPer() + ",");
//			System.out.print(aManagerUser.getCreatetime() + ",");
//			System.out.println();
//		}
	}
}