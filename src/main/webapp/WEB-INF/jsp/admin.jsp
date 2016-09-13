<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="i18n">
    <fmt:message key="admin.title" var="title"/>
    <fmt:message key="admin.register" var="register"/>
    <fmt:message key="user.nick.name" var="nickName"/>
    <fmt:message key="admin.delete.user" var="deleteUser"/>
</fmt:bundle>

<head>
    <title>${title}</title>
</head>
<body>
<a href="admin-registration">
    <c:out value="${register}"/><br>
</a>

<br>
<c:forEach items="${users}" var="user">

    ${nickName}<c:out value="${user.nickName}"/><br>

    <a href="delete-user?id=${user.id}">
        <c:out value="${deleteUser}"/><br>
    </a>
    <br>
</c:forEach>
</body>
</html>
