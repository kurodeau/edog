<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sellerLv.entity.*"%>

<%
//見com.seller.controller.EmpServlet.java第238行存入req的sellerLvVO物件 (此為輸入格式有錯誤時的sellerLvVO物件)
SellerLvVO sellerLvVO = (SellerLvVO) request.getAttribute("sellerLvVO");
%>
<br>
<%=sellerLvVO == null ? "" : sellerLvVO.getSellerLvId()%>

<br>
<br>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addSeller.jsp</title>

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
				<h3>員工資料新增 - addSeller.jsp</h3>
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

	<FORM METHOD="post" ACTION="sellerLv.do" name="form1">
		<table>

		
			<tr>
				<td>Level Name:</td>
				<td><input type="TEXT" name="lvName"
					value="${sellerLvVO == null ? 'DefaultLevelName' : sellerLvVO.lvName}"
					size="45" /></td>
			</tr>
			<tr>
				<td>Platform Commission:</td>
				<td><input type="TEXT" name="platformCommission"
					value="${sellerLvVO == null ? 0.01 : sellerLvVO.platformCommission}"
					size="45" /></td>
			</tr>
			<tr>
				<td>Ad Allow Type:</td>
				<td><input type="TEXT" name="adAllowType"
					value="${sellerLvVO == null ? 1 : sellerLvVO.adAllowType}"
					size="45" /></td>
			</tr>
			<tr>
				<td>Export Goldflow:</td>
				<td><input type="checkbox" name="isExportGoldflow"
					${sellerLvVO != null && sellerLvVO.isExportGoldflow ? 'checked' : ''}>
					Yes</td>
			</tr>
			<tr>
				<td>Freight Sub:</td>
				<td><input type="TEXT" name="freightSub"
					value="${sellerLvVO == null ? 12: sellerLvVO.freightSub}"
					size="45" /></td>
			</tr>
			<tr>
				<td>Return Sub Per Month:</td>
				<td><input type="TEXT" name="returnSubPerMonth"
					value="${sellerLvVO == null ? 33: sellerLvVO.returnSubPerMonth}"
					size="45" /></td>
			</tr>
			<tr>
				<td>Show Priority:</td>
				<td><input type="checkbox" name="isShowPriority"
					${sellerLvVO != null && sellerLvVO.isShowPriority ? 'checked' : ''}>
					Yes</td>
			</tr>
			<tr>
				<td>Shelves Number:</td>
				<td><input type="TEXT" name="shelvesNumber"
					value="${sellerLvVO == null ? 12: sellerLvVO.shelvesNumber}"
					size="45" /></td>
			</tr>
			<!-- <tr> -->
			<!--     <td>建立時間:</td> -->
			<%--     <td><input type="datetime-local" name="sellerCreateTime" value="<%= (sellerLvVO==null)? "" : sellerLvVO.getSellerCreateTime()%>" size="45"/></td> --%>
			<!-- </tr> -->
			<!-- <tr> -->
			<!--     <td>確認狀態:</td> -->
			<%--     <td><input type="checkbox" name="isConfirm" value="<%= (sellerLvVO!=null && sellerLvVO.getIsConfirm())? "checked" : "" %>" /></td> --%>
			<!-- </tr> -->






			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(sellerLvVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


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
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
// 	       theme: '',              //theme: 'dark',
// 	       timepicker:false,       //timepicker:true,
// 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
// 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 		   value: '<%=sellerCreateTime%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>