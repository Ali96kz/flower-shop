<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>

<t:autorized-user-template>
    <jsp:attribute name="navbar">

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
                            <h4><a href="product-inf?productId=${product.id}">${product.flower.name}</a>
                            </h4>
                            <p>${product.description}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
        </div>
    </div>
</div>
    </jsp:attribute>
</t:autorized-user-template>

