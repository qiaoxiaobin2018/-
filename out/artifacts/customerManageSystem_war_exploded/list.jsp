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
    <title>查询所有客户</title>
    <base href="<%=basePath%>">
</head>
<body>
<table border="1" bgcolor="#faebd7" align="center" width="60%">
        <tr bgcolor="#ff7f50">
            <td>姓名</td>
            <td>性别</td>
            <td>出生年月</td>
            <td>电话</td>
            <td>邮件</td>
            <td>描述</td>
            <td>操作</td>
        </tr>
<%--    }"之间不能有空格--%>
    <c:forEach items="${customerList}" var="cstm">
        <tr>
            <td>${cstm.cname}</td>
            <td>${cstm.gender}</td>
            <td>${cstm.birthday}</td>
            <td>${cstm.cellphone}</td>
            <td>${cstm.email}</td>
            <td>${cstm.description}</td>
            <td>
                <a href="<c:url value="/customerServlet?method=preEdit&cid=${cstm.cid}"/> ">编辑</a>
                <a href="<c:url value="/customerServlet?method=delete&cid=${cstm.cid}"/> ">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
