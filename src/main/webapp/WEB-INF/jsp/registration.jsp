<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<form method="POST">
    Your first name: <input type="text" size="16" value="${user.firstName}" name="firstName"><br>
    Your last name: <input type="text" size="16" value="${user.lastName}"><br>
    your nick Name: <input type="text" size="16" value="${user.nickName}"><br>
    your birthday: <input type="text" size="16" value="${user.dateBirthday}"><br>
    create password: <input type="password" size="16" name="password"><br>
    confirm password: <input type="password" size="16" name="confirmPassword"><br>
    <button type="submit"></button>
</form>
<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>
<a href="/"></a>

</body>
</html>