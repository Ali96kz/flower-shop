<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager</title>
</head>
<body>
<a href="add-product">
    <c:out value="add new product"/>
</a>

<c:forEach items="${products}" var="product">
    <a href="product-inf?id=${product.id}">
        <c:out value="${product.flower.name}"/><br>
        <c:out value="${product.price}"/><br>
        <c:out value="${product.description}"/><br>
        quantity<c:out value="${product.quantity}"/><br>

    </a>
    <br>
    <a href="add-quantity?id=${product.id}">
        <c:out value="add quantity"/><br>
    </a>

    <br>
    <a href="delete-product?id=${product.id}">
        <c:out value="delete"/><br>
    </a>

    <br>
    <br>
</c:forEach>

<c:forEach items="${pageList}" var="number">
    <a href="vitrine?page=${number}">
        <c:out value="${number}"/><br>
    </c:forEach>

</body>
</html>
