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
                            <p>Made place : ${product.origin.country} - ${product.origin.province}</p>
                            <p>Price : ${product.price}</p>
                            <p>VisualParameters: </p>
                            <p>Leaves color : ${product.flower.visualParameters.colorLeaves}</p>
                            <p>Steam color : ${product.flower.visualParameters.colorSteam}</p>
                            <p>Average height : ${product.flower.averageHeight}</p>

                            <p>Growing condition: </p>
                            <p>Love light: ${product.flower.growingCondition.lovelight}</p>
                            <p>Temperature: ${product.flower.growingCondition.temperature.tmin} - ${product.flower.growingCondition.temperature.tmax}</p>
                            <p>Water in week: ${product.flower.growingCondition.waterInWeek.min} - ${product.flower.growingCondition.waterInWeek.max}</p>

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



