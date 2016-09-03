<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager</title>
</head>
<body>

<c:forEach items="${products}" var="product">
    <a href="product-inf?id=${product.id}">
        <c:out value="${product.flower.name}"/><br>
        <c:out value="${product.price}"/><br>
        <c:out value="${product.description}"/><br>
    </a>
    <br>

    <a href="edit-product?id=${product.id}">
        <c:out value="edit"/>
    </a>
    <a href="delete-product?id=${product.id}">
        <c:out value="delete"/><br>
    </a>

    <br>
    <br>
</c:forEach>

</body>
</html>
