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
    Your role: ${user.userRole.name}
</div>

<a href="basket">
    My basket
</a>
<br>
<a href="cash">
    Cash
</a>
<br>
<a href="logout">
    logout
</a>
<br>
<a href="vitrine">
    online vitrine
</a>
<br>
<a href="delete">
    DELETE ACCOUNT
</a>

</body>
</html>