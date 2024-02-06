<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sellerLv.entity.*"%>
<%@ page import="com.sellerLv.service.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<html>
<head>
<title>所有員工資料 - listAllSeller.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllSeller.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
<th>賣家等級ID</th>
<th>等級名稱</th>
<th>平台佣金</th>
<th>廣告允許類型</th>
<th>匯出金流</th>
<th>運費補助</th>
<th>每月退貨補助</th>
<th>顯示優先順序</th>
<th>上架數量</th>
	</tr>
	<c:forEach var="sellerLvVO" items="${list}">
		<tr>
		<td>${sellerLvVO.sellerLvId}</td>
                <td>${sellerLvVO.lvName}</td>
                <td>${sellerLvVO.platformCommission}</td>
                <td>${sellerLvVO.adAllowType}</td>
                <td>${sellerLvVO.isExportGoldflow ? 'Yes' : 'No'}</td>
                <td>${sellerLvVO.freightSub}</td>
                <td>${sellerLvVO.returnSubPerMonth}</td>
                <td>${sellerLvVO.isShowPriority ? 'Yes' : 'No'}</td>
                <td>${sellerLvVO.shelvesNumber}</td>
			<td>
			  <form method="post" action="<%=request.getContextPath()%>/sellerLv/sellerLv.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="sellerLvId"  value="${sellerLvVO.sellerLvId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </form>
			</td>
			<td>
			  <form method="post" action="<%=request.getContextPath()%>/sellerLv/sellerLv.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="sellerLvId"  value="${sellerLvVO.sellerLvId}">
			     <input type="hidden" name="action" value="delete">
			  </form>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>