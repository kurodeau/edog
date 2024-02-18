<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Ad: Home</title>

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
   <tr><td><h3>IBM Ad: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Ad: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
<%-- 	value : ${errorMsgs}= Servlet(key:"errorMsgs"  value:errorMsgs)�s�J�ܼ�"$message"
		��${message}�L�X --%>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllAd.jsp'>List</a> all Ads.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/edog/ad/ad.do" >
        <b>��J�s�i�s�� (�p1):</b>
        <input type="text" name="adId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" />
   
  <li>
     <FORM METHOD="post" ACTION="/edog/ad/ad.do" >
       <b>��ܼs�i�s��:</b>
       <select size="1" name="adId">
         <c:forEach var="adVO" items="${adSvc.all}" > 
          <option value="${adVO.adId}">${adVO.adId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/edog/ad/ad.do" >
       <b>��ܽ�a�s��:</b>
       <select size="1" name="adId">
         <c:forEach var="adVO" items="${adSvc.all}" > 
          <option value="${adVO.adId}">${adVO.sellerId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�s�i�޲z</h3>

<ul>
  <li><a href='addAd.jsp'>Add</a> a new Ad.</li>
</ul>

</body>
</html>