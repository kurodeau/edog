<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.entity.*"%>
<%@ page import="com.coupon.service.*"%>
<%@ page import="java.util.Date"%>


<%
//��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>
--EL--<%=couponVO == null ? "NULL" : couponVO.getCouponId()%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��ƭק� - update_coupon_input.jsp</title>

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
				<h3>���u��ƭק� - update_coupon_input.jsp</h3>
				<h4>
					<a href="index.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~���C --%>
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
				<td>�u�f��ID<font color=red><b>*</b></font></td>
				<td>${couponVO.couponId}</td>
			</tr>
			<tr>
				<td>�u�f��W��<font color="red"><b>*</b></font></td>
				<td><input type="text" name="couponName"
					value="${couponVO.couponName}" size="45"></td>
			</tr>
			<tr>
				<td>�u�f��N�X<font color="red"><b>*</b></font></td>
				<td><input type="text" name="couponCode"
					value="${couponVO.couponCode}" size="45"></td>
			</tr>
			<tr>
				<td>�}�l�ɶ�<font color="red"><b>*</b></font></td>
				<td><input type="datetime-local" name="startTime" step="1"
					value="${couponVO.startTime}" size="45" class="startTimeInput"></td>
			</tr>


			<tr>
				<td>�����ɶ�<font color="red"><b>*</b></font></td>
				<td><input type="datetime-local" name="endTime" step="1"
					value="${couponVO.endTime}" size="45" class="endTimeInput"></td>
			</tr>
			<tr>
				<td>�̧C���O���B</td>
				<td><input type="number" name="minSpendingAmount"
					value="${couponVO.minSpendingAmount}" size="45"></td>
			</tr>
			<tr>
				<td>�u�f��ƶq</td>
				<td><input type="number" name="couponQuantity"
					value="${couponVO.couponQuantity}" size="45"></td>
			</tr>
			<tr>
				<td>�|���i����ƶq</td>
				<td><input type="number" name="memberAllowQuantity"
					value="${couponVO.memberAllowQuantity}" size="45"></td>
			</tr>
			<tr>
				<td>�u�f�馩</td>
				<td><input type="number" name="couponDiscount"
					value="${couponVO.couponDiscount}" size="45"></td>
			</tr>
			<tr>
				<td>�Ыخɶ�</td>
				<td><input type="datetime-local" name="couponCreateTime"
					step="1" value="${couponVO.couponCreateTime}" size="45" class="createTime"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="couponId" value="<%=couponVO.getCouponId()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
	<button type="button" class="autofill">�۰ʶ��</button>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->


<script>



document.addEventListener("DOMContentLoaded", function () {
	  let startTime_input = document.querySelector(".startTimeInput");
	  let endTime_input = document.querySelector(".endTimeInput");
	let createTime_input = document.querySelector(".createTime");
	  let autofill = document.querySelector(".autofill");

	  // ***********************
	  // constants
	  // ***********************

	  function init() {
	    // ***********************
	    // get data from backend
	    // ***********************
	    let startTimeString = "${couponVO.startTime}";
	    let endTimeString = "${couponVO.endTime}";
	    let createTimeString = "${couponVO.couponCreateTime}";

	    // ***********************
	    // Process data
	    // ***********************
	    try {
	      let formattedDateStr = splitDateFromJava(
	        startTimeString,
	        startTime_input
	      );
	      let formattedDateStr2 = splitDateFromJava(endTimeString, endTime_input);
	      let formattedDateStr3 = splitDateFromJava(createTimeString, createTime_input);
		

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

	      // (2) �᭱�[Z�A�N���OUTC�ɶ�(��L�ªv)
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

	  autofill.addEventListener("click", () => {
	    // Example: Set the start time to the current date and time

	    let currentDateTime = new Date().toISOString().slice(0, 19);
	    console.log(currentDateTime);
	    startTime_input.value = currentDateTime;
	    endTime_input.value = currentDateTime;
	  });

	  init();
	});


</script>

</html>