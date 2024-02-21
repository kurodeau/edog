<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.buyer.entity.BuyerVO"%>

<%
//��com.coupon.controller.EmpServlet.java��238��s�Jreq��buyerVO���� (������J�榡�����~�ɪ�buyerVO����)
BuyerVO buyerVO = (BuyerVO) request.getAttribute("buyerVO");
%>
<br>
<%=buyerVO == null ? "" : buyerVO.getMemberId()%>

<br>
<br>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>��Ʒs�W - addBuyer.jsp</title>

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
		<tr>
			<td>
				<h3>��Ʒs�W - addBuyer.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="index.jsp"><img src="images/tomcat.png" width="100"
						height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="buyer.do" name="form1">
		<table>
			<tr>
				<td>�Τ�H�c:</td>
				<td><input type="TEXT" name="memberEmail"
					value="<%=(buyerVO == null) ? "�п�J�H�c" : buyerVO.getMemberEmail()%>"
					size="45" /></td>
			</tr>
			
			<!-- �O�_���ĤT�褣�O��ʷs�W -->		
			<!-- 
			<tr>
				<td>�Τ�j�wgoogle�b��:</td>
				<td><input type="TEXT" name="thirdFrom"
					value="<%=(buyerVO == null) ? null : buyerVO.getThirdFrom()%>"
					size="45" /></td>
			</tr>
			 -->
			
			<tr>
				<td>�Τ�W��:</td>
				<td><input type="TEXT" name="memberName"
					value="<%=(buyerVO == null) ? "�п�J�W��" : buyerVO.getMemberName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�Τ�q��:</td>
				<td><input type="TEXT" name="memberPhone"
					value="<%=(buyerVO == null) ? "�г]�w�q��" : buyerVO.getMemberPhone()%>"
					size="45" /></td>
			</tr>			
			<tr>
				<td>�Τ���:</td>
				<td><input type="TEXT" name="memberMobile"
					value="<%=(buyerVO == null) ? "�г]�w���" : buyerVO.getMemberMobile()%>"
					size="45" /></td>
			</tr>			
			<tr>
				<td>�Τ�ͤ�:</td>
				<td><input type="datetime-local" name="memberBirthday" step="1" class = "memberBirthday"
					value="<%=(buyerVO == null) ? "�г]�w�ͤ�" : buyerVO.getMemberBirthday()%>"
					size="45" /></td>
			</tr>			
			<tr>
				<td>�Τ�K�X:</td>
				<td><input type="TEXT" name="memberPassword"
					value="<%=(buyerVO == null) ? "�г]�w�K�X" : buyerVO.getMemberPassword()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�Τ�a�}:</td>
				<td><input type="TEXT" name="memberAddress"
					value="<%=(buyerVO == null) ? "�г]�w�a�}" : buyerVO.getMemberAddress()%>"
					size="45" /></td>
			</tr>
			
			<!-- �O�_���ҹw�]���O�S�� -->				
			<!-- 
			<tr>
				<td>�Τ�O�_���ҹL�H�c:</td>
				<td><input type="TEXT" name="isMemberEmail"
					value="<%=(buyerVO == null) ? "false" : buyerVO.getIsMemberEmail()%>"
					size="45" /></td>
			</tr>
			 -->
			 
            <!-- ���U�ɶ��۰ʷs�W-->
            <!-- 
			<tr>
				<td>�Τ���U�ɶ�:</td>
				<td><input type="TEXT" name="memberRegistrationTime"
					value="<%=(buyerVO == null) ? null : buyerVO.getMemberRegistrationTime()%>"
					size="45" /></td>
			</tr>
			 -->			
			<tr>
				<td>�d���W��:</td>
				<td><input type="TEXT" name="petName"
					value="<%=(buyerVO == null) ? "���]�w�d���W��" : buyerVO.getPetName()%>"
					size="45" /></td>
			</tr>	
			<tr>
				<td>�d���Ӥ�:</td>
				<!-- ���|�B�z�Ϥ� ���屼���e -->				
			</tr>
			
			<!-- ���U�ɶ����|��ʷs�W -->
			<!-- 
			<tr>
				<td>�d���Ӥ���s�ɶ�:</td>
				<td><input type="TEXT" name="petImgUploadTime"
					value="<%=(buyerVO == null) ? "�L�W�ǹϤ��ɶ�" : buyerVO.getPetImgUploadTime()%>"
					size="45" /></td>
			</tr>
			 -->			
			
			<tr>
				<td>�d���̭]�@:</td>
				<td><input type="TEXT" name="petVaccName1"
					value="<%=(buyerVO == null) ? "�]�w�̭]�W�٤@" : buyerVO.getPetVaccName1()%>"
					size="45" /></td>
			</tr>
		    <tr>
				<td>�̭]�@�̪�I�����:</td>
				<td><input type="datetime-local" name="petVaccTime1" step="1" class = "petVaccTime1"
					value="<%=(buyerVO == null) ? null : buyerVO.getPetVaccTime1()%>"
					size="45" /></td>
			</tr>					
			<tr>
				<td>�d���̭]�G:</td>
				<td><input type="TEXT" name="petVaccName2"
					value="<%=(buyerVO == null) ? "�]�w�̭]�W�٤G" : buyerVO.getPetVaccName2()%>"
					size="45" /></td>
			</tr>
			<tr>
			    <td>�̭]�G�̪�I�����:</td>
				<td><input type="datetime-local" name="petVaccTime2" step="1" class = "petVaccTime2"
					value="<%=(buyerVO == null) ? null : buyerVO.getPetVaccTime2()%>"
					size="45" /></td>
			</tr>
			<!-- 	
			<tr>
				<td>�Τ��ƬO�_����:</td>
				<td><input type="TEXT" name="isConfirm"
					value="<%=(buyerVO == null) ? true : buyerVO.getIsConfirm()%>"
					size="45" /></td>
			</tr>
			 -->
		</table>

		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
java.util.Date memberRegistrationTime = null;
try {
	memberRegistrationTime = buyerVO.getMemberRegistrationTime();
} catch (Exception e) {
	memberRegistrationTime = new java.sql.Date(System.currentTimeMillis());
	/* 2147483647-24*24*60*60*1000 < 0 �A�p�G�ɶ��W�L25�ѡA��Int�|�X�{���D */
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	

</script>
</html>