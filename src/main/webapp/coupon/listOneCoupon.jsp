
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.entity.*"%>
<%@ page import="com.coupon.service.*"%>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); //SellerServlet.java(Concroller), �s�Jreq��couponVO����
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
		<tr>
			<td>
				<h3>���u��� - listOneSeller.jsp</h3>
				<h4>
					<a href="index.jsp"><img src="images/back1.gif" width="100"
						height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�u�f��s��</th>
			<th>�u�f��W��</th>
			<th>�u�f��N�X</th>
			<th>�}�l�ɶ�</th>
			<th>�����ɶ�</th>
			<th>�̧C���O���B</th>
			<th>�u�f��ƶq</th>
			<th>�|���i�ϥμƶq</th>
			<th>�u�f�馩</th>
			<th>�Ыخɶ�</th>

		</tr>
		<tr>

			<td>${couponVO.couponId}</td>
			<td>${couponVO.couponName}</td>
			<td>${couponVO.couponCode}</td>
			<td>${couponVO.startTime}</td>
			<td>${couponVO.endTime}</td>
			<td>${couponVO.minSpendingAmount}</td>
			<td>${couponVO.couponQuantity}</td>
			<td>${couponVO.memberAllowQuantity}</td>
			<td>${couponVO.couponDiscount}</td>
			<td>${couponVO.couponCreateTime}</td>

		</tr>
	</table>

</body>
</html>