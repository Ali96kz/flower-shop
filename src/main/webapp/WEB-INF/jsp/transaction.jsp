<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--@elvariable id="UserBalance" type="com.epam.az.flower.shop.entity.UserTransaction"--%>

<fmt:bundle basename="i18n">
    <fmt:message key="transaction.page.code.operation" var="operationCode"/>
    <fmt:message key="transaction.page.sum" var="transactionSum"/>
    <fmt:message key="transaction.page.date" var="date"/>
    <fmt:message key="transaction.page.number" var="transactionNumber"/>
    <fmt:message key="transaction.page.name" var="transactionName"/>
</fmt:bundle>

<t:autorized-user-template>
    <jsp:attribute name="navbar">
<div class="container">

    <div class="row">
        <div class="col-md-9">
            <div class="row">
            <c:forEach items="${transactions}" var="UserBalance">
                <p>
                        ${transactionNumber}:${UserBalance.id}<br>
                        ${transactionName}:<c:out value="${UserBalance.transaction.name}"/><br>
                        ${operationCode}:<c:out value="${UserBalance.transaction.id}"/><br>
                        ${transactionSum}:<c:out value="${UserBalance.sum}"/><br>
                        ${date}:<c:out value="${UserBalance.transactionDate}"/><br>
                </p>
            </c:forEach>
            </div>
        </div>
    </div>
</div>

</jsp:attribute>

</t:autorized-user-template>
