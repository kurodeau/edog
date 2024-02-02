package com.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ArticleDAO implements ArticleDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO article ( memberId, articleTitle, articleContent, artUpdateTime, articleLike, articleComment, articleShare, articleSort, isEnabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE article SET memberId = ?, articleTitle = ?, articleContent = ?, artUpdateTime = ?, articleLike = ?, articleComment = ?, articleShare = ?, articleSort = ?, isEnabled = ? WHERE articleId = ?";
	private static final String DELETE_STMT = "DELETE FROM article WHERE articleId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM article WHERE articleId = ?";
	private static final String GET_ALL = "SELECT * FROM article";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getMemberId());
			pstmt.setString(2, articleVO.getArticleTitle());
			pstmt.setString(3, articleVO.getArticleContent());
			pstmt.setTimestamp(4, articleVO.getArtUpdateTime());
			pstmt.setInt(5, articleVO.getArticleLike());
			pstmt.setInt(6, articleVO.getArticleComment());
			pstmt.setInt(7, articleVO.getArticleShare());
			pstmt.setInt(8, articleVO.getArticleSort());
			pstmt.setBoolean(9, articleVO.getIsEnabled());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, articleVO.getMemberId());
			pstmt.setString(2, articleVO.getArticleTitle());
			pstmt.setString(3, articleVO.getArticleContent());
			pstmt.setTimestamp(4, articleVO.getArtUpdateTime());
			pstmt.setInt(5, articleVO.getArticleLike());
			pstmt.setInt(6, articleVO.getArticleComment());
			pstmt.setInt(7, articleVO.getArticleShare());
			pstmt.setInt(8, articleVO.getArticleSort());
			pstmt.setBoolean(9, articleVO.getIsEnabled());
			pstmt.setInt(10, articleVO.getArticleId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

//	@Override
//	public void delete(Integer articleId) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(DELETE_STMT);
//
//			pstmt.setInt(1, articleId);
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
	public ArticleVO findByPrimaryKey(Integer articleId) {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticleId(rs.getInt("articleId"));
				articleVO.setMemberId(rs.getInt("memberId"));
				articleVO.setArticleTitle(rs.getString("articleTitle"));
				articleVO.setArticleContent(rs.getString("articleContent"));
				articleVO.setArtUpdateTime(rs.getTimestamp("artUpdateTime"));
				articleVO.setArticleLike(rs.getInt("articleLike"));
				articleVO.setArticleComment(rs.getInt("articleComment"));
				articleVO.setArticleShare(rs.getInt("articleShare"));
				articleVO.setArticleSort(rs.getInt("articleSort"));
				articleVO.setIsEnabled(rs.getBoolean("isEnabled"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> articleList = new ArrayList<>();
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticleId(rs.getInt("articleId"));
				articleVO.setMemberId(rs.getInt("memberId"));
				articleVO.setArticleTitle(rs.getString("articleTitle"));
				articleVO.setArticleContent(rs.getString("articleContent"));
				articleVO.setArtUpdateTime(rs.getTimestamp("artUpdateTime"));
				articleVO.setArticleLike(rs.getInt("articleLike"));
				 articleVO.setArticleComment(rs.getInt("articleComment"));
				 articleVO.setArticleShare(rs.getInt("articleShare"));
				 articleVO.setArticleSort(rs.getInt("articleSort"));
				 articleVO.setIsEnabled(rs.getBoolean("isEnabled"));
				articleList.add(articleVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return articleList;
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
