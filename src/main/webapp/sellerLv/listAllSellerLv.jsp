<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sellerLv.entity.*"%>
<%@ page import="com.sellerLv.service.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>
<html>
<head>
<title>�Ҧ����u��� - listAllSeller.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllSeller.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
<th>��a����ID</th>
<th>���ŦW��</th>
<th>���x����</th>
<th>�s�i���\����</th>
<th>�ץX���y</th>
<th>�B�O�ɧU</th>
<th>�C��h�f�ɧU</th>
<th>����u������</th>
<th>�W�[�ƶq</th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="sellerLvId"  value="${sellerLvVO.sellerLvId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </form>
			</td>
			<td>
			  <form method="post" action="<%=request.getContextPath()%>/sellerLv/sellerLv.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="sellerLvId"  value="${sellerLvVO.sellerLvId}">
			     <input type="hidden" name="action" value="delete">
			  </form>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>