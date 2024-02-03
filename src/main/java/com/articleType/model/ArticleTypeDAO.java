package com.articleType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ArticleTypeDAO implements ArticleTypeDAO_interface {
    private static final String INSERT_STMT = "INSERT INTO articleType (articleTypeId, articleTypeName) VALUES (?, ?)";
    private static final String UPDATE_STMT = "UPDATE articleType SET articleTypeName = ? WHERE articleTypeId = ?";
    private static final String DELETE_STMT = "DELETE FROM articleType WHERE articleTypeId = ?";
    private static final String FIND_BY_PK = "SELECT * FROM articleType WHERE articleTypeId = ?";
    private static final String GET_ALL = "SELECT * FROM articleType";
    
    static {
        try {
            Class.forName(Util.DRIVER);
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    @Override
    public void insert(ArticleTypeVO articleTypeVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, articleTypeVO.getArticleTypeId());
            pstmt.setString(2, articleTypeVO.getArticleTypeName());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(con, pstmt, null);
        }
    }

    @Override
    public void update(ArticleTypeVO articleTypeVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setString(1, articleTypeVO.getArticleTypeName());
            pstmt.setInt(2, articleTypeVO.getArticleTypeId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(con, pstmt, null);
        }
    }

    @Override
    public void delete(Integer articleTypeId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, articleTypeId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(con, pstmt, null);
        }
    }

    @Override
    public ArticleTypeVO findByPrimaryKey(Integer articleTypeId) {
        ArticleTypeVO articleTypeVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            pstmt = con.prepareStatement(FIND_BY_PK);
            pstmt.setInt(1, articleTypeId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleTypeVO = new ArticleTypeVO();
                articleTypeVO.setArticleTypeId(rs.getInt("articleTypeId"));
                articleTypeVO.setArticleTypeName(rs.getString("articleTypeName"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(con, pstmt, rs);
        }
        return articleTypeVO;
    }

    @Override
    public List<ArticleTypeVO> getAll() {
        List<ArticleTypeVO> articleTypeList = new ArrayList<>();
        ArticleTypeVO articleTypeVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            pstmt = con.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleTypeVO = new ArticleTypeVO();
                articleTypeVO.setArticleTypeId(rs.getInt("articleTypeId"));
                articleTypeVO.setArticleTypeName(rs.getString("articleTypeName"));
                articleTypeList.add(articleTypeVO);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(con, pstmt, rs);
        }
        return articleTypeList;
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
