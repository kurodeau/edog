<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM ArticlePic: Home</title>

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
   <tr><td><h3>IBM ArticlePic: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM ArticlePic: Home</p>

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
  <li>
    <FORM METHOD="post" ACTION="articlePic.do" >
        <b>所有圖片(傳遞回CONTROLLER，有做NULL設定)</b>
        <input type="hidden" name="action" value="get_All">
        <input type="submit" value="送出">
    </FORM>
  </li>


  <li><a href='listAllArticlePic.jsp'>(路徑)所有圖片(不傳遞回CONTROLLER，使用路徑)</a> all ArticlePics.  <br><br></li>
    <li><a href='listAllArticlePic3.jsp'>(老師的)所有圖片(不傳遞回CONTROLLER，直接在JSP頁面轉換)</a> all ArticlePics.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="articlePic.do" >
        <b>輸入文章圖片編號 (如7001):</b>
        <input type="text" name="articlePicId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="articlePicSvc" scope="page" class="com.articlePic.model.ArticlePicService" />
   
  <li>
     <FORM METHOD="post" ACTION="articlePic.do" >
       <b>選擇文章圖片編號:</b>
       <select size="1" name="articlePicId">
         <c:forEach var="articlePicVO" items="${articlePicSvc.all}" > 
          <option value="${articlePicVO.articlePicId}">${articlePicVO.articlePicId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="articlePic.do" >
       <b>選擇文章編號:</b>
       <select size="1" name="articlePicId">
         <c:forEach var="articlePicVO" items="${articlePicSvc.all}" > 
          <option value="${articlePicVO.articlePicId}">${articlePicVO.articleId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addArticlePic.jsp'>Add</a> a new ArticlePic.</li>
</ul>

</body>
</html>