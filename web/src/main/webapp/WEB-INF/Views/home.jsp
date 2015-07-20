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





<%--<form method="post" action='/web/' method='post'>--%>
  <table style="margin: auto">
    <tr>
      <td>健身房管理系统</td>
    </tr>
    </table>

<table style="margin: auto">
    <tr style="margin-top: 30px;">
      <td><a href="home/regist_emp">雇员登记</a></td>
      <td><a href="home/class">课程信息</a></td>
      <td><a href="home/private">私教预约查询</a></td>
      <td><a href="home/customer">顾客信息</a></td>
      <td><a href="home/user">用户管理</a></td>

    <%--<td><input name="submit" type="submit" value="雇员登记" /></td>--%>
    </tr>
  </table>
<%--</form>--%>



  <%String Login = (String)request.getSession().getAttribute("Login");

    if (Login != null && Login.equals("OK"))
    {
      //request.getSession().invalidate();
    }
    else
    {
      Cookie[] cookieid=request.getCookies();
      String url="/home";
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



<form method="post" action='/web/home/user/quit'>
  <table style="float: right">
    <tr>
      <td><input name="submit" type="submit" value="退出登录" /></td>
    </tr>
  </table>
</form>
<%--</form>--%>

</body>
</html>
