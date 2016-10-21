<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%--@elvariable id="origin" type="com.epam.az.flower.shop.entity.Origin"--%>
<%--@elvariable id="visualParametersWord" type="com.epam.az.flower.shop.entity.VisualParameters"--%>
<%--@elvariable id="growingCondition" type="com.epam.az.flower.shop.entity.GrowingCondition"--%>
<%--@elvariable id="flowerType" type="com.epam.az.flower.shop.entity.FlowerType"--%>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="add.product.flower.name" var="flowerName"/>
    <fmt:message key="add.product.flower.help" var="flowerNameHelp"/>
    <fmt:message key="add.product.add.new.product" var="addNewProduct"/>
    <fmt:message key="add.product.average.height" var="averageHeight"/>
    <fmt:message key="add.product.description" var="description"/>
    <fmt:message key="add.product.description.help" var="descriptionHelp"/>
    <fmt:message key="add.product.visual.parameters" var="visualParameterWord"/>
    <fmt:message key="add.product.visual.parameters.help" var="visualParameterHelp"/>
    <fmt:message key="add.product.growing.condition" var="growingConditionWord"/>
    <fmt:message key="add.product.price" var="price"/>
    <fmt:message key="add.product.growing.help" var="growingHelp"/>
    <fmt:message key="add.product.flowe.type" var="flowerTypeWord"/>
    <fmt:message key="add.product.made.place" var="madePlace"/>
    <fmt:message key="edit.product.button.name" var="editProduct"/>

</fmt:bundle>
<t:autorized-user-template>
    <jsp:attribute name="navbar">
    <div class="container">
        <div class="row">
            <div class="col-md-6">

                <form class="form-horizontal" method="POST">
                    <fieldset>
                        <div id="legend">
                            <legend class="">${editProduct}</legend>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="username">${flowerName}</label>
                            <div class="controls">
                                <input type="text" size="16" value="${product.flower.name}" name="flowerName" class="form-control input-lg">
                                <p class="help-block">${flowerNameHelp}</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${price}</label>
                            <div class="controls">
                                <input type="text" size="16" value="${product.price}" name="price" class="form-control input-lg">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${description}</label>
                            <div class="controls">
                                <input id="username" name="description" value="${product.description}" placeholder="" class="form-control input-lg"
                                       type="text">


                                <p class="help-block">${descriptionHelp}</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${averageHeight}</label>
                            <div class="controls">
                                <input id="averageHeight" value="${product.flower.averageHeight}"
                                       name="averageHeight" placeholder="" class="form-control input-lg" type="text">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${visualParameterWord}</label>
                            <div class="controls">
                                <p>
                                    <select name="visualParametersId">
                                    <c:forEach items="${visualParameters}" var="visualParameter">
                                    <option value="${visualParameter.id}">${visualParameter.colorLeaves}- ${visualParameter.colorSteam}</option>
                                    </c:forEach>
                                    </select>
                                </p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${madePlace}</label>
                            <div class="controls">
                                <p><select name="originId">
                                    <option disabled>Choose a</option>
                                    <c:forEach items="${origins}" var="origin">
                                <option value="${origin.id}">${origin.country}- ${origin.province}</option>
                                </c:forEach>
                                </select></p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${growingConditionWord}</label>
                            <div class="controls">
                                <p><select name="growingConditionId">
                                    <c:forEach items="${growingConditions}" var="growingCondition">
                                        <option value="${growingCondition.id}">${growingCondition.name}</option>
                                    </c:forEach>
                                </select></p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${flowerTypeWord}</label>
                            <div class="controls">

                                <p><select name="flowerTypeId">
                                    <option disabled></option>
                            <c:forEach items="${flowerTypes}" var="flowerType">
                                <option value="${flowerType.id}">${flowerType.name}</option>
                            </c:forEach>
                                </select></p>
                            </div>
                        </div>

                        <div class="control-group">
                           <div class="controls">
                                <c:forEach items="${errorMsg}" var="msg">
                                    <c:out value="${msg}"/><br>
                                </c:forEach>
                            </div>
                        </div>



                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls">
                                <button class="btn btn-success">${editProduct}</button>
                            </div>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>

    </jsp:attribute>
</t:autorized-user-template>
