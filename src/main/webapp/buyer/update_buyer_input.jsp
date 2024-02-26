<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.buyer.entity.*"%>
<%@ page import="com.buyer.service.*"%>
<%@ page import="java.util.Date"%>


<%
//見com.buyer.controller.BuyerServlet.java第163行存入req的buyerVO物件 (此為從資料庫取出的buyerVO, 也可以是輸入格式有錯誤時的buyerVO物件)
BuyerVO buyerVO = (BuyerVO) request.getAttribute("buyerVO");
%>
--EL--<%=buyerVO == null ? "NULL" : buyerVO.getMemberId()%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料修改 - update_buyer_input.jsp</title>

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
				<h3>員工資料修改 - update_buyer_input.jsp</h3>
				<h4>
					<a href="index.jsp"><img src="images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="buyer.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>會員Id<font color=red ><b>*</b></font></td>
				<td >${buyerVO.memberId}</td>
			</tr>
			<tr>
				<td>會員信箱<font color="red"><b>*</b></font></td>
				<td><input type="text" name="memberEmail"
					value="${buyerVO.memberEmail}" size="45"></td>
			</tr>
			<tr>
				<td>第三方資訊</td>
				<td><input type="text" name="thirdFrom"
					value="${buyerVO.thirdFrom}" size="45"></td>
			</tr>
			<tr>
				<td>會員名稱<font color="red"><b>*</b></font></td>
				<td><input type="text" name="memberName"
					value="${buyerVO.memberName}" size="45"></td>
			</tr>
			<tr>
				<td>會員電話<font color="red"><b>*</b></font></td>
				<td><input type="text" name="memberPhone"
					value="${buyerVO.memberPhone}" size="45"></td>
			</tr>
			<tr>
				<td>會員手機<font color="red"><b>*</b></font></td>
				<td><input type="text" name="memberMobile"
					value="${buyerVO.memberMobile}" size="45"></td>
			</tr>
		    <tr>
				<td>開始時間<font color="red"><b>*</b></font></td>
				<td><input type="datetime-local" name="memberBirthday" step="1"
					value="${buyerVO.memberBirthday}" size="45" class="memberBirthday"></td>
			</tr>
			<tr>
				<td>會員密碼<font color="red"><b>*</b></font></td>
				<td><input type="text" name="memberPassword"
					value="${buyerVO.memberPassword}" size="45"></td>
			</tr>
			<tr>
				<td>會員地址<font color="red"><b>*</b></font></td>
				<td><input type="text" name="memberAddress"
					value="${buyerVO.memberAddress}" size="45"></td>
			</tr>			
			<tr>
				<td>是否驗證信箱<font color="red"><b>*</b></font></td>
				<td><input type="text" name="isMemberEmail"
					value="${buyerVO.isMemberEmail}" size="45"></td>
			</tr>	
		    <tr>
				<td>會員註冊時間<font color="red"><b>*</b></font></td>
				<td><input type="datetime-local" name="memberRegistrationTime" step="1"
					value="${buyerVO.memberRegistrationTime}" size="45" class="memberRegistrationTime"></td>
			</tr>
			<tr>
				<td>寵物名稱<font color="red"><b>*</b></font></td>
				<td><input type="text" name="petName"
					value="${buyerVO.petName}" size="45"></td>
			</tr>	
			<tr>
				<td>寵物圖片<font color="red"><b>*</b></font></td>
				<!-- 不會處理圖片 先砍掉內容 -->

			</tr>
			<tr>
				<td>寵物圖片更新時間<font color="red"><b>*</b></font></td>
				<td><input type="datetime-local" name="petImgUploadTime" step="1"
					value="${buyerVO.petImgUploadTime}" size="45" class="petImgUploadTime"></td>
			</tr>
			<tr>
				<td>寵物疫苗一<font color="red"><b>*</b></font></td>
				<td><input type="text" name="petVaccName1"
					value="${buyerVO.petVaccName1}" size="45"></td>
			</tr>	
			<tr>
				<td>疫苗一施打時間<font color="red"><b>*</b></font></td>
				<td><input type="datetime-local" name="petVaccTime1" step="1"
					value="${buyerVO.petVaccTime1}" size="45" class="petVaccTime1"></td>
			</tr>		
			<tr>
				<td>寵物疫苗二<font color="red"><b>*</b></font></td>
				<td><input type="text" name="petVaccName2"
					value="${buyerVO.petVaccName2}" size="45"></td>
			</tr>			
			<tr>
				<td>疫苗二施打時間<font color="red"><b>*</b></font></td>
				<td><input type="datetime-local" name="petVaccTime2" step="1"
					value="${buyerVO.petVaccTime2}" size="45" class="petVaccTime2"></td>
			</tr>	
			<tr>
				<td>資料是否生效<font color="red"><b>*</b></font></td>
				<td><input type="text" name="isConfirm"
					value="${buyerVO.isConfirm}" size="45"></td>
			</tr>	

		</table>
		<br>
		<input type="hidden" name="action" value="update"> 
<!-- 	<input type="hidden" name="memberId" value="<%=buyerVO.getMemberId()%>">  -->
		<input type="hidden" name="memberId" value="${buyerVO.memberId}">
		<input type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<script>


<!-- 尚不清楚這些內容作用
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

	      // (1) 會轉成UTC時間(格林威治)
	      // let dt = new Date(year, month, day, hour, minute, second).toISOString();
	      // console.log(dt);
	      // console.log("dt" + dt);

	      // (2) 後面加Z，代表是UTC時間(格林威治)
	      let dt2 =
	        new Date(year, month, day, hour, minute, second)
	          .toISOString()
	          .split(".")[0] + "Z";

	      // (3) 自己拼接ISO
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

-->

</script>

</html>