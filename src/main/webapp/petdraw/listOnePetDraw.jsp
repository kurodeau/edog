<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.petdraw.entity.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
PetDrawVO petDrawVO = (PetDrawVO) request.getAttribute("petDrawVO"); //PetDrawServlet.java(Concroller), 存入req的petDrawVO物件
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PetDraw 資料 - listOnePetDraw.jsp</title>
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
            width: 600px;
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

    <h4>此頁暫練習採用 Script 的寫法取值:</h4>
    <table id="table-1">
        <tr>
            <td>
                <h3>PetDraw 資料 - listOnePetDraw.jsp</h3>
                <h4>
                    <a href="select_page.jsp"><img src="images/back1.gif"
                        width="100" height="32" border="0">回首頁</a>
                </h4>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <th>memberId</th>
            <th>memberPairId</th>
            <th>isMemberLike</th>
            <th>memberResTime</th>
            <th>memberPairResTime</th>
            <th>isMemberPairLike</th>
            <th>petDrawTime</th>
            <th>petDrawLog</th>
            <th>petDrawLat</th>
            <th>petDrawId</th>
        </tr>
        <tr>
            <td>${petDrawVO.memberId}</td>
            <td>${petDrawVO.memberPairId}</td>
            <td>${petDrawVO.isMemberLike}</td>
            <td>${petDrawVO.memberResTime}</td>
            <td>${petDrawVO.memberPairResTime}</td>
            <td>${petDrawVO.isMemberPairLike}</td>
            <td>${petDrawVO.petDrawTime}</td>
            <td>${petDrawVO.petDrawLog}</td>
            <td>${petDrawVO.petDrawLat}</td>
            <td>${petDrawVO.petDrawId}</td>
        </tr>
    </table>
</body>
</html>
