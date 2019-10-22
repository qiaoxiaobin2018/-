<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/21
  Time: 23:02
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
    <title>edit</title>
    <base href="<%=basePath%>">
</head>
<body style="text-align: center;">
<h3 align="center">编辑客户</h3>
<form action="<c:url value="/customerServlet"/> " method="post">
    <%--    向servlet传递一个名为method的参数，其值表示要调用servlet的哪一个方法--%>
    <input type="hidden" name="method" value="edit"/>
        <input type="hidden" name="cid" value="${customer.cid}"/>
    <table border="0" align="center" width="40%">
        <tr>
            <td width="100px">客户名称</td>
            <td width="40%">
                <input type="text" name="cname" value="${customer.cname}"/>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户性别</td>
            <td>
                <input type="radio" name="gender" value="男" id="male" <c:if test="${customer.gender eq '男'}">checked="checked"</c:if>/>
                <label for="male">男</label>
                <input type="radio" name="gender" value="女" id="falmale" <c:if test="${customer.gender eq '女'}">checked="checked"</c:if>/>
                <label for="falmale">女</label>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户生日</td>
            <td>
                <input type="date" name="birthday" id="birthday" value="${customer.birthday}"/>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户电话</td>
            <td>
                <input type="text" name="cellphone" id="cellphone" value="${customer.cellphone}"/>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户邮箱</td>
            <td>
                <input type="text" name="email" id="email" value="${customer.email}"/>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>客户描述</td>
            <td>
<%--                <input type="text" name="description" id="description" value="${customer.description}"/>--%>
                <textarea rows="5" cols="30" name="description">${customer.description}</textarea>
            </td>
            <%--            错误信息--%>
            <td align="left">

            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="修改">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
