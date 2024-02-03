<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.remittance.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    RemittanceService remittanceSvc = new RemittanceService();
    List<RemittanceVO> list = remittanceSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��״ک��Ӹ�� - listAllRemittance.jsp</title>

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
	width: 100%;
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
	<tr><td>
		 <h3>�Ҧ��״ک��Ӹ�� - listAllRemittance.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/huhcatt.jpeg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�״ڽs��</th>
		<th>��aID</th>
		<th>�w�p�״ڮɶ�</th>
		<th>�״ڮɶ�</th>
		<th>����ɶ�</th>
		<th>��~�B</th>
		<th>����O</th>
		<th>�״ڪ��B</th>
		<th>�״ڪ��A</th>
		<th>�Բӻ���</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="remittanceVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${remittanceVO.remittanceId}</td>
			<td>${remittanceVO.sellerId}</td>
			<td>${remittanceVO.remittanceEstimatedTime}</td>
			<td>${remittanceVO.remittanceTime}</td>
			<td>${remittanceVO.settlementTime}</td>
			<td>${remittanceVO.turnover}</td> 
			<td>${remittanceVO.handlingFee}</td>
			<td>${remittanceVO.remittanceAmount}</td>
			<td>${remittanceVO.remittanceStatus}</td>
			<td>${remittanceVO.explanation}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/remittance/remittance.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="remittanceId"  value="${remittanceVO.remittanceId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/remittance/remittance.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="remittanceId"  value="${remittanceVO.remittanceId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>