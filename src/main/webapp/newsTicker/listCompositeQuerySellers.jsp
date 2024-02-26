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

			<th>賣家Lv</th>
			<th>電子郵件</th>
			<th>公司名稱</th>
			<th>統一編號</th>
			<th>資本額</th>
			<th>公司負責人</th>
			<th>公司電話</th>
			<th>公司分機</th>
			<th>手機號碼</th>
			<th>地址</th>
			<th>密碼</th>
			<th>銀行帳戶</th>
			<th>銀行代碼</th>
			<th>銀行帳號</th>
			<th>創建時間</th>
			<th>確認狀態</th>

		</tr>
		<c:forEach var="sellerVO" items="${requestScope.list}">
			<tr>
				<td>${sellerVO.sellerId}</td>
				<td>${sellerVO.sellerLvId.sellerLvId}</td>
				<td>${sellerVO.sellerEmail}</td>
				<td>${sellerVO.sellerCompany}</td>
				<td>${sellerVO.sellerTaxId}</td>
				<td>${sellerVO.sellerCapital}</td>
				<td>${sellerVO.sellerContact}</td>
				<td>${sellerVO.sellerCompanyPhone}</td>
				<td>${sellerVO.sellerCompanyExtension}</td>
				<td>${sellerVO.sellerMobile}</td>
				<td>${sellerVO.sellerAddress}</td>
				<td>${sellerVO.sellerPassword}</td>
				<td>${sellerVO.sellerBankAccount}</td>
				<td>${sellerVO.sellerBankCode}</td>
				<td>${sellerVO.sellerBankAccountNumber}</td>
				<td>${sellerVO.sellerCreateTime}</td>
				<td>${sellerVO.isConfirm}</td>
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