<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="i18n">
    <fmt:message key="admin.title" var="title"/>
    <fmt:message key="admin.register" var="register"/>
    <fmt:message key="user.nick.name" var="nickName"/>
    <fmt:message key="admin.delete.user" var="deleteUser"/>
</fmt:bundle>

<t:autorized-user-template>
    <jsp:attribute name="navbar">

    <a href="admin-registration">
        <c:out value="${register}"/><br>
    </a>

    <c:forEach items="${users}" var="user">
        ${nickName}<c:out value="${user.nickName}"/><br>
        <a href="delete-user?id=${user.id}">
            <c:out value="${deleteUser}"/><br>
        </a>
        <br>
    </c:forEach>
    </jsp:attribute >
</t:autorized-user-template>