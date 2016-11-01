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
    <fmt:message key="register.label.register" var="labelReg"/>
    <fmt:message key="register.label.register" var="labelReg"/>
    <fmt:message key="register.help.for.string" var="helpCom"/>
    <fmt:message key="register.help.for.date" var="helpDate"/>
    <fmt:message key="register.help.for.password" var="helpPassword"/>
    <fmt:message key="register.help.for.confirm.password" var="helpConfirmPassword"/>
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
                            <legend class="">${labelReg}</legend>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${firstName}</label>
                            <div class="controls">
                                <input id="firstName" name="firstName"  value="${user.firstName}" placeholder="" class="form-control input-lg"
                                       type="text">
                                <p class="help-block">${helpCom}</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${lastName}</label>
                            <div class="controls">
                                <input id="lastName" name="lastName" placeholder=""  value="${user.lastName}" class="form-control input-lg"
                                       type="text">
                                <p class="help-block">${helpCom}</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${nickName}</label>
                            <div class="controls">
                                <input id="username" name="nickName"  value="${user.nickName}" placeholder="" class="form-control input-lg"
                                       type="text">
                                <p class="help-block">date</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${birhday}</label>
                            <div class="controls">
                                <input id="dateBirthday" name="dateBirthday" placeholder=""
                                       class="form-control input-lg" value="${user.dateBirthday}" type="text">
                                <p class="help-block">${helpDate}</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password" >${password}</label>
                            <div class="controls">
                                <input id="password" name="password" placeholder="" class="form-control input-lg"
                                       type="password">
                                <p class="help-block">${helpPassword}</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password_confirm">${confirmPassword}</label>
                            <div class="controls">
                                <input id="password_confirm" name="confirmPassword" placeholder=""
                                       class="form-control input-lg" type="password">
                                <p class="help-block">${helpConfirmPassword}</p>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="password_confirm">${confirmPassword}</label>
                            <div class="controls">

                                <c:forEach items="${errorMsg}" var="msg">
                                    <font size="3" color="red"><c:out value="${msg}"></c:out></font><br>
                                </c:forEach>
                            </div>
                        </div>


                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls">
                                <button class="btn btn-success">${signUpButton}</button>
                            </div>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>


     </jsp:attribute>
</t:unautorized-user-template>
