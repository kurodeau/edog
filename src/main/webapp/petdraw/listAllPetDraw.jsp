<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.petdraw.entity.*"%>
<%@ page import="com.petdraw.service.*"%>


<%
    PetDrawService petDrawSvc = new PetDrawService();
    List<PetDrawVO> list = petDrawSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>所有抽卡資料 - listAllPetDraw.jsp</title>
    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }

        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }

        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 800px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        table, th, td {
            border: 1px solid #CCCCFF;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>所有抽卡配對資料 - listAllPetDraw.jsp</h3>
            <h4>
                <a href="select_page.jsp"><img src="images/back1.gif"
                                                width="100" height="32" border="0">回首頁</a>
            </h4>
        </td>
    </tr>
</table>
<table>
    <tr>
    	<th>petDrawId</th>
        <th>memberId</th>
        <th>memberPairId</th>
        <th>isMemberLike</th>
        <th>memberResTime</th>
        <th>memberPairResTime</th>
        <th>isMemberPairLike</th>
        <th>petDrawTime</th>
        <th>petDrawLog</th>
        <th>petDrawLat</th>
        <th>修改</th>
        <th>刪除</th>
    </tr>
    <%@ include file="page1.file"%>
    <c:forEach var="petDrawVO" items="${list}">
        <tr>
            <td>${petDrawVO.petDrawId}</td>
            <td>${petDrawVO.memberMain}</td>
            <td>${petDrawVO.memberPair}</td>
            <td>${petDrawVO.isMemberLike}</td>
            <td>${petDrawVO.memberResTime}</td>
            <td>${petDrawVO.memberPairResTime}</td>
            <td>${petDrawVO.isMemberPairLike}</td>
            <td>${petDrawVO.petDrawTime}</td>
            <td>${petDrawVO.petDrawLog}</td>
            <td>${petDrawVO.petDrawLat}</td>
            
            <td>
                <form method="post" action="<%=request.getContextPath()%>/petDraw/petDraw.do" style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="petDrawId" value="${petDrawVO.petDrawId}">
                    <input type="hidden" name="action" value="getOne_For_Update">
                </form>
            </td>
            <td>
                <form method="post" action="<%=request.getContextPath()%>/petDraw/petDraw.do" style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="petDrawId" value="${petDrawVO.petDrawId}">
                    <input type="hidden" name="action" value="delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="page2.file"%>
</body>
</html>
