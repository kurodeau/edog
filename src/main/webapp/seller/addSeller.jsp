<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.seller.entity.*"%>

<%
//��com.seller.controller.EmpServlet.java��238��s�Jreq��sellerVO���� (������J�榡�����~�ɪ�sellerVO����)
   SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
%>
<br>
<%= sellerVO == null ? "" : sellerVO.getSellerId() %>

<br>
<br>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��Ʒs�W - addSeller.jsp</title>

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
	<tr><td>
		 <h3>���u��Ʒs�W - addSeller.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="seller.do" name="form1">
<table>
	
	



<tr>
    <td>�q�l�l��:</td>
    <td><input type="TEXT" name="sellerEmail" value="<%= (sellerVO==null)? "john.doe@example.com" : sellerVO.getSellerEmail()%>" size="45"/></td>
</tr>
<tr>
    <td>���q�W��:</td>
    <td><input type="TEXT" name="sellerCompany" value="<%= (sellerVO==null)? "ASBYUS COMPANY" : sellerVO.getSellerCompany()%>" size="45"/></td>
</tr>
<tr>
    <td>�Τ@�s��:</td>
    <td><input type="TEXT" name="sellerTaxId" value="<%= (sellerVO==null)? "12345678" : sellerVO.getSellerTaxId()%>" size="45"/></td>
</tr>
<tr>
    <td>�ꥻ�B:</td>
    <td><input type="TEXT" name="sellerCapital" value="<%= (sellerVO==null)? "1000000" : sellerVO.getSellerCapital()%>" size="45"/></td>
</tr>
<tr>
    <td>���q�t�d�H:</td>
    <td><input type="TEXT" name="sellerContact" value="<%= (sellerVO==null)? "MEASSAAS" : sellerVO.getSellerContact()%>" size="45"/></td>
</tr>
<tr>
    <td>���q�q��:</td>
    <td><input type="TEXT" name="sellerCompanyPhone" value="<%= (sellerVO==null)? "22143086" : sellerVO.getSellerCompanyPhone()%>" size="45"/></td>
</tr>
<tr>
    <td>���q����:</td>
    <td><input type="TEXT" name="sellerCompanyExtension" value="<%= (sellerVO==null)? "1234" : sellerVO.getSellerCompanyExtension()%>" size="45"/></td>
</tr>
<tr>
    <td>������X:</td>
    <td><input type="TEXT" name="sellerMobile" value="<%= (sellerVO==null)? "0999319004" : sellerVO.getSellerMobile()%>" size="45"/></td>
</tr>
<tr>
    <td>���q�a�}:</td>
    <td><input type="TEXT" name="sellerAddress" value="<%= (sellerVO==null)? "123 Main St, City" : sellerVO.getSellerAddress()%>" size="45"/></td>
</tr>
<tr>
    <td>�K�X:</td>
    <td><input type="TEXT" name="sellerPassword" value="<%= (sellerVO==null)? "asdsadS1" : sellerVO.getSellerPassword()%>" size="45"/></td>
</tr>
<tr>
    <td>�Ȧ�b��:</td>
    <td><input type="TEXT" name="sellerBankAccount" value="<%= (sellerVO==null)? "987654321" : sellerVO.getSellerBankAccount()%>" size="45"/></td>
</tr>
<tr>
    <td>�Ȧ�N�X:</td>
    <td><input type="TEXT" name="sellerBankCode" value="<%= (sellerVO==null)? "5678" : sellerVO.getSellerBankCode()%>" size="45"/></td>
</tr>
<tr>
    <td>�Ȧ�b��:</td>
    <td><input type="TEXT" name="sellerBankAccountNumber" value="<%= (sellerVO==null)? "654321987" : sellerVO.getSellerBankAccountNumber()%>" size="45"/></td>
</tr>
<!-- <tr> -->
<!--     <td>�إ߮ɶ�:</td> -->
<%--     <td><input type="datetime-local" name="sellerCreateTime" value="<%= (sellerVO==null)? "" : sellerVO.getSellerCreateTime()%>" size="45"/></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!--     <td>�T�{���A:</td> -->
<%--     <td><input type="checkbox" name="isConfirm" value="<%= (sellerVO!=null && sellerVO.getIsConfirm())? "checked" : "" %>" /></td> --%>
<!-- </tr> -->


	
	
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(sellerVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date sellerCreateTime = null;
  try {
	  sellerCreateTime = sellerVO.getSellerCreateTime();
   } catch (Exception e) {
	   sellerCreateTime = new java.sql.Date(System.currentTimeMillis());
	 /* 2147483647-24*24*60*60*1000 < 0 �A�p�G�ɶ��W�L25�ѡA��Int�|�X�{���D */
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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=sellerCreateTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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