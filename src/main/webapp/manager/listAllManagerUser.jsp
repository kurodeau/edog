<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.entity.*"%>
<%@ page import="com.manager.service.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ManagerUserService managerUserSvc = new ManagerUserService();
    List<ManagerUserVO> list = managerUserSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有後臺管理者資料 - listAllManagerUser.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有後臺管理者資料 - listAllManagerUser.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>後臺管理者編號</th>
		<th>後臺管理者稱呼</th>
		<th>後臺管理者密碼</th>
		<th>後臺管理者權限</th>
		<th>新增管理者日期</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="managerUserVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${managerUserVO.managerId}</td>
			<td>${managerUserVO.managername}</td>
			<td>${managerUserVO.managerPassword}</td>
			<td>${managerUserVO.managerPer}</td>
			<td>${managerUserVO.createtime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="managerId"  value="${managerUserVO.managerId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="managerId"  value="${managerUserVO.managerId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>