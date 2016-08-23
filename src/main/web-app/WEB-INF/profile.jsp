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
</body>
</html>