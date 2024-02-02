package com.article.model;

import java.util.*;
import java.sql.*;

public class ArticleJDBCDAO implements ArticleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/edog?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO article (memberId,articleContent,artUpdateTime,articleLike,articleComment,articleShare,articleSort,isEnabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT articleId,memberId,articleContent,artUpdateTime,articleLike,articleComment,articleShare,articleSort,isEnabled FROM article order by articleId";
	private static final String GET_ONE_STMT = "SELECT articleId,memberId,articleContent,artUpdateTime,articleLike,articleComment,articleShare,articleSort,isEnabled FROM article where articleId = ?";
//	private static final String DELETE = "DELETE FROM article where articleId = ?";
	private static final String UPDATE = "UPDATE article set memberId=?,articleContent=?,artUpdateTime=?,articleLike=?,articleComment=?,articleShare=?,articleSort=?,isEnabled=? where articleId = ?";

	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getMemberId());
			pstmt.setString(2, articleVO.getArticleContent());
			pstmt.setTimestamp(3, articleVO.getArtUpdateTime());
			pstmt.setInt(4, articleVO.getArticleLike());
			pstmt.setInt(5, articleVO.getArticleComment());
			pstmt.setInt(6, articleVO.getArticleShare());
			pstmt.setInt(7, articleVO.getArticleSort());
			pstmt.setBoolean(8, articleVO.getIsEnabled());

			pstmt.executeUpdate();

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

	@Override
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleVO.getMemberId());
			pstmt.setString(2, articleVO.getArticleContent());
			pstmt.setTimestamp(3, articleVO.getArtUpdateTime());
			pstmt.setInt(4, articleVO.getArticleLike());
			pstmt.setInt(5, articleVO.getArticleComment());
			pstmt.setInt(6, articleVO.getArticleShare());
			pstmt.setInt(7, articleVO.getArticleSort());
			pstmt.setBoolean(8, articleVO.getIsEnabled());
			pstmt.setInt(9, articleVO.getArticleId());

			pstmt.executeUpdate();

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

//	@Override
//	public void delete(Integer articleId) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, articleId);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
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

	@Override
	public ArticleVO findByPrimaryKey(Integer articleId) {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticleId(rs.getInt("articleId"));
				articleVO.setMemberId(rs.getInt("memberId"));
				articleVO.setArticleContent(rs.getString("articleContent"));
				articleVO.setArtUpdateTime(rs.getTimestamp("artUpdateTime"));
				articleVO.setArticleLike(rs.getInt("articleLike"));
				articleVO.setArticleComment(rs.getInt("articleComment"));
				articleVO.setArticleShare(rs.getInt("articleShare"));
				articleVO.setArticleSort(rs.getInt("articleSort"));
				articleVO.setIsEnabled(rs.getBoolean("isEnabled"));
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticleId(rs.getInt("articleId"));
				articleVO.setMemberId(rs.getInt("memberId"));
				articleVO.setArticleContent(rs.getString("articleContent"));
				articleVO.setArtUpdateTime(rs.getTimestamp("artUpdateTime"));
				articleVO.setArticleLike(rs.getInt("articleLike"));
				articleVO.setArticleComment(rs.getInt("articleComment"));
				articleVO.setArticleShare(rs.getInt("articleShare"));
				articleVO.setArticleSort(rs.getInt("articleSort"));
				articleVO.setIsEnabled(rs.getBoolean("isEnabled"));
				list.add(articleVO); // Store the row in the list
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

		ArticleJDBCDAO dao = new ArticleJDBCDAO();
//		// 新增
//		ArticleVO articleVO1 = new ArticleVO();
//		articleVO1.setMemberId(3);
//		articleVO1.setArticleContent("abc");
//		articleVO1.setArtUpdateTime(java.sql.Timestamp.valueOf("2005-01-01 03:14:07"));
//		articleVO1.setArticleLike(10);
//		articleVO1.setArticleComment(5);
//		articleVO1.setArticleShare(3);
//		articleVO1.setArticleSort(6);
//		articleVO1.setIsEnabled(true);
//		dao.insert(articleVO1);
//
//		// 修改
//		ArticleVO articleVO2 = new ArticleVO();
//		articleVO2.setArticleId(2);
//		articleVO2.setMemberId(1);
//		articleVO2.setArticleContent("def");
//		articleVO2.setArtUpdateTime(java.sql.Timestamp.valueOf("2005-01-01 04:14:07"));
//		articleVO2.setArticleLike(11);
//		articleVO2.setArticleComment(6);
//		articleVO2.setArticleShare(4);
//		articleVO2.setArticleSort(7);
//		articleVO2.setIsEnabled(true);
//		dao.update(articleVO2);
//
//		// 刪除
//		dao.delete(1);
//
//		// 查詢
//		ArticleVO articleVO3 = dao.findByPrimaryKey(1);
//		System.out.print(articleVO3.getArticleId() + ",");
//		System.out.print(articleVO3.getMemberId() + ",");
//		System.out.print(articleVO3.getArticleContent() + ",");
//		System.out.print(articleVO3.getArtUpdateTime() + ",");
//		System.out.print(articleVO3.getArticleLike() + ",");
//		System.out.print(articleVO3.getArticleComment() + ",");
//		System.out.print(articleVO3.getArticleShare() + ",");
//		System.out.print(articleVO3.getArticleSort() + ",");
//		System.out.println(articleVO3.getIsEnabled());
//		System.out.println("---------------------");

		// 查詢
		List<ArticleVO> list = dao.getAll();
		for (ArticleVO aArticle : list) {
			System.out.print(aArticle.getArticleId() + ",");
			System.out.print(aArticle.getMemberId() + ",");
			System.out.print(aArticle.getArticleContent() + ",");
			System.out.print(aArticle.getArtUpdateTime() + ",");
			System.out.print(aArticle.getArticleLike() + ",");
			System.out.print(aArticle.getArticleComment() + ",");
			System.out.print(aArticle.getArticleShare() + ",");
			System.out.print(aArticle.getArticleSort() + ",");
			System.out.print(aArticle.getIsEnabled());
			System.out.println();
		}
	}
}
