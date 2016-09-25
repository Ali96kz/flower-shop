<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%--@elvariable id="origin" type="com.epam.az.flower.shop.entity.Origin"--%>
<%--@elvariable id="visualParameters" type="com.epam.az.flower.shop.entity.VisualParameters"--%>
<%--@elvariable id="growingCondition" type="com.epam.az.flower.shop.entity.GrowingCondition"--%>
<%--@elvariable id="flowerType" type="com.epam.az.flower.shop.entity.FlowerType"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>

<fmt:bundle basename="i18n">
    <fmt:message key="user.profile.balance" var="balance"/>
    <fmt:message key="user.profile.birthday.day" var="bitrthdayDay"/>
    <fmt:message key="user.profile.last.name" var="lastName"/>
    <fmt:message key="user.profile.delete" var="deleteAccount"/>
    <fmt:message key="user.profile.role" var="role"/>
    <fmt:message key="user.profile.first.name" var="name"/>
    <fmt:message key="user.profile.edit.account" var="editAccount"/>
    <fmt:message key="user.profile.logout" var="logout"/>
    <fmt:message key="user.profile.online.vitrine" var="vitrine"/>
    <fmt:message key="user.profile.cash" var="cash"/>
    <fmt:message key="user.profile.my.basket" var="basket"/>
</fmt:bundle>
<t:autorized-user-template>
    <jsp:attribute name="navbar">

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
        <value>add this product</value>
    </button>

    <c:forEach items="${errorMsg}" var="msg">
        <c:out value="${msg}"/><br>
    </c:forEach>

</form>
    </jsp:attribute>
</t:autorized-user-template>
