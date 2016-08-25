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
    Your first name: <input type="text" size="45" name="firstName"><br>
    Your last name: <input type="text" size="45" name="lastName"><br>
    your nick Name: <input type="text" size="45" name="nickName"><br>
    your birthday: <input type="text" size="45" name="birthdayDate"><br>
    create password: <input type="text" size="45" name="password"><br>
    confirm password: <input type="text" size="45" name="confirmPassword"><br>
    <button type="submit"></button>
</form>

<c:forEach items = "${errorMsg}" var = "msg">
    <c:out value = "${msg}" /><br>
</c:forEach>

</body>
</html>