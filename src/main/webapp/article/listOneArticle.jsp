<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.article.model.*"%>
<%
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<!DOCTYPE html>
<html>
<head>
<title>�峹��� - listOneArticle.jsp</title>

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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�峹��� - listOneArticle.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�峹ID</th>
			<th>�|��ID</th>
			<th>�峹���D</th>
			<th>�峹���e</th>
			<th>�W�Ǯɶ�</th>
			<th>���w��</th>
			<th>�d����</th>
			<th>���ɼ�</th>
			<th>�峹����</th>
			<th>�ҥΪ��A</th>
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
				<td>${articleVO.isEnabled ? '�ҥ�' : '����'}</td>
				
		</tr>
	</table>

</body>
</html>