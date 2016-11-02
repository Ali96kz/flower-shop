<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>


<t:autorized-user-template>
    <jsp:attribute name="navbar">
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="row">
                        Your balance ${user.balance}<br>
                        Add money to balance
                        <form action="addMoneyToBalance" method="POST">
                            <input type="text" size="8" name="money"><br>
                            <button type="submit" name="add">
                                <value>add money</value>
                            </button>
                            <br>
                        </form>
                        <c:forEach items="${errorMsg}" var="msg">

                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:autorized-user-template>
