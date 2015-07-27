<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tw.core.entity.Classinfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tw.core.entity.Employee" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/20/15
  Time: 8:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>私教预约</title>
  <link rel="stylesheet" href="<c:url value="/lib/css/iframe.css"/>" type="text/css"/>

</head>
<body>


<form method="post" action='/web/home/private/selecttime'>
  <table>
    <tr>
      <td>时间:</td>
      <td><input name="time" type="date"/></td>
      <td><input name="submit" type="submit" value="查询" /></td>
    </tr>
  </table>
</form>
<form method="post" action='/web/home/private/selectcoach'>
  <table>
    <tr>
      <td>教练:</td>
      <td>
        <select name="coach">
          <%
            List<Employee> coach = (List<Employee>) request.getAttribute("coach");
          %>
          <%
            for (Employee c : coach) {
          %>
          <option><%=c.getName()%></option>
          <%
            }
          %>
        </select>
      </td>

      <td><input name="submit" type="submit" value="查询" /></td>
    </tr>
  </table>
</form>

<form method="get" action='/web/home/private' >
  <table id="privatetable"  >
    <tr>
      <td id="ClassId" style="display: none">id</td>
      <td style="background-color: #cccccc">时间</td>
      <td style="background-color: #cccccc">教练</td>
    </tr>
    <%
      List<Classinfo> priclss = (List<Classinfo>) request.getAttribute("clsList");
    %>
    <%
      for (Classinfo pricls : priclss) {
    %>
    <tr>

      <td><%=pricls.getTime()%></td>
      <td><%=pricls.getCoach()%></td>

    </tr>
    <%
      }
    %>
  </table>
</form>


<form method="post" action='/web/home/private/date'>
  <table>
    <tr>
      <td>时间:</td>
      <td><input name="datetime" type="date"/></td>
      <td>教练:</td>
      <td>
        <select name="datecoach">
          <%
            for (Employee c : coach) {
          %>
          <option><%=c.getName()%></option>
          <%
            }
          %>
        </select>
      </td>
      <td><input name="submit" type="submit" value="预约" /></td>
    </tr>
  </table>
</form>

</body>
</html>
