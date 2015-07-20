<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/10/15
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tw.core.entity.User" %>
<%@ page import="javax.servlet.http.*" %>
<html>
<head>
    <title></title>
</head>
<script type="text/javascript" src="../../lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">


</script>
<body>
<%

%>

<form method="post" action="">
    <table id="login" style="border: groove;margin: auto"  >
        <tr>
            <td>姓名:</td>
            <td><input name="loginname" type="text"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input name="password" type="password"/></td>
            <td><input name="login" type="submit" value="登录" /></td>
        </tr>
    </table>
</form>

    <table style="margin: auto">
        <tr>
            <td><a href="regist" >注册新用户</a></td>

        </tr>
    </table>
</body>
</html>
