<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/15/15
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%String Login = (String)request.getSession().getAttribute("Login");

  if (Login != null && Login.equals("OK"))
  {
    //request.getSession().invalidate();
  }
  else
  {
    System.out.print("w");
    String url="/regist";
    Cookie cookie_url = new Cookie("url_jumpregist", url);
    cookie_url.setPath("/");
    response.addCookie(cookie_url);
    response.setHeader("Refresh","0;URL=/web");
  }
%>
<form method="post" action='/web/register' method='post'>
  <table style="margin: auto">
    <tr>
      <td>姓名:</td>
      <td><input name="username" type="text"/></td>
    <td>密码：:</td>
    <td><input name="password" type="password"/></td>
      <td><input name="submit" type="submit" value="注册" /></td>

    </tr>
  </table>
</form>

<form method="post" action='/web/regist/quit' method='post'>
  <table style="float: right">
    <tr>
      <td><a href="usertable" >返回</a></td>
      <td><input name="submit" type="submit" value="退出登录" /></td>

    </tr>
  </table>
</form>
</body>
</html>
