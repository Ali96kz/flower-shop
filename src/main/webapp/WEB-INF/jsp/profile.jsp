<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<!doctype html>
<html lang="en">

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


<head>
    <meta charset="UTF-8">
    <title>${user.firstName} Profile</title>
</head>
<body>

<div>
    ${name}:${user.firstName}
    ${lastName}:${user.nickName}
    ${bitrthdayDay}:${user.dateBirthday}
    ${balance}:${user.balance}
    ${role} :${user.userRole.name}
</div>

<a href="basket">
    ${basket}
</a>
<br>
<a href="cash">
    ${cash}
</a>
<br>
<a href="logout">
    ${logout}
</a>
<br>
<a href="vitrine">
    ${vitrine}
</a>
<br>
<a href="edit-account">
    ${editAccount}
</a>
<a href="delete-account">
    ${deleteAccount}
</a>

</body>
</html>1