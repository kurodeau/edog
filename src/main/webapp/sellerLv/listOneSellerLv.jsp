
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sellerLv.entity.*"%>
<%@ page import="com.sellerLv.service.*"%>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
SellerLvVO sellerLvVO = (SellerLvVO) request.getAttribute("sellerLvVO"); //SellerServlet.java(Concroller), �s�Jreq��sellerVO����
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
			<th>��a����ID</th>
			<th>���ŦW��</th>
			<th>���x����</th>
			<th>�s�i���\����</th>
			<th>�ץX���y</th>
			<th>�B�O�ɧU</th>
			<th>�C��h�f�ɧU</th>
			<th>����u������</th>
			<th>�W�[�ƶq</th>
		</tr>
		<tr>

			<!-- 	�Ĥ@�ӥ��o��sellerLv��H�A�A�o��sellerLvId���� -->
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