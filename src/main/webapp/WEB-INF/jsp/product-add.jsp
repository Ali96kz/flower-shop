<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%--@elvariable id="origin" type="com.epam.az.flower.shop.entity.Origin"--%>
<%--@elvariable id="visualParameters" type="com.epam.az.flower.shop.entity.VisualParameters"--%>
<%--@elvariable id="temperature" type="com.epam.az.flower.shop.entity.Temperature"--%>
<%--@elvariable id="growingCondition" type="com.epam.az.flower.shop.entity.GrowingCondition"--%>
<%--@elvariable id="waterInWeek" type="com.epam.az.flower.shop.entity.WaterInWeek"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>

<form method="POST">
    Flower name: <input type="text" size="16" value="${product.flower.name}" name="flowerName"><br>
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

    VisualParameters: <br>

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

    or insert new
    name: <input type="text" size="16" value="${product.flower.name}" name="growingConditionName"><br>
    Temperature :

    <p><select name="temperatureId">
        <c:forEach items="${temperatures}" var="temperature">
            <option value="${temperature.id}">${temperature.tmin}- ${temperature.tmax}</option>
        </c:forEach>
    </select></p>
    <br>
    Water in week:

    <p><select name="waterInWeekId">
        <c:forEach items="${waterInWeeks}" var="waterInWeek">
            <option value="${waterInWeek.id}">${waterInWeek.min}- ${waterInWeek.max}</option>
        </c:forEach>
    </select></p>
    <br>

    <button type="submit">
        <value>change</value>
    </button>

    <c:forEach items="${errorMsg}" var="msg">
        <c:out value="${msg}"/><br>
    </c:forEach>

</form>
</body>
</html>
