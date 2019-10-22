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
    <title>welcome</title>
    <base href="<%=basePath%>">
</head>
<body style="text-align:center;">
<h2><font color="#ff7f50">欢迎!</font></h2>
</body>
</html>
