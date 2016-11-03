<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

    <style><jsp:directive.include file="/WEB-INF/css/login.css" /></style>
    <title>Log in</title>
    <fmt:bundle  basename="i18n">
        <fmt:message key="login.sign.nickName" var="nickName"/>
        <fmt:message key="login.sign.password" var="password"/>
        <fmt:message key="login.sign.login" var="login"/>
        <fmt:message key="login.sign.welcome.back" var="welcomeMsg"/>
    </fmt:bundle>
<t:unautorized-user-template>
    <jsp:attribute name="navbar">
    <div class = "container">
    <div class="wrapper">
        <form method="post" name="Login_Form"  class="form-signin">
            <h3 class="form-signin-heading">${welcomeMsg}</h3>
            <hr class="colorgraph"><br>

            <input type="text" class="form-control" name="nickName" placeholder="${nickName}" />
            <input type="password" class="form-control" name="password" placeholder="${password}" />
                        <fmt:bundle basename="i18n">
                            <c:forEach items="${errorMsg}" var="msg">
                                <c:choose>
                                    <c:when test="${msg eq 'login.error.notFound.user'}">
                                        <font size="3" color="red"> <fmt:message key="login.error.notFound.user"/></font>
                                    </c:when>

                                    <c:when test="${msg eq 'login.error.incorrect.nickname'}">
                                        <font size="3" color="red"> <fmt:message key="login.error.incorrect.nickname"/></font>
                                    </c:when>

                                    <c:when test="${msg eq 'login.error.incorrect.password'}">
                                        <font size="3" color="red"> <fmt:message key="login.error.incorrect.password"/></font>
                                    </c:when>
                                </c:choose>
                                <br>
                            </c:forEach>
                        </fmt:bundle>
            <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">${login}</button>
        </form>
    </div>
    </div>
    </jsp:attribute >
</t:unautorized-user-template>