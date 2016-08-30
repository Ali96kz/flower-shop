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
Your transactions
</body>
</html>