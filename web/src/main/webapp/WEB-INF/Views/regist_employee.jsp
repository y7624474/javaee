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

  <link rel="stylesheet" href="<c:url value="/lib/css/iframe.css"/>" type="text/css"/>

</head>
<body>
<div>
<form method="get" action='/web/home' method='get'>
<table id="emptable" >
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
    <td><%=emp.getIdEmployee()%></td>

    <td><a href="/web/home/regist_emp/<%=emp.getIdEmployee()%>" onclick="javascript:return confirm('您确定要删除吗？');">删除</a>></td>

  </tr>
  <%
    }
  %>
</table>
</form>


<form method="post" action='/web/home/regist_emp/add'>
  <table>
    <tr>
      <td>姓名:</td>
      <td><input name="name" type="text"/></td>
      </tr>
    <tr>
      <td>职位:</td>
      <td>
        <select name="role">
        <option>HR</option>
        <option>OP</option>
        <option>coach</option>
      </select>
      </td>
      </tr>
    <tr>
      <td>工号:</td>
      <td><input name="num" type="text"/></td>
      <td><input name="submit" type="submit" value="添加" /></td>
    </tr>
  </table>
</form>
</div>


</body>
</html>
