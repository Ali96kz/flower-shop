<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product</title>
</head>
<body>

<form method="POST">
    Product type : <input type="text" size="16" value="${product.flower.name}" name="productType"><br>
    <br>
    Description : <input type="text" size="16" value="${product.description}" name="description"><br>
    <br>
    Made place :
    <input type="text" size="16" value="${product.origin.country}" name="country">-
    <input type="text" size="16" value="${product.origin.province}" name="province"><br>
    Price : <input type="text" size="16" value="${product.price}" name="price"><br>
    VisualParameters: <br>
    Leaves color :<input type="text" size="16" value="${product.flower.visualParameters.colorLeaves}" name="colorLeaves"><br>
    Steam color : <input type="text" size="16" value="${product.flower.visualParameters.colorSteam}" name="colorSteam"><br>
    Average height : <input type="text" size="16" value="${product.flower.visualParameters.averageHeight}" name="averageHeight"><br>

    <button type="submit"></button>
</form>
</body>
</html>
