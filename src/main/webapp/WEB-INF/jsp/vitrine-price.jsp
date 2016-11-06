<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<fmt:bundle basename="i18n">
    <fmt:message key="product.buy.product" var="buyProduct"/>
    <fmt:message key="product.add.into.basket" var="addToBasket"/>
</fmt:bundle>

<t:autorized-user-template>
    <jsp:attribute name="navbar">
            <form method="POST">
                <p><select name="growingConditionId">
                    Price min
                    <input type="text" size="16" value="${product.price}" name="priceMin"
                           class="form-control input-lg">

                    Price max
                    <input type="text" size="16" value="${product.price}" name="priceMax"
                           class="form-control input-lg">
                </select></p>
                <button>filter</button>
            </form>
<!-- Page Content -->
<div class="container">

    <div class="row">
        <div class="col-md-9">
            <div class="row">
            <c:forEach items="${products}" var="product">
                <div class="col-sm-4 col-lg-4 col-md-4">
                    <div class="thumbnail">
                        <img src="http://placehold.it/320x150" alt="">
                        <div class="caption">
                            <h4 class="pull-right">${product.price} KZT</h4>
                            <h4><a href="product-inf?productId=${product.id}">${product.flower.name}</a></h4>
                            <p>
                                    ${product.description}
                                <br>
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
            </c:forEach>
                <c:forEach items="${pageList}" var="number">
                <a href="vitrine?page=${number}">
                        <c:out value="${number}"/>
                    </c:forEach>
            </div>
        </div>
    </div>
</div>
    </jsp:attribute>
</t:autorized-user-template>

