<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bill</title>
</head>
<body>
Your bill ${bill}<br>
<c:forEach items="${basket.products}" var="product">
    <c:out value="${product.flower.name}"/><br>
    <c:out value="${product.price}"/><br>
    <c:out value="${product.description}"/><br>
    </a>
    <br>
</c:forEach>

</body>
</html>
