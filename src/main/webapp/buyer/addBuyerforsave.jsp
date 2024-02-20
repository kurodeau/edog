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
	
	
	
<!-- �|���M���o�̦b���ƻ� -->
document.addEventListener("DOMContentLoaded", function () {
	  let startTime_input = document.querySelector(".startTime");
	  let endTime_input = document.querySelector(".endTime");

	  // ***********************
	  // constants
	  // ***********************

	  function init() {
	    // ***********************
	    // get data from backend
	    // ***********************
	    let startTimeString = "${buyerVO.memberRegistrationTime}";
	    let endTimeString = "${buyerVO.memberRegistrationTime}";

	    // ***********************
	    // Process data
	    // ***********************
	    try {
	      let formattedDateStr = splitDateFromJava(
	        startTimeString,
	        startTime_input
	      );
	      
	      let formattedDateStr2 = splitDateFromJava(endTimeString, endTime_input);
		

	    } catch (error) {
	      console.error("FATAL: Parse Date String");
	    }
	  }

	  function splitDateFromJava(dateString, element) {
	    // prettier-ignore
	    const monthAbbreviations = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

	    // prettier-ignore
	    const javaDateRegex =/^([A-Za-z]+) ([A-Za-z]+) (\d{2}) (\d{2}:\d{2}:\d{2}) ([A-Za-z]+) (\d{4})$/;

	    // prettier-ignore
	    const isoDateRegex = /^(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d)$/;

	    if (!dateString) {
	      return "dateStringNoMatch:" + "(" + typeof dateString + ")" + dateString;
	    }

	    if (dateString.match(javaDateRegex)) {
	      // javaDateRegex: Wed Nov 01 00:04:04 CST 2023

	      let parts = dateString.split(" ");

	      let year = parseInt(parts[5]);

	      let monthNumeric = parts[1];
	      let month = monthAbbreviations.indexOf(monthNumeric);

	      let day = parseInt(parts[2]);

	      const [hour, minute, second] = parts[3]
	        .split(":")
	        .map((part) => parseInt(part));


	      // prettier-ignore
	      let dt3 =
	            year + '-' +
	            (month + 1 < 10 ? '0' : '') + (month + 1) + '-' +
	            (day < 10 ? '0' : '') + day + 'T' +
	            (hour < 10 ? '0' : '') + hour + ':' +
	            (minute < 10 ? '0' : '') + minute + ':' +
	            (second < 10 ? '0' : '') + second;
	
	      element.value = dt3;
	      

	      return "dateString";
	    }

	    if (dateString.match(isoDateRegex)) {
	      const dateObject = new Date(dateString);
	      // Extract components
	      const year = dateObject.getFullYear();
	      const month = dateObject.getMonth(); // Months are zero-based, so add 1
	      const day = dateObject.getDate();
	      const hour = dateObject.getHours();
	      const minute = dateObject.getMinutes();
	      const second = dateObject.getSeconds();

	      // (1) �|�নUTC�ɶ�(��L�ªv)
	      // let dt = new Date(year, month, day, hour, minute, second).toISOString();
	      // console.log(dt);
	      // console.log("dt" + dt);

	      // (2) �᭱�[Z�A�N��OUTC�ɶ�(��L�ªv)
	      let dt2 =
	        new Date(year, month, day, hour, minute, second)
	          .toISOString()
	          .split(".")[0] + "Z";

	      // (3) �ۤv����ISO
	      // 2023-11-01T00:04:04
	      // prettier-ignore
	      let dt3 =year + "-" + (month + 1 < 10 ? "0" : "") + (month + 1) + "-" + (day < 10 ? "0" : "") 
	      + day + "T" + (hour < 10 ? "0" : "") + hour + ":" 
	      + (minute < 10 ? "0" : "") + minute + ":" + (second < 10 ? "0" : "") + second;

	      // prettier-ignore

	      element.value = new String(dt3);
	      return "isoDateStringRegex";
	    }

	    return "unexpected";
	  }

	

	  init();
	});
<!---->



</script>
</html>