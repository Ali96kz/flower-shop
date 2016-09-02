<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body style="background-color: #ffe85b">
<div style="margin-top:200px;" align="center">
<form method="POST">
    nick Name: <input type="text" size="16" name="nickName"><br>
    password: <input type="password" size="16" name="password"><br>
    <button type="submit"><value>Log in</value></button>
</form></div>

<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>

</body>
</html>
