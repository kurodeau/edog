package com.articlePic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ArticlePicDAO implements ArticlePicDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO articlePic (articleId, articlePicBlob, articlePicTime) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE articlePic SET articleId = ?, articlePicBlob = ?, articlePicTime = ? WHERE articlePicId = ?";
	private static final String DELETE_STMT = "DELETE FROM articlePic WHERE articlePicId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM articlePic WHERE articlePicId = ?";
	private static final String GET_ALL = "SELECT * FROM articlePic";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(ArticlePicVO articlePicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articlePicVO.getArticleId());
			pstmt.setBytes(2, articlePicVO.getArticlePicBlob());
			pstmt.setTimestamp(3, articlePicVO.getArticlePicTime());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(ArticlePicVO articlePicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, articlePicVO.getArticleId());
			pstmt.setBytes(2, articlePicVO.getArticlePicBlob());
			pstmt.setTimestamp(3, articlePicVO.getArticlePicTime());
			pstmt.setInt(4, articlePicVO.getArticlePicId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

//	@Override
//	public void delete(Integer articlePicId) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(DELETE_STMT);
//
//			pstmt.setInt(1, articlePicId);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, null);
//		}
//	}

	@Override
	public ArticlePicVO findByPrimaryKey(Integer articlePicId) {
		ArticlePicVO articlePicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, articlePicId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlePicVO = new ArticlePicVO();
				articlePicVO.setArticlePicId(rs.getInt("articlePicId"));
				articlePicVO.setArticleId(rs.getInt("articleId"));
				articlePicVO.setArticlePicBlob(rs.getBytes("articlePicBlob"));
				articlePicVO.setArticlePicTime(rs.getTimestamp("articlePicTime"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return articlePicVO;
	}

	@Override
	public List<ArticlePicVO> getAll() {
		List<ArticlePicVO> articlePicList = new ArrayList<>();
		ArticlePicVO articlePicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlePicVO = new ArticlePicVO();
				articlePicVO.setArticlePicId(rs.getInt("articlePicId"));
				articlePicVO.setArticleId(rs.getInt("articleId"));
				articlePicVO.setArticlePicBlob(rs.getBytes("articlePicBlob"));
				articlePicVO.setArticlePicTime(rs.getTimestamp("articlePicTime"));
				articlePicList.add(articlePicVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return articlePicList;
	}
	
	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
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
}
