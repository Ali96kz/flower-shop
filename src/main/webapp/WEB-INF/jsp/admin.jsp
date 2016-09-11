<%--@elvariable id="order" type="com.epam.az.flower.shop.entity.UserOrder"--%>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Admin page</title>
</head>

<body>
<a href="admin-registration">
    <c:out value="Register new user in system"/><br>
</a>

<br>
<c:forEach items="${users}" var="user">
    Nick name <c:out value="${user.nickName}"/><br>
    <a href="delete-user?id=${product.id}">
        <c:out value=""/><br>
    </a>
    <br>
</c:forEach>


</body>

</html>
