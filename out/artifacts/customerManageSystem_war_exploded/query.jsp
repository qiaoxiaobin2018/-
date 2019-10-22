<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/21
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<html>
<head>
    <title>高级搜索</title>
    <base href="<%=basePath%>">
</head>
<body style="text-align: center;">
<h3 align="center">高级搜索</h3>
<form action="<c:url value="/customerServlet"/> " method="post">
    <%--    向servlet传递一个名为method的参数，其值表示要调用servlet的哪一个方法--%>
    <input type="hidden" name="method" value="search"/>
        <table align="center">
        <tr>
            <td width="100px">客户名称</td>
            <td width="40%">
                <input type="text" name="cname"/>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户性别</td>
            <td>
                <input type="radio" name="gender" value="男" id="male">
                <label for="male">男</label>
                <input type="radio" name="gender" value="女" id="falmale">
                <label for="falmale">女</label>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户电话</td>
            <td>
                <input type="text" name="cellphone" id="cellphone"/>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户邮箱</td>
            <td>
                <input type="text" name="email" id="email"/>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="搜索">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
