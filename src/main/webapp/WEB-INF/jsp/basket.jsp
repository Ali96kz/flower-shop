<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="basket" type="com.epam.az.flower.shop.entity.Basket"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Basket</title>
</head>
<body>
A number of products in your basket: ${basket.products.size()}
<c:forEach items="${basket.products}" var="product">
        <c:out value="${product.flower.name}"/><br>
        <c:out value="${product.price}"/><br>
        <c:out value="${product.description}"/><br>
    </a>
    <br>
    <br>
</c:forEach>
</body>
</html>