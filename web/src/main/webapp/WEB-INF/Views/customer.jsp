<%@ page import="com.tw.core.entity.Customer" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/19/15
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" href="./lib/css/home.css" type="text/css"/>

</head>
<body>
<form method="get" action='/web/home/customer' >
  <table id="custable" style="border: groove; margin: auto;width: 700;font-size: 25;"  >
    <tr>
      <td id="CusId" style="display: none">id</td>
      <td style="background-color: #cccccc">顾客</td>
      <td style="background-color: #cccccc">私教</td>
    </tr>
    <%
      List<Customer> cuss = (List<Customer>) request.getAttribute("cusList");
    %>
    <%
      for (Customer cus : cuss) {
    %>
    <tr>

      <td><%=cus.getCustomer()%></td>
      <td><%=cus.getCoach()%></td>
      <td><a href="/web/home/customer/<%=cus.getIdCustomer()%>">删除</a>></td>

      <%--<td><%=user.getPassword()%></td>--%>
    </tr>
    <%
      }
    %>
  </table>
</form>

<form method="post" action='/web/home/customer/add'>
  <table style="margin: auto">
    <tr>
      <td>顾客:</td>
      <td><input name="customer" type="text"/></td>
    </tr>
    <tr>
      <td>私教:</td>
      <td><input name="coach" type="text"/></td>

      <td><input name="submit" type="submit" value="添加" /></td>
    </tr>
  </table>
</form>


</body>
</html>
