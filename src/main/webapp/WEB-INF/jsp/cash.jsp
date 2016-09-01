<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cash</title>
</head>
<body>
Your balance ${user.balance}
<br>
<form action="addMoneyToBalance" method="POST">
    Add money to balance <input type="text" size="8" name="money"><br>
    <button type="submit" name = "add"></button>
</form>
<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>

<a href="basket">
    My basket
</a>
<br>
<a href="logout">
    logout
</a>
<br>
<a href="vitrine">
    online vitrine
</a>
</body>
</html>