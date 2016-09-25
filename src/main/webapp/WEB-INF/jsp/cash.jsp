<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>

<t:autorized-user-template>
    <jsp:attribute name="navbar">
        Your balance ${user.balance}<br>
        <form action="addMoneyToBalance" method="POST">
            Add money to balance <input type="text" size="8" name="money"><br>
            <button type="submit" name="add"><value>add money</value></button>
        </form>

        <c:forEach items="${errorMsg}" var="msg">
            <c:out value="${msg}"/><br>
        </c:forEach>
    </jsp:attribute>
</t:autorized-user-template>
