<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.tw.core.entity.Classinfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.tw.core.entity.Employee" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/19/15
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程表</title>
  <link rel="stylesheet" href="./lib/css/iframe.css" type="text/css"/>

</head>
<body>
<script type="text/javascript">
  function update()
  {
    document.getElementById("update").style.display='block';
  }
  function reupdate()
  {
    document.getElementById("update").style.display='none';
  }
</script>
<div >
<form method="get" action='/web/home/class' >
  <table id="classtable" style="border: groove; margin: auto;width: 700;font-size: 25;">
    <%--<table> class="table table-bordered"></table>--%>
    <tr>
      <td id="ClassId" style="display: none">id</td>
      <td style="background-color: #cccccc">课程</td>
      <td style="background-color: #cccccc">时间</td>
      <td style="background-color: #cccccc">教练</td>
    </tr>
    <%
      List<Classinfo> clss = (List<Classinfo>) request.getAttribute("clsList");
    %>
    <%
      for (Classinfo cls : clss) {
    %>
    <tr>

      <td><%=cls.getClassname()%></td>
      <td><%=cls.getTime()%></td>
      <td><%=cls.getCoach()%></td>
      <td><a href="/web/home/class/<%=cls.getIdClass()%>">删除</a></td>
      <td><input type="button" value="修改" onclick="update()"></td>

      <%--<td><%=user.getPassword()%></td>--%>
    </tr>
    <%
      }
    %>
  </table>
  <table id="update" style="margin: auto; display: none">
    <tr>
      <td>课程:</td>
      <td><input name="updateclassname" type="text"/></td>
    </tr>
    <tr>
      <td>时间:</td>
      <td><input name="updatetime" type="date"/></td>

    </tr>
    <tr>
      <td>教练:</td>
      <%--<td><input name="coach" type="text"/></td>--%>
      <td>
        <select name="coach">
          <%
            List<Employee> uopdatecoach = (List<Employee>) request.getAttribute("coach");
          %>
          <%
            for (Employee c : uopdatecoach) {
          %>
          <option><%=c.getName()%></option>
          <%
            }
          %>
        </select>
      </td>

    </tr>
    <tr>
      <td><input type="button" value="修改" onclick="reupdate()"></td>
    </tr>
  </table>

</form>

<form method="post" action='/web/home/class/add'>
  <table style="margin: auto">
    <tr>
      <td>课程:</td>
      <td><input name="classname" type="text"/></td>
    </tr>
    <tr>
      <td>时间:</td>
      <td><input name="time" type="date"/></td>

    </tr>
    <tr>
      <td>教练:</td>
      <%--<td><input name="coach" type="text"/></td>--%>
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


      <td><input name="submit" type="submit" value="添加" /></td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
