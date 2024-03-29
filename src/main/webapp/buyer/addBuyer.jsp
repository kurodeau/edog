<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.buyer.entity.BuyerVO"%>

<%
//見com.coupon.controller.EmpServlet.java第238行存入req的buyerVO物件 (此為輸入格式有錯誤時的buyerVO物件)
BuyerVO buyerVO = (BuyerVO) request.getAttribute("buyerVO");
%>
<br>
<%=buyerVO == null ? "" : buyerVO.getMemberId()%>

<br>
<br>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>資料新增 - addBuyer.jsp</title>

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
				<h3>資料新增 - addBuyer.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="index.jsp"><img src="images/tomcat.png" width="100"
						height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="buyer.do" name="form1">
		<table>
			<tr>
				<td>用戶信箱:</td>
				<td><input type="TEXT" name="memberEmail"
					value="<%=(buyerVO == null) ? "請輸入信箱" : buyerVO.getMemberEmail()%>"
					size="45" /></td>
			</tr>
			
			<!-- 是否有第三方不是手動新增 -->		
			<!-- 
			<tr>
				<td>用戶綁定google帳號:</td>
				<td><input type="TEXT" name="thirdFrom"
					value="<%=(buyerVO == null) ? null : buyerVO.getThirdFrom()%>"
					size="45" /></td>
			</tr>
			 -->
			
			<tr>
				<td>用戶名稱:</td>
				<td><input type="TEXT" name="memberName"
					value="<%=(buyerVO == null) ? "請輸入名稱" : buyerVO.getMemberName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>用戶電話:</td>
				<td><input type="TEXT" name="memberPhone"
					value="<%=(buyerVO == null) ? "請設定電話" : buyerVO.getMemberPhone()%>"
					size="45" /></td>
			</tr>			
			<tr>
				<td>用戶手機:</td>
				<td><input type="TEXT" name="memberMobile"
					value="<%=(buyerVO == null) ? "請設定手機" : buyerVO.getMemberMobile()%>"
					size="45" /></td>
			</tr>			
			<tr>
				<td>用戶生日:</td>
				<td><input type="datetime-local" name="memberBirthday" step="1" class = "memberBirthday"
					value="<%=(buyerVO == null) ? "請設定生日" : buyerVO.getMemberBirthday()%>"
					size="45" /></td>
			</tr>			
			<tr>
				<td>用戶密碼:</td>
				<td><input type="TEXT" name="memberPassword"
					value="<%=(buyerVO == null) ? "請設定密碼" : buyerVO.getMemberPassword()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>用戶地址:</td>
				<td><input type="TEXT" name="memberAddress"
					value="<%=(buyerVO == null) ? "請設定地址" : buyerVO.getMemberAddress()%>"
					size="45" /></td>
			</tr>
			
			<!-- 是否驗證預設都是沒有 -->				
			<!-- 
			<tr>
				<td>用戶是否驗證過信箱:</td>
				<td><input type="TEXT" name="isMemberEmail"
					value="<%=(buyerVO == null) ? "false" : buyerVO.getIsMemberEmail()%>"
					size="45" /></td>
			</tr>
			 -->
			 
            <!-- 註冊時間自動新增-->
            <!-- 
			<tr>
				<td>用戶註冊時間:</td>
				<td><input type="TEXT" name="memberRegistrationTime"
					value="<%=(buyerVO == null) ? null : buyerVO.getMemberRegistrationTime()%>"
					size="45" /></td>
			</tr>
			 -->			
			<tr>
				<td>寵物名稱:</td>
				<td><input type="TEXT" name="petName"
					value="<%=(buyerVO == null) ? "未設定寵物名稱" : buyerVO.getPetName()%>"
					size="45" /></td>
			</tr>	
			<tr>
				<td>寵物照片:</td>
				<!-- 不會處理圖片 先砍掉內容 -->				
			</tr>
			
			<!-- 註冊時間不會手動新增 -->
			<!-- 
			<tr>
				<td>寵物照片更新時間:</td>
				<td><input type="TEXT" name="petImgUploadTime"
					value="<%=(buyerVO == null) ? "無上傳圖片時間" : buyerVO.getPetImgUploadTime()%>"
					size="45" /></td>
			</tr>
			 -->			
			
			<tr>
				<td>寵物疫苗一:</td>
				<td><input type="TEXT" name="petVaccName1"
					value="<%=(buyerVO == null) ? "設定疫苗名稱一" : buyerVO.getPetVaccName1()%>"
					size="45" /></td>
			</tr>
		    <tr>
				<td>疫苗一最近施打日期:</td>
				<td><input type="datetime-local" name="petVaccTime1" step="1" class = "petVaccTime1"
					value="<%=(buyerVO == null) ? null : buyerVO.getPetVaccTime1()%>"
					size="45" /></td>
			</tr>					
			<tr>
				<td>寵物疫苗二:</td>
				<td><input type="TEXT" name="petVaccName2"
					value="<%=(buyerVO == null) ? "設定疫苗名稱二" : buyerVO.getPetVaccName2()%>"
					size="45" /></td>
			</tr>
			<tr>
			    <td>疫苗二最近施打日期:</td>
				<td><input type="datetime-local" name="petVaccTime2" step="1" class = "petVaccTime2"
					value="<%=(buyerVO == null) ? null : buyerVO.getPetVaccTime2()%>"
					size="45" /></td>
			</tr>
			<!-- 	
			<tr>
				<td>用戶資料是否有效:</td>
				<td><input type="TEXT" name="isConfirm"
					value="<%=(buyerVO == null) ? true : buyerVO.getIsConfirm()%>"
					size="45" /></td>
			</tr>
			 -->
		</table>

		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.util.Date memberRegistrationTime = null;
try {
	memberRegistrationTime = buyerVO.getMemberRegistrationTime();
} catch (Exception e) {
	memberRegistrationTime = new java.sql.Date(System.currentTimeMillis());
	/* 2147483647-24*24*60*60*1000 < 0 ，如果時間超過25天，用Int會出現問題 */
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