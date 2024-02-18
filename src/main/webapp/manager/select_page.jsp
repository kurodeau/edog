<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM ManagerUser: Home</title>

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
   <tr><td><h3>IBM ManagerUser: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM ManagerUser: Home</p>

<h3>後臺管理者資料查詢:</h3>
	
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
  <li><a href='listAllManagerUser.jsp'>List</a> all ManagerUsers.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="manager.do" > <!-- 這個.do是甚麼意思? -->
        <b>輸入後臺管理者編號 (如1):</b>
        <input type="text" name="managerId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="managerUserSvc" scope="page" class="com.manager.service.ManagerUserService" />
   
  <li>
     <FORM METHOD="post" ACTION="manager.do" >
       <b>選擇後臺管理者編號:</b>
       <select size="1" name="managerId">
         <c:forEach var="managerUserVO" items="${managerUserSvc.all}" >
          <option value="${managerUserVO.managerId}">${managerUserVO.managerId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="manager.do" >
       <b>選擇後臺管理者姓名:</b>
       <select size="1" name="managerId">
         <c:forEach var="managerUserVO" items="${managerUserSvc.all}" > 
          <option value="${managerUserVO.managerId}">${managerUserVO.managername}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>後臺管理者管理</h3>

<ul>
  <li><a href='addManagerUser.jsp'>新增</a> 一個新的後臺管理者.</li>
</ul>

</body>
</html>