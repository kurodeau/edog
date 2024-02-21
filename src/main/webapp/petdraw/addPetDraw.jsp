<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petdraw.entity.*"%>
<%
//��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
PetDrawVO petDrawVO = (PetDrawVO) request.getAttribute("petDrawVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�d����d�s�W - addPetDraw.jsp</title>


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
				<h3>�d����d�s�W - addPetDraw.jsp</h3>
			</td>
			<td>

				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>
	<!-- �p�G�����~�����A��ܿ��~���� -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="petDraw.do" name="form1">
		<table>
			<tr>
				<td>�Q�t��쪺��H:</td>
				<td><c:if test="${PetDrawVO ne null}">
                        �t���HID: ${PetDrawVO.getMemberPairId()}<br>
						<img src="${pairedPetDrawVO.petImageURL}" alt="�d���Ϥ�">
					</c:if>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" name="action" value="�P�N">
	</FORM>
</body>
</html>
