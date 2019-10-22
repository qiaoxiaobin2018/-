<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/21
  Time: 14:51
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
    <title>主页</title>
    <base href="<%=basePath%>">
</head>
<frameset rows="20%,*">
    <frame src="<c:url value="/top.jsp"/>" name="top"/>
    <frame src="<c:url value="/welcome.jsp"/>" name="main"/>
</frameset>
</html>
