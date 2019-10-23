<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/21
  Time: 14:56
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
<%--    确定新页面的显示位置--%>
    <base target="main"/>
    <title>TOP</title>
    <base href="<%=basePath%>">
</head>
<body style="text-align: center;">
    <h2>客户关系管理系统</h2>
    <a href="<c:url value="/add.jsp"/>">添加客户</a> |
    <a href="<c:url value="/customerServlet?method=newFindAll"/>">查询客户</a> |
    <a href="<c:url value="/query.jsp"/>">高级搜索</a>
</body>
</html>
