<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

<% //��com.manager.controller.ManagerUserServlet.java��238��s�Jreq��managerVO���� (������J�榡�����~�ɪ�empVO����)
    ManagerUserVO managerUserVO = (ManagerUserVO) request.getAttribute("managerUserVO");
%>
--<%= managerUserVO==null %>--${managerUserVO.managerId}-- <!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��O�޲z�̸�Ʒs�W - addManagerUser.jsp</title>

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
		 <h3>��O�޲z�̸�Ʒs�W - addManagerUser.jsp</h3></td><td>
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

<FORM METHOD="post" ACTION="manager.do" name="form1">
<table>
	
	<tr>
		<td>��O�޲z�̺٩I:</td>
		<td><input type="TEXT" name="managername" value="<%= (managerUserVO==null)? "�w�]�٩I" : managerUserVO.getManagername()%>" size="45"/></td>
	</tr>
	<tr>
		<td>��O�޲z�̱K�X:</td>
		<td><input type="TEXT" name="managerPassword" value="<%= (managerUserVO==null)? "password" : managerUserVO.getManagerPassword()%>" size="45"/></td>
	</tr>
	<tr>
		<td>��O�޲z���v��:</td>
		<td><input type="TEXT" name="managerPer" value="<%= (managerUserVO==null)? "10" : managerUserVO.getManagerPer()%>" size="45"/></td>
	</tr>

	<jsp:useBean id="managerUserSvc" scope="page" class="com.manager.model.ManagerUserService" />
 
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>



       
</html>