<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.entity.ArticleVO"%>
<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章新增 - addArticle.jsp</title>
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
    background-color: #f0f0f0; /* 背景顏色 */
    border: 1px solid #ccc;   /* 邊框 */
    padding: 4px;             /* 內邊距 */
    color: #555;              /* 文字顏色 */
    cursor: not-allowed;      /* 游標樣式 */
}

 .readonly-input:focus { 
    outline: none;  /* 移除點擊時的輪廓線 */
 }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>文章新增 - addArticle.js</h3></td><td>
		 <h4><a href="index.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="article.do" name="form1"> 
<table>
	<tr>
		<td>會員ID:</td>
		<td><input type="TEXT" name="memberId" value="<%= (articleVO==null)? 1 : articleVO.getMemberId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>文章標題:</td>
		<td><input type="TEXT" name="articleTitle" value="<%= (articleVO==null)? "abc" : articleVO.getArticleTitle()%>" size="45"/></td>
	</tr>
	<tr>
		<td>文章內容:</td>
		<td><input type="TEXT" name="articleContent" value="<%= (articleVO==null)? "abc" : articleVO.getArticleContent()%>" size="45"/></td>
	</tr>
	<tr>
   <tr>
	    <td>上傳時間:</td>
	    <td><input type="datetime-local" name="artUpdateTime"
					value="<%= java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) %>" size="45"></td>
	</tr>
	<tr>
    	<td>喜歡數:</td>
    	<td><input type="TEXT" name="articleLike" value="" size="10" readonly class="readonly-input"/></td>
	</tr>
	<tr>
    	<td>留言數:</td>
    	<td><input type="TEXT" name="articleComment" value="" size="10" readonly class="readonly-input"/></td>
	</tr>
	<tr>
    	<td>分享數:</td>
    	<td><input type="TEXT" name="articleShare" value="" size="10" readonly class="readonly-input"/></td>
	</tr>
	
	<jsp:useBean id="articleTypeSvc" scope="page" class="com.articleType.model.ArticleTypeService" />
	<tr>
		<td>文章分類:</td>
		<td>
    <select size="1" name="articleSort">
        <c:forEach var="articleTypeVO" items="${articleTypeSvc.all}">
            <option value="${articleTypeVO.articleTypeId}">${articleTypeVO.articleTypeName}</option>
        </c:forEach>
    </select>
</td>

	</tr>
	<tr>
    <td>文章狀態:</td>
    <td>
        <select name="isEnabled" size="1">
		    <option value="1" <%= (articleVO == null || !articleVO.getIsEnabled()) ? "selected" : "" %>>啟用</option>
		    <option value="0" <%= (articleVO != null && articleVO.getIsEnabled()) ? "selected" : "" %>>隱藏</option>
		</select>

    </td>
	</tr>	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
</body>
</html>