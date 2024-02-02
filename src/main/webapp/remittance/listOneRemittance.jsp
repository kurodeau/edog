<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.remittance.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  RemittanceVO remittanceVO = (RemittanceVO) request.getAttribute("remittanceVO"); //EmpServlet.java(Concroller), 存入req的remittanceVO物件
%>

<html>
<head>
<title>員工資料 - listOneRemittance.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/huhcatt.jpeg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>匯款編號</th>
		<th>賣家ID</th>
		<th>預計匯款時間</th>
		<th>匯款時間</th>
		<th>結算時間</th>
		<th>營業額</th>
		<th>手續費</th>
		<th>匯款金額</th>
		<th>匯款狀態</th>
		<th>詳細說明</th>
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