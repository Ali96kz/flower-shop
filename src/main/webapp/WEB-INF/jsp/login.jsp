<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body style="background-color: #ffe85b">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle  basename="i18n">
    <fmt:message key="login.sign.nickName" var="nickName"/>
    <fmt:message key="login.sign.password" var="password"/>
    <fmt:message key="login.sign.login" var="login"/>
</fmt:bundle>

<div style="margin-top:200px;" align="center">
<form method="POST">

    ${nickName}<input type="text" size="16" name="nickName" ><br>
    ${password} <input type="password" size="16" name="password" placeholder="${password}"><br>
    <button type="submit"><value>${login}</value></button>
</form></div>

<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>

</body>
</html>
