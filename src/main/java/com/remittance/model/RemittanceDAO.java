package com.remittance.model;

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

public class RemittanceDAO implements RemittanceDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/edog");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO remittance (sellerId,remittanceEstimatedTime,remittanceTime,settlementTime,turnover,handlingFee, remittanceAmount, remittanceStatus, explanation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT remittanceId, sellerId,remittanceEstimatedTime,remittanceTime,settlementTime,turnover,handlingFee, remittanceAmount, remittanceStatus, explanation FROM remittance order by remittanceId";
	private static final String GET_ONE_STMT = "SELECT remittanceId, sellerId,remittanceEstimatedTime,remittanceTime,settlementTime,turnover,handlingFee, remittanceAmount, remittanceStatus, explanation FROM remittance where remittanceId = ?";
			private static final String DELETE = 
				"DELETE FROM remittance where remittanceId = ?";
	private static final String UPDATE = "UPDATE remittance set sellerId=?,remittanceEstimatedTime=?,remittanceTime=?,settlementTime=?,turnover=?,handlingFee=?, remittanceAmount=?, remittanceStatus=?, explanation=? where remittanceId = ?";

	@Override
	public void insert(RemittanceVO remittanceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, remittanceVO.getSellerId());
			pstmt.setTimestamp(2, remittanceVO.getRemittanceEstimatedTime());
			pstmt.setTimestamp(3, remittanceVO.getRemittanceTime());
			pstmt.setTimestamp(4, remittanceVO.getSettlementTime());
			pstmt.setInt(5, remittanceVO.getTurnover());
			pstmt.setInt(6, remittanceVO.getHandlingFee());
			pstmt.setInt(7, remittanceVO.getRemittanceAmount());
			pstmt.setInt(8, remittanceVO.getRemittanceStatus());
			pstmt.setString(9, remittanceVO.getExplanation());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void update(RemittanceVO remittanceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, remittanceVO.getSellerId());
			pstmt.setTimestamp(2, remittanceVO.getRemittanceEstimatedTime());
			pstmt.setTimestamp(3, remittanceVO.getRemittanceTime());
			pstmt.setTimestamp(4, remittanceVO.getSettlementTime());
			pstmt.setInt(5, remittanceVO.getTurnover());
			pstmt.setInt(6, remittanceVO.getHandlingFee());
			pstmt.setInt(7, remittanceVO.getRemittanceAmount());
			pstmt.setInt(8, remittanceVO.getRemittanceStatus());
			pstmt.setString(9, remittanceVO.getExplanation());
			pstmt.setInt(10, remittanceVO.getRemittanceId());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public RemittanceVO findByPrimaryKey(Integer remittanceId) {

		RemittanceVO remittanceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, remittanceId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				remittanceVO = new RemittanceVO();
				remittanceVO.setRemittanceId(rs.getInt("RemittanceId"));
				remittanceVO.setSellerId(rs.getInt("sellerId"));
				remittanceVO.setRemittanceEstimatedTime(rs.getTimestamp("remittanceEstimatedTime"));
				remittanceVO.setRemittanceTime(rs.getTimestamp("remittanceTime"));
				remittanceVO.setSettlementTime(rs.getTimestamp("settlementTime"));
				remittanceVO.setTurnover(rs.getInt("turnover"));
				remittanceVO.setHandlingFee(rs.getInt("handlingFee"));
				remittanceVO.setRemittanceAmount(rs.getInt("remittanceAmount"));
				remittanceVO.setRemittanceStatus(rs.getInt("remittanceStatus"));
				remittanceVO.setExplanation(rs.getString("explanation"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return remittanceVO;
	}

	@Override
	public List<RemittanceVO> getAll() {
		// TODO Auto-generated method stub
		List<RemittanceVO> list = new ArrayList<RemittanceVO>();
		RemittanceVO remittanceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				remittanceVO = new RemittanceVO();
				remittanceVO.setRemittanceId(rs.getInt("RemittanceId"));
				remittanceVO.setSellerId(rs.getInt("sellerId"));
				remittanceVO.setRemittanceEstimatedTime(rs.getTimestamp("remittanceEstimatedTime"));
				remittanceVO.setRemittanceTime(rs.getTimestamp("remittanceTime"));
				remittanceVO.setSettlementTime(rs.getTimestamp("settlementTime"));
				remittanceVO.setTurnover(rs.getInt("turnover"));
				remittanceVO.setHandlingFee(rs.getInt("handlingFee"));
				remittanceVO.setRemittanceAmount(rs.getInt("remittanceAmount"));
				remittanceVO.setRemittanceStatus(rs.getInt("remittanceStatus"));
				remittanceVO.setExplanation(rs.getString("explanation"));
				list.add(remittanceVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer remittanceId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, remittanceId);

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
