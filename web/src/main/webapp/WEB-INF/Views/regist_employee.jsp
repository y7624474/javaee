<%@ page import="com.tw.core.entity.Employee" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/16/15
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<%--<form method="post" action='/web/home/regist_emp/del'>--%>
  <%--<table style="margin: auto">--%>
    <%--<tr>--%>
      <%--<td>姓名:</td>--%>
      <%--<td><input name="id" type="text"/></td>--%>
      <%--<td><input name="submit" type="submit" value="删除" /></td>--%>
    <%--</tr>--%>
  <%--</table>--%>
<%--</form>--%>

<form method="get" action='/web/home' method='get'>
<table id="emptable" style="border: groove;margin: auto"  >
  <tr>
    <td id="listUserId" style="display: none">id</td>
    <td style="background-color: #cccccc">姓名</td>
    <td style="background-color: #cccccc">职位</td>
    <td style="background-color: #cccccc">工号</td>

  </tr>
  <%
    List<Employee> emps = (List<Employee>) request.getAttribute("empList");
  %>
  <%
    for (Employee emp : emps) {
  %>
  <tr>

    <td><%=emp.getName()%></td>
    <td><%=emp.getRole()%></td>
    <td><%=emp.getNum()%></td>

  <%--<td><a href="/web/home/regist_emp/${emp.getIdEmployee()}">删除</a>></td>--%>
    <td><a href="/web/home/regist_emp/<%=emp.getIdEmployee()%>">删除</a>></td>

  <%--<td><%=user.getPassword()%></td>--%>
  </tr>
  <%
    }
  %>
</table>
</form>


<form method="post" action='/web/home/regist_emp/add'>
  <table style="margin: auto">
    <tr>
      <td>姓名:</td>
      <td><input name="name" type="text"/></td>
      </tr>
    <tr>
      <td>职位:</td>
      <td><input name="role" type="text"/></td>
      </tr>
    <tr>
      <td>工号:</td>
      <td><input name="num" type="text"/></td>
      <td><input name="submit" type="submit" value="添加" /></td>
    </tr>
  </table>
</form>

<form method="post" action='/web/home/regist_emp/quit'>
  <table style="float: right">
    <tr>
      <td><a href="/web/home" >返回</a></td>
      <td><input name="submit" type="submit" value="退出登录" /></td>
    </tr>
  </table>
</form>

</body>
</html>
