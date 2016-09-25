<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css"/>
    </style>
    <!-- Custom CSS -->
    <style>
        <jsp:directive.include file="/WEB-INF/css/shop-homepage.css"/>
    </style>
    <title>${product.flower.name}</title>
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
            </div>
        </div>

        <div class="col-md-9">

            <div class="thumbnail">
                <img class="img-responsive" src="http://placehold.it/800x300" alt="">
                <div class="caption-full">
                    <h4 class="pull-right">${product.price} KZT</h4>
                    <h4><a href="#">${product.flower.name}</a>
                    </h4>
                    <p>Made place : ${product.origin.country} - ${product.origin.province}</p>
                    <p>Price : ${product.price}</p>
                    <p>VisualParameters: </p>
                    <p>Leaves color : ${product.flower.visualParameters.colorLeaves}</p>
                    <p>Steam color : ${product.flower.visualParameters.colorSteam}</p>
                    <p>Average height : ${product.flower.averageHeight}</p>

                    <p>Growing condition: </p>
                    <p>Love light: ${product.flower.growingCondition.lovelight}</p>
                    <p>Temperature: ${product.flower.growingCondition.temperature.tmin} - ${product.flower.growingCondition.temperature.tmax}</p>
                    <p>Water in week: ${product.flower.growingCondition.waterInWeek.min} - ${product.flower.growingCondition.waterInWeek.max}</p>

                    <p>${product.description}</p>
                    <p>
                        <a href="product-in-basket?productId=${product.id}">
                        <c:out value="add in basket"/><br>
                        </a>
                        <a href="buy-product?productId=${product.id}">
                            <c:out value="buy this product"/><br>
                        </a>
                    </p>



                </div>
               </div>

        </div>

    </div>

</div>
<!-- /.container -->

<div class="container">

    <hr>

    <!-- Footer -->
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
