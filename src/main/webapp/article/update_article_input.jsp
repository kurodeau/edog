<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
//��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
String formattedDateTime = dateTimeFormat.format(currentTimestamp);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�峹�ק� - update_article_input.jsp</title>
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
		<tr>
			<td>
				<h3>�峹�ק� - update_article_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="article.do" name="form1">
	<table>
	<tr>
		<td>�峹Id:<font color=red><b>*</b></font></td>
		<td><input type="text" name="articleId" value="<%=articleVO.getArticleId()%>" class="readonly-input" readonly /></td>
	</tr>
	<tr>
		<td>�|��Id:</td>
		<td><input type="text" name="memberId" value="<%=articleVO.getMemberId()%>" class="readonly-input" readonly /></td>
	</tr>
	<tr>
		<td>�峹���D:</td>
		<td><input type="TEXT" name="articleTitle"   value="<%=articleVO.getArticleTitle()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><input type="TEXT" name="articleContent"   value="<%=articleVO.getArticleContent()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�峹��s�ɶ�:</td>
		<td><input name="artUpdateTime" id="f_date1" type="datetime-local" value="<%= formattedDateTime %>"></td>
	</tr>
	<tr>
	    <td>���w��:</td>
	    <td><input type="text" name="articleLike" value="<%=articleVO.getArticleLike()%>" class="readonly-input" readonly /></td>
	</tr>
	<tr>
	    <td>�d����:</td>
	    <td><input type="text" name="articleComment" value="<%=articleVO.getArticleComment()%>" class="readonly-input" readonly /></td>
	</tr>
	<tr>
	    <td>���ɼ�:</td>
	    <td><input type="text" name="articleShare" value="<%=articleVO.getArticleShare()%>" class="readonly-input" readonly /></td>
	</tr>
	<jsp:useBean id="articleTypeSvc" scope="page" class="com.articleType.model.ArticleTypeService" />
	<tr>
    <td>�峹����:</td>
    <td>
        <select size="1" name=articleSort>
            <c:forEach var="articleTypeVO" items="${articleTypeSvc.all}">
                <option value="${articleTypeVO.articleTypeId}" ${articleTypeVO.articleTypeId eq articleVO.articleSort ? 'selected' : ''}>
                    ${articleTypeVO.articleTypeName}
                </option>
            </c:forEach>
        </select>
    </td>
	</tr>
	<tr>
    <td>�ҥΪ��A:</td>
    <td>
        <select name="isEnabled" size="1">
		    <option value="1" ${articleVO != null && articleVO.getIsEnabled() ? 'selected' : ''}>�ҥ�</option>
		    <option value="0" ${articleVO == null || !articleVO.getIsEnabled() ? 'selected' : ''}>����</option>
		</select>     
    </td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="articleId" value="<%=articleVO.getArticleId()%>">
<input type="submit" value="�e�X�ק�">
	</FORM>
</body>
</html>