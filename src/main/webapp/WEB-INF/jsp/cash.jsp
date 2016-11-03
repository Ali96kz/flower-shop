<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<fmt:bundle basename="i18n">
    <fmt:message key="cash.your.add.money" var="addMoney"/>
    <fmt:message key="cash.your.balnce" var="yourBalance"/>
    <fmt:message key="cash.your.insert.money" var="insertMoney"/>
</fmt:bundle>

<t:autorized-user-template>
    <jsp:attribute name="navbar">
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="row">
                        ${yourBalance} ${user.balance}<br>
                        ${insertMoney}
                        <form action="addMoneyToBalance" method="POST">
                            <input type="text" size="8" name="money"><br>
                            <button type="submit" name="add">
                                <value>${addMoney}</value>
                            </button>
                            <br>
                        </form>
                        <c:forEach items="${errorMsg}" var="msg">
                             <fmt:bundle basename="i18n">
                                 <c:if test="${msg == error.cash.incorrect.money}"/>
                                 <font size="3" color="red"><fmt:message key="error.cash.incorrect.money"/></font><br/>
                             </fmt:bundle>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:autorized-user-template>
