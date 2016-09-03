<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="basket" type="com.epam.az.flower.shop.entity.Basket"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Basket</title>
</head>
<body>
A number of products in your basket: ${basket.products.size()}<br>
Your bill ${bill}<br>
<c:forEach items="${basket.products}" var="product">
    <br><c:out value="${product.flower.name}"/><br>
        <c:out value="${product.price}"/><br>
        <c:out value="${product.description}"/><br>
    <a href="deleteProduct?id=${product.id}">
        delete from basket
    </a>
    <br>
    <br>
</c:forEach>
<a href="buy-all-basket">pay for this</a>

<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>

<form  method="post">
    <p><select size="3" multiple name="hero[]">
        <option disabled>Choose a </option>
        <option value="Чебурашка">Чебурашка</option>
        <option selected value="Крокодил Гена">Крокодил Гена</option>
        <option value="Шапокляк">Шапокляк</option>
        <option value="Крыса Лариса">Крыса Лариса</option>
    </select></p>
    <p><input type="submit" value="Отправить"></p>
</form>
</body>
</html>