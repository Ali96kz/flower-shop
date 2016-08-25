<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>

<html>
<head>
    <title>Online vitrine</title>
</head>
<body>
<c:forEach items = "${products}" var = "product">
    <c:out value = "${product.flower.name}" /><br>
    <c:out value = "${product.price}" /><br>
    <c:out value = "${product.description}" /><br>
    <br>
</c:forEach>
${product1}
${product1}
</body>
</html>
