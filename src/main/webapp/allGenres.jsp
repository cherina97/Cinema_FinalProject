<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
    <meta>
    <title>All genres</title>

    <link rel="stylesheet" href="css/allSessions.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createFilm.css"/>

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
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Genre name</th>
                    <th>Genre name uk</th>
                </tr>
                </thead>

                <c:forEach var="genre" items="${requestScope.genres}">
                    <tbody>
                    <td>${genre.id}</td>
                    <td>${genre.genreName}</td>
                    <td>${genre.genreNameUK}</td>
                    </tbody>
                </c:forEach>
            </table>
        </main>
    </div>
</div>

<script src="js/navbar.js"></script>


</body>
</html>