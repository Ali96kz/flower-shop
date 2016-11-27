<%--@elvariable id="userOrder" type="com.epam.az.flower.shop.entity.UserOrder"--%>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="form-horizontal" method="POST">
    <select name="userId">
        <c:forEach items="${users}" var="user">
            <option value="${user.id}">${user.firstName} ${user.lastName}</option>
        </c:forEach>
    </select>
    <br>
    День заказа
    <input name="date" type="text">
    <button>Find</button>
</form>

<c:forEach items="${userOrders}" var="userOrder">
    ${userOrder.user.firstName} ${userOrder.orderDate} ${userOrder.product.flower.name}
    <br>
</c:forEach>

</body>
</html>
