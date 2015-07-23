<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/10/15
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.*" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="./lib/css/home.css" type="text/css"/>
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
<div style="background-image: url('./lib/image/back.jpg')">
<div>
    <table style="margin: auto">
        <tr>
            <h1>健身房管理系统</h1>
        </tr>
    </table>

    <table style="margin: auto">
        <ul>
            <li><a href="home/regist_emp" target="link">雇员登记</a></li>
            <li><a href="home/class" target="link">课程信息</a></li>
            <li><a href="home/private" target="link">私教预约查询</a></li>
            <li><a href="home/customer" target="link">顾客信息</a></li>
            <li><a href="home/user" target="link">用户管理</a></li>
        </ul>
    </table>
    <form method="post" action='/web/home/user/quit'>
        <table style="float: right;margin-right: 40">
            <tr>
                <td><input name="submit" type="submit" value="退出登录"/></td>
            </tr>
        </table>
    </form>
</div>
<div>
    <iframe name="link" src="home/regist_emp" ></iframe>
</div>
</div>

<%--<form method="post" action='/web/' method='post'>--%>


<%--</form>--%>


<%
    String Login = (String) request.getSession().getAttribute("Login");

    if (Login != null && Login.equals("OK")) {
        //request.getSession().invalidate();
    } else {
        Cookie[] cookieid = request.getCookies();
        String url = "/home";
        for (int i = 0; i < cookieid.length; i++) {
            if (cookieid[i].getName().equals("url_jump")) {
                cookieid[i].setValue(url);
            } else {
                Cookie cookie_url = new Cookie("url_jump", url);
                cookie_url.setPath("/");
                response.addCookie(cookie_url);
            }
        }

        response.setHeader("Refresh", "0;URL=/web");
    }
%>



<%--</form>--%>

</body>
</html>
