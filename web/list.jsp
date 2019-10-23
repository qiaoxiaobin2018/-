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
<h3 align="center">客户列表</h3>
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
    <c:forEach items="${pageBean.beanList}" var="cstm">
        <tr>
            <td>${cstm.cname}</td>
            <td>${cstm.gender}</td>
            <td>${cstm.birthday}</td>
            <td>${cstm.cellphone}</td>
            <td>${cstm.email}</td>
            <td>${cstm.description}</td>
            <td>
<%--                method=search&cname=&gender=%E7%94%B7&cellphone=&email=   ${pageBean.paramString}--%>
                <a href="<c:url value="/customerServlet?method=preEdit&cid=${cstm.cid}"/> ">编辑</a>
                <a href="<c:url value="/customerServlet?method=delete&cid=${cstm.cid}"/> ">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<center>
    第${pageBean.pageNum}页/共${pageBean.totalPage}页&nbsp
    <a href="<c:url value="${pageBean.paramString}&pageNum=1"/>">首页</a>&nbsp
    <a href="<c:url value="${pageBean.paramString}&pageNum=${pageBean.pageNum - 1}"/> " <c:if test="${pageBean.pageNum eq 1}">hidden="hidden"</c:if>>上一页</a>&nbsp

<%--    添加页码--%>
    <c:choose>
<%--        总页面数小于10--%>
        <c:when test="${pageBean.totalPage <= 10}">
            <c:set var="b" value="1"/>
            <c:set var="e" value="${pageBean.totalPage}"/>
        </c:when>
<%--        总页面数大于10--%>
        <c:otherwise>
            <c:set var="b" value="${pageBean.pageNum - 5}"/>
            <c:set var="e" value="${pageBean.pageNum + 4}"/>
<%--            头溢出--%>
            <c:if test="${b < 1}">
                <c:set var="b" value="1"/>
                <c:set var="e" value="10"/>
            </c:if>
<%--            尾溢出--%>
            <c:if test="${e > pageBean.totalPage}">
                <c:set var="e" value="${pageBean.totalPage}"/>
                <c:set var="b" value="${e - 9}"/>
            </c:if>
        </c:otherwise>

    </c:choose>
<%--    打印页码--%>
    <c:forEach begin="${b}" end="${e}" var="i">
        <c:choose>
            <c:when test="${i == pageBean.pageNum}">
                <font color="#ff7f50">${i}</font>&nbsp
            </c:when>
            <c:otherwise>
                <a href="<c:url value="${pageBean.paramString}&pageNum=${i}"/> "><font color="#7fff00">${i}</font></a>&nbsp
            </c:otherwise>
        </c:choose>
    </c:forEach>


    <a href="<c:url value="${pageBean.paramString}&pageNum=${pageBean.pageNum + 1}"/> " <c:if test="${pageBean.pageNum eq pageBean.totalPage}">hidden="hidden"</c:if> >下一页</a>&nbsp
    <a href="<c:url value="${pageBean.paramString}&pageNum=${pageBean.totalPage}"/> ">尾页</a>
</center>

</body>
</html>
