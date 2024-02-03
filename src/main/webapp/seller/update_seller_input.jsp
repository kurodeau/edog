<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.seller.model.*"%>

<%
//��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
%>
--EL--<%=sellerVO == null ? "NULL" : sellerVO.getSellerId()%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��ƭק� - update_seller_input.jsp</title>

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
				<h3>���u��ƭק� - update_seller_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="seller.do" name="form1">
		<table>
			<tr>
				<td>��aId<font color=red><b>*</b></font></td>
				<td><%=sellerVO.getSellerId()%></td>
			</tr>
			<tr>
				<td>��aLv<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerLvId"
					value="<%=sellerVO.getSellerLvId()%>" size="45" required></td>
			</tr>
			<tr>
				<td>��aEmail<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerEmail"
					value="<%=sellerVO.getSellerEmail()%>" size="45" required></td>
			</tr>
			<tr>
				<td>��a���q�W��<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerCompany"
					value="<%=sellerVO.getSellerCompany()%>" size="45" required></td>
			</tr>
			<tr>
				<td>��a�Τ@�s��</td>
				<td><input type="text" name="sellerTaxId"
					value="<%=sellerVO.getSellerTaxId()%>" size="45"></td>
			</tr>
			<tr>
				<td>��a�ꥻ�B</td>
				<td><input type="text" name="sellerCapital"
					value="<%=sellerVO.getSellerCapital()%>" size="45"></td>
			</tr>
			<tr>
				<td>��a�p���H<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerContact"
					value="<%=sellerVO.getSellerContact()%>" size="45" required></td>
			</tr>
			<tr>
				<td>��a���q�q��<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerCompanyPhone"
					value="<%=sellerVO.getSellerCompanyPhone()%>" size="45" required></td>
			</tr>
			<tr>
				<!-- Add input fields for the missing properties -->
				<td>��a���q����</td>
				<td><input type="text" name="sellerCompanyExtension"
					value="<%=sellerVO.getSellerCompanyExtension()%>" size="45"></td>
			</tr>
			<tr>
				<td>��a���</td>
				<td><input type="text" name="sellerMobile"
					value="<%=sellerVO.getSellerMobile()%>" size="45"></td>
			</tr>
			<tr>
				<td>��a�a�}</td>
				<td><input type="text" name="sellerAddress"
					value="<%=sellerVO.getSellerAddress()%>" size="45"></td>
			</tr>
			<tr>
				<td>��a�K�X<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerPassword"
					value="<%=sellerVO.getSellerPassword()%>" size="45" required></td>
			</tr>
			<tr>
				<td>��a�Ȧ�b��<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerBankAccount"
					value="<%=sellerVO.getSellerBankAccount()%>" size="45" required></td>
			</tr>
			<tr>
				<td>��a�Ȧ�N�X<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerBankCode"
					value="<%=sellerVO.getSellerBankCode()%>" size="45" required></td>
			</tr>
			<tr>
				<td>��a�Ȧ�b��<font color="red"><b>*</b></font></td>
				<td><input type="text" name="sellerBankAccountNumber"
					value="<%=sellerVO.getSellerBankAccountNumber()%>" size="45"
					required></td>
			</tr>
			<tr>
				<td>��a�Ыخɶ�</td>
				<td><input type="date" name="sellerCreateTime"
					value="<%=sellerVO.getSellerCreateTime()%>" size="45"></td>
			</tr>
			<tr>
				<td>�b���O�_�q�L</td>
				<td><input type="checkbox" name="isConfirm"
					<%=(sellerVO.getIsConfirm() ? "checked" : "")%>></td>
			</tr>


			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(sellerVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="sellerId" value="<%=sellerVO.getSellerId()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=sellerVO.getSellerCreateTime()%>
	', // value:   new Date(),
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