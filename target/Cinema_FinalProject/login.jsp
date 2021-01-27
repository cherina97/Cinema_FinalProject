<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>

    <link rel="stylesheet" href="css/login.css">
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div id="viewport">
    <div class="login-page">
        <div class="form">
            <form class="register-form">
                <input class="firstName" type="text" placeholder="First name"/>
                <input class="lastName" type="text" placeholder="Last name"/>
                <input class="email" type="email" placeholder="Email"/>
                <input class="registerPass" type="password" placeholder="Password"/>

                <button class="register">Create</button>
                <p class="message">Already registered? <a href="#">Sign In</a></p>
            </form>

            <form class="login-form">
                <input class="logEmail" type="text" placeholder="Email"/>
                <input class="loginPass" type="password" placeholder="Password"/>
                <button class="login">Login</button>
                <p class="message">Not registered? <a href="#">Create an account</a></p>
            </form>
        </div>
    </div>
</div>

<script src="js/login.js"></script>
</body>
</html>