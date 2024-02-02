//package com.remittance.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
////public class RemittanceJDBCDAO implements RemittanceDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/edog?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO remittance (sellerId,remittanceEstimatedTime,remittanceTime,settlementTime,turnover,handlingFee, remittanceAmount, remittanceStatus, explanation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT remittanceId, sellerId,remittanceEstimatedTime,remittanceTime,settlementTime,turnover,handlingFee, remittanceAmount, remittanceStatus, explanation FROM remittance order by remittanceId";
//	private static final String GET_ONE_STMT = "SELECT remittanceId, sellerId,remittanceEstimatedTime,remittanceTime,settlementTime,turnover,handlingFee, remittanceAmount, remittanceStatus, explanation FROM remittance where remittanceId = ?";
////		private static final String DELETE = 
////			"DELETE FROM remittance where remittanceId = ?";
//	private static final String UPDATE = "UPDATE remittance set sellerId=?,remittanceEstimatedTime=?,remittanceTime=?,settlementTime=?,turnover=?,handlingFee=?, remittanceAmount=?, remittanceStatus=?, explanation=? where remittanceId = ?";
//
//	@Override
//	public void insert(RemittanceVO remittanceVO) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, remittanceVO.getSellerId());
//			pstmt.setTimestamp(2, remittanceVO.getRemittanceEstimatedTime());
//			pstmt.setTimestamp(3, remittanceVO.getRemittanceTime());
//			pstmt.setTimestamp(4, remittanceVO.getSettlementTime());
//			pstmt.setInt(5, remittanceVO.getTurnover());
//			pstmt.setInt(6, remittanceVO.getHandlingFee());
//			pstmt.setInt(7, remittanceVO.getRemittanceAmount());
//			pstmt.setInt(8, remittanceVO.getRemittanceStatus());
//			pstmt.setString(9, remittanceVO.getExplanation());
//
//			pstmt.executeUpdate();
//
////			 Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void update(RemittanceVO remittanceVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, remittanceVO.getSellerId());
//			pstmt.setTimestamp(2, remittanceVO.getRemittanceEstimatedTime());
//			pstmt.setTimestamp(3, remittanceVO.getRemittanceTime());
//			pstmt.setTimestamp(4, remittanceVO.getSettlementTime());
//			pstmt.setInt(5, remittanceVO.getTurnover());
//			pstmt.setInt(6, remittanceVO.getHandlingFee());
//			pstmt.setInt(7, remittanceVO.getRemittanceAmount());
//			pstmt.setInt(8, remittanceVO.getRemittanceStatus());
//			pstmt.setString(9, remittanceVO.getExplanation());
//			pstmt.setInt(10, remittanceVO.getRemittanceId());
//
//			pstmt.executeUpdate();
//
////			 Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public RemittanceVO findByPrimaryKey(Integer remittanceId) {
//		// TODO Auto-generated method stub
//
//		RemittanceVO remittanceVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, remittanceId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
////				remittanceVO = new RemittanceVO(
////			
////						rs.getInt("RemittanceId"), 
////						rs.getInt("getSellerId"),
////						rs.getTimestamp("remittanceEstimatedTime"),
////						rs.getTimestamp("remittanceTime"),
////						rs.getTimestamp("settlementTime"), 
////						rs.getInt("turnover"), 
////						rs.getInt("handlingFee"),
////						rs.getInt("remittanceAmount"), 
////						rs.getInt("remittanceStatus"), 
////						rs.getString("explanation"));
//				
//				remittanceVO = new RemittanceVO();
//				remittanceVO.setRemittanceId(rs.getInt("RemittanceId"));
//				remittanceVO.setSellerId(rs.getInt("sellerId"));
//				remittanceVO.setRemittanceEstimatedTime(rs.getTimestamp("remittanceEstimatedTime"));
//				remittanceVO.setRemittanceTime(rs.getTimestamp("remittanceTime"));
//				remittanceVO.setSettlementTime(rs.getTimestamp("settlementTime"));
//				remittanceVO.setTurnover(rs.getInt("turnover"));
//				remittanceVO.setHandlingFee(rs.getInt("handlingFee"));
//				remittanceVO.setRemittanceAmount(rs.getInt("remittanceAmount"));
//				remittanceVO.setRemittanceStatus(rs.getInt("remittanceStatus"));
//				remittanceVO.setExplanation(rs.getString("explanation"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return remittanceVO;
//
//	}
//
//	@Override
//	public List<RemittanceVO> getAll() {
//		// TODO Auto-generated method stub
//		List<RemittanceVO> list = new ArrayList<RemittanceVO>();
//		RemittanceVO remittanceVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
////		"SELECT remittanceId, sellerId,remittanceEstimatedTime,remittanceTime,settlementTime,turnover,handlingFee, remittanceAmount, remittanceStatus, explanation FROM remittance order by remittanceId";
//			while (rs.next()) {
//				// remittanceVO �]�٬� Domain objects
//				remittanceVO = new RemittanceVO();
//				remittanceVO.setRemittanceId(rs.getInt("RemittanceId"));
//				remittanceVO.setSellerId(rs.getInt("sellerId"));
//				remittanceVO.setRemittanceEstimatedTime(rs.getTimestamp("remittanceEstimatedTime"));
//				remittanceVO.setRemittanceTime(rs.getTimestamp("remittanceTime"));
//				remittanceVO.setSettlementTime(rs.getTimestamp("settlementTime"));
//				remittanceVO.setTurnover(rs.getInt("turnover"));
//				remittanceVO.setHandlingFee(rs.getInt("handlingFee"));
//				remittanceVO.setRemittanceAmount(rs.getInt("remittanceAmount"));
//				remittanceVO.setRemittanceStatus(rs.getInt("remittanceStatus"));
//				remittanceVO.setExplanation(rs.getString("explanation"));
//				list.add(remittanceVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//
//	}
//
//	public static void main(String[] args) {
//		RemittanceJDBCDAO dao = new RemittanceJDBCDAO();
//
////		RemittanceVO remittanceVO1 = new RemittanceVO();
//		
////		remittanceVO1.setRemittanceId(null);
////		remittanceVO1.setSellerId(8);
////		remittanceVO1.setRemittanceEstimatedTime(null);
////		remittanceVO1.setRemittanceTime(null);
////		remittanceVO1.setSettlementTime(null);
////		remittanceVO1.setTurnover(null);
////		remittanceVO1.setHandlingFee(null);
////		remittanceVO1.setRemittanceAmount(null);
////		remittanceVO1.setRemittanceStatus(null);
////		remittanceVO1.setExplanation("123");
////		dao.insert(remittanceVO1);
//
////		RemittanceVO remittanceVO2 = new RemittanceVO();
////		remittanceVO2.setRemittanceId(null);
////		remittanceVO2.setSellerId(null);
////		remittanceVO2.setRemittanceEstimatedTime(null);
////		remittanceVO2.setRemittanceTime(null);
////		remittanceVO2.setSettlementTime(null);
////		remittanceVO2.setTurnover(null);
////		remittanceVO2.setHandlingFee(null);
////		remittanceVO2.setRemittanceAmount(null);
////		remittanceVO2.setRemittanceStatus(null);
////		remittanceVO2.setExplanation("123");
////		dao.update(remittanceVO1);
//
////		RemittanceVO remittanceVO3 = dao.findByPrimaryKey(1);
////		System.out.print(remittanceVO3.getRemittanceId() + ",");
////		System.out.print(remittanceVO3.getSellerId() + ",");
////		System.out.print(remittanceVO3.getRemittanceEstimatedTime() + ",");
////		System.out.print(remittanceVO3.getRemittanceTime() + ",");
////		System.out.print(remittanceVO3.getSettlementTime() + ",");
////		System.out.print(remittanceVO3.getTurnover() + ",");
////		System.out.print(remittanceVO3.getHandlingFee() + ",");
////		System.out.print(remittanceVO3.getRemittanceAmount() + ",");
////		System.out.print(remittanceVO3.getRemittanceStatus() + ",");
////		System.out.println(remittanceVO3.getExplanation());
////		System.out.println("---------------------");
//
////		List<RemittanceVO> list = dao.getAll();
////		for (RemittanceVO remittance : list) {
////			System.out.print(remittanceVO3.getRemittanceId() + ",");
////			System.out.print(remittanceVO3.getSellerId() + ",");
////			System.out.print(remittanceVO3.getRemittanceEstimatedTime() + ",");
////			System.out.print(remittanceVO3.getRemittanceTime() + ",");
////			System.out.print(remittanceVO3.getSettlementTime() + ",");
////			System.out.print(remittanceVO3.getTurnover() + ",");
////			System.out.print(remittanceVO3.getHandlingFee() + ",");
////			System.out.print(remittanceVO3.getRemittanceAmount() + ",");
////			System.out.print(remittanceVO3.getRemittanceStatus() + ",");
////			System.out.println(remittanceVO3.getExplanation());
////			System.out.println();
////		}
//	}
//}
