<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="java.util.Base64"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AdService adSvc = new AdService();
    List<AdVO> list = adSvc.getAll();
    pageContext.setAttribute("list",list);
  %>


<html>
<head>
<title>所有廣告資料 - listAllAd.jsp</title>

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
	width: 100%;
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
	<tr><td>
		 <h3>所有廣告資料 - listAllAd.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>廣告編號</th>
		<th>賣家ID</th>
		<th>廣告圖片</th>
		<th>廣告圖片上傳時間</th>
		<th>廣告名稱</th>
		<th>廣告網址</th>
		<th>廣告開始時間</th>
		<th>廣告結束時間</th>
		<th>廣告等級</th>
		<th>廣告備註</th>
		<th>廣告審核</th>
		<th>廣告創立時間</th>
		<th>廣告狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${adVO.adId}</td>
			<td>${adVO.sellerId}</td>
			<td>  <img src="data:image/png;base64,${Base64.getEncoder().encodeToString(adVO.adImg)}" alt="廣告圖片" style="max-width: 100px; max-height: 100px;"></td>
			<td>${adVO.adImgUploadTime}</td>
			<td>${adVO.adName}</td>
			<td>${adVO.adUrl}</td> 
			<td>${adVO.adStartTime}</td>
			<td>${adVO.adEndTime}</td>
			<td>${adVO.adLv}</td>
			<td>${adVO.adMemo}</td>
			<td>${adVO.isAdConfirm}</td>
			<td>${adVO.adCreateTime}</td>
			<td>${adVO.isEnabled}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ad/ad.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adId"  value="${adVO.adId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ad/ad.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adId"  value="${adVO.adId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>