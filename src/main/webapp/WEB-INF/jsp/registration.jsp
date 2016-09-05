<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%--@elvariable id="userRole" type="com.epam.az.flower.shop.entity.UserRole"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body style="background-color: #ffe85b">
<div style="margin-top:200px;" align="center">
    <form method="POST">
        Your first name: <input type="text" size="16" value="${user.firstName}" name="firstName"><br>
        Your last name: <input type="text" size="16" value="${user.lastName}" name="lastName"><br>
        your nick Name: <input type="text" size="16" value="${user.nickName}" name="nickName"><br>
        your birthday: <input type="text" size="16" value="${user.dateBirthday}" name="dateBirthday"><br>
        create password: <input type="password" size="16" name="password"><br>
        confirm password: <input type="password" size="16" name="confirmPassword"><br>
        <button type="submit">
            <value>sign up</value>
        </button>
        <p><select name="userRoleId">
            <c:forEach items="${userRoles}" var="userRole">
                <option value="${userRole.id}">${userRole.name}</option>
            </c:forEach>
        </select></p>
        <br>
    </form>
    <c:forEach items="${errorMsg}" var="msg">
        <c:out value="${msg}"/><br>
    </c:forEach>
    <a href="/"></a>
</div>
</body>
</html>