<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>

<html>
<head>
    <title>Online vitrine</title>
</head>
<body>
<c:forEach items="${products}" var="product">
    <a href="product-inf?id=${product.id}">
        <c:out value="${product.flower.name}"/><br>
        <c:out value="${product.price}"/><br>
        <c:out value="${product.description}"/><br>
    </a>
    <br>

    <a href="product-in-basket?productId=${product.id}">
        <c:out value="add in basket"/><br>
    </a>

    <br>
    <br>
</c:forEach>
</body>
</html>
