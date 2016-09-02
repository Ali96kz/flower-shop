<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online flower shop</title>
</head>
<body STYLE="background-color:#ffe85b; ">
<div style="margin-top: 200px;">
    <div STYLE="display:block;padding:0 auto; background-color:#ff7647; width:250px; height: 20px"><a href="admin">
        <c:out value="Admin panel"></c:out><br>
    </a></div>

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
</div>
</body>
</html>
