<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User was delete</title>
</head>
<body>
    User with that nickname was deleted
    <a href="registration">
        <c:out value="registration form"></c:out><br>
    </a>

    <a href="login">
        <c:out value="login form"></c:out><br>
    </a>

    <a href="vitrine">
        <c:out value="shop vitrine"></c:out><br>
    </a>

    <a href="basket">
        <c:out value="your basket"></c:out><br>
    </a>

</body>
</html>
