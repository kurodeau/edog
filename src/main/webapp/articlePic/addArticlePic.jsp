<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.articlePic.model.*"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
//��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    // �N Timestamp �榡�Ƭ� "yyyy-MM-ddTHH:mm" �榡
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    String formattedDateTime = dateTimeFormat.format(currentTimestamp);
ArticlePicVO articlePicVO = (ArticlePicVO) request.getAttribute("articlePicVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�峹�Ϥ��s�W - addArticlePic.jsp</title>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�峹�Ϥ��s�W - addArticlePic.js</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="articlePic.do" name="form1" enctype="multipart/form-data"> 
<!-- �[�W��^�ǭȬ�null enctype="multipart/form-data" -->
<table>
	

	<tr>
		<td>�峹ID:</td>
		<td><input type="TEXT" name="articleId" value="<%= (articlePicVO==null)? "1" : articlePicVO.getArticleId()%>" size="45"/></td>
	</tr>
	<tr>
	 <tr>
        <td>�Ϥ�:</td>
        <td><input type="file" name="articlePicBlob" accept="image/*" /></td> <!-- �W�ǹϤ�����J��� -->
    </tr>
<!-- 		<td>�Ϥ�:</td> -->
<%-- 		<td><input type="TEXT" name="articlePicBlob" value="<%= (articlePicVO==null)? "�Ϥ�" : articlePicVO.getArticlePicBlob()%>" size="45"/></td> --%>
<!-- 		<td><input type="file" name="articlePicBlob"></td> -->
	<tr>
	    <td>�W�Ǯɶ�:</td>
	    <td><input name="articlePicTime" id="f_date1" type="datetime-local" value="<%= formattedDateTime %>"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>
</html>