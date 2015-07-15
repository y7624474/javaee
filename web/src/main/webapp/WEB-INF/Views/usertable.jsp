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
<%@ page import="javax.servlet.http.*" %>
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

  <%String Login = (String)request.getSession().getAttribute("Login");

    if (Login != null && Login.equals("OK"))
    {
      //request.getSession().invalidate();
    }
    else
    {
      System.out.print("w");
      String url="/usertable";
      Cookie cookie_url = new Cookie("url_jump", url);
      cookie_url.setPath("/");
      response.addCookie(cookie_url);
      response.setHeader("Refresh","0;URL=/web");
    }
  %>
  <%
    List<User> users = (List<User>) request.getAttribute("userList");
  %>
  <%
    for (User user : users) {
  %>
  <tr>
    <td><%=user.getUsername()%></td>
    <%--<td><%=user.getPassword()%></td>--%>
  </tr>
  <%
    }
  %>
</table>

</br>
<form method="post" action='/web/regist' method='post'>
  <table style="margin: auto">
    <tr>
      <td><input name="submit" type="submit" value="注册新用户" /></td>

    </tr>
  </table>
</form>

<form method="post" action='/web/usertable/quit' method='post'>
  <table style="float: right">
    <tr>
      <td><input name="submit" type="submit" value="退出登录" /></td>
    </tr>
  </table>
</form>
<%--</form>--%>

</body>
</html>
