<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="basket" type="com.epam.az.flower.shop.entity.Basket"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Basket</title>
</head>
<body>
A number of products in your basket: ${basket.products.size()}<br>
Your bill ${bill}<br>
<c:forEach items="${basket.products}" var="product">
    <br><c:out value="${product.flower.name}"/><br>
        <c:out value="${product.price}"/><br>
        <c:out value="${product.description}"/><br>
    <a href="basket/delete/product?id=${product.id}">
        delete from basket
    </a>
    <br>
    <br>
</c:forEach>
<a href="buy-all-basket">pay for this</a>

</body>
</html>