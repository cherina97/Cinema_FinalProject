<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="cont">

    <div class="header">
        <jsp:include page="navbar.jsp"></jsp:include>
    </div>

    <div class="mainBlock">

        <main role="main" class="container">
            <div class="login-page">
                <div class="form">
                    <form class="login-form" action="login" method="post">
                        <input name="email" type="text" placeholder="Email"
                               required/>
                        <input name="password" type="password" placeholder="Password"
                               required/>
                        <button class="login">Login</button>
                        <p class="message">Not registered? <a href="${pageContext.request.contextPath}/register">Create an account</a></p>
                        <div class="error">
                            <p> ${requestScope.userError} </p>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </div>
</div>

<script src="js/navbar.js"></script>
<%--<script src="js/login.js"></script>--%>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


</body>
</html>