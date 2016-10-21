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
    ${buyProduct}
    </jsp:attribute>
</t:autorized-user-template>
