<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.entity.ArticleVO"%>
<%
//��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�峹�s�W - addArticle.jsp</title>
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
<style>
.readonly-input {
    background-color: #f0f0f0; /* �I���C�� */
    border: 1px solid #ccc;   /* ��� */
    padding: 4px;             /* ����Z */
    color: #555;              /* ��r�C�� */
    cursor: not-allowed;      /* ��м˦� */
}

 .readonly-input:focus { 
    outline: none;  /* �����I���ɪ������u */
 }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�峹�s�W - addArticle.js</h3></td><td>
		 <h4><a href="index.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="article.do" name="form1"> 
<table>
	<tr>
		<td>�|��ID:</td>
		<td><input type="TEXT" name="memberId" value="<%= (articleVO==null)? 1 : articleVO.getMemberId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�峹���D:</td>
		<td><input type="TEXT" name="articleTitle" value="<%= (articleVO==null)? "abc" : articleVO.getArticleTitle()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><input type="TEXT" name="articleContent" value="<%= (articleVO==null)? "abc" : articleVO.getArticleContent()%>" size="45"/></td>
	</tr>
	<tr>
   <tr>
	    <td>�W�Ǯɶ�:</td>
	    <td><input type="datetime-local" name="artUpdateTime"
					value="<%= java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) %>" size="45"></td>
	</tr>
	<tr>
    	<td>���w��:</td>
    	<td><input type="TEXT" name="articleLike" value="" size="10" readonly class="readonly-input"/></td>
	</tr>
	<tr>
    	<td>�d����:</td>
    	<td><input type="TEXT" name="articleComment" value="" size="10" readonly class="readonly-input"/></td>
	</tr>
	<tr>
    	<td>���ɼ�:</td>
    	<td><input type="TEXT" name="articleShare" value="" size="10" readonly class="readonly-input"/></td>
	</tr>
	
	<jsp:useBean id="articleTypeSvc" scope="page" class="com.articleType.model.ArticleTypeService" />
	<tr>
		<td>�峹����:</td>
		<td>
    <select size="1" name="articleSort">
        <c:forEach var="articleTypeVO" items="${articleTypeSvc.all}">
            <option value="${articleTypeVO.articleTypeId}">${articleTypeVO.articleTypeName}</option>
        </c:forEach>
    </select>
</td>

	</tr>
	<tr>
    <td>�峹���A:</td>
    <td>
        <select name="isEnabled" size="1">
		    <option value="1" <%= (articleVO == null || !articleVO.getIsEnabled()) ? "selected" : "" %>>�ҥ�</option>
		    <option value="0" <%= (articleVO != null && articleVO.getIsEnabled()) ? "selected" : "" %>>����</option>
		</select>

    </td>
	</tr>	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W">
</FORM>
</body>
</html>