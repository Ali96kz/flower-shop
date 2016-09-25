<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product edit page</title>
</head>
<body>

<form method="POST">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <form class="form-horizontal"  method="POST">
                    <fieldset>
                        <div id="legend">
                            <legend class="">Register</legend>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${firstName}</label>
                            <div class="controls">
                                <input id="firstName" name="firstName" placeholder="" class="form-control input-lg" type="text">
                                <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${lastName}</label>
                            <div class="controls">
                                <input id="lastName" name="lastName" placeholder="" class="form-control input-lg" type="text">
                                <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${nickName}</label>
                            <div class="controls">
                                <input id="username" name="nickName" placeholder="" class="form-control input-lg" type="text">
                                <p class="help-block">date</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="username">${birhday}</label>
                            <div class="controls">
                                <input id="dateBirthday" name="dateBirthday" placeholder="" class="form-control input-lg" type="text">
                                <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password">Password</label>
                            <div class="controls">
                                <input id="password" name="password" placeholder="" class="form-control input-lg" type="password">
                                <p class="help-block">Password should be at least 6 characters</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password_confirm">Password (Confirm)</label>
                            <div class="controls">
                                <input id="password_confirm" name="confirmPassword" placeholder="" class="form-control input-lg" type="password">
                                <p class="help-block">Please confirm password</p>
                            </div>
                        </div>

                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls">
                                <button class="btn btn-success">Register</button>
                            </div>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>

    <c:forEach items="${errorMsg}" var="msg">
        <c:out value="${msg}"/><br>
    </c:forEach>

</form>
</body>
</html>
<%--    Flower name: <input type="text" size="16" value="${product.flower.name}" name="flowerName"><br>
    <br>
    Flower type:
    <p><select name="flowerTypeId">
        <option disabled>Choose a</option>
        <c:forEach items="${flowerTypes}" var="flowerType">
            <option value="${flowerType.id}">${flowerType.name}</option>
        </c:forEach>
    </select></p>

    <br>
    Description : <input type="text" size="16" value="${product.description}" name="description"><br>
    <br>

    Made place :
    <p><select name="originId">
        <option disabled>Choose a</option>
        <c:forEach items="${origins}" var="origin">
            <option value="${origin.id}">${origin.country}- ${origin.province}</option>
        </c:forEach>
    </select></p>

    Price : <input type="text" size="16" value="${product.price}" name="price"><br>

    VisualParameters:
    <p><select name="visualParametersId">
        <c:forEach items="${visualParameters}" var="visualParameters">
            <option value="${visualParameters.id}">${visualParameters.colorLeaves}- ${visualParameters.colorSteam}</option>
        </c:forEach>
    </select></p>

    Average height : <input type="text" size="16" value="${product.flower.averageHeight}"
                            name="averageHeight"><br>
    Growing Condition :
    Choose one of this:
    <p><select name="growingConditionId">
        <c:forEach items="${growingConditions}" var="growingCondition">
            <option value="${growingCondition.id}">${growingCondition.name}</option>
        </c:forEach>
    </select></p>
    <br>

    <button type="submit">
        <value>edit this product</value>
    </button>
--%>