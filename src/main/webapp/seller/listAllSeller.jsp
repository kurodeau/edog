<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seller.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    SellerService sellerSvc = new SellerService();
    List<SellerVO> list = sellerSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllSeller.jsp</title>

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
		 <h3>所有員工資料 - listAllSeller.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
	<th>賣家Id</th>
		<th>賣家Lv</th>
		<th>電子郵件</th>
		<th>公司名稱</th>
		<th>統一編號</th>
		<th>資本額</th>
		<th>公司負責人</th>
		<th>公司電話</th>
		<th>公司分機</th>
		<th>手機號碼</th>
		<th>地址</th>
		<th>密碼</th>
		<th>銀行帳戶</th>
		<th>銀行代碼</th>
		<th>銀行帳號</th>
		<th>創建時間</th>
		<th>確認狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="sellerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
		<td>${sellerVO.sellerId}</td>
			<td>${sellerVO.sellerLvId}</td>
			<td>${sellerVO.sellerEmail}</td>
			<td>${sellerVO.sellerCompany}</td>
			<td>${sellerVO.sellerTaxId}</td>
			<td>${sellerVO.sellerCapital}</td>
			<td>${sellerVO.sellerContact}</td> 
			<td>${sellerVO.sellerCompanyPhone}</td>
			<td>${sellerVO.sellerCompanyExtension}</td>
			<td>${sellerVO.sellerMobile}</td>
			<td>${sellerVO.sellerAddress}</td>
			<td>${sellerVO.sellerPassword}</td>
			<td>${sellerVO.sellerBankAccount}</td>
			<td>${sellerVO.sellerBankCode}</td>
			<td>${sellerVO.sellerBankAccountNumber}</td>
			<td>${sellerVO.sellerCreateTime}</td>
			<td>${sellerVO.isConfirm}</td>
			<td>
			  <form method="post" action="<%=request.getContextPath()%>/seller/seller.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="sellerId"  value="${sellerVO.sellerId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </form>
			</td>
			<td>
			  <form method="post" action="<%=request.getContextPath()%>/seller/seller.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="sellerId"  value="${sellerVO.sellerId}">
			     <input type="hidden" name="action" value="delete">
			  </form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>