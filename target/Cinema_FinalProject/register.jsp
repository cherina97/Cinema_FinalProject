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
                    <form class="register-form" action="register" method="post">
                        <input name="firstName" type="text" placeholder="First name"
                               required
                               maxlength="10"
                               oninvalid="this.setCustomValidity('Cannot be empty')"
                               oninput="setCustomValidity('')"
                        />
                        <input name="lastName" type="text" placeholder="Last name"
                               required
                               maxlength="25"
                               oninvalid="this.setCustomValidity('Cannot be empty')"
                               oninput="setCustomValidity('')"
                        />
                        <input name="email" type="email" placeholder="Email"
                               required
                               pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
                               oninvalid="this.setCustomValidity('Email shod be like this: example@mail.com')"
                               oninput="setCustomValidity('')"
                        />
                        <input name="password" type="password" placeholder="Password"
                               required
                               minlength="4"
                               oninvalid="this.setCustomValidity('Password cannot be less than 4 symbols')"
                               oninput="setCustomValidity('')"
                        />
                        <button class="register" type="submit">Create</button>
                        <p class="message">Already registered? <a href="${pageContext.request.contextPath}/login">Sign
                            In</a></p>
                        <div class="error">
                            <p> ${requestScope.userPresent} </p>
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