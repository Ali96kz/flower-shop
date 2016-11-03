<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<fmt:bundle basename="i18n">
    <fmt:message key="bill.buy" var="buyProduct"/>
</fmt:bundle>

<t:autorized-user-template>
    <jsp:attribute name="navbar">
        <c:choose>
            <c:when test="${errorMsg ne null}">
            <fmt:bundle basename="i18n">
                <c:forEach items="${errorMsg}" var="msg">
                    <c:choose>
                        <c:when test="${msg eq 'error.havent.enough.money'}">
                            <font size="3" color="red"> <fmt:message
                                    key="error.havent.enough.money"/></font>
                        </c:when>
                          <c:when test="${msg eq 'error.buy.sign.in'}">
                              <font size="3" color="red"> <fmt:message key="error.buy.sign.in"/></font>
                          </c:when>
                    </c:choose>
                    <br>
                </c:forEach>
            </fmt:bundle>
            </c:when>
            <c:otherwise>${buyProduct}</c:otherwise>
    </c:choose>
    </jsp:attribute>
</t:autorized-user-template>
