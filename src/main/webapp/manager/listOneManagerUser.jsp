<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.manager.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ManagerUserVO managerUserVO = (ManagerUserVO) request.getAttribute("managerUserVO"); //ManagerUserServlet.java(Concroller), 存入req的managerVO物件
%>

<html>
<head>
<title>後臺管理者資料 - listOneManagerUser.jsp</title>

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
	<tr><td>
		 <h3>後臺管理者資料 - listOneManagerUser.jsp</h3>
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
	<tr>
		<td><%=managerUserVO.getManagerId()%></td>
		<td><%=managerUserVO.getManagername()%></td>
		<td><%=managerUserVO.getManagerPassword()%></td>
		<td><%=managerUserVO.getManagerPer()%></td>
		<td><%=managerUserVO.getCreatetime()%></td>
	</tr>
</table>

</body>
</html>