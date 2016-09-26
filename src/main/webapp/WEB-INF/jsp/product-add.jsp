<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%--@elvariable id="origin" type="com.epam.az.flower.shop.entity.Origin"--%>
<%--@elvariable id="visualParameters" type="com.epam.az.flower.shop.entity.VisualParameters"--%>
<%--@elvariable id="growingCondition" type="com.epam.az.flower.shop.entity.GrowingCondition"--%>
<%--@elvariable id="flowerType" type="com.epam.az.flower.shop.entity.FlowerType"--%>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="add.product.flower.name" var="flowerName"/>
    <fmt:message key="add.product.flower.help" var="flowerName"/>
    <fmt:message key="add.product.add.new.product" var="addNewProduct"/>
    <fmt:message key="add.product.average.height" var="averageHeight"/>
    <fmt:message key="add.product.description" var="description"/>
    <fmt:message key="add.product.description.help" var="descriptionHelp"/>
    <fmt:message key="add.product.visual.parameters" var="visualParameter"/>
    <fmt:message key="add.product.growing.condition" var="growingCondition"/>
    <fmt:message key="add.product.growing.help" var="growingHelp"/>

</fmt:bundle>
<t:autorized-user-template>
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
                                <p class="help-block">Username can contain any letters or numbers, without spaces</p>
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
                            <label class="control-label" for="username">Password</label>
                            <div class="controls">
                                <p>
                                    <select name="visualParametersId">
                                    <c:forEach items="${visualParameters}" var="visualParameter">
                                    <option value="${visualParameter.id}">${visualParameter.colorLeaves}- ${visualParameter.colorSteam}</option>
                                    </c:forEach>
                                    </select>
                                </p>
                                <p class="help-block">Password should be at least 6 characters</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">Password</label>
                            <div class="controls">
                                <p><select name="originId">
                                    <option disabled>Choose a</option>
                                    <c:forEach items="${origins}" var="origin">
                                <option value="${origin.id}">${origin.country}- ${origin.province}</option>
                                </c:forEach>
                                </select></p>
                                <p class="help-block">Password should be at least 6 characters</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">Password</label>
                            <div class="controls">
                                <p><select name="growingConditionId">
                                    <c:forEach items="${growingConditions}" var="growingCondition">
                                        <option value="${growingCondition.id}">${growingCondition.name}</option>
                                    </c:forEach>
                                </select></p>
                                <p class="help-block">Password should be at least 6 characters</p>
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

    </jsp:attribute>
</t:autorized-user-template>
<%--
--%>