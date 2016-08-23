<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cash</title>
</head>
<body>
Your balance ${user.balance}
<form action="addMoneyToBalance" method="POST">
    Add money to balance <input type="text" size="8" name="money">
    <button type="submit"></button>
</form>
</body>
</html>