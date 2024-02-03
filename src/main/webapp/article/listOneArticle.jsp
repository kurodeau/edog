<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.article.model.*"%>
<%
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html>
<html>
<head>
<title>文章資料 - listOneArticle.jsp</title>

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
				<h3>文章資料 - listOneArticle.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
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
		
		<tr>
				<td>${articleVO.articleId}</td>
				<td>${articleVO.memberId}</td>
				<td>${articleVO.articleTitle}</td>
				<td>${articleVO.articleContent}</td>
				<td>${articleVO.artUpdateTime}</td>
				<td>${articleVO.articleLike}</td>
				<td>${articleVO.articleComment}</td>
				<td>${articleVO.articleShare}</td>
				<td>${articleVO.articleSort}</td>
				<td>${articleVO.isEnabled ? '啟用' : '隱藏'}</td>
				
		</tr>
	</table>

</body>
</html>