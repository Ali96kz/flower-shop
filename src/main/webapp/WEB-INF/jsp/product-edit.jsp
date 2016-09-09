<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product edit page</title>
</head>
<body>

<form method="POST">
    Flower name: <input type="text" size="16" value="${product.flower.name}" name="flowerName"><br>
    <br>
    Flower type:
    <p><select name="flowerTypeId">
        <option disabled>Choose a</option>
        <c:forEach items="${flowerTypes}" var="flowerType">
            <option value="${flowerType.id}">${flowerType.name}</option>
        </c:forEach>
    </select></p>

    <br>
    Description : <input type="text" size="16" value="${product.description}" name="description"><br>
    <br>

    Made place :
    <p><select name="originId">
        <option disabled>Choose a</option>
        <c:forEach items="${origins}" var="origin">
            <option value="${origin.id}">${origin.country}- ${origin.province}</option>
        </c:forEach>
    </select></p>

    Price : <input type="text" size="16" value="${product.price}" name="price"><br>

    VisualParameters:
    <p><select name="visualParametersId">
        <c:forEach items="${visualParameters}" var="visualParameters">
            <option value="${visualParameters.id}">${visualParameters.colorLeaves}- ${visualParameters.colorSteam}</option>
        </c:forEach>
    </select></p>

    Average height : <input type="text" size="16" value="${product.flower.averageHeight}"
                            name="averageHeight"><br>
    Growing Condition :
    Choose one of this:
    <p><select name="growingConditionId">
        <c:forEach items="${growingConditions}" var="growingCondition">
            <option value="${growingCondition.id}">${growingCondition.name}</option>
        </c:forEach>
    </select></p>
    <br>

    <button type="submit">
        <value>edit this product</value>
    </button>

    <c:forEach items="${errorMsg}" var="msg">
        <c:out value="${msg}"/><br>
    </c:forEach>

</form>
</body>
</html>
