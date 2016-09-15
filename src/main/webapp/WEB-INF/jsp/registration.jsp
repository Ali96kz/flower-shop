<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%--@elvariable id="origin" type="com.epam.az.flower.shop.entity.Origin"--%>
<%--@elvariable id="userRole" type="com.epam.az.flower.shop.entity.UserRole"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="register.sign.title" var="title"/>
        <fmt:message key="register.nick.name" var="nickName "/>
        <fmt:message key="register.first.name" var="firstName"/>
        <fmt:message key="register.lastname" var="yourLastname"/>
        <fmt:message key="register.passsword" var="password"/>
        <fmt:message key="register.confirm.passsword" var="confirmPassword"/>
        <fmt:message key="register.birth.day.date" var="birhday"/>
        <fmt:message key="register.sign.up" var="signUpButton"/>
    </fmt:bundle>

    <meta charset="UTF-8">
    <title>${title}</title>
</head>

<body style="background-color: #ffe85b">

<div style="margin-top:200px;" align="center">
    <form method="POST">
        ${firstName} <input type="text" size="16" value="${user.firstName}" name="firstName"><br>
        ${yourLastname}<input type="text" size="16" value="${user.lastName}" name="lastName"><br>
        ${nickName }<input type="text" size="16" value="${user.nickName}" name="nickName"><br>
        ${birhday}<input type="text" size="16" value="${user.dateBirthday}" name="dateBirthday"><br>
        ${password}<input type="password" size="16" name="password"><br>
        ${confirmPassword}<input type="password" size="16" name="confirmPassword"><br>
        <p><select name="originId">
            <c:forEach items="${origins}" var="origin">
                <option value="${origin.id}">${origin.country}-${origin.province}</option>
            </c:forEach>
        </select></p>
        <br>
        <button type="submit">
            <value>${signUpButton}</value>
        </button>
    </form>
    <c:forEach items="${errorMsg}" var="msg">
        <c:out value="${msg}"/><br>
    </c:forEach>
    <a href="/"></a>
</div>
</body>
</html>