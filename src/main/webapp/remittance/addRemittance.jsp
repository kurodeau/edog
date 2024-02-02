<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.remittance.model.*"%>

<% //見com.emp.controller.RemittanceServlet.java第238行存入req的remittanceVO物件 (此為輸入格式有錯誤時的remittanceVO物件)
   RemittanceVO remittanceVO = (RemittanceVO) request.getAttribute("remittanceVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addRemittance.jsp</title>

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
	width: 500px;
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
	<tr><td>
		 <h3>員工資料新增 - addRemittance.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/huhcatt.jpeg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="remittance.do" name="form1">
<table>
	
	
	
	
	<tr>
		<td>賣家ID:</td>
		<td><input type="text" name="sellerId" value="<%= (remittanceVO==null)? "1" : remittanceVO.getSellerId()%>" size="45" required = "required" /></td>
	</tr>
	
	<tr>
		<td>預計匯款時間:</td>
		<td><input type="text" id="f_date1" name="remittanceEstimatedTime" ></td>
	</tr>
	
	<tr>
		<td>匯款時間:</td>
		<td><input type="text" id="f_date2" name="remittanceTime" ></td>
	</tr>
	
	<tr>
		<td>結算時間:</td>
		<td><input type="text" id="f_date3" name="settlementTime" ></td>
	</tr>
	
	<tr>
		<td>營業額:</td>
		<td><input type="TEXT"  name="turnover"  value="<%= (remittanceVO==null)? "100" : remittanceVO.getTurnover()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>手續費:</td>
		<td><input type="TEXT" name="handlingFee"  value="<%= (remittanceVO==null)? "10" : remittanceVO.getHandlingFee()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>匯款金額:</td>
		<td><input type="TEXT" name="remittanceAmount"  value="<%= (remittanceVO==null)? "90" : remittanceVO.getRemittanceAmount()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>匯款狀態:</td>
		<td><input type="TEXT" name="remittanceStatus"  value="<%= (remittanceVO==null)? "1" : remittanceVO.getRemittanceStatus()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>詳細說明:</td>
		<td><input type="TEXT" name="explanation"  value="<%= (remittanceVO==null)? "還沒匯款" : remittanceVO.getExplanation()%>" size="45"/></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp remittanceEstimatedTime = null;
  try {
	  remittanceEstimatedTime = remittanceVO.getRemittanceEstimatedTime();
   } catch (Exception e) {
	   remittanceEstimatedTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

 <% 
  java.sql.Timestamp remittanceTime = null;
  try {
	  remittanceTime = remittanceVO.getRemittanceTime();
   } catch (Exception e) {
	   remittanceTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Timestamp settlementTime = null;
  try {
	  settlementTime = remittanceVO.getSettlementTime();
   } catch (Exception e) {
	   settlementTime = new java.sql.Timestamp(System.currentTimeMillis());
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
		   value: '<%=remittanceEstimatedTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
      $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=remittanceTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=settlementTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        }); 
        
   
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