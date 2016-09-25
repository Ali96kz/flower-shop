<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="i18n">
    <fmt:message key="delete.msg" var="msg"/>
</fmt:bundle>

<t:unautorized-user-template>
    <jsp:attribute name="navbar">
        ${msg}
    </jsp:attribute>
</t:unautorized-user-template>