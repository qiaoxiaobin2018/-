<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/21
  Time: 15:11
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
    <title>msg</title>
    <base href="<%=basePath%>">
</head>
<body>
    <h3 style="color: coral" align="center">${msg}</h3>
</body>
</html>
