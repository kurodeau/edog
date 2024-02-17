<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ page
import="java.util.*"%> <%@ page import="com.coupon.entity.*"%> <%@ page
import="com.coupon.service.*"%> <%-- 此頁暫練習採用 Script 的寫法取值 --%> <%
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
//SellerServlet.java(Concroller), 存入req的couponVO物件 %>

<html>
  <head>
    <title>員工資料 - listOneSeller.jsp</title>

    <style>
      table#table-1 {
        background-color: #ccccff;
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
        width: 600px;
        background-color: white;
        margin-top: 5px;
        margin-bottom: 5px;
      }

      table,
      th,
      td {
        border: 1px solid #ccccff;
      }

      th,
      td {
        padding: 5px;
        text-align: center;
      }
    </style>
  </head>
  <body bgcolor="white">
    <h4>此頁暫練習採用 Script 的寫法取值:</h4>
    <table id="table-1">
      <tr>
        <td>
          <h3>員工資料 - listOneSeller.jsp</h3>
          <h4>
            <a href="index.jsp"
              ><img
                src="images/back1.gif"
                width="100"
                height="32"
                border="0"
              />回首頁</a
            >
          </h4>
        </td>
      </tr>
    </table>

    <table>
      <tr>
        <th>優惠券編號</th>
        <th>優惠券名稱</th>
        <th>優惠券代碼</th>
        <th>開始時間</th>
        <th>結束時間</th>
        <th>最低消費金額</th>
        <th>優惠券數量</th>
        <th>會員可使用數量</th>
        <th>優惠折扣</th>
        <th>創建時間</th>
      </tr>
      <tr>
        <td>${couponVO.couponId}</td>
        <td>${couponVO.couponName}</td>
        <td>${couponVO.couponCode}</td>
        <td>${couponVO.startTime}</td>
        <td>${couponVO.endTime}</td>
        <td>${couponVO.minSpendingAmount}</td>
        <td>${couponVO.couponQuantity}</td>
        <td>${couponVO.memberAllowQuantity}</td>
        <td>${couponVO.couponDiscount}</td>
        <td>${couponVO.couponCreateTime}</td>
      </tr>
    </table>
  </body>
</html>
