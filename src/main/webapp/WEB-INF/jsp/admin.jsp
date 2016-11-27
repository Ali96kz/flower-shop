<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="i18n">
    <fmt:message key="admin.title" var="title"/>
    <fmt:message key="admin.register" var="register"/>
    <fmt:message key="usernick.name" var="nickName"/>
    <fmt:message key="admin.delete.user" var="deleteUser"/>
</fmt:bundle>

<t:autorized-user-template>
    <jsp:attribute name="navbar">
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="row">
                <a href="admin-registration">
                    <c:out value="${register}"/><br>
                </a>
            <c:forEach items="${users}" var="user">
                ${nickName}<c:out value="${user.nickName}"/><br>
                <br>
            </c:forEach>
            </div>
        </div>
    </div>
</div>
    </jsp:attribute>
</t:autorized-user-template>