<%--@elvariable id="order" type="com.epam.az.flower.shop.entity.Order"--%>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Admin page</title>
</head>

<body>

All user in system
<c:forEach items="${users}" var="user">
    First name: <c:out value="${user.firstName}"/><br>
    Last name <c:out value="${user.lastName}"/><br>
    Nick name <c:out value="${user.nickName}"/><br>
    <br>
    <br>
</c:forEach>
<%--TODO problem with SQL--%>
All order in system
<c:forEach items="${orders}" var="order">
    <c:out value="${order.user.nickName}"/> buy a
    <c:out value="${order.product.flower.name}"/>
    <br>
    <br>
</c:forEach>
All product
<c:forEach items="${products}" var="product">
    <a href="product-inf?id=${product.id}">
        name: <c:out value="${product.flower.name}"/><br>
        price: <c:out value="${product.price}"/><br>
    </a>
    <br>
    <br>
</c:forEach>


</body>

</html>
