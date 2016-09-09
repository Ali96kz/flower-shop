<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="UserBalance" type="com.epam.az.flower.shop.entity.UserTransaction"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User trasaction</title>
</head>
<body>
Your transaction
<c:forEach items="${transactions}" var="UserBalance">
    <pre>
        Transaction number: ${UserBalance.id}
            <c:out value="${UserBalance.transaction.name}"/>
            <c:out value="${UserBalance.transaction.id}"/>
            <c:out value="${UserBalance.sum}"/>
            <c:out value="${UserBalance.transactionDate}"/>
    </pre>
</c:forEach>

</body>
</html>
