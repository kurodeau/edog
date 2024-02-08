<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/main/main.css">
<title>List Emps</title>
</head>
<body>
	<h1>員工列表</h1>
	<br>
	<table style="width: 50%; text-align: center;">
		<tr>

	<th>優惠券編號</th>
			<th>優惠券名稱</th>
			<th>優惠券代碼</th>
			<th>開始時間</th>
			<th>結束時間</th>
			<th>最低消費金額</th>
			<th>優惠券數量</th>
			<th>會員可使用數量</th>
			<th>優惠折扣</th>

		</tr>
		<c:forEach var="couponVO" items="${requestScope.list}">
			<tr>
			<td>${couponVO.couponId}</td>
			<td>${couponVO.couponName}</td>
			<td>${couponVO.couponCode}</td>
			<td>${couponVO.startTime}</td>
			<td>${couponVO.endTime}</td>
			<td>${couponVO.minSpendingAmount}</td>
			<td>${couponVO.couponQuantity}</td>
			<td>${couponVO.memberAllowQuantity}</td>
			<td>${couponVO.couponDiscount}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<img width="140px" height="100px" alt="要飛囉貓貓"
		src="${pageContext.request.contextPath}/img/inversecat.png">
	<br>
	<br>

	<a href="${pageContext.request.contextPath}/seller/index.jsp">回首頁</a>
</body>
</html>