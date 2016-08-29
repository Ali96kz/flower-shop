<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.firstName} Profile</title>
</head>
<body>
<div>
    Name: ${user.firstName}
    Last name: ${user.nickName}
    Birthday: ${user.dateBirthday}
    Balance: ${user.balance}
</div>
<a href="logout">
    <c:out value="logout"/><br>
</a>

</body>
</html>