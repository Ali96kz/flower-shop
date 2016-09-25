<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--@elvariable id="user" type="com.epam.az.flower.shop.entity.User"--%>
<!doctype html>
<html lang="en">

<fmt:bundle basename="i18n">
    <fmt:message key="user.profile.balance" var="balance"/>
    <fmt:message key="user.profile.birthday.day" var="bitrthdayDay"/>
    <fmt:message key="user.profile.last.name" var="lastName"/>
    <fmt:message key="user.profile.delete" var="deleteAccount"/>
    <fmt:message key="user.profile.role" var="role"/>
    <fmt:message key="user.profile.first.name" var="name"/>
    <fmt:message key="user.profile.edit.account" var="editAccount"/>
    <fmt:message key="user.profile.logout" var="logout"/>
    <fmt:message key="user.profile.online.vitrine" var="vitrine"/>
    <fmt:message key="user.profile.cash" var="cash"/>
    <fmt:message key="user.profile.my.basket" var="basket"/>
</fmt:bundle>


<head>
    <meta charset="UTF-8">
    <title>${user.firstName} Profile</title>
    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css"/>
    </style>
    <!-- Custom CSS -->
    <style>
        <jsp:directive.include file="/WEB-INF/css/shop-homepage.css"/>
    </style>

</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Start Bootstrap</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#">About</a>
                </li>
                <li>
                    <a href="#">Services</a>
                </li>
                <li>
                    <a href="#">Contact</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-md-3">
            <p class="lead">Shop Name</p>
            <div class="list-group">
                <a href="profile" class="list-group-item active">Profile</a>
                <a href="vitrine" class="list-group-item">Online vitrine</a>
                <a href="login" class="list-group-item">login form</a>
                <a href="registration" class="list-group-item">registration form</a>
                <a href="basket" class="list-group-item">My basket</a>
                <a href="transaction" class="list-group-item">My transaction</a>
            </div>
        </div>

        <div class="col-md-9">

            <div class="thumbnail">
                <img class="img-responsive" src="http://placehold.it/800x300" alt="">
                <div class="caption-full">
                    <p>${name}:${user.firstName}</p>
                    <p>${lastName}:${user.nickName}</p>
                    <p>${bitrthdayDay}:${user.dateBirthday}</p>
                    <p>${balance}:${user.balance}</p>
                    <p>${role} :${user.userRole.name}</p>
                </div>
            </div>

        </div>

    </div>

</div>
<!-- /.container -->

<div class="container">
    <hr>
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Your Website 2014</p>
            </div>
        </div>
    </footer>

</div>
<!-- /.container -->

<!-- jQuery -->
<script>
    <jsp:directive.include file="/WEB-INF/js/jquery.js"/>
</script>

<!-- Bootstrap Core JavaScript -->
<script>
    <jsp:directive.include file="/WEB-INF/js/bootstrap.min.js"/>
</script>
</body>
</html>