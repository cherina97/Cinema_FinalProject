<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>All sessions</title>

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
            <div class="sortingButtons">
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=filmTitle"
                   role="button">By film title</a>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=freePlace"
                   role="button">By free place</a>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=date" role="button">By
                    date</a>
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/allSession?sortBy=allSession" role="button">All session</a>
            </div>

            <table class="table">
                <caption>All sessions</caption>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Film title</th>
                    <th>Start At</th>
                    <th>Week day</th>
                    <th>Tickets</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <c:forEach var="session" items="${requestScope.allSession}">
                    <tbody>
                    <td>${session.id}</td>
                    <td>${session.film.filmTitle}</td>
                    <td>${session.startAt} </td>
                    <td>${session.date} </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/allSession/tickets?id=${session.id}">
                            Tickets
                        </a>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/allSession/update?id=${session.id}">Update</a>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/allSession/delete?id=${session.id}">Delete</a>
                    </td>
                    </tbody>
                </c:forEach>
            </table>
        </main>
    </div>
</div>

<script src="js/navbar.js"></script>

</body>
</html>