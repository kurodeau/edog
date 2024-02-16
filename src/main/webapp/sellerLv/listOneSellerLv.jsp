
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sellerLv.entity.*"%>
<%@ page import="com.sellerLv.service.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
SellerLvVO sellerLvVO = (SellerLvVO) request.getAttribute("sellerLvVO"); //SellerServlet.java(Concroller), 存入req的sellerVO物件
%>

<html>
<head>
<title>員工資料 - listOneSeller.jsp</title>

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
		<tr>
			<td>
				<h3>員工資料 - listOneSeller.jsp</h3>
				<h4>
					<a href="index.jsp"><img src="images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>賣家等級ID</th>
			<th>等級名稱</th>
			<th>平台佣金</th>
			<th>廣告允許類型</th>
			<th>匯出金流</th>
			<th>運費補助</th>
			<th>每月退貨補助</th>
			<th>顯示優先順序</th>
			<th>上架數量</th>
		</tr>
		<tr>

			<!-- 	第一個先得到sellerLv對象，再得到sellerLvId的值 -->
			<%-- value="<%=sellerLvVO.getSellerLvId().getSellerLvId()%> --%>
			<td>${sellerLvVO.sellerLvId}</td>
			<td>${sellerLvVO.lvName}</td>
			<td>${sellerLvVO.platformCommission}</td>
			<td>${sellerLvVO.adAllowType}</td>
			<td>${sellerLvVO.isExportGoldflow ? 'Yes' : 'No'}</td>
			<td>${sellerLvVO.freightSub}</td>
			<td>${sellerLvVO.returnSubPerMonth}</td>
			<td>${sellerLvVO.isShowPriority ? 'Yes' : 'No'}</td>
			<td>${sellerLvVO.shelvesNumber}</td>
		</tr>
	</table>

</body>
</html>