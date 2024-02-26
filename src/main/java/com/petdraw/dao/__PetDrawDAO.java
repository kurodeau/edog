package com.petdraw.dao;

//
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.petdraw.entity.PetDrawVO;
//
//public class __PetDrawDAO implements PetDrawDAO_interface {
//
//    // ��Ʈw�s�u���]�w
//    private static DataSource dataSource = null;
//
//    // ��l�Ƹ�Ʈw�s�u��
//    static {
//        try {
//            Context context = new InitialContext();
//            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/edog");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static final String INSERT_STMT = 
//            "INSERT INTO petdraw (memberid, memberpairid, ismemberlike, memberrestime, memberpairrestime, ismemberpairlike, petdrawtime, petdrawlog, petdrawlat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//    private static final String GET_ALL_STMT = 
//            "SELECT petdrawid, memberid, memberpairid, ismemberlike, memberrestime, memberpairrestime, ismemberpairlike, petdrawtime, petdrawlog, petdrawlat FROM petdraw order by petdrawid";
//    private static final String GET_ONE_STMT = 
//            "SELECT petdrawid, memberid, memberpairid, ismemberlike, memberrestime, memberpairrestime, ismemberpairlike, petdrawtime, petdrawlog, petdrawlat FROM petdraw where petdrawid = ?";
//    private static final String DELETE = 
//            "DELETE FROM petdraw where petdrawid = ?";
//    private static final String UPDATE = 
//            "UPDATE petdraw set memberid=?, memberpairid=?, ismemberlike=?, memberrestime=?, memberpairrestime=?, ismemberpairlike=?, petdrawtime=?, petdrawlog=?, petdrawlat=? where petdrawid = ?";
//
//    @Override
//    public void insert(PetDrawVO petDrawVO) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement(INSERT_STMT);
//
//            preparedStatement.setInt(1, petDrawVO.getMemberPairId());
//            preparedStatement.setBoolean(2, petDrawVO.getMemberLike());
//            preparedStatement.setTimestamp(3, new Timestamp(petDrawVO.getMemberResTime().getTime()));
//            preparedStatement.setTimestamp(4, new Timestamp(petDrawVO.getMemberPairResTime().getTime()));
//            preparedStatement.setBoolean(5, petDrawVO.getMemberPairLike());
//            preparedStatement.setTimestamp(6, new Timestamp(petDrawVO.getPetDrawTime().getTime()));
//            preparedStatement.setDouble(7, petDrawVO.getPetDrawLog());
//            preparedStatement.setDouble(8, petDrawVO.getPetDrawLat());
//            preparedStatement.setInt(9, petDrawVO.getPetDrawId());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(connection, preparedStatement);
//        }
//    }
//
//    @Override
//    public void update(PetDrawVO petDrawVO) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement(UPDATE);
//
//            preparedStatement.setInt(1, petDrawVO.getMemberPairId());
//            preparedStatement.setBoolean(2, petDrawVO.getMemberLike());
//            preparedStatement.setTimestamp(3, new Timestamp(petDrawVO.getMemberResTime().getTime()));
//            preparedStatement.setTimestamp(4, new Timestamp(petDrawVO.getMemberPairResTime().getTime()));
//            preparedStatement.setBoolean(5, petDrawVO.getMemberPairLike());
//            preparedStatement.setTimestamp(6, new Timestamp(petDrawVO.getPetDrawTime().getTime()));
//            preparedStatement.setDouble(7, petDrawVO.getPetDrawLog());
//            preparedStatement.setDouble(8, petDrawVO.getPetDrawLat());
//            preparedStatement.setInt(9, petDrawVO.getPetDrawId());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(connection, preparedStatement);
//        }
//    }
//
//    @Override
//    public void delete(Integer petDrawId) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement(DELETE);
//
//            preparedStatement.setInt(1, petDrawId);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(connection, preparedStatement);
//        }
//    }
//
//    @Override
//    public PetDrawVO findByPrimaryKey(Integer petDrawId) {
//        PetDrawVO petDrawVO = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement(GET_ONE_STMT);
//
//            preparedStatement.setInt(1, petDrawId);
//
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//            	 petDrawVO = new PetDrawVO();
//                 petDrawVO.setPetDrawId(resultSet.getInt("petdrawid"));
//                 petDrawVO.setMemberPairId(resultSet.getInt("memberpairid"));
//                 petDrawVO.setMemberLike(resultSet.getBoolean("ismemberlike"));
//                 petDrawVO.setMemberResTime(resultSet.getTimestamp("memberrestime"));
//                 petDrawVO.setMemberPairResTime(resultSet.getTimestamp("memberpairrestime"));
//                 petDrawVO.setMemberPairLike(resultSet.getBoolean("ismemberpairlike"));
//                 petDrawVO.setPetDrawTime(resultSet.getTimestamp("petdrawtime"));
//                 petDrawVO.setPetDrawLog(resultSet.getDouble("petdrawlog"));
//                 petDrawVO.setPetDrawLat(resultSet.getDouble("petdrawlat"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(connection, preparedStatement, resultSet);
//        }
//
//        return petDrawVO;
//    }
//
//    @Override
//    public List<PetDrawVO> getAll() {
//        List<PetDrawVO> list = new ArrayList<>();
//        PetDrawVO petDrawVO = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement(GET_ALL_STMT);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//            	petDrawVO = new PetDrawVO();
//                petDrawVO.setPetDrawId(resultSet.getInt("petdrawid"));
//                petDrawVO.setMemberPairId(resultSet.getInt("memberpairid"));
//                petDrawVO.setMemberLike(resultSet.getBoolean("ismemberlike"));
//                petDrawVO.setMemberResTime(resultSet.getTimestamp("memberrestime"));
//                petDrawVO.setMemberPairResTime(resultSet.getTimestamp("memberpairrestime"));
//                petDrawVO.setMemberPairLike(resultSet.getBoolean("ismemberpairlike"));
//                petDrawVO.setPetDrawTime(resultSet.getTimestamp("petdrawtime"));
//                petDrawVO.setPetDrawLog(resultSet.getDouble("petdrawlog"));
//                petDrawVO.setPetDrawLat(resultSet.getDouble("petdrawlat"));
//                list.add(petDrawVO);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(connection, preparedStatement, resultSet);
//        }
//
//        return list;
//    }
//
//    private void closeResources(Connection connection, PreparedStatement preparedStatement) {
//        try {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            closeResources(connection, preparedStatement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
