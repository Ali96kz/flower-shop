<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>

<html>
<head>
    <title>Online vitrine</title>
</head>
<body>
<c:forEach items = "${products}" var = "product">
    <c:out value = "${product}" /><br>
    ${product}
</c:forEach>
${product1}
${product1}
</body>
</html>
