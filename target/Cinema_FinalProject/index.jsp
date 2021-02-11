<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome</title>

    <link rel="stylesheet" href="css/index.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">
    <!-- Content -->
    <div id="content">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <c:choose>
                    <c:when test="${sessionScope.user ne null}">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.request.contextPath}/logout"> Logout </a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.request.contextPath}/cabinet.jsp"> Cabinet </a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.request.contextPath}/login"> Login </a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>



            </div>
        </nav>

        <div class="container-fluid">
            <jsp:include page="carousel.html"></jsp:include>
        </div>
    </div>
</div>

<script src="js/navbar.js"></script>
<script src="js/login.js"></script>

</body>
</html>