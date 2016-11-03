<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="i18n">
    <fmt:message key="manager.product.description" var="description"/>
    <fmt:message key="manager.product.price" var="price"/>
    <fmt:message key="manager.product.delete" var="delete" />
    <fmt:message key="manager.product.quantity" var="quantity" />
    <fmt:message key="manager.product.name" var="productName" />
    <fmt:message key="manager.product.add" var="addNewProduct" />
    <fmt:message key="manager.product.edit" var="editProduct" />
    <fmt:message key="product.buy.product" var="buyProduct" />
    <fmt:message key="product.add.into.basket" var="addToBasket" />

    <fmt:message key="product.page.average.height" var="averageHeight" />
    <fmt:message key="product.page.temperature" var="temperature" />
    <fmt:message key="product.page.water.in.week" var="waterInWeek" />
    <fmt:message key="product.page.price" var="price" />
    <fmt:message key="product.page.lova.light" var="isLoveLight" />
    <fmt:message key="product.page.made.place" var="madePlace" />
    <fmt:message key="product.page.growing.condition" var="growingCondition" />
    <fmt:message key="product.page.visual.parameters" var="visualParameters" />
    <fmt:message key="product.page.leaf.color" var="leafColor" />
    <fmt:message key="product.page.steam.color" var="steamColor" />

</fmt:bundle>


<t:autorized-user-template>
    <jsp:attribute name="navbar">
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="thumbnail">
                        <img class="img-responsive" src="http://placehold.it/800x300" alt="">
                        <div class="caption-full">
                            <h4 class="pull-right">${product.price} KZT</h4>
                            <h4><a href="#">${product.flower.name}</a>
                            </h4>
                            <p>${madePlace}: ${product.origin.country} - ${product.origin.province}</p>
                            <p>${price}: ${product.price}</p>
                            <p>${visualParameters}: </p>
                            <p>${leafColor}: ${product.flower.visualParameters.colorLeaves}</p>
                            <p>${steamColor} : ${product.flower.visualParameters.colorSteam}</p>
                            <p>${averageHeight}: ${product.flower.averageHeight}</p>

                            <p>${growingCondition}: </p>
                            <p>${isLoveLight}: ${product.flower.growingCondition.lovelight}</p>
                            <p>${temperature}: ${product.flower.growingCondition.temperature.tmin} - ${product.flower.growingCondition.temperature.tmax}</p>
                            <p>${waterInWeek}: ${product.flower.growingCondition.waterInWeek.min} - ${product.flower.growingCondition.waterInWeek.max}</p>

                            <p>${product.description}</p>
                            <p>
                                <a href="product-in-basket?productId=${product.id}">
                                <c:out value="${addToBasket}"/><br>
                                </a>
                                <a href="buy-product?productId=${product.id}">
                                    <c:out value="${buyProduct}"/><br>
                                </a>
                            </p>

                </div>
                </div>
                </div>
            </div>

    </jsp:attribute>
</t:autorized-user-template>



