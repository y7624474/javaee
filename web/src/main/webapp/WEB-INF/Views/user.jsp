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
</head>
<body>
<%--<form method="post" action='/web/home/user/del'>--%>
  <%--<table style="margin: auto">--%>
    <%--<tr>--%>
      <%--<td>姓名:</td>--%>
      <%--<td><input name="username" type="text"/></td>--%>
      <%--<td><input name="submit" type="submit" value="删除" /></td>--%>
    <%--</tr>--%>
  <%--</table>--%>
<%--</form>--%>

<%--<form method="get" action='/web/usertable' method='get'>--%>
<table id="usrtable" style="border: groove;margin: auto"  >
  <tr>
    <td id="listUserId" style="display: none">id</td>
    <td style="background-color: #cccccc">用户名</td>
    <td style="background-color: #cccccc">注册邮箱</td>

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

    <td><%=user.getUsername()%></td>
    <td><%=user.getEmail()%></td>
    <td><a href="/web/home/user/<%=user.getIdUser()%>">删除</a>></td>

  <%--<td><%=user.getPassword()%></td>--%>
  </tr>
  <%
    }
  %>
</table>

</br>


<form method="post" action='/web/home/user/quit'>
  <table style="float: right">
    <tr>
      <td><a href="/web/home" >返回</a></td>
      <td><input name="submit" type="submit" value="退出登录" /></td>
    </tr>
  </table>
</form>
</body>
</html>
