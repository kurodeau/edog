
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seller.entity.*"%>
<%@ page import="com.seller.service.*"%>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO"); //SellerServlet.java(Concroller), �s�Jreq��sellerVO����
%>

<html>
<head>
<title>���u��� - listOneSeller.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneSeller.jsp</h3>
		 <h4><a href="index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
	<th>��aId</th>
	
		<th>��aLv</th>
		<th>�q�l�l��</th>
		<th>���q�W��</th>
		<th>�Τ@�s��</th>
		<th>�ꥻ�B</th>
		<th>���q�t�d�H</th>
		<th>���q�q��</th>
		<th>���q����</th>
		<th>������X</th>
		<th>�a�}</th>
		<th>�K�X</th>
		<th>�Ȧ�b��</th>
		<th>�Ȧ�N�X</th>
		<th>�Ȧ�b��</th>
		<th>�Ыخɶ�</th>
		<th>�T�{���A</th>
	</tr>
	<tr>
	<td>${sellerVO.sellerId}</td>
	
<!-- 	�Ĥ@�ӥ��o��sellerLv��H�A�A�o��sellerLvId���� -->
<%-- value="<%=sellerVO.getSellerLvId().getSellerLvId()%> --%>
			<td>${sellerVO.sellerLvId.sellerLvId}</td>
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
	</tr>
</table>

</body>
</html>