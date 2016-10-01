<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%--@elvariable id="origin" type="com.epam.az.flower.shop.entity.Origin"--%>
<%--@elvariable id="userRole" type="com.epam.az.flower.shop.entity.UserRole"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="i18n">
    <fmt:message key="register.sign.title" var="title"/>
    <fmt:message key="register.nick.name" var="nickName"/>
    <fmt:message key="register.first.name" var="firstName"/>
    <fmt:message key="register.lastname" var="lastName"/>
    <fmt:message key="register.passsword" var="password"/>
    <fmt:message key="register.confirm.passsword" var="confirmPassword"/>
    <fmt:message key="register.birth.day.date" var="birhday"/>
    <fmt:message key="register.sign.up" var="signUpButton"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/css/registration.css"/>
</style>
<t:unautorized-user-template>
    <jsp:attribute name="navbar">
    <div class="container">
        <div class="row">
            <div class="col-md-6">

                <form class="form-horizontal" method="POST">
                    <fieldset>
                        <div id="legend">
                            <legend class="">Register</legend>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${firstName}</label>
                            <div class="controls">
                                <input id="firstName" name="firstName" placeholder="" class="form-control input-lg"
                                       type="text">
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${lastName}</label>
                            <div class="controls">
                                <input id="lastName" name="lastName" placeholder="" class="form-control input-lg"
                                       type="text">
                                <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${nickName}</label>
                            <div class="controls">
                                <input id="username" name="nickName" placeholder="" class="form-control input-lg"
                                       type="text">
                                <p class="help-block">date</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${birhday}</label>
                            <div class="controls">
                                <input id="dateBirthday" name="dateBirthday" placeholder=""
                                       class="form-control input-lg" type="text">
                                <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password">Password</label>
                            <div class="controls">
                                <input id="password" name="password" placeholder="" class="form-control input-lg"
                                       type="password">
                                <p class="help-block">Password should be at least 6 characters</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password_confirm">Password (Confirm)</label>
                            <div class="controls">
                                <input id="password_confirm" name="confirmPassword" placeholder=""
                                       class="form-control input-lg" type="password">
                                <p class="help-block">Please confirm password</p>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="password_confirm">Password (Confirm)</label>
                            <div class="controls">
                                <c:forEach items="${userRoles}" var="userRole">
                                    <option value="${userRole.id}">${userRole.name}</option>
                                    </c:forEach>
                            </div>
                        </div>

                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls">
                                <button class="btn btn-success">Register</button>
                            </div>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>

    <c:forEach items="${errorMsg}" var="msg">
        <c:out value="${msg}"/><br>
    </c:forEach>

     </jsp:attribute>
</t:unautorized-user-template>
