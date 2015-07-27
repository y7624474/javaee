<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<c:url value="/lib/css/iframe.css"/>" type="text/css"/>

</head>
<body>
<div style="height: 100%; background-image: url('./lib/image/back.jpg')">
    <h1 style="text-align: center">注册新用户</h1>
    <form method="post" action='/web/regist' method='post'>
        <table style="border: groove; margin: auto;width: 600;font-size: 25;">
            <tr>
                <td>工号:</td>
                <td><input name="num" type="text"/></td>
            </tr>
            <tr>
            <tr>
                <td>用户名:</td>
                <td><input name="username" type="text"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input name="password" type="password"/></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input name="email" type="text"/></td>
                <td><input name="submit" type="submit" value="注册"/></td>
            </tr>
        </table>
    </form>
    <table style="float: right">
        <td><a href="/web">返回</a></td>
    </table>
</div>
</body>
</html>
