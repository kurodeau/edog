<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Remittance: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Remittance: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Remittance: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



<ul>

<!-- 顯示所有Remittance -->
  <li><a href='listAllRemittance.jsp'>List</a> all Remittance.  <br><br></li>
  
  <!-- 輸入匯款編號 -->
  <li>
    <FORM METHOD="post" ACTION="remittance.do" >
        <b>輸入匯款編號 (如1):</b>
        <input type="text" name="remittanceId" required = "required">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="remittanceSvc" scope="page" class="com.remittance.model.RemittanceService" />
   
   
  <!-- 選擇匯款編號 --> 
  <li>
     <FORM METHOD="post" ACTION="remittance.do" >
       <b>選擇匯款編號:</b>
       <select size="1" name="remittanceId">
         <c:forEach var="remittanceVO" items="${remittanceSvc.all}" > 
          <option value="${remittanceVO.remittanceId}">${remittanceVO.remittanceId} <!-- value 對照 innerHTML -->
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li> 
  
  
  
</ul>


<!-- 員工管理 -->

<h3>匯款明細管理</h3>

<ul>
  <li><a href='addRemittance.jsp'>Add</a> a new Remittance.</li>
</ul>

</body>
</html>