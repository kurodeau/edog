<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.remittance.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  RemittanceVO remittanceVO = (RemittanceVO) request.getAttribute("remittanceVO"); //EmpServlet.java(Concroller), �s�Jreq��remittanceVO����
%>

<html>
<head>
<title>���u��� - listOneRemittance.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneEmp.jsp</h3>
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
	</tr>
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
	</tr>
</table>

</body>
</html>