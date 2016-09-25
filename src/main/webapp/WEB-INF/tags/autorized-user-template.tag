<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="navbar" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.firstName} Profile</title>
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
            <a class="navbar-brand" href="vitrine">Flower shop</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="main-page">About my project</a>
                </li>
                <li>
                    <a href="contact">Contact</a>
                </li>
                <li>
                    <a href="vitrine">Product vitrine</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <p class="lead">Ali</p>
            <div class="list-group">
                <a href="profile" class="list-group-item active">Profile</a>
                <a href="basket" class="list-group-item">My basket</a>
                <a href="transaction" class="list-group-item">My transaction</a>
                <a href="cash" class="list-group-item">Cash</a>
                <a href="logout" class="list-group-item">Logout</a>
            </div>
        </div>
            <div class="row">
                <jsp:invoke fragment="navbar"/>
            </div>

        </div>
    </div>
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