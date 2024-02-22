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
			<th>文章ID</th>
			<th>會員ID</th>
			<th>文章標題</th>
			<th>文章內容</th>
			<th>上傳時間</th>
			<th>喜歡數</th>
			<th>留言數</th>
			<th>分享數</th>
			<th>文章分類</th>
			<th>啟用狀態</th>
		</tr>
		<c:forEach var="articleVO" items="${requestScope.list}">
			<tr>
			<td>${articleVO.articleId}</td>
				<td>${articleVO.memberId}</td>
				<td>${articleVO.articleTitle}</td>
				<td>${articleVO.articleContent}</td>
				<td>${articleVO.artUpdateTime}</td>
				<td>${articleVO.articleLike}</td>
				<td>${articleVO.articleComment}</td>
				<td>${articleVO.articleShare}</td>
				<td>${articleVO.articleTypeId.articleTypeName}</td>
				<td>${articleVO.isEnabled ? '啟用' : '隱藏'}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<img width="140px" height="100px" alt="要飛囉貓貓"
		src="${pageContext.request.contextPath}/img/inversecat.png">
	<br>
	<br>

	<a href="${pageContext.request.contextPath}/article/index.jsp">回首頁</a>
</body>
</html>