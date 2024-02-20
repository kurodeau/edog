<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.buyer.entity.*"%>
<%@ page import="com.buyer.service.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
BuyerVO buyerVO = (BuyerVO) request.getAttribute("buyerVO"); //BuyerServlet.java(Concroller), 存入req的buyerVO物件
%>

<html>
<head>
<title>會員資料 - listOnebuyerer.jsp</title>

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
				<h3>員工資料 - listOnebuyer.jsp</h3>
				<h4>
					<a href="index.jsp"><img src="images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>會員編號</th>
			<th>會員信箱</th>
			<th>第三方綁定</th>
			<th>會員名稱</th>
			<th>會員電話</th>
			<th>會員手機</th>
			<th>會員生日</th>
			<th>會員密碼</th>
			<th>會員地址</th>
			<th>是否驗證信箱</th>
			<th>會員註冊時間</th>
			<th>寵物名稱</th>
			<th>寵物圖片</th>
			<th>寵物圖片更新時間</th>
			<th>寵物疫苗名稱一</th>
			<th>最近施打時間</th>
			<th>寵物疫苗名稱二</th>
			<th>最近施打時間</th>
			<th>用戶資料是否生效</th>
		</tr>
		<tr>
			<td>${buyerVO.memberId}</td>
			<td>${buyerVO.memberEmail}</td>
			<td>${buyerVO.thirdFrom}</td>
			<td>${buyerVO.memberName}</td>
			<td>${buyerVO.memberPhone}</td>
			<td>${buyerVO.memberMobile}</td>
			<td>${buyerVO.memberBirthday}</td>
			<td>${buyerVO.memberPassword}</td>
			<td>${buyerVO.memberAddress}</td>
			<td>${buyerVO.isMemberEmail}</td>
			<td>${buyerVO.memberRegistrationTime}</td>
			<td>${buyerVO.petName}</td>
			<td>${buyerVO.petImg}</td>
			<td>${buyerVO.petImgUploadTime}</td>
			<td>${buyerVO.petVaccName1}</td>
			<td>${buyerVO.petVaccTime1}</td>
			<td>${buyerVO.petVaccName2}</td>
			<td>${buyerVO.petVaccTime2}</td>
			<td>${buyerVO.isConfirm}</td>
		</tr>
	</table>

</body>
</html>