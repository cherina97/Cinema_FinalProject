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

<div id="viewport">
    <!-- Sidebar -->
    <div id="sidebar">
        <header>
            <a href="#">Cinema</a>
        </header>
        <ul class="nav">
            <li>
                <a href="${pageContext.request.contextPath}/login">
                    <i class="zmdi zmdi-view-dashboard"></i> Login
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/register">
                    <i class="zmdi zmdi-link"></i> Registration
                </a>
            </li>


        </ul>
    </div>

        <!-- Content -->
        <div id="content">


        </div>
</div>

</body>
</html>