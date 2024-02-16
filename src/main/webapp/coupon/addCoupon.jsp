<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.entity.CouponVO"%>

<%
//��com.coupon.controller.EmpServlet.java��238��s�Jreq��couponVO���� (������J�榡�����~�ɪ�couponVO����)
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>
<br>
<%=couponVO == null ? "" : couponVO.getCouponId()%>

<br>
<br>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>��Ʒs�W - addCoupon.jsp</title>

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
				<h3>��Ʒs�W - addCoupon.jsp</h3>
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

	<FORM METHOD="post" ACTION="coupon.do" name="form1">
		<table>
			<tr>
				<td>�u�f��W��:</td>
				<td><input type="TEXT" name="couponName"
					value="<%=(couponVO == null) ? "CouponName" : couponVO.getCouponName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�u�f�X:</td>
				<td><input type="TEXT" name="couponCode"
					value="<%=(couponVO == null) ? "TEST123" : couponVO.getCouponCode()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�}�l�ɶ�:</td>
				<td><input type="datetime-local" name="startTime" step="1" class = "startTime"
					value="<%=(couponVO == null) ? "2024-12-12T23:59:12" : couponVO.getStartTime()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�����ɶ�:</td>
				<td><input type="datetime-local" name="endTime" step="1" class = "endTime"
					value="<%=(couponVO == null) ? "2024-12-12T23:59:12" : couponVO.getEndTime()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�̧C���O���B:</td>
				<td><input type="TEXT" name="minSpendingAmount"
					value="<%=(couponVO == null) ? "100" : couponVO.getMinSpendingAmount()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�u�f��ƶq:</td>
				<td><input type="TEXT" name="couponQuantity"
					value="<%=(couponVO == null) ? "10" : couponVO.getCouponQuantity()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�|���i�ϥμƶq:</td>
				<td><input type="TEXT" name="memberAllowQuantity"
					value="<%=(couponVO == null) ? "5" : couponVO.getMemberAllowQuantity()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>�u�f�馩:</td>
				<td><input type="TEXT" name="couponDiscount"
					value="<%=(couponVO == null) ? "20" : couponVO.getCouponDiscount()%>"
					size="45" /></td>
			</tr>
		</table>

		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
java.util.Date couponCreateTime = null;
try {
	couponCreateTime = couponVO.getCouponCreateTime();
} catch (Exception e) {
	couponCreateTime = new java.sql.Date(System.currentTimeMillis());
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
	    let startTimeString = "${couponVO.startTime}";
	    let endTimeString = "${couponVO.endTime}";

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




</script>
</html>