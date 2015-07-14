<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/10/15
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tw.core.entity.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <title></title>
</head>
<script type="text/javascript" src="lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

//  $(document).ready(function(){
//    $.get("/web/dbrs",function(data,status){
//      var users=data;
//      var us=users.split(",");
//      //     $("#test_span").text(data);
//      for(var i=0;i<us.length-1;i++)
//      {
//        $("#usrtable").append("<tr>"+
//                "<td class='username'>" + us[i] + "</td>" +
//                "</tr>");
//      }
//
//    });
//  });



</script>
<body>

<form method="post" action='/web/usertable' method='post'>
  <table style="margin: auto">
    <tr>
      <td>姓名:</td>
      <td><input name="username" type="text"/></td>
      <td><input name="submit" type="submit" value="添加" /></td>

    </tr>
  </table>
</form>
<%
  List<User> users = (List<User>) request.getAttribute("userList");
%>
<form method="post" action='/web/usertable/del' method='post'>
  <table style="margin: auto">
    <tr>
      <td>姓名:</td>
      <td><input name="username" type="text"/></td>
      <td><input name="submit" type="submit" value="删除" /></td>
    </tr>
  </table>
</form>

<%--<form method="get" action='/web/usertable' method='get'>--%>
<table id="usrtable" style="border: groove;margin: auto"  >
  <tr>
    <td id="listUserId" style="display: none">id</td>
    <td style="background-color: #cccccc">姓名</td>
  </tr>
  <%--<tr>--%>
  <%--<td><%=userCrs.getString("username")%></td>--%>
  <%--<td>[<a href="UserHandleServlet?id=<%=userCrs.getInt("id")%>&handle=delete">删除</a>]</td>--%>
  <%--</tr>--%>
  <%
    for (User user : users) {
  %>
  <tr>
    <td><%=user.getUsername()%></td>
  </tr>
  <%
    }
  %>
</table>
<%--</form>--%>

</body>
</html>
