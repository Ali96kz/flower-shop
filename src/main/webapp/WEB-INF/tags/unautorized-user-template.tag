<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="navbar" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Flower shop</title>
    <fmt:bundle  basename="i18n">
        <fmt:message key="template.basket" var="basket"/>
        <fmt:message key="template.vitrine" var="vitrine"/>
        <fmt:message key="template.about.project" var="aboutMyProject"/>
        <fmt:message key="template.sign.in" var="sign.in"/>
        <fmt:message key="template.sign.up" var="sign.up"/>
        <fmt:message key="template.contact" var="contact"/>
    </fmt:bundle>

    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css"/>
    </style>
    <style>
        <jsp:directive.include file="/WEB-INF/css/shop-homepage.css"/>
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main-page">Flower shop</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="contact">${contact}</a>
                </li>
                <li>
                    <a href="vitrine">${vitrine}</a>
                </li>
                <li>
                    <a href="set-language?lang=ru">Russian</a>
                </li>
                <li>
                    <a href="set-language?lang=en">English</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-3">
        <p class="lead">Ali</p>
        <div class="list-group">
            <a href="basket" class="list-group-item">${basket}</a>
        </div>

        <div class="list-group">
            <a href="login" class="list-group-item">${sign.in}</a>
        </div>

        <div class="list-group">
            <a href="registration" class="list-group-item">${sign.up}</a>
        </div>
    </div>
    <jsp:invoke fragment="navbar"/>
</div>


<div class="container">
    <hr>
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Java lab #19: Zhagparov Ali 2016</p>
            </div>
        </div>
    </footer>
</div>
<script>
    <jsp:directive.include file="/WEB-INF/js/jquery.js"/>
</script>

<script>
    <jsp:directive.include file="/WEB-INF/js/bootstrap.min.js"/>
</script>
</body>
</html>