<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM PetDraw: Home</title>

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
   <tr><td><h3>IBM PetDraw: Home</h3><h4>( MVC )</h4></td></tr>   
</table>

   <p>This is the Home page for IBM PetDraw: Home</p>
   
   <h3>寵物抽卡查詢:</h3>
   
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
  <li><a href='listAllPetDraw.jsp'>List</a> all PetDraw.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="petDraw.do" >
        <b>輸入會員編號:</b>
        <input type="text" name="memberId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="petDrawSvc" scope="page" class="com.petdraw.service.PetDrawService" />
  <li>
     <FORM METHOD="post" ACTION="petDraw.do" >
       <b>選擇會員編號:</b>
       <select size="1" name=memberId>
<%--          <c:forEach var="petDrawVO" items="${petDrawSvc.all}" >  --%>
<%-- 			<option value="${petDrawVO.memberId}">${petDrawVO.memberId} --%>
<%-- 			</c:forEach>    --%>
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  </ul>
  
  <h3>寵物抽卡管理</h3>

<ul>
  <li><a href='addPetDraw.jsp'>Add</a> a new PetDraw.</li>
</ul>

</body>
</html>