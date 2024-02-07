<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articlePic.model.*"%>
<%
// ArticlePicService articlePicSvc = new ArticlePicService();
// List<ArticlePicVO> list = articlePicSvc.getAll();
// pageContext.setAttribute("list", list);

List<ArticlePicVO> list = (List<ArticlePicVO>) request.getAttribute("list");
pageContext.setAttribute("list", list);

Map<Integer, List<String>> articlePicMap = (Map<Integer, List<String>>) request.getAttribute("articlePicMap");
pageContext.setAttribute("articlePicMap", articlePicMap);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�Ҧ��峹�Ϥ� - listAllArticlePic.jsp</title>
<style>
img {
max-width: 100px; 
max-height: 100px;}


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
				<h3>�Ҧ��峹�Ϥ� - listAllArticlePic.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�峹�Ϥ��s��</th>
			<th>�峹ID</th>
			<th>�Ϥ�</th>
			<th>�W�Ǯɶ�</th>
			<th>�ק�</th>
		</tr>

<%@ include file="page1.file"%>

<c:forEach var="articlePicVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${articlePicVO.articlePicId}</td>
				<td>${articlePicVO.articleId}</td>
				<td>${articlePicVO.articlePicBlob}<br> 
				<br> 
					<img
					src="data:image/jpeg;base64, ${articlePicMap.get(articlePicVO.articlePicId)[0]}"
					alt="Image"><br>
				</td>
				<td>${articlePicVO.articlePicTime}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/articlePic/articlePic.do"						
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="articlePicId" value="${articlePicVO.articlePicId}">
						<input type="hidden" name="action" value="getOne_For_Update">
						
					</FORM>
				</td>
				<%--<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/articlePic/articlePic.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="articlePicId" value="${articlePicVO.articlePicId}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>--%>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page3.file"%>
</body>
</html>