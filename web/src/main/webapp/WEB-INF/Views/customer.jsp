<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tw.core.entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tw.core.entity.Employee" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/19/15
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/lib/css/iframe.css"/>" type="text/css"/>

</head>
<body>
<form method="get" action='/web/home/customer'>
    <table id="custable">
        <tr>
            <td id="CusId" style="display: none">id</td>
            <td style="background-color: #cccccc">顾客</td>
            <td style="background-color: #cccccc">私教</td>
        </tr>
        <%
            List<Customer> cuss = (List<Customer>) request.getAttribute("cusList");
        %>
        <%
            for (Customer cus : cuss) {
        %>
        <tr>

            <td><%=cus.getCustomer()%>
            </td>
            <td><%=cus.getCoach()%>
            </td>
            <td><a href="/web/home/customer/<%=cus.getIdCustomer()%>" onclick="javascript:return confirm('您确定要删除吗？');">删除</a>>
            </td>

            <%--<td><%=user.getPassword()%></td>--%>
        </tr>
        <%
            }
        %>
    </table>
</form>

<form method="post" action='/web/home/customer/add'>
    <table>
        <tr>
            <td>顾客:</td>
            <td><input name="customer" type="text"/></td>
        </tr>
        <tr>
            <td>私教:</td>
            <td><select name="coach">
                <%
                    List<Employee> coach = (List<Employee>) request.getAttribute("coach");
                %>
                <%
                    for (Employee c : coach) {
                %>
                <option><%=c.getName()%>
                </option>
                <%
                    }
                %>
            </select>
            </td>
            <td><input name="submit" type="submit" value="添加"/></td>
        </tr>
    </table>
</form>


</body>
</html>
