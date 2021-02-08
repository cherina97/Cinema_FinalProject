<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cabinet</title>

    <link rel="stylesheet" href="css/cabinet.css"/>
    <link rel="stylesheet" href="css/login.css"/>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>
</head>

<body>


<div id="viewport">

    <!-- Sidebar -->
    <jsp:include page="navbar.jsp"></jsp:include>

    <!-- Content -->
    <div id="content">

        <div id="wrapper">
            <div id="container">

                <div id="back"></div>

                <img src="css/img/22965342.jpg"/>

                <div id="info">

                    <h1> ${sessionScope.user.get().email} </h1>

                    <ul id="about">
                        <li><i class="fa fa-briefcase" aria-hidden="true"></i>${sessionScope.user.get().firstName}</li>
                        <li><i class="fa fa-heart" aria-hidden="true"></i>${sessionScope.user.get().lastName}</li>
                        <li><i class="fa fa-map-marker" aria-hidden="true"></i> Ukraine</li>
                        <li><i class="fa fa-map-marker" aria-hidden="true"></i>
                            <c:choose>
                                <c:when test="${sessionScope.user.get().roleId == 1}">
                                    USER
                                </c:when>
                                <c:otherwise>
                                    ADMIN
                                </c:otherwise>
                            </c:choose>

                        </li>
                    </ul>

                    <ul id="social">
                        <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                        <li><a href="#"><i class="fa fa-codepen" aria-hidden="true"></i></a></li>
                    </ul>

                </div>

            </div>
        </div>


    </div>




</div>
</body>
</html>