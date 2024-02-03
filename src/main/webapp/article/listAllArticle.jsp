<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%
ArticleService articleSvc = new ArticleService();
List<ArticleVO> list = articleSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�Ҧ��峹 - listAllArticle.jsp</title>
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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ��峹 - listAllArticle.jsp</h3>
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
			<th>�ק�</th>
<!-- 			<th>�R��</th> -->
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

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
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="articleId" value="${articleVO.articleId}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/articlePic/articlePic.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="�R��"> <input type="hidden" -->
<%-- 							name="articlePicId" value="${articleVO.articleId}"> <input type="hidden" --%>
<!-- 							name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>