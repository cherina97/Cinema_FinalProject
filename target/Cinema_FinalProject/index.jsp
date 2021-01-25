<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<form action="register" method="post">

    <label for="firstName">First Name :</label> <input name="firstName" id="firstName">
    <br>
    <label for="lastName">Last Name :</label> <input name="lastName" id="lastName">
    <br>
    <label for="email">Email :</label> <input name="email" id="email">
    <br>
    <label for="password">Password : </label> <input name="password" id="password">
    <br>
    <input type="submit" value="submit">
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>