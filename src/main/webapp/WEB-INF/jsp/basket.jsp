<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--@elvariable id="basket" type="com.epam.az.flower.shop.entity.Basket"--%>
<!doctype html>
<html lang="en">
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="basket.number.of.products" var="numberProducts"/>
        <fmt:message key="basket.bill" var="operationBill"/>
        <fmt:message key="basket.delete.product" var="deleteProduct"/>
        <fmt:message key="basket.buy" var="buyBasket"/>
        <fmt:message key="basket.buy" var="buyBasket"/>

    </fmt:bundle>
    <title>Basket</title>
    <meta charset="UTF-8">
</head>
<body>
${numberProducts}${basket.products.size()}<br>
${operationBill}${bill}<br>
<c:forEach items="${basket.products}" var="product">
    <br><c:out value="${product.flower.name}"/><br>
        <c:out value="${product.price}"/><br>
        <c:out value="${product.description}"/><br>
    <a href="delete-product-basket?id=${product.id}">
        ${deleteProduct}
    </a>
    <br>
    <br>
</c:forEach>
<a href="buy-all-basket">${buyBasket}</a>

<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>

</body>
</html>