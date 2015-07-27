<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <link rel="stylesheet" href="<c:url value="/lib/css/iframe.css"/>" type="text/css"/>
  <script src="<c:url value="/lib/js/jquery-1.11.1.min.js" />"></script>
  <%--<script type="text/javascript" src="lib/js/jquery-1.11.1.min.js"></script>--%>
  <script type="text/javascript">


    $(document).ready(function(){
      $('#updateTable').hide();
       $(".updateBtn").click(function(){
         var name=$(this).data('name');
         $("#updatename").val(name);
         var id=$(this).data('id');
         $("#updateid").val(id);
          $("#updateTable").show();
        });
       $("#reupdate").click(function(){
         $("#updateTable").hide();
        });
    });
  </script>

</head>

<body>


<div >
<form method="get" action='/web/home/class' >
  <table id="classtable">
    <tr style="background-color: #cccccc">
      <td id="ClassId" style="display: none">id</td>
      <td >课程</td>
      
      <td >时间</td>
      <td >教练</td>
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
      <td><a href="/web/home/class/<%=cls.getIdClass()%>" onclick="javascript:return confirm('您确定要删除吗？');">删除</a></td>
      <td><input class="updateBtn" type="button" value="修改" data-id="<%=cls.getIdClass()%>" data-name="<%=cls.getClassname()%>"></td>
    </tr>
    <%
      }
    %>
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

<form  method="post" action='/web/home/class/update'>>
  <table id="updateTable">
    <tr>
      <td>课程:</td>
      <td style="display: none">
        <input id="updateid" name="updateid" type="text"/></td>

      <td><input id="updatename" name="updateclassname" type="text"/></td>
    </tr>
    <tr>
      <td>时间:</td>
      <td><input id="updatetime" name="updatetime" type="date"/></td>

    </tr>
    <tr>
      <td>教练:</td>
      <td>
        <select name="updatecoach">
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
      <td><button id="reupdate" >修改</button></td>
    </tr>
  </table>
</form>

</div>
</body>
</html>
