<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/main/main.css">
<title>List 會員</title>
</head>
<body>
	<h1>員工列表</h1>
	<br>
	<table style="width: 50%; text-align: center;">
		<tr>
			<th>會員編號</th>
			<th>會員信箱</th>
			<th>第三方綁定</th>
			<th>會員名稱</th>
			<th>會員電話</th>
			<th>會員手機</th>
			<th>會員生日</th>
			<th>會員密碼</th>
			<th>會員地址</th>
			<th>是否驗證信箱</th>
			<th>會員註冊時間</th>
			<th>寵物名稱</th>
			<th>寵物圖片</th>
			<th>寵物圖片更新時間</th>
			<th>寵物疫苗名稱一</th>
			<th>最近施打時間</th>
			<th>寵物疫苗名稱二</th>
			<th>最近施打時間</th>
			<th>用戶資料是否生效</th>
		</tr>
		<c:forEach var="buyerVO" items="${requestScope.list}">
			<tr>
			    <td>${buyerVO.memberId}</td>
				<td>${buyerVO.memberEmail}</td>
				<td>${buyerVO.thirdFrom}</td>
				<td>${buyerVO.memberName}</td>
				<td>${buyerVO.memberPhone}</td>
				<td>${buyerVO.memberMobile}</td>
				<td>${buyerVO.memberBirthday}</td>
				<td>${buyerVO.memberPassword}</td>
				<td>${buyerVO.memberAddress}</td>
				<td>${buyerVO.isMemberEmail}</td>
				<td>${buyerVO.memberRegistrationTime}</td>
				<td>${buyerVO.petName}</td>
				<td>${buyerVO.petImg}</td>
				<td>${buyerVO.petImgUploadTime}</td>
				<td>${buyerVO.petVaccName1}</td>
				<td>${buyerVO.petVaccTime1}</td>
				<td>${buyerVO.petVaccName2}</td>
				<td>${buyerVO.petVaccTime2}</td>
				<td>${buyerVO.isConfirm}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<img width="140px" height="100px" alt="要飛囉貓貓"
		src="${pageContext.request.contextPath}/img/inversecat.png">
	<br>
	<br>

	<a href="${pageContext.request.contextPath}/buyer/index.jsp">回首頁</a>
</body>
</html>