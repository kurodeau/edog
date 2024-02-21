<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/main/main.css">
<title>Hibernate Demo</title>
</head>
<body>
	<h1>這是一位後端人員作的網頁 QQ</h1>
	<h2>走馬燈系統</h2>
	<a
		href="${pageContext.request.contextPath}/newsTicker/newsTicker.do?action=getAll">查詢所有走馬燈資料</a>
	<br>
	<br>
	<jsp:useBean id="newsTickerSvc" scope="page"
		class="com.newsTicker.service.NewsTickerService" />

	<FORM METHOD="post" ACTION="newsTicker.do">
		<b>選擇編號</b> <select size="1" name="newsTickerId">
			<c:forEach var="newsTickerVO" items="${newsTickerSvc.getAll()}">
				<option value="${newsTickerVO.newsTickerId}">${newsTickerVO.newsTickerId}
			</c:forEach>
		</select> <input type="hidden" name="action" value="getOne_For_Display">
		<input type="submit" value="送出">
	</FORM>

	<br>
	<br>
	<a href='addNewsTicker.jsp'>Add</a>


<!--  
	<h3>
		<b>複合查詢 (使用 Criteria Query)：</b>
	</h3>
	<form action="${pageContext.request.contextPath}/newsTicker/newsTicker.do"
		method="post">
		<p>
			<label>名字模糊查詢：</label>
		</p>
		<input type="text" name="newsTickerContent"><br>
		<p>
			<label>日期間範圍</label>
		</p>
		<input type="datetime-local" name="startdate"> ～ <input
			type="datetime-local" name="enddate"><br>

		<p>
			<input type="submit" value="送出">
		</p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
 -->



</body>
</html>