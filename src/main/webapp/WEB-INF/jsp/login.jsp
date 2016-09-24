<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><jsp:directive.include file="/WEB-INF/css/login.css" /></style>
    <title>Log in</title>
    <fmt:bundle  basename="i18n">
        <fmt:message key="login.sign.nickName" var="nickName"/>
        <fmt:message key="login.sign.password" var="password"/>
        <fmt:message key="login.sign.login" var="login"/>
    </fmt:bundle>

</head>
<body >

<div class = "container">
    <div class="wrapper">
        <form method="post" name="Login_Form"  class="form-signin">
            <h3 class="form-signin-heading">Welcome Back! Please Sign In</h3>
            <hr class="colorgraph"><br>

            <input type="text" class="form-control" name="nickName" placeholder="${nickName}" required="" autofocus="" />
            <input type="password" class="form-control" name="password" placeholder="${password}" required=""/>

            <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Login</button>
        </form>
    </div>
</div>


</body>
</html>


<%--
<c:forEach items="${errorMsg}" var="msg">
    <c:out value="${msg}"/><br>
</c:forEach>


--%>