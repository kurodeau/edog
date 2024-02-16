<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="java.util.Base64"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
AdVO adVO = (AdVO) request.getAttribute("adVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>廣告資料修改 - update_ad_input.jsp</title>

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
	width: 100%;
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
				<h3>員工資料修改 - update_ad_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
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

	<FORM METHOD="post" ACTION="ad.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>廣告編號:<font color=red><b>*</b></font></td>
				<td><%=adVO.getAdId()%></td>
			</tr>
			<tr>
				<td>賣家ID:</td>
				<td><input type="TEXT" name="sellerId"
					value="<%= adVO.getSellerId()%>" size="45" /></td>
			</tr>

			<tr>
				<td>廣告名稱:</td>
				<td><input type="TEXT" name="adName"
					value="<%= adVO.getAdName()%>" size="45" /></td>
			</tr>

			<tr>
				<td>廣告網址url:</td>
				<td><input type="TEXT" name="adUrl"
					value="<%=adVO.getAdUrl()%>" size="45" /></td>
			</tr>

			<tr>
				<td>廣告圖片:</td>
				<td><input type="file" name="adImg" accept="image/*" />
					<img src="data:image/png;base64,${Base64.getEncoder().encodeToString(adVO.adImg)}" alt="廣告圖片" style="max-width: 100px; max-height: 100px;">
					</td>
				<!-- 上傳圖片的輸入欄位 -->
			
			</tr>


			<tr>
				<td>廣告開始時間:</td>
				<td><input name="adStartTime" id="f_date1" type="text"></td>
			</tr>

			<tr>
				<td>廣告結束時間:</td>
				<td><input name="adEndTime" id="f_date2" type="text"></td>
			</tr>

			<tr>
				<td>廣告等級:</td>
				<td><input type="TEXT" name="adLv"
					value="<%= adVO.getAdLv()%>" size="45" /></td>
			</tr>


			<tr>
				<td>廣告備註:</td>
				<td><input type="TEXT" name="adMemo"
					value="<%= adVO.getAdMemo()%>" size="45" /></td>
			</tr>


  <tr>
		<td>廣告審核:</td>
		<td><input type="TEXT" name="isAdConfirm"   value="<%= adVO.getIsAdConfirm() %>" size="45"/></td>
	</tr>
	
	
	  <tr>
		<td>廣告狀態]:</td>
		<td><input type="TEXT" name="isEnabled"   value="<%= adVO.getIsEnabled()  %>" size="45"/></td>
	</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="adId" value="<%=adVO.getAdId()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp adStartTime = null;
  try {
	  adStartTime = adVO.getAdStartTime() ;
   } catch (Exception e) {
	   adStartTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

 <% 
  java.sql.Timestamp adEndTime = null;
  try {
	  adEndTime = adVO.getAdEndTime();
   } catch (Exception e) {
	   adEndTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>



<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
        theme: '',              //theme: 'dark',
        timepicker:false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
     value: '<%=adStartTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:             '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
      $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
        theme: '',              //theme: 'dark',
        timepicker:false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
     value: '<%=adEndTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:             '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        

   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //           if (  date.getYear() <  somedate1.getYear() || 
        //             (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //             (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //           if (  date.getYear() >  somedate2.getYear() || 
        //             (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //             (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
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
        //           if (  date.getYear() <  somedate1.getYear() || 
        //             (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //             (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //               ||
        //              date.getYear() >  somedate2.getYear() || 
        //             (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //             (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>