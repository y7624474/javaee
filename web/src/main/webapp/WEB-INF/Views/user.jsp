<%@ page import="com.tw.core.entity.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/19/15
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" href="./lib/css/home.css" type="text/css"/>

</head>
<body>

<table id="usrtable" style="border: groove; margin: auto;width: 700;font-size: 25;" >
  <tr>
    <td id="listUserId" style="display: none">id</td>
    <td style="background-color: #cccccc">工号</td>

    <td style="background-color: #cccccc">用户名</td>
    <td style="background-color: #cccccc">注册邮箱</td>

  </tr>

  <%String Login = (String)request.getSession().getAttribute("Login");

    if (Login != null && Login.equals("OK"))
    {
      //request.getSession().invalidate();
    }
    else
    {
      Cookie[] cookieid=request.getCookies();
      String url="/home/user";
      for(int i=0;i<cookieid.length;i++) {
        if (cookieid[i].getName().equals("url_jump"))
        {
          cookieid[i].setValue(url);
        }
        else
        {
          Cookie cookie_url = new Cookie("url_jump", url);
          cookie_url.setPath("/");
          response.addCookie(cookie_url);
        }
      }

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
    <td><%=user.getEmp().getIdEmployee()%></td>

    <td><%=user.getUsername()%></td>
    <td><%=user.getEmail()%></td>
    <td><a href="/web/home/user/<%=user.getIdUser()%>">删除</a>></td>
  </tr>
  <%
    }
  %>
</table>

</br>


</body>
</html>
