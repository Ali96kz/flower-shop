<%--@elvariable id="order" type="com.epam.az.flower.shop.entity.Order"--%>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
</head>

<body>

All user in system${users}
<c:forEach items="${users}" var="user">
    <a href="product-inf?id=${user.id}">
        First name: <c:out value="${user.firstName}"/><br>
        Last name <c:out value="${user.lastName}"/><br>
        Nick name <c:out value="${user.nickName}"/><br>
    </a>
    <br>
    <br>
</c:forEach>

All order in system
<c:forEach items="${orders}" var="order">
    <c:out value="${order.user.nickName}"/> buy a
    <c:out value="${order.product.flower.name}"/>
    <br>
    <br>
</c:forEach>

</body>

</html>
