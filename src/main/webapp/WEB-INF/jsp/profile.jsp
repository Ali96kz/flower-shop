<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>

<fmt:bundle basename="i18n">
    <fmt:message key="userprofile.balance" var="balance"/>
    <fmt:message key="userprofile.birthday.day" var="bitrthdayDay"/>
    <fmt:message key="userprofile.last.name" var="lastName"/>
    <fmt:message key="userprofile.delete" var="deleteAccount"/>
    <fmt:message key="userprofile.role" var="role"/>
    <fmt:message key="userprofile.first.name" var="name"/>
    <fmt:message key="userprofile.edit.account" var="editAccount"/>
    <fmt:message key="userprofile.logout" var="logout"/>
    <fmt:message key="userprofile.online.vitrine" var="vitrine"/>
    <fmt:message key="userprofile.cash" var="cash"/>
    <fmt:message key="userprofile.my.basket" var="basket"/>
</fmt:bundle>
<t:autorized-user-template>
    <jsp:attribute name="navbar">
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="thumbnail">
                <div class="caption-full">
                    <p>${name}:${user.firstName}</p>
                    <p>${lastName}:${user.nickName}</p>
                    <p>${bitrthdayDay}:${user.dateBirthday}</p>
                    <p>${balance}:${user.balance}</p>
                    <p>${role} :${user.userRole.name}</p>
                </div>
            </div>
        </div>
    </div>
</div>
        </jsp:attribute>

</t:autorized-user-template>
