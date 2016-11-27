<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%--@elvariable id="transaction" type="com.epam.az.flower.shop.entity.Transaction"--%>
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

    <select name="transactionId">
        <c:forEach items="${transactions}" var="transaction">
            <option value="${transaction.id}">${transaction.name}</option>
        </c:forEach>
    </select>

    <button>Find</button>
    Общая сумма по данному типу транзакции ${sum}

</form>
</body>
</html>
