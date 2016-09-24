<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager</title>
</head>
<body>
<fmt:bundle basename="i18n">
    <fmt:message key="manager.product.description" var="description"/>
    <fmt:message key="manager.product.price" var="price"/>
    <fmt:message key="manager.product.delete" var="delete" />
    <fmt:message key="manager.product.quantity" var="quantity" />
    <fmt:message key="manager.product.add.quantity" var="addQuantity" />
    <fmt:message key="manager.product.name" var="productName" />
    <fmt:message key="manager.product.add" var="addNewProduct" />
    <fmt:message key="manager.product.edit" var="editProduct" />
</fmt:bundle>

<a href="add-product">
    <c:out value="${addNewProduct}"/>
</a>
<br>
<c:forEach items="${products}" var="product">
    <br>
    <a href="product-inf?id=${product.id}">
    ${productName}<c:out value="${product.flower.name}"/><br>
    ${price}<c:out value="${product.price}"/><br>
    ${description}<c:out value="${product.description}"/><br>
    ${quantity}<c:out value="${product.quantity}"/><br>

    </a>
    <br>
    <a href="add-quantity?id=${product.id}">
        <c:out value="${addQuantity}"/><br>
    </a>
    <a href="delete-product?id=${product.id}">
        <c:out value="${delete}"/><br>
    </a>
    <a href="edit-product?productId=${product.id}">
        <c:out value="${editProduct}"/><br>
    </a>
    <br>
</c:forEach>

<c:forEach items="${pageList}" var="number">
<a href="vitrine?page=${number}">
        <c:out value="${number}"/><br>
    </c:forEach>

</body>
</html>
