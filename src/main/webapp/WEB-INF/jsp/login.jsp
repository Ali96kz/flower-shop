<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>

<form method="POST">
    nick Name: <input type="text" size="16" name="nickName"><br>
    password: <input type="password" size="16" name="password"><br>
    <button type="submit"></button>
</form>
<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>
</body>
</html>
